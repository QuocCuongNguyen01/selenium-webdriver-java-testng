package webdriver;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Window_Tab {

	WebDriver driver;
	Alert alert;
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
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		// Phóng to browser lên
		driver.manage().window().maximize();
		// Mở app lên

	}

//@Test
	public void TC_01_Basic_form() {
		driver.get("https://automationfc.github.io/basic-form/");
		String formTabID = driver.getWindowHandle();
		System.out.println("Form ID:" + formTabID);
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();

		switchToWindowByID(formTabID);

		sleepInsecond(5);
		System.out.println("Form URL:" + driver.getCurrentUrl());
		System.out.println("Form title:" + driver.getTitle());
		// driver.switchTo().window();
		driver.findElement(By.name("q")).sendKeys("cuongnguyen");
		String googleID = driver.getWindowHandle();
		switchToWindowByID(googleID);
		sleepInsecond(5);

	}

	@Test
	public void TC_01_Basic_forms() {
		driver.get("https://automationfc.github.io/basic-form/");
		
		String parentID = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInsecond(2);
		switchToWindowByTitle("Google");

		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");

		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInsecond(2);
		switchToWindowByTitle("Facebook – log in or sign up");

		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");

		driver.findElement(By.xpath("//a[text()='LAZADA']")).click();
		sleepInsecond(2);
		switchToWindowByTitle("Shopping online - Buy online on Lazada.vn");

		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");

		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleepInsecond(5);
		switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		closeAllWindowWithoutParent(parentID);
		sleepInsecond(5);
		
	}

	public void switchToWindowByID(String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();

		for (String id : allWindowIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				sleepInsecond(3);
			}
		}
	}

	public void switchToWindowByTitle(String expectedTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			driver.switchTo().window(id);
			String currentPageTitle = driver.getTitle();
			if (currentPageTitle.equals(expectedTitle)) {
				break;

			}

		}
	}

	public void closeAllWindowWithoutParent(String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
				sleepInsecond(2);
			}

		}
		driver.switchTo().window(parentID);
	}

	// @Test
	public void TC_02_TechPanda() {
		driver.get("http://live.techpanda.org/index.php/mobile.html");
		driver.findElement(By.xpath(
				"//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']"))
				.click();

		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),
				"The product Sony Xperia has been added to comparison list.");
		driver.findElement(By.xpath(
				"//a[text()='IPhone']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']"))
				.click();

		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),
				"The product IPhone has been added to comparison list.");
		driver.findElement(By.xpath(
				"//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']"))
				.click();

		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),
				"The product Samsung Galaxy has been added to comparison list.");

		driver.findElement(By.cssSelector("button[title='Compare']")).click();

		sleepInsecond(3);

		switchToWindowByTitle("Products Comparison List - Magento Commerce");

		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Compare Products']")).isDisplayed());
		driver.findElement(By.xpath("//button[@title='Close Window']")).click();

		sleepInsecond(3);
		switchToWindowByTitle("Mobile");

		driver.findElement(By.cssSelector("input#search")).sendKeys("cuongnguyen");
		sleepInsecond(3);
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		alert = driver.switchTo().alert();

		sleepInsecond(3);

		alert.accept();

		Assert.assertTrue(
				driver.findElement(By.xpath("//span[text()='The comparison list was cleared.']")).isDisplayed());
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