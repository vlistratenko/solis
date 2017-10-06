package com.salsalabs.ignite.automation.pages.hq.manage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.tools.ant.taskdefs.WaitFor;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.WebElement;

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
	private Button createActivityCFBtn = new ButtonImpl("//span[contains(text(), ' Create an Activity')]/parent::button",
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
	private Button activityCustomFields = new ButtonImpl("//a[contains(text(), 'Activity Custom Fields')]",
			"Create a Supporter Custom Field Button", true);
	private Button feedbackMessage = new ButtonImpl("	//div[@class='feedback-message']",	"Feedback message");

	public CustomFieldsPage openActivittiesCustomFieldsTab() {
		activityCustomFields.clickJS();
		sleep(1);
		return this;
	}
	
	public CustomFieldsPage selectActivityCustomField(String activityName) {
		if(!createActivityCFBtn.isDisplayed()){
			waitConditionBecomesTrue(createActivityCFBtn.isDisplayed(), 4);
		}
		createActivityCFBtn.scrollIntoView();
		sleep(2);
		if(feedbackMessage.isDisplayed()){
			Button closeModalWindowButton = new ButtonImpl("//a[@class='close']", "Close Dialog Message BUtton");
			if(closeModalWindowButton.isDisplayed()){
				closeModalWindowButton.click();
			}
			sleep(2);
		}
		
		createActivityCFBtn.clickJS();
		Button form = new ButtonImpl("//a[contains(text(), '"+activityName+"')]",	" Custom Field with type + " + activityName , true);
		form.click();
		return this;
	}
	
	public void createCustomField(String customFieldType) {
		createCustomField(CustomFieldType.valueOf(customFieldType));
	}

	public CustomFieldsPage createCustomField(CustomFieldType cfType) {
		return this.createCustomField(new CustomField(cfType, cfType.name() + "_" + CommonUtils.getUnicName()));
	}

	public CustomFieldsPage clickCreatesupporterCustomFieldButton(){
		createCFBtn.clickJS();
		return this;
	}
	
	
	public CustomFieldsPage createCustomField(CustomField customField) {
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
				"//table//tbody/tr/td[contains(@title, '"+ customFieldName+"')]/preceding-sibling:: td/input", "Delete");
		if (!select.isDisplayed()) {
			waitConditionBecomesTrue(select.isDisplayed(), 4);
		}
		select.scrollIntoView();
		List<WebElement> checkboxs =  select.findElementsByXpath("//table//tbody/tr/td[contains(@title, '"+ customFieldName+"')]/preceding-sibling:: td/input");
		for (WebElement webElement : checkboxs) {
			if(!webElement.isSelected()){
				webElement.click();
			}	
		}
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
	
	public JSONObject getTextBoxCustomFieldJson(String type, String cfName) throws JSONException{
		String json = "{\"header\":{},\"payload\":{\"name\":\""+cfName+"\",\"description\":\"supporterCustomFieldds\",\"dataType\":\"TEXT\",\"controlType\":\"INPUT\","
				+ "\"ghostText\":\"\",\"defaultValue\":\"\","
				+ "\"valueLabels\":[],\"activityType\":\"\",\"type\":\""+type+"\",\"validation\":{\"minValue\":0,\"maxValue\":256,\"textValidation\":\"ANY_CHARACTER\"}}}";
		return new JSONObject(json);
				
	}
	
	public JSONObject getSingleChoiceCustomFieldJson(String type, String cfName) throws JSONException{
		String json = "{\"header\":{},\"payload\":{\"name\":\""+cfName+"\",\"description\":\"111\",\"dataType\":\"TEXT\",\"controlType\":\"RADIO\",\"controlOrientation\":\"VERTICAL\","
				+ "\"ghostText\":\"\",\"defaultValue\":\"\",\"valueLabels\":[{\"value\":\"\",\"label\":\"No Selection\"},{\"value\":\"1choice\",\"label\":\"1choice\"},"
				+ "{\"value\":\"2choice\",\"label\":\"2choice\"}],\"activityType\":\"\",\"type\":\""+type+"\",\"validation\":{}}}";
		return new JSONObject(json);			
	}
	
	public JSONObject getDateCustomFieldJson(String type, String cfName) throws JSONException{
		String json = " {\"header\":{},\"payload\":{\"name\":\""+cfName+"\",\"description\":\"dateTime1\",\"dataType\":\"DATE\",\"controlType\":\"DATE\",\"ghostText\":\"\",\"defaultValue\":\"\","
				+ "\"valueLabels\":[],\"activityType\":\"\",\"type\":\""+type+"\",\"validation\":{}}}";
		return new JSONObject(json);			
	}
	
	public JSONObject getBoleanCustomFieldJson(String type, String cfName) throws JSONException{
		String json = "{\"header\":{},\"payload\":{\"name\":\""+cfName+"\",\"description\":\"Description\",\"dataType\":\"BOOLEAN\",\"controlType\":\"RADIO\",\"controlOrientation\":\"HORIZONTAL\",\"ghostText\":\"\","
				+ "\"defaultValue\":true,\"valueLabels\":[{\"value\":true,\"label\":\"True\"},{\"value\":false,\"label\":\"False\"}],\"activityType\":\"\",\"type\":\""+type+"\"}}";
		return new JSONObject(json);			
	}
	
	public JSONObject getNumberCustomFieldJson(String type, String cfName) throws JSONException{
		String json = "	{\"header\":{},\"payload\":{\"name\":\""+cfName+"\",\"description\":\"Number111\",\"dataType\":\"NUMBER\",\"controlType\":\"INPUT\",\"ghostText\":\"numberGhostText\",\"defaultValue\":\"\","
				+ "\"valueLabels\":[],\"activityType\":\"\",\"type\":\""+type+"\",\"validation\":{}}}";
		return new JSONObject(json);			
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

	/**
	 * Returns CustomFieldAPIGenerator object. {@link CustomFieldAPIGenerator} decorator allows to build supporter and activity custom fields of desired configuration.
	 * Used for creating custom fields via API only.
	 * @param  customFieldName  custom field reference name
	 * @param  customFieldDescription custom field description
	 * @return      the object of CustomFieldAPIGenerator
	 * @see         CustomFieldAPIGenerator
	 */

	public static CustomFieldsPage.CustomFieldAPIGenerator getCustomFieldApiGenerator (String customFieldName, String customFieldDescription) {
		return new CustomFieldsPage.CustomFieldAPIGenerator (customFieldName, customFieldDescription);
	}

	/**
	 * Returns CustomField object build using {@link CustomFieldAPIGenerator}. This object is assigned all possible custom fields properties.
	 * @param  customFieldAPIGenerator  object of {@link CustomFieldAPIGenerator}
	 * @return      the object of CustomField
	 * @see         CustomFieldAPIGenerator
	 */

	public static CustomFieldsPage.CustomField createCustomField (CustomFieldAPIGenerator customFieldAPIGenerator) {
		return new CustomField (customFieldAPIGenerator);
	}

	/**
	 * CustomField is static nested class which reflects custom field object.
	 * This class contains all fields which describe properties of custom fields of all types.
	 * Object of this class consums {@link CustomFieldAPIGenerator} object as parameter in order to initialize fields.
	 */

	public static class CustomField {
		private CustomFieldType type;
		private String name;
		private String descriptionApi;
		private String dataTypeApi;
		private String controlTypeApi;
		private String ghostTextApi;
		private int textFieldMinLengthValueApi;
		private int textFieldMaxLengthValueApi;
		private String textFieldValidationApi;
		private String yesNoFieldcontrolOrientationApi;
		private String defaultValueApi;
		private String[] yesNoFieldValueLabelsApi;
		private String[] singleChoiceFieldValueLabelsApi;
		private String minDateApi;
		private String maxDateApi;
		private String minTimeApi;
		private String maxTimeApi;

		public CustomField(CustomFieldType type, String name) {
			this.type = type;
			this.name = name;
		}

		/**
		 * CustomField constructor with {@link CustomFieldAPIGenerator} parameter.
		 * {@link CustomFieldAPIGenerator} object should have all neccessary fields configured.
		 */

		public CustomField(CustomFieldAPIGenerator customFieldGenerator) {
			this.name = customFieldGenerator.name;
			this.descriptionApi = customFieldGenerator.descriptionApi;
			this.dataTypeApi = customFieldGenerator.dataTypeApi;
			this.controlTypeApi = customFieldGenerator.controlTypeApi;
			this.ghostTextApi = customFieldGenerator.ghostTextApi;
			this.textFieldMaxLengthValueApi = customFieldGenerator.textFieldMaxLengthValueApi;
			this.textFieldMinLengthValueApi = customFieldGenerator.textFieldMinLengthValueApi;
			this.textFieldValidationApi = customFieldGenerator.textFieldValidationApi;
			this.yesNoFieldcontrolOrientationApi = customFieldGenerator.yesNoFieldControlOrientationApi;
			this.defaultValueApi = customFieldGenerator.defaultValueApi;
			this.yesNoFieldValueLabelsApi = customFieldGenerator.yesNoFieldValueLabelsApi;
			this.singleChoiceFieldValueLabelsApi = customFieldGenerator.singleChoiceFieldValueLabelsApi;
			this.minDateApi = customFieldGenerator.minDateApi;
			this.maxDateApi = customFieldGenerator.maxDateApi;
			this.minTimeApi = customFieldGenerator.minTimeApi;
			this.maxTimeApi = customFieldGenerator.maxTimeApi;
		}

		/**
		 * Returns custom field type
		 * @return      type of the custom field
		 */

		public CustomFieldType getType() {
			return type;
		}

		/**
		 * Returns custom field name
		 * @return      name of the custom field
		 */

		public String getName() {
			return name;
		}

		/**
		 * Returns JSONObject object of Supporter custom field which should be created.
		 * It will be consumed by {@link com.salsalabs.ignite.automation.common.HttpClient#createCustomField(JSONObject)}
		 * @param  customFieldType  type of custom field that should be created. Should be selected among {@link CustomFieldType} enum options
		 * @return      json object of supporter custom field
		 * @see         CustomFieldType
		 */

		public JSONObject createSupporterCustomFieldViaApiJsonObject(CustomFieldType customFieldType) throws JSONException {
			String type = customFieldType.name().toLowerCase();
			String json = "";

			switch (type) {
				case ("textbox"): json = "{\"header\":{},\"payload\":{\"name\":\"" + this.name + "\",\"description\":\"" + this.descriptionApi + "\",\"dataType\":\"" + this.dataTypeApi +
						"\",\"controlType\":\"" + this.controlTypeApi + "\",\"ghostText\":\"" + this.ghostTextApi +
						"\",\"defaultValue\":\"\",\"valueLabels\":[],\"activityType\":\"\",\"type\":\"PERSON\",\"validation\":{\"minValue\":" + this.textFieldMinLengthValueApi +
						",\"maxValue\":" + this.textFieldMaxLengthValueApi + ",\"textValidation\":\"" + this.textFieldValidationApi + "\"}}}"; break;
				case ("number"): json = "{\"header\":{},\"payload\":{\"name\":\""+ this.name + "\",\"description\":\"" + this.descriptionApi +
						"\",\"dataType\":\"NUMBER\",\"controlType\":\"INPUT\",\"ghostText\":\"" + this.ghostTextApi +
						"\",\"defaultValue\":\"\",\"valueLabels\":[],\"activityType\":\"\",\"type\":\"PERSON\",\"validation\":{}}}"; break;
				case ("yesno"): json = "{\"header\":{},\"payload\":{\"name\":\"" + this.name + "\",\"description\":\"" + this.descriptionApi +
						"\",\"dataType\":\"BOOLEAN\",\"controlType\":\"RADIO\",\"controlOrientation\":\"" + this.yesNoFieldcontrolOrientationApi +
						"\",\"ghostText\":\"\",\"defaultValue\":" + this.defaultValueApi + ",\"valueLabels\":[{\"value\":true,\"label\":\"" +
						this.yesNoFieldValueLabelsApi[0] + "\"},{\"value\":false,\"label\":\"" + this.yesNoFieldValueLabelsApi[1] + "\"}],\"activityType\":\"\",\"type\":\"PERSON\"}}"; break;
				case ("datetime"): json = "{\"header\":{},\"payload\":{\"name\":\"" + this.name + "\",\"description\":\"" + this.descriptionApi + "\",\"dataType\":\"DATE\",\"controlType\":\"" +
						this.controlTypeApi + "\",\"ghostText\":\"\",\"defaultValue\":\"\",\"valueLabels\":[],\"activityType\":\"\",\"type\":\"PERSON\",\"validation\":{\"minDate\":\"" +
						this.minDateApi + "\",\"maxDate\":\"" + this.maxDateApi + "\",\"minTime\":\"" + this.minTimeApi + "\",\"maxTime\":\"" + this.maxTimeApi + "\"}}}"; break;
				case ("singlechoice"): json = "{\"header\":{},\"payload\":{\"name\":\"" + this.name + "\",\"description\":\"" + this.descriptionApi + "\",\"dataType\":\"TEXT\",\"controlType\":\"" + this.controlTypeApi +
						"\",\"controlOrientation\":\"VERTICAL\",\"ghostText\":\"\",\"defaultValue\":\"" + this.singleChoiceFieldValueLabelsApi[0] + "\",\"valueLabels\":[{\"value\":\"" + this.singleChoiceFieldValueLabelsApi[0] +
						"\",\"label\":\"" + this.singleChoiceFieldValueLabelsApi[0] + "\"},{\"value\":\"" + this.singleChoiceFieldValueLabelsApi[1] + "\",\"label\":\"" + this.singleChoiceFieldValueLabelsApi[1] +
						"\"}],\"activityType\":\"\",\"type\":\"PERSON\",\"validation\":{}}}"; break;
			}
			System.out.println(json);
			return new JSONObject(json);

		}

		/**
		 * Returns JSONObject object of activity custom field which should be created.
		 * It will be consumed by {@link com.salsalabs.ignite.automation.common.HttpClient#createCustomField(JSONObject)}
		 * @param  customFieldType  type of custom field that should be created. Should be selected among {@link CustomFieldType} enum options
		 * @param  activityType  type of activity this custom field should be created for. Expected values are "SUBSCRIBE", "FUNDRAISE", "PETITION", "TICKETED_EVENT", "TARGETED_LETTER", "P2P_EVENT"
		 * @return      json object of activity custom field
		 * @see         CustomFieldType
		 */

		public JSONObject createActivityCustomFieldViaApiJsonObject(CustomFieldType customFieldType, String activityType) throws JSONException {
			String type = customFieldType.name().toLowerCase();
			String json = "";

			switch (type) {
				case ("textbox"): json = "{\"header\":{},\"payload\":{\"name\":\"" + this.name + "\",\"description\":\"" + this.descriptionApi +
						"\",\"dataType\":\"" + this.dataTypeApi + "\",\"controlType\":\"" + this.controlTypeApi + "\",\"ghostText\":\"" + this.ghostTextApi +
						"\",\"defaultValue\":\"\",\"valueLabels\":[],\"activityType\":\"" + activityType + "\",\"type\":\"ACTIVITY\",\"validation\":{\"minValue\":\"" +
						this.textFieldMinLengthValueApi + "\",\"maxValue\":" + this.textFieldMaxLengthValueApi + ",\"textValidation\":\"" + this.textFieldValidationApi +
						"\"}}}"; break;
				case ("number"): json = "{\"header\":{},\"payload\":{\"name\":\"" + this.name + "\",\"description\":\"" + this.descriptionApi +
						"\",\"dataType\":\"NUMBER\",\"controlType\":\"INPUT\",\"ghostText\":\"" + this.ghostTextApi +
						"\",\"defaultValue\":\"\",\"valueLabels\":[],\"activityType\":\"" + activityType + "\",\"type\":\"ACTIVITY\",\"validation\":{}}}"; break;
				case ("yesno"): json = "{\"header\":{},\"payload\":{\"name\":\"" + this.name + "\",\"description\":\"" + this.descriptionApi +
						"\",\"dataType\":\"BOOLEAN\",\"controlType\":\"RADIO\",\"controlOrientation\":\"" + this.yesNoFieldcontrolOrientationApi +
						"\",\"ghostText\":\"\",\"defaultValue\":" + this.defaultValueApi +
						",\"valueLabels\":[{\"value\":true,\"label\":\"" + this.yesNoFieldValueLabelsApi[0] + "\"},{\"value\":false,\"label\":\"" +
						this.yesNoFieldValueLabelsApi[1] + "\"}],\"activityType\":\"" + activityType + "\",\"type\":\"ACTIVITY\"}}"; break;
				case ("datetime"): json = "{\"header\":{},\"payload\":{\"name\":\"" + this.name + "\",\"description\":\"" + this.descriptionApi + "\",\"dataType\":\"DATE\",\"controlType\":\"" +
						this.controlTypeApi + "\",\"ghostText\":\"\",\"defaultValue\":\"\",\"valueLabels\":[],\"activityType\":\"" + activityType + "\",\"type\":\"ACTIVITY\",\"validation\":{\"minDate\":\"" +
						this.minDateApi + "\",\"maxDate\":\"" + this.maxDateApi + "\",\"minTime\":\"" + this.minTimeApi + "\",\"maxTime\":\"" + this.maxTimeApi + "\"}}}"; break;
				case ("singlechoice"): json = "{\"header\":{},\"payload\":{\"name\":\"" + this.name + "\",\"description\":\"" + this.descriptionApi + "\",\"dataType\":\"TEXT\",\"controlType\":\"" + this.controlTypeApi +
						"\",\"controlOrientation\":\"VERTICAL\",\"ghostText\":\"\",\"defaultValue\":\"" + this.singleChoiceFieldValueLabelsApi[0] + "\",\"valueLabels\":[{\"value\":\"" + this.singleChoiceFieldValueLabelsApi[0] +
						"\",\"label\":\"" + this.singleChoiceFieldValueLabelsApi[0] + "\"},{\"value\":\"" + this.singleChoiceFieldValueLabelsApi[1] +
						"\",\"label\":\"" + this.singleChoiceFieldValueLabelsApi[1] + "\"}],\"activityType\":\"" + activityType + "\",\"type\":\"ACTIVITY\",\"validation\":{}}}"; break;
			}
			System.out.println(json); return new JSONObject(json);

		}

	}

	/**
	 * Enum contains types of available custom fields.
	 */

	public enum CustomFieldType {
		TextBox("//p[contains(text(), 'Text Box')]/ancestor::a", "Ghost Text"),
		Number("//p[contains(text(), 'Number')]/ancestor::a", "Ghost Number Text"),
		YesNo("//p[contains(text(), 'Yes')]/ancestor::a", null),
		DateTime("//p[contains(text(), 'Date')]/ancestor::a",null),
		SingleChoice("//p[contains(text(), 'Single')]/ancestor::a", null);

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

	/**
	 * Class used to configure properties of custom field  which will be created via API.
	 * Includes all possible properties for all types of supporter and activity custom fields.
	 * Object of this class with pre-set properties should be used as parameter of {@link CustomField#createCustomField(CustomFieldType)} method
	 */

	public static class CustomFieldAPIGenerator {
		private String name;
		private String descriptionApi;
		private String controlTypeApi = "";
		private String ghostTextApi = "";
		private String defaultValueApi = "";
		private String dataTypeApi = "";
		private int textFieldMinLengthValueApi = 0;
		private int textFieldMaxLengthValueApi = 0;
		private String textFieldValidationApi = "";
		private String yesNoFieldControlOrientationApi = "";
		private String[] yesNoFieldValueLabelsApi = new String[2];
		private String[] singleChoiceFieldValueLabelsApi = new String[2];
		private String minDateApi = "";
		private String maxDateApi = "";
		private String minTimeApi = "";
		private String maxTimeApi = "";

		/**
		 * CustomFieldAPIGenerator object.
		 * @param name custom field reference name
		 * @param descriptionApi custom field description
		 */

		public CustomFieldAPIGenerator(String name, String descriptionApi) {
			this.name = name;
			this.descriptionApi = descriptionApi;
		}

		/**
		 * Sets maxDate value of {@link CustomFieldType#DateTime} field.
		 * @param maxDateApi max date value. Should be specified according to "mm/dd/yyyy" format and should be later than {@link CustomFieldAPIGenerator#minDateApi}. Example: 09/15/2017
		 * @return CustomFieldAPIGenerator object
		 */

		public CustomFieldAPIGenerator setDateFieldMaxDateApi(String maxDateApi) {
			this.maxDateApi = maxDateApi;
			return this;
		}

		/**
		 * Sets minDate value of {@link CustomFieldType#DateTime} field.
		 * @param minDateApi min date value. Should be specified according to "mm/dd/yyyy" format and should be earlier than {@link CustomFieldAPIGenerator#maxDateApi}. Example: 09/15/2017
		 * @return CustomFieldAPIGenerator object
		 */

		public CustomFieldAPIGenerator setDateFieldMinDateApi(String minDateApi) {
			this.minDateApi = minDateApi;
			return this;
		}

		/**
		 * Sets minTime value of {@link CustomFieldType#DateTime} field.
		 * @param minTimeApi min time value. Should be specified according to "hh:mm am/pm" format and should be earlier than {@link CustomFieldAPIGenerator#maxTimeApi}. Example: 10:30pm
		 * @return CustomFieldAPIGenerator object
		 */

		public CustomFieldAPIGenerator setDateFieldMinTimeApi(String minTimeApi) {
			this.minTimeApi = minTimeApi;
			return this;
		}

		/**
		 * Sets maxTime value of {@link CustomFieldType#DateTime} field.
		 * @param maxTimeApi max time value. Should be specified according to "hh:mm am/pm" format and should be later than {@link CustomFieldAPIGenerator#minTimeApi}. Example: 10:30pm
		 * @return CustomFieldAPIGenerator object
		 */

		public CustomFieldAPIGenerator setDateFieldMaxTimeApi(String maxTimeApi) {
			this.maxTimeApi = maxTimeApi;
			return this;
		}

		/**
		 * Sets orientation of {@link CustomFieldType#YesNo} field.
		 * @param yesNoFieldControlOrientationApi yesNo custom field orientation. Expected values are "HORIZONTAL" and "VERTICAL"
		 * @return CustomFieldAPIGenerator object
		 */

		public CustomFieldAPIGenerator setYesNoFieldControlOrientationApi(String yesNoFieldControlOrientationApi) {
			this.yesNoFieldControlOrientationApi = yesNoFieldControlOrientationApi;
			return this;
		}

		/**
		 * Sets values label of {@link CustomFieldType#SingleChoice} field.
		 * Current implementation allows to specify only 2 values of Single choice field.
		 * @param value1 labels to be used for Single choice custom field. These may be any values.
		 * @param value2 labels to be used for Single choice custom field. These may be any values.
		 * @return CustomFieldAPIGenerator object
		 */

		public CustomFieldAPIGenerator setSingleChoiceFieldValueLabelsApi(String value1, String value2) {
			this.singleChoiceFieldValueLabelsApi[0] = value1; this.singleChoiceFieldValueLabelsApi[1] = value2;
			return this;
		}

		/**
		 * Sets default value of {@link CustomFieldType#SingleChoice} or {@link CustomFieldType#YesNo} fields.
		 * For other fields types this property value is static and does not need to be configured separately.
		 * @param defaultValue field default value. Should be any value available in the list of field options
		 * @return CustomFieldAPIGenerator object
		 */

		public CustomFieldAPIGenerator setDefaultValue(String defaultValue){
			this.defaultValueApi = defaultValue;
			return this;
		}

		/**
		 * Sets field ghost text of {@link CustomFieldType#TextBox} and {@link CustomFieldType#Number} fields.
		 * For other fields types this property value is static and does not need to be configured separately.
		 * @param ghostText field ghost text
		 * @return CustomFieldAPIGenerator object
		 */

		public CustomFieldAPIGenerator setGhostText(String ghostText){
			this.ghostTextApi = ghostText;
			return this;
		}

		/**
		 * Sets control type only for {@link CustomFieldType#TextBox}, {@link CustomFieldType#SingleChoice} and {@link CustomFieldType#DateTime} fields.
		 * For other fields types this property value is static and does not need to be configured separately.
		 * <p>
		 * {@link CustomFieldType#TextBox} field expected values are "INPUT" and "TEXTAREA". In first case field length is limited to 256 characters and 'dataType' property is automatically assigned "TEXT".
		 * In the second case field length is limited to 2000 characters and 'dataType' property is automatically assigned "TEXTAREA" value.
		 * <p>
		 * {@link CustomFieldType#SingleChoice} field expected values are "SELECT" and "RADIO"
		 * <p>
		 * {@link CustomFieldType#DateTime} field expected values are "DATE" and "DATETIME"
		 * @param controlTypeApi control type
		 * @return CustomFieldAPIGenerator object
		 */

		public CustomFieldAPIGenerator setControlType(String controlTypeApi){
			this.controlTypeApi = controlTypeApi;
			this.dataTypeApi = controlTypeApi.equals("INPUT") ? "TEXT" : "TEXTAREA";
			return this;
		}

		/**
		 * Sets field max length of {@link CustomFieldType#TextBox} field.
		 * Accepts any value from 0 to 256 if {@link CustomFieldAPIGenerator#setControlType(String)} is "INPUT"
		 * Accepts any value from 0 to 2000 if {@link CustomFieldAPIGenerator#setControlType(String)} is "TEXTAREA"
		 * @param textFieldMaxLengthValueApi max length
		 * @return CustomFieldAPIGenerator object
		 */

		public CustomFieldAPIGenerator setTextFieldMaxLengthValue(int textFieldMaxLengthValueApi){
			this.textFieldMaxLengthValueApi = textFieldMaxLengthValueApi;
			return this;
		}

		/**
		 * Sets field min length of {@link CustomFieldType#TextBox} field.
		 * Accepts any value from 0 to 256 if {@link CustomFieldAPIGenerator#setControlType(String)} is "INPUT"
		 * Accepts any value from 0 to 2000 if {@link CustomFieldAPIGenerator#setControlType(String)} is "TEXTAREA"
		 * @param textFieldMinLengthValueApi min length
		 * @return CustomFieldAPIGenerator object
		 */

		public CustomFieldAPIGenerator setTextFieldMinLengthValue(int textFieldMinLengthValueApi){
			this.textFieldMinLengthValueApi = textFieldMinLengthValueApi;
			return this;
		}

		/**
		 * Sets validation type of {@link CustomFieldType#TextBox} field
		 * @param textFieldValidationApi type of validation. Accepted values are ALPHA_ONLY, ANY_CHARACTER, ALPHA_NUMERIC
		 * @return CustomFieldAPIGenerator object
		 */

		public CustomFieldAPIGenerator setTextFieldValidationValue(String textFieldValidationApi){
			this.textFieldValidationApi = textFieldValidationApi;
			return this;
		}

		/**
		 * Sets values label of {@link CustomFieldType#YesNo} field.
		 * @param yesNoFieldValueLabelsApi labels to be used for Yes/No custom field. Expected values are "yesno' AND "truefalse"
		 * @return CustomFieldAPIGenerator object
		 */

		public CustomFieldAPIGenerator setYesNoFieldValueLabelsApi(String yesNoFieldValueLabelsApi) {
			if (yesNoFieldValueLabelsApi.equalsIgnoreCase("yesno")) {this.yesNoFieldValueLabelsApi[0] = "Yes"; this.yesNoFieldValueLabelsApi[1] = "No";} else {
				this.yesNoFieldValueLabelsApi[0] = "True"; this.yesNoFieldValueLabelsApi[1] = "False";
			}
			return this;
		}

	}
	}


