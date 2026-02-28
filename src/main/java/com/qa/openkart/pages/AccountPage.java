package com.qa.openkart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.openkart.utils.ElementUtil;

public class AccountPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private By header = By.tagName("h2");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("button.btn-default");

	public AccountPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getAccPageTitle() {
		String title = driver.getTitle();
		System.out.println("Accounts page title is: " + title);
		return title;
	}

	public String getAccPageUrl() {
		String url = driver.getCurrentUrl();
		System.out.println("Account page url is: " + url);
		return url;
	}

	public List<String> getAccPageHeader() {
		eleUtil.waitForElementVisible(header, 10);
		return eleUtil.getElementTextList(header);
	}

	public SearchResultPage doSearch(String searchKey) {
		eleUtil.doSendkeys(search, searchKey);
		eleUtil.doClick(searchIcon);
		return new SearchResultPage(driver);
	}
}
