package com.salsalabs.ignite.automation.pages.hq.supporters;

import com.salsalabs.ignite.automation.common.Supporter;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.Table;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.TableImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;
import com.salsalabs.ignite.automation.pages.hq.AudiencePage;

public class SupportersPage extends AudiencePage{

	Button AddSupporterButton = new ButtonImpl("//button[text()='Add a New Supporter']", "Add supporter");
	Table supportersTable = new TableImpl("//table-list/div[2]/div/div/table", "Table with supporters");
	TextBox searchField = new TextBoxImpl("//input[@name='query']", "Search");
	Button doSearchButton = new ButtonImpl("//a[contains(@ng-click,'processing.search')]", "Do search");
	
	public SupportersAddPage openAddSupporterPage() {
		if (feedBackDialogPanel.isDisplayed()) {
			closeFeedbackDialog.click();
		}
		AddSupporterButton.click();
		return new SupportersAddPage();
	}
	
	public SupportersPage verifySupporterOnTopOfTableFull(Supporter supporter) {
		verifier.verifyEquals(supportersTable.getCellValue(1, 2), supporter.getFinalEMAIL());
		verifier.verifyEquals(supportersTable.getCellValue(1, 3), supporter.getFirstName());
		verifier.verifyEquals(supportersTable.getCellValue(1, 4), supporter.getLastName());
		verifier.verifyEquals(supportersTable.getCellValue(1, 5), supporter.getCity());
		verifier.verifyEquals(supportersTable.getCellValue(1, 7), supporter.getZipCode());
		return this;
	}
	
	public SupportersPage verifySupporterOnTopOfTableByEmail(Supporter supporter) {
		return verifySupporterOnTopOfTableByEmail(supporter.getFinalEMAIL());
	}
	
	public SupportersPage verifySupporterOnTopOfTableByEmail(String email) {
		verifier.verifyEquals(supportersTable.getCellValue(1, 2), email);
		return this;
	}
	
//	public SupportersPage verifySupporterIsNotInTable(Supporter supporter) {
//		return verifySupporterIsNotInTable(supporter.getFinalEMAIL());
//	}
//	
//	public SupportersPage verifySupporterIsNotInTable(String email) {
//		verifier.verifyElementIsNotDisplayed(new ButtonImpl("//span[contains(text(), '" + email + "')]", ""));
//		return this;
//	}

	public SupportersPage checkSupporterExists(String param) {
		verifier.verifyEquals(supportersTable.isValueExists(param)>0, true, "Supporter " + param + " was not found."); 
		return this;
		
	}
	
	public SupportersPage checkSupporterNotExists(String param) {
		verifier.verifyEquals(supportersTable.isValueExists(param)>0, false, "Supporter " + param + " was found."); 
		return this;
		
	}

	public SupportersPage searchSupporter(String personEmail) {
		searchField.type(personEmail);
		for (int i = 0; i < 10; i++) {
			doSearchButton.click();
			if (waitConditionBecomesTrue(supportersTable.isValueExists(personEmail)>0, 15)) {
				break;
			}
		}
		return new SupportersPage();
	}

	public SupportersAddPage openSupporterDetailsPage() {
		sleep(3);
		supportersTable.clickInCell(1, 2, "span/span[@ng-click='editItem(item)']");
		return new SupportersAddPage();
	}

	
}
