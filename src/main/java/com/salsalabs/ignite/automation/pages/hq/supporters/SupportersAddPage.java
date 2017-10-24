package com.salsalabs.ignite.automation.pages.hq.supporters;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.HttpClient;
import com.salsalabs.ignite.automation.common.Supporter;
import com.salsalabs.ignite.automation.elements.*;
import com.salsalabs.ignite.automation.elements.impl.*;
import com.salsalabs.ignite.automation.pages.hq.HomePage;
import com.salsalabs.ignite.automation.pages.hq.manage.CustomFieldsPage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

public class SupportersAddPage extends HomePage {

	// Supporter fields
	TextBox supporterExternalId = new TextBoxImpl("//input[@name='externalSystemId']", "Supporter External id");
	TextBox supporterBirthDateField = new TextBoxImpl("//input[@name='birthDate']", "Supporter Birthdate");
	TextBox supporterEmailField = new TextBoxImpl("//input[@name='MessagingEmail']", "Supporter email");
	TextBox supporterAddressLine1Field = new TextBoxImpl("//*[@id='line1']", "Supporter address Line1");
	TextBox supporterAddressLine2Field = new TextBoxImpl("//*[@id='line2']", "Supporter address Line2");
	TextBox supporterFirstNameField = new TextBoxImpl("//input[@name='first_name']", "Supporter first name");
	TextBox supporterLastNameField = new TextBoxImpl("//input[@name='last_name']", "Supporter last name");
	TextBox supporterMiddleName = new TextBoxImpl("//input[@name='middle_name']", "Supporter middle name");
	TextBox supporterPhoneField = new TextBoxImpl("//input[@name='phone']", "Phone");
	TextBox supporterSuffixField = new TextBoxImpl("//input[@name='suffix']", "Supporter suffix");
	TextBox supporterTitleField = new TextBoxImpl("//input[@name='prefix']", "Supporter title");
	TextBox supporterWorkPhoneField = new TextBoxImpl("//input[@name='workPhone']", "Supporter work phone");
	TextBox supporterBirthdayField = new TextBoxImpl("//input[@name='birthDate']", "Supporter birth date");
	DropDown supporterCountryField = new DropDownImpl("//label[='Country']/following-sibling::*[@data='countries']",
			"Supporter Country");
	TextBox supporterCellPhoneField = new TextBoxImpl("//input[@name='cellPhone']", "Supporter cell phone");
	TextBox supporterStreetField = new TextBoxImpl("//input[@name='line1']", "Street");
	TextBox supporterCityField = new TextBoxImpl("//input[@name='city']", "City");
	DropDown supporterStatesField = new DropDownImpl("//custom-select2[@data='states']/div/ul/li",
			"//custom-select2[@data='states']/div/a", "States");
	TextBox supporterZipField = new TextBoxImpl("//input[@name='zip']", "Zip");
	TextBox supporterFaceBookField = new TextBoxImpl("//input[@id='SocialFacebook']", "FaceBook");
	TextBox supporterTwitterField = new TextBoxImpl("//input[@id='SocialTwitter']", "Twitter");

	TextBox supporterLnField = new TextBoxImpl("//input[@id='SocialLinkedIn']", "Twitter");
	TextBox supporterGooglePlusField = new TextBoxImpl("//input[@id='SocialGooglePlus']", "GooglePlus");

	Button subscriptionTab = new ButtonImpl("//a[.=\"Subscription\"]", "Subscription tab");
	Button subscribedRadioButton = new ButtonImpl("//custom-radio-button[@text='Subscribed']//span",
			"Subscribed radio button");
	Button unsubscribedRadioButton = new ButtonImpl("//custom-radio-button[@text='Unsubscribed']//span",
			"Unsubscribed radio button");

	Label supporterStatusRadio = new LabelImpl("//span[@class='subscription custom radio checked']/ancestor::label",
			"Status");
	Label supporterStatusLabel = new LabelImpl("//p[.='Unsubscribed']", "Status");
	Button saveButton = new ButtonImpl("//button/descendant-or-self::*[text()='Save this Supporter!']", "Save button");
	Label supporterSubscriptions = new LabelImpl("//a[text()='Subscription']", "Subscription tab");
	Table supporterTable = new TableImpl("//table", "Supporter Table");
	Button customFieldAccordion = new ButtonImpl("//h2[@ng-click='toggleAccordion(customFields)']",
			"Custom  Fields Accordion Button");
	Button doneCalendarButton = new ButtonImpl("//button[contains(text(), 'Done')]", "Done calenard button   Button");
	Button subscribtionTab = new ButtonImpl("//a[contains(text(), 'Subscription')]", "Subscription tab Button");

