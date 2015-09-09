package com.salsalabs.ignite.automation.suites;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.HomePage;
import com.salsalabs.ignite.automation.pages.hq.PurchasePage;

/**
 * <b>This test contains scenarios related to Purchasing Trial Organization (TestLink: TC12)</b>
 *
 */
public class BuyLicenseTest extends SeleneseTestCase {
	
	private static final String SUBJECT_NEW_INVOICE = "You have a new Solis invoice from Salsa Labs";
	private static final String SUBJECT_PAYMENT_PROCESSED = "Your payment for Solis was successfully processed.";

	/**
	 * <b>Create Trial ORG
	 * <p>
	 * Steps:
	 * <ul>
	 * <li> Create Trial ORG via admin tool
	 * </ul>
	 *  
	 */	
	@Test(retryAnalyzer = RetryAnalyzer.class, priority = 10, enabled = true, groups = { "buy" })
	@Parameters({ "createOrg.orgName", "createOrg.orgDescrption", "createOrg.firstName", "createOrg.lastName", "createOrg.status", "createOrg.product", "newuser.password" })
	public void createOrgTest(String orgName, String orgDescrption, String firstName, String lastName, String status, String product, String userPassword) {
		CreateNewOrgTest test = new CreateNewOrgTest();
		test.createOrgTest(orgName, orgDescrption, firstName, lastName, status, product);
		test.confirmAdminAccountTest(userPassword);
		test.loginAsNewSuperAdminTest();
	}

	/**
	 * <b>Purchase Solis/FE</b>
	 * <p>
	 * Steps:
	 * <ul>
	 * <li> Login into trial org
	 * <li> Click on Buy now
	 * <li> Select random supporter list size
	 * <li> Select random monthly billing cycle
	 * <li> Enter credit card holder name and number
	 * <li> Enter expiration date in month and year
	 * <li> Enter the security code of 3 digits
	 * <li> Click on submit button
	 * <li> Observe the state of the account
	 * <li> Observer the notification
	 * <li> Verify purchase confirmation email
	 * </ul>
	 *  
	 */	
	@Test(retryAnalyzer = RetryAnalyzer.class, priority = 20, enabled = true, groups = { "buy" }, dependsOnMethods = "createOrgTest")
	@Parameters({ "createOrg.product", "buy.cardNumber","buy.cvv","buy.name" })
	public void buyNowTest(String product, String cardNumber, String cvv, String name) {
		mailosaur.deleteAllEmails();
		PurchasePage page = new HomePage().clickBuyButton();
		if (!product.equalsIgnoreCase("Fundraising")) {
			page.selectListSize().chooseBillingType();
		}
		page.enterCreditCardInfo(cardNumber, cvv, name).
		purchase().
		verifyBuyButtonIsNotDisplayed().
		verifyEmail(mailosaur, SUBJECT_NEW_INVOICE).
		verifyEmail(mailosaur, SUBJECT_PAYMENT_PROCESSED);
	}
	
	/**
	 * <b>Upgrade FE</b>
	 * <p>
	 * Steps:
	 * <ul>
	 * <li> Login into FE org
	 * <li> Click on Upgrade
	 * <li> Select random supporter list size
	 * <li> Select random monthly billing cycle
	 * <li> Click on submit button
	 * <li> Observe the state of the account
	 * <li> Observe the notification
	 * <li> Verify purchase confirmation email
	 * </ul>
	 *  
	 */	
	@Test(retryAnalyzer = RetryAnalyzer.class, priority = 30, enabled = true, groups = { "upgrade" }, dependsOnMethods = "buyNowTest")
	public void upgradeFETest() {
		mailosaur.deleteAllEmails();
		new HomePage().
		logOut().
		doSuccessLogin().
		clickBuyButton().
		selectListSize().
		chooseBillingType().
		purchase().
		verifyBuyButtonIsNotDisplayed().
		verifyEmail(mailosaur, SUBJECT_NEW_INVOICE).
		verifyEmail(mailosaur, SUBJECT_PAYMENT_PROCESSED);
	}

}
