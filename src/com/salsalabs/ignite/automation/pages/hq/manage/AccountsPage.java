package com.salsalabs.ignite.automation.pages.hq.manage;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.Table;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.TableImpl;
import com.salsalabs.ignite.automation.pages.hq.HomePage;

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
