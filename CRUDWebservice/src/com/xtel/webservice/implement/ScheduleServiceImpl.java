package com.xtel.webservice.implement;

import javax.jws.WebService;

import com.xtel.webservice.queue.InsertQueue;
import com.xtel.webservice.service.ScheduleService;
import com.xtel.webservie.entiy.Schedule;


@WebService(endpointInterface = "com.xtel.webservice.service.ScheduleService")
public class ScheduleServiceImpl implements ScheduleService {
	
	private static ScheduleServiceImpl instance= new ScheduleServiceImpl();
	
	public static ScheduleServiceImpl getInstance() {
		return instance;
	}

	@Override
	public void insert(Schedule schedule) throws Exception {
		InsertQueue.getInstance().put(schedule);
	}

	@Override
	public Schedule findbyCode() throws Exception {
		return null;
	}
	
	

}
