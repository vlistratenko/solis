package com.salsalabs.ignite.automation.suites;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.EmailClient;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.admin.LoginPageAdmin;
import com.salsalabs.ignite.automation.pages.hq.HomePage;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.supporters.SupportersPage;

public class BuildAcceptanceTests extends SeleneseTestCase {

	@Test(priority=10, enabled = true, groups = {/*"acceptanceTests.admin",*/ "dev", "test", "createAdmin"}, description = "484:51:New org was NOT created")
	@Parameters({"createOrg.domainType",
		"createOrg.orgName",
		"createOrg.orgDescrption",
		"createOrg.firstName",
		"createOrg.lastName",		
		"createOrg.status",
		"createOrg.featureList" })
	
	public void createOrgTest(String domainType,
						String orgName,
						String orgDescrption,
						String firstName,
						String lastName,		
						String status,
						String featureList) {
		
		orgName = orgName + CommonUtils.getUnicName();
		String orgAdminUserId = EmailClient.getEmailBox(CommonUtils.getUnicName());
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
	}
	
    @Test(priority=20, enabled = true, groups = {/*"acceptanceTests.admin",*/ "dev", "test", "createAdmin"}, dependsOnMethods="createOrgTest", description = "489:52:New Admin account was NOT confirmed")
	@Parameters({ "email.login",
     	"email.password",
     	"newuser.password"})
	public void confirmAdminAccountTest(String login,
	     	String password,	     	
			String userPassword){
		
		LoginPage loginPage = new LoginPage();
		loginPage.
		openConfirmationPage().
		completeInvite(userPassword);
		
		CommonUtils.setProperty(PropertyName.ADMIN_PASSWORD, userPassword);
		CommonUtils.setProperty(PropertyName.AMOUNT_OF_SUPPORTERS, String.valueOf(1));
	}
	
	@Test(priority=30, groups = {"acceptanceTests.user", "dev", "test", "createAdmin"}, description = "489:52:New admin can NOT login", dependsOnMethods="confirmAdminAccountTest")
	public void loginAsNewSuperAdminTest(){
		
		LoginPage loginPage = new LoginPage();
		loginPage.
		doSuccessLogin(CommonUtils.getProperty(PropertyName.ADMIN_EMAIL),  CommonUtils.getProperty(PropertyName.ADMIN_PASSWORD)).
		verifyHomePageIsOpened();
	}
	
	@Parameters({ "createSupporter.amount"})
	@Test(priority=40, groups = {"acceptanceTests.user", "dev"}, dependsOnMethods="loginAsNewSuperAdminTest", description = "494:53:Supporter was NOT created")
	public void createSupporerManually(Integer amount){
		
		HomePage homePage = new HomePage();
		SupportersPage supportersPage = homePage.
		openAudiencePage().
		openSupportersPage();
		
		for (int i = 0; i < amount; i++) {
			supportersPage.
			openAddSupporterPage().
			createNewSupporter().
			checkSupporterExists(CommonUtils.getParam("supporterEmail"));
			logger.info("Supporter �" + i + " was added");
		}
		amount = amount + Integer.parseInt(CommonUtils.getProperty(PropertyName.AMOUNT_OF_SUPPORTERS)); 
		CommonUtils.setProperty(PropertyName.AMOUNT_OF_SUPPORTERS, amount.toString());
		
	}
	
	//@Test(priority=50, groups = {"acceptanceTests.user", "dev"}, dependsOnMethods="loginAsNewSuperAdminTest", description = "510:57:Links are NOT correct in the Side bar.")
	public void verifyLinksInSideBarTest(){
		HomePage homePage = new HomePage();
		homePage.
		openActivitiesPage().
		verifyURL().
		openAudiencePage().
		verifyURL();
	}
	
	//@Test(priority=60, groups = {"acceptanceTests.user", "dev"}, dependsOnMethods="loginAsNewSuperAdminTest", description = "513:58:Links are NOT correct in the Tool set.")
	public void verifyLinksInToolSetTest(){
		HomePage homePage = new HomePage();
		homePage.
		openAlertPopup().
		checkAlertPanelIsPresent().
		openNewsPopup().
		verifyNewsPopUpIsExists().
		openSettingsPage().
		verifyURL();
	}
	
