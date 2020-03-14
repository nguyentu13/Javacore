package com.xtel.vngolf.api.controller.channels.core;

import com.xtel.vngolf.api.controller.base.AbsApiBaseParamReqTypeCmd;
import com.xtel.vngolf.api.listener.response.data.BasicPagingData;
import com.xtel.vngolf.api.model.DbGolfTourGetListCmd;

import javax.servlet.http.HttpServletRequest;

public class GolfTourGetListCmd extends AbsApiBaseParamReqTypeCmd {
    private int page_index;
    private int page_size;
    private String order_by;
    private String order_type;
    private String tour_name;
    private float duration_time;
    private String address;
    private double price;

    public GolfTourGetListCmd(HttpServletRequest httpServletRequest, String channel, String transid,int page_index,
                              int page_size,String order_by,String order_type,String tour_name,float duration_time,
                              String address,double price) {
        super(httpServletRequest, channel, transid);
        this.page_index = page_index;
        this.page_size = page_size;
        this.order_by = order_by;
        this.order_type = order_type;
        this.tour_name  = tour_name;
        this.duration_time = duration_time;
        this.address = address;
        this.price = price;
    }

    @Override
    protected void executeCmd() throws Exception {
        DbGolfTourGetListCmd dbCmd = new DbGolfTourGetListCmd(transid,channel,page_index,page_size,order_by,order_type,
                tour_name,duration_time,address,price);
        dbCmd.execute();
        setResponse(dbCmd.getCode(),dbCmd.getMessage(),new BasicPagingData(dbCmd.getPageInfo(),dbCmd.getList()));
    }

    protected boolean validateToken(){
        return true;
    }
}
