package com.salsalabs.ignite.automation.pages.hq.supporters;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.Supporter;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.DropDown;
import com.salsalabs.ignite.automation.elements.Label;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.DropDownImpl;
import com.salsalabs.ignite.automation.elements.impl.LabelImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;
import com.salsalabs.ignite.automation.pages.hq.HomePage;

public class SupportersAddPage extends HomePage{

	TextBox supporterEmailField = new TextBoxImpl("//input[@name='MessagingEmail']", "Supporter email");
	TextBox supporterFirstNameField = new TextBoxImpl("//input[@name='first_name']", "Supporter first name");
	TextBox supporterLastNameField = new TextBoxImpl("//input[@name='last_name']","Supporter last name");
	TextBox supporterPhoneField = new TextBoxImpl  ("//input[@name='phone']", "Phone");
	TextBox supporterStreetField = new TextBoxImpl("//input[@name='line1']", "Street");
	TextBox supporterCityField = new TextBoxImpl("//input[@name='city']", "City");
	DropDown supporterStatesField = new DropDownImpl("//custom-select2[@data='states']/div/ul/li", "//custom-select2[@data='states']/div/a", "States");
	TextBox supporterZipField = new TextBoxImpl("//input[@name='zip']", "Zip");
	TextBox supporterFaceBookField = new TextBoxImpl("//input[@id='SocialFacebook']", "FaceBook");
	TextBox supporterTwitterField = new TextBoxImpl("//input[@id='SocialTwitter']", "Twitter");
	TextBox supporterGooglePlusField  = new TextBoxImpl("//input[@id='SocialGooglePlus']", "GooglePlus");
	Label supporterStatusRadio = new LabelImpl("//span[@class='subscription custom radio checked']/ancestor::label", "Status");
	Label supporterStatusLabel = new LabelImpl("//p[.='Unsubscribed']", "Status");
	Button saveButton = new ButtonImpl("//button/descendant-or-self::*[text()='Save this Supporter!']", "Save button");
	
	public SupportersPage createNewSupporter() {
		return createNewSupporter(Supporter.generateSupporter());
	}
	
	public SupportersPage createNewSupporter(Supporter supporter) {
		supporterEmailField.type(supporter.getFinalEMAIL());
		supporterFirstNameField.type(supporter.getFirstName());
		supporterLastNameField.type(supporter.getLastName());
		supporterPhoneField.type(supporter.getcPhone());
		supporterStreetField.type(supporter.getAddressLine1());
		supporterCityField.type(supporter.getCity());
		supporterStatesField.selectByID(CommonUtils.getRandomValueFromTo(1, 5, 0));
		supporterZipField.type(supporter.getZipCode());
		supporterFaceBookField.type(supporter.getFacebook());
		supporterTwitterField.type(supporter.getTwitter());
		saveButton.click();
		sleep(3);
		return new SupportersPage();
	}
	
	public SupportersPage verifySupporterData(String email, String fName, String lName, String phone, String addressLine1,
			String city, String zipCode, String faceBook, String twitter, String status) {
		verifier.verifyEquals(supporterEmailField.getValue().contains(email), true, "Wrong email", false);
		verifier.verifyEquals(supporterFirstNameField.getValue().contains(fName), true, "Wrong firstname", false);
		verifier.verifyEquals(supporterLastNameField.getValue().contains(lName), true, "Wrong last name", false);
		verifier.verifyEquals(supporterPhoneField.getValue(), phone, "Wrong Phone", false);
		verifier.verifyEquals(supporterStreetField.getValue().contains(addressLine1), true, "Wrong Street", false);
		verifier.verifyEquals(supporterCityField.getValue().contains(city), true, "Wrong City", false);
		verifier.verifyEquals(supporterZipField.getValue(), zipCode, "Wrong zip", false);
		verifier.verifyEquals(supporterFaceBookField.getValue(), faceBook, "Wrong FaceBook", false);
		verifier.verifyEquals(supporterTwitterField.getValue(), twitter, "Wrong Twitter", false);
		verifySupporterStatus(status);
		return new SupportersPage();
	}
	
	public SupportersPage verifySupporterStatus(String status) {
		Label tempElement;
		if (status.equalsIgnoreCase("Unsubscribed")) {
			tempElement = supporterStatusLabel;
		}else{
			tempElement = supporterStatusRadio;
		}
		for (int i = 0; i < 10; i++) {
			if (waitConditionBecomesTrueWithRefersh(tempElement.getText().equalsIgnoreCase(status), 30)) {
				break;
			}
		}
		verifier.verifyEquals(tempElement.getText(), status, "Wrong status", false);
		return new SupportersPage();
	}
	
}
