package com.salsalabs.ignite.automation.suites;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.activities.ActivitiesPage;
import com.salsalabs.ignite.automation.pages.hq.activities.AddSubscribeWidgetPage;

public class CreateSignupFormTest extends SeleneseTestCase {
	private AddSubscribeWidgetPage addSignupFormsPage;
	private ActivitiesPage activitiesPage;
	
	@Test(groups = {"createAndPublishSignupForm"}, retryAnalyzer = RetryAnalyzer.class)
	public void testCreateAndPublishSignupForm() {
		doLoginAndOpenSignupFormsPage();
		String widgetName = "SubscribeWidgetName_" + RandomStringUtils.randomAlphanumeric(5);
		String widgetDescription = "SubscribeWidgetDescription_" + RandomStringUtils.randomAlphanumeric(10);
		String layoutName = "Hero";
		String expectedLink = "https://automatedtesting1.test.igniteaction.net/" + widgetName.toLowerCase();
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
		// open widget lik in new tab
		addSignupFormsPage.openWidgetInNewWindow(expectedLink);
		// open Activities menu item in left sidebar and check that signup form is present in table
//		activitiesPage = addSignupFormsPage.openActivitiesPage().openAllActivitiesTab();
		// open Sign-Up Forms tab and check that our form is present in table too
		
		System.out.println("111");
	}
	
	private void doLoginAndOpenSignupFormsPage() {
		addSignupFormsPage = new LoginPage().doSuccessLogin().openActivitiesPage().
				openSubscribeWidgetsPage().openAddSubscribeWidgetPage();
	}

}
