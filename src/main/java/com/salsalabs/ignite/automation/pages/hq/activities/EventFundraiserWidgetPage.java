package com.salsalabs.ignite.automation.pages.hq.activities;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.Label;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.LabelImpl;
import com.salsalabs.ignite.automation.suites.regression.EventTeamWidgetPage;

public class EventFundraiserWidgetPage extends EventWidget {

	Label donorsList = new LabelImpl(
			"//div[contains(@class, 'sli-donor-list-results')]/descendant::div[.='$amountToReplace.00']/preceding-sibling::div[@class='sli-donor-name']",
			"");
	Button donateOnlyFundraiserButton = new ButtonImpl("//a[contains(text(),'Donate')]", "Donate only on Fundraiser page", true);
	Button submitFundrDonationButton = new ButtonImpl("//a[.='Donate Now']", "Donate Now", true);
	Label fundraiserDonationIsSccessMessage = new LabelImpl("//span[contains(.,'Thank You!')]", "Fundraiser donation is success");
	
	public void verifyNameForLastDonotByDonationAmount(String donorName, String donationAmount, String expectedValue) {
		donorsList.changePath("amountToReplace", donationAmount);
		verifier.verifyEquals(donorsList.getText(), donorName, "Wrong donor name");
		
	}
	
	
	public EventFundraiserWidgetPage openDonationPageOnFundraiserForm() {
		// TODO Auto-generated method stub
		donateOnlyFundraiserButton.click();
		return this;
	}
	
	public EventFundraiserWidgetPage fillFundraiserDonationForm(String personEmail,
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
	
	public EventFundraiserWidgetPage checkDisplayDonationAnonymouslyOption(boolean isChecked) {
		displayDonationAnonymouslyOptionCheckBox.check(isChecked);
		return this;
	}
	
	public EventFundraiserWidgetPage clickSubmitFundraiserDonationForm() {
		submitFundrDonationButton.clickJS();
		return this;
	}
	
	public EventFundraiserWidgetPage verifyTeamDonationIsSuccesses() {
		for (int i = 0; i < 10; i++) {
			if (waitConditionBecomesTrue(fundraiserDonationIsSccessMessage.isDisplayed(), 10)) {
				break;
			}
		}
		verifier.verifyElementIsDisplayed(fundraiserDonationIsSccessMessage);
		return this;
	}

}
