package com.spiderframe.selenium.utility;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;
import com.spiderframe.common.utility.ExtentManager;
import com.spiderframe.common.utility.LogWorker;
import com.spiderframe.selenium.pom.Doer;
import com.spiderframe.selenium.pom.TestCase;

public class TestCaseRunner {

	SeleniumLibrary library;
	WebDriver driver;

	public TestCaseRunner(String browser) {
		library = new SeleniumLibrary();
		driver = SeleniumLibrary.getDriver(browser);
	}

	public boolean runTest(TestCase testcase, ExtentManager extent) {
		LogWorker logWorker = new LogWorker(testcase.getName());
		int k = 0;
		try {
			for (int j = 0; j < testcase.getSteps().size(); j++) {
				Doer doer = new Doer();
				doer.setKeyword(testcase.getKeywords().get(j));
				doer.setLocator(testcase.getLocators().get(j));
				doer.setTarget(testcase.getTargets().get(j));
				doer.setData(testcase.getValues().get(j));
				boolean step = library.perform(driver, doer);
				logWorker.info(step + " | " + testcase.getDescriptions().get(j));
				extent.log(Status.PASS, testcase.getDescriptions().get(j));
				if (testcase.getKeywords().get(j).toLowerCase().equals("capturescreen"))
					extent.addImageFromPath(library.screenshotFilePath);
				k = j;
			}
		} catch (Exception e) {
			logWorker.error(false + " | " + testcase.getDescriptions().get(k + 1) + " | \n" + e.getMessage());
			Doer doer = new Doer();
			doer.setKeyword("capturescreen");
			doer.setData("Error_" + testcase.getName());
			library.perform(driver, doer);
			extent.createImageForLog(testcase.getDescriptions().get(k + 1), library.screenshotFilePath);
			return false;
		}
		driver.quit();
		return true;
	}

}
