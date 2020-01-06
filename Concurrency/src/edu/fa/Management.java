package edu.fa;

import java.util.ArrayList;

public class Management {

	public static void main(String[] args) {
		Enrollment erm= new Enrollment(new ArrayList<Student>());
		
		Thread thread= new Thread(erm, "student enrollment");
		thread.start();

	}

}
