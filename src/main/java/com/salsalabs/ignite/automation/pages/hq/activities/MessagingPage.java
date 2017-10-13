package com.salsalabs.ignite.automation.pages.hq.activities;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.common.Supporter;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.CheckBox;
import com.salsalabs.ignite.automation.elements.Table;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.CheckBoxImpl;
import com.salsalabs.ignite.automation.elements.impl.TableImpl;
import com.salsalabs.ignite.automation.pages.hq.HomePage;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.emails.AddSocialPostsPage;
import com.salsalabs.ignite.automation.pages.hq.emails.EmailBlastsPage;


public class MessagingPage extends HomePage {
	
	Button emailTab = new ButtonImpl("//a[@autotest-id='EMAIL']", "Emails tab"); 
	Button socialTab = new ButtonImpl("//a[@autotest-id='SOCIAL']", "Social Posts tab"); 
	Button mseriesTab = new ButtonImpl("//a[@autotest-id='MESSAGE_SERIES']", "Messsage series tab"); 
	Button receiptTab = new ButtonImpl("//a[@autotest-id='COMM_CONFIRMATION']", "Receipts tab"); 
	Button allActivitiesTab = new ButtonImpl("//a[@autotest-id='ALL']", "All Messaging tab");
	Table activitiesTable = new TableImpl("//table[contains(@id,'JColResizer')]", "Messaging Table");
	CheckBox selectFirstWidget = new CheckBoxImpl("//table[contains(@id,'JColResizer')]/tbody/tr[1]/td[1]/input", "Select First Row");
	Button deleteButton = new ButtonImpl("//a[@ng-click='confirmDelete()']", "Delete Selected");
	Button confirmDeletionBtn = new ButtonImpl("//*[@id='formConfigModal']/div[2]/button[2]", "Yes, delete already!");
	Button rejectDeletionBtn = new ButtonImpl("//*[@id='formConfigModal']/div[2]/button[1]", "Nevermind, leave it be!");
	
	public MessagingPage verifyURL() {
		verifier.verifyTrue(getLocation().contains("activities"), "Current URL is not contains Activities");
		return this;
	}
	
	public EmailBlastsPage openEmailBlastsPage() {
		emailTab.click();
		return new EmailBlastsPage();
	}
	
	public AddSocialPostsPage openSocialPostsPage() {
		socialTab.click();
		return new AddSocialPostsPage();
	}
	
	public MessagingPage openAllActivitiesTab() {
		allActivitiesTab.click();
		sleep(2);
		return this;
	}
	
	public MessagingPage verifyActivityIsPresentInTableAllActivities(String type, String handyReferenceName) {
		verifier.verifyEquals(activitiesTable.getCellValue(1, 2), type, "Widget is not present in table (type)");
		verifier.verifyEquals(activitiesTable.getCellValue(1, 3), handyReferenceName, "Widget is not present in table (name)");
		SeleneseTestCase.logger.info("Activity Status is " + activitiesTable.getCellValue(1, 5));
		return this;
	}
	
	/**
	 * Wait for expected status with refreshing each 10 second n times
	 * @param expectedStatus
	 * @param n
	 * @return
	 */
	public MessagingPage waitForStatus(String expectedStatus, Integer n) {
		for (int i = 0; i < n; i++) {
			if (waitConditionBecomesTrue(activitiesTable.getCellValue(1, 5).equalsIgnoreCase(expectedStatus), 10)) {
				return this;
			}else{
				refresh();
			}
		}
		logger.warn("Status was not changed");
		return this;
	}
	
	public void verifyActivityIsPresentInTableAllActivities(String type, String handyReferenceName, String description, String status) {
		verifyActivityIsPresentInTableAllActivities(type, handyReferenceName);
		verifier.verifyEquals(activitiesTable.getCellValue(1, 4), description, "Widget is not present in table (description)");
		verifier.verifyEquals(activitiesTable.getCellValue(1, 5), status, "Widget is not present in table (status)");
	}
	
	public void verifyActivityIsNotPresentInTableAllActivities(String handyReferenceName, String description) {
		verifier.verifyNotEquals(activitiesTable.getCellValue(1, 3), handyReferenceName, "Widget is not present in table (name)");
		verifier.verifyNotEquals(activitiesTable.getCellValue(1, 4), description, "Widget is not present in table (description)");
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
	
	public MessagingPage verifyMergeFieldsInTheEmail() throws KeyManagementException, ClientProtocolException, NoSuchAlgorithmException, KeyStoreException, JSONException, URISyntaxException, IOException {
		String emailBody = new LoginPage().getEmailBodyByRecipient(CommonUtils.getProperty(PropertyName.SUPPORTER_EMAIL));	
		String emailSubj = new LoginPage().getEmailSubjByRecipient(CommonUtils.getProperty(PropertyName.SUPPORTER_EMAIL));	
		Supporter sup = new Supporter().
				getSupporterFromSystemByEmail("supporterformergefieldstesting@testauto.ignite.net",
												SeleneseTestCase.USED_ENVIRONMENT.getBaseTestUrl(),
												CommonUtils.getProperty(PropertyName.CURRENT_LOGIN),
												CommonUtils.getProperty(PropertyName.CURRENT_PASSWORD));
		
		//verify supporters merge fields
		verifier.verifyContains(emailBody, sup.postalCode, "Supporter's zip is not found in the email", false);
		//mergeFieldTypeOfMostRecentDonation
		//mergeFieldTotalPetitionSignatures
		verifier.verifyContains(emailBody, sup.title, "Supporter's zip is not found in the email", false);
		//mergeFieldSupporterSince
		verifier.verifyContains(emailBody, sup.suffix, "Supporter's zip is not found in the email", false);
		//mergeFieldSubscriptionDate
		//mergeFieldSubscriptionChangeDate
		//mergeFieldStateSenateDistrict
		//mergeFieldStateHouseDistrict
		verifier.verifyContains(emailBody, sup.state, "Supporter's zip is not found in the email", false);
		//mergeFieldOrganizationName
		//mergeFieldOrgZip
		//mergeFieldOrgState
		//mergeFieldOrgPhone
		//mergeFieldOrgEmail
		//mergeFieldOrgCity
		//mergeFieldOrgAddressLine2
		//mergeFieldOrgAddressLine1
		//mergeFieldNumber
		verifier.verifyContains(emailBody, sup.middleName, "Supporter's zip is not found in the email", false);
		//mergeFieldLifetimeNumberofDonations
		//mergeFieldLifetimeGivingAmount
		//mergeFieldLastPetitionSignatureDate
		verifier.verifyContains(emailBody, sup.lastorOrgName, "Supporter's zip is not found in the email", false);
		//mergeFieldLargestDonationAmount
		//mergeFieldFirstPetitionSignatureDate
		verifier.verifyContains(emailBody, sup.firstName, "Supporter's zip is not found in the email", false);
		//mergeFieldFederalHouseDistrict
		verifier.verifyContains(emailBody, sup.finalEMAIL, "Supporter's zip is not found in the email", false);
		//mergeFieldDateOfMostRecentDonation
		verifier.verifyContains(emailBody, sup.city, "Supporter's zip is not found in the email", false);
		verifier.verifyContains(emailBody, sup.phoneCell, "Supporter's zip is not found in the email", false);
		//mergeFieldAverageDonationAmount
		//mergeFieldAmountOfMostRecentDonation
		verifier.verifyContains(emailBody, sup.addressLine1, "Supporter's zip is not found in the email", false);
		verifier.verifyContains(emailBody, sup.addressLine1, "Supporter's zip is not found in the email", false);
		return this;
		
	}
}
