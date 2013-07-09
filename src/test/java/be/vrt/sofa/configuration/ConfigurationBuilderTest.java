package be.vrt.sofa.configuration;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;
import java.util.Properties;

/**
 * User: vanlebr
 * Date: 13/09/11 9:46
 */
@RunWith(MockitoJUnitRunner.class)
public class ConfigurationBuilderTest {

    @Mock
    SystemHostPlatformSelector systemHostPlatformSelector;

    @Mock
    Properties properties;

    @Test
    public void testBuild(){
        ConfigurationBuilder<String> builder = new ConfigurationBuilder<String>(properties, systemHostPlatformSelector);
        Configuration<String> configuration = builder.build();

        Assert.assertNotNull(configuration);
        assertEqualsProperties(properties, configuration.getDefaultProperties());
        assertEqualsProperties(properties, configuration.getConfigurationProperties());
        Assert.assertTrue(configuration.getCustomizers().isEmpty());
    }

    @Test
    public void testBuildWithCustomizer(){
        ConfigurationBuilder<String> builder = new ConfigurationBuilder<String>(properties, systemHostPlatformSelector);
        Properties customProperties = new Properties();
        customProperties.put("customKey", "customValues");
        ConfigurationCustomizer<String> customizer = new ConfigurationCustomizerBuilder<String>(systemHostPlatformSelector.getPlatformIdentifier(), customProperties).build();
        builder.withCustomizer(customizer);
        Configuration<String> configuration = builder.build();
        Map<String, ConfigurationCustomizer<String>> map = configuration.getCustomizers();

        Assert.assertNotNull(configuration);
        Assert.assertEquals(properties.size() + customProperties.size(), configuration.getConfigurationProperties().size());
        assertEqualsProperties(properties, configuration.getDefaultProperties());
        Assert.assertEquals(1, map.size());
        Assert.assertEquals(customizer, map.get(systemHostPlatformSelector.getPlatformIdentifier()));
    }

    public void assertEqualsProperties(Properties expected, Properties actual){
        Assert.assertEquals(expected.size(), actual.size());
        while(expected.keySet().iterator().hasNext()){
            Assert.assertEquals(expected.keySet().iterator().next(), actual.keySet().iterator().next());
        }
        while(expected.values().iterator().hasNext()){
            Assert.assertEquals(expected.values().iterator().next(), actual.values().iterator().next());
        }
    }
}
