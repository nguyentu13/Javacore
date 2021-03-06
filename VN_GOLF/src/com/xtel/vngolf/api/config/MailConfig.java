package com.xtel.vngolf.api.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.tbv.utils.config.base.AbsConfigUtils;
import com.tbv.utils.textbase.StringUtil;
import com.xtel.vngolf.api.common.mail.FromMailEntity;
import com.xtel.vngolf.api.common.mail.MailConfigEntity;

/**
 * @author trongbv
 * @version 1.0
 * @created 12-Oct-2016 12:25:41
 */
public class MailConfig extends AbsConfigUtils {

	public static MailConfig instance;
	
	public MailConfig(String configFile, boolean autoReload, long reload_delay_ms) {
		super(configFile, autoReload, reload_delay_ms);
	}
	
	public static synchronized MailConfig createInstance(String configFile, boolean autoReload, long reload_delay_ms) {
		if (instance == null) {
			instance = new MailConfig(configFile, autoReload, reload_delay_ms);
		}
		return instance;
	}
	
	public static MailConfigEntity mailSetupConfig;
	public static List<FromMailEntity> froms;
	
	@Override
	public void readProperties() throws Exception {
		
		String HOST = xmlConfig.getString(String.format("MAIL(%d).HOST", 0));
		int PORT = xmlConfig.getInt(String.format("MAIL(%d).PORT", 0));
		boolean SSL = xmlConfig.getBoolean(String.format("MAIL(%d).SSL", 0));
		String PROTOCOL = xmlConfig.getString(String.format("MAIL(%d).PROTOCOL", 0));
		String FROM_PERSONAL = xmlConfig.getString(String.format("MAIL(%d).FROM_PERSONAL", 0));
		
		mailSetupConfig = new MailConfigEntity();
		mailSetupConfig.setHost(HOST);
		mailSetupConfig.setPort(PORT);
		mailSetupConfig.setSsl(SSL);
		mailSetupConfig.setProtocol(PROTOCOL);
		mailSetupConfig.setFromPersonal(FROM_PERSONAL);
		
		this.loadFroms();
	}
	
	private void loadFroms() {
		
		List<FromMailEntity> FROMS_TEMP = new ArrayList<FromMailEntity>();
		
		Object list = xmlConfig.getProperty(String.format("MAIL(%d).FROMS.FROM.FROM_MAIL", 0));
		
		int size = 1;
		if (list instanceof Collection) {	          
			size = ((Collection<?>) list).size();
		}

        logger.info("[" + Thread.currentThread().getId() + "] " + String.format("load config size FROMS: %s", size));
        
        if (size > 0) {
            for (int i = 0; i < size; i++) {            	
            	
            	String FROM_MAIL = xmlConfig.getString(String.format("MAIL(%d).FROMS.FROM(%d).FROM_MAIL(%d)", 0, i, 0));
        		String USERNAME = xmlConfig.getString(String.format("MAIL(%d).FROMS.FROM(%d).USERNAME(%d)", 0, i, 0));
        		String PASSWORD = xmlConfig.getString(String.format("MAIL(%d).FROMS.FROM(%d).PASSWORD(%d)", 0, i, 0));
        		
        		FromMailEntity fromObj = new FromMailEntity();
        		fromObj.setFromMail(FROM_MAIL);
        		fromObj.setUsername(USERNAME);
        		fromObj.setPassword(PASSWORD);

            	logger.info("[" + Thread.currentThread().getId() + "] " + String.format("add FROM to FROMS: %s", fromObj));
            	
            	if(fromObj != null && !StringUtil.isNullOrEmpty(USERNAME) && !StringUtil.isNullOrEmpty(PASSWORD)) {
            		logger.info("[" + Thread.currentThread().getId() + "] " + String.format("add FROM to FROMS: %s", fromObj));
            		FROMS_TEMP.add(fromObj);
            	}
            }
        }
        
        froms = FROMS_TEMP;
        
        logger.info("[" + Thread.currentThread().getId() + "] " + String.format("load config FROMS: %s", FROMS_TEMP));
	}
	
	@Override
	public void reloadProcess() throws Exception {
		
	}
}

