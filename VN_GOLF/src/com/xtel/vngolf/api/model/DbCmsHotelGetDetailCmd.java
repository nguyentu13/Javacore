package com.xtel.vngolf.api.model;

import java.sql.ResultSet;
import java.sql.Types;

import com.tbv.utils.db.cmd.DbCallableCmd;
import com.xtel.vngolf.api.listener.entities.CmsHotel;

public class DbCmsHotelGetDetailCmd extends DbCallableCmd{
	private int id;
	private CmsHotel obj;

	public DbCmsHotelGetDetailCmd(String transid, String channel,int id) {
		super(transid, channel);
		this.id=id;
	}

	@Override
	protected void getOutputResult() throws Exception {
		code = cst.getInt(1);
        message = cst.getString(2);
        if(code!=0) {
        	return;
        }
        ResultSet rs = cst.getResultSet();;
        if(rs!=null) {
        	 try{
                 while(rs.next()){
                     obj = new CmsHotel();
                     obj.setId(rs.getInt("id"));
                     obj.setName(rs.getString("name"));
                     obj.setDescription(rs.getString("description"));
                     break;
                 }
             }
             finally {            
                     rs.close();  
             }
        }
       
		
	}

	@Override
	protected void setSqlCommand() throws Exception {
		setProc("hotel_detail", 3);
		
	}

	@Override
	protected void setSqlParameter() throws Exception {
		cst.setInt(idx++, Types.INTEGER);
		cst.setInt(idx++, Types.VARCHAR);
		cst.setInt(idx++, id);
		
	}

	public CmsHotel getObj() {
		return obj;
	}

	@Override
	public String toString() {
		return "DbCmsHotelGetDetailCmd{" +
                "id=" + id +
                ", obj=" + obj.toString() +
                '}';
	}
	
	
}
