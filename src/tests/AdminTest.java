package tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.Admin.LoginPageAdmin;
import pages.HQ.LoginPage;
import selenium.CommonUtils;
import selenium.EmailClient;
import selenium.SeleneseTestCase;

public class AdminTest extends SeleneseTestCase{
	@Test(priority=10, enabled = true, groups = {/*"acceptanceTests.admin",*/ "dev", "test", "createAdmin"}, description = "484:51:New org was NOT created")
	@Parameters({ "admin.login",
     	"admin.password",     	
     	"createOrg.domainType",
		"createOrg.orgName",
		"createOrg.orgDescrption",
		"createOrg.firstName",
		"createOrg.lastName",		
		"createOrg.status",
		"createOrg.featureList" })
	
	public void createOrgTest(String login,
				     	String password,	     	
				     	String domainType,
						String orgName,
						String orgDescrption,
						String firstName,
						String lastName,		
						String status,
						String featureList){
		
		orgName = orgName + CommonUtils.getUnicName();
		String orgAdminUserId = EmailClient.getEmailBox(CommonUtils.getUnicName());
		
		new LoginPageAdmin().
			doSuccessLogin(login, password).
			openAddNewOrganizationPage().
			createNewOrg(domainType, orgName, orgDescrption, orgAdminUserId, firstName, lastName, status, featureList).
			checkOrganizationExists(orgName).
			clickLogOut();
		
		CommonUtils.setProperty("Admin.email", orgAdminUserId);
		CommonUtils.setProperty("Admin.firstName", firstName);
		CommonUtils.setProperty("Admin.lastName", lastName);
		CommonUtils.setProperty("Admin.orgName", orgName);
	}
	
    @Test(priority=20, enabled = true, groups = {"dev", "test", "createAdmin"}, dependsOnMethods="createOrgTest", description = "489:52:New Admin account was NOT confirmed")
	@Parameters({ "email.login",
     	"email.password",
     	"newuser.password"})
	public void confirmAdminAccountTest(String login,
	     	String password,	     	
			String userPassword){
    	
		LoginPage loginPage = new LoginPage();
		loginPage.
		openConfirmationPage().
		completeInvite(userPassword).
		verifyHomePageIsOpened();
		
    	CommonUtils.setProperty("Admin.Password", userPassword);
		CommonUtils.setProperty("amountOfSupporters", String.valueOf(1));		
		
	}
	
	@Test(priority=30, groups = {"acceptanceTests.user", "dev", "test", "createAdmin"}, description = "489:52:New admin can NOT login", dependsOnMethods="confirmAdminAccountTest")
	public void loginAsNewSuperAdminTest(){
		
		LoginPage loginPage = new LoginPage();
		loginPage.
		doSuccessLogin(CommonUtils.getProperty("Admin.email"),  CommonUtils.getProperty("Admin.Password")).
		verifyHomePageIsOpened();
	}
}
