package com.xtel;

import java.io.File;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class App {
	public static Logger logger ;
	public static void main(String[] args) {
		
		File fileXML = new File("./config/log4j.xml");
        DOMConfigurator.configureAndWatch(fileXML.getPath());
        logger = LogManager.getLogger(App.class);
        logger.error("loi");
	}

  }

