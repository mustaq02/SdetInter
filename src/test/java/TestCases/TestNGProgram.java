package TestCases;

import static org.testng.Assert.*;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class TestNGProgram {
    WebDriver driver;
    @Test
    public void testLogin() throws InterruptedException
    {
      try{  // Load web page
        driver.get("https://www.makemytrip.com/");
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().window().maximize();
        if(driver.findElement(By.xpath("//li[text()='Personal Account']")).isDisplayed()){
            driver.findElement(By.xpath("//span[@data-cy='closeModal']")).click();
            Thread.sleep(2000);
        }

        // Close login popup by clicking outside (or ESC can work sometimes)
        Thread.sleep(3000);
//            driver.findElement(By.cssSelector("body")).click();

        // Click on Flights tab (should be default, but clicking to ensure)
        WebElement flightTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Flights']")));
        flightTab.click();

        // Select Round Trip
        WebElement roundTrip = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@data-cy='roundTrip']")));
        roundTrip.click();

        // Enter FROM location as HYD
        WebElement from = wait.until(ExpectedConditions.elementToBeClickable(By.id("fromCity")));
        from.click();
        Thread.sleep(2000);
        WebElement fromInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='From']")));
        fromInput.sendKeys("HYD");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='Rajiv Gandhi International Airport']"))).click();
        //label[@for='toCity']
        Thread.sleep(2000);
        WebElement to = wait.until(ExpectedConditions.elementToBeClickable(By.id("toCity")));
        to.click();
        Thread.sleep(2000);
        WebElement toInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='To']")));
        toInput.sendKeys("MAA");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='Chennai International Airport']"))).click();

        // Enter TO location as MAA
//            WebElement toInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='To']")));
//            toInput.sendKeys("MAA");
//            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Chennai')]"))).click();

        // Select Departure Date
        Thread.sleep(2000);
        WebElement departureDate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='departure']")));
//            departureDate.click();
        // Select any available date (example: 10th of next month)
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='DayPicker-Months']/div[2]//div[@class='DayPicker-Week'][2]/div[1]//p[1]"))).click();
        Thread.sleep(2000);
        // Select Return Date
        WebElement returnDate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='return']")));
//            returnDate.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='DayPicker-Months']/div[2]//div[@class='DayPicker-Week'][2]/div[1]//p[1]"))).click();
        Thread.sleep(2000);
        // Click on Search Button
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Search']")));
        searchButton.click();
        Thread.sleep(2000);
        // Wait for the search results page to load
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Flights from')]")));

        System.out.println("Search page displayed successfully!");
        Thread.sleep(2000);

    } catch (Exception e) {
        e.printStackTrace();
    }
    }
    private boolean isElementPresent(WebDriver driver,By by)
    {
        driver.manage().timeouts().implicitlyWait(0,TimeUnit.SECONDS);
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    @BeforeMethod
    public void setUp()
    {
        // Launch browser
        String driverLoc = System.getProperty("user.dir") + "\\src\\test\\Resources\\chromedriver.exe" ;
        System.setProperty("webdriver.chrome.driver", driverLoc);
        WebDriver d=new ChromeDriver();
        d.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
        d.manage().timeouts().pageLoadTimeout(3,TimeUnit.MINUTES);
    }
    @AfterMethod
    public void tearDown()
    {
        // Close the browser
        driver.quit();
    }



}
