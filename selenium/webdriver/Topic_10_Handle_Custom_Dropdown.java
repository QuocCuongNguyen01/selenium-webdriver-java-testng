package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Handle_Custom_Dropdown {
	WebDriver driver;
	Select select;
	String tinh, khuvuc;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		// Mở browser lên
				//System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
				//	driver = new FirefoxDriver();
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDriver/Chromedriver");
			driver = new ChromeDriver();
				// Luôn khởi tạo sau driver = > cần giá trị driver để khởi tạo explicitwait lên
				// Wait cho các element theo điều kiện có sẵn :	visible/ invisible/ presence/ clickable/ ....
				explicitWait = new WebDriverWait(driver, 15);
				//System.setProperty("webdriver.chrome.driver", projectPath + "/browserDriver/Chromedriver");
				//driver = new ChromeDriver();
				// Hàm này áp dụng việc tìm element(findElement/ findElements)
				
				
				// Wait cho việc tìm element. Findelement(s)
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.manage().window().maximize();
				tinh = "TP. Hồ Chí Minh";
				khuvuc = "Khu vực I";
	
	}

	@Test
		public void TC_01_JQuery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		//"ul#number-menu li div"
		//ul#number-menu li div
		selectItemCustomDropdown("span#number-button span.ui-selectmenu-text", "ul#number-menu li div", "10");
		selectItemCustomDropdown("span#number-button span.ui-selectmenu-text", "ul#number-menu li div", "5");
			
		
	}
	@Test
	public void TC_02_JQuery2() {
		driver.get("https://www.honda.com.vn/o-to/du-toan-chi-phi");
		driver.findElement(By.xpath("//button[@class='btn btn-primary x']")).click();
		selectItemCustomDropdown("button.btn", "div.dropdown-menu a", "CITY L");
		
		select = new Select(driver.findElement(By.name("province")));
		select.selectByVisibleText(tinh);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), tinh);
		select = new Select(driver.findElement(By.name("registration_fee")));
		select.selectByVisibleText(khuvuc);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), khuvuc);
		
	}
	@Test
	public void TC_03_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectItemCustomDropdown("div.divider","div[role='option']", "Jenny Hess" );
		
	}
	@Test
	public void TC_04_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItemCustomDropdown("div.btn-group","li a", "Second Option" );
		
	}

	

		// - Item này sẽ đổ dữ liệu vào dropdown này => Verify chọn thành công
	
	public void selectItemCustomDropdown (String parentLocator, String childLocator, String expectedItem) {
		
		//	- Click vào dropdown cho xổ hết tất cả các item con bên trong ra => Click
				
		driver.findElement(By.cssSelector(parentLocator)).click();
		// - Chờ cho tất cả các item con bên trong được load ra => WebDriverWait
	    // By Locator = đại diện cho " các item con bên trong" được load ra 
		// lấy locator đến thẻ chưa text item	 
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
		// - Tìm item mong muốn (nếu như ko hiển thị thì cần cuộn chuột xuống để tìm) =>
		// Vòng lặp để lấy hết tất cả item rồi duyệt qua - getText từng cái
		
		
		// Khai báo từng Element thì nhiều / mất thời gian / code nhiều 
		// Lấy tất cả các item ra lưu vào 1 list WebElement 
		List<WebElement> allDropdownItems = driver.findElements(By.cssSelector(childLocator));
		//19 element trong này 
		// Duyệt qua từng item
		
		//Duyệt qua gọn: vòng lặp
		for (WebElement item : allDropdownItems) {
			String actualTextItem = item.getText();
			System.out.println("Item text = "+ actualTextItem);
			// - Thấy item cần chọn thì click vào => So sánh vs item mong muốn sau đó Click vào
			if (actualTextItem.equals(expectedItem)) {
				item.click();
				// Thoát ra khỏi vòng lặp
				break;
			}
		}
		
		// - Item này sẽ đổ dữ liệu vào dropdown này => Verify chọn thành công
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
		//driver.quit();
	}
}