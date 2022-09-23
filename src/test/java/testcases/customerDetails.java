package testcases;

import base.Listeners.CustomListeners;
import base.TestBase;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Listeners(CustomListeners.class)
public class customerDetails extends TestBase {

    FileInputStream fis;


    public void customerDetails() throws IOException {
        fis = new FileInputStream(excelpath);
        OR.load(fis);
        config.load(fis);
    }


    public List<String> SortandCompareMethod(String customerDetailsGrid) throws InterruptedException {

        //Capturing the Values and Sorting it in Descending order
        List<WebElement> BeforeSort = driver.findElements(By.xpath(customerDetailsGrid));
        List<String> SortedManually = new ArrayList<>();
        for (WebElement p : BeforeSort) {
            SortedManually.add(p.getText());
        }

        Collections.sort(SortedManually, Collections.reverseOrder());
        return SortedManually;
    }

    public List<String> WebElementToString(String customerDetailsGrid) {
        List<WebElement> ListElements = driver.findElements(By.xpath(customerDetailsGrid));
        List<String> StringElements = new ArrayList<>();
        for (WebElement p : ListElements) {
            StringElements.add(p.getText());
        }
        return StringElements;
    }

    @Test(dataProviderClass = AddCustomerTest.class, dataProvider = "getData")
    public void Deletecustomer(String Fname) {
        driver.findElement(By.xpath(OR.getProperty("Customertab"))).click();
        for (int i = 0; i <= Fname.length() - 1; i++) {
            if (Fname == "Clinda")
                driver.findElement(By.xpath(OR.getProperty("SearchText"))).sendKeys(Fname);
            String path = "//div/table/tbody/" + "tr[" + i + "]/td[5]";

            driver.findElement(By.xpath(OR.getProperty("Deletcustomerbutton"))).click();
        }
    }

    @Test
    public void FirstNameSortComparison() throws InterruptedException {
        List<String> FNamesortedManually = SortandCompareMethod((OR.getProperty("CustomerFirstName")));
        driver.findElement(By.xpath(OR.getProperty("FirstNamehyperlink"))).click();
        List<String> StringSortedName = WebElementToString(OR.getProperty("CustomerFirstName"));
        Assert.assertEquals(FNamesortedManually, StringSortedName);

    }

    @Test
    public void LastnameSortComparison() throws InterruptedException {
        List<String> LNamesortedManually = SortandCompareMethod((OR.getProperty("LastNameDetails")));
        Collections.sort(LNamesortedManually);
        driver.findElement(By.xpath(OR.getProperty("LastNamehyperlink"))).click();
        List<String> StringSortedLName = WebElementToString(OR.getProperty("LastNameDetails"));

        Assert.assertEquals(LNamesortedManually, StringSortedLName);
    }

    @Test
    public void PostCodeSortComparison() throws InterruptedException {
        List<String> FNamesortedManually = SortandCompareMethod((OR.getProperty("PostalCodeDetails")));
        driver.findElement(By.xpath(OR.getProperty("postalcodehyperlink"))).click();
        List<String> StringSortedName = WebElementToString(OR.getProperty("PostalCodeDetails"));
        Assert.assertEquals(FNamesortedManually, StringSortedName);

    }


}
