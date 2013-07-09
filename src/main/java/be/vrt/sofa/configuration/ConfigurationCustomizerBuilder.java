package be.vrt.sofa.configuration;

import java.util.Properties;

/**
 * Configuration Customizer builder. Provides a fluent API to build configuration customizers. You must provide the
 * platform identifier and properties for extending/overriding the defaults to create an instance of this builder.
 *
 * @param <T> The type of the platform identifier
 * @author Mike Seghers
 */
public class ConfigurationCustomizerBuilder<T> {

    /**
     * The platform identifier for which we want to build a customizer.
     */
    private T forPlatform;

    /**
     * The customized properties.
     */
    private Properties customized;

    /**
     * Initializes a builder for the given platform identifier and customized properties.
     *
     * @param forPlatform the platform identifier
     * @param customized  the properties to use for extension/overriding the defaults
     */
    public ConfigurationCustomizerBuilder(final T forPlatform, final Properties customized) {
        this.forPlatform = forPlatform;
        this.customized = customized;
    }

    /**
     * Builds a {@link ConfigurationCustomizer}, using the properties set on this instance.
     *
     * @return a configuration customizer
     */
    public ConfigurationCustomizer<T> build() {
        return new AbstractConfigurationCustomizer<T>(forPlatform) {

            @Override
            public void customize(final Properties properties) {
                properties.putAll(customized);
            }
        };
    }
}
