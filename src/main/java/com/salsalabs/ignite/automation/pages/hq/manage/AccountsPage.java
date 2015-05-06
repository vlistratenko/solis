package com.salsalabs.ignite.automation.pages.hq.manage;


import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.Table;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.LabelImpl;
import com.salsalabs.ignite.automation.elements.impl.TableImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;
import com.salsalabs.ignite.automation.pages.hq.HomePage;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;

public class AccountsPage extends HomePage{

	Button inviteNewUserButton =  new ButtonImpl("//button[@ng-click='btnInviteUser()']", "Invite new user");
	Table invitationsTable = new TableImpl("//table[last()]", "Pending Invitations");
	TextBox inviteSearch = new TextBoxImpl("(//input[@placeholder='Search...'])[last()]", "Search invite textbox");
	Button inviteSearchBtn = new ButtonImpl("(//*[@autotest-id='btn_search_list'])[last()]", "Search invite btn");

	public CreateInvitePage openInviteNewUserPage() {
		sleep(5);
		inviteNewUserButton.click();
		return new CreateInvitePage();
		
	}

	public AccountsPage verifyInvitationSent() {
		sleep(10);
		inviteSearch.type(CommonUtils.getProperty(PropertyName.CM_EMAIL));
		inviteSearchBtn.click();
		verifier.verifyElementIsDisplayed(new LabelImpl("//span[contains(text(), '" + CommonUtils.getProperty(PropertyName.CM_EMAIL) + "')]", "Admin in table"));
		verifier.verifyNotEquals(new LoginPage().getInvitationUrl(), null, "Invitation link hasn't been sent", true);
		return this;
	}
	
	public AccountsPage resendInvite(){
		new TextBoxImpl("(//input[@placeholder='Search...'])[last()]", "Search invite textbox").type(CommonUtils.getProperty(PropertyName.CM_EMAIL));
		inviteSearchBtn.click();
		sleep(10);
		new ButtonImpl("(//table[starts-with(@id,'JColResizer')])[last()]//tr[@class='ng-scope' and ./td[.//span[text()='" + CommonUtils.getProperty(PropertyName.CM_EMAIL) + "']]]//a[@class='ng-scope ng-isolate-scope']", "").click();
		feedBackDialogPanel.waitElement(10);
		verifier.verifyNotEquals(new LoginPage().getInvitationUrl(), null, "Invitation link hasn't been sent", true);
		return this;
	}
}
