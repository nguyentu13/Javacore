package com.xtel;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProduserConsumerDemo {

	public static void main(String[] args) {
		LinkedList<Integer> queue = new LinkedList<>();
	
		ExecutorService executorService1 = Executors.newFixedThreadPool(3);
		ExecutorService executorService2 = Executors.newFixedThreadPool(3);
		
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				while(true) {
					executorService1.execute(new Producer(queue));
				}
				
			}
			
		});
		
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				while(true) {
					executorService2.execute(new Consumer(queue));
				}
				
			}
			
		});
		t1.start();
		t2.start();
	}
}

class Producer implements Runnable {
	private final LinkedList<Integer> queue;
	private final int size = 10;
	
	public Producer(LinkedList<Integer> queue) {
		this.queue = queue;
	}
	
	public void run() {
		try {
			produce();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void produce() throws InterruptedException {
			synchronized (queue) {
				while (queue.size() == size) {
					try {
						System.out.println("Queue is full!!! Queue size : " + size);
						Thread.sleep(100);
						queue.wait();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}

				Random random = new Random();
				int i = random.nextInt(1000);
				System.out.println(" putting value : " + i);
				queue.add(i);
				queue.notify();
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
				System.out.println(" taking value : " + queue.remove(0));
				queue.notify();
			}
	}
}
