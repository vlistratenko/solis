package com.salsalabs.ignite.automation.pages.hq.manage;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.Table;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.TableImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;
import com.salsalabs.ignite.automation.pages.hq.HomePage;

public class CreateInvitePage extends HomePage {

	TextBox emailField = new TextBoxImpl("//input[@id='invite_email']", "Email");
	TextBox firstNameField = new TextBoxImpl("//input[@id='invite_firstName']", "First name");
	TextBox lastNameField = new TextBoxImpl("//input[@id='invite_lastName']", "Last name");
	Table listBuildingFormsTable = new TableImpl("//div[contains(@config,'listBuildingFormsConfig')]/div[@class='permissions-section-body']/table", "List Building forms");
	Table fundraisingFormsTable = new TableImpl("//div[contains(@config,'fundraisingFormsConfig')]/div[@class='permissions-section-body']/table", "Fundraising forms");
	Table donationManagementTable = new TableImpl("//div[contains(@config,'donationManagementConfig')]/div[@class='permissions-section-body']/table", "Donation Management");
	Table messagingTable = new TableImpl("//div[contains(@config,'messagingConfig')]/div[@class='permissions-section-body']/table", "Messaging");
	Table listManagementTable = new TableImpl("//div[contains(@config,'listManagementConfig')]/div[@class='permissions-section-body']/table", "List Management");
	Table insightReportsTable = new TableImpl("//div[contains(@config,'insightReportsConfig')]/div[@class='permissions-section-body']/table", "Insight Reports");
	Table applicationAdminTable = new TableImpl("//div[contains(@config,'applicationAdministrationConfig')]/div[@class='permissions-section-body']/table", "Application Administration");
	Button saveInviteButton = new ButtonImpl("//button[@id='btnSendInvite']", "Save invite");
	
	public AccountsPage inviteNewUser(String cmEmail,
			String cmFirstName,
			String cmLastName,
			int cmListBuildingFormsRole, 
			int cmFundraisingFormsRole, 
			int cmDonationManagementRole, 
			int cmMessagingRole, 
			int cmListManagementRole, 
			int cmInsightReportsRole,
			int cmAppAdminRole) {
		emailField.type(cmEmail);
		firstNameField.type(cmFirstName);
		lastNameField.type(cmLastName);
		listBuildingFormsTable.clickInCell(1, cmListBuildingFormsRole + 1, "div");
		fundraisingFormsTable.clickInCell(1, cmFundraisingFormsRole + 1, "div");
		donationManagementTable.clickInCell(1, cmDonationManagementRole + 1, "div");
		messagingTable.clickInCell(1, cmMessagingRole + 1, "div");
		listManagementTable.clickInCell(1, cmListManagementRole + 1, "div");
		insightReportsTable.clickInCell(1, cmInsightReportsRole + 1, "div");
		applicationAdminTable.clickInCell(1, cmAppAdminRole + 1, "div");
		saveInviteButton.click();
		sleep(10);
		return new AccountsPage();
	}

}
