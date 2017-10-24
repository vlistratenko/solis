package com.salsalabs.ignite.automation.pages.hq.activities;

import com.google.sitebricks.client.Web;
import com.salsalabs.ignite.automation.common.Browser;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.CheckBox;
import com.salsalabs.ignite.automation.elements.VE2Elements.SignupFormElements;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.CheckBoxImpl;
import com.salsalabs.ignite.automation.pages.hq.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class FormFieldConfigurationModalWindow extends HomePage {

    Button saveButton = new ButtonImpl("//*[@id='FieldEditModal-form:subscribe']/div[3]/a[2]","Save Content button for field configuration");
    Button cancelButton = new ButtonImpl("//*[@id='FieldEditModal-form:subscribe']/div[3]/a[1]","Cancel button of fields configuration modal window");
    CheckBox requiredCheckbox = new CheckBoxImpl("//*[@class='appModalContent']//*[@type='checkbox']","Checkbox to mark fields as required");

    private static List<String> supporterFieldsNames = new ArrayList<>();

    public FormFieldConfigurationModalWindow(){
        if(supporterFieldsNames.isEmpty()) supporterFieldsNames = getAllSupporterFieldsNames();

    }

    public ArrayList<String> getAllSupporterFieldsNames(){
        ArrayList<String> supporterFieldNames = new ArrayList<>();
        new SignupFormElements().performDrop(SignupFormElements.VE.FORM_FIELD);
        fluentWaitForElementPresenceIgnoringExceptions("//*[@id='FieldEditModal-form:subscribe']//tbody//*[.='Supporter ']//ancestor::tr//td[1]");
        List<WebElement> elements = driver.findElements(By.xpath("//*[@id='FieldEditModal-form:subscribe']//tbody//*[.='Supporter ']//ancestor::tr//td[1]"));
        for (WebElement element : elements) {
            supporterFieldNames.add(element.getText().trim());
        } closeFieldConfigurationModalWindow();
        return supporterFieldNames;
    }

    public FormFieldConfigurationModalWindow dropFormFieldByName(String fieldName){
        Button addFieldButton = new ButtonImpl("//*[contains(text(),'" + fieldName + "')]/following-sibling::*//*[@ng-click='selectField(item)']","Add field button of " + fieldName + " in form field configuration modal window");
        new SignupFormElements().performDrop(SignupFormElements.VE.FORM_FIELD);
        if(addFieldButton.isDisplayed()) addFieldButton.click();
        return this;
    }

    public FormFieldConfigurationModalWindow markFieldAsRequired(){
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
        List<String> supporterFieldNames = supporterFieldsNames;
        for (String name : supporterFieldNames) {
            dropFormFieldByName(name);
            logger.info(name + " was dropped in the layout");
            markFieldAsRequired().saveFieldConfiguration();
        }
        return (T) new HomePage();
    }

    public <T extends HomePage> T dropAllSupporterFieldsOnForm(){
        List<String> supporterFieldNames = supporterFieldsNames;
        for (String name : supporterFieldNames) {
            dropFormFieldByName(name);
            saveFieldConfiguration();
        }
        return (T) new HomePage();

        }
    }



