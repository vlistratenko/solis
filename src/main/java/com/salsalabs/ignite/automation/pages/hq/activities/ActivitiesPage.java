package com.salsalabs.ignite.automation.pages.hq.activities;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.CheckBox;
import com.salsalabs.ignite.automation.elements.Table;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.CheckBoxImpl;
import com.salsalabs.ignite.automation.elements.impl.TableImpl;
import com.salsalabs.ignite.automation.pages.hq.HomePage;


public class ActivitiesPage extends HomePage {
	
	Button fundraisingWidgetLink = new ButtonImpl("//a[.='Raise Money']", "Fundraising Widget");
	Button subscribeWidgetLink = new ButtonImpl("//a[text()='Grow Your List']", "Sign-Up Forms");
	Button allActivitiesTab = new ButtonImpl("//a[text()='All Activities']", "All Activities");
	Table activitiesTable = new TableImpl("//table[contains(@id,'JColResizer')]", "Activities Table");
	CheckBox selectFirstWidget = new CheckBoxImpl("//table[contains(@id,'JColResizer')]/tbody/tr[1]/td[1]/input", "Select First Row");
	Button deleteButton = new ButtonImpl("//a[@ng-click='confirmDelete()']", "Delete Selected");
	Button confirmDeletionBtn = new ButtonImpl("//*[@id='formConfigModal']/div[2]/button[2]", "Yes, delete already!");
	Button rejectDeletionBtn = new ButtonImpl("//*[@id='formConfigModal']/div[2]/button[1]", "Nevermind, leave it be!");
	
	public ActivitiesPage verifyURL() {
		verifier.verifyTrue(getLocation().contains("activities"), "Current URL is not contains Activities");
		return this;
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
	
	public void verifyWidgetIsPresentInTableSignupForms(String widgetName, String description, String status, String visibility) {
		verifier.verifyEquals(activitiesTable.getCellValue(1, 2), widgetName, "Widget is not present in table (name)");
		verifier.verifyEquals(activitiesTable.getCellValue(1, 3), description, "Widget is not present in table (description)");
		verifier.verifyEquals(activitiesTable.getCellValue(1, 4), status, "Widget is not present in table (status)");
		verifier.verifyEquals(activitiesTable.getCellValue(1, 5), visibility, "Widget is not present in table (visibility)");
	}
	
	public void verifyActivityIsNotPresentInTableAllActivities(String handyReferenceName, String description) {
		verifier.verifyNotEquals(activitiesTable.getCellValue(1, 3), handyReferenceName, "Widget is not present in table (name)");
		verifier.verifyNotEquals(activitiesTable.getCellValue(1, 4), description, "Widget is not present in table (description)");
	}
	
	public void verifyWidgetIsNotPresentInTableSignupForms(String widgetName, String description) {
		verifier.verifyNotEquals(activitiesTable.getCellValue(1, 2), widgetName, "Widget is not present in table (name)");
		verifier.verifyNotEquals(activitiesTable.getCellValue(1, 3), description, "Widget is not present in table (description)");
	}

	public AddSubscribeWidgetPage openWidgetFromTable() {
		new ButtonImpl(activitiesTable.getPath() + "/tbody/tr[1]/td[2]/div/span/span", "First Row").click();
		return new AddSubscribeWidgetPage();
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
}
