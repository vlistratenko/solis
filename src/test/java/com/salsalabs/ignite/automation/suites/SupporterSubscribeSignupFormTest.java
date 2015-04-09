package com.salsalabs.ignite.automation.suites;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.common.Supporter;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.activities.AddSubscribeWidgetPage;
import com.salsalabs.ignite.automation.pages.hq.activities.SubscribeWidget;

public class SupporterSubscribeSignupFormTest extends SeleneseTestCase {
	private AddSubscribeWidgetPage addSignupFormsPage;
	private SubscribeWidget subscribeWidget;

//	@Test(groups = {"subscribeExistingSupporter"}, retryAnalyzer = RetryAnalyzer.class)
//	public void testSubscribeByExistingSupporter() {
//		String widgetName = "SubscribeWidgetName_" + RandomStringUtils.randomAlphanumeric(5);
//		String widgetDescription = "SubscribeWidgetDescription_" + RandomStringUtils.randomAlphanumeric(10);
//		doLoginAndCreateSignupForm(widgetName, widgetDescription);
//		
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
		
	}
	
	private void doLoginAndCreateSignupForm(String widgetName, String widgetDescription) {
		addSignupFormsPage = new LoginPage().doSuccessLogin().openActivitiesPage().
				openSubscribeWidgetsPage().openAddSubscribeWidgetPage();
		addSignupFormsPage.createSignupForm(widgetName, widgetDescription);
	}
}
