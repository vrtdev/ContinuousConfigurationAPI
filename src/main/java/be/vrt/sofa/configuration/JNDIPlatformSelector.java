package be.vrt.sofa.configuration;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * {@link PlatformSelector} implementation, returning the platform identifier found in a JNDI bound value.
 * <p/>
 * The bound value should be a String.
 *
 * @author Mike Seghers
 */
public class JNDIPlatformSelector implements PlatformSelector<String> {
    /**
     * The root context in which to look for the bound value.
     */
    private Context rootContext;

    /**
     * The name of the bound value.
     */
    private String name;

    /**
     * Initializes the JNDI root context for looking up the bound value. The root context will be the JNDI initial
     * context.
     *
     * @param name the name of the bound value
     * @throws NamingException when initializing the root context fails
     */
    public JNDIPlatformSelector(final String name) throws NamingException {
        this.rootContext = new InitialContext();
        this.name = name;
    }

    /**
     * Initializes the JNDI root context for looking up the bound value. The root context will be a sub context derived
     * from the given rootContextName
     *
     * @param rootContextName The name of the root context, relative the the initial context
     * @param name            the name of the bound value
     * @throws NamingException when initializing the root context fails
     */
    public JNDIPlatformSelector(final String rootContextName, final String name) throws NamingException {
        this(name);
        this.rootContext = (Context) this.rootContext
                .lookup(rootContextName);
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
        try {
            return (String) rootContext.lookup(this.name);
        } catch (NamingException e) {
            throw new IllegalStateException("Could not get the platform identifier for name " + name, e);
        }
    }
}
