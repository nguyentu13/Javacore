package com.xtel.webservice.cache;

import java.util.HashMap;
import java.util.Map;

import com.xtel.webservice.entity.Schedule;

public class MyCache {
	private static MyCache ins = new MyCache();
	private Map<String, Schedule> cache = new HashMap<>();

	public static MyCache getInstance() {
		return ins;
	}

	private MyCache() {

	}
	public void setScheduleToCache(String key, Schedule value) {
		cache.put(key, value);
	}
	
	public Schedule getScheduleFromCache(String key) {		
		return cache.get(key);
		
	}

}
