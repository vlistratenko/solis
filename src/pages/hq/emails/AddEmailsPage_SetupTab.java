package pages.hq.emails;

import elements.Button;
import elements.TextBox;
import elements.impl.ButtonImpl;
import elements.impl.TextBoxImpl;


public class AddEmailsPage_SetupTab extends AddEmailsPage {

	TextBox ReferenceNameField = new TextBoxImpl("//input[@name='name']", "Reference name", true);
	TextBox DescriptionField = new TextBoxImpl("//textarea[@name='description']", "Description", true);
	Button ChooseAudienceButton = new ButtonImpl("//button[@id='btnChooseAudience']", "Choose Audience");
	
	public AddEmailsPage_ChooseAudienceTab fillAllFieldsAndGoForward(String emailBlastName) {
		ReferenceNameField.type(emailBlastName);
		DescriptionField.type("Descr");
		ChooseAudienceButton.click();
		return new AddEmailsPage_ChooseAudienceTab();
	}
	
}
