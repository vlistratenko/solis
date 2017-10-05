package com.salsalabs.ignite.automation.pages.hq.emails;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.DropDown;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.DropDownImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;


public class AddEmailsPage_SetupTab extends AddEmailsPage {
	
	DropDown selectEmailType = new DropDownImpl("//label[contains(text(),'Message Topic ')]/parent::div/descendant::custom-select3", "//label[contains(text(),'Message Topic ')]/parent::div/descendant::custom-select3/div/a", "Email type");
	TextBox ReferenceNameField = new TextBoxImpl("//input[@name='name']", "Reference name", true);
	TextBox DescriptionField = new TextBoxImpl("//textarea[@name='description']", "Description", true);
	Button ChooseAudienceButton = new ButtonImpl("//button[@id='btnChooseAudience']", "Choose Audience");
	
	public AddEmailsPage_ChooseAudienceTab fillAllFieldsAndGoForward(String emailBlastName) {
		ReferenceNameField.type(emailBlastName);
		DescriptionField.type("Descr");
		SelectEmailType();
		ChooseAudienceButton.click();
		sleep(10);
		return new AddEmailsPage_ChooseAudienceTab();
	}
	
	
	public void SelectEmailType() {
		selectEmailType.selectByLabelJS("Fundraising");
	}
	
	public  void verifyNewAddedMessageTopicInTheBlast(String label) {
		selectEmailType.selectByLabelJS(label);
		TextBox element = new TextBoxImpl("//label[contains(text(),'Message Topic ')]/parent::div/descendant::custom-select3//descendant::*[text()='"+ label+ "']", "Selected Message Topic");
		verifier.verifyEquals(element.getText(), label, " New added topic does not display");
	
	} 
	
	
}
