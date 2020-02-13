package com.xtel.webservice.queue;

import java.util.concurrent.LinkedBlockingQueue;

import com.xtel.webservie.entiy.Patient;

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

	public void put(Patient patient) throws Exception {
		if (queue.size() > 800) {
			System.out.println("The system is overloaded!");
			throw new Exception("The system is overloaded ! Please try again later");
		}
		try {
			queue.put(patient);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Patient take() {
		Patient patient = null;

		try {
			patient = (Patient) queue.take();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return patient;
	}
	public synchronized int getSize() {
		return queue.size();
	}
}
