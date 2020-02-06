package com.spiderframe.bdd.apps.newtours.steps;

import org.openqa.selenium.WebDriver;

import com.spiderframe.bdd.apps.newtours.pom.Home;
import com.spiderframe.bdd.apps.newtours.pom.Registration;
import com.spiderframe.selenium.utility.SeleniumLibrary;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Steps_Registration {

	SeleniumLibrary library;
	WebDriver driver;
	Home home;
	Registration registration;

	public Steps_Registration() {
		library = new SeleniumLibrary();
		driver = SeleniumLibrary.getDriver("chrome");
		registration = new Registration(driver);
	}

	@Given("^user opens NewTours$")
	public void user_opens_NewTours() throws Throwable {
		home = new Home(driver);
	}

	@When("^user clicks on registration link$")
	public void user_clicks_on_registration_link() throws Throwable {
		home.clickRegister();
	}

	@And("^user adds registration details$")
	public void user_adds_registration_details() throws Throwable {
		registration.setFirstName("FirstName");
		registration.setLastName("LastName");
		registration.setPhone("123456789");
		registration.setUserName("email@address.com");
		registration.setAddress1("Address Line 1");
		registration.setAddress2("Address Line 2");
		registration.setCity("City");
		registration.setState("State");
		registration.setPostalCode("560056");
		registration.setCountry("92");
	}

	@Then("^user should complete registration$")
	public void user_should_complete_registration() throws Throwable {
		driver.quit();
	}

}
