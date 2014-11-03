package pages.HQ.Supporters;

import objects.Button;
import objects.Panel;
import objects.Table;
import objects.TextBox;
import pages.HQ.AudiencePage;

public class SupportersPage extends AudiencePage{

	Button AddSupporterButton = new Button("//button[text()='Add a New Supporter']", "Add supporter");
	Table supportersTable = new Table("//table/descendant::a[text()='Email Address']/ancestor::table", "Table with supporters");
	Panel feedBackDialogPanel = new Panel("//feedback-dialog/div[contains(@class, 'feedback alert-box')]", "Feedback dialog");
	Button closeFeedbackDialog = new Button(feedBackDialogPanel.path + "/descendant::a[@class='close']", "Close feedback dialog");
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
		verify(supportersTable.isValueExists(param)>=0, true, "Supprter " + param + " was not found."); 
		return this;
		
	}

	public SupportersPage searchSupporter(String personEmail) {
		searchField.type(personEmail);
		doSearchButton.click();
		return new SupportersPage();
	}

	public SupportersAddPage openSupporterDetailsPage() {
		supportersTable.clickInCell(1, 2, "span[@ng-click='editItem(item)']");
		return new SupportersAddPage();
	}
	
}
