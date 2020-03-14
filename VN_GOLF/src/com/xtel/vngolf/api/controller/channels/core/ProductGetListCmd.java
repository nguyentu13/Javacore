package com.xtel.vngolf.api.controller.channels.core;

import com.xtel.vngolf.api.controller.base.AbsApiBaseParamReqTypeCmd;
import com.xtel.vngolf.api.listener.response.data.BasicPagingData;
import com.xtel.vngolf.api.model.DbProductGetListCmd;

import javax.servlet.http.HttpServletRequest;

public class ProductGetListCmd extends AbsApiBaseParamReqTypeCmd {
    private int index;
    private int size;
    private int id;

    public ProductGetListCmd(HttpServletRequest httpServletRequest, String channel, String transid,
                             int index, int size, int id) {
        super(httpServletRequest, channel, transid);
        this.index = index;
        this.size = size;
        this.id = id;
    }

    @Override
    protected void executeCmd() throws Exception {
        DbProductGetListCmd dbCmd = new DbProductGetListCmd(transid,channel,index,size,id);
        dbCmd.execute();
        setResponse(dbCmd.getCode(),dbCmd.getMessage(),new BasicPagingData(dbCmd.getPageInfo(),dbCmd.getList()));
    }

    protected boolean validateToken(){
        return true;
    }
}
