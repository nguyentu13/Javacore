package com.xtel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	@Value("${loda.mysql.url}")
	String mysqlUrl;
	
	  @Bean
	    DatabaseConnection mysqlConfigure() {
	        DatabaseConnection mySqlConnector = new MysqlConnection();
	        // Set Url
	        System.out.println("Config Mysql Url: " + mysqlUrl);
	        mySqlConnector.setUrl(mysqlUrl);
	        // Set username, password, format, v.v...
	        return mySqlConnector;
	    }
}
