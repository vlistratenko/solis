package com.salsalabs.ignite.automation.elements.VE2Elements;

public class GeneralFormsElements<T> {

    VEElements element = null;
    
    public enum veRows {
        ONECOLUMN
    }

    GeneralFormsElements(){}

    public T performDrop(Enum<?> ve) {
        String value = ve.name();
        switch (value) {
            case "TEXT": {element = new Text("//*[contains(text(),'Text')]", "Text element"); element.drop(); break;}
            case "FORM_FIELD": {element = new FormField("//*[@class='icon-insert-template']//*[.='Form Field']", "Form Field element"); element.drop(); break;}
            case "ONECOLUMN": {element = new OneColumnRow("//*[contains(text(),'1-Column')]", "One column Row element"); element.drop(); break;}
            case "FORM": {element = new Form("//span[.='Form']", "Form element"); element.drop(); break;}
        } return (T) this;
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