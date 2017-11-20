package com.salsalabs.ignite.automation.suites.regression;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.common.Supporter;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.activities.ActivitiesPage;
import com.salsalabs.ignite.automation.pages.hq.activities.AddPetitionPage;
import com.salsalabs.ignite.automation.pages.hq.supporters.SupportersPage;

public class CreatePetitionTest extends SeleneseTestCase {
	private AddPetitionPage addPetitionPage;
	private ActivitiesPage activitiesPage;
	private SupportersPage supportersPage;

	String widgetName;
	String widgetDescription;
	public final String LAYOUT_HERO = "Hero";
	public static  final String ApproveComment = "Post";
	public static  final String rejectComment = "Hide";
	

	@Parameters({ "login", "passward" })
	@Test(enabled = true, priority = 6, groups = { "createAndPublishPetition" }, retryAnalyzer = RetryAnalyzer.class)
	public void createAndPublishPetitionTest(String login , String pass) {
		doLoginAndOpenPetitionsPage(login , pass);
		widgetName = "PName_" + RandomStringUtils.randomAlphanumeric(10);
		widgetDescription = "PDesc_" + RandomStringUtils.randomAlphanumeric(10);
		
		addPetitionPage.fillFieldsWidgetStepOne(widgetName, widgetDescription).
		selectLayoutStep(LAYOUT_HERO).
		proceedToTheNextAutoresponderStep().
		publishForm().
		verifyFormLinkIsPresent(widgetName);
		activitiesPage = addPetitionPage.openActivitiesPage().openAllActivitiesTab();
		activitiesPage.verifyActivityIsPresentInTableAllActivities("Petition", widgetName, widgetDescription,
				"PUBLISHED");
		activitiesPage.openPetitionsPage().verifyWidgetIsPresentInTableForms(widgetName, widgetDescription, "PUBLISHED",
				"PUBLIC");
		addPetitionPage.verifyWidgetVisible(true);

	}

	@Parameters({ "login", "passward" , "widget" })
	@Test(enabled = true, priority = 1, groups = {"createAndPublishPetition" }, retryAnalyzer = RetryAnalyzer.class)
	public void signPetitionTest(String login, String pass , String widget) {
		String comment = "Comment_" + RandomStringUtils.randomAlphanumeric(5);
		Supporter sup = Supporter.generateSupporter();
		LoginPage lp = new LoginPage();
		lp.openPetitionWidgetByLink(widget).
		signPetition(sup, comment, true, true).
		verifyNewSignature(sup, comment, true, true);
		doLoginAndOpenSupportersPage(login , pass).
		verifySupporterOnTopOfTableByEmail(sup).
		openSupporterDetailsPage().
		verifySupporterStatus("Subscribed");
		
	}

	@Parameters({ "login", "passward" , "widget" })
	  @Test(enabled = true, priority = 2, groups = { "createAndPublishPetition"}, retryAnalyzer = RetryAnalyzer.class)
	public void anonymousSignPetitionTest(String login, String pass , String widget) { 
		String comment = "Comment_" + RandomStringUtils.randomAlphanumeric(5);
		Supporter sup = Supporter.generateSupporter();
		LoginPage lp = new LoginPage();
		lp.openPetitionWidgetByLink(widget).
		signPetition(sup, comment, false, true).
		verifyNewSignature(sup, comment, false, true);
		  
	}
	  

	@Parameters({ "login", "passward", "widget" })
	@Test(enabled = true, priority = 3, groups = {
			"createAndPublishPetition" }, retryAnalyzer = RetryAnalyzer.class)
	public void signPetitionHideCommentTest(String login, String pass , String widget) {
		String comment = "Comment_" + RandomStringUtils.randomAlphanumeric(5);
		Supporter sup = Supporter.generateSupporter();
		LoginPage lp = new LoginPage();
		lp.openPetitionWidgetByLink(widget).
		signPetition(sup, comment, true, false).
		verifyNewSignature(sup, comment, true, false);
	}


