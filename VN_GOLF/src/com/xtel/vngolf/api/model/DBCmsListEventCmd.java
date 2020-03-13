package com.xtel.vngolf.api.model;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;

import com.tbv.utils.db.cmd.DbPagingCmd;
import com.xtel.vngolf.api.listener.entities.CmsEvent;

public class DBCmsListEventCmd extends DbPagingCmd{
	private int page_index;
	private int page_size;
	private int lang_id;
	
	ArrayList<String> list_event;
	
	public DBCmsListEventCmd(String transid, String channel, int page_index, int page_size, int lang_id) {
		super(transid, channel);
		this.page_index = page_index;
		this.page_size = page_size;
		this.lang_id = lang_id;
		//---------------
		list_event = new ArrayList<String>();

		list_event.add("Event1");
		list_event.add("event2");
		list_event.add("event3");
		list_event.add("event4");
	}
	@Override
	protected void getOutputResult() throws Exception {
		
	}
	
	@Override
	protected void setSqlCommand() throws Exception {
		setProc("getListEvent", 5);
		
	}
	@Override
	protected void setSqlParameter() throws Exception {
		
		cst.setInt(idx++, Types.INTEGER);
		cst.setInt(idx++, Types.VARCHAR);
		cst.setInt(idx++, 1);
		cst.setInt(idx++, 2);
		cst.setInt(idx++, 1);
	}
	public ArrayList<String> getList_event() {
		return list_event;
	}
	public void setList_event(ArrayList<String> list_event) {
		this.list_event = list_event;
	}

	
	
}
