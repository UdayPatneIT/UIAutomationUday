package com.qa.automation.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.automation.BaseTest.BaseTest;
import com.qa.automation.constant.Constants;
import com.qa.automation.pages.ProductInformationPage;

public class ProductPageTest extends BaseTest {

	@BeforeClass()
	public void doLogin() {
		homePage = loginPage.Login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@DataProvider
	public Object[][] getProductSearchData() {
		return new Object[][] { { "Jeans", 3 }, { "Tops", 13 }, { "Shirts", 13 }, { "T-shirt", 3 } };
	}

	@Test(dataProvider = "getProductSearchData")
	public void verifySearchProduct(String productName, int productCount) {
		productPage = homePage.clickProductsBtn();
		searchResultPage = productPage.searchProduct(productName);
		int actualcount = searchResultPage.productResultCount();
		Assert.assertEquals(actualcount, productCount, "Search result is not matched");
		// Assert.assertNotNull(actualcount, "Blank search result");
	}

	@Test()
	public void verifyProductInfoPage() {
		productPage = homePage.clickProductsBtn();
		searchResultPage = productPage.searchProduct("Jeans");
		ProductInformationPage productInformationPage = searchResultPage.selectProduct("Grunt Blue Slim Fit Jeans");
		Assert.assertEquals(productInformationPage.productInfoPageTitle(), Constants.PRODUCT_DETAIL_TITLE);
		String actualProduct = productInformationPage.getProductHeader();
		Assert.assertEquals(actualProduct, "Grunt Blue Slim Fit Jeans", "Product Name is not matched");
	}

	public void verifyProductImage() {
		productPage = homePage.clickProductsBtn();
		searchResultPage = productPage.searchProduct("Jeans");
		ProductInformationPage productInformationPage = searchResultPage.selectProduct("Grunt Blue Slim Fit Jeans");
		int actualProductImageCount = productInformationPage.getProductImageCount();
		Assert.assertEquals(actualProductImageCount, 1, "images count not matched with expected");
	}

	@Test
	public void verifyProductInformation() {

		productPage = homePage.clickProductsBtn();
		searchResultPage = productPage.searchProduct("Jeans");
		ProductInformationPage productInformationPage = searchResultPage.selectProduct("Grunt Blue Slim Fit Jeans");
		Map<String, String> actualProductInfo = productInformationPage.getProductInfor();
		System.out.println(actualProductInfo);
		System.out.println("--------");
		Assert.assertEquals(actualProductInfo.get("ProductNameHeader"), "Grunt Blue Slim Fit Jeans");
		Assert.assertEquals(actualProductInfo.get("Availability"), "In Stock");
		Assert.assertEquals(actualProductInfo.get("Brand"), "Polo");
		Assert.assertEquals(actualProductInfo.get("Condition"), "New");
		Assert.assertEquals(actualProductInfo.get("Images"), "1");
		Assert.assertEquals(actualProductInfo.get("priceInRupee"), "Rs. 1400");

	}
	
	@Test
	public void verifyLogoutLinkPresent() {
		productPage = homePage.clickProductsBtn();
		searchResultPage = productPage.searchProduct("Jeans");
		ProductInformationPage productInformationPage = searchResultPage.selectProduct("Grunt Blue Slim Fit Jeans");
	
		Assert.assertTrue(productInformationPage.isLogoutLinkPresent(), "logout link is not present on product page");
	}

}
