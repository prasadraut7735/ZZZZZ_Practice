package com.qa.openkart.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.openkart.base.BaseTest;

public class ProductInfoTest extends BaseTest {

	@BeforeClass
	public void productInfoSetUp() {
		accpage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@DataProvider
	public Object getData() {
		return new Object[][] { { "MacBook", "MacBook Air" }, { "MacBook", "MacBook" }, { "MacBook", "MacBook Pro" },
				{ "imac", "iMac" }, { "Samsung", "Samsung SyncMaster 941BW" },
				{ "Samsung", "Samsung Galaxy Tab 10.1" } };
	}

	@Test(dataProvider = "getData")
	public void productHeaderTest(String key, String value) {
		searchpage = accpage.doSearch(key);
		productpage = searchpage.doProductSelect(value);
		String actHeader = productpage.getProductHeader();
		Assert.assertEquals(actHeader, value);
	}

	@Test
	public void productImageCountTest() {
		searchpage = accpage.doSearch("MacBook");
		productpage = searchpage.doProductSelect("MacBook Air");
		int actCount = productpage.getProductImageCount();
		Assert.assertEquals(actCount, 4);
	}

	@Test
	public void getProductMetaDataTest() {
		searchpage = accpage.doSearch("MacBook");
		productpage = searchpage.doProductSelect("MacBook Air");
		Map<String, String> actMap = productpage.getProductMetaData();
		Assert.assertEquals(actMap.get("Brand"), "Apple");
		Assert.assertEquals(actMap.get("Product Code"), "Product 17");
		Assert.assertEquals(actMap.get("Reward Points"), "700");
		Assert.assertEquals(actMap.get("Availability"), "Out Of Stock");
	}
}
