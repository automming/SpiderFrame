package com.spiderframe.bdd.apps.tricentis.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "features/AutomobileInsurance.feature"
		, tags = "@TricentisAutomobileInsurance"
		, glue = "com.spiderframe.bdd.apps.tricentis.steps"
		)

public class Runner_TricentisVI_Automobile {

}
