package com.xtel.vngolf.api.model;

import java.sql.Types;

import com.tbv.utils.db.cmd.DbCallableCmd;


public class DbCmsHelloWorldCmd extends DbCallableCmd{
	private String username;
	
	private String result;
	public DbCmsHelloWorldCmd(String transid, String channel,String username) {
		super(transid, channel);
		this.username  = username;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void getOutputResult() throws Exception {
		// TODO Auto-generated method stub
		code = cst.getInt(1);
		message = cst.getString(2);
		result = cst.getString(4);
	}

	@Override
	protected void setSqlCommand() throws Exception {
		// TODO Auto-generated method stub
		setProc("hello_world", 4);
	}

	@Override
	protected void setSqlParameter() throws Exception {
		cst.registerOutParameter(idx++, Types.INTEGER);
		cst.registerOutParameter(idx++, Types.VARCHAR);
		
		cst.setString(idx++, username);
		cst.registerOutParameter(idx++, Types.VARCHAR);
	}

	public String getResult() {
		return result;
	}

	@Override
	public String toString() {
		return "DbCmsHelloWorldCmd [username=" + username + ", result=" + result + "]";
	}

}