	String dateValue = CommonUtils.createTodayDateString();
	String supportercustomFieldtextBoxValue = RandomStringUtils.randomAlphabetic(5);
	String supportercustomFieldNumberBoxValue = CommonUtils.getRandomNumericValueFixedLength(5);
	private String boleanOptionValue = "False";
	final String customFiledType = "PERSON";

	public SupportersPage createNewSupporter() {
		return createNewSupporter(Supporter.generateSupporter());
	}

	public SupportersPage createNewSupporter(Supporter supporter) {
		supporterEmailField.type(supporter.getFinalEMAIL());
		supporterExternalId.type(supporter.getExternalId());
		supporterBirthDateField.type(supporter.getBirthdate());
		if (doneCalendarButton.isDisplayed()) {
			doneCalendarButton.click();
			sleep(1);
		}
		supporterFirstNameField.type(supporter.getFirstName());
		supporterLastNameField.type(supporter.getLastName());
		supporterMiddleName.type(supporter.getMiddleName());
		supporterPhoneField.type(supporter.getcPhone());
		supporterStreetField.type(supporter.getAddressLine1());
		supporterCityField.type(supporter.getCity());
		supporterStatesField.selectByID(CommonUtils.getRandomValueFromTo(1, 5, 0));
		supporterZipField.type(supporter.getZipCode());
		supporterFaceBookField.type(supporter.getFacebook());
		supporterTwitterField.type(supporter.getTwitter());
		supporterLnField.type(supporter.getLinkedin());
		saveButton.click();
		for (int i = 0; i < 3; i++) {
			if (waitConditionBecomesTrue(supporterTable.isDisplayed(), 5))
				;
			break;
		}
		return new SupportersPage();
	}

	public SupportersAddPage verifySupporterData(String email, String fName, String lName, String phone,
			String addressLine1, String city, String zipCode, String faceBook, String twitter, String externalId,
			String supporterDateOfBirth, String middleName, String linkedin) {
		verifier.verifyEquals(supporterEmailField.getValue().contains(email), true, "Wrong email", false);
		verifier.verifyEquals(supporterFirstNameField.getValue().contains(fName), true, "Wrong firstname", false);
		verifier.verifyEquals(supporterLastNameField.getValue().contains(lName), true, "Wrong last name", false);
		verifier.verifyEquals(supporterPhoneField.getValue(), phone, "Wrong Phone", false);
		verifier.verifyEquals(supporterStreetField.getValue().contains(addressLine1), true, "Wrong Street", false);
		verifier.verifyEquals(supporterCityField.getValue().contains(city), true, "Wrong City", false);
		verifier.verifyEquals(supporterZipField.getValue(), zipCode, "Wrong zip", false);
		verifier.verifyEquals(supporterFaceBookField.getValue(), faceBook, "Wrong FaceBook", false);
		verifier.verifyEquals(supporterTwitterField.getValue(), twitter, "Wrong Twitter", false);
		verifier.verifyEquals(supporterExternalId.getValue().contains(externalId), true, "Wrong External ID", false);
		verifier.verifyEquals(supporterBirthDateField.getValue().contains(supporterDateOfBirth), true,
				"Wrong BirthDate field", false);
		verifier.verifyEquals(supporterMiddleName.getValue().contains(middleName), true, "Wrong Middle name", false);
		verifier.verifyEquals(supporterLnField.getValue().contains(linkedin), true, "Wrong Middle name", false);
		return new SupportersAddPage();
	}

	public SupportersAddPage goToSubscriptionTab() {
		subscriptionTab.click();
		return this;
	}

	public String getSupporterSubscriptionStatus() {
		goToSubscriptionTab();
		return (subscribedRadioButton.isDisplayed()
				&& subscribedRadioButton.getAttribute("class").equalsIgnoreCase("subscription custom radio checked"))
						? "Subscribed" : "Unsubscribed";
	}

