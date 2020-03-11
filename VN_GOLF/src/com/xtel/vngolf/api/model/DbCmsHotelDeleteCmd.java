package com.xtel.vngolf.api.model;

import java.sql.Types;

import com.tbv.utils.db.cmd.DbCallableCmd;

public class DbCmsHotelDeleteCmd extends DbCallableCmd {
	private int hotel_id;

	public DbCmsHotelDeleteCmd(String transid, String channel, int hotel_id) {
		super(transid, channel);
		this.hotel_id = hotel_id;
	}

	@Override
	protected void getOutputResult() throws Exception {
		code = cst.getInt(1);
        message = cst.getString(2);
	}

	@Override
	protected void setSqlCommand() throws Exception {
		setProc("hotel_delete", 3);

	}

	@Override
	protected void setSqlParameter() throws Exception {
		cst.setInt(idx++, Types.INTEGER);
		cst.setInt(idx++, Types.VARCHAR);
		cst.setInt(idx++, hotel_id);
	}

}
