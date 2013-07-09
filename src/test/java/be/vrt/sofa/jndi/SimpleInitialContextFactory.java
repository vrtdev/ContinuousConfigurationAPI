package be.vrt.sofa.jndi;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;
import java.util.Hashtable;

/**
 * @author Mike Seghers
 */
public class SimpleInitialContextFactory implements InitialContextFactory {
    public static final ThreadLocal<Context> threadLocal = new ThreadLocal<Context>();


    @Override
    public Context getInitialContext(Hashtable<?, ?> hashtable) throws NamingException {
        return threadLocal.get();
    }
}
