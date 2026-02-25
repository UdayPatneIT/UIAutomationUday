package com.qa.automation.pages;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.automation.Utils.GenericUtils;
import com.qa.automation.constant.Constants;

public class ProductInformationPage {

	private WebDriver driver;
	private GenericUtils genericUtils;
	private Map<String, String> productMap;

	public ProductInformationPage(WebDriver driver) {

		this.driver = driver;
		genericUtils = new GenericUtils(driver);

	}

	private By nameField = By.xpath("//*[@placeholder='Your Name']");
	private By emailAddress = By.xpath("//*[@placeholder='Email Address']");
	private By reviewField = By.xpath("//*[@placeholder='Add Review Here!']");
	private By submit = By.xpath("//*[@type='submit' and @id='button-review']");
	private By productHeader = By.xpath("//*[@class='product-information']//h2");

	private By productImage = By.cssSelector("div.view-product img");

	private By productInformation = By.xpath("//div[@class='product-information']//p");
	private By productPrice = By.xpath("//div[@class='product-information']//span[contains(text(),'Rs')]");

	public String productInfoPageTitle() {
		return genericUtils.waitForTitleIs(Constants.PRODUCT_DETAIL_TITLE, Constants.LONG_TIMEOUT);
	}

	public void typeAndSubmitReview(String name, String emailId, String review) {
		genericUtils.doSendKeys(nameField, name);
		genericUtils.doSendKeys(emailAddress, emailId);
		genericUtils.doSendKeys(reviewField, review);
		genericUtils.doClick(submit);
	}

	public String getProductHeader() {
		String productHeaderText = genericUtils.getElement(productHeader).getText();
		System.out.println(productHeaderText);
		return productHeaderText;
	}

	public int getProductImageCount() {
		int imagesCount = genericUtils.waitForElementsPresence(productImage, Constants.LONG_TIMEOUT).size();
		System.out.println(getProductHeader() + " : Image count is " + imagesCount);
		return imagesCount;
	}

	public Map<String, String> getProductInfor() {
		productMap = new HashMap<String, String>();
		productMap.put("ProductNameHeader", getProductHeader());
		productMap.put("Images", getProductImageCount() + "");
		getProductMetaData();
		getProductPrice();
		return productMap;
	}

	private void getProductMetaData() {
		List<WebElement> metaList = genericUtils.waitForElementsPresence(productInformation, Constants.DEFAULT_TIMEOUT);
		for (WebElement e : metaList) {
			String text = e.getText();
			String[] meta = text.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productMap.put(metaKey, metaValue);
		}

	}

	private void getProductPrice() {
		String price = genericUtils.getElement(productPrice).getText();
		productMap.put("priceInRupee", price);
	}

}
