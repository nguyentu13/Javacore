package edu.fa;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Example1 {
	public static void TinhTienDien() {
		int[] stair= {0,100,150};
		int[] unitPrice= {1000,1500,2000};
		int[] amount= {0,99000,174000};
		String path = new File("config/input.properties").getAbsolutePath();
		FileInputStream in;
		try {
			in = new FileInputStream(path);
			Properties prop = new Properties();
			try {
				prop.load(in);

				int number= Integer.parseInt(prop.getProperty("number"));
				for(int i=1;i<stair.length;i++) {
					if(number<stair[i]) {
						int price1=amount[i-1]+(number-stair[i-1]+1)*unitPrice[i-1];
						System.out.println(price1);
					}
					
				}
				if(number>stair[stair.length-1]) {
					int price2=(number-stair[stair.length-1]+1)*unitPrice[unitPrice.length-1]+amount[amount.length-1];
					System.out.println(price2);
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		

	}
	
	


	public static void main(String[] args) throws Exception {
		TinhTienDien();
//		QuickSort();
	}
}
