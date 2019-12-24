package edu.fa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.fa.model.Student;

public class ColectionSorting {
	public static void main(String[] args) {
		List<Integer> yearList = new ArrayList<>();
		yearList.add(2018);
		yearList.add(2017);
		yearList.add(2019);

		for (Integer i : yearList) {
			System.out.println(i);
		}
		Collections.sort(yearList);
		System.out.println("=================");
		for (Integer i : yearList) {
			System.out.println(i);
		}

		List<String> nameList = new ArrayList<String>();
		nameList.add("Fresher");
		nameList.add("Academy");
		nameList.add("Ducky");
		System.out.println("==========");
		for (String name : nameList) {
			System.out.println(name);
		}
		Collections.sort(nameList);
		System.out.println("========");
		for (String name : nameList) {
			System.out.println(name);
		}
		
		List<Student> studentList= new ArrayList<Student>();
		studentList.add(new Student("Ducky",6));
		studentList.add(new Student("Clover",8));
		studentList.add(new Student("Hugo",7));
//		Collections.sort(studentList);
		Collections.sort(studentList, new StudentComparator());
		System.out.println(studentList);
		
		
		
	}
}
