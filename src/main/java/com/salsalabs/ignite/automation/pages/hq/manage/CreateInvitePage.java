package com.salsalabs.ignite.automation.pages.hq.manage;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.Table;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.TableImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;

public class CreateInvitePage {

	TextBox emailField = new TextBoxImpl("//input[@id='invite_email']", "Email");
	TextBox firstNameField = new TextBoxImpl("//input[@id='invite_firstName']", "First name");
	TextBox lastNameField = new TextBoxImpl("//input[@id='invite_lastName']", "Last name");
	Table contentAndMessagingTable = new TableImpl("//form[@id='inviteForm']/div[2]/div/div[4]/div/table[1]", "Content and Messaging permissions grid");
	Table dataAndAnalyticsTable = new TableImpl("//form[@id='inviteForm']/div[2]/div/div[4]/div/table[2]", "Data And Analytics permissions grid");
	Table assetManagementTable = new TableImpl("//form[@id='inviteForm']/div[2]/div/div[4]/div/table[3]", "Asset Management permissions grid");
	Button saveInviteButton = new ButtonImpl("//button[@id='btnSendInvite']", "Save invite");
	
	public AccountsPage inviteNewUser(String cmEmail,
			String cmFirstName,
			String cmLastName,
			String cmContentAndMessagingRole1,
			String cmContentAndMessagingRole2,
			String cmContentAndMessagingRole3,
			String cmDataAndAnalyticsRole1,
			String cmDataAndAnalyticsRole2,
			String cmDataAndAnalyticsRole3,
			String cmAssetManagementRole1) {
		emailField.type(cmEmail);
		firstNameField.type(cmFirstName);
		lastNameField.type(cmLastName);
		contentAndMessagingTable.clickInCell(
				CommonUtils.getArrayFromStringBySymbol(cmContentAndMessagingRole1, ":")[0],
				CommonUtils.getArrayFromStringBySymbol(cmContentAndMessagingRole1, ":")[1]
				);
		contentAndMessagingTable.clickInCell(
				CommonUtils.getArrayFromStringBySymbol(cmContentAndMessagingRole2, ":")[0],
				CommonUtils.getArrayFromStringBySymbol(cmContentAndMessagingRole2, ":")[1]
				);
		contentAndMessagingTable.clickInCell(
				CommonUtils.getArrayFromStringBySymbol(cmContentAndMessagingRole3, ":")[0],
				CommonUtils.getArrayFromStringBySymbol(cmContentAndMessagingRole3, ":")[1]
				);
		dataAndAnalyticsTable.scrollIntoView();
		dataAndAnalyticsTable.clickInCell(
				CommonUtils.getArrayFromStringBySymbol(cmDataAndAnalyticsRole1, ":")[0],
				CommonUtils.getArrayFromStringBySymbol(cmDataAndAnalyticsRole1, ":")[1]
				);
		dataAndAnalyticsTable.clickInCell(
				CommonUtils.getArrayFromStringBySymbol(cmDataAndAnalyticsRole2, ":")[0],
				CommonUtils.getArrayFromStringBySymbol(cmDataAndAnalyticsRole2, ":")[1]
				);
		dataAndAnalyticsTable.clickInCell(
				CommonUtils.getArrayFromStringBySymbol(cmDataAndAnalyticsRole3, ":")[0],
				CommonUtils.getArrayFromStringBySymbol(cmDataAndAnalyticsRole3, ":")[1]
				);
		assetManagementTable.clickInCell(
				CommonUtils.getArrayFromStringBySymbol(cmAssetManagementRole1, ":")[0],
				CommonUtils.getArrayFromStringBySymbol(cmAssetManagementRole1, ":")[1]
				);
		saveInviteButton.click();
		return new AccountsPage();
	}

}
