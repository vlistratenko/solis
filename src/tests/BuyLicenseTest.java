package tests;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.hq.LoginPage;
import pages.hq.PurchasePage;
import core.util.SeleneseTestCase;

public class BuyLicenseTest extends SeleneseTestCase {
	public static boolean sendEmails = true;
	
	@Parameters({ "trial.login", "trial.password", "paymentFrequency"})
	@Test(priority=10, groups = {"BuyLicenseTest"}, description = "")
	public void BuyLicense(String login, String password, String paymentFrequency) throws AddressException, MessagingException{
		
		for (int i = 0; i < 1000000000; i++) {
			PurchasePage page = new LoginPage(true).
					doSuccessLogin(login, password).
					clickBuyButton().
					verifyPriceExist(sendEmails);
			for (String freq : paymentFrequency.split(":")) {
				page.
				selectPaymentFrequency(freq).
				verifyPriceExist(sendEmails);	
			}
		}		
			
	}
}
