package com.xtel.soapwebservice.process;

import java.util.ArrayList;




public class ConsumerManager {
	private ArrayList<Thread> threadPool = new ArrayList<>();
	private static ConsumerManager ins = new ConsumerManager();
	
	
	public static ConsumerManager getInstance() {
		return ins;
	}
	
	public void start() {
//		ExecutorService executorService = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 15; i++) {
			InsertProcess insertprocess = new InsertProcess();
//			insertprocess.setName(String.format("OrderPool - Thread [%d]", i + 1));
//			executorService.execute(insertprocess);
			threadPool.add(insertprocess);
		}
		
		for (Thread thread : threadPool) {
			thread.start();
		}
	}
	
}