	@Parameters({"segmentName",
				 "addIncludeRule",
				 "addExcludeRule",
				 "criteriaForExcludeSupporterManually",
				 "criteriaForAddSupporterManually"})
	
	@Test(priority=70, groups = {"acceptanceTests.user", "qa"},  description = "516:59:Segment was NOT created.")
	public void createCustomSegmentTest(String segmentName,
							            String addIncludeRule,
							            String addExcludeRule,
							            String criteriaForExcludeSupporterManually,
							            String criteriaForAddSupporterManually){
		
		segmentName = segmentName + CommonUtils.getUnicName();
		///////
		LoginPage loginPage = new LoginPage();
		loginPage.
		doSuccessLogin(CommonUtils.getProperty(PropertyName.ADMIN_EMAIL),  CommonUtils.getProperty(PropertyName.ADMIN_PASSWORD));
		//////
		HomePage homePage = new HomePage();
		
		homePage.
		openAudiencePage().
		openSegmentsPage().
		openAddSegmentPage().
		createNewCustomSegment(segmentName,
						       addIncludeRule,
						       addExcludeRule,
						       criteriaForExcludeSupporterManually,
						       criteriaForAddSupporterManually).
		checkSegmentsExists(segmentName);
		CommonUtils.setProperty("segmentName", segmentName);
	
	}
	
	@Test(priority=80, groups = {"acceptanceTests.user", "dev"}, dependsOnMethods="creteSegmentTest",  description = "521:60:Alert for egment was NOT found.")
	public void verifyMessagesInAlertsTest() {
		loginAsNewSuperAdminTest();
		HomePage homePage = new HomePage();
		homePage.
		openAlertPopup().
		checkAlertIsPresentInTheList().
		openAlertsPage().
		verifyTableWithAlertsDisplayed();
	}
	
	//@Test(groups = {"acceptanceTests.user", "dev"}, dependsOnMethods="loginAsNewSuperAdminTest", priority=8, description = "521:60:News messages was NOT found.")
	public void verifyMessagesInNewsTest() {
		HomePage homePage = new HomePage();
		homePage.
		openNewsPopup().
		verifyNewsPopUpIsExists().
		openNewsPage().
		verifyTableWithMessagesDisplayed();
	}
	
	@Parameters({"sendEmail.From", "sendEmail.percentageOfTestGroup","sendEmail.splitsAmount"})
	@Test( priority=90, groups = {"acceptanceTests.user", "dev"}, dependsOnMethods="loginAsNewSuperAdminTest", description = "528:62:Split emails were NOT sent")
	public void sendTestEmailsTest(String emailFrom, Integer percentageOfTestGroup, int splitsAmount) {
		String emailBlastName = "TestV" + CommonUtils.getUnicName();
		String emailSubject = "TestVAuto" + CommonUtils.getUnicName();
		HomePage homePage = new HomePage();
		homePage.openActivitiesPage().
		openEmailBlastsPage().
		openAddEmailPage().
		fillAllFieldsAndGoForward(emailBlastName).
		selectAudienceType("Entire list ").
		SelectEmailType().
		openComposePage().
		selectLayout("Basic").
		fillAllFieldsAndGoForward(emailSubject, emailFrom, splitsAmount).
		fillAllFieldsAndPublish(percentageOfTestGroup, splitsAmount);
		
		CommonUtils.setProperty(PropertyName.EMAIL_BLAST_NAME, emailBlastName);
		if (splitsAmount <= 1) {
			CommonUtils.setProperty(PropertyName.EMAIL_SUBJECT, emailSubject);
		}else{
			CommonUtils.setProperty(PropertyName.EMAIL_SPLIT_SUBJECT, emailSubject);
		}
			
		CommonUtils.setProperty(PropertyName.EMAIL_FROM, emailFrom);
		if (splitsAmount > 1) {
			CommonUtils.setProperty(PropertyName.PERCENTAGE_OF_TEST_GROUP, percentageOfTestGroup.toString());
		}
		
	}
	
