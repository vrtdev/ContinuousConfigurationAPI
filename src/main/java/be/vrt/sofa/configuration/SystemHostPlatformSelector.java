package be.vrt.sofa.configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * {@link PlatformSelector} implementation, generating the platform identifier by looking at the host on which the
 * program is running. Optionally a port number can also be given to add to the generated platform identifier. The host
 * identifier can be the IP-address of the current host (default) or the host name. Examples:
 * <ul>
 * <li>123.123.123.123 (useHostName property = false, port property = null)</li>
 * <li>123.123.123.123:8080 (useHostName property = false, port property = 8080)</li>
 * <li>server.host.com (useHostName property = true, port property = null)</li>
 * <li>server.host.com:8080 (useHostName property = true, port property = 8080)</li>
 * </ul>
 *
 * @author Mike Seghers
 */
public class SystemHostPlatformSelector implements PlatformSelector<String> {

    /**
     * If true, the host name should be resolved, otherwise the IP address will be used.
     */
    private boolean useHostName;

    /**
     * If set, also include the port number in the eventual platform identifier in the form of
     * <code>hostname:port</code>.
     */
    private Integer port;

    /**
     * Initializes a default SystemHostPlatformSelector (the generated identifier will be <i>IP-address</i>, e.g.:
     * <i>123.123.123.123</i>).
     */
    public SystemHostPlatformSelector() {
        this(false, null);
    }

    /**
     * Initializes a SystemHostPlatformSelector, defining the use host name flag. If true the generated identifier will
     * be <i>host name</i>, e.g.: <i>server.host.com</i>. If false the generated identifier will be <i>IP-address</i>,
     * e.g.: <i>123.123.123.123</i> which is the default.
     *
     * @param useHostName If true, the host name should be resolved to be included in the identifier, otherwise the IP
     *                    address will be used.
     */
    public SystemHostPlatformSelector(final boolean useHostName) {
        this(useHostName, null);
    }

    /**
     * Initializes a SystemHostPlatformSelector, defining the port number to be used in the identifier. The generated
     * identifier will be <i>IP-address:portnumber</i>, e.g.: <i>123.123.123.123:8080</i>
     *
     * @param port the port number to use in the generated identifier
     */
    public SystemHostPlatformSelector(final Integer port) {
        this(false, port);
    }

    /**
     * Initializes a {@link SystemHostPlatformSelector}, defining the user name flag and the port number to be used in
     * the generated identifier. The generated identifier will be <i>IP-address:portnumber</i>, e.g.:
     * <i>123.123.123.123:8080</i> if the use host name flag is false. Otherwise the generated identifier will be
     * <i>hostname:portnumber</i>, e.g.: <i>server.host.com:8080</i>
     *
     * @param useHostName the use host name flag
     * @param port        the port number to use in the generated identifier
     */
    public SystemHostPlatformSelector(final boolean useHostName, final Integer port) {
        this.useHostName = useHostName;
        this.port = port;
    }

    @Override
    public String getPlatformIdentifier() {
        StringBuilder platform = new StringBuilder();
        try {
            InetAddress addr = InetAddress.getLocalHost();

            if (useHostName) {
                platform.append(addr.getHostName());
            } else {
                platform.append(addr.getHostAddress());
            }
        } catch (UnknownHostException e) {
            platform.append("unknown");
        }

        if (port != null) {
            platform.append(":")
                    .append(port);
        }

        return platform.toString();
    }

}
