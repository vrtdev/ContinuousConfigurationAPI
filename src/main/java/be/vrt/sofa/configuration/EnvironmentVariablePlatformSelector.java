package be.vrt.sofa.configuration;

/**
 * {@link PlatformSelector} implementation, returning the platform identifier found in an environment variable. An
 * environment variable is provided through system settings (How this is done depends on your OS). Example: If you have
 * defined an environment variable <code>platform.identifier</code> with the value <code>PROD</code>, then the returned
 * identifier by this selector is "PROD".
 *
 * @author Mike Seghers
 */
public class EnvironmentVariablePlatformSelector implements PlatformSelector<String> {
    /**
     * The name of the environment variable.
     */
    private String environmentVariableName;

    /**
     * Initializes a SystemPropertyPlatformSelector with the given system property name.
     *
     * @param environmentVariableName The name of the environment variable
     */
    public EnvironmentVariablePlatformSelector(final String environmentVariableName) {
        this.environmentVariableName = environmentVariableName;
    }

    /**
     * Gets the value of the specified environmentVariableName variable.
     *
     * @return the string value of the environmentVariableName, or <code>null</code>
     *         if the environmentVariableName is not defined in the system properties
     * @throws NullPointerException if <code>environmentVariableName</code> is <code>null</code>
     */
    @Override
    public String getPlatformIdentifier() {
        return System.getenv(environmentVariableName);
    }

}
