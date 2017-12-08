package com.salsalabs.ignite.automation.pages.hq.activities;

import com.salsalabs.ignite.automation.elements.*;
import com.salsalabs.ignite.automation.elements.VE2Elements.SignupFormElements;
import com.salsalabs.ignite.automation.elements.impl.*;
import com.salsalabs.ignite.automation.pages.hq.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FormFieldConfigurationModalWindow extends HomePage {

    Button saveButton = new ButtonImpl("//*[contains(@id,'FieldEditModal-form')]/div[3]/a[2]","Save Content button for field configuration");
    Button cancelButton = new ButtonImpl("//*[contains(@id,'FieldEditModal-form')]/div[3]/a[1]","Cancel button of fields configuration modal window");
    CheckBox requiredCheckbox = new CheckBoxImpl("//*[@class='appModalContent']//*[@type='checkbox'][@ng-model='fieldConfig.required']","Checkbox to mark fields as required");
    TextBox designationFieldOptionTextField = new TextBoxImpl("//*[contains(@id,'FieldEditModal-form')]//input[@placeholder='Add an option....']","Designation field option field");
    Button designationFieldAddButton = new ButtonImpl("//*[contains(@id,'FieldEditModal-form')]//button[@class='button postfix']","Designation button Add option button");
    TextBox labelTextBox = new TextBoxImpl("//*[@class='appModalContent']//*[@ng-model='fieldConfig.labelText']","Field label");
    SelectBox checkBoxDefaultValue = new SelectBoxImpl(".//*[text()='Default Value']/following-sibling::*","Default value");

    private static List<String> supporterFieldNames  = new ArrayList<>();;

    public FormFieldConfigurationModalWindow dropFormFieldByName(String fieldName){
        Button addFieldButton = new ButtonImpl("//*[contains(text(),'" + fieldName + "')]/following-sibling::*//*[@ng-click='selectField(item)']","Add field button of " + fieldName + " in form field configuration modal window");
        new SignupFormElements().performDrop(SignupFormElements.VE.FORM_FIELD);
        if(this.supporterFieldNames.isEmpty()) {
            Label supporterNameLabel = new LabelImpl("//*[contains(@id,'FieldEditModal-form')]//tbody//*[.='Supporter ']//ancestor::tr//td[1]","Supporter name label");
            supporterNameLabel.fluentWaitForElementPresenceIgnoringExceptions();
            List<WebElement> elements = driver.findElements(By.xpath("//*[not(@class='unselectable')][td[3][span[.='Supporter ']]]//td[1]"));
            this.supporterFieldNames = elements.stream().map(WebElement::getText).collect(Collectors.toList());
        }
        if(addFieldButton.isDisplayed()) addFieldButton.click();
        return this;
    }

    public FormFieldConfigurationModalWindow markFieldAsRequired(){
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(requiredCheckbox.isDisplayed()){
            if(!requiredCheckbox.isChecked()) requiredCheckbox.check();}
        return this;
    }

    public  <T extends HomePage> T saveFieldConfiguration(){
        saveButton.click();
        return (T) new HomePage();
    }

    public FormFieldConfigurationModalWindow closeFieldConfigurationModalWindow(){
        cancelButton.click();
        return this;
    }

    public <T extends HomePage> T dropAllSupporterFieldsOnFormAndMarkAsRequired() {
        List<String> supporterFieldNames = this.supporterFieldNames;
        supporterFieldNames.stream().forEach(name -> {
            dropFormFieldByName(name);
            logger.info(name + " was dropped in the layout");
            if(name.equalsIgnoreCase("designation")) {
                addDesignationFieldOption("Option1");
                markFieldAsRequired().
                saveFieldConfiguration();
            } else {
                markFieldAsRequired().
                saveFieldConfiguration();
            }
        } );
        return (T) new HomePage();
    }

    public <T extends HomePage> T dropAllSupporterFieldsOnForm(){
        List<String> supporterFieldNames = this.supporterFieldNames;
        supporterFieldNames.stream().forEach(name -> {
            dropFormFieldByName(name);
            logger.info(name + " was dropped in the layout");
            if(name.equalsIgnoreCase("designation")) {
                addDesignationFieldOption("Option1");
                saveFieldConfiguration();
            } else {
                saveFieldConfiguration();
            }
        } );
        return (T) new HomePage();
        }

        public void addDesignationFieldOption(String optionValue){
        designationFieldOptionTextField.type(optionValue);
        designationFieldAddButton.click();
        }
    }



