package com.xtel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MyCallable implements Callable<String> {
	
	private String name;
	public MyCallable(String name) {
		this.name=name;
	}
	@Override
	public String call() throws Exception {
		System.out.println(name + " dang thuc thi...");
		Thread.sleep(2000);
		return name;
	}
	public static void main(String[] args) {
		ExecutorService executoService= Executors.newFixedThreadPool(5);
		List<Future<String>> listFuture = new ArrayList<Future<String>>();
		
		for(int i=1;i<10;i++) {
			MyCallable myCallable = new MyCallable("Callable" + (i+1));
			Future<String> future = executoService.submit(myCallable);
			listFuture.add(future);
		}
		for(Future future : listFuture) {
			try {
				System.out.println(future.get()+ " ket thuc");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		executoService.shutdown();
	}
}
