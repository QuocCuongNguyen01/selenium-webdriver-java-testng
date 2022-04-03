package JavaTester;

import org.testng.annotations.Test;

public class Topic_05_Param {
	//Hàm không có tham số

	public void clickToElement() {
		
	}
	//Hàm có 1 tham số
	//Tham số này có kiểu dữ liệu là String
	public void clickToElement(String elementName) {
		
	}
	//Hàm này có hai tham số
	public void clickToElement(String elementName, String locatorName) {
	
	}
	//Test / Test method/ Test case/ Testscript
	@Test
	public void TC_01_Login() {
		clickToElement("Textbox");
	}

}
