package com.salsalabs.ignite.automation.pages.donation;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;
import com.salsalabs.ignite.automation.pages.hq.activities.ActivitiesPage;

public class DonationsAddPage extends ActivitiesPage {
	private TextBox nameTextBox = new TextBoxImpl("//input[@name='name']", "Donation Form Name");
	private TextBox descriptionTextBox = new TextBoxImpl("//textarea[@name='description']", "Donation Form Description");
	private Button goToStep2Btn = new ButtonImpl("//button[@id='btnCompose2']", "Design My Form");
	
	// Fill name and description. Click Next button
	public DonationsAddPage fillFirstStep(String formName, String formDescription) {
		nameTextBox.type(formName);
		descriptionTextBox.type(formDescription);
		goToStep2Btn.click();
		return this;
	}
	
	// Choose layout
	public DonationsAddPage fillSecondStep(String layoutName) {
		
		return this;
	}
}
