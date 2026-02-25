package com.qa.automation.constant;

public class Constants {
	public final static int DEFAULT_TIMEOUT = 5;
	public final static int SHORT_TIMEOUT = 10;
	public final static int LONG_TIMEOUT = 20;
	
	//Environment paths
	public static final String CONFIG_PROD_PATH_FILE_PATH = "./src/test/resources/config/config.properties";
	public static final String CONFIG_QA_PATH_FILE_PATH = "./src/test/resources/config/qa.config.properties";
	public static final String CONFIG_UAT_PATH_FILE_PATH = "./src/test/resources/config/uat.config.properties";


	public final static String LOGIN_PAGE_TITLE = "Automation Exercise - Signup / Login";
	public final static String LOGIN_PAGE_URL = "https://automationexercise.com/login";
	public final static String HOME_PAGE_TITLE = "Automation Exercise";
	public final static String PRODUCT_DETAIL_TITLE="Automation Exercise - Product Details";

}