	@Test(priority=100, groups = {"acceptanceTests.user", "dev"}, dependsOnMethods="sendTestEmailsTest", description = "536:63:Split emails were NOT delivered")
	@Parameters({"sendEmail.splitsAmount"})
	public void confirmTestEmailsWereSentTest(int SplitsAmount){
		
		new LoginPage().
		verifyAmountOfEmails(Integer.valueOf(CommonUtils.getProperty(PropertyName.AMOUNT_OF_SUPPORTERS)), SplitsAmount, 5, true);
	}
	
	@Test(priority=110, groups = {"acceptanceTests.user", "dev"}, dependsOnMethods={"loginAsNewSuperAdminTest"}, description = "536:63:Emails were NOT sent")
	@Parameters({"sendEmail.From"})
	public void sendEmailsTest(String emailFrom) {
		sendTestEmailsTest(emailFrom, 100, 1);
		
	}
	
	@Test(expectedExceptions = AssertionError.class, priority=120, groups = {"acceptanceTests.user", "dev"}, dependsOnMethods="sendEmailsTest",description = "528:62:Emails were NOT delivered")
	@Parameters({ "email.login",
     	"email.password"})
	public void confirmEmailsWereSentTest(String login,
	     	String password){
		
		confirmTestEmailsWereSentTest(1);
	}
 

	/*****************************************/
	
	@Parameters({"cm.firstName",
				 "cm.lastName",
				 "cm.contentAndMessagingTable.role1",
				 "cm.contentAndMessagingTable.role2",
				 "cm.contentAndMessagingTable.role3",
				 "cm.dataAndAnalyticsTable.role1",
				 "cm.dataAndAnalyticsTable.role2",
				 "cm.dataAndAnalyticsTable.role3",
				 "cm.assetManagementTable.role1"})
	
	@Test (priority=130, groups = {"acceptanceTests.user", "dev"}, dependsOnMethods="loginAsNewSuperAdminTest", description = "506:56:New CM account was NOT created")
	public void createCMManually(String cmFirstName,
								 String cmLastName,
								 String cmContentAndMessagingRole1,
								 String cmContentAndMessagingRole2,
								 String cmContentAndMessagingRole3,
								 String cmDataAndAnalyticsRole1,
								 String cmDataAndAnalyticsRole2,
								 String cmDataAndAnalyticsRole3,
								 String cmAssetManagementRole1) {
				
		new EmailClient();
		String cmEmail =  EmailClient.getEmailBox("cm" + CommonUtils.getUnicName());
		HomePage homePage = new HomePage();
		homePage.
		openSettingsPage().
		openAccountsPage().
		openInviteNewUserPage().
		inviteNewUser(cmEmail,
					  cmFirstName,
					  cmLastName,
					  cmContentAndMessagingRole1,
					  cmContentAndMessagingRole2,
					  cmContentAndMessagingRole3,
					  cmDataAndAnalyticsRole1,
					  cmDataAndAnalyticsRole2,
					  cmDataAndAnalyticsRole3,
					  cmAssetManagementRole1).
		verifyInvitationSent();	
		
		CommonUtils.setProperty(PropertyName.CM_LOGIN, cmEmail);
		CommonUtils.setProperty(PropertyName.CM_FIRST_NAME, cmFirstName);
		CommonUtils.setProperty(PropertyName.CM_LAST_NAME, cmLastName);
		CommonUtils.setProperty(PropertyName.CM_EMAIL, cmEmail);
	}
	
