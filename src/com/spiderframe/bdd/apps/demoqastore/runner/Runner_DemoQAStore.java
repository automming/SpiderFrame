package com.spiderframe.bdd.apps.demoqastore.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "features/DemoQAStore.feature"
		, tags = "@DemoQAStore"
		, glue = "com.spiderframe.bdd.apps.demoqastore.steps"
		)

public class Runner_DemoQAStore {

}
