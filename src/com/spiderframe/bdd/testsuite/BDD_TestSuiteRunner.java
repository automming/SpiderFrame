package com.spiderframe.bdd.testsuite;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.spiderframe.bdd.utility.ReadBddTestSuite;

public class BDD_TestSuiteRunner {

	@Test(dataProvider = "getData")
	public void test(Class<?> clazz) {
		System.out.println("Test Started");

		Result result = JUnitCore.runClasses(clazz);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
			Assert.fail();
		}

		System.out.println("Test Ended");
	}

	@DataProvider(parallel = true)
	public Object[][] getData() {
		ReadBddTestSuite readTestSuite = new ReadBddTestSuite();
		return readTestSuite.setData();
	}

}
