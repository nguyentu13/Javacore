package com.xtel.webservice.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.xtel.webservie.entiy.Patient;


@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface PatientService {
	@WebMethod
	public void insert(Patient patient) throws Exception;
}
