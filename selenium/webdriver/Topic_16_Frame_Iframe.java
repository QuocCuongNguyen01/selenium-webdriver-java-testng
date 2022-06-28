package webdriver;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Frame_Iframe {

	WebDriver driver;
	String osName = System.getProperty("os.name");
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");

		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDriver\\geckodriver");
		}

		// Mở browser lên
		// System.setProperty("webdriver.gecko.driver", projectPath +
		// "/browserDriver/geckodriver");
		driver = new FirefoxDriver();

		// System.setProperty("webdriver.chrome.driver", projectPath +
		// "/browserDriver/Chromedriver");
		// driver = new ChromeDriver();
		// Hàm này áp dụng việc tìm element(findElement/ findElements)
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		// Phóng to browser lên
		// driver.manage().window().maximize();
		// Mở app lên

	}

	//@Test
	public void TC_01() {
		driver.get("https://kyna.vn/");
		// driver.switchTo().frame(0);
		// index
		// ID/Name
		// driver.switchTo().frame(ID/Name);
		// WebElement
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.fanpage iframe")));
		String FBLikeNumber = driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div"))
				.getText();
		Assert.assertEquals(FBLikeNumber, "166K likes");
		driver.switchTo().defaultContent();
		driver.switchTo().frame("cs_chat_iframe");
		driver.findElement(By.cssSelector("div.border_overlay")).click();
		sleepInsecond(3);
		driver.findElement(By.xpath("//input[@ng-model='login.username']")).sendKeys("cuong nguyen");
		driver.findElement(By.xpath("//input[@ng-model='login.phone']")).sendKeys("0367887486");
		new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
		driver.findElement(By.xpath("//textarea[@ng-model='login.content']")).sendKeys("chat lan dau");
		sleepInsecond(5);
		driver.switchTo().defaultContent();
		String keyInput = "Excel";
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys(keyInput);
		driver.findElement(By.cssSelector("button.search-button")).click();
		sleepInsecond(5);
		
		List<WebElement> courseNames = driver.findElements(By.cssSelector("div.content>h4"));
		
		Assert.assertEquals(courseNames.size(), 10);
		for (WebElement course : courseNames) {
			System.out.println(course.getText());
			Assert.assertTrue(course.getText().contains(keyInput));
			
		}
				
		
	

	}

	@Test
	public void TC_02_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		driver.switchTo().frame("login_page");
		driver.findElement(By.name("fldLoginUserId")).sendKeys("cuongnuyen");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		Assert.assertTrue(driver.findElement(By.name("fldPassword")).isDisplayed());
		
		driver.findElement(By.name("fldPassword")).sendKeys("123456");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		
	}

	@Test
	public void TC_03() {
	}

	public void sleepInsecond(long timeInsecond) {
		try {
			Thread.sleep(timeInsecond * 1000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}