package com.xtel;

import java.util.concurrent.TimeUnit;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

public class cache {
	public static void main(String[] args) {
		Cache<String, Customer> cache = Caffeine.newBuilder()
				  .expireAfterWrite(10, TimeUnit.MINUTES)
				  .maximumSize(10000)
				  .build();
		
		cache.put("a", new Customer(1,"aaaa"));
		
		Customer cus = cache.getIfPresent("a");
		System.out.println(cus.getName());
	}
}
