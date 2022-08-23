package tiki.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeTest(alwaysRun = true)
	public void initBrowser() {
		System.out.println("----------------Open Browser------------------");
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
		driver = new FirefoxDriver();
	}
	@AfterTest(alwaysRun = true)
	public void cleanBrowser() {
		driver.quit();
		System.out.println("----------------Close Browser------------------");
	}
	public WebDriver getBrowserDriver() {
		return driver;
	}
}
