package edu.fa;

import java.util.concurrent.TimeUnit;

public class Worker implements Runnable {
	private String name;
	
	

	public Worker(String name) {
		super();
		this.name = name;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	@Override
	public void run() {
		int count=0;
		while(count++ <5) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(this.getName());
		}
		
		
	}

}
