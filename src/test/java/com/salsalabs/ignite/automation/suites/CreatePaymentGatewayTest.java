package com.salsalabs.ignite.automation.suites;

import org.testng.annotations.Test;

import com.mailosaur.exception.MailosaurException;
import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.manage.AddWePayPage;
import com.salsalabs.ignite.automation.pages.hq.manage.PaymentGatewaysPage;

/**
 * <b>This test contains scenarios related to payment gateway creation (TestLink: TC22)</b>
 * @author a.hubachov
 */
public class CreatePaymentGatewayTest extends SeleneseTestCase {
	
	private PaymentGatewaysPage paymentGatewayPage;
	private AddWePayPage addWePayPage;
	
	/**
	 * <b>Create Payment Gateway.</b>
	 * <p>
	 * Steps:
	 * <ul>
	 * <li> Login into existing organization
	 * <li> Open settings page
	 * <li> Switch to payment gateway creation page
	 * <li> Type gateway nickname, description, first name, last name, email and choose type of organization
	 * <li> Click Create My Gateway button
	 * <li> <font color="green"><b>Verify that email is received from WePay</b></font>
	 * <li> <font color="green"><b>Verify that created account is listed on the page</b></font>
	 * <li> Open WePay confirmation page from email
	 * <li> <font color="green"><b>Verify that page contains button Grant Access</b></font>
	 * </ul>
	 * @throws MailosaurException 
	 *  
	 */
	@Test(groups = {"createPaymentGateway"}, retryAnalyzer = RetryAnalyzer.class)
	public void testCreatePaymentGateway() throws MailosaurException {
		String cmEmail = emailClient.getEmailBox("gateway" + CommonUtils.getUnicName());
		String firstName = "FirstName";
		String lastName = "LastName";
		String nickname = "nickname_" + CommonUtils.getUnicName();
		doLoginAndOpenPaymentGatewayPage();
		addWePayPage = paymentGatewayPage.openAddWePayPage();
		CommonUtils.setProperty(PropertyName.ADMIN_EMAIL, cmEmail);
		CommonUtils.setProperty(PropertyName.ADMIN_FIRST_NAME, firstName);
		CommonUtils.setProperty(PropertyName.ADMIN_LAST_NAME, lastName);
		paymentGatewayPage = addWePayPage.createWePayAcount(nickname, "wePayDescr", addWePayPage.chooseRandomOrgType());
		paymentGatewayPage.verifyWePayEmail();
		paymentGatewayPage.verifyCreatedAccountExists(nickname);
		paymentGatewayPage.openWePayConfirmationPage();
	}
	
	private void doLoginAndOpenPaymentGatewayPage() {
		paymentGatewayPage = new LoginPage().doSuccessLogin().openSettingsPage().switchToPaymentGatewaysPage();
	}
}
