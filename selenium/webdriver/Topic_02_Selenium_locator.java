package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_locator {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
		driver = new FirefoxDriver();
		
		//System.setProperty("webdriver.chrome.driver", projectPath + "/browserDriver/Chromedriver");
		//driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://stackoverflow.com/users/login?ssrc=user_prefs&returnurl=https%3a%2f%2fstackoverflow.com%2fusers%2fpreferences%2fcurrent");
	
	}

	@Test
	public void TC_01() {
		//id
		driver.findElement(By.id("email")).sendKeys("cuongdnqb5@gmail.com");
		driver.findElement(By.id("password")).sendKeys("Cuong040996");
		driver.findElement(By.id("submit-button")).click();;
		//Class
		driver.findElement(By.className("s-input"));
		//Name
		driver.findElement(By.name("email"));
		//Tagname: Tìm xem có bao nhiêu element cùng loại trên HLML
		driver.findElement(By.tagName("input"));
		//Linktext:Truyển cả text vào
		driver.findElement(By.linkText("Sign up"));
		//Partial link text: 1 Phần của text
		driver.findElement(By.partialLinkText("Sign"));
		
		
		//CSS
		driver.findElement(By.cssSelector("input[id='email']"));
		driver.findElement(By.cssSelector("input#email"));
		driver.findElement(By.cssSelector("#email"));
		
		driver.findElement(By.cssSelector("input[class='s-input']"));
		driver.findElement(By.cssSelector("input.s-input"));
		driver.findElement(By.cssSelector(".s-input"));
		
		driver.findElement(By.cssSelector("input[name='email']"));
		
		driver.findElement(By.cssSelector("input"));
		
		//Xpath
		driver.findElement(By.xpath("//input[@id='email']"));
		driver.findElement(By.xpath("//input[@class='s-input']"));
		driver.findElement(By.xpath("//input[contains(@class,'s-inpu')]"));
		driver.findElement(By.xpath("//input[starts-with(@class,'s-inpu')]"));
		driver.findElement(By.xpath("//input[@name='email']"));
		driver.findElement(By.xpath("//input"));
		driver.findElement(By.xpath("//a[text()='Sign up']"));
		driver.findElement(By.xpath("//a[contains(text(),'Sign')]"));
		driver.findElement(By.xpath("//a[contains(text(),'up')]"));
		
		
		
	}
	@Test
	public void TC_02() {
		
	}
	@Test
	public void TC_03() {
	}

	//@AfterClass
	//public void afterClass() {
	//	driver.quit();
	}
//}