package edu.fa;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIODemo {

	public static void main(String[] args) throws Exception {
		ReadAndWriteFile();
	}
	
	
	public static void ReadAndWriteFile() throws Exception{
		File file= new File("D:/input.txt"); 
		FileInputStream fis= new FileInputStream(file);
		FileChannel fc1=fis.getChannel();
		long fsize= fc1.size();
		ByteBuffer bb1= ByteBuffer.allocate((int)fsize);
		fc1.read(bb1);
		bb1.rewind();
		String str= new String(bb1.array());
		System.out.println(str);
		FileOutputStream fos= new FileOutputStream("D:/output.txt",true);
		FileChannel fc2= fos.getChannel();
		byte[] data=str.getBytes();
		ByteBuffer bb2= ByteBuffer.allocateDirect(data.length);
		for(int i=0;i<data.length;i++) {
			bb2.put(data[i]);
		}
		bb2.rewind();
		fc2.write(bb2);
		fc1.close();
		fis.close();
		fc2.close();
		fos.close();
	}
}
