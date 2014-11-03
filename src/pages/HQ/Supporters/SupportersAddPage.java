package pages.HQ.Supporters;

import objects.Button;
import objects.DropDown;
import objects.Supporter;
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
	Button saveButton = new Button("//button/descendant-or-self::*[text()='Save this Supporter!']", "Save button");
	
	public SupportersPage createNewSupporter() {
		String unicID = CommonUtils.getRandomValue(100000, 0);
		CommonUtils.setParam("supporterEmail", EmailClient.getEmailBox("supMan" + unicID));
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
	
	public SupportersPage verifySupporterData() {
		verify(supporterEmailField.getText().contains(new Supporter().Email), true, "Wrong email", false);
		verify(supporterFirstNameField.getText().contains(new Supporter().First_Name), true, "Wrong firstname", false);
		verify(supporterLastNameField.getText().contains(new Supporter().Last_Name), true, "Wrong last name", false);
		verify(supporterPhoneField.getText(), new Supporter().cPhone, "Wrong Phone", false);
		verify(supporterStreetField.getText().contains(new Supporter().AddressLine1), true, "Wrong Street", false);
		verify(supporterCityField.getText(), new Supporter().City, "Wrong City", false);
		verify(supporterZipField.getText(), new Supporter().Zip_Code, "Wrong zip", false);
		verify(supporterFaceBookField.getText(), new Supporter().Facebook, "Wrong FaceBook", false);
		verify(supporterTwitterField.getText(), new Supporter().Twitter, "Wrong Twitter", false);
		return new SupportersPage();
	}
	
}
