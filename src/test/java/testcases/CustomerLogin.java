package testcases;

import base.Listeners.CustomListeners;
import base.TestBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Listeners(CustomListeners.class)
public class CustomerLogin extends TestBase {

    FileInputStream fis = new FileInputStream(excelpath);
    Alert alert;
    SoftAssert sa = new SoftAssert();


    public CustomerLogin() throws IOException {
        OR.load(fis);
        config.load(fis);
    }

    @Test(dataProviderClass = AddCustomerTest.class, dataProvider = "getData")
    public void customerLoginpageverification(String BankName) {
        driver.findElement(By.xpath(OR.getProperty("Home"))).click();
        SoftAssert sassert = new SoftAssert();
        sassert.assertEquals(OR.getProperty("Header"), BankName);
        driver.findElement(By.xpath("//div[@class='box mainhdr']")).isDisplayed();
        driver.findElement(By.xpath(OR.getProperty("CustomerLoginButton"))).click();

    }

    @Test(dataProviderClass = AddCustomerTest.class, dataProvider = "getData")
    public void loginAsCustomer(String customername) throws InterruptedException {
        List<WebElement> ele = driver.findElements(By.xpath(OR.getProperty("CustomerNameoptionDropdown")));
        WebElement el1 = driver.findElement(By.xpath(OR.getProperty("CustomerNameoptionDropdown")));
        el1.sendKeys(customername);
        driver.findElement(By.xpath(OR.getProperty("LoginButton"))).click();

    }

    @Test(dataProviderClass = AddCustomerTest.class, dataProvider = "getData")
    public void customerLoginHomePageVerificat(String customer) throws InterruptedException {
        Thread.sleep(1000);
    }


    @Test(dataProviderClass = AddCustomerTest.class, dataProvider = "getData")
    public void DepositAmount(String Amount, String withdraw) throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath(OR.getProperty("Depositbutton"))).click();
        driver.findElement(By.xpath(OR.getProperty("Amttodeposit"))).sendKeys(Amount);
        driver.findElement(By.xpath(OR.getProperty("button"))).click();
        sa.assertEquals(driver.findElement(By.xpath(OR.getProperty("DepositMesaage"))), "Deposit Successful");

    }

    @Test
    public void transaction() throws InterruptedException {
        driver.findElement(By.xpath(OR.getProperty("Transactiobutton"))).click();
        Thread.sleep(1000);

    }

    @Test
    public void TransactionPageveririfcation() throws InterruptedException, ParseException {

        List<String> listDates = new ArrayList<>();
        List<String> listDatesq = new ArrayList<>();

        List<String> sorted = new ArrayList<>();
        List<WebElement> Transactiontable = driver.findElements(By.xpath(OR.getProperty("TransactioTableDateColumn")));
        SimpleDateFormat dateFormatter = new SimpleDateFormat("E, MMM DD,YYYY HH:mm:ss", Locale.ENGLISH);

        for (WebElement p : Transactiontable) {
            listDates.add(p.getText());
        }
        Collections.sort(listDates);
        driver.findElement(By.xpath(OR.getProperty("Datehyperlink"))).click();
        List<WebElement> Transactiontableafterhyperlink = driver.findElements(By.xpath(OR.getProperty("TransactioTableDateColumn")));
        for (WebElement q : Transactiontableafterhyperlink) {
            listDatesq.add(q.getText());
        }
        Assert.assertEquals(listDates, listDatesq);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div/button[@class='btn'][1]")).click();
Thread.sleep(1000);
    }

    @Test
    public static void withdraw() throws InterruptedException {
        driver.findElement(By.xpath(OR.getProperty("withdrawelbutton"))).click();
        String balance= driver.findElement(By.xpath(OR.getProperty("balanceAmt"))).getText();
        int balance1 = Integer.parseInt(balance);
        driver.findElement(By.xpath(OR.getProperty("withdrawamount"))).sendKeys("16");
        driver.findElement(By.xpath(OR.getProperty("withdrabutton"))).click();
        WebElement tempmsg = driver.findElement(By.xpath(OR.getProperty("withdrawdmsg")));
        String temp=tempmsg.getText();
        Assert.assertEquals(temp,"Transaction Failed. You can not withdraw amount more than the balance.");
        Thread.sleep(1000);
        driver.findElement(By.xpath(OR.getProperty("withdrawamount"))).click();
        driver.findElement(By.xpath(OR.getProperty("withdrawamount"))).sendKeys("1");
        driver.findElement(By.xpath(OR.getProperty("withdrabutton"))).click();
        tempmsg = driver.findElement(By.xpath(OR.getProperty("withdrawdmsg")));
         temp=tempmsg.getText();
        Assert.assertEquals(temp,"Transaction successful");
        String currbalance =driver.findElement(By.xpath(OR.getProperty("balanceAmt"))).getText();
        int curBal = Integer.parseInt(currbalance);
        Assert.assertEquals((balance1-1),curBal);
        if(balance1<curBal)
        {
            log.info("Working fine =====================");
        }
        else{
            log.info("Not Working fine =====================");
        }
        driver.findElement(By.xpath(OR.getProperty("HomeButton"))).click();


    }
}

