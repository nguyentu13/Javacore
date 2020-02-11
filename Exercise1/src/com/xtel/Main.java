package com.xtel;

public class Main {

	public static void main(String[] args) {
		Lock lock = new Lock(); 
		Thread printerThread = new Printer(lock);
		Thread stopperThread = new Stopper(lock);
		printerThread.start();
		stopperThread.start();

	}

}
