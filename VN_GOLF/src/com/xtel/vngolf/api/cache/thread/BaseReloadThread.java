package com.xtel.vngolf.api.cache.thread;

import com.tbv.utils.concurrent.ThreadWait;
import com.tbv.utils.db.DBLogic;
import com.tbv.utils.db.cmd.DbCommand;
import com.xtel.vngolf.api.main.MainApplication;

public class BaseReloadThread extends ThreadWait {

	protected String channel = "";
	
	public BaseReloadThread(String name, String taskName, long waitTime) {
		super(true, name, Thread.MAX_PRIORITY, taskName, waitTime);
	}
	
	public BaseReloadThread(String name, String taskName, long waitTime, String channel) {
		super(true, name, Thread.MAX_PRIORITY, taskName, waitTime);
		this.channel = channel;
	}
	
	protected static DBLogic dbCtrl = MainApplication.cmsDb.getDbCtrl();
	protected boolean executeDbCmd(DbCommand dbCmd) {

		try {
			dbCtrl.executeCommand(dbCmd);
			
			if (dbCmd.getCode() == 0) {
				return true;
			}
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
		}

		return false;
	}
	
	@Override
	protected void action() {
		
	}
}
