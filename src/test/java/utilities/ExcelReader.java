package utilities;

import base.TestBase;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.IOException;

public class ExcelReader extends TestBase
{
    static String propertypath = System.getProperty("user.dir");
    static String excelpath = propertypath+ "\\src\\test\\java\\resources\\excel\\TestData.xlsx";
    static XSSFWorkbook workbook = null;
    static XSSFSheet sheet = null;

    public ExcelReader(String excelpath, String sheetName)  {
        try {
            workbook = new XSSFWorkbook(excelpath);
            sheet = workbook.getSheet(sheetName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static int getRowCount() {
        return sheet.getPhysicalNumberOfRows();
    }
    public static int getcolumnsCount() {
        XSSFRow row = sheet.getRow(0);
        int colNum = row.getLastCellNum();
        return colNum;
    }

    public String getcellData(String sheetname,int rowNum, int colNum) throws IOException {
        String celldata = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
        return celldata;
    }

    public  void getcellDataNumber(int rowNum, int colNum) {
        double celldatanumber = sheet.getRow(rowNum).getCell(colNum).getNumericCellValue();

    }
}



