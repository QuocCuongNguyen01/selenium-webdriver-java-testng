package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_04_Xpath_Part_II {
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	//public class bien{
		//WebElement textName = driver.findElement(By.xpath("//input[@id='txtFirstname']"));
		
	//	WebElement textEmail = driver.findElement(By.xpath("//input[@id='txtEmail']"));
	//	WebElement textCEmail = driver.findElement(By.xpath("//input[@id='txtCEmail']"));
	//	WebElement textPassWord = driver.findElement(By.xpath("//input[@id='txtPassword']"));
	//	WebElement textCPassWord = driver.findElement(By.xpath("//input[@id='txtCPassword']"));
	//	WebElement textPhone = driver.findElement(By.xpath("//input[@id='txtPhone']"));
	//	WebElement clickButton = driver.findElement(By.xpath("//button[@type='submit']"));
	//	String fullName = "Quoc Cuong";
	//	String email = 	"cuongdnqb5@gmail";
	//	String passWord = "cuong111";
	//	String phone = "0367887486";
	//}


	

	@BeforeClass
	public void beforeClass() {
		// Mở browser lên
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
		driver = new FirefoxDriver();
		
		//System.setProperty("webdriver.chrome.driver", projectPath + "/browserDriver/Chromedriver");
		//driver = new ChromeDriver();
		// Hàm này áp dụng việc tìm element(findElement/ findElements)
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// Phóng to browser lên
		driver.manage().window().maximize();
		// Mở app lên
		//driver.get("https://www.facebook.com/");
		
	//	driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	
	}

	@Test
	public void Register_01_Empty_Data() {
		
		// Mở app
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		// Click vào button đăng ký
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	   
	    // Kiểm tra 1 điều kiện trả về là bằng với điều kiện mong muốn
	    Assert.assertEquals( driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
	    Assert.assertEquals( driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
	    Assert.assertEquals( driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
	    Assert.assertEquals( driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(), "Vui lòng nhập mật khẩu");
	    Assert.assertEquals( driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Vui lòng nhập lại mật khẩu");
	    Assert.assertEquals( driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");
	}
	@Test
	public void Register_02_Invalid_Email() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		// Nhập liệu
		driver.findElement(By.id("txtFirstname")).sendKeys("Quoc Cuong");
		driver.findElement(By.id("txtEmail")).sendKeys("cuong@123@123");
		driver.findElement(By.id("txtCEmail")).sendKeys("cuong@123@123");
		driver.findElement(By.id("txtPassword")).sendKeys("123@456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123@456");
		driver.findElement(By.id("txtPhone")).sendKeys("0367887486");
		// Click vào button đăng ký
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		// Kiểm tra message lỗi hiển thị
	
	
	    Assert.assertEquals( driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
	    Assert.assertEquals( driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");

		//textName.sendKeys(fullName);
		//textPassWord.sendKeys(passWord);
		//textCPassWord.sendKeys(passWord);
		//textPhone.sendKeys(phone);
		//clickButton.click();			
	}
	@Test
	public void Register_03_Incorrect_Confirm_Email() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		// Nhập liệu
		driver.findElement(By.id("txtFirstname")).sendKeys("Quoc Cuong");
		driver.findElement(By.id("txtEmail")).sendKeys("cuongdnqb5@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("cuongdnqb6@gmail.cm");
		driver.findElement(By.id("txtPassword")).sendKeys("123@456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123@456");
		driver.findElement(By.id("txtPhone")).sendKeys("0367887486");
		// Click vào button đăng ký
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		// Kiểm tra message lỗi hiển thị

	    Assert.assertEquals( driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");

		
	}
	@Test
	public void Register_04_Password_Less_Than_6_chars() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		// Nhập liệu
		driver.findElement(By.id("txtFirstname")).sendKeys("Quoc Cuong");
		driver.findElement(By.id("txtEmail")).sendKeys("cuongdnqb5@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("cuongdnqb5@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("12456");
		driver.findElement(By.id("txtCPassword")).sendKeys("12456");
		driver.findElement(By.id("txtPhone")).sendKeys("0367887486");
		// Click vào button đăng ký
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		// Kiểm tra message lỗi hiển thị

	    Assert.assertEquals( driver.findElement(By.id("txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
	    Assert.assertEquals( driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");

	}
	@Test
	public void Register_05_Invcorrect_Confirm_Password() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		// Nhập liệu
		driver.findElement(By.id("txtFirstname")).sendKeys("Quoc Cuong");
		driver.findElement(By.id("txtEmail")).sendKeys("cuongdnqb5@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("cuongdnqb5@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("1234567");
		driver.findElement(By.id("txtPhone")).sendKeys("0367887486");
		// Click vào button đăng ký
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		// Kiểm tra message lỗi hiển thị

	    Assert.assertEquals( driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");

		
	}
	@Test
	public void Register_06_Invalid_Phone_Number() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		// Nhập liệu
		driver.findElement(By.id("txtFirstname")).sendKeys("Quoc Cuong");
		driver.findElement(By.id("txtEmail")).sendKeys("cuongdnqb5@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("cuongdnqb5@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("036788748");
		// Click vào button đăng ký
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		// Kiểm tra message lỗi hiển thị

	    Assert.assertEquals( driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");

		
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}