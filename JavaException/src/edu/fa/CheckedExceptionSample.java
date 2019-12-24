package edu.fa;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CheckedExceptionSample {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Thread.sleep(1000);
		
		try {
			Files.createFile(Paths.get("student.txt"));
		}catch(FileAlreadyExistsException | FileNotFoundException | NullPointerException e) {
			System.out.println("File Already");
			System.out.println(e.toString());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("exception: " +e.toString());
		} catch (Exception e) {
			
		} finally {
			System.out.println("Always execute");
		}
		
		System.out.println("after exception");
	}

}
