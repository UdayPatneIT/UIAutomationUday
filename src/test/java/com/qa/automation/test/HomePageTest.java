package com.qa.automation.test;

import java.util.ArrayList;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.automation.BaseTest.BaseTest;

public class HomePageTest extends BaseTest {

	@BeforeClass
	public void login() {
		homePage = loginPage.Login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void verifyTest() {
		Assert.assertTrue(homePage.isLogoutLinkPresent(), "logout link is not present");
	}

	@Test
	public void verifyHeaders() {
		ArrayList<String> actualHeadersList = homePage.getHeaderList();
		System.out.println("Test" + actualHeadersList);
		ArrayList<String> expectedHeadersList = new ArrayList<String>(Arrays.asList(
				"Home,Products,Cart,Logout,Delete Account, Test Cases, API Testing, Video Tutorials, Contact us"));
		System.out.println(expectedHeadersList);
		// Assert.assertListContains(null, null, null);
	}

}
