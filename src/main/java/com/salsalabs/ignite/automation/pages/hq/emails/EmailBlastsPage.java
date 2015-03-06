package com.salsalabs.ignite.automation.pages.hq.emails;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.Table;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.TableImpl;
import com.salsalabs.ignite.automation.pages.hq.HomePage;


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
		verifier.verifyTrue(emailsTable.isValueExists(param) > 0, "Email " + param + " was not found.");
		return this;
		
	}
	
	public EmailBlastDetailsPage openEmailBlastDetailsPage(String emailName) {
		emailNameLink.changePath(CommonUtils.getProperty(PropertyName.EMAIL_BLAST_NAME), emailName);
		sleep(3);
		emailNameLink.click();
		return new EmailBlastDetailsPage();
		
	}
	
	public EmailBlastDetailsPage openEmailBlastDetailsPage() {
		emailNameLink.click();
		return new EmailBlastDetailsPage();
		
	}
}
