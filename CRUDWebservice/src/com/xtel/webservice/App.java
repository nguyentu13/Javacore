package com.xtel.webservice;

import java.util.concurrent.Executors;

import javax.xml.ws.Endpoint;
import com.xtel.webservice.implement.CustomerServiceImpl;
import com.xtel.webservice.process.ConsumerManager;

public class App {
	 public static final String WS_URL = "http://localhost:8080/ws/customers";
	 
	    public static void main(String[] args) {
	    	ConsumerManager.getInstance().start();
	        Endpoint endpoint = Endpoint.publish(WS_URL, CustomerServiceImpl.getInstance());
	        endpoint.setExecutor(Executors.newFixedThreadPool(15));
	    }
}
