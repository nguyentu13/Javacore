package edu.fa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GenericColection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> yearList= new ArrayList<>();
		yearList.add(2017);
		yearList.add(2018);
		yearList.add(2019);
		
		Integer number=yearList.get(0);
		
		for(Integer i : yearList) {
			System.out.println(i);
		}
		
	}

}
