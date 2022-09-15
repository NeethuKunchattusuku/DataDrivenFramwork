package testcases;

import base.Listeners.CustomListeners;
import base.TestBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.ExcelReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

@Listeners(CustomListeners.class)
public class AddCustomerTest extends TestBase {

//    public static Properties config = new Properties();

    FileInputStream fis = new FileInputStream(excelpath);


    public AddCustomerTest() throws IOException {
       OR.load(fis);
       config.load(fis);
    }

    @Test(dataProvider = "getData")
    public void AddCustomerTest(String Fname, String Lname, String postalcod,String alerMessage) throws IOException, InterruptedException {
        driver.findElement(By.xpath(OR.getProperty("FirstNam"))).sendKeys(Fname);
        driver.findElement(By.xpath(OR.getProperty("Lname"))).sendKeys(Lname);
        driver.findElement(By.xpath(OR.getProperty("postalCod"))).sendKeys(postalcod);
        driver.findElement(By.xpath(OR.getProperty("AddCustButton"))).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertTrue(alert.getText().contains(alerMessage));
        alert.accept();

    }

    @DataProvider(name = "getData")
    public static Object[][] getdata(Method m) throws IOException {
        String sheetdata= m.getName();
        Object[][] data = TestdataReading(excelpath,sheetdata);
        TestBase.OR.getProperty(excelpath);
        return data;
    }

    @Test
    public static Object[][] TestdataReading(String excelpath, String sheetname) throws IOException {
        ExcelReader excel = new ExcelReader(excelpath, sheetname);
        int rowcount = excel.getRowCount();
        int colcount = excel.getcolumnsCount();

        Object data[][] = new Object[rowcount - 1][colcount];
        for (int i = 1; i < rowcount; i++) {
            for (int j = 0; j < colcount; j++) {
                String celldata = excel.getcellData(sheetname, i, j);
                System.out.println(celldata);
                data[i - 1][j] = celldata;
            }

        }

        return data;

    }


}
