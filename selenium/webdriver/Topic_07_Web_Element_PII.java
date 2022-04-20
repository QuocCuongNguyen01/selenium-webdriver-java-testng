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

	//@Test
	public void TC_01_isDisplayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement emailTextbox = driver.findElement(By.id("mail"));
		if (emailTextbox.isDisplayed()) {
			emailTextbox.sendKeys("Automation Testing");
			System.out.println("Email Textbox is displayed");
		} else {
			System.out.println("Email Textbox is not  displayed");
		}
		
		WebElement ageUnder18Radio = driver.findElement(By.id("under_18"));
		if (ageUnder18Radio.isDisplayed()) {
			ageUnder18Radio.click();
			System.out.println("Age under 18 Radio is displayed");
		} else {
			System.out.println("Age under 18 Radio is not  displayed");
		}
		
		WebElement educationTextarea = driver.findElement(By.id("edu"));
		if (educationTextarea.isDisplayed()) {
			educationTextarea.sendKeys("Automation Testing");
			System.out.println("Education Textarea is displayed");
		} else {
			System.out.println("Education Textarea is not  displayed");
		}
		
		WebElement name5Text = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
		if (name5Text.isDisplayed()) {
			System.out.println("Name 5 Text is displayed");
		} else {
			System.out.println("Name 5 Text is not  displayed");
		}
		
		
	}
	//@Test
	public void TC_02_Enabled() {
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Enabled
		WebElement emailTextbox = driver.findElement(By.id("mail"));
		if (emailTextbox.isEnabled()) {
			System.out.println("Email Textbox is Enabled");
		} else {
			System.out.println("Email Textbox is not Enabled");
		}
		Assert.assertTrue(emailTextbox.isEnabled());
		
		WebElement ageUnder18Radio = driver.findElement(By.id("under_18"));
		if (ageUnder18Radio.isEnabled()) {
			System.out.println("Age under 18 Radio is Enabled");
		} else {
			System.out.println("Age under 18 Radio is Disabled");
		}
		Assert.assertTrue(ageUnder18Radio.isEnabled());
		
		WebElement educationTextarea = driver.findElement(By.id("edu"));
		if (educationTextarea.isEnabled()) {	
			System.out.println("Education Textarea is Enabled");
		} else {
			System.out.println("Education Textarea is Disabled");
		}
		Assert.assertTrue(educationTextarea.isEnabled());
		
		WebElement jobRole1Dropdown = driver.findElement(By.cssSelector("select#job1"));
		if (jobRole1Dropdown.isEnabled()) {	
			System.out.println("Job Rule 1 is Enabled");
		} else {
			System.out.println("Job Rule 1 is Disabled");
		}
		Assert.assertTrue(jobRole1Dropdown.isEnabled());
		
		WebElement jobRole1DropdownMultiple = driver.findElement(By.cssSelector("select#job2"));
		if (jobRole1DropdownMultiple.isEnabled()) {	
			System.out.println("Job Rule 2 is Enabled");
		} else {
			System.out.println("Job Rule 2 is Disabledd");
		}
		Assert.assertTrue(jobRole1DropdownMultiple.isEnabled());
		
		WebElement developmentCheckbox = driver.findElement(By.cssSelector("input#development"));
		if (developmentCheckbox.isEnabled()) {	
			System.out.println("Development Checkbox is Enabled");
		} else {
			System.out.println("Development Checkbox is Disabled");
		}
		Assert.assertTrue(developmentCheckbox.isEnabled());
		
		WebElement slider1 = driver.findElement(By.cssSelector("input#slider-1"));
		if (slider1.isEnabled()) {	
			System.out.println("Slider-1 is Enabled");
		} else {
			System.out.println("Slider-1 is Disabled");
		}
		Assert.assertTrue(slider1.isEnabled());
		
		//Disabled
		WebElement passWordTexbox = driver.findElement(By.cssSelector("input#password"));
		if (passWordTexbox.isEnabled()) {	
			System.out.println("Password Textbox is Enabled");
		} else {
			System.out.println("Password Textbox is Disabled");
		}
		Assert.assertTrue(passWordTexbox.isEnabled());
		
		WebElement ageRadioDisabled = driver.findElement(By.cssSelector("input#radio-disabled"));
		if (ageRadioDisabled.isEnabled()) {	
			System.out.println("Age Radio Disabled is Enabled");
		} else {
			System.out.println("Age Radio Disabled is Disabled");
		}
		Assert.assertFalse(ageRadioDisabled.isEnabled());
		
		WebElement bioTextbox = driver.findElement(By.cssSelector("textarea#bio"));
		if (bioTextbox.isEnabled()) {	
			System.out.println("Bio extbox is Enabled");
		} else {
			System.out.println("Bio extbox is Disabled");
		}
		Assert.assertFalse(bioTextbox.isEnabled());
		
		WebElement job3Textbox = driver.findElement(By.cssSelector("select#job3"));
		if (job3Textbox.isEnabled()) {	
			System.out.println("Job3 Textbox is Enabled");
		} else {
			System.out.println("Job3 Textbox is Disabled");
		}
		Assert.assertFalse(job3Textbox.isEnabled());
		
		WebElement interestsCheckboxDisable = driver.findElement(By.cssSelector("input#check-disbaled"));
		if (interestsCheckboxDisable.isEnabled()) {	
			System.out.println("interests Checkbox Disabled is Enabled");
		} else {
			System.out.println("interests Checkbox Disabled is Disabled");
		}
		Assert.assertFalse(interestsCheckboxDisable.isEnabled());
		
		WebElement slider2 = driver.findElement(By.cssSelector("input#slider-2"));
		if (slider2.isEnabled()) {	
			System.out.println("Slider-2 is Enabled");
		} else {
			System.out.println("Slider-2 is Disabled");
		}
		Assert.assertFalse(slider2.isEnabled());
		
	}
	//@Test
	public void TC_03_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		WebElement ageUnder18Radio = driver.findElement(By.id("under_18"));
		WebElement javaCheckbox = driver.findElement(By.id("java"));
		ageUnder18Radio.click();
		javaCheckbox.click();
		
		//Verify selected
		
		Assert.assertTrue(ageUnder18Radio.isSelected());
		Assert.assertTrue(javaCheckbox.isSelected());
		if (ageUnder18Radio.isSelected()) {
			System.out.println("Age under 18 is Selected");
		} else {
			System.out.println("Age under 18 is deselected");
		}
		if (javaCheckbox.isSelected()) {
			System.out.println("Java checkbox is Selected");
		} else {
			System.out.println("Java checkbox is deselected");
		}
		
		ageUnder18Radio.click();
		javaCheckbox.click();
		
		Assert.assertTrue(ageUnder18Radio.isSelected());
		Assert.assertFalse(javaCheckbox.isSelected());
		if (ageUnder18Radio.isSelected()) {
			System.out.println("Age under 18 is Selected");
		} else {
			System.out.println("Age under 18 is deselected");
		}
		if (javaCheckbox.isSelected()) {
			System.out.println("Java checkbox is Selected");
		} else {
			System.out.println("Java checkbox is deselected");
		}
	}
	@Test
	public void TC_04_MailChimp() {
		driver.get("https://login.mailchimp.com/signup/");
		driver.findElement(By.cssSelector("input#email")).sendKeys("cuongdnqb5@gmail.com");
		driver.findElement(By.cssSelector("input#new_username")).sendKeys("cuongdnqb5");
		WebElement passwordTextbox = driver.findElement(By.cssSelector("input#new_password"));
		WebElement signupButton = driver.findElement(By.cssSelector("button#create-account"));
		//lowercase
		passwordTextbox.sendKeys("cuong");
		sleepInsecond(5);
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed' and  text()='One lowercase character']")).isDisplayed());
		Assert.assertFalse(signupButton.isEnabled());
		//Uppercase
		passwordTextbox.clear();
		passwordTextbox.sendKeys("CUONG");
		sleepInsecond(5);
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed' and  text()='One uppercase character']")).isDisplayed());
		Assert.assertFalse(signupButton.isEnabled());
		//Number
		passwordTextbox.clear();
		passwordTextbox.sendKeys("12345");
		sleepInsecond(5);
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed' and  text()='One number']")).isDisplayed());
		Assert.assertFalse(signupButton.isEnabled());
		//Special
		passwordTextbox.clear();
		passwordTextbox.sendKeys("!@#");
		sleepInsecond(5);
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed' and  text()='One special character']")).isDisplayed());
		Assert.assertFalse(signupButton.isEnabled());
		//Special
		passwordTextbox.clear();
		passwordTextbox.sendKeys("12345678");
		sleepInsecond(5);
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']")).isDisplayed());
		Assert.assertFalse(signupButton.isEnabled());
		//Combine
		passwordTextbox.clear();
		passwordTextbox.sendKeys("Cuong040996!@#");
		driver.findElement(By.cssSelector("input#marketing_newsletter")).click();
		sleepInsecond(5);
		
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()=\"Your password is secure and you're all set!\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("input#marketing_newsletter")).isSelected());
		
		Assert.assertTrue(signupButton.isEnabled());
					
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public void sleepInsecond(long timeInsecond) {
		try {
			Thread.sleep(timeInsecond * 1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
}