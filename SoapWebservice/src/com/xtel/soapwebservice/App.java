package com.xtel.soapwebservice;

import java.util.concurrent.Executors;

import javax.xml.ws.Endpoint;

import com.xtel.soapwebservice.implement.ScheduleServiceImpl;
import com.xtel.soapwebservice.process.ConsumerManager;

public class App {
	 public static final String WS_URL = "http://localhost:8080/ws/schedules";
	 
	    public static void main(String[] args) {
	    	ConsumerManager.getInstance().start();
	        Endpoint endpoint = Endpoint.publish(WS_URL, ScheduleServiceImpl.getInstance());
	        endpoint.setExecutor(Executors.newFixedThreadPool(15));
	    }
}
