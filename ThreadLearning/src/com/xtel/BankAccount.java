package com.xtel;

public class BankAccount {

	long amount = 5000000; // Số tiền có trong tài khoản

	public synchronized boolean checkAccountBalance(long withdrawAmount) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (withdrawAmount <= amount) {
			// Cho phep rut tien
			return true;
		}
		// Khong cho phep rut tien
		return false;
	}

	public synchronized void withdraw(String threadName, long withdrawAmount) {
		// In thong tin nguoi dung
		System.out.println(threadName + " withdraw: " + withdrawAmount);
		synchronized (this) {
			if (checkAccountBalance(withdrawAmount)) {
				// Gia lap thoi gian rut tien va
				// cap nhat so tien con lai vao du lieu
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				amount -= withdrawAmount;
				System.out.println(threadName + " withdraw successful: " + withdrawAmount);

			} else {
				System.out.println(threadName + " whithdraw error");
			}
		}

		// In ra so du tai khoan
		System.out.println(threadName + " see balance: " + amount);
	}

	public synchronized void withdrawWhenBalanceEnough(String threadName, long withdrawAmount) {
        // In thông tin người rút
        System.out.println(threadName + " check: " + withdrawAmount);
          
        while (!checkAccountBalance(withdrawAmount)) {
            // Nếu không đủ tiền, thì đợi cho đến khi có đủ tiền thì rút
            System.out.println(threadName + " wait for balance enough");
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
         
        // Đủ tiền, hoặc không còn đợi nữa, thì được phép rút
        // Giả lập thời gian rút tiền và 
        // cập nhật số tiền còn lại vào cơ sở dữ liệu
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         
        amount -= withdrawAmount;
        System.out.println(threadName + " withdraw successful: " + withdrawAmount);
    }
     
    public synchronized void deposit(String threadName, long depositAmount) {
        // In thông tin người nạp tiền
        System.out.println(threadName + " deposit: " + depositAmount);
         
        // Giả lập thời gian nạp tiền và 
        // cập nhật số tiền mới vào cơ sở dữ liệu
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         
        amount += depositAmount;
         
        // Đánh thức đối tượng đang ngủ và chờ có tiền thì rút
        notify();
    }

}
