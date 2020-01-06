package edu.fa;

public class ThreadSample {

	public static void main(String[] args) {
		CustomerThread t1 = new CustomerThread();
		t1.setName("A");
		t1.start();
		CustomerThread t2 = new CustomerThread();
		t2.setName("B");
		t2.start();
	}

}

class CustomerThread extends Thread {

	@Override
	public void run() {
		int count = 0;
		while (count++ < 5) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.format("Customer Thread  %s is  running... %d\n",this.getName(),count);
		}
	}

}
