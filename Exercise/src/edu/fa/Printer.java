package edu.fa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class Printer extends Thread {
	private Lock lk;
	private String path = new File("config/output.txt").getAbsolutePath();
	
	public Printer(Lock lock) {
		this.lk=lock;
	}

	@Override
	public void run() {
		while(true) {
			if(lk.getLock().equals("stop")) {
				break;
			}
			FileOutputStream fos = null;
			Random rd = new Random();
			String number = Integer.toString(rd.nextInt(10)) + " ";
			System.out.println("number: " + number);
			try {
				fos = new FileOutputStream(path, true);
				byte[] n = number.getBytes();
				try {
					fos.write(n);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			} finally {
				if (fos != null) {
					try {
						fos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

}
