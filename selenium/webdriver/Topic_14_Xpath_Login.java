package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Xpath_Login {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String fistName, lastName, fullName;

	@BeforeClass
	public void beforeClass() {
		// Mở browser lên
		//System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
		//driver = new FirefoxDriver();
		
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDriver/Chromedriver");
		driver = new ChromeDriver();
		// Hàm này áp dụng việc tìm element(findElement/ findElements)
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// Phóng to browser lên
		driver.manage().window().maximize();
		// Mở app lên
		//driver.get("https://www.facebook.com/");
	
	}

	@Test
	public void TC_01_Empty_Data() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.cssSelector("button#send2")).click();
		
		//assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals( driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals( driver.findElement(By.cssSelector("div#advice-required-entry-pass")).getText(), "This is a required field.");
	}
	@Test
	public void TC_02_Invalid_Email() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("123123123@123.123");
		driver.findElement(By.id("pass")).sendKeys("123123");
		driver.findElement(By.cssSelector("button#send2")).click();
		
		//assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals( driver.findElement(By.cssSelector("div#advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
	}
	@Test
	public void TC_03_Invalid_Password() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("cuongdnqb5@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123");
		driver.findElement(By.cssSelector("button#send2")).click();
		
		//assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals( driver.findElement(By.cssSelector("div#advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
	}
	@Test
	public void TC_04() {
	}
	@Test
	public void TC_05() {
	}
	@Test
	public void TC_06() {
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}