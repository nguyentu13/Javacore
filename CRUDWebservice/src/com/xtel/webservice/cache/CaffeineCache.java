package com.xtel.webservice.cache;

import java.util.concurrent.TimeUnit;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.xtel.webservie.entiy.Schedule;

public class CaffeineCache {
	private static CaffeineCache ins = new CaffeineCache();

	private Cache<String, Schedule> ordercache;

	public static CaffeineCache getInstance() {
		return ins;
	}

	private CaffeineCache() {
		Cache<String, Schedule> cache = Caffeine.newBuilder().expireAfterWrite(10, TimeUnit.MINUTES).maximumSize(10000)
				.build();
	}

	public void setOrderCache(Cache<String, Schedule> ordercache) {
		this.ordercache = ordercache;
	}

	public Cache<String, Schedule> getOrderFromCache() {
		return ordercache;

	}
}
