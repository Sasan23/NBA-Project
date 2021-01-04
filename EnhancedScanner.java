package nbaProject;

//Sasan Dehkhoda, sade0599

import java.util.Scanner;

public class EnhancedScanner {
	
	private static Scanner scanner = new Scanner(System.in); 
	
	public String nextLine(String text) { 
		System.out.println(text + ": "); 
		String input = scanner.nextLine(); 
		if (!input.equals("") || !input.isEmpty()) {
			input = capitalizeFirst(input);
		}
		return input;  
	}
	
	public String nextString() {
		return scanner.nextLine();
	}
	
	public int nextInt(String text) { 
		System.out.println(text + ": "); 
		while (true) { 
			try { 
				int input = scanner.nextInt(); 
				scanner.nextLine(); 
				return input;
			} catch (Exception e) { 
				System.out.println("Invalid data type. Enter an integer."); 
				scanner.nextLine(); 
			}
		}
	}
	
	public double nextDouble(String text) { 
		System.out.println(text + ": ");
		while (true) {
			try {
				double input = scanner.nextDouble();
				scanner.nextLine();
				return input;
			} catch (Exception e) {
				System.out.println("Invalid data type. Enter a number.");
				scanner.nextLine();
			}
		}
	}
	
	private static String capitalizeFirst(String text) {  
		text.toLowerCase();
		String convertedText = text.substring(0,1).toUpperCase() + text.substring(1).toLowerCase();
		return convertedText;
	}
	
	public void close() {
		scanner.close();
	}
}
