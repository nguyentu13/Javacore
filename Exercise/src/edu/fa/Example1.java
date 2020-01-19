package edu.fa;


import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Example1 {
	public int LaySoDien() throws Exception {
		String path = new File("config/input.properties").getAbsolutePath();
		FileInputStream fis = new FileInputStream(path);
		Properties prop = new Properties();
		prop.load(fis);
		int number = Integer.parseInt(prop.getProperty("number"));
		return number;
	}
	public int MaxArrays(int[] a) {
		int max= a[0];
		for(int i=0;i<a.length;i++) {
			if(a[i]>max) {
				max= a[i];
			}
		}
		return max;
	}
	
	static List<DinhMuc> dinhMucs = new ArrayList<>();
	public int tinhTienTheoSoDien(int soDien) {
		int soTien = 0;
		int[] arr= new int[dinhMucs.size()];
		int[] arr1= new int[dinhMucs.size()];
		for (int i = 0; i < dinhMucs.size(); i++) {
			DinhMuc dinhMuc = dinhMucs.get(i);
			if(soDien > dinhMuc.from && soDien > dinhMuc.to) {
				soTien += (dinhMuc.to - dinhMuc.from + 1) * dinhMuc.price;
			}
			if(soDien > dinhMuc.from && soDien < dinhMuc.to) {
				int duRa = soDien - dinhMuc.from + 1;
				soTien += duRa * dinhMuc.price;
			}
			arr[i]=dinhMuc.to;
			arr1[i]=dinhMuc.price;
		}
		if(soDien>MaxArrays(arr)) {
			soTien+= (soDien-MaxArrays(arr))*(MaxArrays(arr1)+500);
		}
//		System.out.println(new Example1().MaxArrays(arr));
		return soTien;
	}
	
	


	public static void main(String[] args) throws Exception {

		dinhMucs.add(new DinhMuc(1, 100, 1000));
		dinhMucs.add(new DinhMuc(101, 150, 1500));
		dinhMucs.add(new DinhMuc(151, 200, 2000));
		dinhMucs.add(new DinhMuc(201, 250, 2500));
		dinhMucs.add(new DinhMuc(251, 300, 3000));
		int soDien = new Example1().LaySoDien();
		int a = new Example1().tinhTienTheoSoDien(soDien);
		
		System.out.println("So tien la = " + a);
	}
}
