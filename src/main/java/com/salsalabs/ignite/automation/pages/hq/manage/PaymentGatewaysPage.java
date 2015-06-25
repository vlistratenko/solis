package com.salsalabs.ignite.automation.pages.hq.manage;

import com.mailosaur.exception.MailosaurException;
import com.salsalabs.ignite.automation.common.EmailClient;
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
		return verifyWePayEmail(SeleneseTestCase.emailClient);
	}

	public PaymentGatewaysPage verifyWePayEmail(EmailClient<?> emailClient) {
		Integer amounOfEmails = 0;
		amounOfEmails = emailClient.waitForEmails("Please confirm your ignite account", 1, 10).getEmailsBySubject("Please confirm your ignite account").size();
		verifier.verifyEquals(amounOfEmails, 1, "Wrong amount of emails", false);
		return this;
		
	}
	
	public PaymentGatewaysPage openWePayConfirmationPage() {
		return openWePayConfirmationPage(SeleneseTestCase.emailClient);
	}

	public PaymentGatewaysPage openWePayConfirmationPage(EmailClient<?> emailClient) {
		String activationLink = "";
		Button wePayAccessButton = new ButtonImpl("//input[@value='Grant Access']", "Grant Access");
		String caption = "Please confirm your ignite account";
		activationLink = emailClient.getURLByDomain(caption, "stage.wepay.com");
		if ((activationLink == null || activationLink.isEmpty()) && SeleneseTestCase.USED_ENVIRONMENT.getEnvironment().name().equalsIgnoreCase("dev")) {
			caption = "Please confirm your ignite2 account";
		}
		String primaryHandle = this.getWindowHandle();
		this.openInNewWindow(activationLink);
		verifier.verifyElementIsVisible(wePayAccessButton);
		this.closeWindow();
		this.switchToWindow(primaryHandle);
		return this;
	}
	
}
