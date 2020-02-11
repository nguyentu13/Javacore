package queuemananger;

import java.util.LinkedList;

public class MyQueue extends LinkedList<Integer>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int size = 10;
	private final Object lockP = new Object();
	private final Object lockC = new Object();
	
	public void put(int ele) {
		synchronized (lockP) {
			if(this.getSize()>size) {
				System.out.println("Queue is Full!!");
				try {
					lockP.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			this.addFirst(ele);
		}
		
		synchronized (lockC) {
			lockC.notifyAll();
		}
	}
	
	public int take() {
		int ele = 0;
		synchronized (lockC) {
			
			if(this.getSize() == 0)
				System.out.println("Queue is Empty!!");
				try {
					lockC.wait();
				} catch (Exception e) {
					e.printStackTrace();	
				}
			}
				ele = this.poll();
			
		
		synchronized(lockP) {
			lockP.notifyAll();
		}
		
		return ele;
	}
	
	public synchronized int getSize() {
		return this.size();
	}
	
}
