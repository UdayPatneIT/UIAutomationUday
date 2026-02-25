package com.qa.automation.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.automation.Utils.GenericUtils;
import com.qa.automation.constant.Constants;

public class HomePage {
	private WebDriver driver;
	private GenericUtils genericUtils;

	private By logout = By.xpath("//a[@href='/logout']");
	private By home = By.xpath("//*[text()=' Home']");
	private By cart = By.xpath("//*[text()=' Cart']");
	private By products = By.xpath("//*[text()=' Products']");
	private By contactUs = By.xpath("//*[text()=' Contact us']");
	private By videoTutorial = By.xpath("//*[text()=' Video Tutorials']");
	private By headers = By.xpath("//header[@id='header']//div[@class='shop-menu pull-right']//li");

	
	private By advertisePopup = By.xpath("//*[@id='ad_position_box']");
	private By advertiseCloseBtn = By.xpath("//*[@aria-label=\"Close ad\"]");
	private By advertiseParentFrame = By.xpath("//iframe[@title='Advertisement']");
	private By advertiseFrame = By.xpath("//iframe[@title='Advertisement' and @name='ad_iframe']");

	
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		genericUtils= new GenericUtils(driver);
	}

	public String getHomePageTitle() {
		String title = genericUtils.waitForTitleContains(Constants.HOME_PAGE_TITLE, Constants.DEFAULT_TIMEOUT);
		return title;
	}

	public boolean isLogoutLinkPresent() {
		return genericUtils.isElementDisplayed(logout, 1);
				}

	public ProductPage clickProductsBtn() {
		genericUtils.refreshPage();
		genericUtils.clickElementWhenReady(products, Constants.DEFAULT_TIMEOUT);
		return new ProductPage(driver);
	}
	
	public void logout() {
		if(isLogoutLinkPresent()) {
			genericUtils.waitForElementPresence(logout, Constants.DEFAULT_TIMEOUT);
		}
	}

	public ArrayList<String> getHeaderList() {
		List<WebElement> headerList = genericUtils.getElements(headers);
		ArrayList<String> headerValList = new ArrayList<String>();

		for (WebElement e : headerList) {
			String text = e.getText();
			headerValList.add(text);
		}
		return headerValList;
	}

}
