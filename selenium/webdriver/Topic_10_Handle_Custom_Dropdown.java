package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Handle_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		// Mở browser lên
				System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
				driver = new FirefoxDriver();
				
				// Luôn khởi tạo sau driver = > cần giá trị driver để khởi tạo explicitwait lên
				// Wait cho các element theo điều kiện có sẵn :	visible/ invisible/ presence/ clickable/ ....
				explicitWait = new WebDriverWait(driver, 15);
				//System.setProperty("webdriver.chrome.driver", projectPath + "/browserDriver/Chromedriver");
				//driver = new ChromeDriver();
				// Hàm này áp dụng việc tìm element(findElement/ findElements)
				
				
				// Wait cho việc tìm element. Findelement(s)
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				driver.manage().window().maximize();
	
	}

	@Test
		public void TC_01_JQuery() {
		//"ul#number-menu li div"
		//ul#number-menu li div
		selectItemCustomDropdown("ul#number-menu li div", "ul#number-menu li div", "10");
		selectItemCustomDropdown("ul#number-menu li div", "ul#number-menu li div", "5");
			
		
	}
	public void selectItemCustomDropdown (String parentLocator, String childLocator, String expectedItem) {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		//	- Click vào dropdown cho xổ hết tất cả các item con bên trong ra => Click
				
		driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).click();
		// - Chờ cho tất cả các item con bên trong được load ra => WebDriverWait
	    // By Locator = đại diện cho " các item con bên trong" được load ra 
		// lấy locator đến thẻ chưa text item	 
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(parentLocator)));
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