package com.divsly.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgotPasswordPage {

	WebDriver ldriver;

	public ForgotPasswordPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(xpath = "//input[@placeholder='Enter your email']")
	WebElement inputemail;

	@FindBy(xpath = "//button[text()='Submit']")
	WebElement buttonSubmit;

	@FindBy(xpath = "//span[text()='Cancel']")
	WebElement buttonCancel;

	@FindBy(xpath = "//span[text()='This account does not exist!']")
	WebElement textAccountDoesntExist;

	public void updatePassword(String email) {
		inputemail.clear();
		inputemail.sendKeys(email);
		buttonSubmit.click();
	}

	public void cancelUpdatePassword() {
		buttonCancel.click();
	}

}
