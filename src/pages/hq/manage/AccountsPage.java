package pages.hq.manage;

import core.util.CommonUtils;
import core.util.PropertyName;
import elements.Button;
import elements.Table;
import elements.impl.ButtonImpl;
import elements.impl.TableImpl;
import pages.hq.HomePage;

public class AccountsPage extends HomePage{

	Button inviteNewUserButton =  new ButtonImpl("//button[text()='+ Invite my team member']", "Invite new user");
	Table invitationsTable = new TableImpl("//*[@id='JColResizer1']", "Pending Invitations");

	public CreateInvitePage openInviteNewUserPage() {
		inviteNewUserButton.click();
		return new CreateInvitePage();
		
	}

	public AccountsPage verifyInvitationSent() {
		verifier.verifyEquals(invitationsTable.isValueExists(CommonUtils.getProperty(PropertyName.CM_EMAIL))>0, true, "User " + CommonUtils.getProperty(PropertyName.CM_EMAIL) + " was not found.");
		return this;
	}
	
	
}
