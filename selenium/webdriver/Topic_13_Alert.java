package webdriver;

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

public class Topic_13_Alert {

WebDriver driver;
Alert alert;
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

	


}

//@Test
public void TC_01_Accept_Alert() {
	driver.get("https://automationfc.github.io/basic-form/index.html");
	driver.findElement(By.xpath("//div/button[text()='Click for JS Alert']")).click();
	sleepInsecond(3);
	// Nó chỉ khởi tạo khi element/alert đó xuất hiện
	// Muốn thao tác vào Alert đang bật lên thì phải switch vẻo nó
	alert = driver.switchTo().alert();
	// Verify
	Assert.assertEquals(alert.getText(), "I am a JS Alert");
	// Accept Alert
	alert.accept();
	Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");
	
}
//@Test
public void TC_02_Confirm_Alert() {
	driver.get("https://automationfc.github.io/basic-form/index.html");
	driver.findElement(By.xpath("//div/button[text()='Click for JS Confirm']")).click();
	sleepInsecond(3);
	alert = driver.switchTo().alert();
	Assert.assertEquals(alert.getText(), "I am a JS Confirm");
	// Cancel
	alert.dismiss();
	// Verify được cancel thành công
	Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");
	
	
}
//@Test
public void TC_03_Prompt_Alert() {
	driver.get("https://automationfc.github.io/basic-form/index.html");
	driver.findElement(By.xpath("//div/button[text()='Click for JS Prompt']")).click();
	sleepInsecond(3);
	alert = driver.switchTo().alert();
	Assert.assertEquals(alert.getText(), "I am a JS prompt");
	String text = "Cuongtestprompt";
	alert.sendKeys(text);
	sleepInsecond(3);
	alert.accept();
	// Verify Alert được accept thành công
	Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: "+ text);
	
	
	
}
@Test
public void TC_04_Authentication_Alert() {
	// Selenium nó cho mình pass user/password vào trước khi open URL
	// Format: http(s)://username:password@domain
	String username = "admin";
	String password = "admin";
	String url = "http://" + username +":"+ password +"@"+"the-internet.herokuapp.com/basic_auth";
	driver.get(url);
	Assert.assertEquals(driver.findElement(By.cssSelector("div#content p")).getText(), "Congratulations! You must have the proper credentials.");
	
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