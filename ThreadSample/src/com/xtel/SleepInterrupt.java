package com.xtel;

public class SleepInterrupt extends Object implements Runnable {
	
	public static void main(String[] args) {
		SleepInterrupt si = new SleepInterrupt();
		Thread t = new Thread(si);
		t.start();
	}

	@Override
	public void run() {
		try {
			System.out.println("in run() - about to sleep for 20s");
			Thread.sleep(20000);
	       System.out.println("in run() woke up");
	       
		} catch (Exception e) {
			System.out.println("in run() interrupted while sleeping");
			return;
		}
		System.out.println("in run()-doing stuff after nap");
		System.out.println("in run()-leaving normally");
	}

}
