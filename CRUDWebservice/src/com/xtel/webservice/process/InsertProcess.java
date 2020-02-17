package com.xtel.webservice.process;

import org.apache.log4j.Logger;

import com.xtel.webservice.log.Log;
import com.xtel.webservice.model.Connector;
import com.xtel.webservice.queue.InsertQueue;
import com.xtel.webservie.entiy.Schedule;


public class InsertProcess extends AbsThread {
	private Logger logger = new Log().getLogger(InsertProcess.class);

	@Override
	protected void execute() {
		Schedule schedule = InsertQueue.getInstance().take();
		Connector connector= new Connector();
//		System.out.println(schedule.toString());
		try {
			connector.insertSchedule(schedule);
		} catch (Exception e) {
			logger.debug(e);
		}
	}
	
}
