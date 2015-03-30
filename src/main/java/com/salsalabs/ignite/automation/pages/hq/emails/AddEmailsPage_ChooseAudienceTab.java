package com.salsalabs.ignite.automation.pages.hq.emails;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.DropDown;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.DropDownImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;


public class AddEmailsPage_ChooseAudienceTab extends AddEmailsPage{
	
	DropDown selectEmailType = new DropDownImpl("//span[.='Choose...']/ancestor::custom-select3", "//span[.='Choose...']/parent::a", "Email type");
	
	Button EntireList = new ButtonImpl("//span[contains(@ng-class, 'sendToAllSelected')]", "Entire list");
	Button SelectedSegmentsOrSupporters  = new ButtonImpl("//span[contains(@ng-class, '!blast.sendToAllSelected')]", " Selected segments of your list, or specific supporters");
	Button ComposeButton = new ButtonImpl("//button[@id='btnCompose']", "Next: Compose Your Email");
	
	TextBox addSupportersField = new TextBoxImpl("//p[contains(text(), 'Want to add some additional folks to this blast?')]/following-sibling::div/descendant::input", "Manually add supporters");
	Button suppirtersItemInTheSearchButton = new ButtonImpl("//div[@class='row result fade-out ng-scope']", "Supporters item in the search result", false);
	
	public AddEmailsPage_ChooseAudienceTab selectAudienceType(String type) {
		if (type.trim().equalsIgnoreCase("Entire list")) {
			EntireList.click();
		}
		if (type.trim().equalsIgnoreCase(" Selected segments of your list, or specific supporters")) {
			SelectedSegmentsOrSupporters.click();
		}
		return this;
	}
	
	public AddEmailsPage_ChooseAudienceTab addSupporters(String searchString, Integer amount) {
		if (amount==0) {
			return this;
		}
		sleep(5);
		addSupportersField.type(searchString);
		sleep(5);
		for (int i = 0; i < amount; i++) {
			suppirtersItemInTheSearchButton.click();
			sleep(1);
		}
		return this;
	}
	
	public AddEmailsPage_ChooseAudienceTab SelectEmailType() {
		selectEmailType.selectByLabelJS("Newsletter");
		return this;
	}
	
	public AddEmailsPage_ComposeTab openComposePage() {
		ComposeButton.click();
		return new AddEmailsPage_ComposeTab();
	}
	
}
