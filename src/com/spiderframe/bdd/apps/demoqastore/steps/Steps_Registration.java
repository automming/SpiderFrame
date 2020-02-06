package com.spiderframe.bdd.apps.demoqastore.steps;

import org.openqa.selenium.WebDriver;

import com.spiderframe.bdd.apps.demoqastore.pom.Home;
import com.spiderframe.bdd.apps.demoqastore.pom.Login;
import com.spiderframe.bdd.apps.demoqastore.pom.Registration;
import com.spiderframe.selenium.utility.SeleniumLibrary;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Steps_Registration {

	SeleniumLibrary library;
	WebDriver driver;
	Home home;
	Login login;
	Registration registration;

	public Steps_Registration() {
		library = new SeleniumLibrary();
		driver = SeleniumLibrary.getDriver("chrome");
		login = new Login(driver);
		registration = new Registration(driver);
	}
	
	@Given("^user opens DemoQA store$")
	public void user_opens_DemoQA_store() throws Throwable {
		home = new Home(driver);
		home.clickMyAccount();
	}

	@When("^user clicks on registration link$")
	public void user_clicks_on_registration_link() throws Throwable {
		login.clickRegister();
	}

	@And("^user adds registration details$")
	public void user_adds_registration_details() throws Throwable {
		registration.addUsername("username");
		registration.addEmail("username@email.com");
		registration.addCaptcha("Two into Five is Ten");
	}

	@Then("^user should complete registration$")
	public void user_should_complete_registration() throws Throwable {
		registration.submit();
		driver.quit();
	}

}
