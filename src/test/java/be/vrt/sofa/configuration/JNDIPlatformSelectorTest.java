package be.vrt.sofa.configuration;

import be.vrt.sofa.jndi.SimpleInitialContextFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.naming.Context;
import javax.naming.NamingException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author Mike Seghers
 */
@RunWith(MockitoJUnitRunner.class)
public class JNDIPlatformSelectorTest {
    private JNDIPlatformSelector selector;

    @Mock
    private Context context;

    @Mock
    private Context rootContext;

    @Test
    public void testGetPlatformIdentifier() throws Exception {
        SimpleInitialContextFactory.threadLocal
                                   .set(context);

        when(context.lookup("val")).thenReturn("result");

        selector = new JNDIPlatformSelector("val");

        String identifier = selector.getPlatformIdentifier();
        assertThat(identifier, is("result"));

        SimpleInitialContextFactory.threadLocal
                                   .remove();
    }

    @Test
    public void testGetPlatformIdentifierWithRootContext() throws Exception {
        SimpleInitialContextFactory.threadLocal
                                   .set(context);
        when(context.lookup("root/")).thenReturn(rootContext);
        when(rootContext.lookup("val")).thenReturn("result");

        selector = new JNDIPlatformSelector("root/", "val");

        String identifier = selector.getPlatformIdentifier();
        assertThat(identifier, is("result"));

        SimpleInitialContextFactory.threadLocal
                                   .remove();
    }

    @Test(expected = IllegalStateException.class)
    public void testGetPlatformIdentifierNamingException() throws Exception {
        when(context.lookup("val")).thenThrow(NamingException.class);

        SimpleInitialContextFactory.threadLocal
                                   .set(context);


        selector = new JNDIPlatformSelector("val");

        selector.getPlatformIdentifier();

        SimpleInitialContextFactory.threadLocal
                                   .remove();
    }
}
