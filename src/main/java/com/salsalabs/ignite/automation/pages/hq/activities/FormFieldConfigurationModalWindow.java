package com.salsalabs.ignite.automation.pages.hq.activities;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.google.sitebricks.client.Web;
import com.salsalabs.ignite.automation.common.Browser;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.CheckBox;
import com.salsalabs.ignite.automation.elements.Label;
import com.salsalabs.ignite.automation.elements.Table;
import com.salsalabs.ignite.automation.elements.VE2Elements.SignupFormElements;
import com.salsalabs.ignite.automation.elements.VE2Elements.VEElements;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.CheckBoxImpl;
import com.salsalabs.ignite.automation.elements.impl.LabelImpl;
import com.salsalabs.ignite.automation.elements.impl.TableImpl;
import com.salsalabs.ignite.automation.pages.hq.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class FormFieldConfigurationModalWindow extends HomePage {

    Button saveButton = new ButtonImpl("//*[contains(@id,'FieldEditModal-form')]/div[3]/a[2]","Save Content button for field configuration");
    Button cancelButton = new ButtonImpl("//*[contains(@id,'FieldEditModal-form')]/div[3]/a[1]","Cancel button of fields configuration modal window");
    CheckBox requiredCheckbox = new CheckBoxImpl("//*[@class='appModalContent']//*[@type='checkbox'][@ng-model='fieldConfig.required']","Checkbox to mark fields as required");

    private static List<String> supporterFieldsNames = new ArrayList<>();

    public FormFieldConfigurationModalWindow(){
        if(supporterFieldsNames.isEmpty()) supporterFieldsNames = getAllSupporterFieldsNames();
    }

    public ArrayList<String> getAllSupporterFieldsNames(){
        ArrayList<String> supporterFieldNames = new ArrayList<>();
        new SignupFormElements().performDrop(SignupFormElements.VE.FORM_FIELD);
        Label supporterNameLabel = new LabelImpl("//*[contains(@id,'FieldEditModal-form')]//tbody//*[.='Supporter ']//ancestor::tr//td[1]","Supporter name label");
        supporterNameLabel.fluentWaitForElementPresenceIgnoringExceptions();
        List<WebElement> elements = driver.findElements(By.xpath("//*[not(@class='unselectable')][td[3][span[.='Supporter ']]]//td[1]"));
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



