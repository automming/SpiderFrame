package com.spiderframe.bdd.apps.demoqastore.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Home {

	@FindBy(className = "account_icon")
	private WebElement myAccount;

	public Home(WebDriver driver) {
		PageFactory.initElements(driver, this);
		driver.get("http://store.demoqa.com/");
	}

	public void clickMyAccount() {
		myAccount.click();
	}

}
