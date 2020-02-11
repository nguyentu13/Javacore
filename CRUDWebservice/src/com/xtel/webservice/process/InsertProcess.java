package com.xtel.webservice.process;

import com.xtel.webservice.model.Connector;
import com.xtel.webservice.queue.OrderQueue;
import com.xtel.webservie.entiy.Customer;

public class InsertProcess extends AbsThread {

	@Override
	protected void execute() {
		Customer customer = OrderQueue.getInstance().take();
		Connector connector= new Connector();
		try {
			connector.insertCustomer(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
