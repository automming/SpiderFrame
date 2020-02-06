package com.spiderframe.common.utility;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	private ExtentReports reports;
	private ExtentTest test;

	public void createInstance(String filePath, String reportName) {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(filePath);
		htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("Spiderframe - ExtentReports");
		htmlReporter.config().setReportName(reportName);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setTimeStampFormat("dd/MMM/yyyy hh:mm:ss a");
		htmlReporter.config().setEncoding("UTF-8");

		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);
	}

	public void initializeExtentTest(String testName, String description, String author, String category) {
		test = reports.createTest(testName, description);
		test.assignAuthor(author);
		test.assignCategory(category);
	}

	public void stepResult(String status, String details) {
		switch (status.toLowerCase()) {
		case "pass":
			test.pass(details);
			break;
		case "debug":
			test.debug(details);
			break;
		case "error":
			test.error(details);
			break;
		case "fail":
			test.fail(details);
			break;
		case "warning":
			test.warning(details);
			break;
		case "skip":
			test.skip(details);
			break;
		case "info":
			test.info(details);
			break;
		case "fatal":
			test.fatal(details);
			break;
		default:
			throw new IllegalArgumentException("Invalid log status for extent test");
		}
	}

	public void log(Status status, String details) {
		test.log(status, details);
	}

	public void addImageFromPath(String imagePath) {
		try {
			test.addScreenCaptureFromPath(imagePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createImageForLog(String details, String path) {
		try {
			test.fail(details, MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void finish() {
		reports.flush();
	}

	// Basic sample example
	public static void main(String[] args) {
		ExtentManager em = new ExtentManager();
		em.createInstance("report/extent.html", "Report 1");
		em.initializeExtentTest("Test Name 1", "Test Description", "Akash A. Murumkar", "Regression");
		em.log(Status.PASS, "This step is passed");
		em.stepResult("info", "This step is an info");
		em.log(Status.FAIL, "This step is failed");
		em.createImageForLog("details", "screenshot.png");
		em.reports.flush();
	}
}