	public SupportersAddPage verifySupporterCustomFieldData(String texBoxCustomFieldName, String dateCustomFieldName,
			String numberCustomFieldName, String yesNoCustomFieldName, String singleChoiceCustomFieldName) {

		if (customFieldAccordion.isDisplayed()) {
			customFieldAccordion.scrollIntoView();
			customFieldAccordion.click();
			TextBox textBoxCf = new TextBoxImpl("//input[@name='" + texBoxCustomFieldName + "']",
					"Text Box custom Field");
			verifier.verifyEquals(textBoxCf.getValue().contains(supportercustomFieldtextBoxValue), true,
					"Wrong Text Box Custom Field Value");
			TextBox dateCf = new TextBoxImpl("//input[@name='" + dateCustomFieldName + "']", "Date custom Field");
			verifier.verifyEquals(dateCf.getValue().contains(dateValue), true, "Wrong Text Box Custom Field Value");
			TextBox numberCf = new TextBoxImpl("//input[@name='" + numberCustomFieldName + "']",
					"Number  custom Field");
			verifier.verifyEquals(numberCf.getValue().contains(supportercustomFieldNumberBoxValue), true,
					"Wrong Number Custom Field Value");
			String booleanOption = yesNoCustomFieldName.substring(0, 1).toUpperCase()
					+ yesNoCustomFieldName.substring(1);
			TextBox yesNoCf = new TextBoxImpl("//label[contains(text(), '" + booleanOption
					+ "')]/ancestor::div[contains(@on, 'fieldDefinition')]//p[contains(., '" + boleanOptionValue
					+ "')]", "Yes No  custom Field");
			verifier.verifyEquals(yesNoCf.isNotExists(), false, "Wrong yesNO Custom Field Value");
			String singleChoice = singleChoiceCustomFieldName.substring(0, 1).toUpperCase()
					+ singleChoiceCustomFieldName.substring(1);
			Button singleChoiceCF = new ButtonImpl(
					"//label[contains(text(), '" + singleChoice
							+ "')]/ancestor::div[contains(@on, 'fieldDefinition')]//p[contains(., '2choice')]",
					"Single Choice   custom Field");
			verifier.verifyEquals(singleChoiceCF.isNotExists(), false, "Wrong Single Choice Custom Field Value");
		}
		return new SupportersAddPage();
	}

	public SupportersAddPage verifyCustomFieldDataForImportedSupporter(String texBoxCustomFieldName,
			String dateCustomFieldName, String numberCustomFieldName, String yesNoCustomFieldName,
			String singleChoiceCustomFieldName, String supportercustomFieldtextBoxValue , String dateValue, String numberValue, String booleanValue, String singleChoiceValue) {

		if (customFieldAccordion.isDisplayed()) {
			customFieldAccordion.scrollIntoView();
			customFieldAccordion.click();
			TextBox textBoxCf = new TextBoxImpl("//input[@name='" + texBoxCustomFieldName + "']",
					"Text Box custom Field");
			verifier.verifyEquals(textBoxCf.getValue().contains(supportercustomFieldtextBoxValue), true,
					"Wrong Text Box Custom Field Value");
			TextBox dateCf = new TextBoxImpl("//input[@name='" + dateCustomFieldName + "']", "Date custom Field");
			verifier.verifyEquals(dateCf.getValue().contains(dateValue), true, "Wrong Text Box Custom Field Value");
			TextBox numberCf = new TextBoxImpl("//input[@name='" + numberCustomFieldName + "']",
					"Number  custom Field");
			verifier.verifyEquals(numberCf.getValue().contains(numberValue), true,
					"Wrong Number Custom Field Value");
			String booleanOption = yesNoCustomFieldName.substring(0, 1).toUpperCase()
					+ yesNoCustomFieldName.substring(1);
			TextBox yesNoCf = new TextBoxImpl("//label[contains(text(), '" + booleanOption
					+ "')]/ancestor::div[contains(@on, 'fieldDefinition')]//p[contains(., '" + booleanValue
					+ "')]", "Yes No  custom Field");
			verifier.verifyEquals(yesNoCf.isNotExists(), false, "Wrong yesNO Custom Field Value");
			String singleChoice = singleChoiceCustomFieldName.substring(0, 1).toUpperCase()
					+ singleChoiceCustomFieldName.substring(1);
			Button singleChoiceCF = new ButtonImpl(
					"//label[contains(text(), '" + singleChoice
							+ "')]/ancestor::div[contains(@on, 'fieldDefinition')]//p[contains(., '"+singleChoiceValue+"')]",
					"Single Choice   custom Field");
			verifier.verifyEquals(singleChoiceCF.isNotExists(), false, "Wrong Single Choice Custom Field Value");
		}
		return new SupportersAddPage();
	}

