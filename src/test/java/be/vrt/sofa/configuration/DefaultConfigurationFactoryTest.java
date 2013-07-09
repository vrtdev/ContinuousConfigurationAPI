package be.vrt.sofa.configuration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Mike Seghers
 */
@RunWith(MockitoJUnitRunner.class)
public class DefaultConfigurationFactoryTest {
    private DefaultConfigurationFactory<String> factory;

    @Before
    public void setUp() throws Exception {
        factory = new DefaultConfigurationFactory<String>();
    }

    @Test
    public void testCreateConfigurationDefaultSettings() throws Exception {
        Configuration<String> configuration = factory.createConfiguration();
        Properties config = configuration.getConfigurationProperties();
        assertThat(config, is(notNullValue()));
    }

    @Test
    public void testCreateConfigurationFullOverride() throws Exception {
        Map<String, List<Properties>> customizers = new HashMap<String, List<Properties>>();
        Properties customizedProp1 = new Properties();
        customizedProp1.put("a", "b");
        customizers.put("platform1", Arrays.asList(customizedProp1));


        factory.setCustomizedProperties(customizers);
        Properties defaultProps = new Properties();
        defaultProps.put("a", "c");
        factory.setDefaultProperties(Arrays.asList(defaultProps));
        PlatformSelector<String> selector = Mockito.mock(PlatformSelector.class);
        factory.setPlatformSelector(selector);
        Configuration<String> configuration = factory.createConfiguration();
        Properties config = configuration.getConfigurationProperties();
        assertThat(config, is(notNullValue()));
        assertThat((String) config.get("a"), is("c"));
    }
}
