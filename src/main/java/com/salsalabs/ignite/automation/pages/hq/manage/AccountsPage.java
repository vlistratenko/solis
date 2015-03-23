package com.salsalabs.ignite.automation.pages.hq.manage;


import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.Table;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.TableImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;
import com.salsalabs.ignite.automation.pages.hq.HomePage;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;

public class AccountsPage extends HomePage{

	Button inviteNewUserButton =  new ButtonImpl("//button[text()='+ Invite my team member']", "Invite new user");
	Table invitationsTable = new TableImpl("//table[last()]", "Pending Invitations");
	TextBox inviteSearch = new TextBoxImpl("(//input[@placeholder='search...'])[last()]", "Search invite textbox");
	Button inviteSearchBtn = new ButtonImpl("(//a[@ng-click='processing.search = true; doSearch();'])[last()]", "Search invite btn");

	public CreateInvitePage openInviteNewUserPage() {
		inviteNewUserButton.click();
		return new CreateInvitePage();
		
	}

	public AccountsPage verifyInvitationSent() {
		sleep(5);
		verifier.verifyTrue(invitationsTable.isValueExists(CommonUtils.getProperty(PropertyName.CM_EMAIL)) > 0, "User " + CommonUtils.getProperty(PropertyName.CM_EMAIL) + " was not found.");
		verifier.verifyNotEquals(new LoginPage().getInvitationUrl(), null, "Invitation link hasn't been sent", true);
		return this;
	}
	
	public AccountsPage resendInvite(){
		inviteSearch.type(CommonUtils.getProperty(PropertyName.CM_EMAIL));
		inviteSearchBtn.click();
		invitationsTable.clickInCell(CommonUtils.getProperty(PropertyName.CM_EMAIL), "5", "div/span/a");
		feedBackDialogPanel.waitElement(10);
		verifier.verifyNotEquals(new LoginPage().getInvitationUrl(), null, "Invitation link hasn't been sent", true);
		return this;
	}
}
