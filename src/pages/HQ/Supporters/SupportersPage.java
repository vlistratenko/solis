package pages.HQ.Supporters;

import objects.Button;
import objects.Panel;
import objects.Table;
import pages.HQ.AudiencePage;

public class SupportersPage extends AudiencePage{

	Button AddSupporterButton = new Button("//button[text()='Add a New Supporter']", "Add supporter");
	Table supportersTable = new Table("//table/descendant::a[text()='Email Address']/ancestor::table", "Table with supporters");
	Panel feedBackDialogPanel = new Panel("//feedback-dialog/div[contains(@class, 'feedback alert-box')]", "Feedback dialog");
	Button closeFeedbackDialog = new Button(feedBackDialogPanel.path + "/descendant::a[@class='close']", "Close feedback dialog");
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
	
}
