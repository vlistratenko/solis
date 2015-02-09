package pages.Donation;

import objects.Button;
import objects.Label;
import objects.Panel;


public class DonationsDetailsPage extends DonationsPage{
	Button refundLink = new Button(donationsTable.path + "/descendant::span[.='Refund']", "Refund");
	
	Panel mainPanel = new Panel("//div[@class='mainContentWrapper']", "Main panel");
	Label donationAmountLabel = new Label("//h3[contains(text(), 'donation')]/ancestor::div[contains(@class,'donation_card')]/descendant::h1", "Donation amount in top of page");
	Label donationTotalAmountLabel = new Label("//h3[contains(text(), 'total')]/ancestor::div[@class='donation_card']/descendant::h1", "Total donation amount in top of page");
	Label creditCardDetailsLabel = new Label("//h4[contains(text(), 'Credit Card')]/ancestor::div[@class='donation_card']/descendant::p", "Total donation amount in top of page");
	Label sourceLabel = new Label("//h4[contains(text(), 'Source')]/ancestor::div[@class='donation_card']/descendant::a", "Source");
	Label donorLabel = new Label("//h4[contains(text(), 'Donor')]/ancestor::div[@class='donation_card']/descendant::a", "Donor");
	Label statusLabel = new Label("//h6[.='status']/following-sibling::h2/a", "Status");
	Button cancelAllLink = new Button("//a[.='Cancel all future transactions']", "Cancel all future transactions");
	
	public DonationsDetailsPage verifyDonation(String amount, String totalAmount, String creditCrdDetails, String source,
			String firstAndLastNameOfDonor, String addressLine1, String addressLine2, String city, String zip,
			String donationAmountInTable, String dType, String status, Boolean recurringDonation) 
	{		
		verify(donationAmountLabel.getText(), amount, "Wrong Donation amount", false);
		verify(donationTotalAmountLabel.getText(), totalAmount, "Wrong Total donation amount", false);
		verify(creditCardDetailsLabel.getText(), creditCrdDetails, "Credit card details", false);
		verify(sourceLabel.getText(), source, "Wrong Source", false);
		verify(donorLabel.getText(), firstAndLastNameOfDonor, "Wrong Donor name", false);
		verify(mainPanel.isValueExists(addressLine1)>0, true, "Element with address line 1 does not exist", false);
		verify(mainPanel.isValueExists(addressLine2)>0, true, "Element with address line 2 does not exist", false);
		verify(mainPanel.isValueExists(city)>0, true, "Element with city does not exist", false);
		verify(mainPanel.isValueExists(zip)>0, true, "Element with zip does not exist", false);
		verify(donationsTable.getCallValue(1, "Amount"), donationAmountInTable, "Wrong amount", false);
		verify(donationsTable.getCallValue(1, "Type"), dType, "Wrong type", false);
		verify(donationsTable.getCallValue(1, "Status").toLowerCase(), "CHARGE".toLowerCase(), "Wrong", false);
		verify(statusLabel.getText().toLowerCase(), status.toLowerCase(), "Wrong status", false);
		if (recurringDonation) {
			verify(cancelAllLink.isDisplayed(), true, "Cancel All  does not exist");
		}
		return this;
	}
	
	public DonationsDetailsPage refundDonation(Boolean recurringDonation) {
		if (!recurringDonation) {
			refundLink.click();
		}else{			
			cancelAllLink.click();
			refundLink.click();
		}
		sleep(5000);
		return this;
	}

	public void verifyDonationAfterRefund(String status, Boolean recurringDonation) {
		
		if (recurringDonation) {
			verify(donationsTable.getCallValue(2, "Status").toLowerCase(), status.toLowerCase(), "Wrong status", false);
			verify(statusLabel.getText().toLowerCase(), status.toLowerCase(), "Wrong status", false);
			verify(refundLink.isNotDisplayed(), true, "Refund link still exists");
			verify(cancelAllLink.isVisible(), false, "Cancel All  link still exists");
		}else{
			verify(donationsTable.getCallValue(1, "Status").toLowerCase(), status.toLowerCase(), "Wrong status", false);
			verify(statusLabel.getText().toLowerCase(), status.toLowerCase(), "Wrong status", false);
			verify(refundLink.isNotDisplayed(), true, "Refund link still exists");
		}
		
	}
}
