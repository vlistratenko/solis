package com.salsalabs.ignite.automation.elements.VE2Elements;

import com.salsalabs.ignite.automation.elements.Panel;
import com.salsalabs.ignite.automation.elements.impl.PanelImpl;

public class GeneralFormsElements<T> {

    VEElements element = null;
    private Panel generalElementPanel = new PanelImpl("//div[@class='content-render']", "Element panel");

    public enum veRows {
        ONECOLUMN
    }

    GeneralFormsElements(){}

    public T performDrop(Enum<?> ve) {
        String value = ve.name();
        int elementsCount = generalElementPanel.getElementsCount()+1;
        switch (value) {
            case "TEXT": {element = new TextVEElement("//*[contains(text(),'Text')]", "Text element"); element.drop(); break;}
            case "FORM_FIELD": {element = new FormField("//*[@class='icon-insert-template']//*[.='Form Field']", "Form Field element"); element.drop(); break;}
            case "ONECOLUMN": {element = new OneColumnRow("//*[contains(text(),'1-Column')]", "One column Row element"); element.drop(); break;}
            case "FORM": {element = new Form("//span[.='Form']", "Form element"); element.drop(); break;}
            case "REGISTERBUTTON": {element = new RegisterButton("//*[@class='icon-icon-button']//*[.='Register Button']","Register Button element");element.drop(); break;}
            case "REGISTRATION": {element = new Registration("//*[@class='icon-clipboard']//span[.='Registration']","Registration element");element.drop(); break;}
            case "DONATEBUTTON": {element = new DonateButton("//span[.='Donate Button']", "Donate Button"); element.drop(); break;}

        }
        if (!value.contains("COLUMN") && !value.contains("FORM_FIELD")) {
        	generalElementPanel.changePath("", generalElementPanel.getPath() + "[" + elementsCount + "]");
            generalElementPanel.waitElement();
		}
        return (T) this;
    }

    public T performEdit(Enum<?> ve, String fieldName) {
        String value = ve.name();
        switch (value) {
            case "FORM_FIELD": {element = new FormField("//label[.='" + fieldName + "']/ancestor::*[@class='content-render-wrapper']//*[@title='Edit']", "Form Field element"); element.edit(fieldName); break;}
        } return (T)this;    }

    public T performDelete(Enum<?> ve, String fieldName) {
        String value = ve.name();
        switch (value) {
            case "FORM_FIELD": {element = new FormField("//*[@title='Delete']/../../..//label[.='" + fieldName + "']/../../..//*[@title='Delete']", "Form Field element"); element.deleteFormField(fieldName); break;}
        } return (T)this;
    }
}