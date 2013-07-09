package be.vrt.sofa.configuration;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Properties;

/**
 * User: vanlebr
 * Date: 13/09/11 9:46
 */
@RunWith(MockitoJUnitRunner.class)
public class ConfigurationCustomizerBuilderTest {

    @Test
    public void testBuild(){
        Properties customProperties = Mockito.mock(Properties.class);
        ConfigurationCustomizerBuilder<String> builder = new ConfigurationCustomizerBuilder<String>("platformIdentifier", customProperties);
        ConfigurationCustomizer<String> customizer = builder.build();
        Properties properties = Mockito.mock(Properties.class);
        customizer.customize(properties);
        Assert.assertNotNull(customizer);
        Assert.assertEquals("platformIdentifier", customizer.getForPlatform());
        Mockito.verify(properties).putAll(customProperties);
    }

    @Test
    public void testBuildPlatformNull(){
        Properties customProperties = Mockito.mock(Properties.class);
        ConfigurationCustomizerBuilder<String> builder = new ConfigurationCustomizerBuilder<String>(null, customProperties);
        ConfigurationCustomizer<String> customizer = builder.build();
        Properties properties = Mockito.mock(Properties.class);
        customizer.customize(properties);
        Assert.assertNotNull(customizer);
        Assert.assertNull(customizer.getForPlatform());
        Mockito.verify(properties).putAll(customProperties);
    }

    @Test
    public void testBuildPropertiesNull(){
        ConfigurationCustomizerBuilder<String> builder = new ConfigurationCustomizerBuilder<String>("platformIdentifier", null);
        ConfigurationCustomizer<String> customizer = builder.build();
        Properties properties = Mockito.mock(Properties.class);
        customizer.customize(properties);
        Assert.assertNotNull(customizer);
        Assert.assertEquals("platformIdentifier", customizer.getForPlatform());
        Mockito.verify(properties).putAll(null);
    }

    @Test
    public void testBuildPlatformNullPropertiesNull(){
        ConfigurationCustomizerBuilder<String> builder = new ConfigurationCustomizerBuilder<String>(null, null);
        ConfigurationCustomizer<String> customizer = builder.build();
        Properties properties = Mockito.mock(Properties.class);
        customizer.customize(properties);
        Assert.assertNotNull(customizer);
        Assert.assertNull(customizer.getForPlatform());
        Mockito.verify(properties).putAll(null);
    }

    @Test(expected = NullPointerException.class)
    public void testBuildNullPointerException(){
        Properties customProperties = Mockito.mock(Properties.class);
        ConfigurationCustomizerBuilder<String> builder = new ConfigurationCustomizerBuilder<String>(null, customProperties);
        ConfigurationCustomizer<String> customizer = builder.build();
        customizer.customize(null);
    }
}
