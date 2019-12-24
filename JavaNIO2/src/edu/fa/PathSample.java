package edu.fa;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathSample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Path path = Paths.get("D:/tmp");
		System.out.println(path.getFileName());
		System.out.println(path.toAbsolutePath());
		
		Path p1= Paths.get("tmp");
		Path p2= Paths.get("student");
		System.out.println(p1.relativize(p2));
		System.out.println(p1.equals(p2));
	}

}
