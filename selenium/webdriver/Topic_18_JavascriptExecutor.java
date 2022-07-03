package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_JavascriptExecutor {

WebDriver driver;
JavascriptExecutor jsExecutor;
String osName = System.getProperty("os.name");
String projectPath = System.getProperty("user.dir");

@BeforeClass
public void beforeClass() {
	if (osName.contains("Mac OS")) {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
		
	}else {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDriver\\geckodriver");
	}
	
	// Mở browser lên
	//System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
	driver = new FirefoxDriver();
	jsExecutor = (JavascriptExecutor) driver;
	
	//System.setProperty("webdriver.chrome.driver", projectPath + "/browserDriver/Chromedriver");
	//driver = new ChromeDriver();
	// Hàm này áp dụng việc tìm element(findElement/ findElements)
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	// Phóng to browser lên
	driver.manage().window().maximize();
	// Mở app lên
	


}

//@Test
public void TC_01_TechPanda() {
	navigateToUrlByJS("http://live.techpanda.org/");
	sleepInsecond(3);
	
	String techPandaDomain = (String) executeForBrowser("return document.domain");
	Assert.assertEquals(techPandaDomain, "live.techpanda.org");
	
	String homepagePandaDomain = (String) executeForBrowser("return document.URL");
	Assert.assertEquals(homepagePandaDomain, "http://live.techpanda.org/");

	hightlightElement("//a[text()='Mobile']");
	clickToElementByJS("//a[text()='Mobile']");
	sleepInsecond(3);

	hightlightElement("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
	
	clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
	sleepInsecond(3);

	Assert.assertTrue(areExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));
	
	hightlightElement("//a[text()='Customer Service']");
	clickToElementByJS("//a[text()='Customer Service']");
	sleepInsecond(3);

	hightlightElement("//input[@id='newsletter']");
	scrollToElementOnTop("//input[@id='newsletter']");
	
	String emailAddress = "cuongdnqb6"+ generateRandomNumber() +"@gmail.com"; 
	sendkeyToElementByJS("//input[@id='newsletter']", emailAddress);	
	
	hightlightElement("//button[@title='Subscribe']");
	clickToElementByJS("//button[@title='Subscribe']");
	sleepInsecond(3);
	Assert.assertTrue(areExpectedTextInInnerText("Thank you for your subscription."));
	
	navigateToUrlByJS("http://demo.guru99.com/v4/");
	sleepInsecond(3);

	String demoGuruDomain = (String) executeForBrowser("return document.domain");
	Assert.assertEquals(demoGuruDomain, "demo.guru99.com");
	

}
@Test
public void TC_02_Rode() {
	driver.get("https://warranty.rode.com/");
	
	By registerButton	 = By.xpath("//button[contains(text(),'Register')]");
	driver.findElement(registerButton).click();
	sleepInsecond(2);
	
	Assert.assertEquals(getElementValidationMessage("//input[@id='firstname']"), "Please fill out this field.");
	driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("automationFC");
	sleepInsecond(2);
	driver.findElement(registerButton).click();
	Assert.assertEquals(getElementValidationMessage("//input[@id='surname']"), "Please fill out this field.");
	driver.findElement(By.xpath("//input[@id='surname']")).sendKeys("automationFC");
	sleepInsecond(2);
	driver.findElement(registerButton).click();
	Assert.assertEquals(getElementValidationMessage("//div[contains(text(),'Register')]//following-sibling::div//input[@id='email']"), "Please fill out this field.");
	driver.findElement(By.xpath("//div[contains(text(),'Register')]//following-sibling::div//input[@id='email']")).sendKeys("automationFC@gmail.co");
	driver.findElement(registerButton).click();
	Assert.assertEquals(getElementValidationMessage("//div[contains(text(),'Register')]//following-sibling::div//input[@id='password']"), "Please fill out this field.");

	sleepInsecond(2);
	driver.findElement(registerButton).click();
	Assert.assertEquals(getElementValidationMessage("//div[contains(text(),'Register')]//following-sibling::div//input[@id='password']"), "Please fill out this field.");
	driver.findElement(By.xpath("//div[contains(text(),'Register')]//following-sibling::div//input[@id='password']")).sendKeys("automationFC");
	sleepInsecond(2);
	driver.findElement(registerButton).click();
	Assert.assertEquals(getElementValidationMessage("//input[@id='password-confirm']"), "Please fill out this field.");
	driver.findElement(By.xpath("//input[@id='password-confirm']")).sendKeys("automationFC");sleepInsecond(2);
	
	driver.findElement(registerButton).click();
	
	
	
}
public Object executeForBrowser(String javaScript) {
	return jsExecutor.executeScript(javaScript);
}

public String getInnerText() {
	return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
}

public boolean areExpectedTextInInnerText(String textExpected) {
	String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
	return textActual.equals(textExpected);
}

public void scrollToBottomPage() {
	jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
}

public void navigateToUrlByJS(String url) {
	jsExecutor.executeScript("window.location = '" + url + "'");
}

public void hightlightElement(String locator) {
	WebElement element = getElement(locator);
	String originalStyle = element.getAttribute("style");
	jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
	sleepInsecond(1);
	jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
}

public void clickToElementByJS(String locator) {
	jsExecutor.executeScript("arguments[0].click();", getElement(locator));
}

public void scrollToElementOnTop(String locator) {
	jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
}

public void scrollToElementOnDown(String locator) {
	jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
}

public void sendkeyToElementByJS(String locator, String value) {
	jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
}

public void removeAttributeInDOM(String locator, String attributeRemove) {
	jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
}

public String getElementValidationMessage(String locator) {
	return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
}

public boolean isImageLoaded(String locator) {
	boolean status = (boolean) jsExecutor.executeScript(
			"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
			getElement(locator));
	return status;
}
public int generateRandomNumber() {
	Random rand = new Random();
	return rand.nextInt(999);
	
	
}
public WebElement getElement(String locator) {
	return driver.findElement(By.xpath(locator));
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