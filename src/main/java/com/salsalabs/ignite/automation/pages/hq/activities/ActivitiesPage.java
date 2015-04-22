package com.salsalabs.ignite.automation.pages.hq.activities;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.CheckBox;
import com.salsalabs.ignite.automation.elements.Table;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.CheckBoxImpl;
import com.salsalabs.ignite.automation.elements.impl.TableImpl;
import com.salsalabs.ignite.automation.pages.hq.HomePage;


public class ActivitiesPage extends HomePage {
	
	Button emailBlastsLink = new ButtonImpl("//a[.='Manage Emails']", "Email blast"); 
	Button fundraisingWidgetLink = new ButtonImpl("//a[@autotest-id='FUNDRAISE']", "Fundraising Widget");
	Button subscribeWidgetLink = new ButtonImpl("//a[@autotest-id='SUBSCRIBE']", "Sign-Up Forms");
	Button allActivitiesTab = new ButtonImpl("//a[@autotest-id='ALL']", "All Activities");
	Button signupFormsTab = new ButtonImpl("//a[@autotest-id='SUBSCRIBE']", "Sign-Up Forms");
	Button petitionTab = new ButtonImpl("//a[@autotest-id='PETITION']", "Petitions");
	Table activitiesTable = new TableImpl("//table[contains(@id,'JColResizer')]", "Activities Table");
	CheckBox selectFirstWidget = new CheckBoxImpl("//table[contains(@id,'JColResizer')]/tbody/tr[1]/td[1]/input", "Select First Row");
	Button deleteButton = new ButtonImpl("//a[@ng-click='confirmDelete()']", "Delete Selected");
	Button confirmDeletionBtn = new ButtonImpl("//*[@id='formConfigModal']/div[2]/button[2]", "Yes, delete already!");
	Button rejectDeletionBtn = new ButtonImpl("//*[@id='formConfigModal']/div[2]/button[1]", "Nevermind, leave it be!");
	
	public ActivitiesPage verifyURL() {
		verifier.verifyTrue(getLocation().contains("activities"), "Current URL is not contains Activities");
		return this;
	}
	
	public PetitionsPage openPetitionsPage() {
		petitionTab.click();
		return new PetitionsPage();
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
	
	public <T> T openFormFromTable(Class<T> clazz) {
		new ButtonImpl(activitiesTable.getPath() + "/tbody/tr[1]/td[2]/div/span/span", "First Row").click();
		try {
			return clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			logger.error("", e);
		}
		return null;
	}
	
	public AddSubscribeWidgetPage openSignupWidgetFromTable() {
		return openFormFromTable(AddSubscribeWidgetPage.class);
	}
	
	public AddDonationWidgetPage openDonationsWidgetFromTable() {
		return openFormFromTable(AddDonationWidgetPage.class);
	}
	
	public AddPetitionPage openPetitionFromTable() {
		return openFormFromTable(AddPetitionPage.class);
	}
	
	// try to remove and click Yes on confirmation dialog
	public void removeWidgetSuccessfully() {
		sleep(2);
		selectFirstWidget.check();
		verifier.verifyElementIsDisplayed(deleteButton);
		deleteButton.click();
		confirmDeletionBtn.click();
		sleep(5);
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
}
