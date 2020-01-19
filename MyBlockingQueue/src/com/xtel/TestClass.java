package com.xtel;

import java.util.LinkedList;
import java.util.Random;

public class TestClass {
	public class Socola {
		LinkedList<Integer> queue= new LinkedList<>();
		private int contents = 0;
		public boolean isRemainingContents;

		public synchronized int get() {
			if(queue.size()==0) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//print onto screen
			System.out.println("Consumer get " + this.contents);
			
			this.contents=queue.getLast();
			queue.removeLast();
//			for(int number : queue) {
//				System.out.print(number+"\t");
//			}
			notifyAll();
			return queue.lastIndexOf(queue);
		}

		public synchronized void set() {
			if(queue.size()==3) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Random rd= new Random();			
			this.contents = rd.nextInt(100);
			queue.addFirst(this.contents);

			//print onto screen
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
				socola.set();

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
					Thread.sleep(500);
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
