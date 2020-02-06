package com.spiderframe.bdd.apps.tricentis.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.spiderframe.common.utility.PropertyReader;

/**
 * @author akash.a.murumkar
 * @category Page Factory
 */

public class TricentisVI_VehicleData {

	@FindBy(id = "make")
	private WebElement make;

	@FindBy(id = "engineperformance")
	private WebElement enginePerformance;

	@FindBy(id = "dateofmanufacture")
	private WebElement dateOfManufacture;

	@FindBy(id = "numberofseats")
	private WebElement numberOfSeats;

	@FindBy(id = "fuel")
	private WebElement fuelType;

	@FindBy(id = "listprice")
	private WebElement listPrice;

	@FindBy(id = "licenseplatenumber")
	private WebElement licensePlateNumber;

	@FindBy(id = "annualmileage")
	private WebElement annualMileage;

	@FindBy(id = "nextenterinsurantdata")
	private WebElement next;

	PropertyReader reader;

	public TricentisVI_VehicleData(WebDriver driver) {
		PageFactory.initElements(driver, this);
		reader = new PropertyReader("input/Tricentis.properties");
	}

	public void fillVehicleData() {
		make(reader.getValue("maker"));
		enginePerformance(reader.getValue("enginePerformance"));
		dateOfManufacture(reader.getValue("manufactureDate"));
		numberOfSeats(reader.getValue("seats"));
		fuelType(reader.getValue("fuel"));
		listPrice(reader.getValue("price"));
		licensePlateNumber(reader.getValue("plate"));
		annualMileage(reader.getValue("mileage"));
	}

	public void make(String makerName) {
		new Select(make).selectByValue(makerName);
	}

	public void enginePerformance(String kW) {
		enginePerformance.sendKeys(kW);
	}

	public void dateOfManufacture(String date) {
		dateOfManufacture.sendKeys(date);
	}

	public void numberOfSeats(String seats) {
		new Select(numberOfSeats).selectByValue(seats);
	}

	public void fuelType(String fuel) {
		new Select(fuelType).selectByValue(fuel);
	}

	public void listPrice(String price) {
		listPrice.sendKeys(price);
	}

	public void licensePlateNumber(String plateNumber) {
		licensePlateNumber.sendKeys(plateNumber);
	}

	public void annualMileage(String mileage) {
		annualMileage.sendKeys(mileage);
	}

	public void clickNext() {
		next.click();
	}

}
