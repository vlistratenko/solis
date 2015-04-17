package com.salsalabs.ignite.automation.suites;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.activities.ActivitiesPage;
import com.salsalabs.ignite.automation.pages.hq.activities.AddSubscribeWidgetPage;


/**
 * <b>This test contains scenarios related to sign-up form creation (TestLink: TC16)</b>
 *
 */
public class CreateSignupFormTest extends SeleneseTestCase {
	private String[] layouts = {"Hero", "Sidebar Right", "Hero Sidekick", "Newsletter", 
			"Sidebar Hero Left", "Sidebar Left", "Sidebar Hero Right", "Basic"};
	private AddSubscribeWidgetPage addSignupFormsPage;
	private ActivitiesPage activitiesPage;
	
	/**
	 * <b>Create sign-up form.</b>
	 * <p>
	 * Steps:
	 * <ul>
	 * <li> Login into existing organization
	 * <li> Open Activities page --> Fundraising Forms
	 * <li> Click on Create a Fundraising Form button
	 * <li> Fill title and description
	 * <li> Choose random layout
	 * <li> Leave default design and click Next button
	 * <li> Leave default settings, click Publish button
	 * <li> <font color="green"><b>Verify that link of form is listed on the page</b></font>
	 * <li> Open Activities page --> All Activities
	 * <li> <font color="green"><b>Verify that record about the widget is on the top of the table (All Activities) and status is PUBLISHED </b></font>
	 * <li> Open Sign-Up Forms tab
	 * <li> <font color="green"><b>Verify that record about the widget is on the top of the table (Sign-Up Forms) and status is PUBLISHED and visibility is PUBLIC</b></font>
	 * <li> Open in separated window link to form
	 * <li> <font color="green"><b>Verify that form is visible to CM</b></font>
	 * <li> Delete cookies and refresh page
	 * <li> <font color="green"><b>Verify that form is visible to potential supporter</b></font>
	 * <li> Close window with form
	 * <li> Open form editing by clicking on form in table
	 * <li> Click on settings icon in the bottom of screen and click on Make it Private
	 * <li> Open form by link in separated window
	 * <li> <font color="green"><b>Verify that form is visible to CM</b></font>
	 * <li> Delete cookies and refresh page
	 * <li> <font color="green"><b>Verify that form is NOT visible to potential supporter</b></font>
	 * <li> Close window with form
	 * <li> Open Activities page --> All Activities
	 * <li> <font color="green"><b>Verify that record about the widget is on the top of the table (All Activities) and status is DRAFT</b></font>
	 * <li> Open Sign-Up Forms tab
	 * <li> <font color="green"><b>Verify that record about the widget is on the top of the table (Sign-Up Forms) and status is DRAFT and visibility is PRIVATE</b></font>
	 * <li> Select the form in table
	 * <li> <font color="green"><b>Verify that button for removing is appeared</b></font>
	 * <li> Click on Remove button, confirm action in modal window
	 * <li> <font color="green"><b>Verify that table do not contains record about form (All Activities)</b></font>
	 * <li> Open Fundraising Forms tab
	 * <li><font color="green"><b>Verify that table do not contains record about form (Sign-Up Forms)</b></font>
	 * <li> Open in separate window 
	 * <li> Open form by link in separated window
	 * <li> <font color="green"><b>Verify that form is NOT visible to CM</b></font>
	 * <li> Delete cookies and refresh page
	 * <li> <font color="green"><b>Verify that form is NOT visible to potential supporter</b></font>
	 * <li> Close window with form
	 * </ul>
	 *  
	 */
	@Test(groups = {"createAndPublishSignupForm"}, retryAnalyzer = RetryAnalyzer.class)
	public void testCreateAndPublishSignupForm() {
		doLoginAndOpenSignupFormsPage();
		String widgetName = "SubscribeWidgetName_" + RandomStringUtils.randomAlphanumeric(5);
		String widgetDescription = "SubscribeWidgetDescription_" + RandomStringUtils.randomAlphanumeric(10);
		// choose random layout
		String layoutName = this.layouts[RandomUtils.nextInt(0, this.layouts.length)];
		String expectedLink = ("https://automatedtesting1." + SeleneseTestCase.USED_ENVIRONMENT.getEnvironment().name() + ".igniteaction.net/" + widgetName).toLowerCase();
		// fill title and description
		addSignupFormsPage.fillFieldsSubscribeWidgetStepOne(widgetName, widgetDescription);
		// select layout for form
		addSignupFormsPage.selectLayoutForSubscribeWidgetStep(layoutName);
		// leave default design and go to settings
		addSignupFormsPage.fillFieldsSubscribeWidgetStepTwo();
		// leave default settings and publish form
		addSignupFormsPage.publishForm();
		// verify link is present on page
		addSignupFormsPage.verifyFormLinkIsPresent(expectedLink);
		// verify that new widget is present in table in All Activities tab (Published state)
		activitiesPage = addSignupFormsPage.openActivitiesPage().openAllActivitiesTab();
		activitiesPage.verifyActivityIsPresentInTableAllActivities("Sign-up Form", widgetName, widgetDescription, "PUBLISHED");
		// verify that new widget present in table in Signup Forms tab (Published state)
		activitiesPage.openSubscribeWidgetsPage().verifyWidgetIsPresentInTableSignupForms(widgetName, widgetDescription, "PUBLISHED", "PUBLIC");
		// open widget link in new tab and ensure it visible for CM and supporter
		activitiesPage = addSignupFormsPage.verifyWidgetVisible(expectedLink, true, true);
		// click on widget in table to open it
		addSignupFormsPage = activitiesPage.openWidgetFromTable();
		// make widget private
		addSignupFormsPage.makeWidgetPrivate();
		// open widget link in new tab and ensure it NOT visible
		addSignupFormsPage.verifyWidgetVisible(expectedLink, true, false);
		// open Activities menu item in left sidebar and check that signup form is present in table (Draft state)
		activitiesPage = addSignupFormsPage.openActivitiesPage().openAllActivitiesTab();
		activitiesPage.verifyActivityIsPresentInTableAllActivities("Sign-up Form", widgetName, widgetDescription, "DRAFT");
		// open Sign-Up Forms tab and check that our form is present in table too (Draft state)
		activitiesPage.openSubscribeWidgetsPage().verifyWidgetIsPresentInTableSignupForms(widgetName, widgetDescription, "DRAFT", "PRIVATE");;
		// remove widget
		activitiesPage.removeWidgetSuccessfully();
		// make sure it's not present in in table in All Activities tab
		activitiesPage.verifyWidgetIsNotPresentInTableSignupForms(widgetName, widgetDescription);
		// make sure it's not present in table in Signup Forms tab
		activitiesPage.openAllActivitiesTab().verifyActivityIsNotPresentInTableAllActivities(widgetName, widgetDescription);
		// try to open widget in separate window
		addSignupFormsPage.verifyWidgetVisible(expectedLink, false, false);
 	}
	
	private void doLoginAndOpenSignupFormsPage() {
		addSignupFormsPage = new LoginPage().doSuccessLogin().openActivitiesPage().
				openSubscribeWidgetsPage().openAddSubscribeWidgetPage();
	}

}
