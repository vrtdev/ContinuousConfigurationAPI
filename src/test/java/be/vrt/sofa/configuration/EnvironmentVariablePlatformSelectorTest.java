package be.vrt.sofa.configuration;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;

/**
 * User: vanlebr
 * Date: 13/09/11 9:46
 */
@RunWith(MockitoJUnitRunner.class)
public class EnvironmentVariablePlatformSelectorTest {

    @Test
    public void testGetPlatform(){
        String key = null;
        String value = null;
        Map<String, String> env = System.getenv();
        if(env.size()>0){
            key = env.keySet().iterator().next();
            value = env.get(key);
        }
        EnvironmentVariablePlatformSelector selector = new EnvironmentVariablePlatformSelector(key);
        Assert.assertEquals(value, selector.getPlatformIdentifier());
    }

    @Test
    public void testGetPlatformNonExistent(){
        EnvironmentVariablePlatformSelector selector = new EnvironmentVariablePlatformSelector("th3_chanc3_of_this_3xisting_is_v3ry_sma11");
        Assert.assertNull(selector.getPlatformIdentifier());
    }

    @Test(expected = NullPointerException.class)
    public void testGetPlatformNullPointerException(){
        EnvironmentVariablePlatformSelector selector = new EnvironmentVariablePlatformSelector(null);
        selector.getPlatformIdentifier();
    }
}
