package be.vrt.sofa.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Configuration builder. Provides a fluent API to build configurations for all known platforms of a program. You must
 * provide default properties and a {@link PlatformSelector} to create an instance of this builder. To further add
 * customizer you call the {@link ConfigurationBuilder#withCustomizer(ConfigurationCustomizer)}
 *
 * @param <T> The type of the platform identifier
 * @author Mike Seghers
 */
public class ConfigurationBuilder<T> {
    /**
     * The default properties to start with.
     */
    private Properties properties;

    /**
     * The platform selector.
     */
    private PlatformSelector<T> platformSelector;

    /**
     * The customizers for each known platform.
     */
    private List<ConfigurationCustomizer<T>> customizers;

    /**
     * Initializes a configuration builder with the given default properties and the given {@link PlatformSelector}.
     *
     * @param defaults         the default configuration properties.
     * @param platformSelector the platform selector
     */
    public ConfigurationBuilder(final Properties defaults, final PlatformSelector<T> platformSelector) {
        this.properties = defaults;
        this.platformSelector = platformSelector;
        customizers = new ArrayList<ConfigurationCustomizer<T>>();
    }

    /**
     * Add a customizer to the builder.
     *
     * @param customizer the customizer to add.
     * @return this builder, for chaining.
     */
    public ConfigurationBuilder<T> withCustomizer(final ConfigurationCustomizer<T> customizer) {
        customizers.add(customizer);
        return this;
    }

    /**
     * Builds a {@link Configuration} with this builder's platform selector, default properties and customizers.
     *
     * @return a configuration instance
     */
    public Configuration<T> build() {
        return new AbstractConfiguration<T>(platformSelector) {

            @Override
            public Map<T, ConfigurationCustomizer<T>> getCustomizers() {
                HashMap<T, ConfigurationCustomizer<T>> map = new HashMap<T, ConfigurationCustomizer<T>>();
                if (customizers != null) {
                    for (ConfigurationCustomizer<T> customizer : customizers) {
                        map.put(customizer.getForPlatform(), customizer);
                    }
                }
                return map;
            }

            @Override
            public Properties getDefaultProperties() {
                Properties defaults = new Properties();
                defaults.putAll(properties);
                return defaults;
            }

        };
    }
}
