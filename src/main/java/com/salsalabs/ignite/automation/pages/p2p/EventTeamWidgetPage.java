package com.salsalabs.ignite.automation.pages.p2p;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.Label;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.LabelImpl;
import com.salsalabs.ignite.automation.pages.hq.activities.EventWidget;

public class EventTeamWidgetPage extends EventWidget {

	Button donateOnlyTeamButton = new ButtonImpl("//a[contains(text(),'Donate')]", "Donate only on Team page", true);
	Label donorsList = new LabelImpl(
			"//div[contains(@class, 'sli-donor-list-results')]/descendant::div[.='$amountToReplace.00']/preceding-sibling::div[@class='sli-donor-name']",
			"");
	Button submitTeamDonationButton = new ButtonImpl("//a[.='Donate Now']", "Donate Now", true);
	Label teamDonationIsSccessMessage = new LabelImpl("//span[contains(.,'Thank You!')]", "Team donation is success");
	
	public void verifyNameForLastDonotByDonationAmount(String donorName, String donationAmount, String expectedValue) {
		super.verifyNameForLastDonotByDonationAmount(donorName, donationAmount);
		
	}
	
	public EventTeamWidgetPage openDonationPageOnFundraiserForm() {
		// TODO Auto-generated method stub
		donateOnlyTeamButton.click();
		return this;
	}
	
	public EventTeamWidgetPage fillFundraiserDonationForm(String personEmail,
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

	
	public EventTeamWidgetPage clickSubmitTeamDonationForm() {
		submitTeamDonationButton.clickJS();
		return this;
	}
	
	public EventTeamWidgetPage verifyTeamDonationIsSuccesses() {
		for (int i = 0; i < 10; i++) {
			if (waitConditionBecomesTrue(teamDonationIsSccessMessage.isDisplayed(), 10)) {
				break;
			}
		}
		verifier.verifyElementIsDisplayed(teamDonationIsSccessMessage);
		return this;
	}
	
	public EventTeamWidgetPage checkDisplayDonationAnonymouslyOption(boolean isChecked) {
		super.checkDisplayDonationAnonymouslyOption(isChecked);
		return this;
	}
	
	public void verifyNameForLastDonorByDonationAmount(String donorName, String donationAmount, String expectedValue) {
		super.verifyNameForLastDonotByDonationAmount(donorName, donationAmount);
	}
}
