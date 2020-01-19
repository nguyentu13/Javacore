package com.xtel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MyRunnable implements Runnable {
	/*ten cua Runnable, giup chung ta phan biet Runnable nao dang
	 thuc thi trong Threadpool*/
	private String name;
	
	public MyRunnable(String name) {
		this.name=name;
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(name + " dang thuc thi...");
		//Gia lap thoi gian chay cua Runnable
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(name + " ket thuc.");
		
	}
	public static void main(String[] args) {
		//Khai bao 1 threadpool thông qua newSingleThreadExecutor() của Executors
		ExecutorService executorService= Executors.newFixedThreadPool(5);
		List<Future> listFuture = new ArrayList<Future>();
		
		for(int i=0;i<10;i++) {
			MyRunnable myRunnable= new MyRunnable("Runnable "+ (i+1));
			Future future=executorService.submit(myRunnable);
			listFuture.add(future);
		}
		for(Future future : listFuture) {
			try {
				System.out.println(future.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		executorService.shutdown();
	}
	
}
