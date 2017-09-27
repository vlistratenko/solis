package com.salsalabs.ignite.automation.pages.hq.manage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

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
	private Button backStep3Button = new ButtonImpl("//button[@autotest-id='btn_back_step3_custom_field_create']", "Back step 3 Button");
	private Button cancelButton = new ButtonImpl("//button[contains(text() , 'Cancel')]", "Cancel Button");
	Button closeModalWindowButton = new ButtonImpl("//a[@class='f-dropdown-close']", "Close Dialog Message BUtton");
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

	public CustomFieldsPage proceedToTheStep3(CustomField customField) {
		createCFBtn.clickJS();
		CustomFieldType cfType = customField.getType();
		customFieldButton = new ButtonImpl(cfType.getXpath(), cfType.name(), true);
		customFieldButton.clickJS();
		nextFieldDetails.clickJS();
		fieldName.type(customField.getName());
		descriptionTxtBox.type("Description");
		toStep3Btn.clickJS();
		return this;
	}

	public CustomFieldsPage validateTextBox(CustomField customField) {
		TextBox previewFieldName = new TextBoxImpl(
				"//span[contains(text() , '" + customField.getName()+ "') ]", "Preview Field Name");
		verifier.verifyElementIsDisplayed(previewFieldName);
		TextBox minValue = new TextBoxImpl("//input[@id='minValue']", "Min Value");
		minValue.clear();
		minValue.type("1000");
		createFieldBtn.clickJS();
		TextBox validationMessage = new TextBoxImpl(
				"//div[contains(text() , 'Maximum value should be larger than minimum value')]",
				"TextBox out of range  Validation Message");
		verifier.verifyElementIsDisplayed(validationMessage);
		if (validationMessage.isDisplayed()) {
			closeModalWindowButton.click();
		}
		backStep3Button.click();
		cancelButton.click();
		return this;
	}
	
	public CustomFieldsPage validateDateTimeField(CustomField customField) {
		sleep(1);
		 Button doneButton = new ButtonImpl("//button[contains(text(), 'Done')]", "Done Button - Date Picker Modal Window »");
		TextBox previewFieldName = new TextBoxImpl("//label[contains(text() , '" + customField.getName()+ "') ]", "Preview Field Name");	
		verifier.verifyElementIsDisplayed(previewFieldName);
		TextBox latestDateField = new TextBoxImpl("//input[@id='maxDate']", "The latest date value");
		TextBox earliestDateField = new TextBoxImpl("//input[@id='minDate']", "The earliest date value");
		 LocalDate date = LocalDate.now();
		 DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("MM/dd/yyyy");
		 String latestDateValue = date.format(formatter);
		date= date.plusYears(1);
		 String earliestDateValue= date.format(formatter);
		 latestDateField.type(latestDateValue);
		 earliestDateField.type(earliestDateValue);
		 latestDateField.type(latestDateValue);
		 if(doneButton.isDisplayed()){
			 doneButton.click();
		 }
		 createFieldBtn.clickJS();
		 TextBox validationMessage = new TextBoxImpl("//div[contains(text(), 'Earliest date is after latest date.')]","TextBox out of range  Validation Message");
		 verifier.verifyElementIsDisplayed(validationMessage);
		 if (validationMessage.isDisplayed()) {
				closeModalWindowButton.click();
			}
		 CheckBox datetime = new CheckBoxImpl("//div[contains(@ng-show, 'DATE')]/descendant::p[contains(. , 'Date/Time')]", "Date time checkbox");
		 datetime.check();
		 TextBox earliestTime = new TextBoxImpl("//input[@id='minTime']", "The earliest date value");
		 TextBox latestTime = new TextBoxImpl("//input[@id='maxTime']", "The earliest date value");
		 earliestTime.scrollIntoView();
		 earliestTime.type("2:00am");
		 latestTime.type("1:00am");
		 if(doneButton.isDisplayed()){
			 doneButton.click();
		 }
		 createFieldBtn.click();
		 TextBox validationMessage2 = new TextBoxImpl("//span[contains(text(), 'The value you entered' )]","TextBox out of range  Validation Message");
		 if (validationMessage2.isDisplayed()) {
			 closeModalWindowButton.click();
			}
		 backStep3Button.click();
			cancelButton.click();
			return this;
	}
	
	public CustomFieldsPage validateYesNo(CustomField customField) {
		 CheckBox yesNo = new CheckBoxImpl("//div[contains(@ng-show, 'BOOLEAN')]/descendant::p[contains(. , 'Yes')]", "Yes No Radio Buttons");
		 CheckBox trueFalse = new CheckBoxImpl("//div[contains(@ng-show, 'BOOLEAN')]/descendant::p[contains(. , 'True')]", "True False  Radio Buttons");
		 int trueFalseOptions = trueFalse.findElementsByXpath("//div[contains(@ng-show, 'BOOLEAN')]/descendant::p[contains(. , 'True')]").size();
		 verifier.verifyEquals(trueFalseOptions, 3, "True false options number is incorrect");
		 yesNo.check();
		 int yesNoValues = yesNo.findElementsByXpath("//div[contains(@ng-show, 'BOOLEAN')]/descendant::p[contains(. , 'Yes')]").size();
		 verifier.verifyEquals(yesNoValues, 3, "Yes No  options number is incorrect");
		return this;
	}
	
	public CustomFieldsPage validateSingleChoice(CustomField customField) {
		String value1 = CommonUtils.getRandomNumericValueFixedLength(5);
		String value2 = CommonUtils.getRandomNumericValueFixedLength(5);
		String value3 = CommonUtils.getRandomNumericValueFixedLength(5);
	
		String[] singlechoiceOptions = { value1, value2, value3 };
		for (int i = 0; i < singlechoiceOptions.length; i++) {
			newOption.type(singlechoiceOptions[i]);
			addBtn.clickJS();
		}
		 Button singleChoiceOption1 = new ButtonImpl("//div[contains(@ng-show, 'SINGLE_CHOICE')]/descendant::p[contains(. , '"+value1+"')]  ", "Hide Single Choice Option");
		 Button singleChoiceOption2 = new ButtonImpl("//div[contains(@ng-show, 'SINGLE_CHOICE')]/descendant::p[contains(. , '"+value1+"')]  ", "Hide Single Choice Option");
		  verifier.verifyElementIsDisplayed(singleChoiceOption1);
		 Button hideButton = new ButtonImpl("//span[contains(text(), '"+value1 +"')]/parent::div/following-sibling:: div/a[@ng-hide='option.hidden']", "Hide Single Choice Option");
		 hideButton.click();
		 verifier.verifyElementIsNotDisplayed(singleChoiceOption1);
		 Button deleteSingleChoiceOption = new ButtonImpl("//span[contains(text(), '"+value2 +"')]/parent::div/following-sibling::div/a[@title='Delete']", "Delete Single Choice Option");
		 deleteSingleChoiceOption.click();
		 verifier.verifyElementIsNotDisplayed(singleChoiceOption2);
		 Button editSingleChoiceOption = new ButtonImpl("//span[contains(text(), '"+value3 +"')]/parent::div/following-sibling::div/a[@title='Edit']", "Delete Single Choice Option");
		 editSingleChoiceOption.scrollIntoView();
		 editSingleChoiceOption.click();
		 TextBox inputEdittingValue = new TextBoxImpl("//input[@ng-model='labelEditing.value']", "Edited Single choice  Input Field ");
		 inputEdittingValue.clear();
		 inputEdittingValue.type("Edited");
		 Button saveEdit = new ButtonImpl("//a[contains(@ng-click, 'saveEdit')] ", "Save Single Choice Option");
		 saveEdit.click();
		 Button singleChoiceOption3AfterEditing = new ButtonImpl("//div[contains(@ng-show, 'SINGLE_CHOICE')]/descendant::p[contains(. , 'Edited')]  ", "Edited Single Choice Option");
		 verifier.verifyElementIsDisplayed(singleChoiceOption3AfterEditing);
		return this;
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
