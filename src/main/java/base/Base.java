package base;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utility.Utility;

public class Base {

	public WebDriver driver;

	@BeforeMethod
	public void initiateDriverInsatance() throws Exception
	{		 
		if (Utility.fetchPropertyValue("browserName").toString().equalsIgnoreCase("chrome")) 
		{
			System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");

			// Create object of HashMap Class
			Map<String, Object> prefs = new HashMap<String, Object>();

			// Set the notification setting it will override the default setting
			prefs.put("profile.default_content_setting_values.notifications", 2);

			// Create object of ChromeOption class
			ChromeOptions options = new ChromeOptions();

			// Set the experimental option
			options.setExperimentalOption("prefs", prefs);

			// pass the options object in Chrome driver
			driver = new ChromeDriver(options);

			//driver = new ChromeDriver();
		} 
		else if (Utility.fetchPropertyValue("browserName").toString().equalsIgnoreCase("firefox")) 
		{
			System.setProperty("webdriver.firefox.marionette","./Driver/geckodriver.exe");
			driver = new FirefoxDriver();

		}
		/*else if (Utility.fetchPropertyValue("browserName").toString().equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "./Driver/geckodriver.exe");
			driver = new FirefoxDriver();

		}*/ else if (Utility.fetchPropertyValue("browserName").toString().equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", "./Driver/internetexplorerdriver.exe");
			driver = new InternetExplorerDriver();

		} else {
			System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
			driver = new ChromeDriver();
		}
		//maximize browser view
		driver.manage().window().maximize();
		//driver.manage().timeouts().wait(15);
		driver.get((Utility.fetchPropertyValue("applicationURL").toString()));
	}

	@AfterMethod
	public void closeDriverInstance() {
		driver.quit();
	}

	/*public void waitForPageLoadComplete(WebDriver driver, int specifiedTimeout) {
		Wait<WebDriver> wait = new WebDriverWait(driver, specifiedTimeout);
		wait.until(driver1 -> String.valueOf(((JavascriptExecutor) driver1).executeScript("return document.readyState"))
				.equals("complete"));
	}*/

}
