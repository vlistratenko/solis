package com.salsalabs.ignite.automation.suites;

import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.manage.PaymentGatewaysPage;

/**
 * <b>This test contains scenarios related to payment gateway creation (TestLink: TC22)</b>
 * @author a.hubachov
 */
public class CreatePaymentGatewayTest extends SeleneseTestCase {
	
	private PaymentGatewaysPage paymentGatewayPage;
	
	/**
	 * <b>Create Payment Gateway.</b>
	 * <p>
	 * Steps:
	 * <ul>
	 * <li> Login into existing organization
	 * <li> Open settings page
	 * <li> Switch to payment gateway creation page
	 * <li> Fill specific field type options on step 1
	 * <li> Fill name, description on step 2
	 * <li> Click creation button on step 3
	 * <li> <font color="green"><b>Verify that custom field was created. Is listed on the page</b></font>
	 * </ul>
	 *  
	 */
	@Test(groups = {"createPaymentGateway"}, retryAnalyzer = RetryAnalyzer.class)
	public void testCreatePaymentGateway() {
		doLoginAndOpenPaymentGatewayPage();
		
	}
	
	private void doLoginAndOpenPaymentGatewayPage() {
		paymentGatewayPage = new LoginPage().doSuccessLogin().openSettingsPage().switchToPaymentGatewaysPage();
	}
}
