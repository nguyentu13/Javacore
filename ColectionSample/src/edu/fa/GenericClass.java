package edu.fa;

import edu.fa.model.Student;
import edu.fa.model.Teacher;

public class GenericClass {
	public static void main(String[] args) {
		DataAccess<Student> studentDataAccess= new DataAccess<>();
		studentDataAccess.update(new Student());
		
		DataAccess<Teacher> teacherDataAccess= new DataAccess<>();
		teacherDataAccess.update(new Teacher("Peter"));
	}
}
