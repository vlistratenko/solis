package com.salsalabs.ignite.automation.pages.hq.activities;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.CheckBox;
import com.salsalabs.ignite.automation.elements.Label;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.CheckBoxImpl;
import com.salsalabs.ignite.automation.elements.impl.LabelImpl;
import com.salsalabs.ignite.automation.elements.impl.SelectBoxImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;

public class DonationWidget extends SubscribeWidget {
	TextBox personAddressLine1Field = new TextBoxImpl("//input[@name='Address@Home@Line1']", "Address line 1", true);
	TextBox personAddressLine2Field = new TextBoxImpl("//input[@name='Address@Home@Line2']", "Address line 2", true);
	CheckBox recurringDonationCheckBox = new CheckBoxImpl("//div[contains(text(), 'Make this donation recurring')]/input", "Recurring Donation");
	LabelImpl donationAmountLabel = new LabelImpl("//label[text()='$20.00']/preceding-sibling::input", "Donation amount");
	LabelImpl donationAmountLabelOneTime = new LabelImpl("//ul[@id='sli-oneTimeDonationAmounts']/descendant::label[text()='$20.00']/preceding-sibling::input", "Donation amount");
	LabelImpl donationAmountLabelRecuring = new LabelImpl("//ul[@id='sli-recurringDonationAmounts']/descendant::label[text()='$20.00']", "Donation amount");
	
	TextBoxImpl donationAmountInput = new TextBoxImpl("//input[@name='customOneTime']", "Input for donation amount");
	TextBoxImpl donationRecuringAmountInput = new TextBoxImpl("//input[@name='customRecurringAmount']", "Input for recuring donation amount");
	TextBoxImpl donationOneTimeAmountInput = new TextBoxImpl("//input[@name='customOneTime']", "Input for donation amount");
	TextBox nameOnCardField = new TextBoxImpl("//input[@name='name_on_card']", "Name on Card", true);
	TextBox cardNumberField = new TextBoxImpl("//input[@name='card_number']", "Card number", true);
	TextBox cvvField = new TextBoxImpl("//input[@name='cvv']", "CVV", true);
	SelectBoxImpl expiryMonthField = new SelectBoxImpl("//select[@name='expiry_month']", "Expiry Month");
	SelectBoxImpl expiryYearField = new SelectBoxImpl("//select[@name='expiry_year']", "Expiry Year");
	
	Button donateButton = new ButtonImpl("//button[@type='submit']", "Donate", true);
	
	Label donationIsSccessMessage = new LabelImpl("//h1[.='Thank You!']", "Donation is success");
	Boolean isEvent = false;
	
	public DonationWidget() {
		super();
	}
	
	public DonationWidget(boolean clean) {
		super(clean);
	}
	
	@Override
	public void verifyWidgetElementsVisible(boolean visible){
		if (visible) {
			verifyBasicElementsVisible();
			verifier.verifyElementIsDisplayed(true, 
					donateButton, 
					recurringDonationCheckBox, 
					donationAmountLabel, 
					nameOnCardField, 
					cardNumberField, 
					cvvField, 
					expiryMonthField, 
					expiryYearField, 
					personAddressLine1Field, 
					personAddressLine2Field);
		} else {
			verifier.verifyElementIsNotDisplayed(true, donateButton);
		}
	}
	
	public DonationWidget openDonationWidgetLink() {
		String currentWindowHandle = getWindowHandle();
		open(CommonUtils.getProperty(PropertyName.DONATION_WIDGET_LINK));	
		switchToPopupWindow(currentWindowHandle);
		CommonUtils.setProperty(PropertyName.CURRENT_WINDOW_HANDLE, currentWindowHandle);
		return this;
	}
	
	
	public DonationWidget fillDonationForm(String personEmail,
			String personFName,
			String personLName,
			String personAddressLine1,
			String personAddressLine2,
			String personCity,
			String personZip,
			boolean recurringDonation,
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
		fillDonationForm(personEmail, personFName, personLName, personAddressLine1, personAddressLine2, personCity, personZip, "", recurringDonation, donationAmount, nameOnCard, cardNumber, cvv, expiryMonth, expiryYear, isFundraising, isNewsletter, isEmail);
		
		return this;
	}
	
	
	
	public DonationWidget fillDonationForm(String personEmail,
			String personFName,
			String personLName,
			String personAddressLine1,
			String personAddressLine2,
			String personCity,
			String personZip,
			String state,
			boolean recurringDonation,
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
		
		String[] donAmounts = new String[] {"5","10","15","20","25"};
		personEmailField.type(personEmail);
		personFNameField.type(personFName);
		personLNameField.type(personLName);
		if (personAddressLine1.length()<1) {
			personAddressLine1 = "TestV address";
		}
		personAddressLine1Field.type(personAddressLine1);
		
		personAddressLine2Field.type(personAddressLine2);
		if (personCity.length()<1) {
			personCity = "New York";
		}
		personCityField.type(personCity);
		personZipField.type(personZip);
		if (state.equals("")) {
			personStatesSelectBox.selectByIndex(Integer.parseInt(CommonUtils.getRandomValueFromTo(1, 50, 0)));
		}else{
			personStatesSelectBox.selectByValue(state);
		}
		if (!isEvent) {
			recurringDonationCheckBox.check(recurringDonation);
		}		
		
		if (recurringDonation) {
			donationAmountLabel.changePath("", donationAmountLabelRecuring.getPath());
			donationAmountInput.changePath("", donationRecuringAmountInput.getPath());
		}else{
			donationAmountLabel.changePath("", donationAmountLabelOneTime.getPath());
			donationAmountInput.changePath("", donationOneTimeAmountInput.getPath());
		}
		
		if (donationAmount.equals("")) {
			donationAmount = donAmounts[CommonUtils.getRandomValueNumericFromTo(0, donAmounts.length-1)];
			donationAmountLabel.changePath("$20", donationAmount);
			donationAmountLabel.click();
		}else{
			donationAmountInput.type(donationAmount);
		}
		nameOnCardField.type(nameOnCard);
		cardNumberField.type(cardNumber);
		cvvField.type(cvv);
		expiryMonthField.selectByLabel(expiryMonth);
		expiryYearField.selectByLabel(expiryYear);
		
		//fundraisingCheckBox.check(isFundraising);
		//newsletterCheckBox.check(isNewsletter);
		
		return this;
	}
	
	public DonationWidget clickDonationButton() {
		donateButton.click();
		return this;
	}
	
	public DonationWidget verifyDonationIsSuccesses() {
		for (int i = 0; i < 10; i++) {
			if (waitConditionBecomesTrue(donationIsSccessMessage.isDisplayed(), 10)) {
				break;
			}
		}
		verifier.verifyElementIsDisplayed(donationIsSccessMessage);
		return this;
	}
	
	public AddDonationWidgetPage backToFundraisingWidgetPage() {
		closeWindow();
		switchToWindow(CommonUtils.getProperty(PropertyName.CURRENT_WINDOW_HANDLE));
		return new AddDonationWidgetPage();
	}
}

