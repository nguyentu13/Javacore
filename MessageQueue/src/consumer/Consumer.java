package consumer;



import producer.AbsThread;
import queuemananger.MyQueue;

public class Consumer extends AbsThread{
	private MyQueue queue;

	public Consumer(MyQueue queue) {
		this.queue = queue;
	}
	@Override
	protected void execute() {
		System.out.println("Consumer take: " + queue.take());
	}

}
