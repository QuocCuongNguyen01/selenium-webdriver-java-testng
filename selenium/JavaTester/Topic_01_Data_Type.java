package JavaTester;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Topic_01_Data_Type {
	
	public static void main(String[] args) {
		// Kiểu dữ liệu
		// Khái báo biến: Kiểu dữ liệu + tên biến
		int studentNumber;
		studentNumber = 1000;
		
		// Khai báo + khởi tạo
		
		int teacherNumber = 100;
		
		// Nguyên thủy
		// Boolean: Luận lý/ logic (true/failse)
		
		boolean studentSex = true;
		
		// Byte
		byte bEmployee = 10;
		
		// Short
		short sEmployee = 10;
		
		// Int
		int iEmployee = 10;
		
		// Long
		long lEmployee = 10000;
		
		// Float
		float fEmployee = 8.5f;
		
		// Double
		double dEmployee = 8.2;
		
		// Char : 1 kí tự
		char b = 'T';
		
		//II Tham chiếu(Reference)
		// Array
		int[] studentNumbers = {5, 6, 7};
		String[] studentNames = {"Nam", "Phat", "Hung"}; 
		
		// Class/Interface
		//WebDriver driver = new ChromeDriver();
		
		//Actions action = new Actions(driver);	
		
		// Colection: List(Arrey list)/ Set/ Queue
		//ArrayList<String> studentCountry = new 	ArrayList<String>();
		
		
		// Object
		Object name;
		
		
		
		// String: chuỗi kí tự bao gồm số/ chữ/ kí tự đặc biệt
		String storyName = "cuoc doi nay";
		String companyName = "Youthdev@2016";
		System.out.println(storyName);
		String c = storyName;
		System.out.println(c);
		
		c = "An";
		storyName = c ;
		System.out.println(storyName);
		System.out.println(c);
		
		
		
		
	}

}
