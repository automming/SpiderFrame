package com.spiderframe.bdd.apps.demoqastore.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {

	@FindBy(xpath = "//a[text()='Register']")
	private WebElement register;

	public Login(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void clickRegister() {
		register.click();
	}

}
