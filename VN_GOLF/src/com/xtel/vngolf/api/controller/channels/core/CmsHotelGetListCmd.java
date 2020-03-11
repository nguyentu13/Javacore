package com.xtel.vngolf.api.controller.channels.core;

import javax.servlet.http.HttpServletRequest;

import com.xtel.vngolf.api.controller.base.AbsApiBaseParamReqTypeCmd;
import com.xtel.vngolf.api.listener.response.data.BasicPagingData;
import com.xtel.vngolf.api.model.DbCmsHotelGetListCmd;

public class CmsHotelGetListCmd extends AbsApiBaseParamReqTypeCmd{
	private int page_index;
	private int page_size;
	private String order_by;
	private String order_type;

	public CmsHotelGetListCmd(HttpServletRequest httpServletRequest, String channel,
							  String transid,int page_index,int page_size,
							  String order_by,String order_type) {
		super(httpServletRequest, channel, transid);
		this.page_index = page_index;
		this.page_size = page_size;
		this.order_by = order_by;
		this.order_type = order_type;
	}

	@Override
	protected void executeCmd() throws Exception {
		DbCmsHotelGetListCmd dbCmd = new DbCmsHotelGetListCmd(transid,channel,page_index,page_size,order_by,order_type);
		executeDbCmd(dbCmd);
		setResponse(dbCmd.getCode(),dbCmd.getMessage(),new BasicPagingData(dbCmd.getPageInfo(),dbCmd.getList()));
	}
	
	protected boolean validateToken() {
		return true;
	}

}
