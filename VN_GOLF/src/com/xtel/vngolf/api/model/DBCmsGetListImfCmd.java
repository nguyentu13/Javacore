package com.xtel.vngolf.api.model;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

import com.tbv.utils.db.cmd.DbCallableCmd;

public class DBCmsGetListImfCmd extends DbCallableCmd{

	private int owner_id;
	private String type;
	private int limit;
	ArrayList<String> listImg;
	
	
	public DBCmsGetListImfCmd(String transid, String channel, int owner_id, String type, int limit) {
		super(transid, channel);
		this.owner_id = owner_id;
		this.type = type;
		this.limit = limit;
		
	}

	@Override
	protected void getOutputResult() throws Exception {
		code = cst.getInt(1);
		message = cst.getString(2);
		ResultSet rs = null;
		listImg = new ArrayList<String>();
		try {
			rs = cst.getResultSet();
			while (rs.next()) {
				listImg.add(rs.getString(1));
			}
		} catch (Exception e) {
			
		}
	}

	@Override
	protected void setSqlCommand() throws Exception {
		setProc("list_img", 5);
		
	}

	@Override
	protected void setSqlParameter() throws Exception {
		cst.setInt(idx++, Types.INTEGER);
        cst.setInt(idx++, Types.VARCHAR);
        cst.setInt(idx++, owner_id);
		cst.setString(idx++, type);
		cst.setInt(idx++, limit);
		
	}

	public ArrayList<String> getListImg() {
		return listImg;
	}

	
	

}
