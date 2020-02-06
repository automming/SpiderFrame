package com.spiderframe.selenium.pom;

import java.util.List;

public class Master {

	List<String> cases;
	List<String> testcases;
	List<String> descriptions;
	List<String> browsers;
	List<String> executes;

	public Master(List<String> cases, List<String> testcases, List<String> descriptions, List<String> browsers,
			List<String> executes) {
		this.cases = cases;
		this.testcases = testcases;
		this.descriptions = descriptions;
		this.browsers = browsers;
		this.executes = executes;
	}

	public List<String> getCases() {
		return cases;
	}

	public void setCases(List<String> cases) {
		this.cases = cases;
	}

	public List<String> getTestcases() {
		return testcases;
	}

	public void setTestcases(List<String> testcases) {
		this.testcases = testcases;
	}

	public List<String> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(List<String> descriptions) {
		this.descriptions = descriptions;
	}

	public List<String> getBrowsers() {
		return browsers;
	}

	public void setBrowsers(List<String> browsers) {
		this.browsers = browsers;
	}

	public List<String> getExecutes() {
		return executes;
	}

	public void setExecutes(List<String> executes) {
		this.executes = executes;
	}

	@Override
	public String toString() {
		String output = "Master:\n|Case|Test Case Name|Description|Browser|Execute?|\n";
		if (this.cases.size() > 0) {
			for (int i = 0; i < this.cases.size(); i++) {
				output += "|" + this.cases.get(i) + "|" + this.testcases.get(i) + "|" + this.descriptions.get(i) + "|"
						+ this.browsers.get(i) + "|" + this.executes.get(i) + "|\n";
			}
		}
		return output;
	}

}
