package com.xtel.crudservice.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface Service {
    @WebMethod
    public String add();

    @WebMethod
    public String update();

    @WebMethod
    public String cancel();
}
