package pages.HQ.Manage;

import com.mailosaur.exception.MailosaurException;

import objects.Button;
import objects.DropDown;
import objects.Table;
import pages.HQ.HomePage;
import selenium.EmailClient;

public class PaymentGatewaysPage extends ManagePage {
	
	DropDown addPaymentGateways = new DropDown("//div[@class='buttonDrop-wrapper']", "//div[@class='buttonDrop-wrapper']/button[contains(@ng-click, 'paymentGateways')]", "Add Payment Gateways");
	Table gateWayAccountsTable = new Table("//table[@id='paymentGatewaysDashboard']", "Gateways accounts");
	
	public AddWePayPage openAddWePayPage() {
		addPaymentGateways.selectByLabelJS("WePay");
		return new AddWePayPage();
	}
	
	public PaymentGatewaysPage verifyCreatedAccountExists(String Nickname) {
		verify(gateWayAccountsTable.getCallValue(0, 2), Nickname, "Wrong nickname in the first row", false);
		verify(gateWayAccountsTable.isValueExists(Nickname)>0, true, "Nick name is not found");
		return this;
	}

	public PaymentGatewaysPage verifyWePayEmail() {
		Integer amounOfEmails = 0;
		try {
			amounOfEmails = new EmailClient().getEmailsBySubject("Please confirm your ignite account").size();
		} catch (MailosaurException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		verify(amounOfEmails, 1, "Wrong amount of emails", false);
		return this;
		
	}

	public HomePage openWePayConfirmationPage() {
		String activationLink = "";
		Button wePayAccessButton = new Button("//input[@value='Grant Access']", "Grant Access");
		try {
			activationLink = new EmailClient().getURLByDomain("Please confirm your ignite account", "stage.wepay.com");
		} catch (MailosaurException e) {
			e.printStackTrace();
		}
		open(activationLink);
		verify(wePayAccessButton.isVisible(), true, "Grand Access button is not found", false);
		open();
		return new HomePage();
	}
}
