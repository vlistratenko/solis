package com.salsalabs.ignite.automation.suites;

import org.testng.annotations.Test;

import com.mailosaur.exception.MailosaurException;
import com.mailosaur.model.Email;
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
	 * <li> Fill specific field type options on step 1
	 * <li> Fill name, description on step 2
	 * <li> Click creation button on step 3
	 * <li> <font color="green"><b>Verify that custom field was created. Is listed on the page</b></font>
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
//		Email e = emailClient.getEmailBySubject("Please confirm your ignite2 account");
		paymentGatewayPage.verifyWePayEmail();
		paymentGatewayPage.verifyCreatedAccountExists(nickname);
		paymentGatewayPage.openWePayConfirmationPage();
	}
	
	private void doLoginAndOpenPaymentGatewayPage() {
		paymentGatewayPage = new LoginPage().doSuccessLogin().openSettingsPage().switchToPaymentGatewaysPage();
	}
}
