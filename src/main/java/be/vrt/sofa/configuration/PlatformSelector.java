package be.vrt.sofa.configuration;

/**
 * A platform selector is responsible for generating a platform identifier for the platform on which the current
 * program
 * is running. This can than be used to decide which configuration should be loaded.
 *
 * @param <T> the type of the platform identifier, usually a String
 * @author Mike Seghers
 */
public interface PlatformSelector<T> {
    /**
     * Get the platform identifier for the current platform on which the program is running.
     *
     * @return the platform identifier for the current platform.
     */
    T getPlatformIdentifier();
}
