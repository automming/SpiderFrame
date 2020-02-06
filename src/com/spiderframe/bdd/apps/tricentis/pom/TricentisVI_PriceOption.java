package com.spiderframe.bdd.apps.tricentis.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.spiderframe.common.utility.PropertyReader;

public class TricentisVI_PriceOption {

	@FindBy(xpath = "//input[@id='selectsilver']/following-sibling::span")
	private WebElement silver;

	@FindBy(xpath = "//input[@id='selectgold']/following-sibling::span")
	private WebElement gold;

	@FindBy(xpath = "//input[@id='selectplatinum']/following-sibling::span")
	private WebElement platinum;

	@FindBy(xpath = "//input[@id='selectultimate']/following-sibling::span")
	private WebElement ultimate;

	@FindBy(id = "preventerproductdata")
	private WebElement previous;

	@FindBy(id = "nextsendquote")
	private WebElement next;
	
	PropertyReader reader;

	public TricentisVI_PriceOption(WebDriver driver) {
		PageFactory.initElements(driver, this);
		reader = new PropertyReader("input/Tricentis.properties");
	}

	public void complete() {
		option(reader.getValue("option"));
	}

	public void option(String option) {
		if (option.trim().equalsIgnoreCase("silver"))
			silver();
		else if (option.trim().equalsIgnoreCase("gold"))
			gold();
		else if (option.trim().equalsIgnoreCase("platinum"))
			platinum();
		else if (option.trim().equalsIgnoreCase("ultimate"))
			ultimate();
		else
			silver();
	}

	public void silver() {
		this.silver.click();
	}

	public void gold() {
		this.gold.click();
	}

	public void platinum() {
		this.platinum.click();
	}

	public void ultimate() {
		this.ultimate.click();
	}
	
	public void clickPrevious() {
		this.previous.click();
	}

	public void clickNext() {
		this.next.click();
	}

}
