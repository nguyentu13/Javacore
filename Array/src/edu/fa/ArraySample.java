package edu.fa;

public class ArraySample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] words=new String[3];
		System.out.println(words.length);
		String[] languages= {"English","Vietnamese"};
		System.out.println(languages.length);
		String[] terms = words;
		System.out.println(terms.length);
		
		String [][] wordMatrix=new String[3][2];
		System.out.println(wordMatrix[0].length);
		System.out.println(wordMatrix[2].length);
		System.out.println(wordMatrix[0].length);
	}

}
