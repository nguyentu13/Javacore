package edu.fa;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class FileSample {

	public static void main(String[] args) throws IOException {
		File file = new File("D:/input.txt");
		// tao file
		file.createNewFile();
		File dir = new File("D:/tmp");
		dir.mkdirs();

		System.out.println(file.exists());
		System.out.println(dir.exists());

		// so sanh path 2 file
		if (file.compareTo(dir) == 0) {
			System.out.println("Both path are same!");
		} else {
			System.out.println("Paths are not same");
		}

//		file.delete();
		System.out.println(file.exists());
		//lay ngay edit file lan cuoi
		File file1 = new File("FileSample.java");
		Long lastModified = file.lastModified();
		Date date = new Date(lastModified);
		System.out.println(date);
		
		//tao file trong thu muc
		File file2= null;
		File dir2= new File("D:/");
		file2 = File.createTempFile("JavaTemp", ".txt", dir2);
		System.out.println(file2.getPath());
		
	}

}
