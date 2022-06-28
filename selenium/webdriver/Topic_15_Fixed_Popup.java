package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
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
JavascriptExecutor jsExecutor;

@BeforeClass
public void beforeClass() {
	
	// Mở browser lên
	System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
	driver = new FirefoxDriver();
	jsExecutor = (JavascriptExecutor) driver;
	
	//System.setProperty("webdriver.chrome.driver", projectPath + "/browserDriver/Chromedriver");
	//driver = new ChromeDriver();
	// Hàm này áp dụng việc tìm element(findElement/ findElements)
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	// Phóng to browser lên
	driver.manage().window().maximize();
	userName = "cuongdnqb5@gmail.com";
	passWord = "123456";


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
//@Test
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
//@Test
public void TC_03_Fixed_Not_In_DOM() {
	driver.get("https://tiki.vn/");
	List<WebElement> loginPopup = driver.findElements(By.cssSelector("div.ReactModal__Overlay"));
	//Undisplayer
	Assert.assertEquals(loginPopup.size(), 0);
	
	driver.findElement(By.xpath("//span[text()='Đăng Nhập / Đăng Ký']")).click();
	
	Assert.assertTrue(driver.findElement(By.cssSelector("div.ReactModal__Overlay")).isDisplayed());
	
	loginPopup = driver.findElements(By.cssSelector("div.ReactModal__Overlay"));
	
	
	Assert.assertEquals(loginPopup.size(), 1);	
	Assert.assertTrue(loginPopup.get(0).isDisplayed());	
	sleepInsecond(5);
	
	driver.findElement(By.cssSelector("img.close-img")).click();
	
	loginPopup = driver.findElements(By.cssSelector("div.ReactModal__Overlay"));
	
	
	Assert.assertEquals(loginPopup.size(), 0);	
	
	
	
}
//@Test
public void TC_04_Random_In_DOM_KMPlayer() {
	driver.get("https://www.kmplayer.com/home");
	WebElement popupLayer = driver.findElement(By.cssSelector("div.pop-layer"));
	
	if (popupLayer.isDisplayed()) {
		System.out.println("Step close popup");
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("area#btn-r")));
		sleepInsecond(3);
	}
	System.out.println("Step close popup 2");
	driver.findElement(By.xpath("//p[text()='Buy us a Coffee']")).click();
	Assert.assertEquals(driver.getCurrentUrl(), "https://www.buymeacoffee.com/kmplayer?ref=hp&kind=top");
}
@Test
public void TC_05_Random_Not_In_DOM() {
	
	driver.manage().window().setSize(new Dimension(1366,768));
	
	driver.get("https://dehieu.vn/");
	List<WebElement> contentPopup = driver.findElements(By.cssSelector("div.popup-content"));
 sleepInsecond(3);
	if (contentPopup.size() > 0 && contentPopup.get(0).isDisplayed()) {
		System.out.println("Đóng popup và qua bước kế tiếp");
		driver.findElement(By.cssSelector("input#popup-name")).sendKeys("cuongnguyen");
		driver.findElement(By.cssSelector("input#popup-email")).sendKeys("cuongnguyen@gmail.com");
		driver.findElement(By.cssSelector("input#popup-phone")).sendKeys("0367887486");
		sleepInsecond(10);
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("button#close-popup")));
		//driver.findElement(By.cssSelector("button#close-popup")).click();
	}
	System.out.println("Không có  popup và qua bước kế tiếp");
	driver.findElement(By.xpath("//a[text()='Tất cả khóa học']")).click();
	Assert.assertEquals(driver.getCurrentUrl(), "https://dehieu.vn/khoa-hoc");
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