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
//import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
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
    @Parameters("browser")
    public void setup(@Optional("chrome")String browser) {

        if (browser.equalsIgnoreCase("chrome")) {
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
        } else if (browser.equalsIgnoreCase("edge")) {
//            WebDriverManager.edgedriver().setup();
            System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")
                    + File.separator +"src" + File.separator + "test" + File.separator
                    + "msedgedriver.exe");
            driver = new EdgeDriver();
          }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

    }

    @Test
    @Parameters({"stockName", "purchasePrice"})
    public void verifyStockInfo(String stockName, double purchasePrice) throws IOException, InterruptedException {
//        test = extent.createTest("Verify Stock Info for " + stockName);
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
        Thread.sleep(3000);
        searchBox.sendKeys(Keys.ARROW_DOWN);
        searchBox.sendKeys(Keys.ENTER);

        log.info("Searching stock: " + stockName);

//        Thread.sleep(8000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//aside[contains(@class,'priceinfo')][1]//span[@id='quoteLtp']")));
        // Extract stock info (these locators may need adjustment using DevTools/Inspect)
        String priceText = driver.findElement(By.xpath("//aside[contains(@class,'priceinfo')][1]//span[@id='quoteLtp']")).getText();
        double currentPrice = Double.parseDouble(priceText.replace(",", "").trim());

        String high52Text = driver.findElement(By.xpath("//span[contains(text(),'52 Week High')]/parent::td/following-sibling::td")).getText();
        String low52Text = driver.findElement(By.xpath("//span[contains(text(),'52 Week Low')]/parent::td/following-sibling::td")).getText();

        log.info("Current Price: " + currentPrice);
        log.info("52 Week High: " + high52Text);
        log.info("52 Week Low: " + low52Text);

        // Screenshot after search
        ScreenshotUtil.capture(driver, "after_search");
//        double purchasePriceVal = Double.parseDouble(purchasePrice.replace(",", "").trim());
        // Profit/Loss
        if (currentPrice > purchasePrice) {
            log.info("Stock is in PROFIT");
            ScreenshotUtil.capture(driver, "Stock is in PROFIT");
            log.info("Stock is in PROFIT. Current: " + currentPrice + " | Purchase: " + purchasePrice);
//            Assert.assertTrue(true,"Stock is in PROFIT. Current: " + currentPrice + " | Purchase: " + purchasePrice);
        } else {
            log.info("Stock is in LOSS");
            ScreenshotUtil.capture(driver, "Stock is in LOSS");
            log.info("Stock is in LOSS. Current: " + currentPrice + " | Purchase: " + purchasePrice);
//            Assert.assertTrue(false,"Stock is in LOSS. Current: " + currentPrice + " | Purchase: " + purchasePrice);
        }

        // Assert data is not null
        Assert.assertTrue(currentPrice > 0, "Price not found!");
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
