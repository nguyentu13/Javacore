package com.xtel.vngolf.api.listener.service.web;

import com.xtel.vngolf.api.controller.channels.core.ProductGetListCmd;
import com.xtel.vngolf.api.listener.service.BaseService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("view/product")
public class ProductService extends BaseService {
    @GET
    @Path("list")
    public Response getList(@QueryParam("transid") String transid,
                            @QueryParam("channel") String channel,
                            @QueryParam("page_index") int index,
                            @QueryParam("page_size") int size,
                            @QueryParam("category_id") int category_id){
        ProductGetListCmd cmd = new ProductGetListCmd(httpServletRequest,channel,transid,index,size,category_id);
        cmd.execute();
        return cmd.getResponse();
    }
}
