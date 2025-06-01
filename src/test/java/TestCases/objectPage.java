package TestCases;

import org.openqa.selenium.WebDriver;

public class objectPage {
    WebDriver driver;
    objectPage(WebDriver driver){
        this.driver = driver;
    }

    AddCustomerPage addCustomerPage;

    public AddCustomerPage getAddCustomerPage()
    {
        return (addCustomerPage==null)? addCustomerPage = new AddCustomerPage(driver):addCustomerPage;
    }
}
