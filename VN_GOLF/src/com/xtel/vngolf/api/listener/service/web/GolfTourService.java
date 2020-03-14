package com.xtel.vngolf.api.listener.service.web;

import com.xtel.vngolf.api.controller.channels.core.GolfTourGetListCmd;
import com.xtel.vngolf.api.listener.service.BaseService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("view/golf_tour")
public class GolfTourService extends BaseService {
    @GET
    @Path("list")
    public Response getList(@QueryParam("transid") String transid,
                            @QueryParam("channel") String channel,
                            @QueryParam("page_index") int page_index,
                            @QueryParam("page_size") int page_size,
                            @QueryParam("order_by") String order_by,
                            @QueryParam("order_type") String order_type,
                            @QueryParam("tour_name") String tour_name,
                            @QueryParam("duration_time") float duration_time,
                            @QueryParam("address") String address,
                            @QueryParam("price") double price){
        GolfTourGetListCmd cmd = new GolfTourGetListCmd(httpServletRequest,channel,transid,page_index,page_size,
                order_by,order_type,tour_name,duration_time,address,price);
        cmd.execute();
        return cmd.getResponse();
    }
}
