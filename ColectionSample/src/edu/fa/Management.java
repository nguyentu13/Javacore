package edu.fa;

import java.util.ArrayList;
import java.util.List;

import edu.fa.model.Student;
import edu.fa.model.Teacher;

public class Management {
	public <T> void manage(List<T> list, T t) {
		if(list==null) {
			return;
		}
		list.add(t);
		System.out.println(list);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Management management=new Management();
		List<Student> studentList= new ArrayList<Student>();
		studentList.add(new Student("Ducky",6));
		studentList.add(new Student("Clover",8));
		studentList.add(new Student("Hugo",7));
		management.manage(studentList, new Student("Victor",9));
		
		List<Teacher> teacherList= new ArrayList<>();
		teacherList.add(new Teacher("Peter"));
		teacherList.add(new Teacher("David"));
		teacherList.add(new Teacher("Ninh"));
		management.manage(teacherList, new Teacher("Mary"));
		
	}

}
