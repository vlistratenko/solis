package com.salsalabs.ignite.automation.pages.hq.emails;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;


public class AddEmailsPage_SetupTab extends AddEmailsPage {

	TextBox ReferenceNameField = new TextBoxImpl("//input[@name='name']", "Reference name", true);
	TextBox DescriptionField = new TextBoxImpl("//textarea[@name='description']", "Description", true);
	Button ChooseAudienceButton = new ButtonImpl("//button[@id='btnChooseAudience']", "Choose Audience");
	
	public AddEmailsPage_ChooseAudienceTab fillAllFieldsAndGoForward(String emailBlastName) {
		ReferenceNameField.type(emailBlastName);
		DescriptionField.type("Descr");
		ChooseAudienceButton.click();
		sleep(10);
		return new AddEmailsPage_ChooseAudienceTab();
	}
	
}
