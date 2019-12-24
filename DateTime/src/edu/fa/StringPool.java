package edu.fa;

public class StringPool {

	public static void main(String[] args) {
		// Luu trong String Pool
		String s1="Fresher";
		String s2="Fresher";
		
		String s3="Academy";
		System.out.println(s1 + s3);
		//Luu trong Heap
		String s4= new String("Fresher");
		//chuyen tu Heap vao Pool
		System.out.println(s4.intern());
		
		//== so sanh ca gia tri va vi tri trong bo nho
		System.out.println(s1==s2);
		System.out.println(s4==s1);
		System.out.println(s1.hashCode());
		System.out.println(System.identityHashCode(s1));
		System.out.println(System.identityHashCode(s4));
		
		//equals chi so sanh gia tri
		System.out.println(s1.equals(s2));
		System.out.println(s1.equals(s4));
		
		String mango = "mango";
		String mango2 = "mango";
		System.out.println(mango != mango2);
		System.out.println(mango == mango2);
	}

}
