package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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

public class Topic_12_Custom_Checkbox_Radio {

WebDriver driver;

String projectPath = System.getProperty("user.dir");
JavascriptExecutor jsExecutor;

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
	jsExecutor = (JavascriptExecutor) driver;
	


}

//@Test
public void TC_01_Custom_Checkbox() {
	driver.get("https://material.angular.io/components/radio/examples");
	sleepInsecond(4);
	//By checkedCheckbox = By.xpath("//span[text()=' Summer ']");
	//driver.findElement(checkedCheckbox).click();
	//sleepInsecond(3);
	//	Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());
	//	By checkedCheckboxText = By.xpath("//span[text()=' Summer ']");
	//	driver.findElement(checkedCheckboxText).click();
	//	sleepInsecond(3);
	//	By checkedCheckbox = By.xpath("//span[text()=' Summer ']/preceding-sibling::span/input");
	//	Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());
	//Thỏa mãn điều kiện vừa click/vừa verify được
	//Thẻ input để click = > Pass (javaScriptExecutor)
	//Thẻ input để verify = > Pass
	//JavaScriptExcutor: Element bị che vẫn click được (không quan tâm bị che)
	
	By beforRadio = By.xpath("//span[text()=' Summer ']/preceding-sibling::span/input");
	jsExecutor.executeScript("arguments[0].click();", driver.findElement(beforRadio));
	sleepInsecond(3);
	Assert.assertTrue(driver.findElement(beforRadio).isSelected());
	
	driver.get("https://material.angular.io/components/checkbox/examples");
	
	By checkedCheckbox = By.xpath("//span[text()='Checked']/preceding-sibling::span/input");
	jsExecutor.executeScript("arguments[0].click();", driver.findElement(checkedCheckbox));
	sleepInsecond(3);
	Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());
	
	By checkedCheckbox1 = By.xpath("//span[text()='Indeterminate']/preceding-sibling::span/input");
	jsExecutor.executeScript("arguments[0].click();", driver.findElement(checkedCheckbox1));
	sleepInsecond(3);
	Assert.assertTrue(driver.findElement(checkedCheckbox1).isSelected());
	
	}
@Test
public void TC_02_Google_Docs() {
	driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
	//RadioButton
	By hanoiRadio = By.xpath("//div[@aria-label='Hà Nội']");
	driver.findElement(hanoiRadio).click();
	sleepInsecond(2);
	Assert.assertEquals(driver.findElement(hanoiRadio).getAttribute("aria-checked"), "true");
	
	//Checkbox
	By quangnamCheckbox  = By.xpath("//div[@aria-label='Quảng Nam']/parent::div");
	driver.findElement(quangnamCheckbox).click();
	sleepInsecond(2);
	Assert.assertEquals(driver.findElement(By.xpath("//div[@aria-label='Quảng Nam']")).getAttribute("aria-checked"), "true");
	
	
	
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