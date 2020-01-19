package com.xtel;

public class TestClass {
	public class Socola {
		private int contents = 0;
		public boolean isRemainingContents;

		public synchronized int get() {
			while (isRemainingContents == false) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			isRemainingContents = false;

			// print onto screen
			System.out.println("\tConsumer get " + this.contents);

			notifyAll();
			return contents;
		}

		public synchronized void set(int piContents) {
			while (isRemainingContents == true) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			this.contents = piContents;
			isRemainingContents = true;
			// print onto screen
			System.out.println("Producer set " + this.contents);
			notifyAll();
		}
	}

	public class Producer extends Thread {
		private Socola socola;

		Producer(Socola pSocola) {
			socola = pSocola;

		}

		public void run() {
			for (int i = 0; i < 10; i++) {
				socola.set(i);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}
			}
		}
	}

	public class Consumer extends Thread {
		private Socola socola;

		Consumer(Socola pSocola) {
			socola = pSocola;
		}

		public void run() {
			for (int i = 0; i < 10; i++) {
				socola.get();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}
			}
		}
	}

	public static void main(String[] args) {
		TestClass t = new TestClass();
		Socola s = t.new Socola();
		Producer p = t.new Producer(s);
		Consumer c = t.new Consumer(s);
		p.start();
		c.start();
	}
}
