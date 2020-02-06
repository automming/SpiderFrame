package com.spiderframe.bdd.apps.demoqastore.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Registration {

	@FindBy(id = "user_login")
	private WebElement username;

	@FindBy(xpath = "//*[@name='user_email']")
	private WebElement email;

	@FindBy(xpath = "//*[@id='aiowps-captcha-answer']")
	private WebElement captcha;

	@FindBy(id = "wp-submit")
	private WebElement submit;

	public Registration(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void addUsername(String username) {
		this.username.sendKeys(username);
	}

	public void addEmail(String email) {
		this.email.sendKeys(email);
	}

	public void addCaptcha(String captcha) {
		this.captcha.sendKeys(captcha);
	}

	public void submit() {
		this.submit.click();
	}

}
