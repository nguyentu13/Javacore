package edu.fa;

import java.util.Scanner;

public class Example4 {

	public static void main(String[] args) {
		String a="5";
		int count=0;
		for(int i=0;i<5;i++) {
			Scanner sc= new Scanner(System.in);
			System.out.println("Number: ");
			String number=sc.nextLine();
			if(number.equals(a)) {
				System.out.println("Success!");
				break;
			} 
			count++;
		}
		if(count==5) {
			System.out.println("Fail");
		}
		
	}

}
