package TestCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AutomationPanda extends BaseClass{


//    AddCustomerPage contactLink = new AddCustomerPage(driver);

//@Test(enabled = false)
//void setUp() throws InterruptedException {
//    AddCustomerPage contactLink = new AddCustomerPage(driver);
//    ChromeOptions options = new ChromeOptions();
//    options.addArguments("--remote-allow-origins=*");
//    System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "/src/test/Resources/chromedriver.exe");
//    WebDriver driver = new ChromeDriver(options);
//    driver.get("https://automationpanda.com/2021/12/29/want-to-practice-test-automation-try-these-demo-sites");
//    System.out.println(driver.getTitle());
//    Thread.sleep(2000);
//    driver.close();
//
//}

@Test(enabled = true)
 void setUp2() throws InterruptedException {
    AddCustomerPage contactLink = new AddCustomerPage(driver);
    driver.get(baseURL);
    System.out.println("Browser title name is : " + driver.getTitle());
    Thread.sleep(2000);
    driver.findElement(By.xpath("//a[text()='Contact']")).click();
//    contactLink.clickContactLink();
    Thread.sleep(2000);
   driver.findElement(By.xpath("//input[@class='name  grunion-field']")).sendKeys("Hyderabad");
//   ContactDetails("Hyderabad");
    driver.findElement(By.xpath("//input[@class='email  grunion-field']")).sendKeys("test@test.com");
    Thread.sleep(2000);
    driver.findElement(By.xpath("//textarea[@class='textarea  grunion-field']")).sendKeys("Entered the data");
    Thread.sleep(2000);
    driver.findElement(By.xpath("//strong[text()='Contact Me']")).click();
    Thread.sleep(2000);
    String mesg = driver.findElement(By.xpath("//h4[@id='contact-form-success-header']")).getText();
    if(mesg.equals("Your message has been sent")){
        Assert.assertTrue(true,"Message displayed properly");
         }else{
        Assert.assertFalse(true,"Message not displayed properly");
    }
    Thread.sleep(2000);
}




}
