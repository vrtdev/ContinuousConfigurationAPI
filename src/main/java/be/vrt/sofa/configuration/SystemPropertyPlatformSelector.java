package be.vrt.sofa.configuration;

/**
 * {@link PlatformSelector} implementation, returning the platform identifier found in a system property. A system
 * property is provided to the JVM by using the -D parameter. Example: If you start your program using
 * <code>java YourProgram -Dplatform.identifier=PROD</code>, then the returned identifier by this selector is "PROD".
 *
 * @author Mike Seghers
 */
public class SystemPropertyPlatformSelector implements PlatformSelector<String> {

    /**
     * The name of the system property.
     */
    private String systemPropertyName;

    /**
     * Initializes a SystemPropertyPlatformSelector with the given system property name.
     *
     * @param systemPropertyName The name of the system property
     */
    public SystemPropertyPlatformSelector(final String systemPropertyName) {
        this.systemPropertyName = systemPropertyName;
    }

    /**
     * Gets the value of the specified systemPropertyName variable.
     *
     * @return the string value of the systemPropertyName, or <code>null</code>
     *         if the systemProperty is not defined in the system properties
     * @throws NullPointerException     if <code>systemPropertyName</code> is <code>null</code>
     * @throws IllegalArgumentException if <code>systemPropertyName</code> is an empty string
     */
    @Override
    public String getPlatformIdentifier() {
        return System.getProperty(systemPropertyName);
    }

}
