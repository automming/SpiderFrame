package com.spiderframe.bdd.apps.newtours.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "features/NewTours.feature"
		, tags = "@NewTours"
		, glue = "com.spiderframe.bdd.apps.newtours.steps"
		)

public class Runner_NewTours {

}
