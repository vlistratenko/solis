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

	@Parameters({ "login", "passward" })
	@Test(enabled = true, priority = 4, groups = { "createAndPublishPetition" }, retryAnalyzer = RetryAnalyzer.class)
	public void createAndPublishPetitionTest(String login , String pass) {
		doLoginAndOpenPetitionsPage(login , pass);
		widgetName = "PetitionName_" + RandomStringUtils.randomAlphanumeric(5);
		widgetDescription = "PetitionDescription_" + RandomStringUtils.randomAlphanumeric(10);
		
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
		signPetition(sup, comment, true, true);
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
		signPetition(sup, comment, false, true);
		  
	
	}
	  
	
	
	@Parameters({ "login", "passward", "widget" })
	@Test(enabled = true, priority = 3, groups = {
			"createAndPublishPetition" }, retryAnalyzer = RetryAnalyzer.class)
	public void signPetitionHideCommentTest(String login, String pass , String widget) {
		String comment = "Comment_" + RandomStringUtils.randomAlphanumeric(5);
		Supporter sup = Supporter.generateSupporter();
		LoginPage lp = new LoginPage();
		lp.openPetitionWidgetByLink(widget).
		signPetition(sup, comment, true, false);
	}

	/**
	 * <b>Unpublishing and deleting petition.</b>
	 * <p>
	 * Steps:
	 * <ul>
	 * <li>Open Petitions tab
	 * <li>Open form editing by clicking on form in table
	 * <li>Click on settings icon in the bottom of screen and click on Make it
	 * Private
	 * <li>Open form by link in separated window
	 * <li><font color="green"><b>Verify that form is visible to CM (Verify all
	 * form elements are displayed)</b></font>
	 * <li>Delete cookies and refresh page
	 * <li><font color="green"><b>Verify that form is NOT visible to potential
	 * supporter (Verify submit button is not displayed)</b></font>
	 * <li>Close window with form
	 * <li>Open Activities page --> All Activities
	 * <li><font color="green"><b>Verify that record about the widget is on the
	 * top of the table (All Activities) and status is DRAFT</b></font>
	 * <li>Open Petitions tab
	 * <li><font color="green"><b>Verify that record about the widget is on the
	 * top of the table (Petitions) and status is DRAFT and visibility is
	 * PRIVATE</b></font>
	 * <li>Select the form in table
	 * <li><font color="green"><b>Verify that button for removing is
	 * appeared</b></font>
	 * <li>Click on Remove button, confirm action in modal window
	 * <li><font color="green"><b>Verify that table do not contains record about
	 * form (All Activities)</b></font>
	 * <li>Open Petitions tab
	 * <li><font color="green"><b>Verify that table do not contains record about
	 * form (Petitions)</b></font>
	 * <li>Open in separate window
	 * <li>Open form by link in separated window
	 * <li><font color="green"><b>Verify that form is NOT visible to CM (Verify
	 * submit button is not displayed)</b></font>
	 * <li>Delete cookies and refresh page
	 * <li><font color="green"><b>Verify that form is NOT visible to potential
	 * supporter (Verify submit button is not displayed)</b></font>
	 * <li>Close window with form
	 * </ul>
	 * 
	 */
	@Parameters({ "login", "passward" })
	@Test(enabled = true, priority = 5, groups = {
			"createAndPublishPetition" }, retryAnalyzer = RetryAnalyzer.class, dependsOnMethods ="createAndPublishPetitionTest")
	public void testPetitionVisibility() {
		addPetitionPage.openActivitiesPage().openPetitionsPage();
		// click on widget in table to open it
		addPetitionPage = activitiesPage.openPetitionFromTable();
		// make widget private
		addPetitionPage.makeWidgetPrivate();
		// open widget link in new tab and ensure it NOT visible
		addPetitionPage.verifyWidgetVisible(false);
		// open Activities menu item in left sidebar and check that petition is
		// present in table (Draft state)
		activitiesPage = addPetitionPage.openActivitiesPage().openAllActivitiesTab();
		activitiesPage.verifyActivityIsPresentInTableAllActivities("Petition", widgetName, widgetDescription, "DRAFT");
		// open Petitions tab and check that our form is present in table too
		// (Draft state)
		activitiesPage.openPetitionsPage().verifyWidgetIsPresentInTableForms(widgetName, widgetDescription, "DRAFT",
				"PRIVATE");
		// remove widget
		activitiesPage.removeWidgetSuccessfully();
		// make sure it's not present in in table in All Activities tab
		activitiesPage.verifyWidgetIsNotPresentInTableForms(widgetName, widgetDescription);
		// make sure it's not present in table in Petitions tab
		activitiesPage.openAllActivitiesTab().verifyActivityIsNotPresentInTableAllActivities(widgetName,
				widgetDescription);
		// try to open widget in separate window
		addPetitionPage.verifyWidgetVisible(false);
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
