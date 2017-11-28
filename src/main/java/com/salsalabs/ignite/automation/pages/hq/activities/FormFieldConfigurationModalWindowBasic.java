package com.salsalabs.ignite.automation.pages.hq.activities;

import com.salsalabs.ignite.automation.elements.*;
import com.salsalabs.ignite.automation.elements.VE2Elements.SignupFormElements;
import com.salsalabs.ignite.automation.elements.impl.*;
import com.salsalabs.ignite.automation.pages.hq.HomePage;


public class FormFieldConfigurationModalWindowBasic extends HomePage {

    Button saveButton = new ButtonImpl("//*[contains(@id,'FieldEditModal-form')]/div[3]/a[2]","Save Content button for field configuration");
    Button cancelButton = new ButtonImpl("//*[contains(@id,'FieldEditModal-form')]/div[3]/a[1]","Cancel button of fields configuration modal window");
    CheckBox requiredCheckbox = new CheckBoxImpl("//*[@class='appModalContent']//*[@type='checkbox'][@ng-model='fieldConfig.required']","Checkbox to mark fields as required");
    TextBox designationFieldOptionTextField = new TextBoxImpl("//*[contains(@id,'FieldEditModal-form')]//input[@placeholder='Add an option....']","Designation field option field");
    Button designationFieldAddButton = new ButtonImpl("//*[contains(@id,'FieldEditModal-form')]//button[@class='button postfix']","Designation button Add option button");
    Button selectFieldButton = new ButtonImpl("//*[contains(text(),'fieldNameForReplacement')]/following-sibling::*//*[@ng-click='selectField(item)']","Add field button of fieldNameForReplacement in form field configuration modal window");


    public FormFieldConfigurationModalWindowBasic dropFormFieldByName(String fieldName){
        Button addFieldButton = new ButtonImpl("//*[contains(text(),'" + fieldName + "')]/following-sibling::*//*[@ng-click='selectField(item)']","Add field button of " + fieldName + " in form field configuration modal window");
        new SignupFormElements().performDrop(SignupFormElements.VE.FORM_FIELD);
        if(addFieldButton.isDisplayed()) addFieldButton.click();
        return this;
    }

    public FormFieldConfigurationModalWindowBasic markFieldAsRequired(){
    	markFieldAsRequired(true);
        return this;
    }
    
    public FormFieldConfigurationModalWindowBasic markFieldAsRequired(Boolean isRequired){
        sleep(2);
        if(requiredCheckbox.isDisplayed()){
            requiredCheckbox.check(isRequired);}
        return this;
    }

    @SuppressWarnings("unchecked")
	public  <T extends HomePage> T saveFieldConfiguration(){
        saveButton.click();
        return (T) new HomePage();
    }

    public FormFieldConfigurationModalWindowBasic closeFieldConfigurationModalWindow(){
        cancelButton.click();
        return this;
    }

    public void addDesignationFieldOption(String optionValue){
	    designationFieldOptionTextField.type(optionValue);
	    designationFieldAddButton.click();
    }
    
    public FormFieldConfigurationModalWindowBasic selectFieldType(String fieldName) {
    	selectFieldButton.changePathAndElementName("fieldNameForReplacement", fieldName, fieldName);
    	selectFieldButton.waitElement(15);
    	selectFieldButton.click();
    	return this;
	}
    
}



