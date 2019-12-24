package edu.fa;

import java.util.Arrays;
import java.util.Scanner;

public class AAAAAAAAA {
	public static void main(String[] args) {
		int [] arr= {1,3,4,5,6,5,2};
		int k ;
		System.out.println(Arrays.toString (arr));
		Scanner sc = new Scanner(System.in);
		System.out.print("Xoa phan tu thu : ");
		k=sc.nextInt();
		for(int i=0;i<arr.length;i++) {
			if((i+1)!=k) {
				System.out.print(arr[i]+"\t");
			}
		}
		sc.close();
	}
}
