package com.qa.automation.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.qa.automation.Utils.GenericUtils;
import com.qa.automation.constant.Constants;

public class SearchResultPage {

	private WebDriver driver;
	private GenericUtils genericUtils;

	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		genericUtils = new GenericUtils(driver);
	}

	private By searchProductsText = By.xpath("//*[text()='Searched Products']");
	private By viewFirstProductBtn = By
			.xpath("//img[@src=\"/get_product_picture/33\"]/ancestor::div/following-sibling::div[@class='choose']");
	// *[text()='Category']/..//div[contains(@class,'category-products')]/div
	// *[contains(@data-toggle,'collapse')]/ancestor::div//*[contains(text(),"Category")]

	private By jeansOne = By.xpath("//img[@src=\"/get_product_picture/33\"]");

	private By productList = By.xpath("//div[@class='features_items']//div[@class='product-image-wrapper']");
	private By allProduct = By.xpath("//*[text()='All Products']");

	private String genericProduct = "//*[text()='%s']/ancestor::div/following-sibling::div[@class='choose']//li";

	private By advertisePopup = By.xpath("//*[@id='ad_position_box']");
	private By advertiseCloseBtn = By.xpath("//*[@aria-label=\"Close ad\"]");
	private By advertiseFrame = By.xpath("//iframe[@title='Advertisement' and @name='ad_iframe']");

	public boolean isSearchProductTextPresent() {
		return genericUtils.isElementDisplayed(searchProductsText);
	}

	public void selectFirstProduct() {
		genericUtils.doClick(viewFirstProductBtn);
	}

	public ArrayList<String> productList() {

		genericUtils.waitForElementPresence(searchProductsText, Constants.LONG_TIMEOUT, 2);
		List<WebElement> categories = genericUtils.getElements(productList);
		ArrayList<String> getCategoriesText = new ArrayList<String>();
		for (WebElement e : categories) {
			String text = e.getText();
			getCategoriesText.add(text);
		}
		System.out.println("Products available on Search result page is " + getCategoriesText);
		return getCategoriesText;
	}

	public int productResultCount() {
		genericUtils.waitForElementPresence(searchProductsText, Constants.LONG_TIMEOUT, 2);
		List<WebElement> categories = genericUtils.getElements(productList);
		int count = categories.size();
		System.out.println("Product result count is " + count);
		return count;
	}

	public void sc(String element) {
		Actions actions = new Actions(driver);
		actions.scrollToElement(driver.findElement(By.xpath(element))).build().perform();
	}

	public ProductInformationPage selectProduct(String productName) {
		System.out.println("product name is " + productName);
		String dynamic = String.format(genericProduct, productName);
		genericUtils.scrollToElement(dynamic);
		genericUtils.getElementByDynamicText(dynamic).click();
		// genericUtils.getElement(By.xpath(dynamic)).click();;
		System.out.println("clicked dynamic page");

		return new ProductInformationPage(driver);
	}

}
