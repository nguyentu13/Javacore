package edu.fa;

public class Connnection {
	public final String EMAIL;
	
	public Connnection() {
		EMAIL="fresher.academy@gmail.com";
	}
	
	public void sendEmail() {
//		EMAIL="ducky@gmail.com";
		System.out.println("Sending email to " + EMAIL);
	}
}
