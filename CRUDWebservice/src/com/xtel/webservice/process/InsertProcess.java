package com.xtel.webservice.process;

import com.xtel.webservice.model.Connector;
import com.xtel.webservice.queue.OrderQueue;
import com.xtel.webservie.entiy.Patient;

public class InsertProcess extends AbsThread {

	@Override
	protected void execute() {
		Patient patient = OrderQueue.getInstance().take();
		Connector connector= new Connector();
		try {
			connector.insertCustomer(patient);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
