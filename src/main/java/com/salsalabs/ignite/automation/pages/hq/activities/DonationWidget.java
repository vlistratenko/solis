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
	CheckBox recurringDonationCheckBox = new CheckBoxImpl(
			"//div[contains(text(), 'Make this donation recurring')]/input", "Recurring Donation");
	CheckBox recurringDonationCheckBoxMultiSpepForm = new CheckBoxImpl("//input[@name='makeRecurring']",
			"Recurring Donation");
	CheckBox includeFeeCheckbox = new CheckBoxImpl("//input[@name='donorPaysFees']", "Include Fee checkbox");

	LabelImpl donationAmountLabel = new LabelImpl("//label[text()='$20.00']/preceding-sibling::input",
			"Donation amount");
	LabelImpl donationAmountLabelOneTime = new LabelImpl(
			"//ul[@id='sli-oneTimeDonationAmounts']/descendant::label[text()='$20.00']/preceding-sibling::input",
			"Donation amount");
	LabelImpl donationAmountLabelRecuring = new LabelImpl(
			"//ul[@id='sli-recurringDonationAmounts']/descendant::label[text()='$20.00']", "Donation amount");

	TextBoxImpl donationAmountInput = new TextBoxImpl("//input[@name='customOneTime']",
			"Input for donation amount for one time donations");
	TextBoxImpl donationAmountrecurringInput = new TextBoxImpl("//input[@name='customRecurringAmount']",
			"Input for donation amount for recurring donations");
	TextBoxImpl donationRecuringAmountInput = new TextBoxImpl("//input[@name='customRecurringAmount']",
			"Input for recuring donation amount");
	TextBoxImpl donationOneTimeAmountInput = new TextBoxImpl("//input[@name='customOneTime']",
			"Input for donation amount");
	TextBox nameOnCardField = new TextBoxImpl("//input[@name='name_on_card']", "Name on Card", true);
	TextBox cardNumberField = new TextBoxImpl("//input[@name='card_number']", "Card number", true);
	TextBox cvvField = new TextBoxImpl("//input[@name='cvv']", "CVV", true);
	SelectBoxImpl expiryMonthField = new SelectBoxImpl("//select[@name='expiry_month']", "Expiry Month");
	SelectBoxImpl expiryYearField = new SelectBoxImpl("//select[@name='expiry_year']", "Expiry Year");
	Button donateButton = new ButtonImpl("//button[@type='submit']", "Donate", true);
	Button donateNewFormButton = new ButtonImpl("//a[contains(text(), 'Donate')]", "Donate", true);
	Button nextButton = new ButtonImpl("//a[contains(text(), 'Next')]", "Next Button");
	Table checkoutSummaryTable = new TableImpl("//table[@class='sli-checkout-summary-table']", "CheckoutSummaryTable");
	Label donationIsSccessMessage = new LabelImpl("//*[contains(.,'Thank You!')]", "Donation is success");
	TextBox pleaseNotifyField = new TextBoxImpl("//input[@name='field-donationsummary-notify']", "Please notify field");
	TextBox dedicationFieldTextField = new TextBoxImpl("//input[@name='dedication']", "Dedication field text area");
	Button dedicationFieldInHonorRadioButton = new ButtonImpl("//*[@type='radio'][@value='InHonorOf']", "Dedication field In Honor radio button");
	SelectBoxImpl designationField = new SelectBoxImpl("//select[@name='designation']", "Designation list");
	Label missingDonationAmountMessage = new LabelImpl("//*[@class='sli-field-error'][@data-fv-for='donationAmtToggle']","Error message for missing donation amount");

	protected Boolean isEvent = false;

	public DonationWidget() {
		super();
	}

	public DonationWidget(boolean clean) {
		super(clean);
	}

	public DonationWidget fillDonationWidgetAllCustomFields (String supporterTextBoxCustomFieldValue, String supporterNumberCustomFieldValue, String supporterDateTimeCustomFieldValue, String activityTextBoxCustomFieldValue,
															 String activityNumberCustomFieldValue, String activityDateTimeCustomFieldValue, String personEmail, String personFName, String personLName,
															 String personAddressLine1, String personCity, String personZip, String state, String donationAmount, String nameOnCard,
															 String cardNumber, String cvv, String expiryMonth, String expiryYear){
		super.fillSubscribeWidgetAllCustomFields(personEmail, personFName, personLName,supporterTextBoxCustomFieldValue, supporterNumberCustomFieldValue,
				supporterDateTimeCustomFieldValue, activityTextBoxCustomFieldValue, activityNumberCustomFieldValue,activityDateTimeCustomFieldValue);
		this.fillCreditCardDetails(donationAmount, cardNumber, cvv, expiryMonth, expiryYear, nameOnCard);
		personAddressLine1Field.type(personAddressLine1);
		personCityField.type(personCity);
		personZipField.type(personZip);
		personStatesSelectBox.selectByValue(state);
		return this;
	}

	public DonationWidget fillDonationWidgetAllSupporterFields(String cardNumber, String securityCode, String expirationMonth, String expirationYear,
															   String nameOnCard, String personEmail, String personFName, String personLName, String personCity,
															   String personZip, String state, String addressLine1, String addressLine2, String gender, String homePhone,
															   String personMName, String language, String suffix, String title, String workPhone, String cellPhone,
															   String country, String dateOfBirth, String dedication, String pleaseNotify, String designation, String amount){
		this.fillDonationFormSpecificSupporterFields(designation, dedication, pleaseNotify);
		super.fillSubscribeWidgetAllSupporterFields(personEmail, personFName, personLName, personCity, personZip, state, addressLine1, addressLine2, gender, homePhone,
													personMName, language, suffix, title, workPhone, cellPhone, country, dateOfBirth);
		this.fillCreditCardDetails(amount, cardNumber, securityCode, expirationMonth, expirationYear, nameOnCard);
		return this;
	}

	public DonationWidget fillCreditCardDetails(String amount, String cardNumber, String securityCode, String expirationMonth, String expirationYear, String nameOnCard) {
		donationAmountInput.type(amount);
		nameOnCardField.type(nameOnCard);
		cardNumberField.type(cardNumber);
		cvvField.type(securityCode);
		expiryMonthField.selectByLabel(expirationMonth);
		expiryYearField.selectByLabel(expirationYear);
		return this;
	}

	public DonationWidget fillDonationFormSpecificSupporterFields(String designation, String dedication, String pleaseNotify) {
		designationField.selectByLabel(designation);
		dedicationFieldInHonorRadioButton.click();
		dedicationFieldTextField.type(dedication);
		pleaseNotifyField.type(pleaseNotify);
		return this;
	}

	@Override
	public void verifyWidgetElementsVisible(boolean visible) {
		if (visible) {
			verifyBasicElementsVisible();
			verifier.verifyElementIsDisplayed(true, donateButton, recurringDonationCheckBox, donationAmountLabel,
					nameOnCardField, cardNumberField, cvvField, expiryMonthField, expiryYearField,
					personAddressLine1Field, personAddressLine2Field);
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

	public DonationWidget fillDonationForm(String personEmail, String personFName, String personLName,
			String personAddressLine1, String personAddressLine2, String personCity, String personZip,
			boolean recurringDonation, String donationAmount, String nameOnCard, String cardNumber, String cvv,
			String expiryMonth, String expiryYear, boolean isFundraising, boolean isNewsletter, boolean isEmail) {
		fillDonationForm(personEmail, personFName, personLName, personAddressLine1, personAddressLine2, personCity,
				personZip, "", recurringDonation, donationAmount, nameOnCard, cardNumber, cvv, expiryMonth, expiryYear,
				isFundraising, isNewsletter, isEmail);

		return this;
	}

	public DonationWidget fillDonationForm(String personEmail, String personFName, String personLName,
			String personAddressLine1, String personAddressLine2, String personCity, String personZip, String state,
			boolean recurringDonation, String donationAmount, String nameOnCard, String cardNumber, String cvv,
			String expiryMonth, String expiryYear, boolean isFundraising, boolean isNewsletter, boolean isEmail) {

		String[] donAmounts = new String[] { "5", "10", "15", "20", "25" };
		personEmailField.type(personEmail);
		personFNameField.type(personFName);
		personLNameField.type(personLName);
		if (personAddressLine1.length() < 1) {
			personAddressLine1 = "TestV address";
		}
		personAddressLine1Field.type(personAddressLine1);

		personAddressLine2Field.type(personAddressLine2);
		if (personCity.length() < 1) {
			personCity = "New York";
		}
		personCityField.type(personCity);		
		if (personZip.length() < 1) {
			personZip = "91602";
		}
		personZipField.type(personZip);
		if (state.equals("")) {
			personStatesSelectBox.selectByIndex(Integer.parseInt(CommonUtils.getRandomValueFromTo(1, 50, 0)));
		} else {
			personStatesSelectBox.selectByValue(state);
		}
		if (!isEvent) {
			recurringDonationCheckBox.check(recurringDonation);
		}

		if (recurringDonation) {
			donationAmountLabel.changePath("", donationAmountLabelRecuring.getPath());
			donationAmountInput.changePath("", donationRecuringAmountInput.getPath());
		} else {
			donationAmountLabel.changePath("", donationAmountLabelOneTime.getPath());
			donationAmountInput.changePath("", donationOneTimeAmountInput.getPath());
		}

		if (donationAmount.equals("")) {
			donationAmount = donAmounts[CommonUtils.getRandomValueNumericFromTo(0, donAmounts.length - 1)];
			donationAmountLabel.changePath("$20", donationAmount);
			donationAmountLabel.click();
		} else {
			donationAmountInput.type(donationAmount);
		}
		nameOnCardField.type(nameOnCard);
		cardNumberField.type(cardNumber);
		cvvField.type(cvv);
		expiryMonthField.selectByLabel(expiryMonth);
		expiryYearField.selectByLabel(expiryYear);

		// fundraisingCheckBox.check(isFundraising);
		// newsletterCheckBox.check(isNewsletter);

		return this;
	}
	
	public DonationWidget fillDonationForm(String personEmail, String personFName, String personLName,
			String personAddressLine1, String personAddressLine2, String personCity, String personZip, String state,
			boolean recurringDonation, String donationAmount, String nameOnCard, String cardNumber, String cvv,
			String expiryMonth, String expiryYear) {

		String[] donAmounts = new String[] { "5", "10", "15", "20", "25" };
		personEmailField.type(personEmail);
		personFNameField.type(personFName);
		personLNameField.type(personLName);
		if (personAddressLine1.length() < 1) {
			personAddressLine1 = "TestV address";
		}
		personAddressLine1Field.type(personAddressLine1);

		personAddressLine2Field.type(personAddressLine2);
		if (personCity.length() < 1) {
			personCity = "New York";
		}
		personCityField.type(personCity);
		personZipField.type(personZip);
		if (state.equals("")) {
			personStatesSelectBox.selectByIndex(Integer.parseInt(CommonUtils.getRandomValueFromTo(1, 50, 0)));
		} else {
			personStatesSelectBox.selectByValue(state);
		}
		if (!isEvent) {
			recurringDonationCheckBox.check(recurringDonation);
		}

		if (recurringDonation) {
			donationAmountLabel.changePath("", donationAmountLabelRecuring.getPath());
			donationAmountInput.changePath("", donationRecuringAmountInput.getPath());
		} else {
			donationAmountLabel.changePath("", donationAmountLabelOneTime.getPath());
			donationAmountInput.changePath("", donationOneTimeAmountInput.getPath());
		}

		if (donationAmount.equals("")) {
			donationAmount = donAmounts[CommonUtils.getRandomValueNumericFromTo(0, donAmounts.length - 1)];
			donationAmountLabel.changePath("$20", donationAmount);
			donationAmountLabel.click();
		} else {
			donationAmountInput.type(donationAmount);
		}
		nameOnCardField.type(nameOnCard);
		cardNumberField.type(cardNumber);
		cvvField.type(cvv);
		expiryMonthField.selectByLabel(expiryMonth);
		expiryYearField.selectByLabel(expiryYear);

		// fundraisingCheckBox.check(isFundraising);
		// newsletterCheckBox.check(isNewsletter);

		return this;
	}

	public DonationWidget fillTheFirstStepOfTheDonationForm(String amount, boolean recurringChecbox,
			boolean feeCheckBox) {
		if (recurringChecbox) {
			verifier.verifyElementIsDisplayed(true, recurringDonationCheckBoxMultiSpepForm);
			recurringDonationCheckBoxMultiSpepForm.check();
			donationAmountInput.changePath("//input[@name='customOneTime']", "//input[@name='customRecurringAmount']");
			donationAmountInput.waitElement(10);
		}
		donationAmountInput.click();
		donationAmountInput.type(amount);
		sleep(2);
		if (feeCheckBox) {
			includeFeeCheckbox.check();
		}
		nextButton.click();
		return this;
	}

	public DonationWidget fillTheSecondStepOfTheDonationForm(String personEmail, String personFName, String personLName,
			String personAddressLine1, String personCity, String personZip, String state) {

		personEmailField.type(personEmail);
		personFNameField.type(personFName);
		personLNameField.type(personLName);
		if (personAddressLine1.length() < 1) {
			personAddressLine1 = "Personal address";
		}
		personAddressLine1Field.type(personAddressLine1);
		personCityField.type(personCity);
		personZipField.type(personZip);
		if (personCity.length() < 1) {
			personCity = "New York";
		}
		personStatesSelectBox.selectByValue("AL");
		nextButton.scrollIntoView();
		nextButton.click();
		return this;
	}

	public DonationWidget fillTheThirdStepOfTheDonationForm(String cardNumber, String cvv, String expMonth,
			String year) {
		nameOnCardField.clear();
		nameOnCardField.type("Name on Card");
		cardNumberField.clear();
		cardNumberField.type(cardNumber);
		sleep(1);
		cvvField.clear();
		cvvField.type(cvv);
		expiryMonthField.selectByLabel(expMonth);
		expiryYearField.selectByLabel(year);
		sleep(1);
		return this;
	}

	public DonationWidget verifyFeeForProvidedCardType(String cardType, String correctFeevalue) {
		checkoutSummaryTable.scrollIntoView();
		verifier.verifyEquals(checkoutSummaryTable.getCellValue(4, 1), correctFeevalue,
				"Fee value for" + cardType + " in the Purchase summary table is incorrect", true);
		return this;
	}

	public DonationWidget veriFyfeeCalculationForWepay(String cardType, double amount) {
		switch (cardType) {
		case "Visa":
			double formulaVisa = (amount + 0.30) / (1 - 0.022) - amount;
			String formatVisa = String.format("%.2f", formulaVisa);
			String visaFeeValue = "$" + formatVisa.replace(",", ".");
			verifier.verifyEquals(checkoutSummaryTable.getCellValue(4, 1), visaFeeValue,
					"Fee value for viza in the Purchase summary table is incorrect", true);
			break;
		case "MasterCard":
			double formulaMC = (amount + 0.30) / (1 - 0.022) - amount;
			String formatMC = String.format("%.2f", formulaMC);
			String mcFeeValue = "$" + formatMC.replace(",", ".");
			verifier.verifyEquals(checkoutSummaryTable.getCellValue(4, 1), mcFeeValue,
					"Fee value for Master Card in the Purchase summary table is incorrect", true);
			break;
		case "Amex":
			double formulaAmex = (amount + 0.30) / (1 - 0.032) - amount;
			String formatAmex = String.format("%.2f", formulaAmex);
			String amexFee = "$" + formatAmex.replace(",", ".");
			verifier.verifyEquals(checkoutSummaryTable.getCellValue(4, 1), amexFee,
					"Fee value for Amex in the Purchase summary table is incorrect", true);
		default:
			break;
		}
		return this;
	}

	public DonationWidget veriFyfeeCalculationForCardConnect(String cardType, double amount) {
		switch (cardType) {
		case "Visa":
			String visaFeeValue = calculateFeeForCardConnect(amount);
			verifier.verifyEquals(checkoutSummaryTable.getCellValue(4, 1), visaFeeValue,
					"Fee value for viza in the Purchase summary table is incorrect", true);
			break;
		case "MasterCard":
			
			String mcFeeValue = calculateFeeForCardConnect(amount);
			verifier.verifyEquals(checkoutSummaryTable.getCellValue(4, 1), mcFeeValue,
					"Fee value for Master Card in the Purchase summary table is incorrect", true);
			break;
		case "Amex":
			String amexFee = calculateFeeForCardConnect(amount);
			verifier.verifyEquals(checkoutSummaryTable.getCellValue(4, 1), amexFee,
					"Fee value for Amex in the Purchase summary table is incorrect", true);
		
		}
		return this;
	}

	/*
	 * this Formula can be used to calculate cardConnect Fee for Visa, MasterCar
	 * and Amex Wepay fee for different card types calculates with another
	 * approach
	 */

	public String calculateFeeForCardConnect(double amount) {
		double fee = (amount + 0.2) / (1 - 0.0289) - amount;
		String format = String.format("%.2f", fee);
		String feeValue = "$" + format.replace(",", ".");
		return feeValue;
	}

	public String calculateAmountWithFeeForCardConnect(double amount) {
		double feeandAmmount = (amount + 0.2) / (1 - 0.0289);
		String format = String.format("%.2f", feeandAmmount);
		String feeValueAndAmount = "$" + format.replace(",", ".");
		return feeValueAndAmount;
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
		sleep(1);
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

	public void verifyValidationMessageFieldRequireValueDisplayedForEmptySupporterFieldsAndDonation(){
		verifier.verifyTrue(missingDonationAmountMessage.isDisplayed(), "Validation message is not displayed despite donation amount is not specified");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("Card Number"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("Security Code"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("Expiration Month"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("Expiration Year"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("Name on Card"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("Address, line 2"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("Confirmation Checkbox"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("Date of Birth"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("Gender"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("Phone"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("Middle Name"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("Preferred Language"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("Suffix"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("Title"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("Work Phone"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("Email Address"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("First Name"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("Last Name"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("Address, line 1"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("City"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("State"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("Zip Code"), "'This field requires a value' validation message is not displayed");
	}
}
