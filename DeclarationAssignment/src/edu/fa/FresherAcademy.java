package edu.fa;

public class FresherAcademy {
	private String name="Fresher Academy";
	private int numberOfStudent;
	
	public void checkVariables() {
		System.out.println("Name: "+name);
		System.out.println("Number of Student: "+ numberOfStudent);
	}
	
	public int getNumberOfStundent() {
		int numberOfStudent=1;
		Student student= new Student();
		System.out.println(student);
		System.out.println(name);
		System.out.println(this.numberOfStudent);
		return numberOfStudent;
	}
	
	public static void main(String[] args) {
		FresherAcademy fa= new FresherAcademy();
		fa.checkVariables();
	}
}
