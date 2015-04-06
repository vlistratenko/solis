package com.salsalabs.ignite.automation.pages.hq.supporters;

import com.salsalabs.ignite.automation.common.Supporter;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.Table;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.TableImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;
import com.salsalabs.ignite.automation.pages.hq.AudiencePage;

public class SupportersPage extends AudiencePage {

	Button addSupporterButton = new ButtonImpl("//button[text()='Add a New Supporter']", "Add supporter");
	Table supportersTable = new TableImpl("//table-list/div[2]/div/div/table", "Table with supporters");
	TextBox searchField = new TextBoxImpl("//input[@name='query']", "Search");
	Button doSearchButton = new ButtonImpl("//a[contains(@ng-click,'processing.search')]", "Do search");
	Button openAddSupporterMenuButton = new ButtonImpl("//*[@id='dashboard']/div[2]/div/div/div/div[3]/div[1]/div[2]/div/button", "Add Supporters");
	Button addSingleSupporterBtn = new ButtonImpl("//*[@id='dashboard']/div[2]/div/div/div/div[3]/div[1]/div[2]/div/ul/div[1]/li/a", "Button", true);
	
	@Deprecated
	public SupportersAddPage openAddSupporterPage() {
		if (feedBackDialogPanel.isDisplayed()) {
			closeFeedbackDialog.click();
		}
		addSupporterButton.click();
		return new SupportersAddPage();
	}
	
	public SupportersPage verifySupporterOnTopOfTable(Supporter supporter) {
		verifier.verifyEquals(supporter.getFinalEMAIL(), supportersTable.getCellValue(1, 2), "Supporter was not created (email)");
		verifier.verifyEquals(supporter.getFirstName(), supportersTable.getCellValue(1, 3), "Supporter was not created (first name)");
		verifier.verifyEquals(supporter.getLastName(), supportersTable.getCellValue(1, 4), "Supporter was not created (last name)");
		verifier.verifyEquals(supporter.getCity(), supportersTable.getCellValue(1, 5), "Supporter was not created (city)");
		verifier.verifyEquals(supporter.getZipCode(), supportersTable.getCellValue(1, 7), "Supporter was not created (zip code)");
		return this;
	}

	public SupportersPage checkSupporterExists(String param) {
		verifier.verifyEquals(supportersTable.isValueExists(param) > 0, true, "Supporter " + param + " was not found."); 
		return this;
		
	}
	
	public SupportersPage checkSupporterNotExists(String param) {
		verifier.verifyEquals(supportersTable.isValueExists(param) > 0, false, "Supporter " + param + " was found."); 
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
	
	public SupportersAddPage switchToSupporterAddManually() {
		sleep(5);
		openAddSupporterMenuButton.click();
		sleep(2);
		addSingleSupporterBtn.click();
		sleep(5);
		return new SupportersAddPage();
	}

	
}
