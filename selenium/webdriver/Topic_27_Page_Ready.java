package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class Topic_27_Page_Ready {

	WebDriver driver;
	String osName = System.getProperty("os.name");
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

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
	}

	@Test
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
	public void TC_02() {

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