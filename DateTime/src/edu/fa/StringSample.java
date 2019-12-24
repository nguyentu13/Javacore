package edu.fa;

public class StringSample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1="Fresher";
		System.out.println(s1);
		String s2= new String("Academy");
		System.out.println(s2);
		StringSample ss= new StringSample();
		System.out.println(ss.toString());
		
		String year="2017";
		Integer.parseInt(year);
		
		System.out.println(s1.toUpperCase());
		System.out.println(s1.toLowerCase());
		System.out.println(s1.substring(3, 6));
		System.out.println(s1.substring(5));
		String[] split= s1.split("e");
		System.out.println(split.length);
		System.out.println(split[2]);
		
		System.out.println(s1);
		
	}

	@Override
	public String toString() {
		return "This is a String Sample";
	}
	

}
