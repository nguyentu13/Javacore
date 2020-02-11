package com.xtel.webservice.queue;

import java.util.concurrent.LinkedBlockingQueue;

import com.xtel.webservie.entiy.Customer;

public class OrderQueue {
	private static OrderQueue ins = null;

	private OrderQueue() {
	}

	public synchronized static OrderQueue getInstance() {
		if (ins == null) {
			ins = new OrderQueue();
		}
		return ins;
	}

	LinkedBlockingQueue<Object> queue = new LinkedBlockingQueue<Object>(1000);

	public void put(Customer customer) throws Exception {
		if (queue.size() > 800) {
			System.out.println("The system is overloaded!");
			throw new Exception("The system is overloaded ! Please try again later");
		}
		try {
			queue.put(customer);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Customer take() {
		Customer customer = null;

		try {
			customer = (Customer) queue.take();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return customer;
	}
	public synchronized int getSize() {
		return queue.size();
	}
}
