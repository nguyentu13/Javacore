package com.xtel.webservice.log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log {
	private final String path = new File("resources/log4j.properties").getAbsolutePath();

	private static Logger logger;

	public Logger getLogger(Class<?> clazz) {
		logger = Logger.getLogger(clazz);
		try {
			PropertyConfigurator.configure(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return logger;
	}
}
