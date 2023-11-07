package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.StartupPage;

public class Loginpage extends StartupPage{
	/*
	 * @FindBy (name = "email") WebElement usename;
	 * 
	 * @FindBy (name = "password")// teamcast WebElement passwd;
	 * 
	 * @FindBy (xpath = "//div/button[@type='submit']") WebElement signin;
	 */
	@FindBy (xpath = "//input[@id='username']")
	WebElement usename;
	@FindBy (xpath = "//input[@id='password']")// teamcast
	WebElement passwd;
	@FindBy (xpath = "//button[text()='Login']")
	WebElement signin;
	StartupPage startupPage=new StartupPage(driver);
	public Loginpage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		
	}
	public void navigatetologin(String username, String password) {
		startupPage.wait(By.xpath("//div[@class='login-form']"));

		usename.sendKeys(username);
		passwd.sendKeys(password);
		signin.click();
		//return PageFactory.initElements(driver, Loginpage.class);
	}

}
