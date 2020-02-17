package com.xtel.webservice;

import java.util.concurrent.Executors;

import javax.xml.ws.Endpoint;
import com.xtel.webservice.implement.ScheduleServiceImpl;
import com.xtel.webservice.process.ConsumerManager;

public class App {
	 public static final String WS_URL = "http://192.168.1.122:8080/ws/chedules";
	 
	    public static void main(String[] args) {
	    	ConsumerManager.getInstance().start();
	        Endpoint endpoint = Endpoint.publish(WS_URL, ScheduleServiceImpl.getInstance());
	        endpoint.setExecutor(Executors.newFixedThreadPool(15));
	    }
}
