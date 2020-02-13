package com.xtel;

import java.util.LinkedList;

public class MyBlockingQueue<T> {
	private static final int capacity = 10;
	private final LinkedList<T> queue = new LinkedList<>();

	public synchronized void put(T value) throws InterruptedException {
		while (queue.size() == capacity) {
			System.out.println("Queue is full");
			wait();
		}
		queue.addLast(value);
		notifyAll();
	}

	public synchronized T take() throws InterruptedException {
		while (queue.size() == 0) {
			System.out.println("Queue is empty");
			wait();
		}
		notifyAll();
		return queue.removeFirst();
	}

	public synchronized int size() {
		return queue.size();

	}
}
