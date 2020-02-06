package com.spiderframe.selenium.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.spiderframe.selenium.pom.Master;
import com.spiderframe.selenium.pom.TestCase;

public class ReadSeleniumTestSuite {

	Map<TestCase, String> testcaseBrowserMap;

	public ReadSeleniumTestSuite() {
		testcaseBrowserMap = new HashMap<TestCase, String>();
	}

	public Object[][] setData() {
		System.out.println("Collecting data...");
		testcaseBrowserMap = readWorkbook();
		Object[][] objectData = new Object[testcaseBrowserMap.size()][2];
		Set<Entry<TestCase, String>> entries = testcaseBrowserMap.entrySet();
		Iterator<Entry<TestCase, String>> iterate = entries.iterator();
		int i = 0;
		while (iterate.hasNext()) {
			Map.Entry<TestCase, String> mapping = iterate.next();
			objectData[i][0] = mapping.getKey();
			objectData[i][1] = mapping.getValue();
			i++;
		}
		return objectData;
	}

	public Map<TestCase, String> readWorkbook() {
		String testsuiteFileName = "input\\Testsuite_Selenium.xlsx";
		Workbook workbook = getWorkbookType(testsuiteFileName);
		Master master = readMaster(workbook);
		return getExecutableTestcases(master, workbook);
	}

	// Choose type of workbook to read
	Workbook getWorkbookType(String fileName) {
		Workbook workbook = null;
		try {
			if (fileName.endsWith(".xls"))
				workbook = new HSSFWorkbook(new FileInputStream(new File(fileName)));
			else if (fileName.endsWith(".xlsx"))
				workbook = new XSSFWorkbook(new FileInputStream(new File(fileName)));
			else
				throw new InvalidFormatException("Invalid file Format");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return workbook;
	}

	// Iterate Master sheet to get list of test cases
	Master readMaster(Workbook book) {
		Master master = null;
		List<String> cases = new ArrayList<String>();
		List<String> testcases = new ArrayList<String>();
		List<String> descriptions = new ArrayList<String>();
		List<String> browsers = new ArrayList<String>();
		List<String> executes = new ArrayList<String>();
		Sheet masterSheet = book.getSheet("Master");
		int rows = masterSheet.getPhysicalNumberOfRows();
		int columns = masterSheet.getRow(0).getPhysicalNumberOfCells();
		System.out.println("Rows in Master sheet: " + rows);
		System.out.println("Columns in Master sheet: " + columns);
		if (rows > 1)
			for (int i = 1; i < rows; i++) {
				Row row = masterSheet.getRow(i);
				addCell(cases, row, 0);
				addCell(testcases, row, 1);
				addCell(descriptions, row, 2);
				addCell(browsers, row, 3);
				addCell(executes, row, 4);
			}
		master = new Master(cases, testcases, descriptions, browsers, executes);
		System.out.println(master.toString());
		return master;
	}

	// Filter to get only executable test cases
	Map<TestCase, String> getExecutableTestcases(Master master, Workbook book) {
		List<String> masterTestcases = master.getTestcases();
		List<String> masterDescriptions = master.getDescriptions();
		List<String> masterBrowsers = master.getBrowsers();
		List<String> masterExecutions = master.getExecutes();
		for (int i = 0; i < masterTestcases.size(); i++) {
			if (masterExecutions.get(i).equalsIgnoreCase("yes") || masterExecutions.get(i).contains("y")) {
				TestCase testcase = readTestCase(book.getSheet(masterTestcases.get(i)), masterDescriptions.get(i));
				String browser = masterBrowsers.get(i);
				testcaseBrowserMap.put(testcase, browser);
				System.out.println("Test Case to be executed: \n" + testcase.fullTestcase() + "\n");
			}
		}
		return testcaseBrowserMap;
	}

	// Read data from each test case sheet
	TestCase readTestCase(Sheet sheet, String testcaseDescription) {
		System.out.println("Reading Testcase Sheet: " + sheet.getSheetName());
		TestCase testcase = null;
		List<String> steps = new ArrayList<String>();
		List<String> descriptions = new ArrayList<String>();
		List<String> keywords = new ArrayList<String>();
		List<String> locators = new ArrayList<String>();
		List<String> targets = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		int rows = sheet.getPhysicalNumberOfRows();
		int columns = sheet.getRow(0).getPhysicalNumberOfCells();
		System.out.println("Rows in testcase sheet: " + rows);
		System.out.println("Columns in testcase sheet: " + columns);
		if (rows > 1)
			for (int i = 1; i < rows; i++) {
				Row row = sheet.getRow(i);
				addCell(steps, row, 0);
				addCell(descriptions, row, 1);
				addCell(keywords, row, 2);
				addCell(locators, row, 3);
				addCell(targets, row, 4);
				addCell(values, row, 5);
			}
		testcase = new TestCase(sheet.getSheetName(), testcaseDescription, steps, descriptions, keywords, locators,
				targets, values);
		return testcase;
	}

	// Add cell data to each test case step type example: step, keyword etc.
	void addCell(List<String> list, Row row, int column) {
		Cell cell = row.getCell(column);
		if (cell == null) {
			list.add("");
		} else {
			row.getCell(column).setCellType(CellType.STRING);
			list.add(cell.getStringCellValue());
		}
	}

}
