package testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_01_Assert {
	WebDriver driver;
	@Test
	public void TC_01(){
		//3 hàm Assert hay dùng
		//Kiểm tra tính đúng đắn của dữ liệu
		
		//1 - Kiểm tra dữ liệu mình mong muốn là ĐÚNG
		//Email textBox hiển thị
		Assert.assertTrue(driver.findElement(By.id("email")).isDisplayed());
		//1 - Kiểm tra dữ liệu mình mong muốn là SAI
		Assert.assertFalse(driver.findElement(By.id("email")).isDisplayed());
		//1 - Kiểm tra dữ liệu mình mong muốn với dữ liệu thực tế là bằng nhau
		Assert.assertEquals(driver.findElement(By.id("search")).getAttribute("placeholder"), "Search entire store here...");
		//Tuyệt đối
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		
		//Tương đối
		String benifitText = driver.findElement(By.cssSelector("ul.benefits")).getText();
		Assert.assertTrue(benifitText.contains("Faster checkout"));
		Assert.assertTrue(benifitText.contains("Save multiple shipping addresses"));
		Assert.assertTrue(benifitText.contains("View and track orders and more"));
		
	}

}
