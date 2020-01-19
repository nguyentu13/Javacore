package com.xtel;

import java.util.Scanner;

public class MainClass {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		 // Đợi người dùng nhấn một phím để bắt đầu
		System.out.println("Nhan phim bat ky de quay so");
		scanner.nextLine();
		
		// Khai báo & Khởi chạy CountTheNumberThread như là một Thread thông qua
	    // phương thức start()
		CountTheNumberThread countingThread = new CountTheNumberThread();
		countingThread.start();
		// Đợi người dùng nhấn một phím để kết thúc
		System.out.println("Nhan phim bat ky de ket thuc quay so");
		scanner.nextLine();
		// Ngừng Thread và xem hiện đang "quay" tới số nào
		countingThread.end();
		System.out.println("Con so may man: "+ countingThread.getCount());
	}
}
