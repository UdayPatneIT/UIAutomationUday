package com.qa.automation.factory;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.automation.constant.Constants;
import com.qa.automation.exception.FrameworkException;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	private static final Logger log= LogManager.getLogger(DriverFactory.class);
	

	public WebDriver initialiseDriver(Properties prop) {
		String browser = prop.getProperty("browser");
		switch (browser.trim().toLowerCase()) {
		case "chrome":
			//System.out.println("chrome is launched");
			log.info("chrome is launched");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);
			break;
		case "edge":
			EdgeOptions edgeOptions= new EdgeOptions();
			log.info("edge browser is launched");
			driver = new EdgeDriver(edgeOptions);
			break;

		case "firefox":
			System.out.println("firefox is launched");
			driver = new FirefoxDriver();
			break;

		default:
			log.error("wrong browser, please pass valid browser");
			throw new FrameworkException("=== invalid browser name");

		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(prop.getProperty("loginurl"));
		return driver;

	}

	public Properties configReader() {

		String envName = System.getProperty("env");
		System.out.println("environment is " + envName);
		FileInputStream fis = null;
		prop = new Properties();
		try {
			if (envName == null) {
				log.warn("env is not selected, hence running automation on QA environment");
				fis = new FileInputStream(Constants.CONFIG_QA_PATH_FILE_PATH);
			} else {
				switch (envName.trim().toLowerCase()) {
				case "qa":
					log.info("qa environment");
					fis = new FileInputStream(Constants.CONFIG_QA_PATH_FILE_PATH);
					break;
				case "uat":
					log.info("uat environment");
					fis = new FileInputStream(Constants.CONFIG_UAT_PATH_FILE_PATH);
					break;
				case "prod":
					log.info("prod environment");
					fis = new FileInputStream(Constants.CONFIG_PROD_PATH_FILE_PATH);
					break;

				default:
					log.error("wrong environment, please pass valid environment name");
					throw new FrameworkException("=== invalid env name");

				}
			}
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return prop;
	}
	
	

}
