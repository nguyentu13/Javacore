package com.xtel.client;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import com.xtel.ws.OrderService;
import java.net.URL;
import java.util.Arrays;

public class GreetingClient {

    public static void main(String[] args) throws Exception {

        URL wsdlUrl = new URL("http://localhost:8888/service/order?wsdl");
        QName qname = new QName("http://ws.xtel.com/", "OrderServiceImplService");
        Service service = Service.create(wsdlUrl, qname);
        OrderService orderService = service.getPort(OrderService.class);

        System.out.println(Arrays.toString(orderService.getOrders()));
        System.out.println("Order completed: " + orderService.addOrder("Mother Board"));
        System.out.println("User count: " + orderService.getUserCount());
    }
}
