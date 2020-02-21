package com.xtel.soapwebservice.config;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.xtel.soapwebservice.log.Log;


public class DatabaseConfiguration {
	private static DatabaseConfiguration instance = new DatabaseConfiguration();

	private DatabaseConfiguration() {}

	public static DatabaseConfiguration getInstance() {
		return instance;
	}

	private static String path = new File("resources/database-config.xml").getAbsolutePath();
	private static Logger logger = new Log().getLogger(DatabaseConfiguration.class);

	private Element readFileXml() {
		Document document = null;
		File file = new File(path);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;

		try {
			dBuilder = dbf.newDocumentBuilder();
			document = dBuilder.parse(file);
		} catch (Exception ex) {
			logger.info(ex);
		}

		return document.getDocumentElement();
	}

	public String getUrl() {
		return readFileXml().getElementsByTagName("url").item(0).getTextContent();
	}

	public String getUser() {
		return readFileXml().getElementsByTagName("user").item(0).getTextContent();
	}

	public String getPassword() {
		return readFileXml().getElementsByTagName("password").item(0).getTextContent();
	}
}
