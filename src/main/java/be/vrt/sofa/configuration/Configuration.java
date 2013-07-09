package be.vrt.sofa.configuration;

import java.util.Map;
import java.util.Properties;

/**
 * Defines a Configuration interface. Configurations should be able to return configuration properties to be used by
 * the program.
 *
 * @param <T> The type of the platform identifier, identifying the platform on which the program is run.
 * @author Mike Seghers
 */
public interface Configuration<T> {
    /**
     * Returns the configuration properties for the platform on which the program is run.
     *
     * @return The configuration properties for the platform on which the program is run.
     */
    Properties getConfigurationProperties();

    /**
     * Implementations must return a map of customizers. The key is the platform identifier, the value is the
     * customizer
     * for that platform. A customizer is capable of overriding and/or extending default properties.
     *
     * @return a map of {@link AbstractConfigurationCustomizer}s identified by the platform identifier for which the
     *         customizer is customizing
     * @see be.vrt.sofa.configuration.Configuration#getDefaultProperties()
     */
    Map<T, ConfigurationCustomizer<T>> getCustomizers();

    /**
     * Get the default properties. The default properties might contain sensible defaults, valid for all systems, or
     * valid when the platform cannot be found. These default properties might be extended and/or overridden by a
     * customizer. The customizer will override properties when they already exist in these defaults.
     *
     * @return the default properties for the program.
     */
    Properties getDefaultProperties();
}
