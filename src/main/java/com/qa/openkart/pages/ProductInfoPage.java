package com.qa.openkart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.openkart.utils.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	Map<String, String> productMap;

	private By header = By.tagName("h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]//li");
	private By priceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]//li");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getProductHeader() {
		return eleUtil.getText(header);
	}

	public int getProductImageCount() {
		return eleUtil.getElementsCount(productImages);
	}

	public Map<String, String> getProductMetaData() {
		productMap = new LinkedHashMap<String, String>();
		List<WebElement> eleList = eleUtil.getElements(productMetaData);
		for (WebElement e : eleList) {
			String text = e.getText();
			String meta[] = text.split(":");
			String key = meta[0].trim();
			String value = meta[1].trim();
			productMap.put(key, value);
		}
		return productMap;
	}

	public Map<String, String> getPriceData() {
		productMap = new LinkedHashMap<String, String>();
		List<WebElement> eleList = eleUtil.getElements(priceData);
		String productPrice = eleList.get(0).getText().trim();
		String extPrice = eleList.get(1).getText().split(":")[1].trim();
		productMap.put("Product price", productPrice);
		productMap.put("Ext price", extPrice);
		return productMap;
	}
}
