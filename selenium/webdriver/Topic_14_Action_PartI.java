package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Action_PartI {

WebDriver driver;
Actions action;
JavascriptExecutor jsExecutor;
String projectPath = System.getProperty("user.dir");

@BeforeClass
public void beforeClass() {
	
	// Mở browser lên
	System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
	driver = new FirefoxDriver();
	
	//System.setProperty("webdriver.chrome.driver", projectPath + "/browserDriver/Chromedriver");	
	//driver = new ChromeDriver();
	
	action = new Actions(driver);
	jsExecutor = (JavascriptExecutor) driver;
	// Hàm này áp dụng việc tìm element(findElement/ findElements)
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	// Phóng to browser lên
	driver.manage().window().maximize();



}

//@Test
public void TC_01_Hover() {
	driver.get("https://automationfc.github.io/jquery-tooltip/");
	
	WebElement ageTextbox = driver.findElement(By.cssSelector("input#age"));
	
	action.moveToElement(ageTextbox).perform();
	sleepInsecond(5);
	
	Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
	
	
}
//@Test
public void TC_02_Hover() {
driver.get("http://www.myntra.com/");
	
	WebElement kidLink = driver.findElement(By.xpath("//div[@class='desktop-navLink']/a[text()='Kids']"));
	
	action.moveToElement(kidLink).perform();
	sleepInsecond(5);
	
	action.click(driver.findElement(By.xpath("//li/a[text()='Home & Bath']"))).perform();
	sleepInsecond(5);
	Assert.assertEquals(driver.getCurrentUrl(),"https://www.myntra.com/kids-home-bath");
	Assert.assertTrue(driver.findElement(By.xpath("//li/span[text()='Kids Home Bath']")).isDisplayed());
	
}
//@Test
public void TC_03_Hover() {
	driver.get("https://fptshop.com.vn/");
	WebElement phoneLink = driver.findElement(By.xpath("//a[@title='ĐIỆN THOẠI']"));
	action.moveToElement(phoneLink).perform();
	sleepInsecond(5);
	action.click(driver.findElement(By.xpath("//a[@title='Apple (iPhone)']"))).perform();
	sleepInsecond(5);
	Assert.assertEquals(driver.getCurrentUrl(),"https://fptshop.com.vn/dien-thoai/apple-iphone");

}
//@Test
public void TC_04_Click_And_Hold_Block() {
	driver.get("https://automationfc.github.io/jquery-selectable/");
	// Store list element 
	List<WebElement> allNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));
	
	Assert.assertEquals(allNumbers.size(), 12);
	// Click và giữ chuột tại vị trí 1 
	action.clickAndHold(allNumbers.get(0))
	// Di chuyển đến 11
	.moveToElement(allNumbers.get(10))
	// Nhả chuột
	.release()
	// Thực thi
	.perform();
	sleepInsecond(4);
	
	allNumbers = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
	
	Assert.assertEquals(allNumbers.size(), 9);
	
}
//@Test
public void TC_05_Click_And_Hold_Random() {
	driver.get("https://automationfc.github.io/jquery-selectable/");
	// Store list element 
	List<WebElement> allNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));
	
	Assert.assertEquals(allNumbers.size(), 12);
	// Giữ phím 
	action.keyDown(Keys.COMMAND).perform();
	// Chọn 
	action.click(allNumbers.get(0)).click(allNumbers.get(2)).click(allNumbers.get(4)).click(allNumbers.get(6)).click(allNumbers.get(8)).click(allNumbers.get(10)).perform();
	// Nhả phím
	action.keyUp(Keys.COMMAND).perform();
	
	sleepInsecond(4);
	
	allNumbers = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
	
	Assert.assertEquals(allNumbers.size(), 6);
	
}
//@Test
public void TC_06_Double_Click() {
	driver.get("https://automationfc.github.io/basic-form/index.html");
	
	WebElement doubleClick = driver.findElement(By.xpath("//button[@ondblclick='doubleClickMe()']"));
	
	jsExecutor.executeScript("arguments[0].scrollIntoView(true);", doubleClick);
	
	action.doubleClick(driver.findElement(By.xpath("//button[@ondblclick='doubleClickMe()']"))).perform();
	
	Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
	
	sleepInsecond(3);
}
//@Test
public void TC_07_Right_Click() {
	driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
	action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
	sleepInsecond(3);
	
	WebElement deleteBefore = driver.findElement(By.cssSelector("li.context-menu-icon-delete"));
	action.moveToElement(deleteBefore).perform();
	
	Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-delete.context-menu-visible.context-menu-hover")).isDisplayed());	
	action.click(deleteBefore).perform();
	
	Alert alert = driver.switchTo().alert();
	Assert.assertEquals(alert.getText(), "clicked: delete");
	alert.accept();
	sleepInsecond(3);
	
	Assert.assertFalse(deleteBefore.isDisplayed());
	}
@Test
public void TC_08_Drag_And_Drop() {
	driver.get("https://automationfc.github.io/kendo-drag-drop/");
	
	WebElement aCircle = driver.findElement(By.cssSelector("div#draggable"));
	WebElement bCircle = driver.findElement(By.cssSelector("div#droptarget"));
	
	action.dragAndDrop(aCircle, bCircle).perform();
	sleepInsecond(3);
	
	// Verify Text
	Assert.assertEquals(bCircle.getText(),"You did great!");
	
	String loginButtonBackgroundHexa = Color.fromString(bCircle.getCssValue("background-color")).asHex().toUpperCase();
	System.out.println(loginButtonBackgroundHexa);
	sleepInsecond(3);

	Assert.assertEquals(loginButtonBackgroundHexa, "#03A9F4");
	
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