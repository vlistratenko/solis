package com.salsalabs.ignite.automation.pages.hq.manage;

import com.mailosaur.exception.MailosaurException;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.DropDown;
import com.salsalabs.ignite.automation.elements.Label;
import com.salsalabs.ignite.automation.elements.Table;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.DropDownImpl;
import com.salsalabs.ignite.automation.elements.impl.LabelImpl;
import com.salsalabs.ignite.automation.elements.impl.TableImpl;

public class PaymentGatewaysPage extends ManagePage {
	
	DropDown addPaymentGateways = new DropDownImpl("//div[@class='buttonDrop-wrapper']", "//div[@class='buttonDrop-wrapper']/button[contains(@ng-click, 'paymentGateways')]", "Add Payment Gateways");
	Table gateWayAccountsTable = new TableImpl("//table[@id='paymentGatewaysDashboard']", "Gateways accounts");
	
	public AddWePayPage openAddWePayPage() {
		addPaymentGateways.selectByLabelJS("WePay");
		return new AddWePayPage();
	}
	
	public PaymentGatewaysPage verifyCreatedAccountExists(String nickname) {
		Label label = new LabelImpl("//*[contains(text(), '" + nickname + "')]", "Label");
		verifier.verifyElementIsDisplayed(label);
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

	public PaymentGatewaysPage openWePayConfirmationPage() {
		String activationLink = "";
		Button wePayAccessButton = new ButtonImpl("//input[@value='Grant Access']", "Grant Access");
		try {
			String caption = "Please confirm your ignite account";
			activationLink = SeleneseTestCase.emailClient.getURLByDomain(caption, "stage.wepay.com");
			if (activationLink.isEmpty() && SeleneseTestCase.USED_ENVIRONMENT.getEnvironment().name().equalsIgnoreCase("dev")) {
				caption = "Please confirm your ignite2 account";
			}
		} catch (MailosaurException e) {
			e.printStackTrace();
		}
		String primaryHandle = this.getWindowHandle();
		this.openInNewWindow(activationLink);
		verifier.verifyElementIsVisible(wePayAccessButton);
		this.closeWindow();
		this.switchToWindow(primaryHandle);
		return this;
	}
	
}