	public SupportersPage verifyEmailAndMiddleName() {
		verifier.verifyEquals(supporterEmailField.getValue(), CommonUtils.getProperty("personEmail").toLowerCase());
		verifier.verifyEquals(supporterMiddleName.getValue(), CommonUtils.getProperty("personMName"));
		return new SupportersPage();
	}

	public SupportersPage verifySupporterStatus(String status) {
		subscribtionTab.scrollIntoView();
		subscribtionTab.click();
		Label tempElement;
		if (status.equalsIgnoreCase("Unsubscribed")) {
			tempElement = supporterStatusLabel;
		} else {
			tempElement = supporterStatusRadio;
		}
		for (int i = 0; i < 10; i++) {
			if (waitConditionBecomesTrueWithRefersh(tempElement.getText().equalsIgnoreCase(status), 10)) {
				break;
			}
		}
		verifier.verifyEquals(tempElement.getText(), status, "Wrong status", false);
		return new SupportersPage();
	}

	//alex hubachev method
	public SupportersPage verifySupporterSubscriptionTopics(String topic, String supporterEmail) {
		supporterSubscriptions.click();

		Element subscriptionItemsLabels = new LabelImpl(
				"//*[@ng-repeat='channel in supporter.contentChannelStatus']/td[1]", "Topics");
		Element subscriptionItemsStatusesOptedIn = new LabelImpl(
				"//*[@ng-repeat='channel in supporter.contentChannelStatus']/td[2]/span", "Topics");
		Element subscriptionItemsStatusesOptedOut = new LabelImpl(
				"//*[@ng-repeat='channel in supporter.contentChannelStatus']/td[3]/span", "Topics");

		java.util.List<WebElement> topicListLabels = subscriptionItemsLabels
				.findElementsByXpath(subscriptionItemsLabels.getPath());
		java.util.List<WebElement> topicListStatusesOptedIn = subscriptionItemsStatusesOptedIn
				.findElementsByXpath(subscriptionItemsStatusesOptedIn.getPath());
		java.util.List<WebElement> topicListStatusesOptedOut = subscriptionItemsStatusesOptedOut
				.findElementsByXpath(subscriptionItemsStatusesOptedOut.getPath());

		for (int i = 0; i < topicListLabels.size(); i++) {
			if (topicListLabels.get(i).getText().equals(topic)) {
				Assert.assertEquals(
						"Message topic must have SUBSCRIBED status, topic name: " + topicListLabels.get(i).getText()
								+ ". Supporter: " + supporterEmail,
						topicListStatusesOptedIn.get(i).getAttribute("ng-show"), "channel.contactStatus=='OPTED_IN'");
				Assert.assertEquals(
						"Message topic must have SUBSCRIBED status, topic name: " + topicListLabels.get(i).getText()
								+ ". Supporter: " + supporterEmail,
						topicListStatusesOptedIn.get(i).getAttribute("class"), "icon-checkmark2");
			} else {
				Assert.assertEquals(
						"Message topic must have UNSUSBCRIBED status, topic name: " + topicListLabels.get(i).getText()
								+ ". Supporter: " + supporterEmail,
						topicListStatusesOptedOut.get(i).getAttribute("ng-show"), "channel.contactStatus=='OPTED_OUT'");
				Assert.assertEquals(
						"Message topic must have UNSUSBCRIBED status, topic name: " + topicListLabels.get(i).getText()
								+ ". Supporter: " + supporterEmail,
						topicListStatusesOptedOut.get(i).getAttribute("class"), "icon-blocked");
			}
		}
		return new SupportersPage();
	}

