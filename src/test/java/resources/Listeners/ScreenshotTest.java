package resources.Listeners;

import base.Listeners.CustomListeners;
import base.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

@Listeners(CustomListeners.class)
public class ScreenshotTest extends TestBase {

    @BeforeMethod
    public void setupforscreenshot()
    {
        setUp();
    }

    @AfterMethod
    public void teardownmethod() throws IOException {
      tearDown();
    }

    @Test
    public void takescreenshottest()
    {

    }

}
