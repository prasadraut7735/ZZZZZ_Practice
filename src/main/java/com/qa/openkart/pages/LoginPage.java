package com.qa.openkart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.openkart.utils.ElementUtil;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private final By loginBtn = By.cssSelector("input[type='submit'].btn-primary");
	private final By logo = By.className("img-responsive");
	private final By emailId = By.id("input-email");
	private final By pwd = By.id("input-password");
	private final By fgtPwd = By.linkText("Forgotten Password");
	private final By registerLink = By.linkText("Register");


	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getLoginpageTitle() {
		String title = driver.getTitle();
		System.out.println("Login Page title is: " + title);
		return title;
	}

	public String getLoginPageTitle() {
		String title = driver.getTitle();
		System.out.println("Login page title is: " + title);
		return title;
	}

	public String getLoginPageUrl() {
		String url = driver.getCurrentUrl();
		System.out.println("Login page url is: " + url);
		return url;
	}

	public boolean isForgetPwdLinkExist() {
		return eleUtil.isElementDisplayed(fgtPwd);
	}

	public boolean isLogoExist() {
		return eleUtil.isElementDisplayed(logo);
	}

	public AccountPage doLogin(String email, String password) {
		eleUtil.doSendkeys(emailId, email);
		eleUtil.doSendkeys(pwd, password);
		eleUtil.doClick(loginBtn);
		eleUtil.waitForTitleContains(10, "My Account");
		return new AccountPage(driver);
	}
}
