package edu.fa;

import java.util.HashSet;
import java.util.Set;

import edu.fa.model.Student;

public class Duplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set<Student> studentSet = new HashSet<>();
		studentSet.add(new Student("Ducky",6));
		studentSet.add(new Student("Clover",8));
		studentSet.add(new Student("Hugo",7));
		
		System.out.println(studentSet);
		studentSet.add(new Student("Clover",9));
		System.out.println(studentSet);
		
	}

}
