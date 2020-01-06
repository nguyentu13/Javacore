
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class TestTimer {
	public static void main(String[] args) {
		TimerTask timerTask = new TimerTask() {
			
			@Override
			public void run() {
				Random rd= new Random();
				System.out.println(rd.nextInt(5));
				
			}
		};
		long delay=1000L;
		Timer timer= new Timer("Timer");
		timer.schedule(timerTask,0, delay);
	}
}
