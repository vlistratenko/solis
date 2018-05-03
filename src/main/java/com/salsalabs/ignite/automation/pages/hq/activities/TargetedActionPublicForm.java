package com.salsalabs.ignite.automation.pages.hq.activities;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.elements.Label;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.LabelImpl;
import com.salsalabs.ignite.automation.elements.impl.SelectBoxImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;

public class TargetedActionPublicForm extends SubscribeWidget {
	TextBox addressField = new TextBoxImpl("//input[@name='field-address-line1']", "Street Number and Name", true);
	TextBox zipField = new TextBoxImpl("//input[@name='field-address-zip']", "Zip", true);
	ButtonImpl findMyLeadersButton = new ButtonImpl("//button[@type='submit'] |//a[contains(., 'Take Action')]", "Find My Leaders");
	ButtonImpl sendButton = new ButtonImpl("//button[@type='submit']", "Send button");
	Label donationIsSccessMessage = new LabelImpl("//h1[.='Thank You!']", "Donation is success");
	SelectBoxImpl personTitleSelectBox = new SelectBoxImpl("//select[@name='PersonCensus@TITLE']", "Title");
	
	public TargetedActionPublicForm() {
		super();
	}	
	
	public TargetedActionPublicForm findLegistratorTLForm(String personAddressLine1,
														  String personZip,
														  String personEmail,
														  String personFName,
														  String personLName,
														  String personCity,
														  String state)
	{		
		addressField.type(personAddressLine1);
		personEmailField.type(personEmail);
		personFNameField.type(personFName);
		personLNameField.type(personLName);
		personCityField.type(personCity);
		personZipField.type(personZip);
		
		if (state.equals("")) {
			personStatesSelectBox.selectByIndex(Integer.parseInt(CommonUtils.getRandomValueFromTo(1, 50, 0)));
		}else{
			personStatesSelectBox.selectByValue(state);
		}
		clickFindButton();
		sleep(3);
		return this;
	}
	
	public SubscribeWidget fillTLWidget(String title) {
		personTitleSelectBox.selectByValue(title);		
		//homePhoneField.type("123-123-1234");
		sleep(3);
		sendButton.click();
		return this;
		
	}
	
	public TargetedActionPublicForm clickFindButton() {
		findMyLeadersButton.click();
		return this;
	}
	
	public TargetedActionPublicForm verifyDonationIsSuccesses() {
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

	public TargetedActionPublicForm fillTargetedActionFormWithAllSupporterFields(String personEmail, String personFName, String personLName, String personCity, String personZip,
																 String state, String addressLine1, String addressLine2, String gender, String homePhone,
																 String personMName, String language, String suffix, String workPhone,
																 String cellPhone, String country, String dateOfBirth) {

		personEmailField.fluentWaitForElementPresenceIgnoringExceptions();
		personEmailField.type(personEmail); CommonUtils.setProperty("personEmail", personEmail);
		personFNameField.type(personFName); CommonUtils.setProperty("personFName", personFName);
		personLNameField.type(personLName); CommonUtils.setProperty("personLName", personLName);
		countryField.selectByValue(country); CommonUtils.setProperty("country", "UA");
		personCityField.type(personCity); CommonUtils.setProperty("personCity", personCity);
		personZipField.type(personZip); CommonUtils.setProperty("personZip", personZip);
		addressLine1Field.type(addressLine1); CommonUtils.setProperty("addressLine1", addressLine1);
		addressLine2Field.type(addressLine2); CommonUtils.setProperty("addressLine2", addressLine2);
		homePhoneField.type(homePhone); CommonUtils.setProperty("homePhone", homePhone);
		personMNameField.type(personMName); CommonUtils.setProperty("personMName", personMName);
		suffixField.type(suffix); CommonUtils.setProperty("suffix", suffix);
		workPhoneField.type(workPhone); CommonUtils.setProperty("workPhone", workPhone);
		cellPhoneField.type(cellPhone); CommonUtils.setProperty("cellPhone", cellPhone);
		dateOfBirthField.type(dateOfBirth); clickCalendarDoneButton(); CommonUtils.setProperty("dateOfBirth", parseDateTimeValueToMatchHqResponse(dateOfBirth));
		personStatesSelectBox.selectByValue(state); CommonUtils.setProperty("state", "UA-63");
		genderSelectBox.selectByValue(gender); CommonUtils.setProperty("gender", "Male");
		preferredLanguageSelectBox .selectByValue(language);
		keepMeInformedCheckbox.check();
		sleep(3);
		return this;
	}

	@Override
	public void verifyValidationMessageFieldRequireValueDisplayedForEmptySupporterFields(){
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("Address, line 2"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("Date of Birth"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("Gender"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("Cell Phone"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("Phone"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("Middle Name"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("Preferred Language"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("Suffix"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("Work Phone"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("Email Address"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("First Name"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("Last Name"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("Address, line 1"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("City"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("State"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("Zip Code"), "'This field requires a value' validation message is not displayed");
	}

	public TargetedActionPublicForm fillTargetedActionFormWithAllCustomFields (String personEmail, String personFName, String personLName, String supporterTextBoxCustomFieldValue, String supporterNumberCustomFieldValue,
																			   String supporterDateTimeCustomFieldValue, String activityTextBoxCustomFieldValue, String activityNumberCustomFieldValue, String activityDateTimeCustomFieldValue,
																			   String address1, String stateValue, String zipCodeValue) {
		super.fillSubscribeWidgetAllCustomFields (personEmail, personFName, personLName, supporterTextBoxCustomFieldValue, supporterNumberCustomFieldValue,
				supporterDateTimeCustomFieldValue, activityTextBoxCustomFieldValue, activityNumberCustomFieldValue, activityDateTimeCustomFieldValue);
		addressLine1Field.type(address1);
		personStatesSelectBox.selectByValue(stateValue);
		personZipField.type(zipCodeValue);
		return this;
	}
}

