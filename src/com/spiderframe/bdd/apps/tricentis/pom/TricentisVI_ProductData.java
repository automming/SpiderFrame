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

public class TricentisVI_ProductData {

	@FindBy(id = "startdate")
	private WebElement startDate;

	@FindBy(id = "insurancesum")
	private WebElement insuranceSum;

	@FindBy(id = "meritrating")
	private WebElement meritRating;

	@FindBy(id = "damageinsurance")
	private WebElement damageInsurance;

	@FindBy(xpath = "//input[@id='EuroProtection']/following-sibling::span")
	private WebElement euroProtection;

	@FindBy(xpath = "//input[@id='LegalDefenseInsurance']/following-sibling::span")
	private WebElement legalDefenseInsurance;

	@FindBy(id = "courtesycar")
	private WebElement courtesyCar;

	@FindBy(id = "preventerinsurancedata")
	private WebElement previous;

	@FindBy(id = "nextselectpriceoption")
	private WebElement next;

	PropertyReader reader;

	public TricentisVI_ProductData(WebDriver driver) {
		PageFactory.initElements(driver, this);
		reader = new PropertyReader("input/Tricentis.properties");
	}

	public void fillProductData() {
		startDate(reader.getValue("startDate"));
		insuranceSum(reader.getValue("insuranceSum"));
		meritRating(reader.getValue("meritRating"));
		damageInsurance(reader.getValue("damageInsurance"));
		products(reader.getValue("products"));
		courtesyCar(reader.getValue("courtesyCar"));
	}

	public void startDate(String startDate) {
		this.startDate.sendKeys(startDate);
	}

	public void insuranceSum(String sum) {
		new Select(this.insuranceSum).selectByValue(sum);
	}

	public void meritRating(String rating) {
		new Select(this.meritRating).selectByValue(rating);
	}

	public void damageInsurance(String insurance) {
		new Select(this.damageInsurance).selectByValue(insurance);
	}

	public void courtesyCar(String courtesy) {
		new Select(this.courtesyCar).selectByValue(courtesy);
	}

	public void products(String products) {
		String[] listOfProducts = products.split(",");
		if (listOfProducts.length > 1)
			for (int i = 0; i < listOfProducts.length; i++) {
				if (listOfProducts[i].trim().equalsIgnoreCase("Euro Protection"))
					euroProtection();
				else if (listOfProducts[i].trim().equalsIgnoreCase("Legal Defense Insurance"))
					legalDefenseInsurance();
				else {
					euroProtection();
					legalDefenseInsurance();
				}
			}
	}

	public void euroProtection() {
		this.euroProtection.click();
	}

	public void legalDefenseInsurance() {
		this.legalDefenseInsurance.click();
	}

	public void clickPrevious() {
		this.previous.click();
	}

	public void clickNext() {
		this.next.click();
	}

}
