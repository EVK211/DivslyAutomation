package com.divsly.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SideNavCommonPage {

	WebDriver ldriver;

	public SideNavCommonPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(xpath = "//span[text()='Dashboard']/parent::span/i")
	WebElement buttonDashboard;

	@FindBy(xpath = "//span[text()=' links']/parent::span/i")
	WebElement buttonLinks;

	@FindBy(xpath = "//span[text()='QR Codes']/parent::span/i")
	WebElement buttonQRCodes;

	@FindBy(xpath = "//span[text()='Link in Bio']/parent::span/i")
	WebElement buttonLinkInBio;

	@FindBy(xpath = "//span[text()='Custom Links']/parent::span/i")
	WebElement buttonCustomeLinks;

	@FindBy(xpath = "//span[text()='Contacts']/parent::span/i")
	WebElement buttonContacts;

	@FindBy(xpath = "//i[contains(@class,'fa-folder-tree')]")
	WebElement buttonFolderTree;

	@FindBy(xpath = "//span[text()='Email']/parent::span/i")
	WebElement buttonEmail;

	@FindBy(xpath = "//span[text()='SMS']/parent::span/i")
	WebElement buttonSMS;

	@FindBy(xpath = "//span[text()='WhatsApp']/parent::span/i")
	WebElement buttonWhatsApp;

	@FindBy(xpath = "//span[text()='Integrations']/parent::span/i")
	WebElement buttonIntegrations;

	@FindBy(xpath = "//span[text()='Notifications']/parent::span/i")
	WebElement buttonNotifications;

	@FindBy(xpath = "//span[text()='Subscription']/parent::span/i")
	WebElement buttonSUbscription;

	@FindBy(xpath = "//span[text()='Settings']/parent::span/i")
	WebElement buttonSettings;

	@FindBy(xpath = "//span[text()='Log out']/parent::span/i")
	List<WebElement> buttonLogOut;

	public void logout() {
		if (buttonLogOut.size() > 0) {
			buttonLogOut.get(0).click();
		}
	}

}
