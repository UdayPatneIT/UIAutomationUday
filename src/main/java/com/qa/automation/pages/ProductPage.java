package com.qa.automation.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.automation.Utils.GenericUtils;
import com.qa.automation.constant.Constants;

public class ProductPage {

	private WebDriver driver;
	private GenericUtils genericUtils;

	private By searchProduct = By.xpath("//*[@name='search']");
	private By submitSearch = By.xpath("//*[@id='submit_search']");
	private By allCategoryOptions = By.xpath(
			"//*[text()='Category']/..//div[contains(@class,'category-products')]/descendant::a[@data-toggle='collapse']");
	private By allBrandOptions = By.xpath("//ul[@class='nav nav-pills nav-stacked']//a");
	private By saleImage = By.xpath("//img[@id='sale_image']");
	private By advertisePopup = By.xpath("//*[@id='ad_position_box']");
	private By advertiseCloseBtn = By.xpath("//*[@aria-label=\"Close ad\"]");
	private By advertiseParentFrame = By.xpath("//iframe[@title='Advertisement']");

	private By advertiseFrame = By.xpath("//iframe[@title='Advertisement' and @name='ad_iframe']");

	public ProductPage(WebDriver driver) {
		this.driver = driver;
		genericUtils = new GenericUtils(driver);
	}

	public SearchResultPage searchProduct(String productName) {
//		genericUtils.waitForElementPresence(advertiseParentFrame, 10);
//		driver.switchTo().frame(genericUtils.getElement(advertiseFrame));
//		genericUtils.getElement(advertisePopup).isDisplayed();
//		genericUtils.doClick(advertiseCloseBtn);
//		genericUtils.refreshPage();
//		driver.switchTo().defaultContent();
        genericUtils.refreshPage();
		System.out.println("Product name is : " + productName);
		genericUtils.clickElementWhenReady(searchProduct, Constants.DEFAULT_TIMEOUT);
		genericUtils.doSendKeys(searchProduct, productName);
		genericUtils.doClick(submitSearch);

		return new SearchResultPage(driver);
	}

	public ArrayList<String> isAllBrandsOptionsPresent() {
		List<WebElement> brands = genericUtils.getElements(allBrandOptions);
		ArrayList<String> getBrandsText = new ArrayList<String>();
		for (WebElement e : brands) {
			String text = e.getText();
			getBrandsText.add(text);
		}
		return getBrandsText;

	}

	public ArrayList<String> listAllCategoryOptions() {
		List<WebElement> categories = genericUtils.getElements(allCategoryOptions);
		ArrayList<String> getCategoriesText = new ArrayList<String>();
		for (WebElement e : categories) {
			String text = e.getText();
			getCategoriesText.add(text);
		}
		return getCategoriesText;
	}

}
