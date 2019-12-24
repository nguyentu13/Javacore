package edu.fa;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetSample {
	public static void main(String[] args) {
		Set<Integer> set= new HashSet<>();
		set.add(2017);
		set.add(2018);
		set.add(2019);
		
		System.out.println(set.size());
		
		set.add(2017);
		
		Iterator<Integer> iterator= set.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
}
