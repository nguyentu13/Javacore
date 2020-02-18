package com.xtel.webservice.process;

import org.apache.log4j.Logger;

import com.xtel.webservice.entity.Schedule;
import com.xtel.webservice.log.Log;
import com.xtel.webservice.model.Connector;
import com.xtel.webservice.queue.InsertQueue;


public class InsertProcess extends AbsThread {
	private Logger logger = new Log().getLogger(InsertProcess.class);

	@Override
	protected void execute() {
		Schedule schedule = InsertQueue.getInstance().take();
		Connector connector= new Connector();
		try {
			connector.insertSchedule(schedule);
		} catch (Exception e) {
			logger.debug(e);
		}
	}
	
}
