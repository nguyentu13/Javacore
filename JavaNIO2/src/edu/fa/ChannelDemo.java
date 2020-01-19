package edu.fa;


import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelDemo {

	public static void main(String[] args) throws IOException {
		RandomAccessFile file = new RandomAccessFile("D:\\output.txt", "r");
		FileChannel filechannel= file.getChannel();
		ByteBuffer bytebuffer = ByteBuffer.allocate(512);
		while(filechannel.read(bytebuffer)>0) {
			//flip the buffer to prepare for get operation
			bytebuffer.flip();
			while(bytebuffer.hasRemaining()) {
				System.out.print((char) bytebuffer.get());
			}
		}
		file.close();
	}

}
