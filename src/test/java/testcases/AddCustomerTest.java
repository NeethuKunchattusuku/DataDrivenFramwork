package testcases;

import base.TestBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddCustomerTest extends TestBase {

    @Test (dataProvider = "getData")
    public void Addcustomer(String fname, String lname, String postcode)
    {

    }

    @DataProvider
    public Object[][] getData()
    {
        String sheetName = "AddCustomerTest";
     int rows =excel.getRowCount(sheetName);
     int cols = excel.getColumnCount(sheetName);
     Object[][] data = new Object[rows-1][cols];
     for(int i = 0; i<=rows;i++){
         for(int j= 0; j<cols;j++)
         {
             data[i-2][j]=excel.getCellData(sheetName,i,j);
         }
     }

     return data;
    }
}
