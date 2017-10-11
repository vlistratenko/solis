package com.salsalabs.ignite.automation.suites;

import com.mailosaur.exception.MailosaurException;
import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.manage.AddCardConnectPage;
import com.salsalabs.ignite.automation.pages.hq.manage.AddWePayPage;
import com.salsalabs.ignite.automation.pages.hq.manage.PaymentGatewaysPage;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * <b>This test contains scenarios related to payment gateway creation (TestLink: TC22)</b>
 * @author a.hubachov
 */
public class CreatePaymentGatewayTest extends SeleneseTestCase {
	
	private PaymentGatewaysPage paymentGatewayPage;
	private AddWePayPage addWePayPage;
	private AddCardConnectPage addCardConnectPage;

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
	@Test(enabled = true, groups = {"createPaymentGateway"}, retryAnalyzer = RetryAnalyzer.class)
	@Parameters({ "login", "password" })
	public void testCreateWePayPaymentGateway(String login, String password) throws MailosaurException {
		mailosaur.deleteAllEmails();
		String cmEmail = mailosaur.getEmailBox("gateway" + CommonUtils.getUnicName());
		String firstName = "FirstName";
		String lastName = "LastName";
		String nickname = "nickname_" + CommonUtils.getUnicName();
		doLoginAndOpenPaymentGatewayPage(login, password);
		addWePayPage = paymentGatewayPage.openAddWePayPage();
		CommonUtils.setProperty(PropertyName.ADMIN_EMAIL, cmEmail);
		CommonUtils.setProperty(PropertyName.ADMIN_FIRST_NAME, firstName);
		CommonUtils.setProperty(PropertyName.ADMIN_LAST_NAME, lastName);
		paymentGatewayPage = addWePayPage.createWePayAcount(nickname, "wePayDescr", addWePayPage.chooseRandomOrgType());
		paymentGatewayPage.verifyWePayEmail(mailosaur);
		paymentGatewayPage.verifyCreatedAccountExists(nickname);
		paymentGatewayPage.openWePayConfirmationPage(mailosaur);
	}

	@Test(enabled = true, groups = {"createPaymentGateway"}, retryAnalyzer = RetryAnalyzer.class)
	@Parameters({ "login", "password" })
	public void testCreateCardConnectPaymentGateway(String login, String password) throws MailosaurException {
		String nickname = "nickname_" + CommonUtils.getUnicName();
		doLoginAndOpenPaymentGatewayPage(login, password);
		addCardConnectPage = paymentGatewayPage.openAddCardConnectPage();
		paymentGatewayPage = addCardConnectPage.createCardConnectAcount(nickname, "descr", addCardConnectPage.chooseRandomCurrency());
		paymentGatewayPage.verifyCreatedAccountExists(nickname);
	}
	
	private void doLoginAndOpenPaymentGatewayPage(String login, String password) {
		paymentGatewayPage = new LoginPage().doSuccessLogin(login, password).openSettingsPage().switchToPaymentGatewaysPage();
	}
}
