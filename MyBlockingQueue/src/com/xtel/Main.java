package com.xtel;

public class Main {
	 public static void main(String[] args) throws InterruptedException {
	        MyBlockingQueue<Integer> queue = new MyBlockingQueue<>();
	 
	        Producer producer = new Producer(queue);
	        Consumer consumer1 = new Consumer(queue);
	        Consumer consumer2 = new Consumer(queue);
	        Consumer consumer3 = new Consumer(queue);
	 
	        new Thread(producer).start();
	        new Thread(consumer1).start();
	        new Thread(consumer2).start();
	        new Thread(consumer3).start();
	         
	        Thread.sleep(5000); 
	        Consumer consumer4 = new Consumer(queue);
	        new Thread(consumer4).start();
	    }
}
