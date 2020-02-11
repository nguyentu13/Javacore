import consumer.Consumer;
import producer.Producer;
import queuemananger.MyQueue;

public class Program {
	public static void main(String a[]) {
		MyQueue queue = new MyQueue();
		Producer p = new Producer(queue);
		Consumer c = new Consumer(queue);
		p.start();
		c.start();
	}
}
