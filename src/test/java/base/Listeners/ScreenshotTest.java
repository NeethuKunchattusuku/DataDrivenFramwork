package base.Listeners;

import base.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CustomListeners.class)
public class ScreenshotTest extends TestBase {

    @BeforeMethod
    public void setupforscreenshot()
    {
        setUp();
    }

    @AfterMethod
    public void teardownmethod()
    {
      tearDown();
    }

    @Test
    public void takescreenshottest()
    {

    }

}
