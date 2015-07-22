package com.salsalabs.ignite.automation.suites;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.HomePage;

/**
 * <b>This test contains scenarios related to Purchasing Trial Organization (TestLink: TC12)</b>
 *
 */
public class BuyLicenseTest extends SeleneseTestCase {

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
	@Parameters({ "createOrg.domainType", "createOrg.orgName", "createOrg.orgDescrption", "createOrg.firstName", "createOrg.lastName", "createOrg.status", "newuser.password" })
	public void createOrgTest(String domainType, String orgName, String orgDescrption, String firstName, String lastName, String status, String userPassword) {
		CreateNewOrgTest test = new CreateNewOrgTest();
		test.createOrgTest(domainType, orgName, orgDescrption, firstName, lastName, status);
		test.confirmAdminAccountTest(userPassword);
		test.loginAsNewSuperAdminTest();
	}

	/**
	 * <b>Purchase Solis</b>
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
	@Parameters({ "buy.cardNumber","buy.cvv","buy.name" })
	public void buyNowTest(String cardNumber, String cvv, String name) {
		new HomePage().clickBuyButton()
		.selectListSize()
		.chooseBillingType()
		.enterCreditCardInfo(cardNumber, cvv, name)
		.purchase()
		.verifyBuyButtonIsNotDisplayed()
		.verifyEmail("Here’s Your Solis Trial Link. Let’s get started.");
	}

}
