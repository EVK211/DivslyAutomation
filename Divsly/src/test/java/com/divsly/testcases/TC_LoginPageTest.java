package com.divsly.testcases;

import org.testng.annotations.Test;

import com.divsly.pageobjects.SignInPage;

public class TC_LoginPageTest extends BaseClass {

	@Test
	public void userLogin() throws InterruptedException {
		driver.get(url);
		loggingMethod("info", "url:" + url + "opened");
		SignInPage signInPage = new SignInPage(driver);
		signInPage.userLogin("evinodkumar211@gmail.com", "Secundrabad1!");
		loggingMethod("info", "Logged in successfully");
		logger.info("Ended");
	}

}
