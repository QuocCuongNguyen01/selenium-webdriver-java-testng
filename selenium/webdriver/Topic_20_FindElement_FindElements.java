package webdriver;

import java.util.List;
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

public class Topic_20_FindElement_FindElements {

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
		driver.manage().window().maximize();
		// Mở app lên
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");

	}

	@Test
	public void TC_01_FindElement() {
		// Tìm thấy duy nhất 1 element/ node
		// -Tìm thấy và thao tác trực tiếp trên node đó
		// -vì nó tìm thấy nên không cần phải chờ hêt time out
		driver.findElement(By.cssSelector("input#email"));

		// Tìm thấy nhiều hơn 1 element/note
		// - Nó sẽ thao tác với node đầu tiên
		// - Không quan tâm các node còn lại
		// - Trong trường hợp bắt locator sai thì nó tìm sai
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("cuongdnqb5@gmail.com");
		// Không tìm thấy element/ node nào
		// - Có cơ chế tìm lại = nửa giây (0,5s) sẽ tìm lại 1 lần
		// - Nếu trong thời gian đang tìm lại mà thấy element thì thõa mãn điều kiện -
		// pass
		// - Nếu thời gian hêt 15s mà vẫn không tìm thấy element thì :
		// +Đánh fail testcase + không chay step tiếp theo
		// +Throw ra 1 ngoại lệ: NoSuchElementException
		driver.findElement(By.cssSelector("input[type='check']"));
	}

	@Test
	public void TC_02_FindElements() {
		// Tìm thấy duy nhất 1 element/ node
		// -Tìm thấy và thao tác trực tiếp trên node đó
		// -vì nó tìm thấy nên không cần phải chờ hêt time out
		List<WebElement> elements = driver.findElements(By.cssSelector("input#email"));
		System.out.println("List element number = " + elements.size());
		// Tìm thấy nhiều hơn 1 element/note
		elements = driver.findElements(By.cssSelector("input[type='email']"));
		System.out.println("List element number = " + elements.size());
		// Không tìm thấy element/ node nào
		// - Có cơ chế tìm lại = nửa giây (0,5s) sẽ tìm lại 1 lần
		// - Nếu trong thời gian đang tìm lại mà thấy element thì thõa mãn điều kiện -pass
		// - Nếu thời gian hêt 15s mà vẫn không tìm thấy element thì :
		//+ Không đánh fail testcase + vẫn chạy step tiếp theo
		//+ Trả về 1 list rỗng(empty) = 0
		elements = driver.findElements(By.cssSelector("input[type='check']"));
		System.out.println("List element number = " + elements.size());
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