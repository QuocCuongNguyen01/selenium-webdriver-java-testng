package testNG;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Listener.ExtentReportListener;
@Listeners(ExtentReportListener.class)
public class Topic_10_Listener {
	public static WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void Register() {
		driver.get("http://live.techpanda.org/index.php/customer/account/create/");

		driver.findElement(By.id("firstname")).sendKeys("Automation");
		driver.findElement(By.id("lastname")).sendKeys("FC");
		String emailAddress = "Same"+getRandomNumber()+"@gmail.com";
		System.out.println("Email address = "+ emailAddress);
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys("123123");
		driver.findElement(By.id("confirmation")).sendKeys("123123");
		driver.findElement(By.cssSelector("button[title='Register']")).click();
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='success-msg']//span[text()='Thank you for registering with Main Website Store.']")).isDisplayed());
		//Check 1 thong tin nào do
		//Action 1 task nào đó
		
		//Post - condition:Logout ra de cho user tiếp theo no chay duoc
//		
//		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
//		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(1000);
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
