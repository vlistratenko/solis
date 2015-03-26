package com.salsalabs.ignite.automation.pages.hq.manage;


import org.apache.commons.lang3.RandomStringUtils;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.CheckBox;
import com.salsalabs.ignite.automation.elements.Element;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.CheckBoxImpl;
import com.salsalabs.ignite.automation.elements.impl.LabelImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;

public class CustomFieldsPage extends ManagePage {

	private Button createCFBtn = new ButtonImpl("//*[@id='customFieldsConfiguration']/div/ng-form/div/div/div[2]/div/div/button", "Create a Custom Field", true);
	private Button toStep2Btn = new ButtonImpl("//*[@id='cf_form']/div[3]/span[1]/a[2]", "Continue to Step 2: Field Details »", true);
	private TextBox fieldNameTxtBox = new TextBoxImpl("//*[@id='cfnew_name']", "Field Name", true);
	private Button toStep3Btn = new ButtonImpl("//*[@id='btnModalStep2']", "Continue to Step 3: Field Settings »", true);
	private TextBox descriptionTxtBox = new TextBoxImpl("//*[@id='cf_form']/div[2]/div[2]/div[2]/div/textarea", "Description", true);
	private Button createFieldBtn = new ButtonImpl("//*[@id='cf_form']/div[3]/span[3]/a[3]", "Create Field! »", true);
	private TextBox cfValue = new TextBoxImpl("//*[@id='cf_form']/div[2]/div[3]/div[1]/div/div[2]/span/span/input", "Text", true);
	private Button customFieldButton;
	
	public void createCustomFields() {
		for (CustomFieldType cf : CustomFieldType.values()) {
			createCustomField(cf);
		}
	}
	
	public void createCustomField(String customFieldType) {
		createCustomField(CustomFieldType.valueOf(customFieldType));
	}
	
	private void createCustomField(CustomFieldType cfType) {
		createCFBtn.clickJS();
		customFieldButton = new ButtonImpl(cfType.getXpath(), cfType.name(), true);
		customFieldButton.clickJS();
		toStep2Btn.clickJS();
		String fieldName = cfType.name() + "_field_" + RandomStringUtils.randomAlphabetic(10);
		fieldNameTxtBox.type(fieldName);
		descriptionTxtBox.type("Description");
		toStep3Btn.clickJS();
		if (cfType.getGhostText() != null) {
			cfValue.type(cfType.getGhostText());
		}
		if (cfType.equals(CustomFieldType.SingleChoice)) {
			constructSingleChoiceOptions();
		}
		createFieldBtn.clickJS();
		sleep(2);
		Element field = new LabelImpl("//*[text()='" + fieldName + "']", "");
		verifier.verifyElementIsDisplayed(field);
	}
	
	private void constructSingleChoiceOptions() {
		CheckBox noSelectionCheckBox = new CheckBoxImpl("//*[@id='choiceFieldNoSelected']/li/div/div[1]/input", "No Selection");
		noSelectionCheckBox.check();
		TextBox newOption = new TextBoxImpl("//*[@id='cf_form']/div[2]/div[3]/div[3]/div/div[1]/div[3]/div[1]/input", "New option name");
		newOption.type("New Option");
		Button addBtn = new ButtonImpl("//*[@id='cf_form']/div[2]/div[3]/div[3]/div/div[1]/div[3]/div[2]/button", "Add Button");
		addBtn.clickJS();
	}
	
	enum CustomFieldType {
		TextBox("//*[@id='cf_form']/div[2]/div[1]/ul/li[1]/a/p", "Ghost Text"), 
		Number("//*[@id='cf_form']/div[2]/div[1]/ul/li[2]/a/p", "Ghost Number Text"), 
		YesNo("//*[@id='cf_form']/div[2]/div[1]/ul/li[3]/a/p", null), 
		DateTime("//*[@id='cf_form']/div[2]/div[1]/ul/li[4]/a/p", null), 
		SingleChoice("//*[@id='cf_form']/div[2]/div[1]/ul/li[5]/a/p", null);
		
		private String xpath;
		private String ghostText;
		
		CustomFieldType(String xpath, String ghostText) {
			this.xpath = xpath;
			this.ghostText = ghostText;
		}
		public String getXpath() { return this.xpath; }
		public String getGhostText() { return this.ghostText; }
	}

}