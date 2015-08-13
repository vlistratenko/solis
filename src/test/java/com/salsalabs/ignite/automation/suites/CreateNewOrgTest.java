package com.salsalabs.ignite.automation.suites;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.admin.LoginPageAdmin;
import com.salsalabs.ignite.automation.pages.admin.OrganizationsListPage;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;

public class CreateNewOrgTest extends SeleneseTestCase {

	@Test(retryAnalyzer = RetryAnalyzer.class, priority = 10, enabled = true, groups = { "createAdmin" })
	@Parameters({ "createOrg.orgName", "createOrg.orgDescrption", "createOrg.firstName", "createOrg.lastName", "createOrg.status", "createOrg.product" })
	public void createOrgTest(String orgName, String orgDescrption, String firstName, String lastName, String status, String product) {

		orgName = orgName + CommonUtils.getUnicName();
		mailosaur.deleteAllEmails();
		String orgAdminUserId = mailosaur.getEmailBox(CommonUtils.getUnicName());
		new LoginPageAdmin().
		doSuccessLogin().
		openActiveOrganizationsPage().
		clickCreateNewOrg().
		createNewOrg(orgName, orgDescrption, orgAdminUserId, firstName, lastName, status, true, "Internal-Test", product).
		checkOrganizationExists(orgName);

		CommonUtils.setProperty(PropertyName.ADMIN_EMAIL, orgAdminUserId);
		CommonUtils.setProperty(PropertyName.ADMIN_FIRST_NAME, firstName);
		CommonUtils.setProperty(PropertyName.ADMIN_LAST_NAME, lastName);
		CommonUtils.setProperty(PropertyName.ADMIN_ORG_NAME, orgName);
	}

	@Test(retryAnalyzer = RetryAnalyzer.class, priority = 20, enabled = true, groups = { "createAdmin" }, dependsOnMethods = "createOrgTest")
	@Parameters({ "newuser.password" })
	public void confirmAdminAccountTest(String userPassword) {
		new OrganizationsListPage().
		openInvitationURL().
		completeInvite(userPassword);
		
		CommonUtils.setProperty(PropertyName.ADMIN_PASSWORD, userPassword);
	}

	@Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, priority = 30, groups = { "createAdmin" }, dependsOnMethods = "confirmAdminAccountTest")
	public void loginAsNewSuperAdminTest() {
		LoginPage loginPage = new LoginPage();
		loginPage.
		doSuccessLogin();
	}
}
