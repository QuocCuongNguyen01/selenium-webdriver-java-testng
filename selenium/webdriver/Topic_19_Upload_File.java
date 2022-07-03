package webdriver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
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

public class Topic_19_Upload_File {

	WebDriver driver;
	String osName = System.getProperty("os.name");
	String projectPath = System.getProperty("user.dir");

	
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
	List<String> filenames = getFileNameInFolder();;
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// Phóng to browser lên
		driver.manage().window().maximize();
		// Mở app lên

	}

	@Test
	public void TC_01_Upload_One_File_Per_Time() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		for (String filename : filenames) {
			driver.findElement(By.cssSelector("input[type='file']")).sendKeys(uploadFileFolder + filename);
			Assert.assertTrue(
					driver.findElement(By.xpath("//p[@class='name' and text()='" + filename + "']")).isDisplayed());
		}
	}

	@Test
	public void TC_02_Upload_Multiple_File_Per_Time() {

	}

	@Test
	public void TC_03() {
	}

	public List<String> getFileNameInFolder() {
		File directoryPath = new File(uploadFileFolder);
		// List of all files and directories
		String contents[] = directoryPath.list();
		List<String> filenames = new ArrayList<String>();
		for (int i = 0; i < contents.length; i++) {
			filenames.add(contents[i]);
		}
		return filenames;
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
		// driver.quit();
	}
}