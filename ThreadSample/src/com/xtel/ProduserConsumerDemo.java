package com.xtel;

import java.util.LinkedList;
import java.util.Random;

public class ProduserConsumerDemo {

	public static void main(String[] args) {
		LinkedList<Integer> queue = new LinkedList<>();
		int size = 5;
		Producer producer = new Producer(queue, size);
		Consumer consumer = new Consumer(queue);
		Thread producerThread1 = new Thread(producer, "Producer");
		Thread producerThread2 = new Thread(producer, "Producer");
		Thread producerThread3 = new Thread(producer, "Producer");

		Thread consumerThread1 = new Thread(consumer, "Consumer");
		Thread consumerThread2 = new Thread(consumer, "Consumer");
		Thread consumerThread3 = new Thread(consumer, "Consumer");
		
		producerThread1.setName("Producer 1");
		producerThread2.setName("Producer 2");
		producerThread3.setName("Producer 3");
		consumerThread1.setName("Consumer 1");
		consumerThread2.setName("Consumer 2");
		consumerThread3.setName("Consumer 3");

		producerThread1.start();
		producerThread2.start();
		producerThread3.start();
		consumerThread1.start();
		consumerThread2.start();
		consumerThread3.start();
	}
}

class Producer implements Runnable {
	private final LinkedList<Integer> queue;
	private final int size;

	public Producer(LinkedList<Integer> queue, final int size) {
		this.queue = queue;
		this.size = size;
	}

	public void run() {
		try {
			produce();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void produce() throws InterruptedException {
		while (true) {
			synchronized (queue) {
				while (queue.size() == size) {
					try {
						System.out.println("Queue is full!!! Queue size : " +size);
						Thread.sleep(100);
						queue.wait();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}

				Random random = new Random();
				int i = random.nextInt(1000);
				System.out.println(Thread.currentThread().getName()+" putting value : " + i );
				queue.add(i);
				queue.notifyAll();
			}

		}
	}
}

class Consumer implements Runnable {
	private final LinkedList<Integer> queue;

	public Consumer(LinkedList<Integer> queue) {
		this.queue = queue;
	}

	public void run() {
		try {
			consume();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void consume() throws InterruptedException {
		while (true) {
			synchronized (queue) {
				while (queue.isEmpty()) {
					System.out.println("Queue is empty!!!");
					try {
						Thread.sleep(100);
						queue.wait();
					} catch (Exception ex) {
						ex.printStackTrace();
					}

				}
				System.out.println(Thread.currentThread().getName()+" taking value : " + queue.remove(0));
				queue.notifyAll();
			}

		}
	}
}
