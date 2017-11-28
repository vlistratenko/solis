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

public class FormFieldConfigurationModalWindow extends FormFieldConfigurationModalWindowBasic {

    Button saveButton = new ButtonImpl("//*[contains(@id,'FieldEditModal-form')]/div[3]/a[2]","Save Content button for field configuration");
    Button cancelButton = new ButtonImpl("//*[contains(@id,'FieldEditModal-form')]/div[3]/a[1]","Cancel button of fields configuration modal window");
    CheckBox requiredCheckbox = new CheckBoxImpl("//*[@class='appModalContent']//*[@type='checkbox'][@ng-model='fieldConfig.required']","Checkbox to mark fields as required");
    TextBox designationFieldOptionTextField = new TextBoxImpl("//*[contains(@id,'FieldEditModal-form')]//input[@placeholder='Add an option....']","Designation field option field");
    Button designationFieldAddButton = new ButtonImpl("//*[contains(@id,'FieldEditModal-form')]//button[@class='button postfix']","Designation button Add option button");
    Button selectFieldButton = new ButtonImpl("//*[contains(text(),'fieldNameForReplacement')]/following-sibling::*//*[@ng-click='selectField(item)']","Add field button of fieldNameForReplacement in form field configuration modal window");

    private static List<String> supporterFieldsNames = new ArrayList<>();

    public FormFieldConfigurationModalWindow(){
        if(supporterFieldsNames.isEmpty()) supporterFieldsNames = getAllSupporterFieldsNames();
    }

    public List<String> getAllSupporterFieldsNames(){
        new SignupFormElements().performDrop(SignupFormElements.VE.FORM_FIELD);
        Label supporterNameLabel = new LabelImpl("//*[contains(@id,'FieldEditModal-form')]//tbody//*[.='Supporter ']//ancestor::tr//td[1]","Supporter name label");
        supporterNameLabel.fluentWaitForElementPresenceIgnoringExceptions();
        List<WebElement> elements = driver.findElements(By.xpath("//*[not(@class='unselectable')][td[3][span[.='Supporter ']]]//td[1]"));
        List<String> supporterFieldNames = elements.stream().map(WebElement::getText).collect(Collectors.toList());
        closeFieldConfigurationModalWindow();
        return supporterFieldNames;
    }

    @SuppressWarnings("unchecked")
	public <T extends HomePage> T dropAllSupporterFieldsOnFormAndMarkAsRequired() {
        List<String> supporterFieldNames = supporterFieldsNames;
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

    @SuppressWarnings("unchecked")
	public <T extends HomePage> T dropAllSupporterFieldsOnForm(){
        List<String> supporterFieldNames = supporterFieldsNames;
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
    
}



