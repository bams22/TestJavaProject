package fortest;

import java.io.File;

public class ForTest {

	public static void main(String[] args) {
		System.out.println("Начало работы программы!");
		LeedTime leedTime = new LeedTime();
		leedTime.start();
		
		outFactorialThreads(1000000);
		 
		try {
			synchronized (leedTime) {
				leedTime.notify();
				leedTime.join();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	static boolean stringEquals(String str1, String str2) {
		return str1.equals(str2);
	}
	
	static void outFactorial(int number) {
		String str = WilsonPrime.factorial(number).toString();
		System.out.println("Строка - содержит " + str.length() + " символов.");
		WilsonPrime.writeOutFile(str, new File("outFile.txt"));
	}
	
	static void outFactorialThreads(int number) {
		FactorialThreads factorialThreads = new FactorialThreads();
		String str = factorialThreads.count(number).toString();
		System.out.println("Строка - содержит " + str.length() + " символов.");
		WilsonPrime.writeOutFile(str, new File("outFile.txt"));
	}
}
