package com.xtel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class Printer extends Thread {
	private Lock lock;
	private String path = new File("output.txt").getAbsolutePath();
	
	public Printer(Lock lock) {
		this.lock=lock;
	}
	
	@Override
	public void run() {
		while (true) {
			if(lock.getLock().equals("stop")) {
				break;
			}
			FileOutputStream fos = null;
			Random rd = new Random();
			String number = Integer.toString(rd.nextInt(10)) + " ";
			System.out.println("number: " + number);
			try {
				fos = new FileOutputStream(path, true);
				byte[] n = number.getBytes();
				fos.write(n);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				if (fos != null) {
					try {
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
