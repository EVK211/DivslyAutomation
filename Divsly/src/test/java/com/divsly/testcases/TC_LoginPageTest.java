package com.divsly.testcases;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import java.time.Duration;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.divsly.pageobjects.SideNavCommonPage;
import com.divsly.pageobjects.SignInPage;

public class TC_LoginPageTest extends BaseClass {
	ExtentTest it1 = null;

	@Test(priority = 1)
	public void userLogin() throws InterruptedException {
		//extentIteration = test.createNode("User with valid credentials");
		it1 = createTestClassDetails("Valid credentails");
		
		driver.get(url);
		addDetailsinReport(it1, "INFO", "Navigated to URL");
		SignInPage signInPage = new SignInPage(driver);
		signInPage.userLogin("evinodkumar211@gmail.com", "Secundrabad1!");
		addDetailsinReport(it1, "INFO", "Logged in successfully");
		Thread.sleep(Duration.ofSeconds(implicitWaitTime));
		SideNavCommonPage sideNavCommonPage = new SideNavCommonPage(driver);
		assertTrue(driver.getCurrentUrl().contains("/dashboard"));
		sideNavCommonPage.logout();
		addDetailsinReport(it1, "INFO", "Loggedout successfully");
		addDetailsinReport(it1, "PASS", "Test Case Passed");
	}

	@Test(priority = 2)
	public void invaliduserLogin() throws InterruptedException {
		//extentIteration = test.createNode("InValid credentails");
		it1 = createTestClassDetails("InValid credentails");
		it1.log(Status.INFO, "Working");
		driver.get(url);
		loggingMethod("info", "url:" + url + "opened");
		SignInPage signInPage = new SignInPage(driver);
		signInPage.userLogin("evinodkumar2112334@gmail.com", "Secundrabad1!");
		assertTrue(signInPage.textAccountNotFoundIsVisible());
		assertTrue(driver.getCurrentUrl().equalsIgnoreCase(url));
		SideNavCommonPage sideNavCommonPage = new SideNavCommonPage(driver);
		sideNavCommonPage.logout();
		loggingMethod("info", "Loggedout successfully");
		it1.log(Status.INFO, "Loggedout successfully");
		it1.log(Status.PASS, MarkupHelper.createLabel("Test passed", ExtentColor.GREEN));
	}

	@Test(priority = 3)
	public void invalidEmailIdLogin() throws InterruptedException {
		it1 = createTestClassDetails("InValid Email");
		driver.get(url);
		loggingMethod("info", "url:" + url + "opened");
		SignInPage signInPage = new SignInPage(driver);
		signInPage.enterEmailAddress("HellowWorld");
		assertTrue(signInPage.textInvalidEmailId());
		assertFalse(driver.getCurrentUrl().equalsIgnoreCase(url));
		SideNavCommonPage sideNavCommonPage = new SideNavCommonPage(driver);
		sideNavCommonPage.logout();
		addDetailsinReport(it1, "INFO", "Loggedout successfully");
	}

}
