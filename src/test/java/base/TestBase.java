package base;


import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public abstract class TestBase  {
    /*
     *WebDriver
     *properties
     *Log-log4j jar, log, log4j.properties,Logger
     *Extent Reports
     *DB
     * Excel
     * Mail
     */

    public static WebDriver driver;
    public static Properties config = new Properties();
    public static Properties OR = new Properties();
    public static FileInputStream fis;
    public static Logger log = LogManager.getLogger("devpinoyLogger");
    public static WebDriverWait wait;
    public static String propertypath = System.getProperty("user.dir");
    public static String excelpath = propertypath + "\\src\\test\\java\\resources\\excel\\TestData.xlsx";

    @BeforeSuite
    public void setUp() {


        if (driver == null) {

            try {
                fis = new FileInputStream(System.getProperty("user.dir") +
                        "\\src\\test\\java\\resources\\properties\\Config.properties");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                config.load(fis);
                log.debug("Config Loaded");
                log.debug("Config file Loaded");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fis = new FileInputStream(System.getProperty("user.dir") +
                        "\\src\\test\\java\\resources\\properties\\OR.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                OR.load(fis);
                log.debug("OR file Loaded");

            } catch (IOException e) {
                e.printStackTrace();
            }


            if (config.getProperty("browser").equals("chrome"))
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +
                        "\\src\\test\\java\\resources\\executables\\chromedriver.exe");
            driver = new ChromeDriver();
            log.debug("Chrome Loaded");
        }
        driver.get(config.getProperty("testsiteurl"));
        log.debug("Url Loaded" + config.getProperty("testsiteurl"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
        wait=new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public boolean isElementPresent(By by) {

        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }


    }


    public  void failed(String testmethodname) throws IOException {
        String UserDirpath = System.getProperty("user,dir");

        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file,new File("src/test/java/screenShots/"+testmethodname+".jpg"));

    }


    @AfterSuite
    public void tearDown() {
        if (driver != null)
            driver.quit();
        log.info("driver closed succesfully");
    }


}