	@Parameters({ "login", "passward" ,"widget" })
	@Test(enabled = true, priority = 5, groups = {
			"createAndPublishPetition" }, retryAnalyzer = RetryAnalyzer.class, dependsOnMethods ="createAndPublishPetitionTest")
	public void testPetitionVisibility() {
		addPetitionPage.openActivitiesPage().openPetitionsPage();
		addPetitionPage = activitiesPage.openPetitionFromTable();
		addPetitionPage.makeWidgetPrivate();
		addPetitionPage.verifyWidgetVisible(false);
		activitiesPage = addPetitionPage.openActivitiesPage().openAllActivitiesTab();
		activitiesPage.verifyActivityIsPresentInTableAllActivities("Petition", widgetName, widgetDescription, "DRAFT");
		activitiesPage.openPetitionsPage().verifyWidgetIsPresentInTableForms(widgetName, widgetDescription, "DRAFT",
				"PRIVATE");
		activitiesPage.removeWidgetSuccessfully();
		activitiesPage.verifyWidgetIsNotPresentInTableForms(widgetName, widgetDescription);
		activitiesPage.openAllActivitiesTab().verifyActivityIsNotPresentInTableAllActivities(widgetName,
				widgetDescription);
		addPetitionPage.verifyWidgetVisible(false);
	}
	
	@Parameters({ "login", "passward", "widgetforCommentsURl" , "widgetforCommentsName"})
	@Test(enabled = true, priority = 4, groups = {
			"createAndPublishPetition" }, retryAnalyzer = RetryAnalyzer.class)
	public void approveCommentsTest(String login, String pass , String widgetforCommentsURl, String widgetforCommentsName) {
		String comment = "Comment_" + RandomStringUtils.randomAlphanumeric(5);
		Supporter sup = Supporter.generateSupporter();
		LoginPage lp = new LoginPage();
		lp.openPetitionWidgetByLink(widgetforCommentsURl).
		signPetition(sup, comment, true, true);
		lp.doSuccessLogin(login, pass).
		openActivitiesPage().
		openPetitionsPage().
		openModerateComments().
		verifyCommentInTheTable(widgetforCommentsName, comment, sup).
		moderateComments( widgetforCommentsName, ApproveComment).
		clickTheApprovedCommentsTab().
		verifyCommentInTheTable(widgetforCommentsName, comment, sup);
		lp.openPetitionWidgetByLink(widgetforCommentsURl).
		verifyNewSignature(sup, comment, true, true);
	}
	
	@Parameters({ "login", "passward", "widget" , "widgenName"})
	@Test(enabled = true, priority = 5, groups = {
			"createAndPublishPetition" }, retryAnalyzer = RetryAnalyzer.class)
	public void rejectCommentsTest(String login, String pass , String widget, String widgenName) {
		String comment = "Comment_" + RandomStringUtils.randomAlphanumeric(5);
		Supporter sup = Supporter.generateSupporter();
		LoginPage lp = new LoginPage();
		lp.openPetitionWidgetByLink(widget).
		signPetition(sup, comment, true, true);
		lp.doSuccessLogin(login, pass).
		openActivitiesPage().
		openPetitionsPage().
		openModerateComments().
		clickTheApprovedCommentsTab().
		verifyCommentInTheTable(widgenName, comment, sup).
		moderateComments( widgenName, rejectComment).
		clickTheRejectedCommentsTab().
		verifyCommentInTheTable(widgenName, comment, sup);
		lp.openPetitionWidgetByLink(widget).
		verifyNewSignature(sup, comment, true, false);
	}
	

	private void doLoginAndOpenPetitionsPage(String login, String pass) {
		addPetitionPage = new LoginPage().doSuccessLogin(login , pass).openActivitiesPage().openPetitionsPage()
				.openAddPetitionPage();
	}
	
	private SupportersPage doLoginAndOpenSupportersPage(String login, String pass) {
		supportersPage = new LoginPage().doSuccessLogin(login , pass).openActivitiesPage().openAudiencePage().openSupportersPage();
		return new SupportersPage();
	}


}
