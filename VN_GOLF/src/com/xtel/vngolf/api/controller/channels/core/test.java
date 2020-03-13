package com.xtel.vngolf.api.controller.channels.core;

import com.xtel.vngolf.api.model.DBCmsListEventCmd;

public class test {

	public static void main(String[] args) {
		System.out.println("-----------");
		DBCmsListEventCmd dbCmd = new DBCmsListEventCmd(null,null,1,1,1);
		System.out.println(dbCmd.getList_event().get(1));

	}

}
