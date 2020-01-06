package edu.fa;

import java.util.Scanner;

public class Stopper extends Thread {
	private Lock lk;
	Scanner sc = new Scanner(System.in);
	@Override
	public void run() {
		while (true) {			
			System.out.println("Enter: ");
			lk.setLock(sc.nextLine());
			if (lk.getLock().equals("stop")) {
				break;
			}
		}
	}

	public Stopper(Lock lk) {

		this.lk = lk;
	}

}
