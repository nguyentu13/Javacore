package edu.fa;

public class Lock {
	
	private String lock="";

	synchronized public String getLock() {
		return lock;
	}

	synchronized public void setLock(String lock) {
		this.lock = lock;
	}
}
