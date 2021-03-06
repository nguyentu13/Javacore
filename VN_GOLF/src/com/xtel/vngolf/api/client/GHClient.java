package com.xtel.vngolf.api.client;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.xtel.vngolf.api.config.CoreConfig;

public class GHClient {

	protected final Log logger = LogFactory.getLog(this.getClass());

	public static RestFulClientHelper clientHelper;
	
//	public java.util.Map<String, RestFulClientHelper> mapClientHelper = new HashMap<String, RestFulClientHelper>();

	public void init() {

		clientHelper = new RestFulClientHelper();
		try {
			clientHelper.init(CoreConfig.ORDER_URL_API, CoreConfig.GH_CONNECT_TIMEOUT_MS, CoreConfig.GH_READ_TIMEOUT_MS,
					CoreConfig.GH_CLIENT_POOL_SIZE);
		} catch (Exception e) {
			logger.warn("", e);
		}
	}

	public RestFulClientHelper getClientHelper() {
		return clientHelper;
	}

	public RestFulClientHelper getClientHelper(String uri) {
		try {
			clientHelper.setUrl(uri);
		} catch (Exception e) {
			logger.warn("", e);
		}
		return clientHelper;
	}
}
