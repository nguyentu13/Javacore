package com.xtel.restful.cache;

import java.util.HashMap;
import java.util.Map;

import com.xtel.restful.entity.Product;

public class MyCache {
	private static MyCache instance = new MyCache();
	private Map<String, Product> cache = new HashMap<>();

	public static MyCache getInstance() {
		return instance;
	}

	public MyCache() {

	}
	
	public void setProductToCache(String key, Product value) {
		cache.put(key, value);
	}
	
	public Product getProductFromCache(String key) {		
		return cache.get(key);
		
	}
}
