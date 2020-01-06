package edu.fa;

import java.util.List;

public class Enrollment implements Runnable{
	private List<Student> studentList;
	
	
	public Enrollment(List<Student> studentList) {
		super();
		this.studentList = studentList;
	}


	@Override
	public void run() {
		if(studentList!=null) {
			System.out.println(Thread.currentThread().getName());
			System.out.println(studentList.size());
		}
		
		
	}

}
