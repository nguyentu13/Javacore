package edu.fa;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
public class ChannelDemo {
   public static void main(String args[]) throws IOException {
      RandomAccessFile file = new RandomAccessFile("D:/output.txt", "r");
      FileChannel fileChannel = file.getChannel();
      ByteBuffer byteBuffer = ByteBuffer.allocate(256);
      while (fileChannel.read(byteBuffer) > 0) {
         // flip the buffer to prepare for get operation
         byteBuffer.flip();
         while (byteBuffer.hasRemaining()) {
            System.out.print((char) byteBuffer.get());
         }
      }
      file.close();
   }
}
