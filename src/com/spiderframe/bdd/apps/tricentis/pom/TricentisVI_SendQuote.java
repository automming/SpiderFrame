package com.spiderframe.bdd.apps.tricentis.pom;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.spiderframe.common.utility.PropertyReader;

public class TricentisVI_SendQuote {

	@FindBy(id = "email")
	private WebElement email;

	@FindBy(id = "phone")
	private WebElement phone;

	@FindBy(id = "username")
	private WebElement username;

	@FindBy(id = "password")
	private WebElement password;

	@FindBy(id = "confirmpassword")
	private WebElement confirmPassword;

	@FindBy(id = "Comments")
	private WebElement comments;

	@FindBy(id = "prevselectpriceoption")
	private WebElement previous;

	@FindBy(id = "sendemail")
	private WebElement send;

	@FindBy(xpath = "//h2[contains(text(),'Sending e-mail success!')]")
	private WebElement successText;

	@FindBy(className = "confirm")
	private WebElement ok;

	PropertyReader reader;

	public TricentisVI_SendQuote(WebDriver driver) {
		PageFactory.initElements(driver, this);
		reader = new PropertyReader("input/Tricentis.properties");
	}

	public void fillSendQuote() {
		email(reader.getValue("email"));
		phone(reader.getValue("phone"));
		username(reader.getValue("username"));
		password(reader.getValue("password"));
		confirmPassword(reader.getValue("confirmPassword"));
		comments(reader.getValue("comments"));
	}

	public void email(String email) {
		this.email.sendKeys(email);
	}

	public void phone(String phone) {
		this.phone.sendKeys(phone);
	}

	public void username(String username) {
		this.username.sendKeys(username);
	}

	public void password(String password) {
		this.password.sendKeys(password);
	}

	public void confirmPassword(String confirmPassword) {
		this.confirmPassword.sendKeys(confirmPassword);
	}

	public void comments(String comments) {
		this.comments.sendKeys(comments);
	}

	public void clickPrevious() {
		this.previous.click();
	}

	public void clickSend() {
		this.send.click();
	}

	public void verifySuccessText() {
		assertEquals("Sending e-mail success!", successText.getText());
	}

	public void ok() {
		this.ok.click();
	}

}
