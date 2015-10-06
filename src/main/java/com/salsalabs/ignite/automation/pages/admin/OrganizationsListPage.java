package com.salsalabs.ignite.automation.pages.admin;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.Label;
import com.salsalabs.ignite.automation.elements.SelectBox;
import com.salsalabs.ignite.automation.elements.Table;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.LabelImpl;
import com.salsalabs.ignite.automation.elements.impl.SelectBoxImpl;
import com.salsalabs.ignite.automation.elements.impl.TableImpl;
import com.salsalabs.ignite.automation.pages.hq.InviteCompletionPage;

public class OrganizationsListPage extends HomePageAdmin {

	Table organizationTable = new TableImpl("//table[@role='grid']", "Table with organizations");
	SelectBox orgClassification = new SelectBoxImpl("//span[text()='Classification']/../select", "Classification");
	Button createNewOrg = new ButtonImpl("//button[contains(@onclick,'orgNew')]", "Create New Org");
	Button invites = new ButtonImpl("//button[contains(@id,'activeOrgTable:0')]/span[text()='Invites']", "Invites");
	Button sendInvite = new ButtonImpl("//button[contains(@id,'adminTable:0')]/span[text()='Send Invitation']", "Send Invitation");
	Button closeModal = new ButtonImpl("//a[contains(@class,'ui-dialog-titlebar-close')]", "Close");
	Label infoSummary = new LabelImpl("//span[contains(@class,'ui-messages-info-summary')]", "Info");
	Label sending = new LabelImpl("//span[text()='Sending...']", "Sending...");

	public OrganizationsListPage checkOrganizationExists(String orgName) {
		orgClassification.selectByLabel("Internal-Test");
		sleep(5);
		verifier.verifyTrue(organizationTable.getRowsNumberByValue(orgName) >= 0, "Organization " + orgName + " was not found.");
		return this;
	}
	
	public AddNewOrgPage clickCreateNewOrg() {
		createNewOrg.click();
		switchToFrame("//iframe[@name='newOrg']");
		return new AddNewOrgPage();
	}
	
	public InviteCompletionPage openInvitationURL() {
		invites.click();
		sleep(5);
		sendInvite.click();
		sending.waitForNotVisible(240);
		closeModal.click();
		String text = infoSummary.getText();
		open(text.substring(18, text.length()));
		sleep(5);
		return new InviteCompletionPage();
	}
}
