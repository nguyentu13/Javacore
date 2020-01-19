package com.xtel;

public class MainOfBankAccount {

	public static void main(String[] args) {
		BankAccount bankAccount = new BankAccount();

		// nguoi vo rut 10 tr
		WithdrawThread wifethread = new WithdrawThread("Wife", bankAccount, 10000000);
		wifethread.start();

		// nguoi chong nap 5 tr
		DepositThread husbandThread = new DepositThread("Husband", bankAccount, 5000000);
		husbandThread.start();

	}

}
