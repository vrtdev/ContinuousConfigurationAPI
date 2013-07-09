package be.vrt.sofa.configuration;

/**
 * Abstract implementation of the {@link ConfigurationCustomizer} interface. Provided for convenience to hold the
 * platform identifier.
 *
 * @param <T> The platform identifier, usually provided by a {@link PlatformSelector}
 * @author Mike Seghers
 */
public abstract class AbstractConfigurationCustomizer<T> implements ConfigurationCustomizer<T> {
    /**
     * The platform identifier, identifying the platform for which this customizer can customize properties.
     */
    private final T forPlatform;

    /**
     * Initializes the customizer for the platform identified by the forPlatform parameter.
     *
     * @param forPlatform the platform identifier
     */
    protected AbstractConfigurationCustomizer(final T forPlatform) {
        this.forPlatform = forPlatform;
    }

    public final T getForPlatform() {
        return forPlatform;
    }
}
