package webdriver;

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

public class Topic_07_Web_Element_PII {
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
		
	
	}

	@Test
	public void TC_01_isDisplayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement emailTextbox = driver.findElement(By.id("mail"));
		if (emailTextbox.isDisplayed()) {
			emailTextbox.sendKeys("Automation Testing");
			System.out.println("Email Textbox is displayed");
		} else {
			System.out.println("Email Textbox is not  displayed");
		}
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement ageUnder18Radio = driver.findElement(By.id("under_18"));
		if (ageUnder18Radio.isDisplayed()) {
			ageUnder18Radio.click();
			System.out.println("Age under 18 Radio is displayed");
		} else {
			System.out.println("Age under 18 Radio is not  displayed");
		}
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement educationTextarea = driver.findElement(By.id("edu"));
		if (educationTextarea.isDisplayed()) {
			educationTextarea.sendKeys("Automation Testing");
			System.out.println("Education Textarea is displayed");
		} else {
			System.out.println("Education Textarea is not  displayed");
		}
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement name5Text = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
		if (name5Text.isDisplayed()) {
			System.out.println("Name 5 Text is displayed");
		} else {
			System.out.println("Name 5 Text is not  displayed");
		}
		
		
	}
	@Test
	public void TC_02_Enabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement emailTextbox = driver.findElement(By.id("mail"));
		if (emailTextbox.isEnabled()) {
			System.out.println("Email Textbox is Enabled");
		} else {
			System.out.println("Email Textbox is not Enabled");
		}
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement ageUnder18Radio = driver.findElement(By.id("under_18"));
		if (ageUnder18Radio.isEnabled()) {
			System.out.println("Age under 18 Radio is Enabled");
		} else {
			System.out.println("Age under 18 Radio is not  Enabled");
		}
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement educationTextarea = driver.findElement(By.id("edu"));
		if (educationTextarea.isEnabled()) {	
			System.out.println("Education Textarea is Enabled");
		} else {
			System.out.println("Education Textarea is not Enabled");
		}
		
	}
	@Test
	public void TC_03() {
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}