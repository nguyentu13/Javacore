package com.xtel.vngolf.api.controller.base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tbv.utils.db.DBLogic;
import com.tbv.utils.db.cmd.DbCommand;
import com.xtel.vngolf.api.config.CoreConfig;
import com.xtel.vngolf.api.listener.response.EnumRsCode;
import com.xtel.vngolf.api.main.MainApplication;

public class BaseProcessCmd {

	protected static Gson gson = new GsonBuilder().setDateFormat(CoreConfig.API_DATE_TIME_OUTPUT_FORMAT)
			.serializeNulls().create();
	protected final Log logger = LogFactory.getLog(this.getClass());
	protected static DBLogic dbCtrl = MainApplication.cmsDb.getDbCtrl();
	
	protected String transid;
	protected String channel;
	
	public BaseProcessCmd() {
		
	}
	
	public BaseProcessCmd(String transid, String channel) {
		this.transid = transid;
		this.channel = channel;
	}
	
	protected boolean executeDbCmd(DbCommand dbCmd) {
		
		try {
			dbCtrl.executeCommand(dbCmd);
		} catch (Exception e) {
			logger.warn(String.format("transid: %s, channel: %s, sql command: %s", 
					transid, channel, dbCmd.getSqlCommand()), e);
			return false;
		}
		
		if (dbCmd.getCode() == EnumRsCode.SUCCESS.getCode()) {
			return true;
		}

		return false;
	}

}
