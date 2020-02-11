package producer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class AbsThread extends Thread{
	private int poolSize = 10;
	protected ExecutorService service = Executors.newFixedThreadPool(poolSize);
	
	public void run() {
		while(true) {
			service.execute(new Runnable() {

				@Override
				public void run() {
					execute();
				}
				
			});
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	protected abstract void execute();
}
