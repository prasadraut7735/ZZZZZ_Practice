package com.qa.openkart.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.openkart.base.BaseTest;

public class SearchTest extends BaseTest {

	@BeforeClass
	public void searchPageSetUp() {
		accpage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void productResultCountTest() {
		searchpage = accpage.doSearch("MacBook");
		int actCount = searchpage.getResultProductCount();
		Assert.assertEquals(actCount, 3);
	}
}
