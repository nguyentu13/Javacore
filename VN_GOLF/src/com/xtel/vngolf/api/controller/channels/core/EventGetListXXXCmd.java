package com.xtel.vngolf.api.controller.channels.core;

import com.tbv.utils.textbase.StringUtils;
import com.xtel.vngolf.api.controller.base.AbsApiBaseParamReqTypeCmd;
import com.xtel.vngolf.api.listener.response.data.BasicPagingData;
import com.xtel.vngolf.api.model.DbEventGetListXXXCmd;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class EventGetListXXXCmd extends AbsApiBaseParamReqTypeCmd {
    private int page_index;
    private int page_size;
    private String order_by;
    private String order_type;
    private int category_id;
    private String date;
    private String author;
    private Date date_xx;
    private int language_id;

    public EventGetListXXXCmd(HttpServletRequest httpServletRequest, String channel, String transid,
                              int page_index,int page_size,String order_by,String order_type,
                              int category_id,String date,String author,int language_id) {
        super(httpServletRequest, channel, transid);
        this.page_index = page_index;
        this.page_size = page_size;
        this.order_by = order_by;
        this.order_type = order_type;
        this.category_id = category_id;
        this.date = date;
        this.author = author;
        this.language_id = language_id;
    }

    @Override
    protected void executeCmd() throws Exception {
        DbEventGetListXXXCmd dbCmd = new DbEventGetListXXXCmd(transid,channel,page_index,page_size,order_by,order_type,
                category_id,author,date_xx,language_id);
        executeDbCmd(dbCmd);
        setResponse(dbCmd.getCode(),dbCmd.getMessage(),new BasicPagingData(dbCmd.getPageInfo(),dbCmd.getList()));
    }

    protected boolean validateToken(){
        return true;
    }

    protected boolean validateData(){

        if(language_id == 0){
            return false;
        }

        if(!StringUtils.isNullOrEmpty(date)){
            date_xx = parseToDate(date);
            if(date_xx == null) return false;
        }

        return true;
    }
}
