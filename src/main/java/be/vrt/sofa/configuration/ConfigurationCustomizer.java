package be.vrt.sofa.configuration;

import java.util.Properties;

/**
 * A configuration customizer provides custom properties for a given platform (identified by the platform identifier,
 * known to the system).
 *
 * @param <T> The platform identifier, usually provided by a {@link PlatformSelector}
 * @author Mike Seghers
 */
public interface ConfigurationCustomizer<T> {
    /**
     * Implementations can customize the given properties. The implementation decides whether it overrides the already
     * existing properties.
     *
     * @param properties the properties to be customized
     */
    void customize(final Properties properties);

    /**
     * Returns the platform identifier, identifying the platform for which this customizer can customize properties.
     *
     * @return the platform identifier, identifying the platform for which this customizer can customize properties
     */
    T getForPlatform();
}
