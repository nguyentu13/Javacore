package edu.fa;

public class WrapperClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int year = 2017;
		Integer number = year;
		year= number;
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);
		String y ="2017";
		System.out.println(Integer.parseInt(y));
		year = Integer.parseInt(y);
		long l = Integer.MAX_VALUE + 1l;
		System.out.println(l);
	}

}
