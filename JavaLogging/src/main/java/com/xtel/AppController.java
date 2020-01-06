package com.xtel;


import org.apache.log4j.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class AppController {
	  private static final Logger LOGGER = LogManager.getLogger(AppController.class);
	  
	    public static void main(String[] args) {
	    	String path = new File("src/main/resources/log4j.properties").getAbsolutePath();
	    	FileInputStream in;
			try {
				in = new FileInputStream(path);
				Properties prop = new Properties();
				try {
					prop.load(in);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(prop.getProperty("log4j.rootLogger"));
//				prop.setProperty("aaaa", "bbbbbbb");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	try {
				 in= new FileInputStream(path);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        LOGGER.debug("Debug log message");
	        LOGGER.fatal("Fatal log message");
	    }
	 
}
