package com.xtel.webservice.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.xtel.webservice.entity.Schedule;


@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ScheduleService {
	@WebMethod
	public void insert(Schedule schedule) throws Exception;
	
	@WebMethod
	public Schedule findbyCode(String code) throws Exception;

}
