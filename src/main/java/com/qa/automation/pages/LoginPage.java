package com.qa.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.automation.Utils.GenericUtils;
import com.qa.automation.constant.Constants;

public class LoginPage {

	private WebDriver driver;
	private GenericUtils genericUtils;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		genericUtils= new GenericUtils(driver);
	}

	private By email = By.xpath("//*[@data-qa='login-email' and not (contains(@id,'susbscribe_email'))]");
	private By password = By.xpath("//*[@data-qa='login-password']");
	private By loginButton = By.xpath("//*[@data-qa='login-button']");
	private By invalidMessage = By.xpath("//*[text()='Your email or password is incorrect!']");
	// headers
	private By home = By.xpath("//*[text()=' Home']");
	private By signUp = By.xpath("//*[text()=' Signup / Login']");
	private By cart = By.xpath("//*[text()=' Cart']");
	private By products = By.xpath("//*[text()=' Products']");
	private By contactUs = By.xpath("//*[text()=' Contact us']");
	private By videoTutorial = By.xpath("//*[text()=' Video Tutorials']");

	public String getLoginPageTitle() {
		String title = genericUtils.waitForTitleIs(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIMEOUT);
		return title;
	}

	public String getLoginPageUrl() {
		String url = genericUtils.waitForURLContains(Constants.LOGIN_PAGE_URL, Constants.DEFAULT_TIMEOUT);
		return url;
	}

	public HomePage Login(String userName, String pwd) {
		genericUtils.waitForElementVisible(email, Constants.SHORT_TIMEOUT, Constants.DEFAULT_TIMEOUT).clear();
		genericUtils.doSendKeys(email, userName);
		genericUtils.getElement(password).clear();
		genericUtils.doSendKeys(password, pwd);
		genericUtils.doActionsClick(loginButton);
		return new HomePage(driver);
	}

	public Boolean invalidLogin() {
		return genericUtils.isElementDisplayed(invalidMessage);
	}

}
