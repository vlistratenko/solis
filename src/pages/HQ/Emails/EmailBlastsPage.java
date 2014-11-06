package pages.HQ.Emails;

import objects.Button;
import objects.Table;
import pages.HQ.HomePage;
import selenium.CommonUtils;


public class EmailBlastsPage extends HomePage{
	Button addEmailButton = new Button("//button[@ng-click='createEmail()']", "Create email");
	Table emailsTable = new Table("//table/descendant::a[text()='Reference Name']/ancestor::table", "Table with emails");
	Button emailNameLink = new Button("//span[text()='" + CommonUtils.getProperty("emailBlastName") + "']", "Email blast link");
	
	public AddEmailsPage_SetupTab openAddEmailPage() {
		addEmailButton.click();
		return new AddEmailsPage_SetupTab();
	}

	public EmailBlastsPage checkEmailExists(String param) {
		verify(emailsTable.isValueExists(param)>0, true, "Email " + param + " was not found."); 
		return this;
		
	}
	
	public EmailBlastDetailsPage openEmailBlastDetailsPage(String emailName) {
		emailNameLink.changePath(CommonUtils.getProperty("emailBlastName"), emailName);
		sleep(3000);
		emailNameLink.click();
		return new EmailBlastDetailsPage();
		
	}
	
	public EmailBlastDetailsPage openEmailBlastDetailsPage() {
		emailNameLink.click();
		return new EmailBlastDetailsPage();
		
	}
}
