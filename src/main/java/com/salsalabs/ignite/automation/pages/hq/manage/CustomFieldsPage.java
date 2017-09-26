package com.salsalabs.ignite.automation.pages.hq.manage;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.CheckBox;
import com.salsalabs.ignite.automation.elements.DropDown;
import com.salsalabs.ignite.automation.elements.Element;
import com.salsalabs.ignite.automation.elements.Table;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.CheckBoxImpl;
import com.salsalabs.ignite.automation.elements.impl.DropDownImpl;
import com.salsalabs.ignite.automation.elements.impl.LabelImpl;
import com.salsalabs.ignite.automation.elements.impl.TableImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;

public class CustomFieldsPage extends ManagePage {

	private Button createCFBtn = new ButtonImpl("//button[contains (text(), 'Supporter Custom ')]",
			"Create a Supporter Custom Field Button", true);
	private Button nextFieldDetails = new ButtonImpl("//*[@autotest-id='btn_next_step1_custom_field_create']",
			"Next: Field Details » »", true);
	private TextBox fieldName = new TextBoxImpl("//*[@id='name']", "Field Name", true);
	private Button toStep3Btn = new ButtonImpl("//button[@id='btnModalStep2']", "Continue to Step 3: Field Settings »",
			true);
	private TextBox descriptionTxtBox = new TextBoxImpl("//textarea[@name='']", "Description", true);
	private Button createFieldBtn = new ButtonImpl("//*[@autotest-id='custom_field-create_field']", "Create Field! »",
			true);
	private TextBox cfValue = new TextBoxImpl("//input[contains(@placeholder,'Enter')]", "Text", true);
	private Button actionsDropDownLink = new ButtonImpl("//a[contains(text(), 'Actions')]",
			"Create a Supporter Custom Field Button", true);
	private Button deleteButton = new ButtonImpl("//a[contains(text(), 'Actions')]/following-sibling::ul//li/button",
			"Delete Item Button");
	private Button confirmDeleteButton = new ButtonImpl("//span[contains(text(), 'Yes')]/ancestor:: button",
			"Confirm the Delete Button");
	private TextBox newOption = new TextBoxImpl("//input[contains(@placeholder , 'Add')]", "Add an Option Input");
	private Button addBtn = new ButtonImpl("//button[@autotest-id='custom_field-add_new_option_btn']", "Add Button");
	private CheckBox noSelectionCheckBox = new CheckBoxImpl("//input[@autotest-id='custom_field-no_selection_changed']",
			"No Selection checkbox");
	private Table customFieldsTable = new TableImpl("//table", "Custom Fields Table");

	private Button customFieldButton;

	public void createCustomField(String customFieldType) {
		createCustomField(CustomFieldType.valueOf(customFieldType));
	}

	public CustomFieldsPage createCustomField(CustomFieldType cfType) {
		return this.createCustomField(new CustomField(cfType, cfType.name() + "_" + CommonUtils.getUnicName()));
	}

	public CustomFieldsPage createCustomField(CustomField customField) {
		createCFBtn.clickJS();
		CustomFieldType cfType = customField.getType();
		customFieldButton = new ButtonImpl(cfType.getXpath(), cfType.name(), true);
		customFieldButton.clickJS();
		nextFieldDetails.clickJS();
		fieldName.type(customField.getName());
		descriptionTxtBox.type("Description");
		toStep3Btn.clickJS();
		if (cfType.getGhostText() != null) {
			cfValue.type(cfType.getGhostText() + CommonUtils.getUnicName());
		}
		if (cfType.equals(CustomFieldType.SingleChoice)) {
			constructSingleChoiceOptions();
		}
		createFieldBtn.clickJS();
		sleep(2);
		waitConditionBecomesTrue(customFieldsTable.isDisplayed(), 3);
		Element field = new LabelImpl("//*[text()='" + customField.getName() + "']", "");
		verifier.verifyElementIsDisplayed(field);
		return this;
	}

	public CustomFieldsPage deleteCustomField(CustomField customField) {
		return deleteCustomField(customField.getName());
	}

	public CustomFieldsPage deleteCustomField(String customFieldName) {
		CheckBox select = new CheckBoxImpl(
				"//table//tbody/tr/td[@title='" + customFieldName + "']/preceding-sibling:: td/input", "Delete");
		if (!select.isDisplayed()) {
			waitConditionBecomesTrue(select.isDisplayed(), 4);
		}
		select.scrollIntoView();
		select.check();
		waitConditionBecomesTrue(actionsDropDownLink.isDisplayed(), 4);
		actionsDropDownLink.clickJS();
		deleteButton.click();
		waitConditionBecomesTrue(confirmDeleteButton.isDisplayed(), 4);
		confirmDeleteButton.click();
		sleep(4);
		Element field = new LabelImpl("//*[text()='" + customFieldName + "']", "");
		verifier.verifyTrue(field.isNotExists(), "Custom field still exists");
		return this;
	}

	private void constructSingleChoiceOptions() {
		String[] singlechoiceOptions = { "1", "2", "3" };
		for (int i = 0; i < singlechoiceOptions.length; i++) {
			newOption.type(singlechoiceOptions[i]);
			addBtn.clickJS();
		}
	}

	public static class CustomField {
		private CustomFieldType type;
		private String name;

		public CustomField(CustomFieldType type, String name) {
			this.type = type;
			this.name = name;
		}

		public CustomFieldType getType() {
			return type;
		}

		public String getName() {
			return name;
		}
	}

	public enum CustomFieldType {
		TextBox("//p[contains(text(), 'Text Box')]/ancestor::a", "Ghost Text"), Number(
				"//p[contains(text(), 'Number')]/ancestor::a",
				"Ghost Number Text"), YesNo("//p[contains(text(), 'Yes')]/ancestor::a", null), DateTime(
						"//p[contains(text(), 'Date')]/ancestor::a",
						null), SingleChoice("//p[contains(text(), 'Single')]/ancestor::a", null);

		private String xpath;
		private String ghostText;

		private CustomFieldType(String xpath, String ghostText) {
			this.xpath = xpath;
			this.ghostText = ghostText;
		}

		public String getXpath() {
			return this.xpath;
		}

		public String getGhostText() {
			return this.ghostText;
		}
	}

}
