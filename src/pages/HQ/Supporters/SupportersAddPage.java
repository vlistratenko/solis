package pages.HQ.Supporters;

import objects.Button;
import objects.DropDown;
import objects.Label;
import objects.TextBox;
import pages.HQ.HomePage;
import selenium.CommonUtils;
import selenium.EmailClient;

public class SupportersAddPage extends HomePage{

	TextBox supporterEmailField = new TextBox("//input[@name='MessagingEmail']", "Supporter email");
	TextBox supporterFirstNameField = new TextBox("//input[@name='first_name']", "Supporter first name");
	TextBox supporterLastNameField = new TextBox("//input[@name='last_name']","Supporter last name");
	TextBox supporterPhoneField = new TextBox  ("//input[@name='phone']", "Phone");
	TextBox supporterStreetField = new TextBox("//input[@name='line1']", "Street");
	TextBox supporterCityField = new TextBox("//input[@name='city']", "City");
	DropDown supporterStatesField = new DropDown("//custom-select2[@data='states']/div/ul/li", "//custom-select2[@data='states']/div/a", "States");
	TextBox supporterZipField = new TextBox("//input[@name='zip']", "Zip");
	TextBox supporterFaceBookField = new TextBox("//input[@id='SocialFacebook']", "FaceBook");
	TextBox supporterTwitterField = new TextBox("//input[@id='SocialTwitter']", "Twitter");
	TextBox supporterGooglePlusField  = new TextBox("//input[@id='SocialGooglePlus']", "GooglePlus");
	Label supporterStatusRadio = new Label("//span[@class='subscription custom radio checked']/ancestor::label", "Status");
	Label supporterStatusLabel = new Label("//p[.='Unsubscribed']", "Status");
	Button saveButton = new Button("//button/descendant-or-self::*[text()='Save this Supporter!']", "Save button");
	
	public SupportersPage createNewSupporter() {
		String unicID = CommonUtils.getRandomValue(100000, 0);
		CommonUtils.setParam("supporterEmail", EmailClient.getEmailBox("supman" + unicID));
		supporterEmailField.type(CommonUtils.getParam("supporterEmail"));
		supporterFirstNameField.type("Tester" + unicID);
		supporterLastNameField.type("Testerov" + unicID);
		supporterPhoneField.type("23" + CommonUtils.getRandomNumericValueFixedLength(9));
		supporterStreetField.type("Street" + unicID);
		supporterCityField.type("City" + unicID);
		supporterStatesField.selectByID(CommonUtils.getRandomValueFromTo(1, 5, 0));
		supporterZipField.type(CommonUtils.getRandomNumericValueFixedLength(6));
		supporterFaceBookField.type("FB" + unicID);
		supporterTwitterField.type("twitter" + unicID);
		supporterGooglePlusField.type("googlePlus" + unicID);
		saveButton.click();
		return new SupportersPage();
	}
	
	public SupportersPage verifySupporterData(String email, String fName, String lName, String phone, String addressLine1,
			String city, String zipCode, String faceBook, String twitter, String status) {
		verify(supporterEmailField.getValue().contains(email), true, "Wrong email", false);
		verify(supporterFirstNameField.getValue().contains(fName), true, "Wrong firstname", false);
		verify(supporterLastNameField.getValue().contains(lName), true, "Wrong last name", false);
		verify(supporterPhoneField.getValue(), phone, "Wrong Phone", false);
		verify(supporterStreetField.getValue().contains(addressLine1), true, "Wrong Street", false);
		verify(supporterCityField.getValue().contains(city), true, "Wrong City", false);
		verify(supporterZipField.getValue(), zipCode, "Wrong zip", false);
		verify(supporterFaceBookField.getValue(), faceBook, "Wrong FaceBook", false);
		verify(supporterTwitterField.getValue(), twitter, "Wrong Twitter", false);
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
			if (waitConditionBecomesTrueWithRefersh(tempElement.getText().equalsIgnoreCase(status), 30000)) {
				break;
			}
		}
		verify(tempElement.getText(), status, "Wrong status", false);
		return new SupportersPage();
	}
	
}
