package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Xpath{
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
		driver.get("https://www.facebook.com/");
	
	}

	@Test
	public void TC_01() {
		// Tìm(find)
		// Thao tác trực tiếp không khai báo biến - sử dụng 1 lần/ Không dùng lại element này
		driver.findElement(By.id("")).getText();
		driver.findElement(By.id("")).click();
		driver.findElement(By.id("")).isDisplayed();
		
		
		driver.findElements(By.id(""));
		// Thao tác trực tiếp không khai báo biến  - dùng lại nhiều lần
		
		WebElement loginButton = driver.findElement(By.id(""));
		loginButton.click();
		loginButton.isDisplayed();
		// Tìm(find) - số nhiều - trả về 1 hoặc >1
		driver.findElements(By.id("")).size();
		// Lặp lại nhiều lần
		List<WebElement> loginCheckboxs = driver.findElements(By.id(""));
		for (int i = 0; i < loginCheckboxs.size(); i++) {
			loginCheckboxs.get(i).click();
			
		}
		
		// Thao tác (Action): click/ type/ select/ hover/ ...
	
		// Kiểm tra(verify/ Assert) getText/ getAttribute/ getCss/ ...
		
		
		// Thao tác với email textbox
		
		// Thao tác với password textbox
		
		// Thao tác login
		
	}
	@Test
	public void TC_02() {
		
	}
	@Test
	public void TC_03() {
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}