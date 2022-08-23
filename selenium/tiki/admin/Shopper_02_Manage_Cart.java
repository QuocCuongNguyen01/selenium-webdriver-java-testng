package tiki.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Shopper_02_Manage_Cart extends BaseTest {
String cartName, cartNumber, cartExpireDate;

	@BeforeTest(alwaysRun = true)
	public void initDateTest() {
		System.out.println("----------------Init Data Test ------------------");

		cartName = "NGUYEN QUOC CUONG";
		cartNumber = "1234-5678-1234-1234";
		cartExpireDate = "20/2030";
		
	}
	@Test(groups = {"admin","cart"})
	public void Cart_01_Create_Visa() {
		driver.get("https://automationfc.com");
	}

	@Test(groups = {"admin","cart"})
	public void Cart_02_View_Visa() {
	}

	@Test(groups = {"admin","cart"})
	public void Cart_03_Update_Visa() {
	}

	@Test(groups = {"admin","cart"})
	public void Cart_04_Delete_Visa() {
	}

}
