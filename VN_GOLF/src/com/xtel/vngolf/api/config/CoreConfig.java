package com.xtel.vngolf.api.config;

import com.tbv.utils.config.base.AbsConfigUtils;

/**
 * @author trongbv
 * @version 1.0
 * @created 20-JUL-2017 12:25:41
 */
public class CoreConfig extends AbsConfigUtils {

	public static CoreConfig instance;

	public CoreConfig(String configFile, boolean autoReload, long reload_delay_ms) {
		super(configFile, autoReload, reload_delay_ms);
	}

	public static synchronized CoreConfig createInstance(String configFile, boolean autoReload, long reload_delay_ms) {
		if (instance == null) {
			instance = new CoreConfig(configFile, autoReload, reload_delay_ms);
		}
		return instance;
	}

	public static int API_PORT = 9000;
	public static String CONTEXT_PATH = "/api";
	public static String API_DAY_INPUT_FORMAT = "dd-MM-yyyy";
	public static String API_DATE_TIME_INPUT_FORMAT = "dd-MM-yyyy_HH:mm:ss";
	public static String API_DATE_TIME_OUTPUT_FORMAT = "dd/MM/yyyy HH:mm:ss";
	
	public static String ORDER_URL_API = "http://localhost:10401/api";
	public static String CARD_URL_API = "http://222.252.16.140:10403/card/partner/api";
	
	public static int GH_CONNECT_TIMEOUT_MS = 20000;
	public static int GH_READ_TIMEOUT_MS = 20000;
	public static int GH_CLIENT_POOL_SIZE = 200;
	public static int GH_RETRY_PUSH_RESULT = 5;
	public static String GH_RESPONSE_SUCCESS = "0";
	public static String GH_RESPONSE_NOT_RECEIVE = "254";
	
	public static String DEFAULT_REPLACE_NULL_STRING_VALUE = "all";

	public static int TOKEN_LOG_MAX_LENGTH = 50;
	public static int RESPONSE_LOG_MAX_LENGTH = 500;

	public static int URL_MESSAGE_SENDER_TIMEOUT = 20000;

	@Override
	public void readProperties() throws Exception {

		API_PORT = xmlConfig.getInt("API_PORT", API_PORT);
		CONTEXT_PATH = xmlConfig.getString("CONTEXT_PATH", CONTEXT_PATH);
		API_DAY_INPUT_FORMAT = xmlConfig.getString("API_DAY_INPUT_FORMAT", API_DAY_INPUT_FORMAT);
		API_DATE_TIME_INPUT_FORMAT = xmlConfig.getString("API_DATE_TIME_INPUT_FORMAT", API_DATE_TIME_INPUT_FORMAT);
		API_DATE_TIME_OUTPUT_FORMAT = xmlConfig.getString("API_DATE_TIME_OUTPUT_FORMAT", API_DATE_TIME_OUTPUT_FORMAT);

		TOKEN_LOG_MAX_LENGTH = xmlConfig.getInt("TOKEN_LOG_MAX_LENGTH", TOKEN_LOG_MAX_LENGTH);
		RESPONSE_LOG_MAX_LENGTH = xmlConfig.getInt("RESPONSE_LOG_MAX_LENGTH", RESPONSE_LOG_MAX_LENGTH);
		URL_MESSAGE_SENDER_TIMEOUT = xmlConfig.getInt("URL_MESSAGE_SENDER_TIMEOUT", URL_MESSAGE_SENDER_TIMEOUT);
		
		ORDER_URL_API = xmlConfig.getString("ORDER_URL_API", ORDER_URL_API);
		CARD_URL_API = xmlConfig.getString("CARD_URL_API", CARD_URL_API);

	}

	@Override
	public void reloadProcess() throws Exception {

	}

}
