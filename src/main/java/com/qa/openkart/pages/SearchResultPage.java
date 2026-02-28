package com.qa.openkart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.openkart.utils.ElementUtil;

public class SearchResultPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private By resultProduct = By.xpath("//div[@class='product-thumb']//h4//a");

	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public int getResultProductCount() {
		return eleUtil.getElementsCount(resultProduct);
	}

	public ProductInfoPage doProductSelect(String productKey) {
		eleUtil.doClick(By.linkText(productKey));
		return new ProductInfoPage(driver);
	}
}
