package com.salsalabs.ignite.automation.pages.hq;

import java.util.Arrays;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.salsalabs.ignite.automation.common.Browser;
import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.EmailClient;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.Label;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.LabelImpl;
//import com.salsalabs.ignite.automation.tests.BuyLicenseTest;

public class PurchasePage extends Browser {

	Label priceLabel = new LabelImpl("//p[contains(text(),\"you'll be billed\")]/strong", "Label with price");
	Label processingIconLabel = new LabelImpl("//p[contains(text(),\"you'll be billed\")]/strong/span[@class='']", "Processing icon");
	Button paymentFrequencyRadioButton = new ButtonImpl("//*[contains(text(), 'frequency')]/ancestor::tr/descendant::*[contains(@class, 'custom radio')]", "payment Frequency");
	
	public PurchasePage verifyPriceExist(boolean sendEmails) {
		priceLabel.highlight();
		String price = priceLabel.getText();
		String[] prices = {"$160", "$314", "$1188","$160.00", "$314.00", "$1,188.00"};
		if (!Arrays.asList(prices).contains(price) && sendEmails) {
			try {
				SeleneseTestCase.emailClient.sendEmail("Wrong price", "Wrong price " + price, SeleneseTestCase.makeScreenshot("WrongPrice" + CommonUtils.getUnicName()));
				//BuyLicenseTest.sendEmails = false;
			} catch (MessagingException e) {
				SeleneseTestCase.logger.error("",e);
			}
		}else if (Arrays.asList(prices).contains(price) && !sendEmails) {
			try {
				SeleneseTestCase.emailClient.sendEmail("Price is correct ", "Price is correct " + price, SeleneseTestCase.makeScreenshot("PriceIsCorrect" + CommonUtils.getUnicName()));
				//BuyLicenseTest.sendEmails = true;
			} catch (AddressException e) {
				SeleneseTestCase.logger.error("",e);
			} catch (MessagingException e) {
				SeleneseTestCase.logger.error("",e);
			}
			
		}
		verifier.verifyFalse(price.equalsIgnoreCase("Loading"), "Price is not correct. " + price);
		verifier.verifyTrue(processingIconLabel.waitForNotExists(30), "Processing icon is not hiden");
		return this;
	}
	
	/**
	 * Accepted values Annual, Quarterly, Monthly
	 * @param frequency
	 * @return
	 */
	public PurchasePage selectPaymentFrequency(String frequency) {
		paymentFrequencyRadioButton = new ButtonImpl("//*[contains(text(), '" + frequency + "')]/ancestor::tr/descendant::*[contains(@class, 'custom radio')]", "payment Frequency");
		paymentFrequencyRadioButton.click();
		sleep(10);
		return this;
	}
}
