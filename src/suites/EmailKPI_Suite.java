package suites;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import selenium.SeleneseTestCase;
import tests.AdminTest;
import tests.EmailBlastTest;
import tests.SettingsTests;
import tests.SupportersTests;

public class EmailKPI_Suite extends SeleneseTestCase{

	@Test(enabled = true, groups = {"createAdmin"}, description = "484:51:New org was NOT created")
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
		
		new AdminTest().createOrgTest(login, password, domainType, orgName, orgDescrption, firstName, lastName, status, featureList);	
	}
	
	@Test(enabled = true, groups = {"createAdmin"}, dependsOnMethods="createOrgTest", description = "489:52:New Admin account was NOT confirmed")
	@Parameters({ "email.login",
     	"email.password",
     	"newuser.password"})
	public void confirmAdminAccountTest(String login,
	     	String password,	     	
			String userPassword){
		new AdminTest().confirmAdminAccountTest(login, password, userPassword);
	}
	
	@Test(priority=30, groups = {"createAdmin"}, description = "489:52:New admin can NOT login", dependsOnMethods="confirmAdminAccountTest")
	public void loginAsNewSuperAdminTest(){
		new AdminTest().loginAsNewSuperAdminTest();
	}
	
	@Test( priority=40, groups = {"settings.import"}, description = "", dependsOnGroups={"createAdmin"})
	public void importSupportersTest() {
		
		new SettingsTests().importSupportersTest();
	}
	
	@Parameters({ "createSupporter.amount"})
	@Test(priority=50, groups = {"acceptanceTests.user", "createSupporter.manually"}, description = "494:53:Supporter was NOT created",
			dependsOnGroups={"createAdmin"})
	public void createSupporerManually(Integer amount){
		
		new SupportersTests().createSupporerManually(amount);		
	}
	
	@Parameters({"sendEmail.From", "sendEmailImport.OpenAmount", "sendEmailImport.ClickAmount", "sendEmailImport.emailOfSupporter", "sendEmailImport.amountOfSupporter"})
	@Test( priority=60, groups = {"email.sendEmails", ""}, description = "", dependsOnGroups={"settings.import"})
	public void sendEmailsTestForImported(String emailFrom, Integer openAmount, Integer clickAmount, String emailOfSupporter, Integer amountOfSupporters) {
		
		new EmailBlastTest().sendEmailsTest(emailFrom, openAmount, clickAmount, emailOfSupporter, amountOfSupporters, 2);
	}
	
	@Parameters({"sendEmail.From", "sendEmailManuall.OpenAmount", "sendEmailManuall.ClickAmount", "sendEmailManuall.emailOfSupporter", "sendEmailManuall.amountOfSupporter"})
	@Test( priority=70, groups = {"email.sendEmails", ""}, description = "", dependsOnGroups={"createSupporter.manually"})
	public void sendEmailsTestForManual(String emailFrom, Integer openAmount, Integer clickAmount, String emailOfSupporter, Integer amountOfSupporters) {
		
		new EmailBlastTest().sendEmailsTest(emailFrom, openAmount, clickAmount, emailOfSupporter, amountOfSupporters, 0);
	}

}