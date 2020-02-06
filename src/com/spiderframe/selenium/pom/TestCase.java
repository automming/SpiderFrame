package com.spiderframe.selenium.pom;

import java.util.List;

public class TestCase {

	String name;
	String testcaseDescription;
	List<String> steps;
	List<String> descriptions;
	List<String> keywords;
	List<String> locators;
	List<String> targets;
	List<String> values;

	public TestCase(String name, String testcaseDescription, List<String> steps, List<String> descriptions,
			List<String> keywords, List<String> locators, List<String> targets, List<String> values) {
		this.name = name;
		this.testcaseDescription = testcaseDescription;
		this.steps = steps;
		this.descriptions = descriptions;
		this.keywords = keywords;
		this.locators = locators;
		this.targets = targets;
		this.values = values;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getTestcaseDescription() {
		return testcaseDescription;
	}

	public void setTestcaseDescription(String testcaseDescription) {
		this.testcaseDescription = testcaseDescription;
	}

	public List<String> getSteps() {
		return steps;
	}

	public void setSteps(List<String> steps) {
		this.steps = steps;
	}

	public List<String> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(List<String> descriptions) {
		this.descriptions = descriptions;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public List<String> getLocators() {
		return locators;
	}

	public void setLocators(List<String> locators) {
		this.locators = locators;
	}

	public List<String> getTargets() {
		return targets;
	}

	public void setTargets(List<String> targets) {
		this.targets = targets;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

	@Override
	public String toString() {
		return this.name;
	}

	public String fullTestcase() {
		String output = "Test Case: " + this.name + "\n|Step|Description|Keyword|Locator|Target|Value|\n";
		if (this.steps.size() > 0) {
			for (int i = 0; i < this.steps.size(); i++) {
				output += "|" + this.steps.get(i) + "|" + this.descriptions.get(i) + "|" + this.keywords.get(i) + "|"
						+ this.locators.get(i) + "|" + this.targets.get(i) + "|" + this.values.get(i) + "|\n";
			}
		}
		return output;
	}

}
