package com.salsalabs.ignite.automation.pages.donation;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.Label;
import com.salsalabs.ignite.automation.elements.Panel;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.LabelImpl;
import com.salsalabs.ignite.automation.elements.impl.PanelImpl;

public class DonationsDetailsPage extends DonationsPage {
	Button refundLink = new ButtonImpl(donationsTable.getPath() + "/descendant::span[.='Refund']", "Refund");

	Panel mainPanel = new PanelImpl("//div[@class='mainContentWrapper']", "Main panel");
	Label donationAmountLabel = new LabelImpl("//h3[contains(text(), 'donation')]/ancestor::div[contains(@class,'donation_card')]/descendant::h1", "Donation amount in top of page");
	Label donationTotalAmountLabel = new LabelImpl("//h3[contains(text(), 'total')]/ancestor::div[@class='donation_card']/descendant::h1", "Total donation amount in top of page");
	Label creditCardDetailsLabel = new LabelImpl("//h4[contains(text(), 'Credit Card')]/ancestor::div[@class='donation_card']/descendant::p", "Total donation amount in top of page");
	Label sourceLabel = new LabelImpl("//h4[contains(text(), 'Source')]/ancestor::div[@class='donation_card']/descendant::a", "Source");
	Label donorLabel = new LabelImpl("//h4[contains(text(), 'Donor')]/ancestor::div[@class='donation_card']/descendant::a", "Donor");
	Label statusLabel = new LabelImpl("//h6[.='status']/following-sibling::h2/a", "Status");
	Button cancelAllLink = new ButtonImpl("//a[.='Cancel all future transactions']", "Cancel all future transactions");

	public DonationsDetailsPage verifyDonation(String amount, String totalAmount, String creditCrdDetails, String source, String firstAndLastNameOfDonor, String addressLine1, String addressLine2, String city, String zip,
			String donationAmountInTable, String dType, String status, boolean recurringDonation) {
		verifier.verifyEquals(donationAmountLabel.getText(), amount, "Wrong Donation amount");
		verifier.verifyEquals(donationTotalAmountLabel.getText(), totalAmount, "Wrong Total donation amount");
		verifier.verifyEquals(creditCardDetailsLabel.getText(), creditCrdDetails, "Credit card details");
		verifier.verifyEquals(sourceLabel.getText(), source, "Wrong Source");
		verifier.verifyEquals(donorLabel.getText(), firstAndLastNameOfDonor, "Wrong Donor name");
		verifier.verifyTrue(mainPanel.isValueExists(addressLine1) > 0, "Element with address line 1 does not exist");
		verifier.verifyTrue(mainPanel.isValueExists(addressLine2) > 0, "Element with address line 2 does not exist");
		verifier.verifyTrue(mainPanel.isValueExists(city) > 0, "Element with city does not exist");
		verifier.verifyTrue(mainPanel.isValueExists(zip) > 0, "Element with zip does not exist");
		verifier.verifyEquals(donationsTable.getCellValue(1, "Amount"), donationAmountInTable, "Wrong amount");
		verifier.verifyEquals(donationsTable.getCellValue(1, "Type"), dType, "Wrong type");
		verifier.verifyEquals(donationsTable.getCellValue(1, "Status").toLowerCase(), "CHARGE".toLowerCase(), "Wrong");
		verifier.verifyEquals(statusLabel.getText().toLowerCase(), status.toLowerCase(), "Wrong status");
		if (recurringDonation) {
			verifier.verifyElementIsDisplayed(cancelAllLink);
		}
		return this;
	}
	
	public DonationsDetailsPage verifyDonationAmmountAndFee(String amount, String fee) {	
		sleep(2);
		donationsTable.scrollIntoView();
		verifier.verifyEquals(donationsTable.getCellValue(1, "Amount"), amount, "Wrong amount", false);
		verifier.verifyEquals(donationsTable.getCellValue(1, "Fees Paid By Supporter"), fee, "Wrong amount", false);
		return this;
	}
	

	public DonationsDetailsPage refundDonation(boolean recurringDonation) {
		if (!recurringDonation) {
			refundLink.click();
		} else {
			cancelAllLink.click();
			refundLink.click();
		}
		sleep(5);
		return this;
	}

	public void verifyDonationAfterRefund(String status, boolean recurringDonation) {

		if (recurringDonation) {
			verifier.verifyEquals(donationsTable.getCellValue(2, "Status").toLowerCase(), status.toLowerCase(), "Wrong status", false);
			verifier.verifyEquals(statusLabel.getText().toLowerCase(), status.toLowerCase(), "Wrong status", false);
			verifier.verifyElementIsNotDisplayed(refundLink);
			verifier.verifyElementIsNotVisible(cancelAllLink);
		} else {
			verifier.verifyEquals(donationsTable.getCellValue(1, "Status").toLowerCase(), status.toLowerCase(), "Wrong status", false);
			verifier.verifyEquals(statusLabel.getText().toLowerCase(), status.toLowerCase(), "Wrong status", false);
			verifier.verifyElementIsNotDisplayed(refundLink);
		}

	}
}
