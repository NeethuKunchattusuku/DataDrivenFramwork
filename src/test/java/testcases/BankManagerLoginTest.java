package testcases;

import base.Listeners.CustomListeners;
import base.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

@Listeners(CustomListeners.class)
public class BankManagerLoginTest extends TestBase {

    @Test
    public void loginAsBankManager() throws IOException {
        System.setProperty("org.uncommon.reportng.escape-output","false");
        driver.findElement(By.xpath(OR.getProperty("bmlbutton"))).click();
        log.debug("Clicked on the Bank Manager button");


    }

    @Test
    public void verifylandingpage() throws InterruptedException {
        Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Addcustomer"))));
        driver.findElement(By.xpath(OR.getProperty("Addcustomer"))).click();
            }

}
