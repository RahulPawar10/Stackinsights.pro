package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.StartupPage;

public class Loginpage extends StartupPage {
	@FindBy(id = "username")
	WebElement Username;
	@FindBy(id = "password")
	WebElement passwd;
	@FindBy(xpath = "//button[@type='submit']")
	WebElement signin;
	StartupPage startupPage = new StartupPage(driver);

	public Loginpage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void navigatetologin(String username, String password) throws InterruptedException {
		Thread.sleep(5000);
		Username.sendKeys(username);
		passwd.sendKeys(password);
		signin.click();
	}
}
