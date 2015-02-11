package pages.HQ.Manage;

import objects.Button;
import objects.Table;
import pages.HQ.HomePage;
import selenium.CommonUtils;

public class AccountsPage extends HomePage{

	Button inviteNewUserButton =  new Button("//button[text()='+ Invite my team member']", "Invite new user");
	Table invitationsTable = new Table("//*[@id='JColResizer1']", "Pending Invitations");

	public CreateInvitePage openInviteNewUserPage() {
		inviteNewUserButton.click();
		return new CreateInvitePage();
		
	}

	public AccountsPage verifyInvitationSent() {
		verify(invitationsTable.isValueExists(CommonUtils.getProperty("CM.email"))>0, true, "User " + CommonUtils.getProperty("CM.email") + " was not found.");
		return this;
	}
	
	
}
