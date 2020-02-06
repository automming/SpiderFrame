package com.spiderframe.bdd.apps.google.steps;

import com.spiderframe.bdd.apps.google.pom.Google;
import com.spiderframe.selenium.utility.SeleniumLibrary;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Steps_Search {

	Google google;

	@Given("^user loads the browser$")
	public void user_loads_the_browser() throws Throwable {
		google = new Google(SeleniumLibrary.getDriver("chrome"));
	}

	@When("^user opens the Google site$")
	public void user_opens_the_Google_site() throws Throwable {
		google.open();
	}

	@And("^types \"([^\"]*)\" in the search$")
	public void types_in_the_search(String arg1) throws Throwable {
		google.search("Selenium");
	}

	@Then("^user should view the results$")
	public void user_should_view_the_results() throws Throwable {
		google.clickSearch();
		google.quit();
	}

}
