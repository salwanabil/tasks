package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.Base;
import utility.Utility;

///will contain methods for each activity in the page
public class LoginPage extends Base{

	WebDriver driver;

	public LoginPage(WebDriver driver) 
	{
		this.driver = driver;
	}
	//enterUsername
	public void enterUsername(String userName) throws Exception{			
		driver.findElement(By.id(Utility.fetchLocatorValue("login_username_id"))).sendKeys(userName);
	}
	//enterPassword
	public void enterPassword (String pass) throws Exception{
		driver.findElement(By.id(Utility.fetchLocatorValue("login_password_id"))).sendKeys(pass);
	}
	//click Sign in button
	public void clickSignin() throws Exception
	{
		driver.findElement(By.xpath(Utility.fetchLocatorValue("login_signin_xpath"))).click();
	}

	public void Logout() throws Exception {
		driver.findElement(By.xpath(Utility.fetchLocatorValue("login_accountsettings_xpath"))).click();
		Thread.sleep(9000);
		driver.findElement(By.linkText(Utility.fetchLocatorValue("logout_linkText"))).click();

		System.out.println("user successfully logged out");
	}

}
