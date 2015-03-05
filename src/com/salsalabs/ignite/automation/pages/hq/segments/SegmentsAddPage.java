package com.salsalabs.ignite.automation.pages.hq.segments;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.Panel;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.DropDownImpl;
import com.salsalabs.ignite.automation.elements.impl.LabelImpl;
import com.salsalabs.ignite.automation.elements.impl.PanelImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;

public class SegmentsAddPage extends SegmentsPage{

	TextBox segmentNameField = new TextBoxImpl("//input[@name='name']", "Segment name");
	Button saveButton = new ButtonImpl("//button/span[text()='Save Segment']", "Save button");
	Button addRuleSet = new ButtonImpl("//button[contains(text(), 'Who should we add?')]", "Add rule set");
	Panel ruleSet = new PanelImpl("//div[@class='ruleSet']/descendant::div[@class='row rule ng-scope']", "Rule set");
	Button addRule = new ButtonImpl("a[@title='Add another rule']", "Add rule set");
	TextBox serachAddSupporterManuallyField = new TextBoxImpl("//h2[.='I also want to add a few specific folks.']/ancestor::div[@class='row']/following-sibling::div[@class='row']/descendant::input", "Manually add supporter search field");
	Button searchAddSupporterManuallyButton = new ButtonImpl("//h2[.='I also want to add a few specific folks.']/ancestor::div[@class='row']/following-sibling::div[@class='row']/descendant::a[@ng-click='doSearch()']", "Add manually search button");
	TextBox serachExcludeSupporterManuallyField = new TextBoxImpl("//h2[.=\"I'd like to leave out a few individuals, too.\"]/ancestor::div[@class='row']/following-sibling::div[@class='row']/descendant::input", "Manually add supporter search field");
	Button searchExcludeSupporterManuallyButton = new ButtonImpl("//h2[.=\"I'd like to leave out a few individuals, too.\"]/ancestor::div[@class='row']/following-sibling::div[@class='row']/descendant::a[@ng-click='doSearch()']", "Add manually search button");
	
	public SegmentsPage createNewCustomSegment(String segmentName,
											   String addIncludeRule,
											   String addExcludeRule,
											   String criteriaForExcludeSupporterManually,
											   String criteriaForAddSupporterManually) {
		segmentNameField.type(segmentName);
		addRule(addIncludeRule);
		addRule(addExcludeRule);
		excludeSupporterManually(criteriaForExcludeSupporterManually);
		addSupporterManually(criteriaForAddSupporterManually);		
		saveButton.click();
		return new SegmentsPage();
	}	

	private void addRule(String ruleCriteria) {
		String rules[] = CommonUtils.getArrayFromStringBySymbol(ruleCriteria, ":");
		int amountExistedRules = ruleSet.getElementsCount();
		int amountOfSup = Integer.valueOf(CommonUtils.getProperty(PropertyName.AMOUNT_OF_SUPPORTERS));
		for (int i = 1 + amountExistedRules; i <= rules.length + amountExistedRules; i++) {
			String criterias[] = CommonUtils.getArrayFromStringBySymbol(rules[i-1-amountExistedRules], ":");
			addRuleSet.click();							
			selectCategory(criterias[0], i);			
			selectChooseMetric(criterias[1], i);
			selectOperator(criterias[2], i);
			enterRuleCriteria(criterias[3], i);	
			
			if (criterias.length == 5) {
				amountOfSup = Integer.valueOf(criterias[4]);
			}
			verifyAmountOfSupporters(i, amountOfSup);
		}
	}
	
	private void addSupporterManually(String supporter) {
		serachAddSupporterManuallyField.type(supporter);
		searchAddSupporterManuallyButton.click();
		sleep(5);
		selectSupporterFromList("sup");
	}
	
	private void excludeSupporterManually(String supporter) {
		serachExcludeSupporterManuallyField.type(supporter);
		searchExcludeSupporterManuallyButton.click();
		sleep(5);
		selectSupporterFromList("sup");
	}

	
	private void verifyAmountOfSupporters(int ruleSetNumber, Integer amountOfSupporters) {
		String checkRuleButtonPath = ruleSet.getChildElementPath("button[contains(text(),'check')]", ruleSetNumber);
		String amountSupportersLabel = ruleSet.getChildElementPath("span[contains(@class,'ruleCalc')]", ruleSetNumber);
		new ButtonImpl(checkRuleButtonPath, "Check rule").click();
		sleep(8);
		Integer actualAmount = Integer.valueOf(new LabelImpl(amountSupportersLabel, "Label with supporters in rule").getText());
		verifier.verifyEquals( actualAmount >= amountOfSupporters, true, "Wrong amount of supporters");
		
	}

	private void selectCategory(String category, int ruleSetNumber) {
		String pathToDropDown = ruleSet.getChildElementPath("div[@class='custom dropdown ng-isolate-scope']", ruleSetNumber);
		String extendButtonPath = ruleSet.getChildElementPath("span[text()='Category']", ruleSetNumber);
		new DropDownImpl(pathToDropDown, extendButtonPath, "Category").selectByLabel(category);
	}

	private void selectChooseMetric(String metric, int ruleSetNumber) {
		String pathToDropDown = ruleSet.getChildElementPath("div[@class='custom dropdown']", ruleSetNumber);
		String extendButtonPath = ruleSet.getChildElementPath("span[text()='Choose metric...']", ruleSetNumber);
		new DropDownImpl(pathToDropDown, extendButtonPath, "Category").selectByLabel(metric);
	}

	private void selectOperator(String operator, int ruleSetNumber) {
		String pathToDropDown = ruleSet.getChildElementPath("div[@class='custom dropdown ng-isolate-scope']", ruleSetNumber);
		String extendButtonPath = ruleSet.getChildElementPath("span[text()='Choose operator...']", ruleSetNumber);
		new DropDownImpl(pathToDropDown, extendButtonPath, "Category").selectByLabel(operator);
	}
	
	private void enterRuleCriteria(String value, int ruleSetNumber) {
		new TextBoxImpl(ruleSet.getChildElementPath("input[@name='operator_value']", ruleSetNumber), "Rule criteria, set N" + ruleSetNumber).type(value);;
	}
	
	private void selectSupporterFromList(String supEmail) {
		new ButtonImpl("//ul[@class='f-dropdown right']/descendant::div/descendant::*[contains(text(),'" + supEmail + "')]", "Supporter in the list").click();
	}
	
	
	
}
