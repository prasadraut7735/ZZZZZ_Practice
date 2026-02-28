package com.qa.openkart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.openkart.exceptions.BrowserException;

public class ElementUtil {
	private WebDriver driver;
	private Actions act;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		act = new Actions(driver);
	}

	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	private void nullCheck(CharSequence inputText) {
		if (inputText == null || inputText.length() <= 0) {
			throw new IllegalArgumentException("====VALUE SHOULD NOT BE NULL====");
		}
	}

	public void doClear(By locator) {
		getElement(locator).clear();
	}

	public void doSendkeys(By locator, String inputText) {
		doClear(locator);
		nullCheck(inputText);
		getElement(locator).sendKeys(inputText);
	}

	public void doSendkeys(By locator, CharSequence inputText) {
		doClear(locator);
		nullCheck(inputText);
		getElement(locator).sendKeys(inputText);
	}

	public String getText(By locator) {
		String text = getElement(locator).getText();
		if (text != null) {
			return text;
		} else {
			System.out.println("===NULL TEXT===");
			return null;
		}
	}

	public boolean isElementDisplayed(By locator) {
		try {
			getElement(locator).isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.out.println("==ELEMENT IS NOT DISPLAYED==");
			return false;
		}
	}

	public boolean isElementEnabled(By locator) {
		try {
			getElement(locator).isEnabled();
			return true;
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.out.println("==ELEMENT IS NOT ENABLED==");
			return false;
		}
	}

	public boolean isElementSelected(By locator) {
		try {
			getElement(locator).isSelected();
			return true;
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.out.println("==ELEMENT IS NOT SELECTED==");
			return false;
		}
	}

	public String doElementGetAttribute(By locator, String attributeName) {
		nullCheck(attributeName);
		String attValue = getElement(locator).getDomAttribute(attributeName);
		return attValue;
	}

	public String doElementGetProperty(By locator, String propertyName) {
		nullCheck(propertyName);
		String property = getElement(locator).getDomProperty(propertyName);
		return property;
	}

	/**
	 * Find Elements util
	 */

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public int getElementsCount(By locator) {
		return getElements(locator).size();
	}

	public List<String> getElementTextList(By locator) {
		List<String> textList = new ArrayList<String>();
		List<WebElement> eleList = getElements(locator);
		for (WebElement e : eleList) {
			String text = e.getText();
			textList.add(text);
		}
		System.out.println("Text list is: " + textList);
		return textList;
	}

	public List<String> getElementTextList1(By locator) {
		List<String> textList = new ArrayList<String>();
		List<WebElement> eleList = getElements(locator);
		for (WebElement e : eleList) {
			String text = e.getText();
			textList.addAll(Arrays.asList(text.split("\\R")));
		}
		System.out.println("Text list is: " + textList);
		return textList;
	}

	public boolean isElementPresent(By locator) {
		int eleList = getElements(locator).size();
		if (eleList == 1) {
			System.out.println("Element is displayed");
			return true;
		} else {
			System.out.println("Element is not displayed");
			return false;
		}
	}

	public boolean isElementPresent(By locator, int expectedElementCount) {
		int actCount = getElements(locator).size();
		if (actCount == expectedElementCount) {
			System.out.println("Element is displayed");
			return true;
		} else {
			System.out.println("Element is not displayed");
			return false;
		}
	}

	public boolean doSearchMethod(By locator, String key, String expectedKey) {
		boolean flag = false;
		List<WebElement> eleList = getElements(locator);
		if (eleList.size() == 0) {
			throw new BrowserException("===INVALID SEARCH===");
		} else {
			for (WebElement e : eleList) {
				String text = e.getText();
				if (text.equalsIgnoreCase(expectedKey)) {
					e.click();
					flag = true;
					break;
				}
			}
		}
		if (flag) {
			System.out.println("Suggest matched");
			return true;
		} else {
			System.out.println("Suggest not matched");
			return false;
		}
	}

	public boolean clickedElementMenthod(By locator, String expectedElementToClick) {
		List<WebElement> eleList = getElements(locator);
		boolean flag = false;
		for (WebElement e : eleList) {
			String text = e.getText();
			if (text.equals(expectedElementToClick)) {
				e.click();
				flag = true;
				break;
			}
		}
		if (flag) {
			return true;
		} else {
			System.out.println("====Element is not found====");
			return false;
		}
	}

	/**
	 * Select base Drop-down util
	 */

	public Select select(By locator) {
		return new Select(getElement(locator));
	}

	public boolean doSelectByIndex(By locator, int index) {
		try {
			select(locator).selectByIndex(index);
			return true;
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean doSelectByValue(By locator, String value) {
		try {
			select(locator).selectByValue(value);
			return true;
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean doSelectByText(By locator, String text) {
		try {
			select(locator).deselectByVisibleText(text);
			return true;
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return false;
		}
	}

	public int getDropDownOptionsCount(By locator) {
		return select(locator).getOptions().size();
	}

	public List<String> getDropDownTextList(By locator) {
		List<String> textList = new ArrayList<String>();
		List<WebElement> eleList = getElements(locator);
		for (WebElement e : eleList) {
			String text = e.getText();
			textList.add(text);
		}
		return textList;
	}

	public boolean selectDropDown(By locator, String valueToBeSelected) {
		List<WebElement> eleList = getElements(locator);
		boolean flag = false;
		for (WebElement e : eleList) {
			String text = e.getText();
			if (text.equals(valueToBeSelected)) {
				e.click();
				break;
			}
		}
		if (flag) {
			System.out.println("Value" + valueToBeSelected + "is selected");
			return true;
		} else {
			System.out.println("Value" + valueToBeSelected + "is not selected");
			return false;
		}
	}

	public boolean compareSelectBasedDropdownOptionsWithExpected(By locator, List<String> expectedList) {
		List<WebElement> eleList = getElements(locator);
		List<String> actTextList = new ArrayList<String>();
		for (WebElement e : eleList) {
			String text = e.getText();
			actTextList.add(text);
		}
		if (actTextList.contains(expectedList)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method is used to select the multiple value from Drop-down: 1. Single
	 * selection is allowed 2. Multiple Selection is allowed 3. Selection of all is
	 * also possible.
	 * 
	 * @param selectDropDown
	 * @param dropDownList
	 * @param valueToBeSelected
	 */

	public void selectDropDown(By locator, String... valueToBeSelected) {
		List<WebElement> eleList = getElements(locator);
		if (valueToBeSelected[0].equalsIgnoreCase("All")) {
			for (WebElement e : eleList) {
				e.click();
			}
		} else {
			for (WebElement e : eleList) {
				String text = e.getText();
				for (String value : valueToBeSelected) {
					if (text.equals(value)) {
						e.click();
						break;
					}
				}
			}
		}
	}

	/**
	 * Actions Utils
	 * 
	 * @return
	 */

	public Actions doMoveToElement(By locator) {
		return act.moveToElement(getElement(locator));
	}

	public void doActionsClick(By locator) {
		act.click(getElement(locator)).build().perform();
	}

	public void doActionsSendKeys(By locator, String input) {
		act.sendKeys(getElement(locator), input).build().perform();
	}

	public void doActiobsSendKeysWithPause(By locator, String value, int pauseDuration) {
		char ch[] = value.toCharArray();
		for (char e : ch) {
			act.sendKeys(getElement(locator), String.valueOf(e)).pause(pauseDuration).build().perform();
		}
	}

	public void handleParentSubMenu(By parentMeny, By subMenu) {
		doMoveToElement(parentMeny).click(getElement(subMenu)).build().perform();
	}

	public void hanle4LevelMenuHandling(By selectmenu, int pausetime, By level1, By level2, By level3) {
		doClick(selectmenu);
		doMoveToElement(level1).pause(pausetime).build().perform();
		doMoveToElement(level2).pause(pausetime).build().perform();
		doClick(level3);
	}

	/**
	 * Wait Utils
	 */
	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible.
	 * 
	 * @param timeout
	 * @param locator
	 */
	public void waitForElementPresence(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public void waitForAllElementPresence(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * 
	 * @param timeout
	 * @param locator
	 */

	public WebElement waitForElementVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public List<WebElement> waitForAllElementVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfAllElements(getElements(locator)));
	}

	/**
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it.
	 * 
	 * @param locator
	 * @param timeout
	 */

	public void clickWhenReady(By locator, int timeout) {
		waitForElementVisible(locator, timeout).click();
	}

	public void sendkeyswithWait(By locator, int timeout, CharSequence inputtext) {
		waitForElementVisible(locator, timeout).sendKeys(inputtext);
	}

	/**
	 * Wait alert utility
	 */

	public Alert waitForAlert(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(int timeout) {
		waitForAlert(timeout).accept();
	}

	public void dismissAlert(int timeout) {
		waitForAlert(timeout).dismiss();
	}

	public String getText(int timeout) {
		return waitForAlert(timeout).getText();
	}

	/**
	 * Wait For Title utility
	 */

	public boolean waitForTitleContains(int timeout, String fractionTitle) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			return wait.until(ExpectedConditions.titleContains(fractionTitle));
		} catch (TimeoutException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean waitForTitle(int timeout, String title) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			return wait.until(ExpectedConditions.titleIs(title));
		} catch (TimeoutException e) {
			e.printStackTrace();
			return false;
		}
	}

	public String getTitleIs(int timeout, String title) {
		if (waitForTitleContains(timeout, title)) {
			return driver.getTitle();
		} else {
			throw new BrowserException("=====TITLE NOT MATCHED=====");
		}
	}

	/**
	 * Wait For URL utility
	 */

	public boolean waitForUrl(int timeout, String url) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			wait.until(ExpectedConditions.urlMatches(url));
			return true;
		} catch (TimeoutException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean waitForFractionUrl(int timeout, String fractionUrl) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			wait.until(ExpectedConditions.urlContains(fractionUrl));
			return true;
		} catch (TimeoutException e) {
			e.printStackTrace();
			return false;
		}
	}

	public String getUrl(int timeout, String url) {
		if (waitForUrl(timeout, url)) {
			return driver.getCurrentUrl();
		} else {
			throw new BrowserException("====INVALID URL MATCH====");
		}
	}

	/**
	 * Wait For Frame utility
	 */

	public void waitForFrame(int timeout, int index) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
	}

	public void waitForFrame(int timeout, String frameName_ID) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName_ID));
	}

	public void waitForFrame(int timeout, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}

	public void waitForFrame(int timeout, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
	}

	/**
	 * Wait Window utility
	 */

	public void waitForWindow(int timeout, int expectedNumberOfWindow) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.numberOfWindowsToBe(expectedNumberOfWindow));
	}

	/**
	 * Fluent Wait
	 */
	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeout
	 * @param pollingTime
	 * @return
	 */
	public void waitForVisibilityOfElementWithPollingInterval(int timeout, int pollingTime, By locator) {
		Wait wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeout))
				.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
				.pollingEvery(Duration.ofMillis(pollingTime)).withMessage("====ELEMENT NOT FOUND EXCEPTION===");
		wait.until(ExpectedConditions.visibilityOf(getElement(locator)));
	}

	public void isPageLoaded(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.jsReturnsValue("document.readyState=='complete'")).toString();
	}
}
