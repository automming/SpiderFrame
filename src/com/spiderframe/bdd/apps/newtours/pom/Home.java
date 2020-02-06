package com.spiderframe.bdd.apps.newtours.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Home {

	@FindBy(xpath = "//*[contains(text(), 'REGISTER')]")
	private WebElement register;

	public Home(WebDriver driver) {
		PageFactory.initElements(driver, this);
		driver.get("http://newtours.demoaut.com/");
	}

	public void clickRegister() {
		register.click();
	}

}
