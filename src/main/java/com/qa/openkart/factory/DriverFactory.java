package com.qa.openkart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.openkart.exceptions.BrowserException;

public class DriverFactory {
	WebDriver driver;
	Properties prop;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser");
		System.out.println("Browser name is: " + browserName);
		OptionManager op = new OptionManager(prop);

		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			tlDriver.set(new ChromeDriver(op.getChromeOption()));
			break;
		case "firefox":
			tlDriver.set(new FirefoxDriver(op.getFirefoxOption()));
			break;
		case "edge":
			tlDriver.set(new EdgeDriver(op.getEdgeOption()));
			break;
		case "safari":
			tlDriver.set(new SafariDriver());
			break;
		default:
			throw new BrowserException("======INVALID BROWSER NAME=====");
		}
		getDriver().get(prop.getProperty("url"));
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		return getDriver();
	}

	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	public static File getScreenshot() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
	}

	public Properties initProp() {
		prop = new Properties();
		String envName = System.getProperty("env");
		FileInputStream ip = null;
		try {
			if (envName == null) {
				ip = new FileInputStream("./src/test/resources/config/sit.config.properties");
			} else {
				switch (envName.toLowerCase().trim()) {
				case "sit":
					ip = new FileInputStream("./src/test/resources/config/sit.config.properties");
					break;
				case "uat":
					ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
					break;
				case "production":
					ip = new FileInputStream("./src/test/resources/config/production.config.properties");
					break;
				default:
					throw new BrowserException("====INVALID ENVIRONMENT===");
				}
			}
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

}
