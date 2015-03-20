package com.salsalabs.ignite.automation.tests.old;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.EmailClient;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.admin.LoginPageAdmin;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;

public class AdminTest extends SeleneseTestCase{
	@Test(retryAnalyzer=RetryAnalyzer.class,  priority=10, enabled = true, groups = {/*"acceptanceTests.admin",*/ "dev", "test", "createAdmin"}, description = "484:51:New org was NOT created")
	@Parameters({ "admin.login",
     	"admin.password",     	
     	"createOrg.domainType",
		"createOrg.orgName",
		"createOrg.orgDescrption",
		"createOrg.firstName",
		"createOrg.lastName",		
		"createOrg.status",
		"createOrg.featureList" })
	public AdminTest createOrgTest(String domainType,
						String orgName,
						String orgDescrption,
						String firstName,
						String lastName,		
						String status,
						String featureList){
		
		orgName = CommonUtils.getProperty(PropertyName.ADMIN_ORG_NAME);
		String orgAdminUserId = CommonUtils.getProperty(PropertyName.ADMIN_EMAIL);
		
		new LoginPageAdmin().
			doSuccessLogin().
			openAddNewOrganizationPage().
			createNewOrg(domainType, orgName, orgDescrption, orgAdminUserId, firstName, lastName, status, featureList, true, "Internal-Test").
			checkOrganizationExists(orgName).
			clickLogOut();
		
		CommonUtils.setProperty(PropertyName.ADMIN_EMAIL, orgAdminUserId);
		CommonUtils.setProperty(PropertyName.ADMIN_FIRST_NAME, firstName);
		CommonUtils.setProperty(PropertyName.ADMIN_LAST_NAME, lastName);
		CommonUtils.setProperty(PropertyName.ADMIN_ORG_NAME, orgName);
		return this;
	}
	
    @Test(retryAnalyzer=RetryAnalyzer.class, priority=20, enabled = true, groups = {"dev", "test", "createAdmin"}, dependsOnMethods="createOrgTest", description = "489:52:New Admin account was NOT confirmed")
	@Parameters({"newuser.password"})
	public AdminTest confirmAdminAccountTest(String userPassword){
    	
		LoginPage loginPage = new LoginPage();
		loginPage.
		openConfirmationPage().
		completeInvite(userPassword).
		verifyHomePageIsOpened();
		
    	CommonUtils.setProperty(PropertyName.ADMIN_PASSWORD, userPassword);
		CommonUtils.setProperty(PropertyName.AMOUNT_OF_SUPPORTERS, String.valueOf(1));		
		return this;
	}
	
	@Test(retryAnalyzer=RetryAnalyzer.class, priority=30, groups = {"acceptanceTests.user", "dev", "test", "createAdmin"}, description = "489:52:New admin can NOT login", dependsOnMethods="confirmAdminAccountTest")
	public void loginAsNewSuperAdminTest(){
		
		LoginPage loginPage = new LoginPage();
		loginPage.
		doSuccessLogin(CommonUtils.getProperty(PropertyName.ADMIN_EMAIL),  CommonUtils.getProperty(PropertyName.ADMIN_PASSWORD)).
		verifyHomePageIsOpened();
	}
}
