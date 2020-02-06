package com.spiderframe.bdd.apps.google.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "features/Google.feature"
		, tags = "@Google"
		, glue = "com.spiderframe.bdd.apps.google.steps"
		)

public class Runner_Google {

}
