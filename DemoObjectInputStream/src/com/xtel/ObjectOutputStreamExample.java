package com.xtel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectOutputStreamExample {
	public static void main(String[] args) throws IOException {
		ObjectOutputStream oos = null;
		List<Student> list= new ArrayList<>();
		Map<String, List<Student>> cache = new HashMap<>();
		try {
			oos = new ObjectOutputStream(new FileOutputStream("D:\\cache.txt"));
			// create student
			Student student1 = new Student(1, "Tran Hao Phong", "Ha Noi", 17);
			Student student2 = new Student(2, "Tran Xuan Truong", "Ha Noi", 17);
			list.add(student1);
			list.add(student2);
			
			cache.put("a", list);
			cache.put("b", list);
			// write student
			oos.writeObject(cache);
			System.out.println("Success...");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			oos.close();
		}
	}
}
