package com.xtel;

import java.util.Random;
import java.util.Scanner;

public class GuessANumberThread implements Runnable {
	static int number;
	@Override
	public void run() {
		int count = 0;
		
		int randomNumber = 0;
		while(true) {
			Random rd= new Random();
			randomNumber = rd.nextInt(100);
			System.out.println("number of "+Thread.currentThread().getName()+" : "+randomNumber);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			count++;
			if(number==randomNumber) {
				break;
			}
			
		}
		System.out.println(Thread.currentThread().getName()+" da doan trung so "+ number + " sau "+count+" lan");
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Nhập một số nguyên để các thread đoán: ");
		number = scanner.nextInt();
	
		GuessANumberThread guessANumber = new GuessANumberThread();
		Thread t1= new Thread(guessANumber);
		t1.setName("Thread 1");
		t1.start();
		
//		GuessANumberThread t2 = new GuessANumberThread();
		Thread t2= new Thread(guessANumber);
		t2.setName("Thread 2");
		t2.start();
	}
}
