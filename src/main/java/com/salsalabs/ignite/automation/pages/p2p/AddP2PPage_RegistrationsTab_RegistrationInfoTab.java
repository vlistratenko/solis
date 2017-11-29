package com.salsalabs.ignite.automation.pages.p2p;

import com.salsalabs.ignite.automation.elements.BooleanRadiobutton;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.BooleanRadiobuttonImpl;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;

public class AddP2PPage_RegistrationsTab_RegistrationInfoTab extends AddP2PPage_RegistrationsTab {

	Button breadCrumbsLink = new ButtonImpl("//li[contains(@class, 'active')]/a[contains(.,'Registration Info')]", "Registration Info tab");
	TextBox registrationNameField = new TextBoxImpl("//input[@id='ticket_name']", "Registration Name field");
	BooleanRadiobutton isFundraiserRadioButton = new BooleanRadiobuttonImpl("//input[@id='require_fundraiser_yes']", "//input[@id='require_fundraiser_no']", "This registration level includes a personal fundraising page radiobuttons"); 
	TextBox ticketPriceField = new TextBoxImpl("//input[@id='ticket_price']", "Ticket price field");
	TextBox ticketDeductibleAmountField = new TextBoxImpl("//input[@id='ticket_deductible_amount']", "Ticket deductible amount field");
	Button saveTicketButton = new ButtonImpl("//input[@value='Save']", "Save ticket button");
	
	public AddP2PPage_RegistrationsTab_InformationCollectedTab fillRegistrationStepAndGoNext(
			String registrationName,
			boolean isFundraiser,
			String ticketPrice,
			String ticketDeductible) {
		gzFrame.waitElement();
		switchToFrame(gzFrame.getPath());
		registrationNameField.waitElement();
		registrationNameField.type(registrationName);
		isFundraiserRadioButton.select(isFundraiser);
		ticketPriceField.type(ticketPrice);
		ticketDeductibleAmountField.type(ticketDeductible);
		clickOnSaveButton();
		switchDefaultContent();
		return new AddP2PPage_RegistrationsTab_InformationCollectedTab();
	}
	
	public  AddP2PPage_RegistrationsTab_InformationCollectedTab clickOnSaveButton() {
		saveTicketButton.click();
		return new AddP2PPage_RegistrationsTab_InformationCollectedTab();
	}
}