	@Test(priority=140, groups = {"acceptanceTests.user", "dev"}, dependsOnMethods="createCMManually", description = "506:56:New CM account was NOT confirmed.")
	@Parameters({ "email.login",
     	"email.password",
     	"newuser.password"})
	public void confirmCMAccountTest(String login,
	     	String password,	     	
			String userPassword){
		LoginPage loginPage = new LoginPage();
		loginPage.
		openConfirmationPage().
		completeInvite(userPassword);
		
		CommonUtils.setProperty(PropertyName.CM_PASSWORD, userPassword);
		Integer newValueOfSupporters = Integer.valueOf(CommonUtils.getProperty(PropertyName.AMOUNT_OF_SUPPORTERS)) + 1;
		CommonUtils.setProperty(PropertyName.AMOUNT_OF_SUPPORTERS, newValueOfSupporters.toString());
		

	}

	@Test(priority=160, groups = {"acceptanceTests.user", "dev"}, dependsOnMethods="confirmCMAccountTest", description = "498:54:New CM can NOT login")
	public void loginAsNewCMTest(){
		
		LoginPage loginPage = new LoginPage();
		loginPage.
		doSuccessLogin(CommonUtils.getProperty(PropertyName.CM_LOGIN),  CommonUtils.getProperty(PropertyName.CM_PASSWORD));//.
		//verifyUserNameDisplayed().
		//verifyOrgNameDisplayed();
	}
	
	@Test(priority=170, groups = {"acceptanceTests.user"}, dependsOnMethods="loginAsNewCMTest")
	public void verifyLinksInSideBarTestAsCM(){
		verifyLinksInSideBarTest();
	}
	
	@Parameters({"segmentName",
				"segmentTag",
				"addIncludeRule",
				"addExcludeRule",
				"criteriaForExcludeSupporterManually",
				"criteriaForAddSupporterManually"})
	
	@Test(priority=18, groups = {"acceptanceTests.user"}, dependsOnMethods="loginAsNewCMTest")
	public void creteSegmentTestAsCM(String segmentName,
								     String segmentTag,
								     String addIncludeRule,
								     String addExcludeRule,
								     String criteriaForExcludeSupporterManually,
								     String criteriaForAddSupporterManually) {
		createCustomSegmentTest(segmentName, 
						     	addIncludeRule, 
						     	addExcludeRule, 
						     	criteriaForExcludeSupporterManually, 
						     	criteriaForAddSupporterManually);
	
	}
	
	@Test(priority=180, groups = {"acceptanceTests.user"}, dependsOnMethods="creteSegmentTestAsCM")
	public void verifyMessagesInAlertsTestAsCM() {
		verifyMessagesInAlertsTest();
	}
	
	@Test(priority=190, groups = {"acceptanceTests.user"}, dependsOnMethods="loginAsNewCMTest" )
	public void verifyMessagesInNewsTestAsCM() {
		verifyMessagesInNewsTest();
	}
	
	@Parameters({"sendEmail.From", "sendEmail.percentageOfTestGroup","sendEmail.splitsAmount"})
	@Test(priority=200, groups = {"acceptanceTests.user"}, dependsOnMethods="loginAsNewCMTest")
	public void sendTestEmailsTestAsCM(String emailFrom, Integer percentageOfTestGroup, int splitsAmount) {
		sendTestEmailsTest(emailFrom, percentageOfTestGroup, splitsAmount);
		
	}
	
	@Test(expectedExceptions = AssertionError.class, priority=210, groups = {"acceptanceTests.user"}, dependsOnMethods="sendTestEmailsTestAsCM")
	@Parameters({"sendEmail.splitsAmount"})
	public void confirmTestEmailsWereSentTestAsCM(int SplitsAmount){
		
		confirmTestEmailsWereSentTest(SplitsAmount);
	}
	
	@Test(priority=220, groups = {"acceptanceTests.user"}, dependsOnMethods="loginAsNewCMTest")
	@Parameters({"sendEmail.From"})
	public void sendEmailsTestAsCM(String emailFrom) {
		sendTestEmailsTest(emailFrom, 100, 1);
		
	}
	
	@Test(expectedExceptions = AssertionError.class, priority=230, groups = {"acceptanceTests.user"}, dependsOnMethods="sendEmailsTestAsCM")
	public void confirmEmailsWereSentTestAsCM(){
		
		confirmTestEmailsWereSentTest(1);
	}
 

		

}
