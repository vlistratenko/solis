package pages.HQ.Segments;

import objects.Button;
import objects.DropDown;
import objects.Label;
import objects.Panel;
import objects.TextBox;
import selenium.CommonUtils;

public class SegmentsAddPage extends SegmentsPage{

	TextBox segmentNameField = new TextBox("//input[@name='name']", "Segment name");
	Button saveButton = new Button("//button/span[text()='Save Segment']", "Save button");
	Button addRuleSet = new Button("//button[contains(text(), 'Who should we add?')]", "Add rule set");
	Panel ruleSet = new Panel("//div[@class='ruleSet']/descendant::div[@class='row rule ng-scope']", "Rule set");
	Button addRule = new Button("a[@title='Add another rule']", "Add rule set");
	TextBox serachAddSupporterManuallyField = new TextBox("//h2[.='I also want to add a few specific folks.']/ancestor::div[@class='row']/following-sibling::div[@class='row']/descendant::input", "Manually add supporter search field");
	Button searchAddSupporterManuallyButton = new Button("//h2[.='I also want to add a few specific folks.']/ancestor::div[@class='row']/following-sibling::div[@class='row']/descendant::a[@ng-click='doSearch()']", "Add manually search button");
	TextBox serachExcludeSupporterManuallyField = new TextBox("//h2[.=\"I'd like to leave out a few individuals, too.\"]/ancestor::div[@class='row']/following-sibling::div[@class='row']/descendant::input", "Manually add supporter search field");
	Button searchExcludeSupporterManuallyButton = new Button("//h2[.=\"I'd like to leave out a few individuals, too.\"]/ancestor::div[@class='row']/following-sibling::div[@class='row']/descendant::a[@ng-click='doSearch()']", "Add manually search button");
	
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
		int amountOfSup = Integer.valueOf(CommonUtils.getProperty("amountOfSupporters"));
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
		sleep(5000);
		selectSupporterFromList("sup");
	}
	
	private void excludeSupporterManually(String supporter) {
		serachExcludeSupporterManuallyField.type(supporter);
		searchExcludeSupporterManuallyButton.click();
		sleep(5000);
		selectSupporterFromList("sup");
	}

	
	private void verifyAmountOfSupporters(int ruleSetNumber, Integer amountOfSupporters) {
		String checkRuleButtonPath = ruleSet.getChildElementPath("button[contains(text(),'check')]", ruleSetNumber);
		String amountSupportersLabel = ruleSet.getChildElementPath("span[contains(@class,'ruleCalc')]", ruleSetNumber);
		new Button(checkRuleButtonPath, "Check rule").click();
		sleep(8000);
		Integer actualAmount = Integer.valueOf(new Label(amountSupportersLabel, "Label with supporters in rule").getText());
		verify( actualAmount >= amountOfSupporters, true, "Wrong amount of supporters");
		
	}
	
		private void selectCategory(String category, int ruleSetNumber) {
		String pathToDropDown = ruleSet.getChildElementPath("div[@class='custom dropdown ng-isolate-scope']", ruleSetNumber);
		String extendButtonPath = ruleSet.getChildElementPath("span[text()='Category']", ruleSetNumber);
		new DropDown(pathToDropDown, extendButtonPath, "Category").selectByLabel(category);
	}
		
	
	/*private void selectCategory(String category, int ruleSetNumber) {
			String extendButtonPath = ruleSet.getChildElementPath("span[text()='Category']", ruleSetNumber);
		new DropDown(extendButtonPath, "Category").selectByLabel(category);
	}*/
	
		private void selectChooseMetric(String metric, int ruleSetNumber) {
			String pathToDropDown = ruleSet.getChildElementPath("div[@class='custom dropdown']", ruleSetNumber);
			String extendButtonPath = ruleSet.getChildElementPath("span[text()='Choose metric...']", ruleSetNumber);
			new DropDown(pathToDropDown, extendButtonPath, "Category").selectByLabel(metric);
		}
	
	private void selectOperator(String operator, int ruleSetNumber) {
		String pathToDropDown = ruleSet.getChildElementPath("div[@class='custom dropdown ng-isolate-scope']", ruleSetNumber);
		String extendButtonPath = ruleSet.getChildElementPath("span[text()='Choose operator...']", ruleSetNumber);
		new DropDown(pathToDropDown, extendButtonPath, "Category").selectByLabel(operator);
	}
	
	private void enterRuleCriteria(String value, int ruleSetNumber) {
		new TextBox(ruleSet.getChildElementPath("input[@name='operator_value']", ruleSetNumber), "Rule criteria, set N" + ruleSetNumber).type(value);;
	}
	
	private void selectSupporterFromList(String supEmail) {
		new Button("//ul[@class='f-dropdown right']/descendant::div/descendant::*[contains(text(),'" + supEmail + "')]", "Supporter in the list").click();
	}
	
	
	
}
