package com.xtel;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Program {
	static BlockingQueue<String> q = new LinkedBlockingQueue<>(10);
	
	public static void main(String args[]) {
		ExecutorService service = Executors.newFixedThreadPool(1);
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
					service.execute(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							try {
								System.out.println(q.take());
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
					});
					
				
				
			}
			
		});
		
		
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				q.offer("hello wolrd");
				service.shutdown();
			}
			
		});
		
		t1.start();
		
		for(int i=0;i<30;i++) {
			System.out.println(i);
		}
		
		t2.start();
		
	}
}
