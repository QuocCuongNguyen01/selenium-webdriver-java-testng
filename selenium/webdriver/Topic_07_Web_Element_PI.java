package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Web_Element_PI {
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
	
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
	
	}

	@Test
	public void TC_01_Element() {
		//Muốn thao tác với Element thì đầu tiên mình phải tìm ra Element trước
		//Sau đó mới áp dụng hành vi và thao tác Element đó
		//1 - Tìm Element
		//2 - Với loại locator nào
		//3 - Tương tác lên/ Kiểm tra nó
		//Muốn thao tác trực tiếp lên Element thì không cần khai báo biến
		driver.findElement(By.id(""));
		
		//Thao tác từ 2 lần trở lên thì cần khai báo biến tránh việc lặp lại
		WebElement element = driver.findElement(By.id("email"));
		element.clear();//***********
		element.sendKeys("cuongdnqb5@gmail.com");//***********
		element.sendKeys(Keys.ENTER);//***********
		element.isDisplayed();	
		
		//Element cha: findElement(By.Classname("footer"));
		//Element con: findElement(By.cssSelector("a[title='My Account']"));
		driver.findElement(By.cssSelector("//div[@class='footer']//a[@title='My Acount']"));
			
		//Không khai báo biến = verify trực tiếp
		Assert.assertEquals(driver.findElement(By.id("search")).getAttribute("placeholder"), "Search entire store here...");
		
		//Verify 1 step: không cần khai báo biến
		//Khai báo biến để dùng nhiều lần	
		
		String searchTextboxPlaceholderValue = driver.findElement(By.id("search")).getAttribute("placeholder");
		Assert.assertEquals(searchTextboxPlaceholderValue, "Search entire store here...");
		
		//GUI: Font/size/Color/Pixel/..
		element.getCssValue("background-color");//***********
		//rgb(51, 153, 204)
		element.getCssValue("font");
		//13px
		element.getLocation();
		element.getRect();
		element.getSize();
		
		//Framework: Attack screenshot to report HTML
		element.getScreenshotAs(OutputType.FILE);//***********	
		
		element.findElement(By.xpath("//div[@id='advice-validate-email-email']"));
		element.findElement(By.cssSelector("div[id='advice-validate-email-email'])"));
		element.findElement(By.cssSelector("#advice-validate-email-email"));
		
		String emailTextboxTagname = element.getTagName();
		
		//Output của element này sẽ là output của element khác
		//Truyền 1 biến vào trong 1 chuỗi: cắt chuỗi ra làm 2 và cộng biến ở giữa
		
		element.findElement(By.xpath("//" + emailTextboxTagname + "[@id='advice-validate-password']"));
		//Chuỗi 1 + biến + chuỗi 2
		
		//Lấy ra text của element hiện tại/ text của element con
		element.getText();//***********
		
		//Mong muốn một element hiển thị/ Không hiển thị
		//Hiển thị: người dùng nhìn thấy được/ có kích thước cụ thể (Chiều rộng/chiều cao)
		//Áp dụng cho tất cả các loại element: Textbox/ Textarea/ Dropdown/ checkbox/ radio/ button..... 
		element.isDisplayed();//***********
		
		//Mong muốn một element có thể tao thác được lên hoặc không
		//Ngược lại với Disable
		//Thao tác được: Enabled
		//Không thao tác được : Disable
		//Áp dụng cho tất cả các loại element: Textbox/ Textarea/ Dropdown/ checkbox/ radio/ button..... 

		element.isEnabled();//***********
		
		//Mong muốn 1 element đã được chọn hay chưa
		//Áp dụng cho một vài loại element: Checkbox/ RadioButton/ Dropdown(Default)
		element.isSelected();//***********
		
		//Click vào 1 element
		element.click();//***********
		
		//Giống hành vi ENTER lên 1 form
		//Chỉ dùng cho tagname: Form
		element.submit()
;		
		//Click lên slider và giữ chuột
		//Kéo slider lên 1 tọa độ bao nhiêu pixel 
		//kéo tới 1 element khác
		
		//action.clickAndHold(element).moveToElement(element).perform();
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}