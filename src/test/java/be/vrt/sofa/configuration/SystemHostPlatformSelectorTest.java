package be.vrt.sofa.configuration;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.net.InetAddress;

/**
 * User: vanlebr
 * Date: 13/09/11 9:46
 */
@RunWith(MockitoJUnitRunner.class)
public class SystemHostPlatformSelectorTest {

    private InetAddress address;


    @Before
    public void setup() throws Exception{
        address = InetAddress.getLocalHost();
    }

    @After
    public void tearDown(){
        address = null;
    }

    @Test
    public void testGetPlatformConstructor(){
        //uses hostAddress
        SystemHostPlatformSelector selector = new SystemHostPlatformSelector();
        String platform = selector.getPlatformIdentifier();
        Assert.assertEquals(address.getHostAddress(), platform);
    }

    @Test
    public void testGetPlatformHostConstructorTrue(){
        //uses hostName
        SystemHostPlatformSelector selector = new SystemHostPlatformSelector(true);
        String platform = selector.getPlatformIdentifier();
        Assert.assertEquals(address.getHostName(), platform);
    }

    @Test
    public void testGetPlatformHostConstructorFalse(){
        //uses hostAddress
        SystemHostPlatformSelector selector = new SystemHostPlatformSelector(false);
        String platform = selector.getPlatformIdentifier();
        Assert.assertEquals(address.getHostAddress(), platform);
    }

    @Test
    public void testGetPlatformPortConstructor(){
        //uses hostAddress and port
        SystemHostPlatformSelector selector = new SystemHostPlatformSelector(80);
        String platform = selector.getPlatformIdentifier();
        Assert.assertEquals(address.getHostAddress() + ":80", platform);
    }

    @Test
    public void testGetPlatformHostPortConstructorTrue(){
        //uses hostName and port
        SystemHostPlatformSelector selector = new SystemHostPlatformSelector(true, 80);
        String platform = selector.getPlatformIdentifier();
        Assert.assertEquals(address.getHostName() + ":80", platform);
    }

    @Test
    public void testGetPlatformHostPortConstructorFalse(){
        //uses hostAddress and port
        SystemHostPlatformSelector selector = new SystemHostPlatformSelector(false, 80);
        String platform = selector.getPlatformIdentifier();
        Assert.assertEquals(address.getHostAddress() + ":80", platform);
    }
}
