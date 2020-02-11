package com.xtel.restful.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import com.xtel.restful.logging.Log;




public class Connector {
	private static final String url= "jdbc:mysql://localhost/tu";
    private static final String user = "root";
    private static final String password = "12111993";
    
    private Logger logger = new Log().getLogger(Connector.class);

    public Connection getConnection(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url, user, password);
//            System.out.println("Connected!!");
        } catch (SQLException e) {
            logger.debug(e);
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
            logger.debug(ex);
        }
        finally{
            try {
                conn.close();
            } catch (SQLException e) {
                logger.debug(e);
            }
        }

        return isDisconected;
    }

}
