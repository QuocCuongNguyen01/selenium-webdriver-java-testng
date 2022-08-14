package webdriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_26_FluentWait {

	WebDriver driver;
	String osName = System.getProperty("os.name");
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExcutor;
	FluentWait<WebDriver> fluentDriver;
	FluentWait<WebElement> fluentElement;
	long alltime = 15;
	long pollingTime = 100;

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
	}

	public void TC_01_FluentWait() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		findElement("//button[text()='Start']").click();
		findElement("//div[@id='finish']/h4").isDisplayed();
		Assert.assertEquals(findElement("//div[@id='finish']/h4").getText(), "Hello World!");
	}
	

	@Test
	public void TC_02() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		WebElement countdountTime  = findElement("//div[@id='javascript_countdown_time']");
		fluentElement = new FluentWait<WebElement>(countdountTime);
		fluentElement.withTimeout(Duration.ofSeconds(alltime))
		.pollingEvery(Duration.ofMillis(pollingTime))
		.ignoring(NoSuchElementException.class);
		
		fluentElement.until(new Function<WebElement, Boolean>() {

			@Override
			public Boolean apply(WebElement element) {
				String text = element.getText();
				System.out.println("Thời gian:"+text);
				return text.endsWith("00");
			}
		});
	}

	@Test
	public void TC_03() {
	}	
	public WebElement findElement(String xpathLocator) {
		fluentDriver = new FluentWait<WebDriver>(driver);
		//Xét tổng thời gian và tần số
		fluentDriver.withTimeout(Duration.ofSeconds(alltime))
	//	fluentDriver.withTimeout(15, TimeUnit.SECONDS);
		//1/3s check 1 lần
		.pollingEvery(Duration.ofMillis(pollingTime))
		.ignoring(NoSuchElementException.class);
			return fluentDriver.until(new Function<WebDriver, WebElement>() {

			@Override
			public WebElement apply(WebDriver driver) {
				// TODO Auto-generated method stub
				return driver.findElement(By.xpath(xpathLocator));
			}
		});
		
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