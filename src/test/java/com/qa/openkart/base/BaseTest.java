package com.qa.openkart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.openkart.factory.DriverFactory;
import com.qa.openkart.pages.AccountPage;
import com.qa.openkart.pages.LoginPage;
import com.qa.openkart.pages.ProductInfoPage;
import com.qa.openkart.pages.SearchResultPage;

@Listeners(ChainTestListener.class)
public class BaseTest {
	WebDriver driver;
	protected Properties prop;
	DriverFactory df;
	protected LoginPage loginpage;
	protected AccountPage accpage;
	protected SearchResultPage searchpage;
	protected ProductInfoPage productpage;

	@Parameters({ "browser" })
	@BeforeTest
	public void setUp(@Optional("edge") String browserName) {
		df = new DriverFactory();
		prop = df.initProp();
		if (browserName != null) {
			prop.setProperty("browser", browserName);
		}
		driver = df.initDriver(prop);
		loginpage = new LoginPage(driver);
	}

	@AfterMethod
	public void takescreenshot(ITestResult result) {
		if (!result.isSuccess()) {
			ChainTestListener.embed(DriverFactory.getScreenshot(), "img/png");
		}
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
