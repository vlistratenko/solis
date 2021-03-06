package com.salsalabs.ignite.automation.pages.hq.activities;

import com.salsalabs.ignite.automation.common.Browser;
import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.CheckBox;
import com.salsalabs.ignite.automation.elements.Label;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.*;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SubscribeWidget extends Browser{

	TextBox addressLine1Field = new TextBoxImpl("//input[@name='field-address-line1']", "Address Line 1 text field");
	TextBox addressLine2Field = new TextBoxImpl("//input[@name='field-address-line2']", "Address Line 2 text field");
	SelectBoxImpl genderSelectBox = new SelectBoxImpl("//select[@name='field-person-gender']", "Gender text field");
	TextBox homePhoneField = new TextBoxImpl("//input[@name='field-person-home_phone']", "Home Phone text field");
	TextBox personMNameField = new TextBoxImpl("//input[@name='field-person-middlename']", "Middle Name text field");
	SelectBoxImpl preferredLanguageSelectBox = new SelectBoxImpl("//select[@name='field-person-language']", "Preferred Language list");
	TextBox suffixField = new TextBoxImpl("//input[@name='field-person-suffix']", "Suffix text field");
	TextBox titleField = new TextBoxImpl("//input[@name='field-person-prefix']", "Title text field");
	TextBox workPhoneField = new TextBoxImpl("//input[@name='field-person-work_phone']", "Work Phone text field");
	TextBox cellPhoneField = new TextBoxImpl("//input[@name='field-person-cell_phone']", "Cell Phone text field");
	SelectBoxImpl countryField = new SelectBoxImpl("//select[@name='field-address-country']", "Country list");
	CheckBox keepMeInformedCheckbox = new CheckBoxImpl("//input[@name='contactOptIn']", "Keep Me Informed checkbox");
	CheckBox confirmationCheckbox = new CheckBoxImpl("//input[@name='termsAndConditions']", "Confirmation checkbox");
	TextBox dateOfBirthField = new TextBoxImpl("//input[@name='field-person-dob']", "Date of Birth text field");
	TextBox personEmailField = new TextBoxImpl("//input[@name='field-contact-email']", "Email", true);
	TextBox personFNameField = new TextBoxImpl("//input[@name='field-person-firstname']", "First name", true);
	TextBox personLNameField = new TextBoxImpl("//input[@name='field-person-lastname']", "Last name", true);
	TextBox personCityField = new TextBoxImpl("//input[@name='field-address-city']", "City", true);
	TextBox personZipField = new TextBoxImpl("//input[@name='field-address-zip']", "Zip", true);
	SelectBoxImpl personStatesSelectBox = new SelectBoxImpl("//select[@name='field-address-state']", "States");
	TextBox supporterTextBoxCustomField = new TextBoxImpl("//*[contains(text(),'supporterTextBox')]/following-sibling::input", "Supporter Text box custom field");
	TextBox supporterNumberCustomField = new TextBoxImpl("//*[contains(text(),'supporterNumber')]/following-sibling::input", "Supporter Number custom field");
	TextBox supporterDateTimeCustomField = new TextBoxImpl("//*[contains(text(),'supporterDateTime')]/following-sibling::input", "Supporter Date Time custom field");
	TextBox activityTextBoxCustomField = new TextBoxImpl("//*[contains(text(),'ActivityTextBox')]/following-sibling::input", "Activity Text box custom field");
	TextBox activityNumberCustomField = new TextBoxImpl("//*[contains(text(),'ActivityNumber')]/following-sibling::input", "Activity Number custom field");
	TextBox activityDateTimeCustomField = new TextBoxImpl("//*[contains(text(),'ActivityDateTime')]/following-sibling::input", "Activity Date Time custom field");
	Button supporterSingleChoiceCustomField = new ButtonImpl("//*[contains(text(),'supporterSingleChoice')]/following-sibling::*//label[1]","Supporter single choice custom field value");
	Button activitySingleChoiceCustomField = new ButtonImpl("//*[contains(text(),'ActivitySingleChoice')]/following-sibling::*//label[1]","Activity single choice custom field value");
	Button supporterYesNoCustomField = new ButtonImpl("//*[contains(text(),'supporterYesNo')]/following-sibling::*//label[1]","Supporter Yes/No custom field value");
	Button activityYesNoCustomField = new ButtonImpl("//*[contains(text(),'ActivityYesNo')]/following-sibling::*//label[1]","Activity Yes/No custom field value");
	Button calendarDoneButton = new ButtonImpl("//*[@id='ui-datepicker-div']//button[.='Done']","Calendar Done button");

	CheckBox fundraisingCheckBox = new CheckBoxImpl("//div[contains(text(), 'Fundraising')]/input", "Fundraising");
	CheckBox newsletterCheckBox = new CheckBoxImpl("//div[contains(text(), 'Newsletter')]/input", "Newsletter");
	CheckBox emailCheckBox = new CheckBoxImpl("//input[contains(@class, 'Email')]", "Email");
	
	Button subscribeButton = new ButtonImpl("//a[@data-ignite-submit-button='data-ignite-submit-button']", "Subscribe", true);
	Button subscribeButtonOld = new ButtonImpl("//button[.='Subscribe!']", "Subscribe", true);
	Label subscriptionIsSuccessMessage = new LabelImpl("//*[contains(.,'Thank You!')]", "Subscription is success");

	public SubscribeWidget() {
		deletecoockies();
		refresh();
	}
	
	public SubscribeWidget(boolean clean) {
		if (clean) {
			deletecoockies();
			refresh();
		}
	}
	
	protected void verifyBasicElementsVisible(){
		verifier.verifyElementIsDisplayed(true, 
				personEmailField, 
				personFNameField, 
				personLNameField, 
				personCityField, 
				personZipField, 
				personStatesSelectBox 
				);
	}
	
	public void verifyWidgetElementsVisible(boolean visible){
		if (visible) {
			verifyBasicElementsVisible();
			verifier.verifyElementIsDisplayed(true, subscribeButton);
		} else {
			verifier.verifyElementIsNotDisplayed(true, subscribeButton);
		}
	}
	
	public AddSubscribeWidgetPage backToSubscribeWidgetPage() {
		closeWindow();
		switchToWindow(CommonUtils.getProperty(PropertyName.CURRENT_WINDOW_HANDLE));
		return new AddSubscribeWidgetPage();
	}
	
	public LoginPage backToLoginPage() {
		closeWindow();
		switchToWindow(CommonUtils.getProperty(PropertyName.CURRENT_WINDOW_HANDLE));
		return new LoginPage();
	}

	public SubscribeWidget fillSubscribeWidget(String personEmail,
			String personFName,
			String personLName,
			String personCity,
			String personZip) {
		fillSubscribeWidget(personEmail, personFName, personLName, personCity, personZip, "");
		return this;
		
	}
	
	public SubscribeWidget fillSubscribeWidget(String personEmail,
			String personFName,
			String personLName,
			String personCity,
			String personZip,
			String state) {
		String stateValue="";
		personEmailField.type(personEmail);
		personFNameField.type(personFName);
		personLNameField.type(personLName);
		personCityField.type(personCity);
		personZipField.type(personZip);
		
		if (state.equals("")) {
			personStatesSelectBox.selectByIndex(Integer.parseInt(CommonUtils.getRandomValueFromTo(1, 50, 0)));
		}else{
			personStatesSelectBox.selectByValue(state);
			stateValue = personStatesSelectBox.getSelectedLabel(personStatesSelectBox.getPath());

		}
		sleep(3);
		subscribeButton.click();
		CommonUtils.setProperty("supporterEmailAddressValue", personEmail);
		CommonUtils.setProperty("supporterFirstFieldNameValue", personFName);
		CommonUtils.setProperty("supporterLastNameFieldValue", personLName);
		CommonUtils.setProperty("supporterCityFieldValue", personCity);
		CommonUtils.setProperty("supporterStateFieldValue", stateValue);
		return this;
	}

	public SubscribeWidget fillSubscribeWidget(String personEmail,
											   String personFName,
											   String personLName,
											   String personCity,
											   String personZip,
											   String state,
											   String addressLine1) {
		personEmailField.type(personEmail);
		personFNameField.type(personFName);
		personLNameField.type(personLName);
		personCityField.type(personCity);
		personZipField.type(personZip);
		addressLine1Field.type(addressLine1);

		if (state.equals("")) {
			personStatesSelectBox.selectByIndex(Integer.parseInt(CommonUtils.getRandomValueFromTo(1, 50, 0)));
		}else{
			personStatesSelectBox.selectByValue(state);
		}
		sleep(3);
		subscribeButton.click();
		sleep(3);
		return this;

	}

	public SubscribeWidget fillSubscribeWidgetMinimumFieldsSet(String personEmail, String personFName, String personLName, boolean subscriptionStatus) {
		personEmailField.type(personEmail);
		personFNameField.type(personFName);
		personLNameField.type(personLName);
		if (subscriptionStatus) {
			keepMeInformedCheckbox.check();
		} else {
			keepMeInformedCheckbox.unCheck();
		}
		sleep(3);
		subscribeButton.click();
		return this;
	}
	
	public SubscribeWidget fillSubscribeWidgetOldForms(String personEmail,
			String personFName,
			String personLName,
			String personCity,
			String personZip,
			String state) {
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
		sleep(3);
		subscribeButtonOld.click();
		return this;
		
	}
	
	public SubscribeWidget verifySubscriptionIsSuccesses() {
		for (int i = 0; i < 10; i++) {
			if (waitConditionBecomesTrue(subscriptionIsSuccessMessage.isVisible(), 10)) {
				break;
			}
		}
		verifier.verifyElementIsDisplayed(subscriptionIsSuccessMessage);
		return this;
	}

	public SubscribeWidget clickOnSubmitFormButton(){
		subscribeButton.scrollIntoView();
		subscribeButton.click();
		sleep(3);
		return this;
	}

	public String parseDateTimeValueToMatchHqResponse(String dateTimeValue) {
		String dateTime;
		DateTimeFormatter hqDateTimeFieldFormatterOut = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		try {
			DateTimeFormatter customFieldFormatterOut = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
			LocalDateTime customFieldLocalDateTime = LocalDateTime.parse(dateTimeValue.toUpperCase(), customFieldFormatterOut);
			dateTime = customFieldLocalDateTime.minusHours(3).format(hqDateTimeFieldFormatterOut).trim();
		} catch (DateTimeParseException e) {
			DateTimeFormatter publicFormBirthDateFieldDateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			LocalDate birthFieldDate = LocalDate.parse(dateTimeValue, publicFormBirthDateFieldDateFormat);
			LocalDateTime birthDateField = LocalDateTime.of(birthFieldDate, LocalTime.MIDNIGHT);
			dateTime = birthDateField.minusDays(1).format(hqDateTimeFieldFormatterOut).trim();
		} return dateTime;
	}

	public void clickCalendarDoneButton(){
		if(calendarDoneButton.isDisplayed()) calendarDoneButton.click();
	}

	public SubscribeWidget fillSubscribeWidgetAllCustomFields(String personEmail, String personFName, String personLName, String supporterTextBoxCustomFieldValue, String supporterNumberCustomFieldValue,
																		  String supporterDateTimeCustomFieldValue, String activityTextBoxCustomFieldValue, String activityNumberCustomFieldValue, String activityDateTimeCustomFieldValue) {

		personEmailField.fluentWaitForElementPresenceIgnoringExceptions();
		personEmailField.type(personEmail); CommonUtils.setProperty("personEmail", personEmail);
		personFNameField.type(personFName); CommonUtils.setProperty("personFName", personFName);
		personLNameField.type(personLName); CommonUtils.setProperty("personLName", personLName);
		personCityField.type("Kharkov");
		supporterTextBoxCustomField.type(supporterTextBoxCustomFieldValue); CommonUtils.setProperty("supporterTextBoxCustomFieldValue", supporterTextBoxCustomFieldValue);
		supporterNumberCustomField.type(supporterNumberCustomFieldValue); CommonUtils.setProperty("supporterNumberCustomFieldValue", supporterNumberCustomFieldValue);
		supporterDateTimeCustomField.type(supporterDateTimeCustomFieldValue); clickCalendarDoneButton(); CommonUtils.setProperty("supporterDateTimeCustomFieldValue", parseDateTimeValueToMatchHqResponse(supporterDateTimeCustomFieldValue));
		activityTextBoxCustomField.type(activityTextBoxCustomFieldValue); CommonUtils.setProperty("activityTextBoxCustomFieldValue", activityTextBoxCustomFieldValue);
		activityNumberCustomField.type(activityNumberCustomFieldValue); CommonUtils.setProperty("activityNumberCustomFieldValue", activityNumberCustomFieldValue);
		activityDateTimeCustomField.type(activityDateTimeCustomFieldValue); clickCalendarDoneButton(); CommonUtils.setProperty("activityDateTimeCustomFieldValue", activityDateTimeCustomFieldValue);
		supporterSingleChoiceCustomField.click(); CommonUtils.setProperty("supporterSingleChoiceCustomFieldValue", "value1");
		activitySingleChoiceCustomField.click(); CommonUtils.setProperty("activitySingleChoiceCustomFieldValue", "value1");
		supporterYesNoCustomField.click(); CommonUtils.setProperty("supporterYesNoCustomFieldValue", "True");
		activityYesNoCustomField.click(); CommonUtils.setProperty("activityYesNoCustomFieldValue", "True");
		sleep(3);
		return this;
	}

	public SubscribeWidget fillSubscribeWidgetAllSupporterFields(String personEmail, String personFName, String personLName, String personCity, String personZip,
																 String state, String addressLine1, String addressLine2, String gender, String homePhone,
																 String personMName, String language, String suffix, String title, String workPhone,
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
		titleField.type(title); CommonUtils.setProperty("title", title);
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

	public boolean isValidationMessageFieldRequiresValueDisplayed(String fieldName){
		Label fieldLabel;
		if(fieldName.toLowerCase().contains("checkbox")) {
			fieldLabel = new LabelImpl("//*[@type='checkbox']/following-sibling::*[@data-fv-validator='notEmpty']", "Label of Confirmation checkbox" );
		} else {
			fieldLabel = new LabelImpl("//label[contains(text(), '" + fieldName + "')]//following-sibling::*[@data-fv-validator='notEmpty']", "Label of " + fieldName + " field" );
		}
		return fieldLabel.isDisplayed() ? true : false;
	}

	public void verifyValidationMessageFieldRequireValueDisplayedForEmptySupporterFields(){
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("Address, line 2"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("Date of Birth"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("Gender"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("Cell Phone"), "'This field requires a value' validation message is not displayed");
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

	public void verifyValidationMessageFieldRequireValueDisplayedForEmptyCustomFields(){
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("ActivityTextBoxCustomField"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("ActivityNumberCustomField"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("supporterDateTimeCustomField"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("ActivityDateTimeCustomField"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("supporterTextBoxCustomField"), "'This field requires a value' validation message is not displayed");
		verifier.verifyTrue(isValidationMessageFieldRequiresValueDisplayed("supporterNumberCustomField"), "'This field requires a value' validation message is not displayed");
	}
}
