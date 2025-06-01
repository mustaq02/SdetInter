package TestCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import PageObjectModel.LoginPage;
import Utilities.ReadConfig;
import org.apache.commons.io.FileUtils;
//import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

//import com.inetbanking.utilities.ReadConfig;

public class BaseClass {
	public static WebDriver driver;
	AddCustomerPage addCustomerPage = new AddCustomerPage(driver);
	ReadConfig readconfig=new ReadConfig();

	LoginPage loginPage = new LoginPage(driver);

	public String baseURL=readconfig.getApplicationURL();

	public String flipKartUrl=readconfig.getFlipKartApplicationURL();

	public String flightUrl=readconfig.getFlightApplicationURL();
	public String username=readconfig.getUsername();
	public String password=readconfig.getPassword();

	public static ThreadLocal<objectPage> objectPageThreadLocal = new ThreadLocal<>();

	
	public static Logger logger;


	@Parameters("browser")
	@BeforeClass()
	public void setup(String br)
	{
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");

		logger = Logger.getLogger("Automation_Panda");
		PropertyConfigurator.configure("Log4j.properties");
		
		if(br.equals("chrome"))
		{
//			System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "/src/test/Resources/chromedriver.exe");
			driver=new ChromeDriver(options);
		}
		else if(br.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",readconfig.getFirefoxPath());
			driver = new FirefoxDriver();
		}
		else if(br.equals("ie"))
		{
			System.setProperty("webdriver.ie.driver",readconfig.getIEPath());
			driver = new InternetExplorerDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
//		driver.get(baseURL);
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	
//	public String randomestring()
//	{
//		String generatedstring=RandomStringUtils.randomAlphabetic(8);
//		return(generatedstring);
//	}
//
//	public static String randomeNum() {
//		String generatedString2 = RandomStringUtils.randomNumeric(4);
//		return (generatedString2);
//	}
	public void ContactDetails(String name){
	addCustomerPage.EnteringContactDetails(name);

	}

  public objectPage getObjectPage(){
		return objectPageThreadLocal.get();
  }

  public void isDisplayed(){

  }

  public void contactLink(){
		loginPage.clickSubmit();
  }
	
}
