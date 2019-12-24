package edu.fa;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteStreamSample {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream("D:/input.txt");
			fos = new FileOutputStream("D:/output.txt");
			int c = 0;

			while ((c = fis.read()) != -1) {
				fos.write(c);
			}
		} finally {
			if (fis != null) {
				fis.close();
			}
			if (fos != null) {
				fos.close();
			}

		}

	}

}
