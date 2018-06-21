package com.salsalabs.ignite.automation.pages.hq.activities;

import com.salsalabs.ignite.automation.elements.*;
import com.salsalabs.ignite.automation.elements.VE2Elements.Signatures;
import com.salsalabs.ignite.automation.elements.VE2Elements.SignupFormElements;
import com.salsalabs.ignite.automation.elements.impl.*;
import com.salsalabs.ignite.automation.pages.hq.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class FormFieldConfigurationModalWindow extends HomePage {

    public Button saveButton = new ButtonImpl("//*[contains(@id,'FieldEditModal-form')]/div[3]/a[2]","Save Content button for field configuration");
    Button cancelButton = new ButtonImpl("//*[contains(@id,'FieldEditModal-form')]/div[3]/a[1]","Cancel button of fields configuration modal window");
    CheckBox requiredCheckbox = new CheckBoxImpl("//*[@class='appModalContent']//*[@type='checkbox'][@ng-model='fieldConfig.required']","Checkbox to mark fields as required");
    TextBox designationFieldOptionTextField = new TextBoxImpl("//*[contains(@id,'FieldEditModal-form')]//input[@placeholder='Add an option....']","Designation field option field");
    Button designationFieldAddButton = new ButtonImpl("//*[contains(@id,'FieldEditModal-form')]//button[@class='button postfix']","Designation button Add option button");
    public TextBox labelTextBox = new TextBoxImpl("//*[@class='appModalContent']//*[@ng-model='fieldConfig.labelText']","Field label");
    public SelectBox checkBoxDefaultValue = new SelectBoxImpl(".//*[text()='Default Value']/following-sibling::*","Default value");
    Button selectFieldButton = new ButtonImpl("//*[contains(text(),'fieldNameForReplacement')]/following-sibling::*//*[@ng-click='selectField(item)']","Add field button of fieldNameForReplacement in form field configuration modal window");
    DropDown petitionSignatureCustomizedSupporterNameOptionsList = new DropDownImpl("//*[@ng-model = 'elementConfig.signatureFields']", "Petition signature element customized supporter name list");
    DropDown petitionSignatureCustomizedSupporterLocationOptionsList = new DropDownImpl("//*[@ng-model = 'elementConfig.locationFields']","Petition signature element customized supporter location list");
    Button saveSignaturesConfigurationButton = new ButtonImpl("//*[@id='htmlEditModal']//*[@class=\"small button primary\"]", "Save Signatures element configuration modal button");
    private static List<String> supporterFieldNames  = new ArrayList<>();

    public FormFieldConfigurationModalWindow dropFormFieldByName(String fieldName){
        Button addFieldButton = new ButtonImpl("//*[contains(text(),'" + fieldName + "')]/following-sibling::*//*[@ng-click='selectField(item)']","Add field button of " + fieldName + " in form field configuration modal window");
        new SignupFormElements().performDrop(SignupFormElements.VE.FORM_FIELD);
        if(FormFieldConfigurationModalWindow.supporterFieldNames.isEmpty()) {
            Label supporterNameLabel = new LabelImpl("//*[contains(@id,'FieldEditModal-form')]//tbody//*[.='Supporter ']//ancestor::tr//td[1]","Supporter name label");
            supporterNameLabel.fluentWaitForElementPresenceIgnoringExceptions();
            List<WebElement> elements = driver.findElements(By.xpath("//*[not(@class='unselectable')][td[3][span[.='Supporter ']]]//td[1]"));
            FormFieldConfigurationModalWindow.supporterFieldNames = elements.stream().map(WebElement::getText).collect(Collectors.toList());
        }
        if(addFieldButton.isDisplayed()) addFieldButton.click();
        return this;
    }

    public FormFieldConfigurationModalWindow markFieldAsRequired(){
        markFieldAsRequired(true);
        return this;
    }

    public FormFieldConfigurationModalWindow markFieldAsRequired(Boolean isRequired){
        sleep(2);
        if(requiredCheckbox.isDisplayed()){
            requiredCheckbox.check(isRequired);}
        return this;
    }

    @SuppressWarnings("unchecked")
    public  <T extends HomePage> T saveFormFieldConfiguration(){
        saveButton.click();
        return (T) new HomePage();
    }

    public FormFieldConfigurationModalWindow closeFieldConfigurationModalWindow(){
        cancelButton.click();
        return this;
    }

    public void initializeListWithAllSupporterFields(){
        new SignupFormElements().performDrop(SignupFormElements.VE.FORM_FIELD);
        Label supporterNameLabel = new LabelImpl("//*[contains(@id,'FieldEditModal-form')]//tbody//*[.='Supporter ']//ancestor::tr//td[1]","Supporter name label");
        supporterNameLabel.fluentWaitForElementPresenceIgnoringExceptions();
        List<WebElement> elements = driver.findElements(By.xpath("//*[not(@class='unselectable')][td[3][span[.='Supporter ']]]//td[1]"));
        FormFieldConfigurationModalWindow.supporterFieldNames = elements.stream().map(WebElement::getText).collect(Collectors.toList());
        closeFieldConfigurationModalWindow();
    }

    @SuppressWarnings("unchecked")
    public <T extends HomePage> T dropAllSupporterFieldsOnFormAndMarkAsRequired() {
        initializeListWithAllSupporterFields();
        supporterFieldNames.stream().forEach(name -> {
            dropFormFieldByName(name);
            logger.info(name + " was dropped in the layout");
            if(name.equalsIgnoreCase("designation")) {
                addDesignationFieldOption("Option1");
                markFieldAsRequired().
                        saveFormFieldConfiguration();
            } else {
                markFieldAsRequired().
                        saveFormFieldConfiguration();
            }
        } );
        return (T) new HomePage();
    }

    @SuppressWarnings("unchecked")
    public <T extends HomePage> T dropAllSupporterFieldsOnForm(){
        initializeListWithAllSupporterFields();
        supporterFieldNames.stream().forEach(name -> {
            dropFormFieldByName(name);
            logger.info(name + " was dropped in the layout");
            if(name.equalsIgnoreCase("designation")) {
                addDesignationFieldOption("Option1");
                saveFormFieldConfiguration();
            } else {
                saveFormFieldConfiguration();
            }
        } );
        return (T) new HomePage();
    }

    public FormFieldConfigurationModalWindow addDesignationFieldOption(String optionValue){
        designationFieldOptionTextField.type(optionValue);
        designationFieldAddButton.click();
        return this;
    }

    public FormFieldConfigurationModalWindow selectFieldType(String fieldName) {
        selectFieldButton.changePathAndElementName("fieldNameForReplacement", fieldName, fieldName);
        selectFieldButton.waitElement();
        selectFieldButton.click();
        return this;
    }

    public FormFieldConfigurationModalWindow selectCustomizeTheDisplayOfTheSupporterNameOptionTo(Signatures.CustomizedSupporterNameOptions option) {
        String label = "";
        switch (option) {
            case FIRST_NAME_LAST_NAME: label = "First Name Last Name"; break;
            case FIRST_INITIAL_LAST_INITIAL: label = "First Initial Last Initial"; break;
            case FIRST_NAME_LAST_INITIAL: label = "First Name Last Initial"; break;
            case FIRST_INITIAL_LAST_NAME: label = "First Initial Last Name"; break;
        }
        petitionSignatureCustomizedSupporterNameOptionsList.selectByLabel(label);
        return new FormFieldConfigurationModalWindow();
    }

    public FormFieldConfigurationModalWindow selectCustomizeTheDisplayOfTheSupporterLocationOptionTo(Signatures.CustomizedSupporterLocationOptions option) {
        String label = "";
        switch (option) {
            case DO_NOT_DISPLAY: label = "Do Not Display"; break;
            case STATE_ONLY: label = "State Only"; break;
            case CITY_STATE: label = "City, State"; break;
            case CITY_ONLY: label = "City Only"; break;
        }
        petitionSignatureCustomizedSupporterLocationOptionsList.selectByLabel(label);
        return new FormFieldConfigurationModalWindow();
    }

    public  <T extends HomePage> T saveSignatureElementConfigurationModal(){
        saveSignaturesConfigurationButton.click();
        return (T) new HomePage();
    }
}



