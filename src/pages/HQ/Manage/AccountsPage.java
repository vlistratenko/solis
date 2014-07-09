package pages.HQ.Manage;

import objects.Button;
import objects.Table;
import pages.HQ.HomePage;
import selenium.CommonUtils;

public class AccountsPage extends HomePage{

	Button inviteNewUserButton =  new Button("//button[text()='+ invite']", "Invite new user");
	Table invitationsTable = new Table("//div[@header='PENDING_INVITATIONS']/descendant::table", "Invitations");

	public CreateInvitePage openInviteNewUserPage() {
		inviteNewUserButton.click();
		return new CreateInvitePage();
		
	}

	public AccountsPage verifyInvitationSent() {
		verify(invitationsTable.isValueExists(CommonUtils.getProperty("CM.email"))>0, true, "User " + CommonUtils.getProperty("CM.email") + " was not found.");
		return this;
	}
	
	
}
