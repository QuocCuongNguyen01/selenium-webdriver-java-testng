package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Xpath_Login {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String firstName, lastName, fullName, emailAddress, password;

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
		firstName = "Cuong"; 
		lastName = "Nguyen" ; 
		fullName = firstName+" "+lastName; 
		emailAddress = "cuongdnqb6"+ generateRandomNumber() +"@gmail.com"; 
		password = "123456" ;
	
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
	public void TC_04_Incorrect_Email_Password() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("cuongdnqb5@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123456");
		driver.findElement(By.cssSelector("button#send2")).click();
		
		Assert.assertEquals( driver.findElement(By.cssSelector("div.page li span")).getText(), "Invalid login or password.");
		
		
	}
	@Test
	public void TC_05() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		driver.findElement(By.cssSelector("input[id='firstname']")).sendKeys(firstName);	
		driver.findElement(By.cssSelector("input[id='lastname']")).sendKeys(lastName);
		driver.findElement(By.cssSelector("input[id='email_address']")).sendKeys(emailAddress);
		driver.findElement(By.cssSelector("input[id='password']")).sendKeys(password);
		driver.findElement(By.cssSelector("input[id='confirmation']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		//Tuyệt đối
		Assert.assertEquals( driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Thank you for registering with Main Website Store.");
		//Tương đối
		String contactInfotext = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		Assert.assertTrue(contactInfotext.contains(fullName));
		Assert.assertTrue(contactInfotext.contains(emailAddress));
		driver.findElement(By.xpath("//a/span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		
	
	}
	@Test
	public void TC_06() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys(emailAddress);
		driver.findElement(By.id("pass")).sendKeys(password);
		driver.findElement(By.cssSelector("button#send2")).click();
		String contactInfotext = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		Assert.assertTrue(contactInfotext.contains(fullName));
		Assert.assertTrue(contactInfotext.contains(emailAddress));
		
	}
	public int generateRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999);
		
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}