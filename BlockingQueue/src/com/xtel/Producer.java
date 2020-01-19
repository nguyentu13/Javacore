package com.xtel;


import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

    private final BlockingQueue<Integer> queue;

    @Override
    public void run() {

        try {        	
        		process();
               
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

    private void process() throws InterruptedException {

        // Put 20 ints into Queue
        for (int i = 0; i < 5; i++) {
            System.out.println("[Producer]"+ Thread.currentThread().getName()+ " Put : " + i);
            queue.put(i);
            System.out.println("[Producer] Queue remainingCapacity : " + queue.remainingCapacity());
            Thread.sleep(1000);
            
        }

    }
    

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }
}


