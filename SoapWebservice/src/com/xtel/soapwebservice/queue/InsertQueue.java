package com.xtel.soapwebservice.queue;

import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

import com.xtel.soapwebservice.entity.Schedule;
import com.xtel.soapwebservice.log.Log;

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
		try {
			queue.put(schedule);
		} catch (Exception ex) {
			logger.warn(ex);
		}
	}

	public Schedule take() {
		Schedule schedule = null;

		try {
			schedule = (Schedule) queue.take();

		} catch (InterruptedException e) {
			logger.warn(e);
		}

		return schedule;
	}
	public synchronized int getSize() {
		return queue.size();
	}
}
