package pages.HQ.Emails;

import objects.Button;
import objects.DropDown;


public class AddEmailsPage_ChooseAudienceTab extends AddEmailsPage{
	
	DropDown selectEmailType = new DropDown("//span[.='Select an email type...']/ancestor::div[@class='custom dropdown']", "//span[.='Select an email type...']/parent::a", "Email type");
	
	Button EntireList = new Button("//label[contains(@ng-click, 'ALL')]", "Entire list");
	Button SelectedSegmentsOrSupporters  = new Button("//label[contains(@ng-click, 'SELECTED')]", "Selected segments or supporters");
	Button ComposeButton = new Button("//button[@id='btnCompose']", "Compose");
	
	
	public AddEmailsPage_ChooseAudienceTab selectAudienceType(String type) {
		if (type.trim().equalsIgnoreCase("Entire list")) {
			EntireList.click();
		}
		if (type.trim().equalsIgnoreCase("Selected segments or supporters")) {
			SelectedSegmentsOrSupporters.click();
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
