package pages.HQ;

import java.util.Arrays;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import objects.Browser;
import objects.Button;
import objects.Label;
import selenium.CommonUtils;
import selenium.EmailClient;
import selenium.SeleneseTestCase;
import tests.BuyLicenseTest;

public class PurchasePage extends Browser {

	Label priceLabel = new Label("//p[contains(text(),\"you'll be billed\")]/strong", "Label with price");
	Label processingIconLabel = new Label("//p[contains(text(),\"you'll be billed\")]/strong/span[@class='']", "Processing icon");
	Button paymentFrequencyRadioButton = new Button("//*[contains(text(), 'frequency')]/ancestor::tr/descendant::*[contains(@class, 'custom radio')]", "payment Frequency");
	
	public PurchasePage verifyPriceExist(Boolean sendEmails) {
		priceLabel.highlight();
		String price = priceLabel.getText();
		String[] prices = {"$160.00", "$314.00", "$1,188.00"};
		if (!Arrays.asList(prices).contains(price) && sendEmails) {
			try {
				new EmailClient().sendEmail("Wrong price", "Wrong price " + price, SeleneseTestCase.makeScreenshot("WrongPrice" + CommonUtils.getUnicName()));
				BuyLicenseTest.sendEmails = false;
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (Arrays.asList(prices).contains(price) && !sendEmails) {
			try {
				new EmailClient().sendEmail("Price is correct ", "Price is correct " + price, SeleneseTestCase.makeScreenshot("PriceIsCorrect" + CommonUtils.getUnicName()));
				BuyLicenseTest.sendEmails = true;
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		verify(price.equalsIgnoreCase("Loading"), false, "Price is not correct. " + price, false);
		verify(processingIconLabel.waitForNotExists(30), true, "Processing icon is not hiden", false);
		return this;
	}
	
	/**
	 * Accepted values Annual, Quarterly, Monthly
	 * @param frequency
	 * @return
	 */
	public PurchasePage selectPaymentFrequency(String frequency) {
		paymentFrequencyRadioButton = new Button("//*[contains(text(), '" + frequency + "')]/ancestor::tr/descendant::*[contains(@class, 'custom radio')]", "payment Frequency");
		paymentFrequencyRadioButton.click();
		sleep(10000);
		return this;
	}
}
