package edu.fa;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Example3 {

	public static void main(String[] args) {

		Timer timer1 = new Timer();
		Timer timer2 = new Timer();

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {

				TimerTask tt1 = new TimerTask() {

					@Override
					public void run() {
						Random rd = new Random();
						String number = Integer.toString(rd.nextInt(10)) + " ";
						System.out.println("number: " + number);

					}
				};
				timer1.schedule(tt1,0, 1000);
			}

		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				TimerTask tt2 = new TimerTask() {

					@Override
					public void run() {
						timer1.cancel();
						timer2.cancel();

					}
				};
				timer2.schedule(tt2, 5000);
			}
		});
		t1.start();
		t2.start();
	}
}
