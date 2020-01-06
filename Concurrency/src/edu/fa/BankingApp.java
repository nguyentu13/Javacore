package edu.fa;

public class BankingApp {

	public static void main(String[] args) {
		Account account= new Account(90);
		Customer cus1= new Customer("Peter", account);
		Customer cus2= new Customer("David", account);
		
		Thread t1= new Thread(cus1);
		Thread t2= new Thread(cus2);
		t1.start();
		t2.start();

	}

}
