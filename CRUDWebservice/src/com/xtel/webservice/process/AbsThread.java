package com.xtel.webservice.process;

import com.xtel.webservie.entiy.Patient;

public abstract class AbsThread extends Thread {
	private boolean isRunning = true;
	private long sleepTime = 10;
	Patient patient = new Patient();

	public synchronized void start() {
		super.start();
	}

	public void run() {
		super.run();
		while (isRunning) {
			try {
				execute();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			synchronized (patient) {
				try {
					patient.wait(sleepTime);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	protected abstract void execute();
}
