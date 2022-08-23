package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_27_Page_Ready {

	WebDriver driver;
	String osName = System.getProperty("os.name");
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	Actions action;

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
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		action = new Actions(driver);
	}

	
	public void TC_01_OrangeHRM() {
		String nameInput = "Peter Mac";
		driver.get("https://opensource-demo.orangehrmlive.com");
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();
		Assert.assertTrue(isPageLoadedSuccess());
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='total']//span[text()='3 month(s)']")).getText(),"3 month(s)");
		driver.findElement(By.xpath("//a[@class='firstLevelMenu']/b[text()='PIM']")).click();
		Assert.assertTrue(isPageLoadedSuccess());
		driver.findElement(By.xpath("//input[@id='empsearch_employee_name_empName']")).sendKeys(nameInput);
		driver.findElement(By.xpath("//input[@id='searchBtn']")).click();
		Assert.assertTrue(isPageLoadedSuccess());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+nameInput+"']")).isDisplayed());
	}

	@Test
	public void TC_02_TestProject() {
		driver.get("https://blog.testproject.io/");
		String keyword = "Selenium";
		if(driver.findElement(By.cssSelector("div.mailch-wrap")).isDisplayed()) {
			System.out.println("close popup");
			driver.findElement(By.cssSelector("div#close-mailch")).click();
		}
		// Hover mouse to search textbox
		action.moveToElement(driver.findElement(By.cssSelector("section#search-2 input.search-field"))).perform();
		System.out.println("wait cho page ready");
		Assert.assertTrue(isPageLoadedSuccess());

		System.out.println("enter value to textbovx");
		driver.findElement(By.cssSelector("section#search-2 input.search-field")).sendKeys(keyword);
		driver.findElement(By.cssSelector("section#search-2 span.glass")).click();
		driver.findElement(By.cssSelector("section#primary h2 span")).isDisplayed();
		
		Assert.assertTrue(isPageLoadedSuccess());
		List<WebElement> postTitle = driver.findElements(By.cssSelector("h3.post-title>a"));
		for (WebElement title : postTitle) {
			String postTitleText = title.getText();
			System.out.println(postTitleText);
			Assert.assertTrue(postTitleText.contains(keyword));
		}

	}

	@Test
	public void TC_03() {
	}

	public boolean isPageLoadedSuccess() {
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);

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