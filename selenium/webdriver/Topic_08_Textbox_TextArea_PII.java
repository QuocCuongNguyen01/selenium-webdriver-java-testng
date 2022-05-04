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

public class Topic_08_Textbox_TextArea_PII {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String EmployeeID, firstName, lastName;
	String firstName2, lastName2;

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
		firstName = "Cuong";
		lastName= "Nguyen";
		firstName2= "Tuan";
		lastName2= "Tran";
		
	}

	@Test
	public void TC_01() {
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.findElement(By.id("txtUsername")).sendKeys("admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();
		//driver.findElement(By.xpath("//a[@id='menu_pim_viewPimModule']/b"));
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/pim/addEmployee");
		driver.findElement(By.id("firstName")).sendKeys(firstName);
		driver.findElement(By.id("lastName")).sendKeys(lastName);
		EmployeeID = driver.findElement(By.id("employeeId")).getAttribute("value");
		driver.findElement(By.id("btnSave")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//input[@title='First Name']")).getAttribute("value"),firstName);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@title='Last Name']")).getAttribute("value"),lastName);
		Assert.assertEquals(driver.findElement(By.id("personal_txtEmployeeId")).getAttribute("value"),EmployeeID);
		
		driver.findElement(By.id("btnSave")).click();
		driver.findElement(By.id("personal_txtEmpFirstName")).clear();
		driver.findElement(By.id("personal_txtEmpFirstName")).sendKeys(firstName2);
		driver.findElement(By.id("personal_txtEmpLastName")).clear();
		driver.findElement(By.id("personal_txtEmpLastName")).sendKeys(lastName2);
		driver.findElement(By.id("personal_txtEmployeeId")).isEnabled();
		driver.findElement(By.id("personal_txtEmpLastName")).isEnabled();
		driver.findElement(By.id("btnSave")).click();
		Assert.assertEquals(driver.findElement(By.id("personal_txtEmpFirstName")).getAttribute("value"),firstName2);
		Assert.assertEquals(driver.findElement(By.id("personal_txtEmpLastName")).getAttribute("value"),lastName2);
		driver.findElement(By.id("personal_txtEmployeeId")).isDisplayed();
		driver.findElement(By.id("personal_txtEmpLastName")).isDisplayed();
		driver.findElement(By.id("personal_txtEmployeeId")).isDisplayed();
		
	}
	@Test
	public void TC_02() {
		
	}
	@Test
	public void TC_03() {
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}