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
	public static final String USERNAME = "administrator";
	public static final String USERNAME2 = "John";
	public static final String PASSWORD = "";
	public static final String ALERTCONFIRMDELETEPAGE = "Are you sure you want to remove this page?";
	public static final String ALERTWARNINGDELETEPAGE = "Can not delete page 'Test' since it has children page(s";

}
