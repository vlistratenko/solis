package pages.hq.emails;

import core.util.CommonUtils;
import core.util.PropertyName;
import elements.Button;
import elements.Table;
import elements.impl.ButtonImpl;
import elements.impl.TableImpl;
import pages.hq.HomePage;


public class EmailBlastsPage extends HomePage{
	Button addEmailButton = new ButtonImpl("//button[@ng-click='createEmail()']", "Create email");
	Table emailsTable = new TableImpl("//table/descendant::a[text()='Reference Name']/ancestor::table", "Table with emails");
	Button emailNameLink = new ButtonImpl("//span[text()='" + CommonUtils.getProperty(PropertyName.EMAIL_BLAST_NAME) + "']", "Email blast link");
	
	public AddEmailsPage_SetupTab openAddEmailPage() {
		if (feedBackDialogPanel.isDisplayed()) {
			closeFeedbackDialog.click();
		}
		addEmailButton.click();
		return new AddEmailsPage_SetupTab();
	}

	public EmailBlastsPage checkEmailExists(String param) {
		verifier.verifyEquals(emailsTable.isValueExists(param)>0, true, "Email " + param + " was not found."); 
		return this;
		
	}
	
	public EmailBlastDetailsPage openEmailBlastDetailsPage(String emailName) {
		emailNameLink.changePath(CommonUtils.getProperty(PropertyName.EMAIL_BLAST_NAME), emailName);
		sleep(3000);
		emailNameLink.click();
		return new EmailBlastDetailsPage();
		
	}
	
	public EmailBlastDetailsPage openEmailBlastDetailsPage() {
		emailNameLink.click();
		return new EmailBlastDetailsPage();
		
	}
}
