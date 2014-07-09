package pages.HQ.Manage;

import objects.Button;
import objects.Table;
import objects.TextBox;
import selenium.CommonUtils;

public class CreateInvitePage {

	TextBox emailField = new TextBox("//input[@id='invite_email']", "Email");
	TextBox firstNameField = new TextBox("//input[@id='invite_firstName']", "First name");
	TextBox lastNameField = new TextBox("//input[@id='invite_lastName']", "Last name");
	Table contentAndMessagingTable = new Table("//form[@id='inviteForm']/descendant::*[text()='Content and Messaging']/following-sibling::table[1]", "Content and Messaging permissions grid");
	Table dataAndAnalyticsTable = new Table("//form[@id='inviteForm']/descendant::*[text()='Data and Analytics']/following-sibling::table[1]", "Data And Analytics permissions grid");
	Table assetManagementTable = new Table("//form[@id='inviteForm']/descendant::*[text()='Assets']/following-sibling::table[1]", "Asset Management permissions grid");
	Button saveInviteButton = new Button("//button[@id='btnSendInvite']", "Save invite");
	
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
		//String unicID  = CommonUtils.getRandomValue(100000, 0);
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
