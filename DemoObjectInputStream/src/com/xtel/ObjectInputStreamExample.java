package com.xtel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectInputStreamExample {
	public static void main(String[] args) throws IOException {
		ObjectInputStream ois = null;
//		List<Student> list = new ArrayList<>();
		Map<String, List<Student>> cache = new HashMap<>();
		try {
			ois = new ObjectInputStream(new FileInputStream("D:\\cache.txt"));
			// read student
			cache =  (Map<String, List<Student>>) ois.readObject();
			// show student
//			System.out.println(list);
			cache.forEach((key, value) -> System.out.println(
			        "Key = " + key + ", value = " + value));
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			ois.close();
		}
	}
}