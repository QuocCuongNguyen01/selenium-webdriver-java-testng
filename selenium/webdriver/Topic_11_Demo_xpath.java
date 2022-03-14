package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Demo_xpath {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

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
		driver.get("http://live.techpanda.org/");
	
	}

	@Test
	public void TC_01() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='Site Map']"));
		
		
		
	}
	@Test
	public void TC_02() {
		driver.get("http://live.techpanda.org/index.php/checkout/cart/");
		driver.findElement(By.xpath("//li[@class='success-msg']//span"));
		
	}
	@Test
	public void TC_03() {
		driver.get("http://live.techpanda.org/index.php/checkout/cart/");
		driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[text()='Desktops ']"));
		
	}
	@Test
	public void TC_04() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer-container']//a[contains(text(),'Site Map')]"));
		driver.findElement(By.xpath("//div[@class='footer-container']//a[contains(@title,'Site Map')]"));

	}
	@Test
	public void TC_05() {
		driver.get("http://live.techpanda.org/index.php/checkout/cart/");
		
		driver.findElement(By.xpath("//li[contains(@class,'success-msg')]"));
		
		driver.findElement(By.xpath("//span[contains(text(),'Samsung Galaxy was added to your shopping cart.')]"));

		driver.findElement(By.xpath("//span[contains(text(),'your shopping cart.')]"));
		driver.findElement(By.xpath("//span[contains(text(),'was added to your shopping cart.')]"));

	}
	@Test
	public void TC_06() {
		driver.get("http://live.techpanda.org/index.php/checkout/cart/");
		driver.findElement(By.xpath("//div[starts-with(@class,'cart')]"));
		
	}
	@Test
	public void TC_07() {
		driver.get("https://member.lazada.vn/user/register?spm=a2o4n.home.header.d6.19056afeKslkvp");
		driver.findElement(By.xpath("//input[starts-with(@data-spm-anchor-id,'a2o4n.login_signup')]"));
		
	}

	public void TC_08() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		driver.findElement(By.xpath("//span[text()='Last name is required.']"));
		driver.findElement(By.xpath("//a[text()='Digital downloads ']"));
		driver.findElement(By.xpath("//a[text()='Apply for vendor account']"));
		
		
		driver.findElement(By.xpath("//h5[contains(.,\"I'm a Hacker\")]"));
		driver.findElement(By.xpath("//h5[contains(.,\"living in Viet Nam\")]"));
		driver.findElement(By.xpath("//h5[contains(.,\"18 years old\")]"));		
		
		// concat
		driver.findElement(By.xpath("//span[text()=concat('Hello \"John\", What', \"'s happened?\")]"));		
		
	}
	
	
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}