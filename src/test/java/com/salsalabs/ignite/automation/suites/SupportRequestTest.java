package com.salsalabs.ignite.automation.suites;

import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.HomePage;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;

public class SupportRequestTest extends SeleneseTestCase {
	@Test(groups = { "supportRequest" }, retryAnalyzer = RetryAnalyzer.class)
	public void openHelp() {
		new LoginPage()
		.doSuccessLogin()
		.openHelpPage()
		.returnToHomePage();
	}

	@Test(groups = { "supportRequest" }, retryAnalyzer = RetryAnalyzer.class, dependsOnMethods = { "openHelp" })
	public void submitSupportRequest() {
		new HomePage()
		.openSubmitRequestPage()
		.checkSubmitFormIsDisplayed();
	}
}
