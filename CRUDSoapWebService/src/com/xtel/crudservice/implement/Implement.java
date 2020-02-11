package com.xtel.crudservice.implement;

import com.xtel.crudservice.service.Service;

import javax.jws.WebService;

@WebService(endpointInterface = "com.xtel.crudservice.service.Service")
public class Implement implements Service {
    @Override
    public String add() {
        // queueu.add(customer);
        return null;
    }

    @Override
    public String update() {
        // code logic
        return null;
    }

    @Override
    public String cancel() {
        // code logic
        return null;
    }
}
