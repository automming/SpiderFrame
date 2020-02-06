package com.spiderframe.bdd.apps.tricentis.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author akash.a.murumkar
 * @category Page Factory
 */

public class TricentisVI_Home {

	@FindBy(id = "tricentis_logo")
	private WebElement home;

	@FindBy(id = "nav_automobile")
	private WebElement automobile;

	@FindBy(id = "nav_truck")
	private WebElement truck;

	@FindBy(id = "nav_motorcycle")
	private WebElement motorcycle;

	@FindBy(id = "nav_camper")
	private WebElement camper;

	public TricentisVI_Home(WebDriver driver) {
		PageFactory.initElements(driver, this);
		driver.manage().window().maximize();
		driver.get("http://sampleapp.tricentis.com/");
	}

	public void clickHome() {
		home.click();
	}

	public void clickAutomobile() {
		automobile.click();
	}

	public void clickTruck() {
		truck.click();
	}

	public void clickMotorCyle() {
		motorcycle.click();
	}

	public void clickCamper() {
		camper.click();
	}

	public void quit(WebDriver driver) {
		driver.quit();
	}

}
