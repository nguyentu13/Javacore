package com.xtel;

public class DepositThread extends Thread{
	String threadName="";
	long depositAmount=0;
	BankAccount bankAccount;
	public DepositThread(String threadName, BankAccount bankAccount, long depositAmount) {
		super();
		this.threadName = threadName;
		this.depositAmount = depositAmount;
		this.bankAccount = bankAccount;
	}
	@Override
	public void run() {
		bankAccount.deposit(threadName, depositAmount);
	}
	
	
}
