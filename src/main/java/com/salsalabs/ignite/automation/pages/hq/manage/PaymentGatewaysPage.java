package com.salsalabs.ignite.automation.pages.hq.manage;

import com.mailosaur.exception.MailosaurException;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.DropDown;
import com.salsalabs.ignite.automation.elements.Table;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.DropDownImpl;
import com.salsalabs.ignite.automation.elements.impl.TableImpl;
import com.salsalabs.ignite.automation.pages.hq.HomePage;

public class PaymentGatewaysPage extends ManagePage {
	
	DropDown addPaymentGateways = new DropDownImpl("//div[@class='buttonDrop-wrapper']", "//div[@class='buttonDrop-wrapper']/button[contains(@ng-click, 'paymentGateways')]", "Add Payment Gateways");
	Table gateWayAccountsTable = new TableImpl("//table[@id='paymentGatewaysDashboard']", "Gateways accounts");
	
	public AddWePayPage openAddWePayPage() {
		addPaymentGateways.selectByLabelJS("WePay");
		return new AddWePayPage();
	}
	
	public PaymentGatewaysPage verifyCreatedAccountExists(String Nickname) {
		verifier.verifyEquals(gateWayAccountsTable.getCellValue(0, 2), Nickname, "Wrong nickname in the first row", false);
		verifier.verifyEquals(gateWayAccountsTable.isValueExists(Nickname)>0, true, "Nick name is not found");
		return this;
	}

	public PaymentGatewaysPage verifyWePayEmail() {
		Integer amounOfEmails = 0;
		try {
			amounOfEmails = SeleneseTestCase.emailClient.waitForEmails("Please confirm your ignite account", 1, 10).getEmailsBySubject("Please confirm your ignite account").size();
		} catch (MailosaurException e) {
			SeleneseTestCase.logger.error("",e);
		}
		verifier.verifyEquals(amounOfEmails, 1, "Wrong amount of emails", false);
		return this;
		
	}

	public HomePage openWePayConfirmationPage() {
		String activationLink = "";
		Button wePayAccessButton = new ButtonImpl("//input[@value='Grant Access']", "Grant Access");
		try {
			activationLink = SeleneseTestCase.emailClient.getURLByDomain("Please confirm your ignite account", "stage.wepay.com");
		} catch (MailosaurException e) {
			e.printStackTrace();
		}
		open(activationLink);
		verifier.verifyElementIsVisible(wePayAccessButton);
		open();
		return new HomePage();
	}
}
