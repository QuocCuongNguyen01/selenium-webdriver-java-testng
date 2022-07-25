package webdriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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

public class Topic_24_ExplicitWait {

	WebDriver driver;
	String osName = System.getProperty("os.name");
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;

	// image name
	String vietnam = "VietNam.jpeg";
	String thailan = "ThaiLan.jpeg";
	String indonesia = "Indonesia.jpeg";

	// Upload file folder
	String uploadFileFolder = projectPath + File.separator + "/UploadFile/" + File.separator;
	// image path
	String vietnamFilePath = uploadFileFolder + vietnam;
	String thailanFilePath = uploadFileFolder + thailan;
	String indonesiaFilePath = uploadFileFolder + indonesia;
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

		// System.setProperty("webdriver.chrome.driver", projectPath +
		// "/browserDriver/Chromedriver");
		// driver = new ChromeDriver();
		// Hàm này áp dụng việc tìm element(findElement/ findElements)
		
		// Phóng to browser lên
		driver.manage().window().maximize();
		// Mở app lên

	}

	//@Test
	public void TC_01_Visible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		//click button start
		explicitWait = new WebDriverWait(driver, 5);
		driver.findElement(By.cssSelector("div#start>button")).click();
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish")));
		//Get text and verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");

	}

	//@Test
	public void TC_02_Invisible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		//click button start
		explicitWait = new WebDriverWait(driver, 5);

		driver.findElement(By.cssSelector("div#start>button")).click();
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));

		//Get text and verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");

	}
	//@Test
	public void TC_03_Ajax_Loading() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		explicitWait = new WebDriverWait(driver, 15);
		//Verify chưa có ngày nào được chọn
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(), "No Selected Dates to display.");
		//Wait cho date picker được hiển thị
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.RadCalendar")));
		//Wait cho 19 được phép click
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='19']")));
		driver.findElement(By.xpath("//a[text()='19']")).click();
		
		//Wati cho loading kêt thúc
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1']>div.raDiv")));
		//Wait cho ngày vừa được chọn là có thể click được lại
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='rcSelected']/a[text()='19']")));
		
		//Verìy ngày đã được chọn
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(),"Tuesday, July 19, 2022");
		
	}
	@Test
	public void TC_04_Upload_File() {
		driver.get("https://gofile.io/?t=uploadFiles");
		explicitWait = new WebDriverWait(driver, 20);

		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div#rowUploadButton button.uploadButton")));
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(vietnamFilePath + "\n" + thailanFilePath + "\n" + indonesiaFilePath);
	//	driver.findElement(By.cssSelector("button.uploadButton")).click();
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.overflow-auto div.progress-bar"))));
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Your files have been successfully uploaded']"))).isDisplayed());
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#rowUploadSuccess-showFiles")));
		driver.findElement(By.cssSelector("button#rowUploadSuccess-showFiles")).click();
		//
	
		Assert.assertTrue(	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+ indonesia +"']//parent::a//parent::div/following-sibling::div//button[@id='contentId-download']"))).isDisplayed());
		Assert.assertTrue(	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+ thailan +"']//parent::a//parent::div/following-sibling::div//button[@id='contentId-download']"))).isDisplayed());
		Assert.assertTrue(	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+ vietnam +"']//parent::a//parent::div/following-sibling::div//button[@id='contentId-download']"))).isDisplayed());
		

		Assert.assertTrue(	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+ indonesia +"']//parent::a//parent::div/following-sibling::div//button[contains(@class,'contentPlay')]"))).isDisplayed());
		Assert.assertTrue(	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+ thailan +"']//parent::a//parent::div/following-sibling::div//button[contains(@class,'contentPlay')]"))).isDisplayed());
		Assert.assertTrue(	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+ vietnam +"']//parent::a//parent::div/following-sibling::div//button[contains(@class,'contentPlay')]"))).isDisplayed());
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