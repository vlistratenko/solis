package com.salsalabs.ignite.automation.pages.hq.activities;


import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.Label;
import com.salsalabs.ignite.automation.elements.SelectBox;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.LabelImpl;
import com.salsalabs.ignite.automation.elements.impl.SelectBoxImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;

public class EventWidget extends DonationWidget {
	TextBox eventPersonFNameField = new TextBoxImpl("//input[contains(@id,'first_name')]", "Event attendees First name", true);
	TextBox eventPersonLNameField = new TextBoxImpl("//input[contains(@id,'last_name')]", "Event attendees Last name", true);
	TextBox eventPersonEmailField = new TextBoxImpl("//input[contains(@id,'email')]", "Event attendees Email", true);
	Label eventSubsrIsSccessMessage = new LabelImpl("//h3[.='Thank You!']", "Event is subscribed");
	Button donateOnlyButton = new ButtonImpl("//a[contains(text(),'Like to Donate')]", "Donate only", true);
	Button registrationButton = new ButtonImpl("//a[.='Register']", "Register", true);
	Button nextButton = new ButtonImpl("//a[.='Next']", "Next", true);
	SelectBox ticketsQtySelectBox = new SelectBoxImpl("//select[@name='ticket_qty']", "Tickets qty");
	Button checkoutButton = new ButtonImpl("//button[contains(text(), 'Checkout')]", "Checkout", true);
	Button submitButton = new ButtonImpl("//button[@type='submit']", "Submit", true);
	
	public EventWidget() {
		super();
	}
	
	public EventWidget(boolean clean) {
		super(clean);
	}

	public DonationWidget verifyEventSubscrIsSuccesses() {
		for (int i = 0; i < 10; i++) {
			if (waitConditionBecomesTrue(eventSubsrIsSccessMessage.isDisplayed(), 10)) {
				break;
			}
		}
		verifier.verifyElementIsDisplayed(eventSubsrIsSccessMessage);
		return this;
	}
	
	public EventWidget openDonationPage() {
		// TODO Auto-generated method stub
		donateOnlyButton.click();
		return this;
	}
	
	public EventWidget openEventRegistrationPage() {
		// TODO Auto-generated method stub
		registrationButton.click();
		return this;
	}
	
	public EventWidget fillEventRegistrationForm(String personEmail, String personFName, String personLName){
		switchToFrame("//iframe[contains(@id, '_ticketFrame')]");
		ticketsQtySelectBox.selectByLabel("1");
		nextButton.click();
		sleep(3);
		eventPersonEmailField.type(personEmail);
		eventPersonFNameField.type(personFName);
		eventPersonLNameField.type(personLName);
		checkoutButton.click();
		sleep(3);
		switchDefaultContent();
		return this;
	}
	
	public EventWidget fillEventDonationForm(String personEmail,
			String personFName,
			String personLName,
			String personAddressLine1,
			String personCity,
			String personZip,
			String personState,
			String donationAmount,
			String nameOnCard,
			String cardNumber,
			String cvv,
			String expiryMonth,
			String expiryYear,			
			boolean isFundraising,
			boolean isNewsletter,
			boolean isEmail) 
	{
		isEvent = true;
		fillDonationForm(personEmail, personFName, personLName, personAddressLine1, "", personCity, personZip, personState, false, donationAmount, nameOnCard, cardNumber, cvv, expiryMonth, expiryYear, isFundraising, isNewsletter, isEmail);
		isEvent = false;
		return this;
	}
	
	public EventWidget clickDonationButton() {
		submitButton.click();
		return this;
	}
}
