package com.salsalabs.ignite.automation.pages.hq.supporters;

import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.DropDown;
import com.salsalabs.ignite.automation.elements.Label;
import com.salsalabs.ignite.automation.elements.Table;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.DropDownImpl;
import com.salsalabs.ignite.automation.elements.impl.LabelImpl;
import com.salsalabs.ignite.automation.elements.impl.SelectBoxImpl;
import com.salsalabs.ignite.automation.elements.impl.TableImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;
import com.salsalabs.ignite.automation.pages.hq.AudiencePage;

public class SupporterQueryBuilderPage extends AudiencePage {
	Button deleteRuleButton = new ButtonImpl("//button[@ng-click='deleteRule()']", "Delete rule button");
	Button createNewQueryButton = new ButtonImpl("//*[contains(text(),'Create New Query')]", "Query tab");
	TextBox enterValueInput = new TextBoxImpl("//input[@placeholder='Enter Value']", "Enter Value input field");
	Button showResultsButton = new ButtonImpl("//button[@class='button primary']", "Show Results Button");
	TextBox errorMessage = new TextBoxImpl("//*[contains(text(),'Please correct')]", "Unable to run Query message");
	public Button newRuleButton = new ButtonImpl("	//button[contains(text(), 'New Rule')]", "Show Results Button");
	TextBox dateBefore = new TextBoxImpl("//input[@name='dateBefore']", "DateBefore Value");
	TextBox dateAfter = new TextBoxImpl("//input[@name='dateAfter']", "DateAfter  Value");
	TextBox calendar = new TextBoxImpl("//input[@type='text']", "Calendar Value");
	public Button closeSelectBoxOption = new ButtonImpl("//multi-select[contains(@ng-if, 'MULTI')]//span[@class='close']", "Close select Box Option");
	
	public SupporterQueryBuilderPage createNewQuery() {
		createNewQueryButton.click();
		sleep(1);
		return new SupporterQueryBuilderPage();
	}

	public SupporterQueryBuilderPage pickUpFirstLevelRuleOption(String firstLevelRule) {
		if (deleteRuleButton.isDisplayed()) {
			deleteRuleButton.click();
		}
		DropDownImpl firstRule = new DropDownImpl("//ul[@class='dropdown-list']/li",
				"//*[contains(text(),'Please select one')]", "First Rule DropdownList");
		if (firstRule.isNotExists()) {
			firstRule.waitForElementPresence();
		}
		firstRule.selectByLabel(firstLevelRule);
		return this;
	}

	public SupporterQueryBuilderPage pickUpNextLevelRuleOption(String secondLevelRule,
			String extedndedDropDownLabel) {
		DropDown firstRule = new DropDownImpl("//ul[@class='dropdown-list']/li",
				"//*[contains(text(),'" + extedndedDropDownLabel + "')]", "First Rule DropdownList");
		firstRule.selectByLabel(secondLevelRule);
		return this;
	}

	public SupporterQueryBuilderPage pickUpTheRuleOperator(String label) {
		SelectBoxImpl ruleOption = new SelectBoxImpl("//select[@ng-model='rule.operation']", "Rule Option");
		ruleOption.selectByLabel(label);
		return this;
	}
	
	public SupporterQueryBuilderPage pickUpValueWhichComesAfterRuleOperator(String label) {
		SelectBoxImpl ruleOption = new SelectBoxImpl("//select[contains(@ng-if, 'SINGLE_OPTION')]", "Rule Option");
		ruleOption.selectByLabel(label);
		return this;
	}
	
	public SupporterQueryBuilderPage pickUpCalendarOperator(String label) {
		SelectBoxImpl ruleOption = new SelectBoxImpl("	//select[contains(@ng-show, 'CALENDAR')]", "Calendar Option");
		ruleOption.selectByLabel(label);
		return this;
	}
	public SupporterQueryBuilderPage pickUpDatePeriod(String label) {
		SelectBoxImpl ruleOption = new SelectBoxImpl("//select[@name='datePeriod']", "Date Period");
		ruleOption.selectByLabel(label);
		return this;
	}
	
	public SupporterQueryBuilderPage specifyCalendarValue(String calendarValue, String dateBeforeValue, String dateAfterValue) {
		if(calendarValue==null){
			dateBefore.type(dateBeforeValue);
			dateAfter.type(dateAfterValue);
		}else{
			calendar.type(calendarValue);
		}
		return this;
	}
	
	public SupporterQueryBuilderPage specifyCalendarValueForRelativeOperator(String inputValue) {
			TextBox input = new TextBoxImpl("//input[@type='number']", "inputValue  for relative Operator");
			input.clear();
			input.type(inputValue);
		return this;
	}
	

