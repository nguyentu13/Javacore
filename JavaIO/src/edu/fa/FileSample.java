package edu.fa;

import java.io.File;
import java.io.IOException;

public class FileSample {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File file = new File("D:/input.txt");
		file.createNewFile();
		File dir=new File("D:/tmp");
		dir.mkdirs();
		
		System.out.println(file.exists());
		System.out.println(dir.exists());
		
//		file.delete();
		System.out.println(file.exists());
	}

}
