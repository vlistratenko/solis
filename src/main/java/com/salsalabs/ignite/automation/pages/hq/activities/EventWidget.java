package com.salsalabs.ignite.automation.pages.hq.activities;


import com.salsalabs.ignite.automation.elements.*;
import com.salsalabs.ignite.automation.elements.impl.*;


public class EventWidget extends DonationWidget {

	Label donorsList = new LabelImpl(
			"//div[contains(@class, 'sli-donor-list-results')]/descendant::div[.='$amountToReplace']/preceding-sibling::div[@class='sli-donor-name' and .='nameToReplace']",
			"");
	TextBox eventPersonFNameField = new TextBoxImpl("//input[contains(@id,'first_name')]", "Event attendees First name", true);
	TextBox eventPersonLNameField = new TextBoxImpl("//input[contains(@id,'last_name')]", "Event attendees Last name", true);
	TextBox eventPersonEmailField = new TextBoxImpl("//input[contains(@id,'email')]", "Event attendees Email", true);
	Label eventSubsrIsSccessMessage = new LabelImpl("//*[contains(.,'Thank You!')]", "Event is subscribed");
	Button donateOnlyButton = new ButtonImpl("//a[contains(text(),'Like to Donate')]", "Donate only", true);
	Button registrationButton = new ButtonImpl("//*[contains(text(), 'Register')]", "Register", true);
	Button nextButton = new ButtonImpl("//a[contains(text(), 'Next')]", "Next", true);
	Button checkoutButton = new ButtonImpl("//a[contains(text(), 'Checkout')]", "Checkout", true);
	Button submitButton = new ButtonImpl("//button[@type='submit']", "Submit", true);
	Button submitButtonNew = new ButtonImpl("//a[.='Submit']", "Submit", true);
	protected CheckBox displayDonationAnonymouslyOptionCheckBox = new CheckBoxImpl("//input[@name='anonymousDonation']", "Display my donation anonymously checkbox");
	Table itemsTable = new TableImpl(".//*[@ignite-ticket-summary='ignite-ticket-summary']", "Checkout table");

	public EventWidget() {
		super();
	}

	public EventWidget(boolean clean) {
		super(clean);
	}

	public DonationWidget verifyEventSubscrIsSuccesses() throws Exception {
		eventSubsrIsSccessMessage.waitElementWithFail(10);
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
		registrationButton.clickJS();
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
		nextButton.click();
		switchDefaultContent();
		switchToFrame("//iframe[contains(@id, '_ticketFrame')]");
		checkoutButton.click();
		switchDefaultContent();
		checkoutSummaryTable.fluentWaitForElementPresenceIgnoringExceptions();
		return this;
	}
	
	/*
	 * Method for event donation forms
	 * Requiring is false
	 */
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
		fillDonationForm(personEmail,
				personFName,
				personLName,
				personAddressLine1,
				"",
				personCity,
				personZip,
				personState,
				false,
				donationAmount,
				nameOnCard,
				cardNumber,
				cvv,
				expiryMonth,
				expiryYear,
				isFundraising,
				isNewsletter,
				isEmail);
		isEvent = false;
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
											 String expiryYear)
	{
		isEvent = true;
		fillDonationForm(personEmail,
				personFName,
				personLName,
				personAddressLine1,
				"",
				personCity,
				personZip,
				personState,
				false,
				donationAmount,
				nameOnCard,
				cardNumber,
				cvv,
				expiryMonth,
				expiryYear,
				false	,
				false,
				false);
		isEvent = false;
		return this;
	}
	
	public EventWidget clickDonationButton() {
		submitButton.clickJS();
		return this;
	}
	
	/*
	 * This method is for new forms
	 */
	public EventWidget clickSubmitButton() {
		submitButtonNew.clickJS();
		eventSubsrIsSccessMessage.fluentWaitForElementPresenceIgnoringExceptions();
		sleep(20); //wait until supporter reaches HQ after submission
		return this;
	}
	
	public EventWidget checkDisplayDonationAnonymouslyOption(boolean isChecked) {
		displayDonationAnonymouslyOptionCheckBox.check(isChecked);
		return this;
	}
	
	public void verifyNameForLastDonotByDonationAmount(String donorName, String donationAmount) {
		donorsList.changePath("amountToReplace", donationAmount);
		donorsList.changePath("nameToReplace", donorName);
		if (!donorsList.isExists()) {
			donorsList.waitElementIsExistWithPageRefresh(5);
		}
		verifier.verifyTrue(donorsList.isExists(),
				"Element with donor name " + donorName + " was not found. Element path " +  donorsList.getPath());
	}


}
