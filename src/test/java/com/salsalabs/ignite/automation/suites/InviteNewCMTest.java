package com.salsalabs.ignite.automation.suites;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.manage.AccountsPage;

/**
 * <b>This test contains scenarios related to CM invitation (TestLink: TC3/TC5)</b>
 *
 */
public class InviteNewCMTest extends SeleneseTestCase {
	
	/**
	 * <b>Invite new CM</b>
	 * <p/>
	 * Steps:
	 * <ul>
	 * <li> Login into existing organization </li>
	 * <li> Navigate to Manage>Manage Accounts>Invite </li>
	 * <li> Enter a unique email id </li>
	 * <li> Enter the first name and last name </li>
	 * <li> Select highest rights for all the roles </li>
	 * <li> Send invite </li>
	 * <li> <font color="green"><b>Verify that new CM is listed in the pending invitations group of CMs </b></font></li>
	 * <li> <font color="green"><b>Verify that user received invitation email </b></font></li>
	 * </ul>
	 *  
	 */
	@Test(groups = {"inviteCM", "resendInvite"}, retryAnalyzer=RetryAnalyzer.class)
	@Parameters({"cm.firstName",
				 "cm.lastName",
				 "cm.listBuildingForms.role",
				 "cm.fundraisingForms.role",
				 "cm.donationManagement.role",
				 "cm.messaging.role",
				 "cm.listManagement.role",
				 "cm.insightReports.role",
				 "cm.appAdmin.role"})
	public void inviteNewCM( String cmFirstName,
							 String cmLastName,
							 int cmListBuildingFormsRole, 
							 int cmFundraisingFormsRole, 
							 int cmDonationManagementRole, 
							 int cmMessagingRole, 
							 int cmListManagementRole, 
							 int cmInsightReportsRole,
							 int cmAppAdminRole) {
		
		String cmEmail = emailClient.getEmailBox("cm" + CommonUtils.getUnicName());
		CommonUtils.setProperty(PropertyName.CM_EMAIL, cmEmail);
		
		new LoginPage()
		.doSuccessLogin()
		.openSettingsPage()
		.openAccountsPage()
		.openInviteNewUserPage()
		.inviteNewUser(cmEmail, cmFirstName, cmLastName, cmListBuildingFormsRole, cmFundraisingFormsRole, cmDonationManagementRole, cmMessagingRole, cmListManagementRole, cmInsightReportsRole, cmAppAdminRole)
		.verifyInvitationSent();
		
		CommonUtils.setProperty(PropertyName.CM_LOGIN, cmEmail);
		CommonUtils.setProperty(PropertyName.CM_FIRST_NAME, cmFirstName);
		CommonUtils.setProperty(PropertyName.CM_LAST_NAME, cmLastName);
	}
	
	/**
	 * <b>Resend invitation</b>
	 * <p/>
	 * Steps:
	 * <ul>
	 * <li> Delete all emails </li>
	 * <li> Find new CM in the pending invitations group of CMs </li>
	 * <li> Click the "Resend Invitation" button </li>
	 * <li> <font color="green"><b>Verify that UI confirmation is displayed </b></font></li>
	 * <li> <font color="green"><b>Verify that user received invitation email </b></font></li>
	 * </ul>
	 *  
	 */
	@Test(groups = {"resendInvite"}, retryAnalyzer=RetryAnalyzer.class, dependsOnMethods = {"inviteNewCM"})
	public void resendInvitation() {
		emailClient.deleteAllEmails();
		new AccountsPage().resendInvite();
	}
	
	/**
	 * <b>Provision new CM</b>
	 * <p/>
	 * Steps:
	 * <ul>
	 * <li> Open the email invitation </li>
	 * <li> Enter the password, and confirm password </li>
	 * <li> Enter a unique email id </li>
	 * <li> Enter the first name and last name </li>
	 * <li> Enter the first security question and answer </li>
	 * <li> Enter the second security question and answer </li>
	 * <li> Click on submit </li>
	 * <li> <font color="green"><b>Verify that user is logged in </b></font></li>
	 * </ul>
	 *  
	 */
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
