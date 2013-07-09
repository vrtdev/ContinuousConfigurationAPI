package be.vrt.sofa.configuration;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * User: vanlebr
 * Date: 13/09/11 9:46
 */
@RunWith(MockitoJUnitRunner.class)
public class SystemPropertyPlatformSelectorTest {

    @Test
    public void testGetPlatform(){
        SystemPropertyPlatformSelector selector = new SystemPropertyPlatformSelector("testPlatformIdentifier");
        System.setProperty("testPlatformIdentifier", "testPlatform");
        Assert.assertEquals("testPlatform", selector.getPlatformIdentifier());
    }

    @Test
    public void testGetPlatformNonExistent(){
        SystemPropertyPlatformSelector selector = new SystemPropertyPlatformSelector("th3_chanc3_of_this_3xisting_is_v3ry_sma11");
        Assert.assertNull(selector.getPlatformIdentifier());
    }

    @Test(expected = NullPointerException.class)
    public void testGetPlatformNullPointerException(){
        SystemPropertyPlatformSelector selector = new SystemPropertyPlatformSelector(null);
        selector.getPlatformIdentifier();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetPlatformIllegalArgumentException(){
        SystemPropertyPlatformSelector selector = new SystemPropertyPlatformSelector("");
        selector.getPlatformIdentifier();
    }
}
