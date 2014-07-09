package pages.HQ.Emails;

import objects.Button;
import objects.TextBox;


public class AddEmailsPage_SetupTab extends AddEmailsPage {

	TextBox ReferenceNameField = new TextBox("//input[@name='name']", "Reference name", true);
	TextBox DescriptionField = new TextBox("//textarea[@name='description']", "Description", true);
	Button ChooseAudienceButton = new Button("//button[@id='btnChooseAudience']", "Choose Audience");
	
	public AddEmailsPage_ChooseAudienceTab fillAllFieldsAndGoForward(String emailBlastName) {
		ReferenceNameField.type(emailBlastName);
		DescriptionField.type("Descr");
		ChooseAudienceButton.click();
		return new AddEmailsPage_ChooseAudienceTab();
	}
	
}
