package testcases;

import base.Listeners.CustomListeners;
import base.TestBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Listeners(CustomListeners.class)
public class OpenAccount extends TestBase {
    WebElement dropdown = null;

    FileInputStream fis = new FileInputStream(excelpath);
    Alert alert;

    public OpenAccount() throws IOException {
        OR.load(fis);
        config.load(fis);
    }


    @Test(dataProviderClass = AddCustomerTest.class, dataProvider = "getData")
    public void OpenAccount(String customer, String currencyparam, String message) throws InterruptedException {
        driver.findElement(By.xpath(OR.getProperty("OpenAccountbutton"))).click();
        Select se = new Select(driver.findElement(By.xpath(OR.getProperty("CustomerName"))));
        List<WebElement> Lelement = se.getOptions();
        Select se1 = new Select(driver.findElement(By.xpath((OR.getProperty("Currency_xpath")))));
        List<WebElement> Celement = se1.getOptions();
//        for (int i = 1; i <= Lelement.size() - 1; i++) {
//            {
//
//                for (int j = 1; j <= Celement.size() - 1; j++) {

//                    System.out.println(Lelement.get(i).getText() + "+++++++++++");
//                    System.out.println(Celement.get(j).getText() + "+++++++++++");
//                    if (Lelement.get(i).getText() == customer) {
//                        if (Celement.get(j).getText() == currencyparam) {
                            se.selectByVisibleText(customer);
                            Thread.sleep(1000);
                            se1.selectByVisibleText(currencyparam);
                            Thread.sleep(1000);
                            driver.findElement(By.xpath(OR.getProperty("Process"))).click();
                            alert = wait.until(ExpectedConditions.alertIsPresent());
                            Assert.assertTrue(alert.getText().contains(message));
                            alert.accept();
//                        }
//                    }

                }


            }
//        }

//    }

//}
