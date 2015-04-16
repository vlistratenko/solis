package com.salsalabs.ignite.automation.pages.hq.activities;

import org.apache.commons.lang3.RandomUtils;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.CheckBox;
import com.salsalabs.ignite.automation.elements.Table;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.CheckBoxImpl;
import com.salsalabs.ignite.automation.elements.impl.TableImpl;
import com.salsalabs.ignite.automation.pages.hq.HomePage;
import com.salsalabs.ignite.automation.pages.hq.emails.EmailBlastsPage;


public class ActivitiesPage extends HomePage {
	protected String[] layouts = {"Hero", "Sidebar Right", "Hero Sidekick", "Newsletter", 
			"Sidebar Hero Left", "Sidebar Left", "Sidebar Hero Right", "Basic"};
	String widgetButtonText;
	Button emailBlastsLink = new ButtonImpl("//a[.='Manage Emails']", "Email blast"); 
	Button fundraisingWidgetLink = new ButtonImpl("//a[.='Fundraising Forms']", "Fundraising Forms");
	Button subscribeWidgetLink = new ButtonImpl("//a[text()='Sign-Up Forms']", "Sign-Up Forms");
	Button allActivitiesTab = new ButtonImpl("//a[text()='All Activities']", "All Activities");
	Button signupFormsTab = new ButtonImpl("//a[text()='Sign-Up Forms']", "Sign-Up Forms");
	Table activitiesTable = new TableImpl("//table[contains(@id,'JColResizer')]", "Activities Table");
	CheckBox selectFirstWidget = new CheckBoxImpl("//table[contains(@id,'JColResizer')]/tbody/tr[1]/td[1]/input", "Select First Row");
	Button deleteButton = new ButtonImpl("//a[@ng-click='confirmDelete()']", "Delete Selected");
	Button confirmDeletionBtn = new ButtonImpl("//*[@id='formConfigModal']/div[2]/button[2]", "Yes, delete already!");
	Button rejectDeletionBtn = new ButtonImpl("//*[@id='formConfigModal']/div[2]/button[1]", "Nevermind, leave it be!");
	Button settingsButton = new ButtonImpl("//a[@class='account-info-drop saveBarBtn']", "Settings Button");
	Button makePrivateButton = new ButtonImpl("//a[contains(@processing-text, 'Unpublishing...')]", "Unpublishing");
	
	public ActivitiesPage verifyURL() {
		verifier.verifyTrue(getLocation().contains("activities"), "Current URL is not contains Activities");
		return this;
	}
	
	public EmailBlastsPage openEmailBlastsPage() {
		emailBlastsLink.click();
		return new EmailBlastsPage();
	}
	
	public FundraisingWidgetPage openFundraisingWidgetPage() {
		fundraisingWidgetLink.click();
		return new FundraisingWidgetPage();
	}

	public SubscribeWidgetPage openSubscribeWidgetsPage() {
		subscribeWidgetLink.click();
		return new SubscribeWidgetPage();
	}
	
	public ActivitiesPage openAllActivitiesTab() {
		allActivitiesTab.click();
		sleep(2);
		return this;
	}
	
	public ActivitiesPage openSignupFormsTab() {
		signupFormsTab.click();
		sleep(2);
		return this;
	}
	
	public void verifyActivityIsPresentInTableAllActivities(String type, String handyReferenceName, String description, String status) {
		verifier.verifyEquals(activitiesTable.getCellValue(1, 2), type, "Widget is not present in table (type)");
		verifier.verifyEquals(activitiesTable.getCellValue(1, 3), handyReferenceName, "Widget is not present in table (name)");
		verifier.verifyEquals(activitiesTable.getCellValue(1, 4), description, "Widget is not present in table (description)");
		verifier.verifyEquals(activitiesTable.getCellValue(1, 5), status, "Widget is not present in table (status)");
	}
	
