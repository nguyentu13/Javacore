package edu.fa;


public class Example2 {
	
	public static void main(String[] args) {
		Lock lk= new Lock();
		Thread t1= new Printer(lk);
		Thread t2= new Stopper(lk);
		t1.start();
		t2.start();
	}
}
