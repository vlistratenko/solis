package com.salsalabs.ignite.automation.pages.hq.activities;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.CheckBox;
import com.salsalabs.ignite.automation.elements.Label;
import com.salsalabs.ignite.automation.elements.Table;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.CheckBoxImpl;
import com.salsalabs.ignite.automation.elements.impl.LabelImpl;
import com.salsalabs.ignite.automation.elements.impl.SelectBoxImpl;
import com.salsalabs.ignite.automation.elements.impl.TableImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;

public class DonationWidget extends SubscribeWidget {
	TextBox personAddressLine1Field = new TextBoxImpl("//input[@name='field-address-line1']", "Address line 1", true);
	TextBox personAddressLine2Field = new TextBoxImpl("//input[@name='field-address-line2']", "Address line 2", true);
	CheckBox recurringDonationCheckBox = new CheckBoxImpl("//div[contains(text(), 'Make this donation recurring')]/input", "Recurring Donation");
	CheckBox recurringDonationCheckBoxMultiSpepForm = new CheckBoxImpl("//input[@name='makeRecurring']", "Recurring Donation");
	CheckBox includeFeeCheckbox = new CheckBoxImpl("//input[@name='donorPaysFees']", "Include Fee checkbox");
	
	
	LabelImpl donationAmountLabel = new LabelImpl("//label[text()='$20.00']/preceding-sibling::input", "Donation amount");
	LabelImpl donationAmountLabelOneTime = new LabelImpl("//ul[@id='sli-oneTimeDonationAmounts']/descendant::label[text()='$20.00']/preceding-sibling::input", "Donation amount");
	LabelImpl donationAmountLabelRecuring = new LabelImpl("//ul[@id='sli-recurringDonationAmounts']/descendant::label[text()='$20.00']", "Donation amount");
	
	TextBoxImpl donationAmountInput = new TextBoxImpl("//input[@name='customOneTime']", "Input for donation amount for one time donations");
	TextBoxImpl donationAmountrecurringInput = new TextBoxImpl("//input[@name='customRecurringAmount']", "Input for donation amount for recurring donations");
	
	

	TextBoxImpl donationRecuringAmountInput = new TextBoxImpl("//input[@name='customRecurringAmount']", "Input for recuring donation amount");
	TextBoxImpl donationOneTimeAmountInput = new TextBoxImpl("//input[@name='customOneTime']", "Input for donation amount");
	TextBox nameOnCardField = new TextBoxImpl("//input[@name='name_on_card']", "Name on Card", true);
	TextBox cardNumberField = new TextBoxImpl("//input[@name='card_number']", "Card number", true);
	TextBox cvvField = new TextBoxImpl("//input[@name='cvv']", "CVV", true);
	SelectBoxImpl expiryMonthField = new SelectBoxImpl("//select[@name='expiry_month']", "Expiry Month");
	SelectBoxImpl expiryYearField = new SelectBoxImpl("//select[@name='expiry_year']", "Expiry Year");
	Button donateButton = new ButtonImpl("//button[@type='submit']", "Donate", true);
	Button donateNewFormButton = new ButtonImpl("//a[contains(text(), 'Donate')]", "Donate", true);
	Button nextButton = new ButtonImpl("//a[contains(text(), 'Next')]", "Next Button");
	Table checkoutSummaryTable = new TableImpl("//table[@class='sli-checkout-summary-table']", "CheckoutSummaryTable");
	
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
	
	public DonationWidget fillTheFirstStepOfTheDonationForm(boolean recurringChecbox, boolean isThisWepayForm){
		if (recurringChecbox){
			recurringDonationCheckBoxMultiSpepForm.check();
			donationAmountInput.changePath("//input[@name='customOneTime']", "//input[@name='customRecurringAmount']");
		}
		donationAmountInput.type("100");
		sleep(2);
		includeFeeCheckbox.check();
		if(isThisWepayForm){
			checkoutSummaryTable.scrollIntoView();
			verifier.verifyEquals(checkoutSummaryTable.getCellValue(4, 1), "$3.62", "Fee value before specifing a Card in the Purchase summary table is incorrect ");
		}else {
			checkoutSummaryTable.scrollIntoView();
			verifier.verifyEquals(checkoutSummaryTable.getCellValue(4, 1), "$3.18", "Fee value before specifing a Card in the Purchase summary table is incorrect ");
		}
		
	
		nextButton.click();
		return this;
	}
	
	
	public DonationWidget fillTheSecondStepOfTheDonationForm(
			String personEmail,
			String personFName,
			String personLName,
			String personAddressLine1,
			String personCity,
			String personZip,
			String state
			){
	
		personEmailField.type(personEmail);
		personFNameField.type(personFName);
		personLNameField.type(personLName);
		if (personAddressLine1.length()<1) {
			personAddressLine1 = "Personal address";
		}
		personAddressLine1Field.type(personAddressLine1);
		personCityField.type(personCity);
		personZipField.type(personZip);
		if (personCity.length()<1) {
			personCity = "New York";
		}
		personStatesSelectBox.selectByValue("AL");
		verifier.verifyEquals(checkoutSummaryTable.getCellValue(4, 1), "$3.62", "Fee value before specifing a Card in the Purchase summary table is incorrect ");
		nextButton.click();
		return this;
	}
	
	
	public DonationWidget fillTheThirdStepOfTheDonationForm(String cardNumber, String cvv,  String expMonth , String year){
		nameOnCardField.clear();
		nameOnCardField.type("Name on Card");
		cardNumberField.clear();
		cardNumberField.type(cardNumber);
		sleep(1);
		cvvField.clear();
		cvvField.type(cvv);
		expiryMonthField.selectByLabel(expMonth);
		expiryYearField.selectByLabel(year);
		return this;
	}
	
	public DonationWidget verifyFeeForProvidedCardType(String cardType, String correctFeevalue){
		checkoutSummaryTable.scrollIntoView();
		verifier.verifyEquals(checkoutSummaryTable.getCellValue(4, 1), correctFeevalue, "Fee value for" + cardType + " in the Purchase summary table is incorrect" , true);
		return this;
	}

	/*
	 * Do not uodate this method. It uses for old form only!!!
	 * 
	 */
	public DonationWidget clickDonationButton() {
		donateButton.click();
		sleep(3);
		return this;
	}
	
	/*
	 * For all new forms, use this method
	 */
	public DonationWidget clickDonationButtonNewForms() {
		donateNewFormButton.click();
		sleep(4);
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

