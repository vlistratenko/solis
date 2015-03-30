package com.salsalabs.ignite.automation.suites;

import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.HomePage;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;

/**
 * <b>This test contains scenarios related to navigating to help and submitting support request (TestLink: TC10/TC11)</b>
 *
 */
public class SupportRequestTest extends SeleneseTestCase {
	
	/**
	 * <b>Navigate to Help</b>
	 * <p/>
	 * Steps:
	 * <ul>
	 * <li> Login into existing organization </li>
	 * <li> Hit the question mark icon in the top right corner </li>
	 * <li> Hit the "Help" </li>
	 * <li> <font color="green"><b>Verify that API returns the zendesk URL and user is redirected to Salsa Labs sandbox in a separate browser window </b></font></li>
	 * <li> Close the window, return to the home page </li>
	 * </ul>
	 *  
	 */
	@Test(groups = { "supportRequest" }, retryAnalyzer = RetryAnalyzer.class)
	public void openHelp() {
		new LoginPage()
		.doSuccessLogin()
		.openHelpPage()
		.returnToHomePage();
	}

	/**
	 * <b>Submit support request</b>
	 * <p/>
	 * Steps:
	 * <ul>
	 * <li> Hit the question mark icon in the top right corner </li>
	 * <li> Hit the "Submit request" </li>
	 * <li> <font color="green"><b>Verify that user is redirected to zendesk support and 'Submit request' form is displayed to the user </b></font></li>
	 * </ul>
	 *  
	 */
	@Test(groups = { "supportRequest" }, retryAnalyzer = RetryAnalyzer.class, dependsOnMethods = { "openHelp" })
	public void submitSupportRequest() {
		new HomePage()
		.openSubmitRequestPage()
		.checkSubmitFormIsDisplayed();
	}
}
