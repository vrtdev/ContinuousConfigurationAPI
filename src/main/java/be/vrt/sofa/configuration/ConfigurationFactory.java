package be.vrt.sofa.configuration;

/**
 * Defines a {@link be.vrt.sofa.configuration.Configuration} factory. A factory is capable of setting up a
 * Configuration. How it does this is up to the implementation.
 *
 * @param <T> The type of the platform identifier, identifying the platform on which the program is run.
 * @author Mike Seghers
 */
public interface ConfigurationFactory<T> {
    /**
     * Creates a configuration instance.
     *
     * @return the configuration instance.
     */
    Configuration<T> createConfiguration();
}
