package common;

import java.util.logging.Logger;

public class Constant {

	// Dashboard
	public static final String DASHBOARD_URL = "http://localhost/TADashboard/";

	/**
	 * @author tuan.vu
	 * 
	 * Creates the logger.
	 *
	 * @param className the class name
	 * @return the logger
	 */
	public static final Logger createLogger(String className) {
		return Logger.getLogger(className);
	}

	//Account
	public static final String REPOSITORY = "SampleRepository";
	public static final String NEW_REPOSITORY = "TestRepository";
	public static final String USERNAME = "administrator";
	public static final String PASSWORD = "";
	
}
