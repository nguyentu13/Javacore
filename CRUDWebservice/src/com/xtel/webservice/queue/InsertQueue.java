package com.xtel.webservice.queue;

import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

import com.xtel.webservice.log.Log;
import com.xtel.webservie.entiy.Schedule;

public class InsertQueue {
	private static InsertQueue ins = null;
	private Logger logger = new Log().getLogger(InsertQueue.class);
	
	private InsertQueue() {
	}

	public synchronized static InsertQueue getInstance() {
		if (ins == null) {
			ins = new InsertQueue();
		}
		return ins;
	}

	LinkedBlockingQueue<Object> queue = new LinkedBlockingQueue<Object>(1000);

	public void put(Schedule schedule) throws Exception {
		if (queue.size() > 800) {
			System.out.println("The system is overloaded!");
			throw new Exception("The system is overloaded ! Please try again later");
		}
		try {
			queue.put(schedule);
		} catch (Exception ex) {
			logger.debug(ex);
		}
	}

	public Schedule take() {
		Schedule schedule = null;

		try {
			schedule = (Schedule) queue.take();

		} catch (InterruptedException e) {
			logger.debug(e);
		}

		return schedule;
	}
	public synchronized int getSize() {
		return queue.size();
	}
}
