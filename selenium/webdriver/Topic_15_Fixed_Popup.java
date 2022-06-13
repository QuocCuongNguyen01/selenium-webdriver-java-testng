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

public class Topic_15_Fixed_Popup {

WebDriver driver;
String userName, passWord;
String projectPath = System.getProperty("user.dir");

@BeforeClass
public void beforeClass() {
	
	// Mở browser lên
	System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
	driver = new FirefoxDriver();
	
	//System.setProperty("webdriver.chrome.driver", projectPath + "/browserDriver/Chromedriver");
	//driver = new ChromeDriver();
	// Hàm này áp dụng việc tìm element(findElement/ findElements)
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	// Phóng to browser lên
	driver.manage().window().maximize();
	userName = "automationfc";
	passWord = "automationfc";


}

//@Test
public void TC_01_Ngoaingu24h() {
	driver.get("https://ngoaingu24h.vn/");
	WebElement loginPopup = driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]"));
	
	// Verify popup isn't display
	Assert.assertFalse(loginPopup.isDisplayed());
	
	driver.findElement(By.cssSelector("button.login_")).click();
	Assert.assertTrue(driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]")).isDisplayed());
	driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//input[@id='account-input']")).sendKeys(userName);
	driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//input[@id='password-input']")).sendKeys(passWord);
	sleepInsecond(3);
	driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//button[contains(@class,'btn-login-v1')]")).click();	
	sleepInsecond(5);

	Assert.assertEquals(driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//div[@class='row error-login-panel']")).getText(), "Tài khoản không tồn tại!");
	
	driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//button[@class='close']")).click();
	
	Assert.assertFalse(loginPopup.isDisplayed());
}
@Test
public void TC_02_Kyna() {
	driver.get("https://kyna.vn/");
	WebElement dangnhapPopup = driver.findElement(By.cssSelector("div#k-popup-account-login"));
	
	Assert.assertFalse(dangnhapPopup.isDisplayed());
	
	driver.findElement(By.cssSelector("a.login-btn")).click();
	
	Assert.assertTrue(dangnhapPopup.isDisplayed());
	
	driver.findElement(By.cssSelector("input#user-login")).sendKeys(userName);
	driver.findElement(By.cssSelector("input#user-password")).sendKeys(passWord);
	sleepInsecond(5);

	driver.findElement(By.cssSelector("button#btn-submit-login")).click();
	sleepInsecond(5);
	Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");
	
	driver.findElement(By.cssSelector("button.k-popup-account-close")).click();
	
	Assert.assertFalse(dangnhapPopup.isDisplayed());
	
	
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