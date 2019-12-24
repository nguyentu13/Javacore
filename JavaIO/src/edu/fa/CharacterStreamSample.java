package edu.fa;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CharacterStreamSample {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileReader fr = null;
		FileWriter fw = null;
		try {
			fr = new FileReader("D:/input.txt");
			fw = new FileWriter("D:/output.txt");
			int c = 0;
			while ((c = fr.read()) != -1) {
				fw.write(c);
			}
		} finally {
			if (fr != null) {
				fr.close();
			}
			if (fw != null) {
				fw.close();
			}
		}
	}

}
