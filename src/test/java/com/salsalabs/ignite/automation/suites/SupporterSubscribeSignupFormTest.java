package com.salsalabs.ignite.automation.suites;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.common.Supporter;
import com.salsalabs.ignite.automation.pages.hq.HomePage;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.activities.AddSubscribeWidgetPage;
import com.salsalabs.ignite.automation.pages.hq.activities.SubscribeWidget;
import com.salsalabs.ignite.automation.pages.hq.supporters.SupportersAddPage;
import com.salsalabs.ignite.automation.pages.hq.supporters.SupportersPage;

/**
 * <b>This test contains scenarios related to subscriptions of new and existing supporter on sign-up form (TestLink: TC17)</b>
 * @author a.hubachov
 *
 */
public class SupporterSubscribeSignupFormTest extends SeleneseTestCase {
	private AddSubscribeWidgetPage addSignupFormsPage;
	private HomePage homePage;
	private SubscribeWidget subscribeWidget;
	private SupportersPage supportersPage;
	private SupportersAddPage supportersAddPage;
	private Supporter supporter;
	private String widgetName;
	private String widgetDescription;

	/**
	 * <b>Subscribe Sign-Up form with existing supporter</b>
	 * <p>
	 * Steps:
	 * <ul>
	 * <li> Login into existing organization
	 * <li> <font color="green"><b>Verify supporter is not exist in current organization</b></font>
	 * <li> Create new supporter manually (detailed in TestLink: TC6)
	 * <li> <font color="green"><b>Verify supporter is exists in current organization</b></font>
	 * <li> Create sign-up form without verifications (detailed in TestLink: TC16)
	 * <li> Open widget in separate window, delete cookies and refresh page
	 * <li> Fill email, first name, last name, city and zip code in form
	 * <li> Click on Subscribe button
	 * <li> <font color="green"><b>Verify that page contains text about successful signing</b></font>
	 * <li> Close window
	 * <li> <font color="green"><b>Verify that table with supporters contains record about new supporter</b></font>
	 * <li> Click on new supporter row in table
	 * <li> <font color="green"><b>Verify new supporter is Subscribed</b></font>
	 * </ul>
	 *  
	 */
	@Test(groups = {"subscribeExistingSupporter"}, retryAnalyzer = RetryAnalyzer.class)
	public void testSubscribeByExistingSupporter() {
		widgetName = "SubscribeWidgetName_" + RandomStringUtils.randomAlphanumeric(5);
		widgetDescription = "SubscribeWidgetDescription_" + RandomStringUtils.randomAlphanumeric(10);
		supporter = Supporter.generateSupporter();
		doLogin();
		homePage.openAudiencePage().openSupportersPage().verifySupporterExists(supporter.getFinalEMAIL(), false);
		createSupporter();
		supportersAddPage.openAudiencePage().openSupportersPage().verifySupporterExists(supporter.getFinalEMAIL(), true);
		supportersPage.verifySupporterOnTopOfTableFull(supporter);
		createSignupForm();
		doSignupFormBySupporter();
		subscribeWidget.verifySubscriptionIsSuccesses();
		openSupportersPage();
		supportersAddPage = supportersPage.openSupporterDetailsPage();
		supportersAddPage.verifySupporterStatus("Subscribed");
	}
	
	/**
	 * <b>Subscribe Sign-Up form with new supporter</b>
	 * <p>
	 * Steps:
	 * <ul>
	 * <li> Login into existing organization
	 * <li> <font color="green"><b>Verify supporter is not exist in current organization</b></font>
	 * <li> Create sign-up form without verifications (detailed in TestLink: TC16)
	 * <li> Open widget in separate window, delete cookies and refresh page
	 * <li> Fill email, first name, last name, city and zip code in form
	 * <li> Click on Subscribe button
	 * <li> <font color="green"><b>Verify that page contains text about successful signing</b></font>
	 * <li> Close window
	 * <li> <font color="green"><b>Verify that table with supporters contains record about new supporter</b></font>
	 * <li> Click on new supporter row in table
	 * <li> <font color="green"><b>Verify new supporter is Subscribed</b></font>
	 * </ul>
	 *  
	 */
	@Test(groups = {"subscribeNewSupporter"}, retryAnalyzer = RetryAnalyzer.class)
	public void testSubscribeByNewSupporter() {
		widgetName = "SubscribeWidgetName_" + RandomStringUtils.randomAlphanumeric(5);
		widgetDescription = "SubscribeWidgetDescription_" + RandomStringUtils.randomAlphanumeric(10);
		supporter = Supporter.generateSupporter();
		doLogin();
		homePage.openAudiencePage().openSupportersPage().verifySupporterExists(supporter.getFinalEMAIL(), false);
		createSignupForm();
		doSignupFormBySupporter();
		subscribeWidget.verifySubscriptionIsSuccesses();
		openSupportersPage();
		supportersPage.verifySupporterOnTopOfTableFull(supporter);
		supportersAddPage = supportersPage.openSupporterDetailsPage();
		supportersAddPage.verifySupporterStatus("Subscribed");
	}

	private void doLogin() {
		homePage = new LoginPage().doSuccessLogin();
	}
	
	private void createSignupForm() {
		addSignupFormsPage = homePage.openActivitiesPage().
				openSubscribeWidgetsPage().openAddSubscribeWidgetPage();
		addSignupFormsPage.createForm(widgetName, widgetDescription);
	}
	
	private void createSupporter() {
		supportersPage = homePage.openAudiencePage().openSupportersPage();
		supportersAddPage = supportersPage.openAddSupporterPage();
		supportersAddPage.createNewSupporter(supporter);
	}
	
	private void doSignupFormBySupporter() {
		subscribeWidget = addSignupFormsPage.openSubscribeWidget();
		subscribeWidget.fillSubscribeWidget(supporter.getFinalEMAIL(), supporter.getFirstName(), 
				supporter.getLastName(), supporter.getCity(), supporter.getZipCode());
	}
	
	private void openSupportersPage() {
		supportersPage = subscribeWidget.backToSubscribeWidgetPage().openAudiencePage().openSupportersPage();
	}

}
