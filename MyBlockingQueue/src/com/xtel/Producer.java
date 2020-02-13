package com.xtel;

import java.util.concurrent.ThreadLocalRandom;

public class Producer implements Runnable {
	private final MyBlockingQueue<Integer> queue;
	 
    Producer(MyBlockingQueue<Integer> queue) {
        this.queue = queue;
    }
    
    public void run() {
        try {
            while (true) {
                queue.put(produce());              
                System.out.println("Producer put - Queue size() = "  + queue.size());                
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
 
    private Integer produce() throws InterruptedException {
    	int a=ThreadLocalRandom.current().nextInt(1, 100);
        Thread.sleep(50);
        return a;
    }
}
