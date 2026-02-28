package com.qa.openkart.test;

import static com.qa.openkart.constants.AppConstants.EXPECTED_HEADER_LIST;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.openkart.base.BaseTest;

public class AccountPageTest extends BaseTest {

	@BeforeClass
	public void accPageSetup() {
		accpage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void accPageTitleTest() {
		String actTitle = accpage.getAccPageTitle();
		Assert.assertEquals(actTitle, "My Account");
	}

	@Test
	public void accPageUrlTest() {
		String actUrl = accpage.getAccPageUrl();
		Assert.assertTrue(actUrl.contains("route=account/account"));
	}

	@Test
	public void accPageHeaderTest() {
		List<String> actHeader = accpage.getAccPageHeader();
		Assert.assertEquals(actHeader, EXPECTED_HEADER_LIST);
	}
}
