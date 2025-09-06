package tests;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.Base;
import utils.Reporting;
import utils.ScreenshotUtil;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static utils.Base.driver;

public class StockInfoTest {
//    public WebDriver driver;
    ExtentReports extent;
    ExtentTest test;
    Logger log = LogManager.getLogger(StockInfoTest.class);

//    @FindBy(xpath = "//img[contains(@title,'Equity Derivatives')]")
//    public WebElement popUp;
//    @FindBy(xpath = "//button[contains(@class,'btn-close')]")
//    public WebElement popUpClose;
//    @FindBy(xpath = "//div[contains(@class,'quote-search')]//input[1]")
//    public WebElement searchBox;



    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("reports/StockReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    public static Logger logger;
    @BeforeMethod
    public void setup() {
//        WebDriverManager.chromedriver().setup();
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--remote-allow-origins=*");
//        options.addArguments("start-maximized");
//        options.addArguments("disable-infobars");
//        options.addArguments("--disable-extensions");
//        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
//                "AppleWebKit/537.36 (KHTML, like Gecko) " +
//                "Chrome/114.0.5735.90 Safari/537.36");
//
//        WebDriver driver = new ChromeDriver(options);
//        String driverHomePath = System.getProperty("user.dir");
//        String driverFilePath = driverHomePath + File.separator + "src" + File.separator + "test" + File.separator + "chromedriver.exe";
//        System.setProperty("webdriver.chrome.driver", driverFilePath);
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--remote-allow-origins=*");
//        driver = new ChromeDriver();

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        options.addArguments("start-maximized");
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                "AppleWebKit/537.36 (KHTML, like Gecko) " +
                "Chrome/114.0.5735.90 Safari/537.36");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

    }

    @Test
    @Parameters({"stockName", "purchasePrice"})
    public void verifyStockInfo(String stockName, double purchasePrice) throws IOException, InterruptedException {
        test = extent.createTest("Verify Stock Info for " + stockName);
        log.info("Opening NSE Website");
        driver.get("https://www.nseindia.com/");

        // Screenshot before search
        ScreenshotUtil.capture(driver, "before_search");
        String beforeSearch = ScreenshotUtil.capture(driver, "before_search");
        Reporting.logInfo("Before search is done " + beforeSearch);
         // Search Stock
        Thread.sleep(2000);
        List<WebElement> elements = driver.findElements(By.xpath("//img[contains(@title,'Equity Derivatives')]"));
        if (elements.size() > 0) {
            System.out.println("Element exists");
            String popUp = ScreenshotUtil.capture(driver, "popUp is displayed");
            Reporting.logInfo("popup is displayed " + popUp);
            WebElement popUpCloseLink = driver.findElement(By.xpath("//button[contains(@class,'btn-close')]"));
            popUpCloseLink.click();
        } else {
            System.out.println("Element does not exist");
        }
//        if(popUp.isDisplayed()){
//            popUpClose.click();
//        }
        WebElement searchBox = driver.findElement(By.xpath("//div[contains(@class,'quote-search')]//input[1]"));
        ScreenshotUtil.capture(driver, "Before enter the text in seach box");
        searchBox.sendKeys(stockName);
        ScreenshotUtil.capture(driver, "After enter the text in seach box");
        searchBox.sendKeys(Keys.ENTER);

        log.info("Searching stock: " + stockName);

  /*      // Extract stock info (these locators may need adjustment using DevTools/Inspect)
        String priceText = driver.findElement(By.cssSelector(".trade_info .value")).getText();
        double currentPrice = Double.parseDouble(priceText.replace(",", "").trim());

        String high52Text = driver.findElement(By.xpath("//td[contains(text(),'52 Wk High')]/following-sibling::td")).getText();
        String low52Text = driver.findElement(By.xpath("//td[contains(text(),'52 Wk Low')]/following-sibling::td")).getText();

        log.info("Current Price: " + currentPrice);
        log.info("52 Week High: " + high52Text);
        log.info("52 Week Low: " + low52Text);

        // Screenshot after search
        ScreenshotUtil.capture(driver, "after_search");

        // Profit/Loss
        if (currentPrice > purchasePrice) {
            log.info("Stock is in PROFIT");
            test.pass("Stock is in PROFIT. Current: " + currentPrice + " | Purchase: " + purchasePrice);
        } else {
            log.info("Stock is in LOSS");
            test.fail("Stock is in LOSS. Current: " + currentPrice + " | Purchase: " + purchasePrice);
        }

        // Assert data is not null
        Assert.assertTrue(currentPrice > 0, "Price not found!");   */
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @AfterSuite
    public void flushReport() {
        extent.flush();
    }


}
