package com.xtel.vngolf.api.main;

import com.tbv.utils.app.AbsAppBase;
import com.tbv.utils.config.base.AbsConfigUtils;
import com.tbv.utils.db.DbModule;
import com.xtel.vngolf.api.client.GHClient;
import com.xtel.vngolf.api.config.CoreConfig;
import com.xtel.vngolf.api.config.InitConfig;
import com.xtel.vngolf.api.config.MailConfig;
import com.xtel.vngolf.api.listener.server.RestfulServer;
import com.xtel.vngolf.api.listener.service.BaseService;

/**
 *
 * @author trongbv
 */
public class MainApplication extends AbsAppBase {

	protected static MainApplication app = null;

	public MainApplication(String pathConf) {
		super(pathConf);
	}

	public static synchronized MainApplication getInstance(String pathConf) {

		if (app == null) {
			app = new MainApplication(pathConf);
		}
		return app;
	}

	public static void main(String[] args) {

		try {
			String pathConf = args != null && args.length > 0 ? args[0] : "";
			app = getInstance(pathConf);
			app.init("log4j.xml");
			app.start();
			app.addShutdownHook();

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			try {
				app.stop();
			} catch (Exception e) {
				logger.warn(e, e);
				System.exit(9);
			}
		}
	}

	@Override
	public void initConfig() {
		InitConfig.createInstance(pathConf + "init.xml");
		CoreConfig.createInstance(pathConf + "core.xml", true, InitConfig.CHECK_RELOAD_INTERVAL_FOR_CORE_CONFIG_MS);
		MailConfig.createInstance(pathConf + "mail.xml", true, InitConfig.CHECK_RELOAD_INTERVAL_FOR_MAIL_CONFIG_MS);
	}

	public static DbModule cmsDb;
	public static RestfulServer restServer;
	public static GHClient ghClient;

	@Override
	public void start() throws Exception {

		try {
			cmsDb = new DbModule(pathConf + "db.xml");
			cmsDb.startUp();
		} catch (Exception e) {
            logger.warn(e.getMessage(), e);
            return;
        }
		
		String baseServicePackage = BaseService.class.getPackage().getName();
		logger.info(("base service package: " + baseServicePackage));
		
		restServer = new RestfulServer(baseServicePackage, CoreConfig.API_PORT, CoreConfig.CONTEXT_PATH, "/*");
		restServer.publish();
		
		ghClient = new GHClient();
		ghClient.init();

		logger.info("-----------------------------MODULE IS STARTED SUCCESS!-----------------------------");
	}

	@Override
	public void stop() throws Exception {

		logger.info(String.format("[%s] STOP RELOAD CONFIG THREAD ...", Thread.currentThread().getName()));
		AbsConfigUtils.stopAllReloadThread();

		restServer.shutdown();
	}

}
