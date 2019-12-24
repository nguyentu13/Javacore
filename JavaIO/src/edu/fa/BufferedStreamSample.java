package edu.fa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class BufferedStreamSample {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = null;
		PrintWriter pw = null;
		try {
			br = new BufferedReader(new FileReader("D:/input.txt"));
			pw = new PrintWriter( new FileWriter("D:/output.txt"));
			String line = null;
			while((line=br.readLine())!=null) {
				System.out.println(line);
				pw.println(line);
			}
			
		} finally {
			if (br != null) {
				br.close();
			}
			if (pw != null) {
				pw.close();
			}
		}
	}
}
