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

public class Topic_10_Handle_Custom_Dropdown_HW {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExcuter;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		
		// Mở browser lên
		//System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
		//driver = new FirefoxDriver();
		
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDriver/Chromedriver");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 15);
		// Hàm này áp dụng việc tìm element(findElement/ findElements)
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Ép kiểu tường minh trong java
		jsExcuter = (JavascriptExecutor) driver;

	
	
	}

	//@Test
	public void TC_01_JQuery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		//"ul#number-menu li div"
		//ul#number-menu li div
		selectItemCustomDropdown("span#number-button", "ul#number-menu>li>div", "10");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "10");
		selectItemCustomDropdown("span#number-button", "ul#number-menu>li>div", "19");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "19");
			
	}
	//@Test
	public void TC_02_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		//"ul#number-menu li div"
		//ul#number-menu li div
		selectItemCustomDropdown("i.dropdown", "div>span.text", "Jenny Hess");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Jenny Hess");
		selectItemCustomDropdown("i.dropdown", "div>span.text", "Justen Kitsune");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Justen Kitsune");
	}
	//@Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItemCustomDropdown("li.dropdown-toggle", "ul>li>a", "First Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");
		selectItemCustomDropdown("li.dropdown-toggle", "ul>li>a", "Second Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");
		selectItemCustomDropdown("li.dropdown-toggle", "ul>li>a", "Third Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");
	}
	//@Test
	public void TC_04_NoComerce() {
		driver.get("https://demo.nopcommerce.com/register");
		selectItemCustomDropdown("select[name='DateOfBirthDay']", "select[name='DateOfBirthDay']>option", "10");
		Assert.assertTrue(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']/option[text()='10']")).isSelected());
		
		selectItemCustomDropdown("select[name='DateOfBirthDay']", "select[name='DateOfBirthDay']>option", "15");
		Assert.assertTrue(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']/option[text()='15']")).isSelected());
		
		
	}
	@Test
	public void TC_05() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		enterItemCustomDropdown("input.search", "div>span.text", "Afghanistan");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(), "Afghanistan");
		enterItemCustomDropdown("input.search", "div>span.text", "Aland Islands");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(), "Aland Islands");
		enterItemCustomDropdown("input.search", "div>span.text", "Bahrain");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(), "Bahrain");
	}
	public void selectItemCustomDropdown (String parentLocator, String childLocator, String expectedItem) {
		
		driver.findElement(By.cssSelector(parentLocator)).click();
		sleepInsecond(0);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
		
		List<WebElement> allDropdownItems = driver.findElements(By.cssSelector(childLocator));
		for (WebElement item : allDropdownItems) {
			String actualTextItem = item.getText();
			System.out.println("Item text = "+ actualTextItem);
			if (actualTextItem.equals(expectedItem)) {
				jsExcuter.executeScript("arguments[0].scrollIntoView(true);", item);
				
				item.click();
				sleepInsecond(1);
				break;
			}
		}
	}
	public void enterItemCustomDropdown (String editableLocator, String childLocator, String expectedItem) {
		
		driver.findElement(By.cssSelector(editableLocator)).sendKeys(expectedItem);
		sleepInsecond(1);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
		
		List<WebElement> allDropdownItems = driver.findElements(By.cssSelector(childLocator));
		for (WebElement item : allDropdownItems) {
			String actualTextItem = item.getText();
			System.out.println("Item text = "+ actualTextItem);
			if (actualTextItem.equals(expectedItem)) {
				jsExcuter.executeScript("arguments[0].scrollIntoView(true);", item);
				
				item.click();
				sleepInsecond(1);
				break;
			}
		}
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