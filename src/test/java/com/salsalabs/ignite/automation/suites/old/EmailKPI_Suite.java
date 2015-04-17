package com.salsalabs.ignite.automation.suites.old;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.tests.old.AdminTest;
import com.salsalabs.ignite.automation.tests.old.EmailBlastTest;
import com.salsalabs.ignite.automation.tests.old.SettingsTests;
import com.salsalabs.ignite.automation.tests.old.SupportersTests;

public class EmailKPI_Suite extends SeleneseTestCase{

	@Test(retryAnalyzer=RetryAnalyzer.class, enabled = true, groups = {"createAdmin"}, description = "484:51:New org was NOT created")
	@Parameters({     	
     	"createOrg.domainType",
		"createOrg.orgName",
		"createOrg.orgDescrption",
		"createOrg.firstName",
		"createOrg.lastName",		
		"createOrg.status",
		"createOrg.featureList" })
	public void createOrgTest(	     	
		     	String domainType,
				String orgName,
				String orgDescrption,
				String firstName,
				String lastName,		
				String status,
				String featureList){
		
		new AdminTest().createOrgTest(domainType, orgName, orgDescrption, firstName, lastName, status, featureList);	
	}
	
	@Test(retryAnalyzer=RetryAnalyzer.class, enabled = true, groups = {"createAdmin"}, dependsOnMethods="createOrgTest", description = "489:52:New Admin account was NOT confirmed")
	@Parameters({"newuser.password"})
	public void confirmAdminAccountTest(String userPassword){
		new AdminTest().confirmAdminAccountTest(userPassword);
	}
	
	@Test(retryAnalyzer=RetryAnalyzer.class, priority=30, groups = {"createAdmin"}, description = "489:52:New admin can NOT login", dependsOnMethods="confirmAdminAccountTest")
	public void loginAsNewSuperAdminTest(){
		new AdminTest().loginAsNewSuperAdminTest();
	}
	
	@Test(retryAnalyzer=RetryAnalyzer.class,  priority=40, groups = {"settings.import"}, description = "", dependsOnGroups={"createAdmin"})
	public void importSupportersTest() {
		
		new SettingsTests().importSupportersTest();
	}
	
	@Parameters({ "createSupporter.amount"})
	@Test(retryAnalyzer=RetryAnalyzer.class, priority=50, groups = {"acceptanceTests.user", "createSupporter.manually"}, description = "494:53:Supporter was NOT created",
			dependsOnGroups={"createAdmin"})
	public void createSupporerManually(Integer amount){
		
		new SupportersTests().createSupporerManually(amount);		
	}
	
	@Parameters({"sendEmail.from", "sendEmailImport.OpenAmount", "sendEmailImport.ClickAmount", "sendEmailImport.emailOfSupporter", "sendEmailImport.amountOfSupporter"})
	@Test(retryAnalyzer=RetryAnalyzer.class,  priority=60, groups = {"email.sendEmails", ""}, description = "", dependsOnGroups={"settings.import"})
	public void sendEmailsTestForImported(String emailFrom, Integer openAmount, Integer clickAmount, String emailOfSupporter, Integer amountOfSupporters) {
		
		new EmailBlastTest().sendEmailsTest(emailFrom, openAmount, clickAmount, "", amountOfSupporters, 2);
	}
	
	@Parameters({"sendEmail.from", "sendEmailManuall.OpenAmount", "sendEmailManuall.ClickAmount", "sendEmailManuall.emailOfSupporter", "sendEmailManuall.amountOfSupporter"})
	@Test(retryAnalyzer=RetryAnalyzer.class,  priority=70, groups = {"email.sendEmails", ""}, description = "", dependsOnGroups={"createSupporter.manually"})
	public void sendEmailsTestForManual(String emailFrom, Integer openAmount, Integer clickAmount, String emailOfSupporter, Integer amountOfSupporters) {
		
		new EmailBlastTest().sendEmailsTest(emailFrom, openAmount, clickAmount, emailOfSupporter, amountOfSupporters, 0);
	}
	
	

}
