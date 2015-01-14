package pages.HQ;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import objects.Browser;
import objects.Button;
import objects.Label;
import selenium.EmailClient;
import selenium.SeleneseTestCase;

public class PurchasePage extends Browser {

	Label priceLabel = new Label("//p[contains(text(),\"you'll be billed\")]/strong", "Label with price");
	Label processingIconLabel = new Label("//p[contains(text(),\"you'll be billed\")]/strong/span[@class='']", "Processing icon");
	Button paymentFrequencyRadioButton = new Button("//*[contains(text(), 'frequency')]/ancestor::tr/descendant::*[contains(@class, 'custom radio')]", "payment Frequency");
	
	public PurchasePage verifyPriceExist() {
		priceLabel.highlight();
		if (priceLabel.getText().equalsIgnoreCase("$undefined")) {
			try {
				new EmailClient().sendEmail("Wrong price", "Wrong price " + priceLabel.getText(), SeleneseTestCase.makeScreenshot("WrongPrice"));
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		verify(priceLabel.getText().equalsIgnoreCase("$undefined"), false, "Price is not correct. " + priceLabel.getText());
		verify(processingIconLabel.isNotExists(), true, "Processing icon is not hiden");
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
		sleep(5000);
		return this;
	}
}
