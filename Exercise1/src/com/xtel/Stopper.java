package com.xtel;

import java.util.Scanner;

public class Stopper extends Thread{
	private Lock lock;
	Scanner sc = new Scanner(System.in);
	
	public Stopper(Lock lock) {
		this.lock=lock;
	}

	@Override
	public void run() {
		while(true) {
			System.out.println("Enter: ");
			lock.setLock(sc.nextLine());
			if(lock.getLock().equals("stop")) {
				break;
			}
		}
	}
	
	
}
