package edu.fa;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapSample {
	public static void main(String[] args) {
		Map<Integer, String> resultMap = new HashMap<>();
		resultMap.put(2017, "Good");
		resultMap.put(2018, "Excellent");
		resultMap.put(2019, "Good");
		
		System.out.println(resultMap.size());
		String result = resultMap.get(2017);
		System.out.println(result);
		
		Set<Integer> keySet = resultMap.keySet();
		Iterator<Integer> iterator= keySet.iterator();
		Integer key =0;
		
		while(iterator.hasNext()) {
			key= iterator.next();
			System.out.println(key + " : " + resultMap.get(key));
		}
	}
}
