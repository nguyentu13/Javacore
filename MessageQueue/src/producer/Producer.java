package producer;

import java.util.Random;

import queuemananger.MyQueue;

public class Producer extends AbsThread {
	private MyQueue queue;
	
	public Producer(MyQueue queue) {
		this.queue = queue;
	}

	@Override
	protected void execute() {
		Random rd = new Random();
		int number = rd.nextInt(10);
		System.out.println("Producer put: " + number);
		queue.put(number);
	}
}
