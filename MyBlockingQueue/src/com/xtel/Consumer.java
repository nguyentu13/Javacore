package com.xtel;

import java.util.concurrent.ThreadLocalRandom;

public class Consumer implements Runnable {
	private final MyBlockingQueue<Integer> queue;

	Consumer(MyBlockingQueue<Integer> queue) {
		this.queue = queue;
	}

	public void run() {
		try {
			while (true) {
				queue.take();
				System.out.println("Consumer take - Queue size() = " + queue.size());
				Thread.sleep(ThreadLocalRandom.current().nextInt(50, 300)); 
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
