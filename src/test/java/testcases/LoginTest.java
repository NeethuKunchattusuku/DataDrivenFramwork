package testcases;

import base.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;


public class LoginTest extends TestBase {

    @Test
    public void loginAsBankManager() throws InterruptedException {
        driver.findElement(By.xpath(OR.getProperty("bmlbutton"))).click();

        log.debug("Clicked on the Bank Manager button");
    }

    @Test
    public void verifylandingpage() {
        Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Addcustomer"))));
        driver.findElement(By.xpath(OR.getProperty("Addcustomer"))).click();


    }

    }
