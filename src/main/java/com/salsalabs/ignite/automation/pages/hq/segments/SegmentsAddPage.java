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
	Button saveButton = new ButtonImpl("//button//span[text()='Save Segment']", "Save button");
	Button cutomSegmentButton = new ButtonImpl("//a[contains(text(),'Custom')]" , "Custom  Segment button");
	Button addRuleSet = new ButtonImpl("//button[@class='tiny button ng-binding']", "Add rule set");
    Button addAnotherRule = new ButtonImpl ("//a[@title='Add another rule']", "Add another rule Button");
	Panel ruleSet = new PanelImpl("//div[@class='ruleSet']/descendant::div[@class='row rule ng-scope']", "Rule set");
	Button addRule = new ButtonImpl("a[@title='Add another rule']", "Add rule set");
	Button nextSegmentRule = new ButtonImpl("//button[@class='saveBarBtn button primary ng-scope ng-isolate-scope']", "nextSegmentRule button");
	TextBox serachAddSupporterManuallyField = new TextBoxImpl("//h2[.='Add these specific supporters']/ancestor::div[@class='row ng-scope']//input[@class='search ng-isolate-scope ng-pristine ng-valid']", "Manually add supporter search field");
	Button searchAddSupporterManuallyButton = new ButtonImpl("//h2[contains(text(),'Add these')]/ancestor::div[@class='large-6 columns']//div[@class='audience-search ng-isolate-scope']//button", "Add manually search button");
	TextBox serachExcludeSupporterManuallyField = new TextBoxImpl("//h2[contains(text(),'add')]/following::div//input", "Manually add supporter search field");
	Button searchExcludeSupporterManuallyButton = new ButtonImpl("//h2[contains(text(),'add')]/ancestor::div[@class='large-6 columns']//div[@class='audience-search ng-isolate-scope']//button", "Add manually search button");
	
	public SegmentsPage createNewCustomSegment(String segmentName,
			                                   String addIncludeRule,
											   String addExcludeRule,
											   String criteriaForExcludeSupporterManually,
											   String criteriaForAddSupporterManually) {
		segmentNameField.type(segmentName);
		clickNextSegmentRules();
		addRule(addIncludeRule);
		addExclureRule(addExcludeRule);
		addSupporterManually(criteriaForAddSupporterManually);	
		excludeSupporterManually(criteriaForExcludeSupporterManually);
		saveButton.click();
		cutomSegmentButton.click();
		return new SegmentsPage();
	}	

	private void addRule(String ruleCriteria) {
		String rules[] = CommonUtils.getArrayFromStringBySymbol(ruleCriteria, ":");
		int amountExistedRules = ruleSet.getElementsCount();
		
		int amountOfSup = Integer.valueOf(CommonUtils.getProperty(PropertyName.AMOUNT_OF_SUPPORTERS));
		
		for (int i = 1 + amountExistedRules; i <= rules.length + amountExistedRules -4; i++) {
			addRuleSet.click();							
			selectCategory(rules[0], i);			
			selectChooseMetric(rules[1], i , 1);
			selectOperator(rules[2], i , 1);
			enterRuleCriteria(rules[3], i, 1);
			
			if (rules.length == 5) {
				amountOfSup = Integer.valueOf(rules[4]);
			
			}
			verifyAmountOfSupporters(i, amountOfSup, 1);
			
		}
	}
	
	private void addExclureRule(String ruleCriteria) {
		String rules[] = CommonUtils.getArrayFromStringBySymbol(ruleCriteria, ":");
		int amountExistedRules = ruleSet.getElementsCount();
		
		int amountOfSup = Integer.valueOf(CommonUtils.getProperty(PropertyName.AMOUNT_OF_SUPPORTERS));
		
		for (int i =  amountExistedRules; i <= rules.length + amountExistedRules -5; i++) {
			addAnotherRule(i);						
			selectCategory(rules[0], i);			
			selectChooseMetric(rules[1], i, 2);
			selectOperator(rules[2], i, 2);
			enterRuleCriteria(rules[3], i, 2);
			
			if (rules.length == 5) {
				amountOfSup = Integer.valueOf(rules[4]);
			
			}
			verifyAmountOfSupporters(i, amountOfSup, 2);
			
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
		selectSupporterFromList("admin");
		sleep(2);
	
	}

	
	private void verifyAmountOfSupporters(int ruleSetNumber, Integer amountOfSupporters, int ruleRow) {
		String checkRuleButtonPath = ruleSet.getChildElementPath("button[contains(text(),'Add')]"+"["+ ruleRow + "]" , ruleSetNumber);
		String amountSupportersLabel = ruleSet.getChildElementPath("span[contains(@class,'ruleCalc')]"+"["+ ruleRow + "]", ruleSetNumber);
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
	

	private void selectChooseMetric(String metric, int ruleSetNumber , int ruleRow) {
		String pathToDropDown = ruleSet.getChildElementPath("div[@class='row ng-scope']"+"["+ruleRow+ "]" , ruleSetNumber);  
		String extendButtonPath = ruleSet.getChildElementPath("span[text()='Choose metric...']", ruleSetNumber);
		new DropDownImpl(pathToDropDown, extendButtonPath, "Category").selectByLabel(metric);
	}

	private void selectOperator(String operator, int ruleSetNumber, int ruleRow ) {
		String pathToDropDown = ruleSet.getChildElementPath("div[@class='row ng-scope']"+"["+ruleRow+ "]", ruleSetNumber);
		String extendButtonPath = ruleSet.getChildElementPath("span[text()='Choose operator...']", ruleSetNumber);
		new DropDownImpl(pathToDropDown, extendButtonPath, "Category").selectByLabel(operator);
	}
	
	private void enterRuleCriteria(String value, int ruleSetNumber, int row) {
	 new TextBoxImpl(ruleSet.getChildElementPath("div[@class='row ng-scope']"+"["+row+ "]"+"//input", ruleSetNumber), "Rule criteria, set N" + ruleSetNumber).type(value);
	}
	
	private void selectSupporterFromList(String supEmail) {
		Button ruleAdd = new ButtonImpl("//ul[@class='f-dropdown']/descendant::div/descendant::*[contains(text(),'" + supEmail + "')]", "Supporter in the list");
		ruleAdd.scrollIntoView();
		ruleAdd.click();
	}
	
	private void addAnotherRule( int ruleNumber){ 
		 new ButtonImpl((ruleSet.getPath()+"["+ruleNumber+"]"+addAnotherRule.getPath()), "Plus sign").click();
		 
		
	}
	
	public void clickNextSegmentRules(){
		nextSegmentRule.click();
		sleep(4);
	}
	
	
	
}
