package com.xtel.soapwebservice.process;

import org.apache.log4j.Logger;

import com.xtel.soapwebservice.entity.Schedule;
import com.xtel.soapwebservice.log.Log;

public abstract class AbsThread extends Thread {
	private boolean isRunning = true;
	private long sleepTime = 10;
	Schedule schedule = new Schedule();
	private Logger logger = new Log().getLogger(AbsThread.class);

	public synchronized void start() {
		super.start();
	}

	public void run() {
		super.run();
		while (isRunning) {
			try {
				execute();
			} catch (Exception ex) {
				logger.warn(ex);
			}

			synchronized (schedule) {
				try {
					schedule.wait(sleepTime);
				} catch (Exception ex) {
					logger.warn(ex);
				}
			}
		}
	}

	protected abstract void execute();
}
