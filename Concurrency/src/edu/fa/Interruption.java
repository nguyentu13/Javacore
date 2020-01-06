package edu.fa;

public class Interruption {

	public static void main(String[] args) {
		BankingThread bt= new BankingThread();
		bt.setName("FA");
		bt.start();

	}

}
class BankingThread extends Thread {

	@Override
	public void run() {
		int count = 0;
		while (count++ < 5) {
			try {
				Thread.sleep(1000);
				if(count==3) {
					this.interrupt();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				System.out.println(e.toString());
			}
			System.out.format("Banking Thread  %s is  running... %d\n",this.getName(),count);
		}
	}

}