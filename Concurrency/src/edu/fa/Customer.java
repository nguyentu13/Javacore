package edu.fa;

public class Customer implements Runnable{
	private String name;
	private Account account;
	
	
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Account getAccount() {
		return account;
	}



	public void setAccount(Account account) {
		this.account = account;
	}

	

	public Customer(String name, Account account) {
		super();
		this.name = name;
		this.account = account;
	}



	@Override
	public void run() {
		synchronized (account) {
			account.withdraw(name, 50);
		}
		
		
	}
	
}
