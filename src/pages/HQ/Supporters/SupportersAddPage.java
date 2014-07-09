package pages.HQ.Supporters;

import objects.Button;
import objects.DropDown;
import objects.TextBox;
import pages.HQ.HomePage;
import selenium.CommonUtils;
import selenium.EmailClient;

public class SupportersAddPage extends HomePage{

	TextBox supporterEmailField = new TextBox("//input[@name='emailAddress']", "Supporter email");
	TextBox supporterFirstNameField = new TextBox("//input[@name='first_name']", "Supporter first name");
	TextBox supporterLastNameField = new TextBox("//input[@name='last_name']","Supporter last name");
	TextBox supporterPhoneField = new TextBox  ("//input[@name='phone']", "Phone");
	TextBox supporterStreetField = new TextBox("//input[@name='line1']", "Street");
	TextBox supporterCityField = new TextBox("//input[@name='city']", "City");
	DropDown supporterStatesField = new DropDown("//custom-select2[@data='states']/div/ul/li", "//custom-select2[@data='states']/div/a", "States");
	TextBox supporterZipField = new TextBox("//input[@name='zip']", "Zip");
	TextBox supporterFaceBookField = new TextBox("//input[@name='facebook']", "FaceBook");
	TextBox supporterTwitterField = new TextBox("//input[@name='twitter']", "Twitter");
	TextBox supporterGooglePlusField  = new TextBox("//input[@name='googlePlus']", "GooglePlus");
	Button saveButton = new Button("//button/descendant-or-self::*[text()='Save']", "Save button");
	
	public SupportersPage createNewSupporter() {
		String unicID = CommonUtils.getRandomValue(100000, 0);
		CommonUtils.SetParam("supporterEmail", new EmailClient().getEmailBox("sup" + unicID));
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
	
}