	public SupportersPage createNewSupporterWithCustomFields(String login, String passward, Supporter supporter,
			String texBoxCustomFieldName, String dateCustomFieldName, String numberCustomFieldName,
			String singleChoiceCustomFieldName, String yesNoCustomFieldName)
			throws KeyManagementException, ClientProtocolException, NoSuchAlgorithmException, KeyStoreException,
			URISyntaxException, IOException, JSONException {
		TextBox textBoxCf = new TextBoxImpl("//input[@name='" + texBoxCustomFieldName + "']", "Text Box custom Field");
		TextBox dateCf = new TextBoxImpl("//input[@name='" + dateCustomFieldName + "']", "Date custom Field");
		TextBox numberCf = new TextBoxImpl("//input[@name='" + numberCustomFieldName + "']", "Number  custom Field");

		// firstLetter is capitalized on a Supporter Details Page
		String singleChoice = singleChoiceCustomFieldName.substring(0, 1).toUpperCase()
				+ singleChoiceCustomFieldName.substring(1);
		Button singleChoiceCF = new ButtonImpl(
				"//label[contains(text(), '" + singleChoice
						+ "')]/ancestor::div[contains(@on, 'fieldDefinition')]//p[contains(., '2choice')]",
				"Single Choice   custom Field");
		String booleanOption = yesNoCustomFieldName.substring(0, 1).toUpperCase() + yesNoCustomFieldName.substring(1);
		Button yesNoCf = new ButtonImpl(
				"//label[contains(text(), '" + booleanOption
						+ "')]/ancestor::div[contains(@on, 'fieldDefinition')]//p[contains(., 'False')]",
				"Yes No   custom Field");

		if (textBoxCf.isNotExists() & dateCf.isNotExists() & numberCf.isNotExists() & singleChoiceCF.isNotExists()
				& yesNoCf.isNotExists()) {
			CustomFieldsPage customFieldsPage = new CustomFieldsPage();
			new HttpClient().login(login, passward)
					.createCustomField(
							customFieldsPage.getTextBoxCustomFieldJson(customFiledType, texBoxCustomFieldName))
					.createCustomField(customFieldsPage.getSingleChoiceCustomFieldJson(customFiledType,
							singleChoiceCustomFieldName))
					.createCustomField(customFieldsPage.getDateCustomFieldJson(customFiledType, dateCustomFieldName))
					.createCustomField(customFieldsPage.getBoleanCustomFieldJson(customFiledType, yesNoCustomFieldName))
					.createCustomField(
							customFieldsPage.getNumberCustomFieldJson(customFiledType, numberCustomFieldName));
			refresh();
			waitConditionBecomesTrue(supporterEmailField.isDisplayed(), 5);
		}

		supporterEmailField.type(supporter.getFinalEMAIL());
		supporterExternalId.type(supporter.getExternalId());
		supporterBirthDateField.type(supporter.getBirthdate());
		if (doneCalendarButton.isDisplayed()) {
			doneCalendarButton.click();
			sleep(1);
		}
		supporterFirstNameField.type(supporter.getFirstName());
		supporterLastNameField.type(supporter.getLastName());
		supporterMiddleName.type(supporter.getMiddleName());
		supporterPhoneField.type(supporter.getcPhone());
		supporterStreetField.type(supporter.getAddressLine1());
		supporterCityField.type(supporter.getCity());
		supporterStatesField.selectByID(CommonUtils.getRandomValueFromTo(1, 5, 0));
		supporterZipField.type(supporter.getZipCode());
		supporterFaceBookField.type(supporter.getFacebook());
		supporterTwitterField.type(supporter.getTwitter());
		supporterLnField.type(supporter.getLinkedin());
		if (customFieldAccordion.isDisplayed()) {
			customFieldAccordion.scrollIntoView();
			customFieldAccordion.click();
			textBoxCf.scrollIntoView();
			textBoxCf.type(supportercustomFieldtextBoxValue);
			dateCf.scrollIntoView();
			dateCf.type(dateValue);
			if (doneCalendarButton.isDisplayed()) {
				doneCalendarButton.click();
				sleep(1);
			}
			numberCf.scrollIntoView();
			numberCf.type(supportercustomFieldNumberBoxValue);
			singleChoiceCF.scrollIntoView();
			singleChoiceCF.click();
			yesNoCf.scrollIntoView();
			yesNoCf.click();
		}

		saveButton.click();
		for (int i = 0; i < 3; i++) {
			if (waitConditionBecomesTrue(supporterTable.isDisplayed(), 5))
				;
			break;
		}
		return new SupportersPage();
	}
}
