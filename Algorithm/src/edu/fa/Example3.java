package edu.fa;

import java.io.BufferedReader;
import edu.fa.SortArraySample;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

public class Example3 {
	public static void main(String[] args) throws Exception {

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("D:/input.txt"));
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				String[] words = line.split(" ");
				int[] number = new int[words.length];
				for (int i = 0; i < words.length; i++) {
					number[i] = Integer.parseInt(words[i]);
				}
				SortArraySample.quickSorf(number, 0, number.length - 1);
				System.out.println(Arrays.toString(number));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
