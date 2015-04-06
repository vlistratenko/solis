package com.salsalabs.ignite.automation.pages.hq.supporters;

import com.salsalabs.ignite.automation.common.Supporter;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.DropDown;
import com.salsalabs.ignite.automation.elements.Table;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.DropDownImpl;
import com.salsalabs.ignite.automation.elements.impl.TableImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;
import com.salsalabs.ignite.automation.pages.hq.AudiencePage;

public class SupportersPage extends AudiencePage{

	Button addSupporterButton = new ButtonImpl("//button[text()='Add a New Supporter']", "Add supporter");
	Table supportersTable = new TableImpl("//table-list/div[2]/div/div/table", "Table with supporters");
	TextBox searchField = new TextBoxImpl("//input[@name='query']", "Search");
	Button doSearchButton = new ButtonImpl("//a[contains(@ng-click,'processing.search')]", "Do search");
	DropDown addSupporterDropDown = new DropDownImpl("//*[@id='dashboard']/div[2]/div/div/div/div[3]/div[1]/div[2]/div/ul/li", "//*[@id='dashboard']/div[2]/div/div/div/div[3]/div[1]/div[2]/div/button", "Add Supporters");
	
	public SupportersAddPage openAddSupporterPage() {
		if (feedBackDialogPanel.isDisplayed()) {
			closeFeedbackDialog.click();
		}
		addSupporterButton.click();
		return new SupportersAddPage();
	}
	
	@SuppressWarnings("unused")
	public SupportersPage verifySupporterOnTopOfTable(Supporter supporter) {
		String email = supportersTable.getCellValue(1, "Email address");
		String firstName = supportersTable.getCellValue(1, "First name");
		String lastName = supportersTable.getCellValue(1, "Last name");
		String state = supportersTable.getCellValue(1, "State");
		String zipCode = supportersTable.getCellValue(1, "Zip code");
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
		addSupporterDropDown.selectByLabelJS("Add a Single Supporter");
		return new SupportersAddPage();
	}

	
}
