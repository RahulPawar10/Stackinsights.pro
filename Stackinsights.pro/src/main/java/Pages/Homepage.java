package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.StartupPage;

public class Homepage extends StartupPage {
       public Homepage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void  returnToHomePage(String baseURL) throws Exception {
        driver.navigate().to(baseURL);
        driver.getTitle();
        //return PageFactory.initElements(driver, HomePage.class);
    }

}
