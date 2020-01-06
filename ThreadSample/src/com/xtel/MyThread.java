package com.xtel;

public class MyThread {
	Countx count = new Countx();

	public MyThread() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 500; i++) {
					count.in();
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 500; i++) {
					count.out();
				}

			}
		}).start();
	}

	public static void main(String[] args) {
		new MyThread();
	}

	class Countx {
		public int i = 0, j = 0;

		public void in() {
			i++;
			j++;
		}

		public void out() {
			System.out.println("down i: " + i);
			System.out.println("down j: " + j);
			System.out.println("-----------");
		}
	}
}
