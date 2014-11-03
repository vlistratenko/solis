package pages.HQ.Emails;

import objects.Button;
import objects.DropDown;
import objects.TextBox;


public class AddEmailsPage_ChooseAudienceTab extends AddEmailsPage{
	
	DropDown selectEmailType = new DropDown("//span[.='Choose...']/ancestor::custom-select3", "//span[.='Choose...']/parent::a", "Email type");
	
	Button EntireList = new Button("//span[contains(@ng-class, 'sendToAllSelected')]", "Entire list");
	Button SelectedSegmentsOrSupporters  = new Button("//span[contains(@ng-class, '!blast.sendToAllSelected')]", " Selected segments of your list, or specific supporters");
	Button ComposeButton = new Button("//button[@id='btnCompose']", "Next: Compose Your Email");
	
	TextBox addSupportersField = new TextBox("//input[contains(@placeholder,'Find supporters')]", "Manually add supporters");
	Button suppirtersItemInTheSearchButton = new Button("//div[@class='row result fade-out ng-scope']", "Supporters item in the search result", false);
	
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
		sleep(5000);
		addSupportersField.type(searchString);
		sleep(5000);
		for (int i = 0; i < amount; i++) {
			suppirtersItemInTheSearchButton.click();
			sleep(1000);
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
