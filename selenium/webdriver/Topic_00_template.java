package webdriver;

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

public class Topic_00_template {

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
	
}
@Test
public void TC_02() {
	
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