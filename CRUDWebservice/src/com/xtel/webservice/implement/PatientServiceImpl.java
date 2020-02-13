package com.xtel.webservice.implement;

import javax.jws.WebService;

import com.xtel.webservice.queue.OrderQueue;
import com.xtel.webservice.service.PatientService;
import com.xtel.webservie.entiy.Patient;


@WebService(endpointInterface = "com.xtel.webservice.service.PatientService")
public class PatientServiceImpl implements PatientService {
	
	private static PatientServiceImpl instance= new PatientServiceImpl();
	
	public static PatientServiceImpl getInstance() {
		return instance;
	}

	@Override
	public void insert(Patient patient) throws Exception {
		OrderQueue.getInstance().put(patient);
	}

}
