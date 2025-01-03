package com.divsly.testcases;

import static org.testng.Assert.assertTrue;
import java.time.Duration;
import org.testng.annotations.Test;
import com.divsly.pageobjects.SideNavCommonPage;
import com.divsly.pageobjects.SignInPage;

public class TC_LoginPageTest2 extends BaseClass {

	@Test(priority = 1)
	public void userLogin() throws InterruptedException {
		driver.get(url);
		reportLog("url:" + url + "opened");
		System.out.println("her1");
		loggingMethod("info", "url:" + url + "opened");
		SignInPage signInPage = new SignInPage(driver);
		signInPage.userLogin("evinodkumar211@gmail.com", "Secundrabad1!");
		// waitforPageToLoad();
		reportLog("Logged in successfully");
		loggingMethod("info", "Logged in successfully");
		Thread.sleep(Duration.ofSeconds(implicitWaitTime));
		SideNavCommonPage sideNavCommonPage = new SideNavCommonPage(driver);
		assertTrue(driver.getCurrentUrl().contains("/dashboard"));
		reportLog("Assertion passed");
		sideNavCommonPage.logout();
		reportLog("Logged out successfully");
		loggingMethod("info", "Loggedout successfully");
	}

	// @Test(dependsOnMethods = { "userLogin" })
	// public void logout() {
	// SideNavCommonPage sideNavCommonPage = new SideNavCommonPage(driver);
	// sideNavCommonPage.logout();
	// loggingMethod("info", "Loggedout successfully");
	// }

	@Test(priority = 2)
	public void invaliduserLogin() throws InterruptedException {
		driver.get(url);
		loggingMethod("info", "url:" + url + "opened");
		SignInPage signInPage = new SignInPage(driver);
		signInPage.userLogin("evinodkumar2112334@gmail.com", "Secundrabad1!");
		assertTrue(signInPage.textAccountNotFoundIsVisible());
		assertTrue(driver.getCurrentUrl().equalsIgnoreCase(url));
		// assertFalse(driver.getCurrentUrl().contains("/dashboard"));
		SideNavCommonPage sideNavCommonPage = new SideNavCommonPage(driver);
		sideNavCommonPage.logout();
		loggingMethod("info", "Loggedout successfully");
	}

	@Test(priority = 3)
	public void invalidEmailIdLogin() throws InterruptedException {
		driver.get(url);
		loggingMethod("info", "url:" + url + "opened");
		SignInPage signInPage = new SignInPage(driver);
		signInPage.enterEmailAddress("HellowWorld");
		assertTrue(signInPage.textInvalidEmailId());
		assertTrue(driver.getCurrentUrl().equalsIgnoreCase(url));
		// assertFalse(driver.getCurrentUrl().contains("/dashboard"));
		SideNavCommonPage sideNavCommonPage = new SideNavCommonPage(driver);
		sideNavCommonPage.logout();
		loggingMethod("info", "Loggedout successfully");
		loggingMethod("debug", "Loggedout successfully");
		loggingMethod("error", "Loggedout successfully");
		loggingMethod("warn", "Loggedout successfully");
		loggingMethod("trace", "Loggedout successfully");
		loggingMethod("fatal", "Loggedout successfully");
		loggingMethod("message", "Loggedout successfully");
	}

}
