package com.xtel.vngolf.api.model;

import java.sql.Types;

import com.tbv.utils.db.cmd.DbCallableCmd;
import com.xtel.vngolf.api.listener.request.CmsHotelAddReq;

public class DbCmsHotelAddCmd extends DbCallableCmd{
	private CmsHotelAddReq obj;
	
	public DbCmsHotelAddCmd(String transid, String channel,CmsHotelAddReq obj) {
		super(transid, channel);
		this.obj=obj;
	}

	@Override
	protected void getOutputResult() throws Exception {
		code = cst.getInt(1);
		message = cst.getString(2);			
	}

	@Override
	protected void setSqlCommand() throws Exception {
		setProc("hotel_insert", 4);		
	}

	@Override
	protected void setSqlParameter() throws Exception {
		cst.setInt(idx++, Types.INTEGER);
		cst.setInt(idx++, Types.VARCHAR);
		cst.setString(idx++, obj.getName());
		cst.setString(idx++, obj.getDescription());
		
	}
	
}
