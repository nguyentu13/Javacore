package queuemananger;

public class QueueManager {
	private MyQueue queue = new MyQueue();
	
	public void put(int ele) {
		queue.put(ele);
	}
	
	public int take() {
		return queue.take();
	}
}
