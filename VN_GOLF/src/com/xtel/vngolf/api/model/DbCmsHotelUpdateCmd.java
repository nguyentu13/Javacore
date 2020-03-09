package com.xtel.vngolf.api.model;

import java.sql.Types;

import com.tbv.utils.db.cmd.DbCallableCmd;
import com.xtel.vngolf.api.listener.request.CmsHotelUpdateReq;

public class DbCmsHotelUpdateCmd extends DbCallableCmd {
	private CmsHotelUpdateReq obj;

	public DbCmsHotelUpdateCmd(String transid, String channel, CmsHotelUpdateReq obj) {
		super(transid, channel);
		this.obj = obj;
	}

	@Override
	protected void getOutputResult() throws Exception {
		code = cst.getInt(1);
		message = cst.getString(2);	

	}

	@Override
	protected void setSqlCommand() throws Exception {
		setProc("hotel_update", 5);	

	}

	@Override
	protected void setSqlParameter() throws Exception {
		cst.setInt(idx++, Types.INTEGER);
		cst.setInt(idx++, Types.VARCHAR);
		cst.setString(idx++, obj.getName());
		cst.setString(idx++, obj.getDescription());
		cst.setInt(idx++, obj.getId());

	}

}
