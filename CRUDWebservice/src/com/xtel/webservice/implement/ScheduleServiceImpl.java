package com.xtel.webservice.implement;

import javax.jws.WebService;

import com.xtel.webservice.cache.MyCache;
import com.xtel.webservice.entity.Schedule;
import com.xtel.webservice.model.Connector;
import com.xtel.webservice.queue.InsertQueue;
import com.xtel.webservice.service.ScheduleService;


@WebService(endpointInterface = "com.xtel.webservice.service.ScheduleService")
public class ScheduleServiceImpl implements ScheduleService {
	
	private static ScheduleServiceImpl instance= new ScheduleServiceImpl();
	private MyCache cache = MyCache.getInstance();
	
	public static ScheduleServiceImpl getInstance() {
		return instance;
	}

	@Override
	public void insert(Schedule schedule) throws Exception {
		InsertQueue.getInstance().put(schedule);
	}

	@Override
	public Schedule findbyCode(String code) throws Exception {
		Schedule schedule=null;
		if(cache.getScheduleFromCache(code)!=null) {
			return cache.getScheduleFromCache(code);
		} else {
			Connector con= new Connector();
			schedule = con.findByCode(code);
			cache.setScheduleToCache(code, schedule);
		}
		return schedule;
	}

	
	
	

}
