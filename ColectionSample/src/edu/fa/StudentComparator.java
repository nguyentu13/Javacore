package edu.fa;

import java.util.Comparator;

import edu.fa.model.Student;

public class StudentComparator implements Comparator<Student> {

	@Override
	public int compare(Student s1, Student s2) {
		// TODO Auto-generated method stub
		return Integer.valueOf(s2.getGrade()).compareTo(s1.getGrade());
	}

}
