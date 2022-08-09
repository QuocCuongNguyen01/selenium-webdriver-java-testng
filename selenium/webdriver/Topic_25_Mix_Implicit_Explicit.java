package webdriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

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

public class Topic_25_Mix_Implicit_Explicit {

	WebDriver driver;
	String osName = System.getProperty("os.name");
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;

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

	}

	public void TC_01_Element_Found() {
		// Element có xuất hiện và không cần chờ hêt timeout
		// Dù có xét cả 2 loại wait thì đều không ảnh hưởng
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 10);
		// Implicit Wait: chỉ áp dụng cho việc findElement/ findElements
		// Explicit Wait: Cho các điều kiện của element

		driver.get("https://wwww.facebook.com/");
		// Explicit
		System.out.println("Thời gian bắt đầu của explicit:" + getTimeStamp());
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("input#email")));
		System.out.println("Thời gian kết thúc của exp;icit:" + getTimeStamp());

		System.out.println("Thời gian bắt đầu của implicit:" + getTimeStamp());
		driver.findElement(By.cssSelector("input#email"));
		System.out.println("Thời gian kết thúc của implicit:" + getTimeStamp());
	}

	public void TC_02_Element_Not_Found_Implicit() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("https://wwww.facebook.com/");

		System.out.println("Thời gian bắt đầu của implicit:" + getTimeStamp());
		try {
			driver.findElement(By.cssSelector("input#cuongnguyen"));
		} catch (Exception e) {
			System.out.println("Thời gian kết thúc của implicit:" + getTimeStamp());

		}

	}

	public void TC_03_Element_Not_Found_Implicit_Explicit() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 8);

		driver.get("https://wwww.facebook.com/");

		// System.out.println("Thời gian bắt đầu của implicit:" + getTimeStamp());
		/// try {
		// driver.findElement(By.cssSelector("input#cuongnguyen"));
		// } catch (Exception e) {
		// System.out.println("Thời gian kết thúc của implicit:" + getTimeStamp());

		// }
		System.out.println("Thời gian bắt đầu của explicit:" + getTimeStamp());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#cuongnguyen")));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Thời gian kết thúc của exp;icit:" + getTimeStamp());
		}

	}

	public void TC_04_Element_Not_Found_Explicit_By() {

		explicitWait = new WebDriverWait(driver, 5);

		driver.get("https://wwww.facebook.com/");

		// Explicit - By là tham số nhận vào của hàmg Explicit -
		// visibilityOfElementLocated(By)
		// Implicit = 0
		// Tổng time = Explicit
		System.out.println("Thời gian bắt đầu của explicit:" + getTimeStamp());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#cuongnguyen")));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Thời gian kết thúc của exp;icit:" + getTimeStamp());
		}
	}
	@Test
	public void TC_05_Element_Not_Found_Explicit_Element() {

		explicitWait = new WebDriverWait(driver, 5);

		driver.get("https://wwww.facebook.com/");

		// Explicit - By là tham số nhận vào của hàmg Explicit -
		// visibilityOfElementLocated(By)
		// Implicit = 0
		// Tổng time = Explicit
		System.out.println("Thời gian bắt đầu của explicit:" + getTimeStamp());
		try {
			explicitWait
					.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#cuongnguyen"))));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Thời gian kết thúc của exp;icit:" + getTimeStamp());
		}
	}

	@AfterClass
	public void afterClass() {
		// Show ra time-Stamp tại thời điểm hàm này
		// Time-Stamp = ngày - giờ - phút - giây

		driver.quit();
	}

	public String getTimeStamp() {
		Date date = new Date();
		return date.toString();
	}
}
