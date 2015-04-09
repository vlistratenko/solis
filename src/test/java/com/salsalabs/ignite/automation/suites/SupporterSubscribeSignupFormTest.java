package com.salsalabs.ignite.automation.suites;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.common.Supporter;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.activities.AddSubscribeWidgetPage;
import com.salsalabs.ignite.automation.pages.hq.activities.SubscribeWidget;
import com.salsalabs.ignite.automation.pages.hq.supporters.SupportersPage;

/**
 * <b>This test contains scenarios related to subscriptions of new and existing supporter on sign-up form (TestLink: TC17)</b>
 * @author a.hubachov
 *
 */
public class SupporterSubscribeSignupFormTest extends SeleneseTestCase {
	private AddSubscribeWidgetPage addSignupFormsPage;
	private SubscribeWidget subscribeWidget;
	private SupportersPage supportersPage;

//	@Test(groups = {"subscribeExistingSupporter"}, retryAnalyzer = RetryAnalyzer.class)
//	public void testSubscribeByExistingSupporter() {
//	}
	
	@Test(groups = {"subscribeNewSupporter"}, retryAnalyzer = RetryAnalyzer.class)
	public void testSubscribeByNewSupporter() {
		String widgetName = "SubscribeWidgetName_" + RandomStringUtils.randomAlphanumeric(5);
		String widgetDescription = "SubscribeWidgetDescription_" + RandomStringUtils.randomAlphanumeric(10);
		Supporter supporter = Supporter.generateSupporter();
		doLoginAndCreateSignupForm(widgetName, widgetDescription);
		subscribeWidget = addSignupFormsPage.openSubscribeWidget();
		subscribeWidget.fillSubscribeWidget(supporter.getFinalEMAIL(), supporter.getFirstName(), 
				supporter.getLastName(), supporter.getCity(), supporter.getZipCode());
		subscribeWidget.verifySubscriptionIsSuccesses();
		supportersPage = subscribeWidget.backToSubscribeWidgetPage().openAudiencePage().openSupportersPage();
		supportersPage.verifySupporterOnTopOfTableFull(supporter);
		supportersPage.openSupporterDetailsPage();
		System.out.println("+++++++++++++++++");
	}
	
	private void doLoginAndCreateSignupForm(String widgetName, String widgetDescription) {
		addSignupFormsPage = new LoginPage().doSuccessLogin().openActivitiesPage().
				openSubscribeWidgetsPage().openAddSubscribeWidgetPage();
		addSignupFormsPage.createSignupForm(widgetName, widgetDescription);
	}
}