	public SupporterQueryBuilderPage enterValue(String value) {
		enterValueInput.type(value);
		return this;
	}
	

	public SupporterQueryBuilderPage checkErrorMessage() {
		try {
			if(errorMessage.isDisplayed()){
				throw new AssertionError("Unable to Run Query");
			}
			
		} finally {
			if(errorMessage.isDisplayed()){
				Button closError = new ButtonImpl("	//a[@ng-click='clearErrors()']", "Close Error Button");
				closError.click();
			}
		}

		return this;
	}

	public SupporterQueryBuilderPage checkShowResultButtonIsDisplayed() {
		if(showResultsButton.isNotExists()){
			sleep(2);
		}
	if(showResultsButton.isNotExists()){
		logger.info("Tests failed because  Show Results button does not displays despite the fact that all rules are selected");
		 throw new AssertionError("Show Result buttons does not display");
	}
	return this;
	} 
	
	public SupporterQueryBuilderPage clickShowTheResults() {
		showResultsButton.click();
		showResultsButton.fluentWaitForElementPresenceIgnoringExceptions();
		return this;
	}
	
	public SupporterQueryBuilderPage checkSupporterEmailInTable( String expectedEmail) {
		Table resultTable = new TableImpl("//table", "Result Table");
		resultTable.scrollIntoView();
		TextBox noResult = new TextBoxImpl("//div[contains(text(), 'No results')]", "No Result");
		if(noResult.isNotExists()){
			String email =resultTable.getCellValueUsingAllHeadersMethod(1, "Email");
			logger.info("Supporter is found with Email" + " " + email);
			verifier.verifyEquals(email, expectedEmail, "Found supporter has Inorect Email", true);	;
		}else {
			throw new AssertionError("No results were found in the table");
		}
				return this;
	}
	
	public SupporterQueryBuilderPage checkNumberOfRecordsInTable(String expectedNumberOfRows) {
		Table resultTable = new TableImpl("//table", "Result Table");
		resultTable.scrollIntoView();
		TextBox noResult = new TextBoxImpl("//div[contains(text(), 'No results')]", "No Result");
		if(noResult.isNotExists()){
			String listOFRows = String.valueOf(resultTable.findElementsByXpath("//table[contains(@id, 'JColResizer')]/tbody/tr").size());
			verifier.verifyEquals(listOFRows, expectedNumberOfRows, "Found incorrect number of supporters", true);
		}else {
			throw new AssertionError("No results were found in the table");
		}
				return this;
	}
	
	public SupporterQueryBuilderPage checkNoResultsIsReturned() {
		Table resultTable = new TableImpl("//table", "Result Table");
		resultTable.scrollIntoView();
		TextBox noResult = new TextBoxImpl("//div[contains(text(), 'No results')]", "No Result");
		if(noResult.isNotExists()){
			throw new AssertionError("No results expected but found a value");
		}else {
			verifier.verifyElementIsDisplayed(true, noResult);
		}
				return this;
	}
	
	public SupporterQueryBuilderPage checkThatShowResultBottonIsNotDisplayedIfSearchValueIsCleared() {
		enterValueInput.scrollIntoView();
		enterValueInput.clear();
		sleep(1);
		if(showResultsButton.isNotExists()){
			logger.info("Show result Button is not displayed");
		}else {
			throw new AssertionError("Show Result Button Displays But should not, because the input field is erased");
		}
		return this;
	}
	
	public SupporterQueryBuilderPage checkThatShowResultBottonIsNotDisplayedIfSelectOptionIsCleared() {
		closeSelectBoxOption.click();
		sleep(1);
		if(showResultsButton.isNotExists()){
			logger.info("Show result Button is not displayed");
		}else {
			throw new AssertionError("Show Result Button Displays But should not, because The select box options  is erased");
		}
		return this;
	}
	
	
	public SupporterQueryBuilderPage checkThatShowResultBottonIsNotDisplayedIfSearchCalendarValueIsCleared(boolean twoOperators) {
		if(twoOperators){
			dateBefore.scrollIntoView();
			dateBefore.clear();
			sleep(1);
			if(showResultsButton.isNotExists()){
				logger.info("Show result Button is not displayed");
			}else{
				throw new AssertionError("Show Result Button Displays But should not, because the input field is erased");
			}
		} else{
			calendar.scrollIntoView();
			calendar.clear();
			sleep(1);
			if(showResultsButton.isNotExists()){
				logger.info("Show result Button is not displayed");	
			}else{
				throw new AssertionError("Show Result Button Displays But should not, because the input field is erased");
			}
		}
		
		return this;
	}
	

}
