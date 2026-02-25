package com.qa.automation.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.automation.BaseTest.BaseTest;
import com.qa.automation.constant.Constants;
import com.qa.automation.pages.HomePage;

public class LoginPageTest extends BaseTest {

	@Test
	public void checkLoginPageTitle() {
		String actualTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitle, Constants.LOGIN_PAGE_TITLE);
	}

	@Test
	public void verifyLoginPageUrl() {
		String actualUrl = loginPage.getLoginPageUrl();
		Assert.assertEquals(actualUrl, Constants.LOGIN_PAGE_URL);
	}

	@Test(priority = Integer.MAX_VALUE)
	public void doLogin() throws InterruptedException {
		String expectedTitle = Constants.HOME_PAGE_TITLE;
		HomePage homePage = loginPage.Login(prop.getProperty("username"), prop.getProperty("password"));
		String homePageTitle = homePage.getHomePageTitle();
		Thread.sleep(3);
		Assert.assertTrue(homePage.isLogoutLinkPresent(), "After login logout link is not present");
		Assert.assertEquals(homePageTitle, expectedTitle, "Home page title not matched");
	}

	@Test
	public void verifyInvalidLoginMessageWithCorrectCredentials() {
		loginPage.Login("jk@gmail.com", "dkkk@123");
		Assert.assertTrue(loginPage.invalidLogin(), "Invalid message not appears");

	}
}
