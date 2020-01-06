package edu.fa;

import java.util.concurrent.TimeUnit;

public class Account {
	private int balance;

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public Account(int balance) {
		super();
		this.balance = balance;
	}

	public void withdraw(String name, int money) {
		int count = 0;
		while (count++ < 3) {
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(name + " withdrawing ");
		}
		if(balance>= money) {
			balance-=money;
			System.out.println(name + " succeeded " + balance);
		} else {
			System.out.println(name + " not enough money " + balance);
		}
	}
}
