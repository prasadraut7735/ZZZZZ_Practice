package com.qa.openkart.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.openkart.base.BaseTest;

public class LoginPageTest extends BaseTest {

	@Test
	public void loginPageTitleTest() {
		String actTitle = loginpage.getLoginPageTitle();
		Assert.assertEquals(actTitle, "Account Login");
	}

	@Test
	public void loginPageUrlTest() {
		String actUrl = loginpage.getLoginPageUrl();
		Assert.assertTrue(actUrl.contains("route=account/login"));
	}

	@Test
	public void forgetPwdLinkExistTest() {
		Assert.assertTrue(loginpage.isForgetPwdLinkExist());
	}

	@Test
	public void logoExistTest() {
		Assert.assertTrue(loginpage.isLogoExist());
	}

	@Test(priority = Short.MAX_VALUE)
	public void loginTest() {
		accpage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accpage.getAccPageTitle(), "My Account");
	}
}
