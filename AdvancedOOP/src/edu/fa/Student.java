package edu.fa;

public class Student extends Person{
	
	public Student() {
		System.out.println("Creating a new student");
	}
	
	public Student(String name) {
		System.out.println("Creating a new student with name: " + name);
	}
	
	public void study(boolean active) {
		if (active) {
			System.out.println("I'm studying");
		} else {
			System.out.println("I'm not studying");
		}
	}

	@SuppressWarnings("deprecation")
	public void study(String subject) {
		Enrollment enrollment = new Enrollment();
		enrollment.enroll();
		System.out.println("I'm studying " + subject);
	}


	public void move() {
		System.out.println("Move method from Person");
		
	}
	public void move(String location) {
		System.out.println("I'm moving to "+ location);
		
	}
}
