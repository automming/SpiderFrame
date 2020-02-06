package com.spiderframe.selenium.testsuite;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.spiderframe.common.utility.ExtentManager;
import com.spiderframe.selenium.pom.TestCase;
import com.spiderframe.selenium.utility.ReadSeleniumTestSuite;
import com.spiderframe.selenium.utility.TestCaseRunner;

public class Selenium_TestSuiteRunner {

	private ExtentManager extent;
	private String fileName;

	@BeforeSuite
	public void beforeSuite() {
		extent = new ExtentManager();
		setFileName("report/ExtentReport");
		extent.createInstance(fileName+ ".html", "Spiderframe - ExtentReports");
	}

	@BeforeMethod
	public synchronized void beforeMethod() {

	}

	@AfterMethod
	public synchronized void afterMethod() {
		extent.finish();
	}

	@Test(dataProvider = "getData")
	public void test(TestCase testcase, String browser) {
		System.out.println("Test Started");
		extent.initializeExtentTest(testcase.getName(), testcase.getTestcaseDescription(), "Akash", "Regression");
		TestCaseRunner testCaseRunner = new TestCaseRunner(browser);
		if (!testCaseRunner.runTest(testcase, extent))
			Assert.fail();
		System.out.println("Test Ended");
	}

	@DataProvider()
	public Object[][] getData() {
		ReadSeleniumTestSuite readTestSuite = new ReadSeleniumTestSuite();
		return readTestSuite.setData();
	}

	public void setFileName(String fileName) {
		SimpleDateFormat date = new SimpleDateFormat("dd_MMM_yyyy-hh_mm_ss");
		String time = date.format(Calendar.getInstance().getTime());
		this.fileName = fileName + "-" + time;
	}

}
