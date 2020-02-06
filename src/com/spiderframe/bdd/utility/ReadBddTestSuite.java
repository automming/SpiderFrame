package com.spiderframe.bdd.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadBddTestSuite {

	List<Class<?>> runnerClassesList;

	public ReadBddTestSuite() {
		runnerClassesList = new ArrayList<Class<?>>();
	}

	public Object[][] setData() {
		System.out.println("Collecting data...");
		runnerClassesList = readWorkbook();
		Object[][] objectData = new Object[runnerClassesList.size()][1];
		System.out.println("Executable runner classes are:");
		for (int i = 0; i < runnerClassesList.size(); i++) {
			objectData[i][0] = runnerClassesList.get(i);
			System.out.println(runnerClassesList.get(i).getName());
		}
		return objectData;
	}

	public List<Class<?>> readWorkbook() {
		String testsuiteFileName = "input\\Testsuite_BDD.xlsx";
		Workbook workbook = getWorkbookType(testsuiteFileName);
		return readMasterSheet(workbook);
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
	List<Class<?>> readMasterSheet(Workbook book) {
		Sheet masterSheet = book.getSheet("RunnerClasses");
		int rows = masterSheet.getPhysicalNumberOfRows();
		int columns = masterSheet.getRow(0).getPhysicalNumberOfCells();
		System.out.println("Rows in Master sheet: " + rows);
		System.out.println("Columns in Master sheet: " + columns);
		if (rows > 1)
			for (int i = 1; i < rows; i++) {
				Row row = masterSheet.getRow(i);
				Cell classCell = row.getCell(1);
				Cell executeCell = row.getCell(2);
				classCell.setCellType(CellType.STRING);
				executeCell.setCellType(CellType.STRING);
				try {
					if ("Yes".equalsIgnoreCase(executeCell.getStringCellValue().trim())
							|| executeCell.getStringCellValue().trim() == "Yes") {
						runnerClassesList.add(Class.forName(classCell.getStringCellValue().trim()));
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		return runnerClassesList;
	}

}
