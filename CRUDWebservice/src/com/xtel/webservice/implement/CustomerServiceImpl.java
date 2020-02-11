package com.xtel.webservice.implement;

import javax.jws.WebService;

import com.xtel.webservice.queue.OrderQueue;
import com.xtel.webservice.service.CustomerService;
import com.xtel.webservie.entiy.Customer;


@WebService(endpointInterface = "com.xtel.webservice.service.CustomerService")
public class CustomerServiceImpl implements CustomerService {
	
	private static CustomerServiceImpl instance= new CustomerServiceImpl();
	
	public static CustomerServiceImpl getInstance() {
		return instance;
	}

	@Override
	public void insert(Customer customer) throws Exception {
		OrderQueue.getInstance().put(customer);
	}

}
