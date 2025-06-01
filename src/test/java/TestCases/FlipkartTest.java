package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class FlipkartTest extends BaseClass{

    objectPage page = new objectPage(driver);

    @Test
    public void flipKartSetUUp() throws InterruptedException {
        driver.get(flipKartUrl);
        Thread.sleep(2000);
//        getObjectPage().getAddCustomerPage().flipKartImageValidatio();
//        page.getAddCustomerPage().flipKartImageValidatio();
        WebElement flipName = driver.findElement(By.xpath("//img[@title='Flipkart']"));
        if(flipName.isDisplayed()){
            Assert.assertTrue(true,"Image exists");
        }else {
            Assert.assertFalse(true,"Image does not exists");
        }
        Thread.sleep(2000);
//WebElement creaAccount = driver.findElement(By.xpath("//button[contains(text(),'Request OTP')]"));
//        if(driver.findElement(By.xpath("//button[contains(text(),'Request OTP')]")).isDisplayed()){
//            driver.findElement(By.xpath("//a[contains(text(),'Create an account')]")).click();
//        }
//if(creaAccount.isDisplayed()) {
//    driver.findElement(By.xpath("//a[contains(text(),'Create an account')]")).click();
//
//}
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@name='q']")).sendKeys("iphone14");
        driver.findElement(By.xpath("//button[contains(@type,'submit')]")).click();
        List<WebElement> iphoneCount = driver.findElements(By.xpath("//span[text()='Sort By']/ancestor::div[@class='W_R1IA']/ancestor::div[3]/div"));
        System.out.println("The number of search items are : " + iphoneCount.size());
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[text()='Sort By']/ancestor::div[@class='W_R1IA']/ancestor::div[3]/div[2]//a")).click();
        String parent=driver.getWindowHandle();

        Set<String> s=driver.getWindowHandles();

// Now iterate using Iterator
        Iterator<String> I1= s.iterator();

        while(I1.hasNext())
        {

            String child_window=I1.next();


            if(!parent.equals(child_window))
            {
                driver.switchTo().window(child_window);

                System.out.println(driver.switchTo().window(child_window).getTitle());

                driver.close();
            }

        }
//switch to the parent window
        driver.switchTo().window(parent);
    }

    }



