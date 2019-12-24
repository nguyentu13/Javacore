package edu.fa;

public class StudentManagement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Object obj= new Student();
		
		System.out.println(obj instanceof String);
		System.out.println(obj instanceof Student);
		System.out.println(obj instanceof Object);
		Student student=(Student) obj;
		student.study();
		
	}

}
