package com.salsalabs.ignite.automation.suites;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.manage.AccountsPage;

public class InviteNewCMTest extends SeleneseTestCase {
	@Test(groups = {"inviteCM", "resendInvite"}, retryAnalyzer=RetryAnalyzer.class)
	@Parameters({"cm.firstName",
				 "cm.lastName",
				 "cm.contentAndMessagingTable.role1",
				 "cm.contentAndMessagingTable.role2",
				 "cm.contentAndMessagingTable.role3",
				 "cm.dataAndAnalyticsTable.role1",
				 "cm.dataAndAnalyticsTable.role2",
				 "cm.dataAndAnalyticsTable.role3",
				 "cm.assetManagementTable.role1"})
	public void inviteNewCM(  String cmFirstName,
										 String cmLastName,
										 String cmContentAndMessagingRole1,
										 String cmContentAndMessagingRole2,
										 String cmContentAndMessagingRole3,
										 String cmDataAndAnalyticsRole1,
										 String cmDataAndAnalyticsRole2,
										 String cmDataAndAnalyticsRole3,
										 String cmAssetManagementRole1) {
		
		String cmEmail = emailClient.getEmailBox("cm" + CommonUtils.getUnicName());
		CommonUtils.setProperty(PropertyName.CM_EMAIL, cmEmail);
		
		new LoginPage()
		.doSuccessLogin()
		.openSettingsPage()
		.openAccountsPage()
		.openInviteNewUserPage()
		.inviteNewUser(cmEmail, cmFirstName, cmLastName, cmContentAndMessagingRole1, cmContentAndMessagingRole2, cmContentAndMessagingRole3, cmDataAndAnalyticsRole1, cmDataAndAnalyticsRole2, cmDataAndAnalyticsRole3, cmAssetManagementRole1)
		.verifyInvitationSent();
		
		CommonUtils.setProperty(PropertyName.CM_LOGIN, cmEmail);
		CommonUtils.setProperty(PropertyName.CM_FIRST_NAME, cmFirstName);
		CommonUtils.setProperty(PropertyName.CM_LAST_NAME, cmLastName);
	}
	
	@Test(groups = {"resendInvite"}, retryAnalyzer=RetryAnalyzer.class, dependsOnMethods = {"inviteNewCM"})
	public void resendInvitation() {
		emailClient.deleteAllEmails();
		new AccountsPage().resendInvite();
	}
	
	@Test(groups = {"inviteCM", "resendInvite"}, retryAnalyzer=RetryAnalyzer.class, dependsOnMethods = {"inviteNewCM","resendInvitation"})
	@Parameters({"newuser.password"})
	public void provisionNewCM(String userPassword) {
		
		LoginPage loginPage = new LoginPage();
		loginPage.
		openConfirmationPage().
		completeCMInvite(userPassword)
		.verifyHomePageIsOpened();
	
		CommonUtils.setProperty(PropertyName.CM_PASSWORD, userPassword);
	}
}
