package com.inetbanking.testCases;

import static org.testng.Assert.*;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class InvalidLogin {

	WebDriver d;
	@Test
	public void testLogin() throws InterruptedException
	{
		// Load web page
		d.get("https://www.freecrm.com/");
		// Verify page title
//		assertTrue(d.getTitle().contains("Cyclos4 Communities"));
		// Click on Sign in
		Thread.sleep(2000);
		d.findElement(By.xpath("//span[text()='Log In']")).click();
		Thread.sleep(2000);
		// Enter user name
		d.findElement(By.xpath("//input[@name='email']")).clear();
		d.findElement(By.xpath("//input[@name='email']")).sendKeys("demo");
//		String uname=d.findElement(By.name("principal")).getAttribute("value");
		// Enter password
		d.findElement(By.xpath("//input[@name='password']")).clear();
		d.findElement(By.xpath("//input[@name='password']")).sendKeys("fsgdfgfh");
//		String password=d.findElement(By.name("password")).getAttribute("value");
		// Click on Sign In button
		d.findElement(By.xpath("//div[text()='Login']")).click();
		Thread.sleep(2000);
		String errorMsg = d.findElement(By.xpath("//div[@class='ui negative message']/div")).getText();
		if(errorMsg.contains("Something went wrong")) {
			System.out.println("Error message displayed");
			Assert.assertTrue(true,"Error message displayed");
			
		}
		else{Assert.assertFalse(false,"Error message not displayed");
		}
		

		Thread.sleep(3000);
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
	
		System.setProperty("webdriver.chrome.driver", "E:\\Driver Server\\chromedriver.exe");
		WebDriver d=new ChromeDriver();
		d.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		d.manage().timeouts().pageLoadTimeout(3,TimeUnit.MINUTES);
	}
	@AfterMethod
	public void tearDown()
	{
		// Close the browser
		d.quit();
	}
	
}
