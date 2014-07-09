package pages.HQ.Supporters;

import objects.Button;
import objects.Table;
import pages.HQ.AudiencePage;

public class SupportersPage extends AudiencePage{

	Button AddSupporterButton = new Button("//button[text()='+ Add supporter']", "Add supporter");
	Table supportersTable = new Table("//table/descendant::a[text()='Email Address']/ancestor::table", "Table with supporters");
	
	public SupportersAddPage openAddSupporterPage() {
		AddSupporterButton.click();
		return new SupportersAddPage();
	}

	public SupportersPage checkSupporterExists(String param) {
		verify(supportersTable.isValueExists(param)>=0, true, "Supprter " + param + " was not found."); 
		return this;
		
	}
	
}
