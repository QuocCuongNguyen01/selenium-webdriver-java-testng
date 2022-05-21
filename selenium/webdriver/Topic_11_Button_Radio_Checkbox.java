package webdriver;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Button_Radio_Checkbox {
	
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
			// Phóng to browser lên
			driver.manage().window().maximize();
			
			
		
		
		}
	
		//@Test
		public void TC_01_Button() {
			driver.get("https://www.fahasa.com/customer/account/create");
			sleepInsecond(5);
			
			driver.findElement(By.cssSelector("ul>li.popup-login-tab-item")).click();
			
			By logginButton = By.cssSelector("button.fhs-btn-login");
			// Verify loggin button is disable
			Assert.assertFalse(driver.findElement(logginButton).isEnabled());
			
			driver.findElement(By.cssSelector("input#login_username")).sendKeys("cuongdnqb5@gmail.com");
			driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");
			sleepInsecond(2);
			
			//Verify loggin button is Enabled
			Assert.assertTrue(driver.findElement(logginButton).isEnabled());
			
			//Verify background color is RED
			String loginButtonBackgroundHexa = Color.fromString(driver.findElement(logginButton).getCssValue("background-color")).asHex().toUpperCase();
			System.out.println(loginButtonBackgroundHexa);
			
			//String loginButtonBackgroundHexa = Color.fromString(loginButtonBackgroundRGBA).asHex().toUpperCase();
			Assert.assertEquals(loginButtonBackgroundHexa, "#C92127");
			
			
			
			
			
			
		}
	//	@Test
		public void TC_02_Default_Radio_Checkbox() {
			driver.get("https://automationfc.github.io/multiple-fields/");
			
			By Anemia = By.xpath("//input[@value='Anemia']");
			By Cancer =  By.xpath("//input[@value='Cancer']");
			By Hepatitis = By.xpath("//input[@value='Hepatitis']");
			By day = By.xpath("//input[@value='3-4 cups/day']");
			By strict = By.xpath("//input[@value='I have a strict diet']");
			By days = By.xpath("//input[@value='5+ days']");
			
			//Checkbox
			driver.findElement(Anemia).click();
			driver.findElement(Cancer).click();
			driver.findElement(Hepatitis).click();
			sleepInsecond(2);			
			
			
			driver.findElement(By.xpath("//input[@value='3-4 cups/day']")).click();
			driver.findElement(By.xpath("//input[@value='I have a strict diet']")).click();
			driver.findElement(By.xpath("//input[@value='5+ days']")).click();
			sleepInsecond(2);	
			
		//verify
			Assert.assertTrue(driver.findElement(Anemia).isSelected());
			Assert.assertTrue(driver.findElement(Cancer).isSelected());
			Assert.assertTrue(driver.findElement(Hepatitis).isSelected());
			Assert.assertTrue(driver.findElement(day).isSelected());
			Assert.assertTrue(driver.findElement(strict).isSelected());
			Assert.assertTrue(driver.findElement(days).isSelected());
			//Bỏ chọn
			driver.findElement(Anemia).click();
			driver.findElement(Cancer).click();
			driver.findElement(Hepatitis).click();
			sleepInsecond(2);			
			
			
			driver.findElement(day).click();
			driver.findElement(strict).click();
			driver.findElement(days).click();
			sleepInsecond(2);	
			
			Assert.assertFalse(driver.findElement(Anemia).isSelected());
			Assert.assertFalse(driver.findElement(Cancer).isSelected());
			Assert.assertFalse(driver.findElement(Hepatitis).isSelected());
			
			Assert.assertTrue(driver.findElement(day).isSelected());
			Assert.assertTrue(driver.findElement(strict).isSelected());
			Assert.assertTrue(driver.findElement(days).isSelected());
		}
		@Test
		public void TC_03_SelectAllCheckbox() {
			driver.get("https://automationfc.github.io/multiple-fields/");
			
			
			//Select All Checkbox
			List<WebElement> allCheckbox = driver.findElements(By.cssSelector("input.form-checkbox"));
			for (WebElement checkbox : allCheckbox) {
				if (!checkbox.isSelected()) {
					checkbox.click();
					sleepInsecond(1);
				}
			}
			//Verify all Checkbox are Selected 
			for (WebElement checkbox : allCheckbox) {
				Assert.assertTrue(checkbox.isSelected());
				
			}
			//Deselected all
			for (WebElement checkbox : allCheckbox) {
				if (checkbox.isSelected()) {
					checkbox.click();
					sleepInsecond(1);
				}
			}
			//Verify all checkbox are
			for (WebElement checkbox : allCheckbox) {
				Assert.assertFalse(checkbox.isSelected());
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
