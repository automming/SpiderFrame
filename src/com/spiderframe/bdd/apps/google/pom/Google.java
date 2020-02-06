package com.spiderframe.bdd.apps.google.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author akash.a.murumkar
 * @category Page Object Model
 */

public class Google {

	private WebDriver driver;
	private By search = By.name("q");
	private By searchButton = By.id("_fZl");

	public Google(WebDriver driver) {
		this.driver = driver;
	}

	public void open() {
		driver.get("http://www.google.com/");
	}

	public void search(String query) {
		driver.findElement(search).sendKeys(query);
	}

	public void clickSearch() {
		driver.findElement(searchButton).click();
	}

	public void quit() {
		driver.quit();
	}
}
