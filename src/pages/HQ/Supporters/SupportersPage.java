package pages.HQ.Supporters;

import objects.Button;
import objects.Table;
import objects.TextBox;
import pages.HQ.AudiencePage;

public class SupportersPage extends AudiencePage{

	Button AddSupporterButton = new Button("//button[text()='Add a New Supporter']", "Add supporter");
	Table supportersTable = new Table("//table/descendant::a[text()='Email address']/ancestor::table", "Table with supporters");
	TextBox searchField = new TextBox("//input[@name='query']", "Search");
	Button doSearchButton = new Button("//a[contains(@ng-click,'processing.search')]", "Do search");
	
	public SupportersAddPage openAddSupporterPage() {
		if (feedBackDialogPanel.isDisplayed()) {
			closeFeedbackDialog.click();
		}
		AddSupporterButton.click();
		return new SupportersAddPage();
	}

	public SupportersPage checkSupporterExists(String param) {
		verify(supportersTable.isValueExists(param)>0, true, "Supprter " + param + " was not found."); 
		return this;
		
	}
	
	public SupportersPage checkSupporterNotExists(String param) {
		verify(supportersTable.isValueExists(param)>0, false, "Supprter " + param + " was found."); 
		return this;
		
	}

	public SupportersPage searchSupporter(String personEmail) {
		searchField.type(personEmail);
		for (int i = 0; i < 10; i++) {
			doSearchButton.click();
			if (waitConditionBecomesTrue(supportersTable.isValueExists(personEmail)>0, 15000)) {
				break;
			}
		}
		return new SupportersPage();
	}

	public SupportersAddPage openSupporterDetailsPage() {
		sleep(3000);
		supportersTable.clickInCell(1, 2, "span/span[@ng-click='editItem(item)']");
		return new SupportersAddPage();
	}

	
}
