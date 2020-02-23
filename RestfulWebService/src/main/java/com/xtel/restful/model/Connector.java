package com.xtel.restful.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.xtel.restful.config.DatabaseConfiguration;
import com.xtel.restful.logging.Log;

public class Connector {
	private final static DatabaseConfiguration configrator = DatabaseConfiguration.getInstance();
	
	private static final String url= configrator.getUrl();
    private static final String user = configrator.getUser();
    private static final String password = configrator.getPassword();
    
    private Logger logger = new Log().getLogger(Connector.class);

    public Connection getConnection(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url, user, password);
//            System.out.println("Connected!!");
        } catch (SQLException e) {
            logger.warn(e);
        }
        return conn;
    }

    public boolean isDisconnected(){
        boolean isDisconected = false;

        Connection conn = getConnection();
        String sql = "SELECT 1 FROM DUAL";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(!rs.next()){
                isDisconected = true;
            }
        }
        catch (Exception ex){
            logger.warn(ex);
        }
        finally{
            try {
                conn.close();
            } catch (SQLException e) {
                logger.warn(e);
            }
        }

        return isDisconected;
    }

}
