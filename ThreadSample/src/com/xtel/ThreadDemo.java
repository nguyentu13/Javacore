package com.xtel;

public class ThreadDemo extends Thread {
	private Thread t;
	private String threadName;

	public ThreadDemo(String name) {
		threadName = name;
		System.out.println("Creating " + threadName);
	}

	@Override
	public void run() {

		try {
			for (int i = 4; i > 0; i--) {
				System.out.println("Thread: " + threadName + ", " + i);
				Thread.sleep(50);
			}

		} catch (InterruptedException e) {
			System.out.println("Thread "+ threadName+ "interrupted.");
			
		}
		System.out.println("Thread "+ threadName+ "exiting.");

	}
	public void start() {
		System.out.println("Starting "+threadName);
		if(t == null) {
			t= new Thread(this, threadName);
			t.start();
		}
	}
}