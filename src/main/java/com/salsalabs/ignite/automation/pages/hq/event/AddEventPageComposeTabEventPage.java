package com.salsalabs.ignite.automation.pages.hq.event;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.VE2Elements.EventFormElementsEventPage;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.GeneralWebElement;
import com.salsalabs.ignite.automation.elements.impl.SelectBoxImpl;
import com.salsalabs.ignite.automation.pages.hq.HomePage;
import com.salsalabs.ignite.automation.pages.hq.activities.FormFieldConfigurationModalWindow;
import org.openqa.selenium.JavascriptExecutor;

public class AddEventPageComposeTabEventPage extends HomePage implements AddEventPageComposeTabPage{

    protected Button idLikeToReceiveUpdatesElement = new ButtonImpl("//*[@name='contactOptInCB']/parent::*", "Edit element");

    public AddEventPageComposeTabEventPage dropOneColumnRow(){
        return AddEventPageComposeTabPage.super.dropOneColumnRow(this.getClass());
    }

    public AddEventPageComposeTabEventPage dropRegisterButton(){
        new EventFormElementsEventPage().performDrop(EventFormElementsEventPage.VE.REGISTERBUTTON);
        return this;
    }

    private AddEventPageComposeTabEventPage selectGatewayByName(String gatewayName) {
        return AddEventPageComposeTabPage.super.selectGatewayByName(gatewayName, this.getClass());
    }


    public AddEventPageComposeTabEventPage editIdLikeToReceiveUpdatesCheckBoxProperties(String newFieldLabel, String defaultValue) {
        new GeneralWebElement(".//*[contains(text(), 'Checkout')]", "Checkout tab").click();
        idLikeToReceiveUpdatesElement.scrollIntoView();
        idLikeToReceiveUpdatesElement.doubleClick();
        FormFieldConfigurationModalWindow modal = new FormFieldConfigurationModalWindow();
        modal.labelTextBox.type(newFieldLabel);
        modal.checkBoxDefaultValue.selectByLabel(defaultValue);
        modal.saveButton.click();
        return this;
    }

    public AddEventPageComposeTabEventPage checkIdLikeToReceiveUpdatesCheckBoxProperties(String fieldLabel, String defaultValue) {
        idLikeToReceiveUpdatesElement.scrollIntoView();
        idLikeToReceiveUpdatesElement.doubleClick();
        FormFieldConfigurationModalWindow modal = new FormFieldConfigurationModalWindow();
        verifier.verifyEquals(
                (String) ((JavascriptExecutor) driver).executeScript("return document.querySelector('#FieldEditModal-form\\\\3a ticketedEvent > div.appModalContent > div > div > div.element-config-container.vertical-scroll > div > div > div > div:nth-child(1) > div.row.display-form-elements > div:nth-child(1) > div > div > div > div > input').value"),
                fieldLabel);
        verifier.verifyEquals(((SelectBoxImpl) modal.checkBoxDefaultValue).getSelectedLabel(modal.checkBoxDefaultValue.getPath()), defaultValue, "Check default value", true);
        modal.saveButton.click();
        return this;
    }

}
