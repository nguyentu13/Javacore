package edu.fa;

public class Student {
	private String [] subjects= new String[3];
	
	private void displaySubject() {
		System.out.println(subjects);
		System.out.println(subjects[2]);
	}
	public static void main(String[] args) {
		Student student = new Student();
		student.displaySubject();
	}
	
}
