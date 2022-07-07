package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Element_Condition_Status {

	WebDriver driver;
	String osName = System.getProperty("os.name");
	String projectPath = System.getProperty("user.dir");
	WebDriverWait expliciwait;

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
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// Phóng to browser lên
		driver.manage().window().maximize();
		// Mở app lên
		expliciwait = new WebDriverWait(driver, 10);

	}

//@Test
	public void TC_01_Visible_Displayed_Visibility() {
		driver.get("https://www.facebook.com/");
		// 1. có trong UI (bắt buộc)
		// 1. có trong HTML (bắt buộc)
		// chờ cho email address textbox hiển thị trong vòng 10s

		expliciwait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("email")));
		driver.findElement(By.id("email")).sendKeys("automationFC@gmail.com");

	}

	// @Test
	public void TC_02_Invisible_Undisplayed_Invisibility_I() {
		driver.get("https://www.facebook.com/");
		// 2.Không có trên UI (bắt buộc)
		// 1.Có trong HTML
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		// chờ cho Re-enter email textbox không hiển thị trong vòng 10s
		expliciwait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
	}

	// @Test
	public void TC_02_Invisible_Undisplayed_Invisibility_II() {
		// 2.Không có trên UI (bắt buộc)
		// 2.không Có trong HTML
		driver.get("https://www.facebook.com/");
		// chờ cho Re-enter email textbox không hiển thị trong vòng 10s
		expliciwait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
	}

	//@Test
	public void TC_03_Presence_I() {
		// Có trong UI
		// Có trong HTML (Bắt buộc)
		// chờ cho email address textbox presence trong HTML hiển thị trong vòng 10s
		driver.get("https://www.facebook.com/");
		expliciwait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
	}

	//@Test
	public void TC_03_Presence_II() {
		driver.get("https://www.facebook.com/");
		// 2.Không có trên UI
		// 1.Có trong HTML(bắt buộc)
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		// chờ cho Re-enter email textbox không hiển thị trong vòng 10s
		expliciwait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
	}

	@Test
	public void TC_04_Staleness() {
		// 2.Không có trên UI (bắt buộc)
		// 2.không Có trong HTML
		driver.get("https://www.facebook.com/");
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		sleepInsecond(2);
		WebElement reEnterEmailAddressTextbox = driver.findElement(By.name("reg_email_confirmation__"));
		
		driver.findElement(By.cssSelector("img._8idr")).click();
		// chờ cho Re-enter email textbox không hiển thị trong vòng 10s
		expliciwait.until(ExpectedConditions.stalenessOf(reEnterEmailAddressTextbox));

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