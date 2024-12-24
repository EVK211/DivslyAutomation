package com.divsly.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {

	WebDriver ldriver;

	public SignInPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(xpath = "//input[@type='email']")
	WebElement inputUserEmail;

	@FindBy(xpath = "//input[@type='password']")
	WebElement inputUserPassword;

	@FindBy(xpath = "//button[text()='Continue']")
	WebElement buttonContinue;

	@FindBy(linkText = "/signup")
	WebElement linkSignUp;

	@FindBy(linkText = "/forgotPassword")
	WebElement linkForgotPassword;

	@FindBy(xpath = "//span[text()='Sign in with Google']")
	WebElement buttonSignInWithGoogle;

	@FindBy(xpath = "//span[text()='Please enter a valid email']")
	WebElement textenteraValidEmail;

	@FindBy(xpath = "//span[text()='Account not found.']")
	WebElement textAccountNotFound;

	public void userLogin(String email, String password) {
		inputUserEmail.clear();
		inputUserEmail.sendKeys(email);
		inputUserPassword.clear();
		inputUserPassword.sendKeys(password);
		buttonContinue.click();
	}

//	public void enterEmailAddress(String email) {
//		inputUserEmail.clear();
//		inputUserEmail.sendKeys(email);
//	}
//
//	public void enterPasswor(String password) {
//		inputUserPassword.clear();
//		inputUserPassword.sendKeys(password);
//	}
//
//	public void clickSignin() {
//		buttonContinue.click();
//	}

	public void clickSignup() {
		linkSignUp.click();
	}

	public void clickForgotPassword() {
		linkForgotPassword.click();
	}

	public void clickSigninWithGoogle() {
		buttonSignInWithGoogle.click();
	}
	
	public boolean textAccountNotFoundIsVisible()
	{
		return textAccountNotFound.isDisplayed();
	}

}
