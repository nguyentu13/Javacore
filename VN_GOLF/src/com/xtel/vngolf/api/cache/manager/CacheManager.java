package com.xtel.vngolf.api.cache.manager;

import java.util.HashMap;

public class CacheManager {
	public static CacheManager _instance;

	public static synchronized CacheManager getInstance() {
		if (_instance == null) {
			_instance = new CacheManager();
		}
		return _instance;
	}
	
	public final Object syncReloadWeekDays = new Object();
	
	public HashMap<String, Integer> mapWeekDays = new HashMap<String, Integer>();

	public HashMap<String, Integer> getMapWeekDays() {
		synchronized (syncReloadWeekDays) {
			return mapWeekDays;
		}
	}

	public void setMapWeekDays(HashMap<String, Integer> _mapWeekDays) {
		synchronized (syncReloadWeekDays) {
			mapWeekDays = _mapWeekDays;
		}
	}
	
	
}
