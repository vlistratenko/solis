package pages.HQ.Emails;

import objects.Button;
import objects.Table;
import pages.HQ.HomePage;


public class EmailBlastsPage extends HomePage{
	Button addEmailButton = new Button("//button[@ng-click='createEmail()']", "Create email");
	Table emailsTable = new Table("//table/descendant::a[text()='Reference Name']/ancestor::table", "Table with emails");
	
	public AddEmailsPage_SetupTab openAddEmailPage() {
		addEmailButton.click();
		return new AddEmailsPage_SetupTab();
	}

	public EmailBlastsPage checkEmailExists(String param) {
		verify(emailsTable.isValueExists(param)>=0, true, "Email " + param + " was not found."); 
		return this;
		
	}
}
