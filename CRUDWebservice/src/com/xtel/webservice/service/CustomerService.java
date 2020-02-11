package com.xtel.webservice.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.xtel.webservie.entiy.Customer;


@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface CustomerService {
	@WebMethod
	public void insert(Customer customer) throws Exception;
}
