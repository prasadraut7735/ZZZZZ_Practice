package com.qa.openkart.listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qa.openkart.factory.DriverFactory;

import io.qameta.allure.Attachment;

public class TestAllureListener implements ITestListener {

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	// Capture screenshot and attach it to Allure report
	@Attachment(value = "Page screenshot", type = "image/png")
	public byte[] saveScreenshotPNG(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	// Capture a log and attach it to Allure report
	@Attachment(value = "log", type = "text/plain")
	public static String saveTextLog(String message) {
		return message;
	}

	// Attach HTML content to Allure report (can be used for advanced logging)
	@Attachment(value = "html", type = "text/html")
	public static String attachHtml(String html) {
		return html;
	}

	@Override
	public void onStart(ITestContext iTestContext) {
		System.out.println("I am in onStart method: " + iTestContext.getName());
	}

	@Override
	public void onFinish(ITestContext iTestContext) {
		System.out.println("I am in onFinish method: " + iTestContext.getName());
	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
		System.out.println("I am in onTestStart method: " + getTestMethodName(iTestResult) + " start");
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		System.out.println("I am in onTestSuccess method: " + getTestMethodName(iTestResult) + " succeed");
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		System.out.println("I am in onTestFailure method: " + getTestMethodName(iTestResult) + " failed");

		// Capture screenshot if test fails
		if (DriverFactory.getDriver() instanceof WebDriver) {
			WebDriver driver = DriverFactory.getDriver();
			System.out.println("Screenshot captured for test case: " + getTestMethodName(iTestResult));
			saveScreenshotPNG(driver); // Attach screenshot to Allure report
		}

		// Attach failure log to Allure
		saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");
	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		System.out.println("I am in onTestSkipped method: " + getTestMethodName(iTestResult) + " skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		System.out.println("Test failed but it is in defined success ratio: " + getTestMethodName(iTestResult));
	}
}