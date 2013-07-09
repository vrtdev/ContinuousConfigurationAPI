package be.vrt.sofa.configuration;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

/**
 * The default {@link be.vrt.sofa.configuration.ConfigurationFactory} implementation. This class is useful in
 * environments that can work better through the factory pattern. Internally, this class uses the {@link
 * be.vrt.sofa.configuration.ConfigurationBuilder} to set up a {@link be.vrt.sofa.configuration.Configuration}. It also
 * sets up a default {@link be.vrt.sofa.configuration.SystemHostPlatformSelector} which looks for a platform.id
 * property
 * in the system properties to get the platform identifier. You can override this default by calling the {@link
 * DefaultConfigurationFactory#setPlatformSelector(PlatformSelector)}
 * <p/>
 * If you can directly use the {@link be.vrt.sofa.configuration.ConfigurationBuilder} your probably should.
 *
 * @param <T> The type of the {@link PlatformSelector}'s platform objects.
 * @author Mike Seghers
 */
public class DefaultConfigurationFactory<T> implements ConfigurationFactory {

    /**
     * The platform selector which will be used by the {@link be.vrt.sofa.configuration.Configuration}.
     */
    private PlatformSelector<?> platformSelector = new SystemPropertyPlatformSelector("platform.id");

    /**
     * The customized properties, used to override and/or extend the default properties.
     */
    private Map<T, List<Properties>> customizedProperties = Collections.emptyMap();

    /**
     * The default properties.
     */
    private Properties defaults = new Properties();

    public void setPlatformSelector(final PlatformSelector<T> platformSelector) {
        this.platformSelector = platformSelector;
    }

    public void setCustomizedProperties(final Map<T, List<Properties>> customizedProperties) {
        this.customizedProperties = customizedProperties;
    }

    /**
     * Merges all properties in the separate lists into the default properties.
     * @param defaultProperties the default properties to set
     */
    public void setDefaultProperties(final List<Properties> defaultProperties) {
        if (defaultProperties != null) {
            for (Properties properties : defaultProperties) {
                defaults.putAll(properties);
            }
        }
    }

    @Override
    public Configuration<T> createConfiguration() {
        @SuppressWarnings("unchecked")
        ConfigurationBuilder<T> builder = new ConfigurationBuilder<T>(defaults, (PlatformSelector<T>) platformSelector);

        for (Entry<T, List<Properties>> entry : customizedProperties.entrySet()) {
            Properties customized = new Properties();
            for (Properties properties : entry.getValue()) {
                customized.putAll(properties);
            }

            builder.withCustomizer(new ConfigurationCustomizerBuilder<T>(entry.getKey(), customized).build());
        }

        return builder.build();
    }
}