	public void verifyWidgetIsPresentInTableForms(String widgetName, String description, String status, String visibility) {
		verifier.verifyEquals(activitiesTable.getCellValue(1, 2), widgetName, "Widget is not present in table (name)");
		verifier.verifyEquals(activitiesTable.getCellValue(1, 3), description, "Widget is not present in table (description)");
		verifier.verifyEquals(activitiesTable.getCellValue(1, 4), status, "Widget is not present in table (status)");
		verifier.verifyEquals(activitiesTable.getCellValue(1, 5), visibility, "Widget is not present in table (visibility)");
	}
	
	public void verifyActivityIsNotPresentInTableAllActivities(String handyReferenceName, String description) {
		verifier.verifyNotEquals(activitiesTable.getCellValue(1, 3), handyReferenceName, "Widget is not present in table (name)");
		verifier.verifyNotEquals(activitiesTable.getCellValue(1, 4), description, "Widget is not present in table (description)");
	}
	
	public void verifyWidgetIsNotPresentInTableForms(String widgetName, String description) {
		verifier.verifyNotEquals(activitiesTable.getCellValue(1, 2), widgetName, "Widget is not present in table (name)");
		verifier.verifyNotEquals(activitiesTable.getCellValue(1, 3), description, "Widget is not present in table (description)");
	}

	public AddSubscribeWidgetPage openSignupWidgetFromTable() {
		new ButtonImpl(activitiesTable.getPath() + "/tbody/tr[1]/td[2]/div/span/span", "First Row").click();
		return new AddSubscribeWidgetPage();
	}
	
	public AddDonationWidgetPage openDonationsWidgetFromTable() {
		new ButtonImpl(activitiesTable.getPath() + "/tbody/tr[1]/td[2]/div/span/span", "First Row").click();
		return new AddDonationWidgetPage();
	}
	
	// try to remove and click Yes on confirmation dialog
	public void removeWidgetSuccessfully() {
		sleep(2);
		selectFirstWidget.check();
		verifier.verifyElementIsDisplayed(deleteButton);
		deleteButton.click();
		confirmDeletionBtn.click();
		sleep(10);
	}
	
	// try to remove and click Cancel button on confirmation dialog
	public void removeWidgetDiscard() {
		sleep(2);
		selectFirstWidget.check();
		verifier.verifyElementIsDisplayed(deleteButton);
		deleteButton.click();
		rejectDeletionBtn.click();
		sleep(2);
	}
	
	public ActivitiesPage verifyWidgetVisible(String link, boolean visibleForCm, boolean visibleForSupporter) {
		if (link.contains(".ignite.")) {
			link = link.replaceFirst(".ignite.", ".igniteaction.");
		}
		String primaryHandle = this.getWindowHandle();
		this.openInNewWindow(link + "/index.html", false);
		Button subscribeButton = new ButtonImpl("//input[@value='" + widgetButtonText + "']", widgetButtonText + " Button");
		if (visibleForCm) {
			verifier.verifyElementIsDisplayed(subscribeButton);
		} else {
			verifier.verifyElementIsNotDisplayed(subscribeButton);
		}
		this.deletecoockies();
		this.refresh();
		if (visibleForSupporter) {
			verifier.verifyElementIsDisplayed(subscribeButton);
		} else {
			verifier.verifyElementIsNotDisplayed(subscribeButton);
		}
		this.closeWindow();
		this.switchToWindow(primaryHandle);
		return new ActivitiesPage();
	}
	
	public void verifyFormLinkIsPresent(String expectedLink) {
		Button link = new ButtonImpl("//a[@href='"+ expectedLink + "']", "Link");
		verifier.verifyElementIsDisplayed(link);
	}
	
	protected String chooseRandomLayout() {
		return this.layouts[RandomUtils.nextInt(0, this.layouts.length)];
	}
	
	public void makeWidgetPrivate() {
		settingsButton.click();
		makePrivateButton.click();
		sleep(10);
	}
}
