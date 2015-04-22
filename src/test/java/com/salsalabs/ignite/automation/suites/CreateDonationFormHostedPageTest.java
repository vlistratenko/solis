package com.salsalabs.ignite.automation.suites;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.activities.ActivitiesPage;
import com.salsalabs.ignite.automation.pages.hq.activities.AddDonationWidgetPage;

/**
 * <b>This test contains scenarios related to donation form creation (TestLink: TC25)</b>
 * @author a.hubachov
 */
public class CreateDonationFormHostedPageTest extends SeleneseTestCase {
	private AddDonationWidgetPage donationsAddPage;
	private ActivitiesPage activitiesPage;
	
	/**
	 * <b>Create Donation form.</b>
	 * <p>
	 * Steps:
	 * <ul>
	 * <li> Login into existing organization
	 * <li> Open Activities page --> Donation Forms
	 * <li> Click on Create a Donation Form button
	 * <li> Fill title and description
	 * <li> Choose random layout
	 * <li> Leave default design and click Next button
	 * <li> Leave default settings, click Publish button
	 * <li> <font color="green"><b>Verify that link of form is listed on the page</b></font>
	 * <li> Open Activities page --> All Activities
	 * <li> <font color="green"><b>Verify that record about the widget is on the top of the table (All Activities) and status is PUBLISHED </b></font>
	 * <li> Open Donation Forms tab
	 * <li> <font color="green"><b>Verify that record about the widget is on the top of the table (Donation Forms) and status is PUBLISHED and visibility is PUBLIC</b></font>
	 * <li> Open in separated window link to form
	 * <li> <font color="green"><b>Verify that form is visible to CM (Verify all form elements are displayed)</b></font>
	 * <li> Delete cookies and refresh page
	 * <li> <font color="green"><b>Verify that form is visible to potential supporter (Verify all form elements are displayed)</b></font>
	 * <li> Close window with form
	 * <li> Open form editing by clicking on form in table
	 * <li> Click on settings icon in the bottom of screen and click on Make it Private
	 * <li> Open form by link in separated window
	 * <li> <font color="green"><b>Verify that form is visible to CM (Verify all form elements are displayed)</b></font>
	 * <li> Delete cookies and refresh page
	 * <li> <font color="green"><b>Verify that form is NOT visible to potential supporter (Verify submit button is not displayed)</b></font>
	 * <li> Close window with form
	 * <li> Open Activities page --> All Activities
	 * <li> <font color="green"><b>Verify that record about the widget is on the top of the table (All Activities) and status is DRAFT</b></font>
	 * <li> Open Donation Forms tab
	 * <li> <font color="green"><b>Verify that record about the widget is on the top of the table (Donation Forms) and status is DRAFT and visibility is PRIVATE</b></font>
	 * <li> Select the form in table
	 * <li> <font color="green"><b>Verify that button for removing is appeared</b></font>
	 * <li> Click on Remove button, confirm action in modal window
	 * <li> <font color="green"><b>Verify that table do not contains record about form (All Activities)</b></font>
	 * <li> Open Donation Forms tab
	 * <li><font color="green"><b>Verify that table do not contains record about form (Donation Forms)</b></font>
	 * <li> Open in separate window 
	 * <li> Open form by link in separated window
	 * <li> <font color="green"><b>Verify that form is NOT visible to CM (Verify submit button is not displayed)</b></font>
	 * <li> Delete cookies and refresh page
	 * <li> <font color="green"><b>Verify that form is NOT visible to potential supporter (Verify submit button is not displayed)</b></font>
	 * <li> Close window with form
	 * </ul>
	 *  
	 */
	@Test(groups = {"createAndPublishDonationForm"}, retryAnalyzer = RetryAnalyzer.class)
	public void testCreateAndPublishDonationForm() {
		String formName = "DonationFormName_" + RandomStringUtils.randomAlphanumeric(5);
		String formDescription = "DonationFormDescription_" + RandomStringUtils.randomAlphanumeric(10);
		String expectedLink = ("https://automatedtesting1." + SeleneseTestCase.USED_ENVIRONMENT.getEnvironment().name() + ".igniteaction.net/" + formName).toLowerCase();
		doLoginAndOpenDonationForms();
		// enter name and description
		donationsAddPage.createForm(formName, formDescription);
		// choose layout
		donationsAddPage.selectLayoutStep();
		// leave default design
		donationsAddPage.fillThirdStep();
		// leave default settings and publish form
		donationsAddPage.publishForm();
		// verify that link is present on page
		donationsAddPage.verifyFormLinkIsPresent(expectedLink);
		// verify that new widget is present in table in All Activities tab (Published state)
		activitiesPage = donationsAddPage.openActivitiesPage().openAllActivitiesTab();
		activitiesPage.verifyActivityIsPresentInTableAllActivities("Fundraising Form", formName, formDescription, "PUBLISHED");
		// verify that new widget present in table in Fundraising Forms tab (Published state)
		activitiesPage.openFundraisingWidgetPage().verifyWidgetIsPresentInTableForms(formName, formDescription, "PUBLISHED", "PUBLIC");
		// open widget link in new tab and ensure it visible for CM and supporter
		donationsAddPage.verifyWidgetVisible(expectedLink, true, true);
		// click on widget in table to open it
		donationsAddPage = activitiesPage.openDonationsWidgetFromTable();
		// make widget private
		donationsAddPage.makeWidgetPrivate();
		// open widget link in new tab and ensure it NOT visible
		donationsAddPage.verifyWidgetVisible(expectedLink, true, false);
		// open Activities menu item in left sidebar and check that donation form is present in table (Draft state)
		activitiesPage = donationsAddPage.openActivitiesPage().openAllActivitiesTab();
		activitiesPage.verifyActivityIsPresentInTableAllActivities("Fundraising Form", formName, formDescription, "DRAFT");
		// open Fundraising Forms tab and check that our form is present in table too (Draft state)
		activitiesPage.openFundraisingWidgetPage().verifyWidgetIsPresentInTableForms(formName, formDescription, "DRAFT", "PRIVATE");;
		// remove widget
		activitiesPage.removeWidgetSuccessfully();
		// make sure it's not present in in table in All Activities tab
		activitiesPage.verifyWidgetIsNotPresentInTableForms(formName, formDescription);
		// make sure it's not present in table in Fundraising Forms tab
		activitiesPage.openAllActivitiesTab().verifyActivityIsNotPresentInTableAllActivities(formName, formDescription);
		// try to open widget in separate window
		donationsAddPage.verifyWidgetVisible(expectedLink, false, false);
	}
	
	private void doLoginAndOpenDonationForms() {
		donationsAddPage = new LoginPage().doSuccessLogin().openActivitiesPage().openFundraisingWidgetPage().openAddDonationWidgetPage();
	}
}
