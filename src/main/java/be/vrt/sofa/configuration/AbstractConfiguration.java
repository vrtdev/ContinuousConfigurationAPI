package be.vrt.sofa.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Abstract configuration class holding default configuration properties (given by the implementation),
 * extended and/or overridden with a platform customizer. The customizer to be used is derived from a list of
 * {@link AbstractConfigurationCustomizer}s given by the implementation and the given {@link PlatformSelector}. To
 * build a configuration from scratch, you can (and should) use the fluent {@link ConfigurationBuilder} API.
 *
 * @param <T> The type of the {@link PlatformSelector}'s platform objects.
 * @author Mike Seghers
 * @see ConfigurationBuilder
 */
public abstract class AbstractConfiguration<T> implements Configuration<T> {
    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractConfiguration.class);

    /**
     * The configuration properties for the selected platform.
     */
    private Properties configurationProperties;

    /**
     * Initializes the configuration properties based on the default properties, the platform returned by the
     * {@link PlatformSelector} and the {@link AbstractConfigurationCustomizer}s.
     *
     * @param platformSelector The platform selector, responsible for returning the current platform on which the
     *                         program is running.
     */
    public AbstractConfiguration(final PlatformSelector<T> platformSelector) {
        configurationProperties = getDefaultProperties();

        ConfigurationCustomizer<T> customizer = getCustomizers().get(platformSelector.getPlatformIdentifier());
        if (customizer != null) {
            customizer.customize(configurationProperties);
        }

        LOGGER.info("Configuration setup with properties {}", configurationProperties);
    }

    @Override
    public Properties getConfigurationProperties() {
        return configurationProperties;
    }
}
