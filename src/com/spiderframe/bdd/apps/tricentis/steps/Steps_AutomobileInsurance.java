package com.spiderframe.bdd.apps.tricentis.steps;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.spiderframe.bdd.apps.tricentis.pom.TricentisVI_Home;
import com.spiderframe.bdd.apps.tricentis.pom.TricentisVI_InsuranceData;
import com.spiderframe.bdd.apps.tricentis.pom.TricentisVI_PriceOption;
import com.spiderframe.bdd.apps.tricentis.pom.TricentisVI_ProductData;
import com.spiderframe.bdd.apps.tricentis.pom.TricentisVI_SendQuote;
import com.spiderframe.bdd.apps.tricentis.pom.TricentisVI_VehicleData;
import com.spiderframe.selenium.utility.SeleniumLibrary;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Steps_AutomobileInsurance {

	WebDriver driver = SeleniumLibrary.getDriver("chrome");
	TricentisVI_Home home;
	TricentisVI_VehicleData vehicleData;
	TricentisVI_InsuranceData insuranceData;
	TricentisVI_ProductData productData;
	TricentisVI_PriceOption priceOption;
	TricentisVI_SendQuote sendQuote;

	@Given("^user opens Tricentis Automobile Insurance Application$")
	public void user_opens_Tricentis_Automobile_Insurance_Application() {
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		home = new TricentisVI_Home(driver);
		vehicleData = new TricentisVI_VehicleData(driver);
		insuranceData = new TricentisVI_InsuranceData(driver);
		productData = new TricentisVI_ProductData(driver);
		priceOption = new TricentisVI_PriceOption(driver);
		sendQuote = new TricentisVI_SendQuote(driver);
		home.clickAutomobile();
	}

	@When("^fills Vehicle Data details$")
	public void fills_Vehicle_Data_details() {
		vehicleData.fillVehicleData();
		vehicleData.clickNext();
	}

	@And("^fills Insurance Data details$")
	public void fills_Insurance_Data_details() {
		insuranceData.fillInsuranceData();
		insuranceData.clickNext();
	}

	@And("^fills Product Data details$")
	public void fills_Product_Data_details() {
		productData.fillProductData();
		productData.clickNext();
	}

	@And("^selects Price Option$")
	public void selects_Price_Option() {
		priceOption.complete();
		priceOption.clickNext();
	}

	@And("^fills Send Quote details$")
	public void fills_Send_Quote_details() {
		sendQuote.fillSendQuote();
		sendQuote.clickSend();
	}

	@Then("^user should successfully sent quote and verifies success alert$")
	public void user_should_successfully_sent_quote_and_verifies_success_alert() {
		sendQuote.verifySuccessText();
		sendQuote.ok();
		home.quit(driver);
	}

}
