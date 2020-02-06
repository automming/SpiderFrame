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

public class TricentisVI_InsuranceData {

	@FindBy(id = "firstname")
	private WebElement firstName;

	@FindBy(id = "lastname")
	private WebElement lastName;

	@FindBy(id = "birthdate")
	private WebElement birthDate;

	@FindBy(xpath = "//input[@id='gendermale']/following-sibling::span")
	private WebElement genderMale;

	@FindBy(id = "genderfemale")
	private WebElement genderFemale;

	@FindBy(id = "streetaddress")
	private WebElement streetAddress;

	@FindBy(id = "country")
	private WebElement country;

	@FindBy(id = "zipcode")
	private WebElement zipCode;

	@FindBy(id = "city")
	private WebElement city;

	@FindBy(id = "occupation")
	private WebElement occupation;

	@FindBy(xpath = "//input[@id='speeding']/following-sibling::span")
	private WebElement speeding;

	@FindBy(xpath = "//input[@id='bungeejumping']/following-sibling::span")
	private WebElement bungeeJumping;

	@FindBy(xpath = "//input[@id='cliffdiving']/following-sibling::span")
	private WebElement cliffDiving;

	@FindBy(xpath = "//input[@id='skydiving']/following-sibling::span")
	private WebElement skydiving;

	@FindBy(xpath = "//input[@id='other']/following-sibling::span")
	private WebElement other;

	@FindBy(id = "website")
	private WebElement website;

	@FindBy(id = "picture")
	private WebElement picture;

	@FindBy(id = "preventervehicledata")
	private WebElement previous;

	@FindBy(id = "nextenterproductdata")
	private WebElement next;

	PropertyReader reader;

	public TricentisVI_InsuranceData(WebDriver driver) {
		PageFactory.initElements(driver, this);
		reader = new PropertyReader("input/Tricentis.properties");
	}

	public void fillInsuranceData() {
		firstName(reader.getValue("firstName"));
		lastName(reader.getValue("lastName"));
		birthDate(reader.getValue("dob"));
		gender(reader.getValue("gender"));
		streetAddress(reader.getValue("streetAddress"));
		country(reader.getValue("country"));
		zipCode(reader.getValue("zipcode"));
		city(reader.getValue("city"));
		occupation(reader.getValue("occupation"));
		hobbies(reader.getValue("hobbies"));
		website(reader.getValue("website"));
		picture(reader.getValue("picture"));
	}

	public void firstName(String firstname) {
		this.firstName.sendKeys(firstname);
	}

	public void lastName(String lastname) {
		this.lastName.sendKeys(lastname);
	}

	public void birthDate(String dob) {
		this.birthDate.sendKeys(dob);
	}

	public void gender(String gender) {
		if (gender.equalsIgnoreCase("female") || gender.equalsIgnoreCase("f"))
			genderFemale.click();
		else
			genderMale.click();
	}

	public void streetAddress(String address) {
		this.streetAddress.sendKeys(address);
	}

	public void country(String country) {
		new Select(this.country).selectByValue(country);
	}

	public void zipCode(String zipcode) {
		this.zipCode.sendKeys(zipcode);
	}

	public void city(String city) {
		this.city.sendKeys(city);
	}

	public void occupation(String occupation) {
		new Select(this.occupation).selectByValue(occupation);
	}

	public void hobbies(String hobbies) {
		String[] listOfHobbies = hobbies.split(",");
		if (listOfHobbies.length > 1)
			for (int i = 0; i < listOfHobbies.length; i++) {
				if (listOfHobbies[i].trim().equalsIgnoreCase("speeding"))
					speeding();
				else if (listOfHobbies[i].trim().equalsIgnoreCase("bungee jumping"))
					bungeeJumping();
				else if (listOfHobbies[i].trim().equalsIgnoreCase("cliff diving"))
					cliffDiving();
				else if (listOfHobbies[i].trim().equalsIgnoreCase("skydiving"))
					skydiving();
				else
					other();
			}
	}

	public void speeding() {
		this.speeding.click();
	}

	public void bungeeJumping() {
		this.bungeeJumping.click();
	}

	public void cliffDiving() {
		this.cliffDiving.click();
	}

	public void skydiving() {
		this.skydiving.click();
	}

	public void other() {
		this.other.click();
	}

	public void website(String url) {
		this.website.sendKeys(url);
	}

	public void picture(String picturePath) {
		this.picture.sendKeys(picturePath);
	}

	public void clickPrevious() {
		this.previous.click();
	}

	public void clickNext() {
		this.next.click();
	}

}
