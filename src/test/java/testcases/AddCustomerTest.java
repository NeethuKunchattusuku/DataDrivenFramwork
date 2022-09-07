package testcases;

import base.TestBase;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.ExcelReader;

import java.io.FileInputStream;
import java.io.IOException;

public class AddCustomerTest extends TestBase {
    static String propertypath = System.getProperty("user.dir");
    static String excelpath = propertypath + "\\src\\test\\java\\resources\\excel\\TestData.xlsx";
    FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +
            "\\src\\test\\java\\resources\\properties\\OR.properties");


    public AddCustomerTest() throws IOException {
        OR.load(fis);
    }

    @Test(dataProvider = "getData1")
    public void Addcustomer(String Fname, String Lname, String postalcod) throws IOException, InterruptedException {
        driver.findElement(By.xpath(OR.getProperty("FirstNam"))).sendKeys(Fname);
        driver.findElement(By.xpath(OR.getProperty("Lname"))).sendKeys(Lname);
        driver.findElement(By.xpath(OR.getProperty("postalCod"))).sendKeys(postalcod);
        driver.findElement(By.xpath(OR.getProperty("AddCustButton"))).click();
        driver.switchTo().alert().accept();
    }

    @DataProvider(name = "getData1")
    public Object[][] getdata() throws IOException {
        Object[][] data = TestdataReading(excelpath, "AddCustomerTest");
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
