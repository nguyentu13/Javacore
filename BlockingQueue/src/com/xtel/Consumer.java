package com.xtel;



import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    private final BlockingQueue<Integer> queue;

    @Override
    public void run() {

        try {
            for(int i=0;i<5;i++) {
                Integer take = queue.take();
                process(take);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

    private void process(Integer take) throws InterruptedException {
        System.out.println("[Consumer] Take : " + take);
        Thread.sleep(2000);
        if(queue.size()==0) {
        	System.out.println("Queue is Empty!");
        }
    }

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }
}

