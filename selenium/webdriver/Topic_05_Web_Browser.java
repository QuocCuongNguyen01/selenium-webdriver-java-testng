package webdriver;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		// Mở browser lên
		//System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
		//driver = new FirefoxDriver();
		
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDriver/Chromedriver");
		driver = new ChromeDriver();
		
	
	}

	@Test
	public void TC_01_Method() {
		
		
		//Browser
		
		//Element
		WebElement element = driver.findElement(By.className(""));
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		//Để close 1 browser/tab
		//Nếu như chỉ có 1 tab: Close browser
		//Nếu như có nhiều hơn 1 tab: Close browser đang active
		driver.close();
		
		//Không quan tâm bao nhiêu tab 
		driver.quit();//*********
		
		//Mở 1 URL
		driver.get("https://www.facebook.com.vn/"); //********* 
		//****************** Wait ************************
		//Để tìm 1 element
		driver.findElement(By.xpath("")); //*********
		//Để tìm nhiều element
		driver.findElements(By.xpath("")); //*********
		//Lấy ra URL của page hiện tại
		driver.getCurrentUrl();
		//Lấy ra source code(HTML/CSS/JS) của page hiện tại
		driver.getPageSource();
		//Lấy ra title của page hiện tại
		driver.getTitle();
		//******************Window/Tab************************
		//Dùng để xử lý window/tab
		//Lấy ra ID của window/tab đang active
		driver.getWindowHandle(); //*********
		
		//Lấy ra ID của tất cả các window/tab đang có
		driver.getWindowHandles(); //*********
		
		//****************** Framework- Cookie ************************
		//Cookie: Lưu lại phiên đăng nhập/Session/Dữ liệu duyệt web/...
		driver.manage().deleteAllCookies();
		//****************** Framework- Log ************************
		//driver.manage().logs().get(log);
		
		//Chờ cho findElement/findElements
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);//********* 
		
		//Chờ cho 1 page load thành công
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.MINUTES);
		
		//****************** Javascript Executor **********************
		//Chờ cho 1 đoạn Javascript được thực thi thành công
		//asynchronus script: Bất đồng bộ
		//synchronus script:  đồng bộ
		
		
		driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
		
		driver.manage().window().fullscreen();
		driver.manage().window().maximize(); //*********
		//Set vị trí của browser so với độ phân giải của màn hình (Resolution )
		driver.manage().window().setPosition(new Point(100,250));
		driver.manage().window().getPosition();
		//Mở browser với kích thước là bao nhiêu
		//Test responsive
		driver.manage().window().setSize(new Dimension(100, 200));
		driver.manage().window().getSize();
		
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();
		driver.navigate().to("https://www.facebook.com.vn/");
		driver.get("https://www.facebook.com.vn/");
		
		//************************ Alert **********************
		driver.switchTo().alert();//*********
		
		//************************ Frame/iframe *****************
		driver.switchTo().frame(0);//*********
		
		//*********************** Window/tab ********************
		driver.switchTo().window(""); //*********
		
		
		
		
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