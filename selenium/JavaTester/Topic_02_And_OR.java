package JavaTester;

public class Topic_02_And_OR {

	public static void main(String[] args) {
		boolean anserPersonA;
		boolean anserPersonB;
		//boolean resultC;
		
		//AND: 1 trong 2 kêt quả sai thì trả kết quả sai
		//OR: 1 trong 2 kết quả đúng thì trả kết quả đúng
		anserPersonA = true;
		anserPersonB = false;
		
		System.out.println("1. A && B = " + (anserPersonA && anserPersonB));
		System.out.println("1. A || B = " + (anserPersonA || anserPersonB));
		
		anserPersonA = false;
		anserPersonB = true;
		System.out.println("1. A && B = " + (anserPersonA && anserPersonB));
		System.out.println("1. A || B = " + (anserPersonA || anserPersonB));
		
		anserPersonA = false;
		anserPersonB = false;
		
		System.out.println("1. A && B = " + (anserPersonA && anserPersonB));
		System.out.println("1. A || B = " + (anserPersonA || anserPersonB));
		
		anserPersonA = true;
		anserPersonB = true;
	
		System.out.println("1. A && B = " + (anserPersonA && anserPersonB));
		System.out.println("1. A || B = " + (anserPersonA || anserPersonB));

	}

}