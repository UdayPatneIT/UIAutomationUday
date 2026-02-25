package com.qa.automation.BaseTest;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.automation.factory.DriverFactory;
import com.qa.automation.pages.HomePage;
import com.qa.automation.pages.LoginPage;
import com.qa.automation.pages.ProductInformationPage;
import com.qa.automation.pages.ProductPage;
import com.qa.automation.pages.SearchResultPage;

public class BaseTest {

	WebDriver driver;
	DriverFactory df;
	
	protected Properties prop;
	protected LoginPage loginPage;
	protected HomePage homePage;
	protected ProductPage productPage;
	protected SearchResultPage searchResultPage;
	protected ProductInformationPage productInformationPage;
	
	

	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		prop=df.configReader();
		driver = df.initialiseDriver(prop);
		loginPage = new LoginPage(driver);
		
	}

	@AfterTest
	public void teatDown() {
		driver.quit();
	}

}
