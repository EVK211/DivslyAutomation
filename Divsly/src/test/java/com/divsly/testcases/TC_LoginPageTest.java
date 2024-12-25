package com.divsly.testcases;


import org.testng.annotations.Test;

import com.divsly.pageobjects.SignInPage;

public class TC_LoginPageTest extends BaseClass {

	@Test
	public void userLogin() throws InterruptedException
	{
		driver.get(url);
		logger.info("url opened");
		logger.info(browser);
		SignInPage signInPage = new SignInPage(driver);
		signInPage.userLogin("evinodkumar211@gmail.com", "Secundrabad1!");
		//Thread.sleep(Duration.ofSeconds(longWaitime));
		//Assert.assertFalse(signInPage.textAccountNotFoundIsVisible());
		//Thread.sleep(Duration.ofSeconds(medWaitime));
		logger.info("Ended");
	}
	
}
