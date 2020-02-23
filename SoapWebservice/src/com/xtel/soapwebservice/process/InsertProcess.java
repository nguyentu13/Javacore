package com.xtel.soapwebservice.process;

import org.apache.log4j.Logger;

import com.xtel.soapwebservice.entity.Schedule;
import com.xtel.soapwebservice.log.Log;
import com.xtel.soapwebservice.model.Connector;
import com.xtel.soapwebservice.queue.InsertQueue;


public class InsertProcess extends AbsThread {
	private Logger logger = new Log().getLogger(InsertProcess.class);

	@Override
	protected void execute() {
		Schedule schedule = InsertQueue.getInstance().take();
		Connector connector= new Connector();
		try {
			connector.insertSchedule(schedule);
		} catch (Exception e) {
			logger.warn(e);
		}
	}
	
}
