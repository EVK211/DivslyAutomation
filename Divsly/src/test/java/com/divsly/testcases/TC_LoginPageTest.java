package com.divsly.testcases;

import com.divsly.pageobjects.SignInPage;

public class TC_LoginPageTest extends BaseClass {

	
	public void userLogin()
	{
		driver.get(url);
		logger.info("url opened");
		SignInPage signInPage = new SignInPage(driver);
		signInPage.userLogin("evinodKumar211@gmail.com", "Secundrabad1!");
		
	}
	
}
