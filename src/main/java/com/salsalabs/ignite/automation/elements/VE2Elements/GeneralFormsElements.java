package com.salsalabs.ignite.automation.elements.VE2Elements;

public class GeneralFormsElements<T> {

    VEElements element = null;
    
    public enum veRows {
        ONECOLUMN,
        TWOCOLUMN,
        THREECOLUMN,
        FOURCOLUMN,
        LSIDEBAR,
        RSIDEBAR,
        HEADER,
        FOOTER;}

    GeneralFormsElements(){}

    public T performDrop(Enum<?> ve) {
        String value = ve.name();
        switch (value) {
            case "TEXT": {element = new Text("//*[contains(text(),'Text')]", "Text element"); element.drop(); break;}
            case "FORM_FIELD": {element = new FormField("//*[contains(text(),'Form Field')]", "Form Field element"); element.drop(); break;}
            case "ONECOLUMN": {element = new OneColumnRow("//*[contains(text(),'1-Column')]", "One column Row element"); element.drop(); break;}
            case "FORM": {element = new Form("//span[.='Form']", "Form element"); element.drop(); break;}
           /*

           case "IMAGE": {element = new Image(); element.drop(); break;}
            case "DIVIDER": {element = new Divider(); element.drop(); break;}
            case "LOGO": {element = new Logo(); element.drop(); break;}
            case "BUTTON": {element = new Button(); element.drop(); break;}
            case "CALLOUT": {element = new Callout(); element.drop(); break;}
            case "SHARE": {element = new Share(); element.drop(); break;}
            case "FOLLOW": {element = new Follow(); element.drop(); break;}
            case "FORM": {element = new Form(); element.drop(); break;}

            case "STEPS_NAVIGATION": {element = new StepsNavigation(); element.drop(); break;}
            case "HTML": {element = new Html(); element.drop(); break;}
            case "TRACKER": {element = new Tracker(); element.drop(); break;}
            case "SIGNATURES": {element = new Signatures(); element.drop(); break;}
            TWOCOLUMN,
            THREECOLUMN,
            FOURCOLUMN,
            LSIDEBAR,
            RSIDEBAR,
            HEADER,
            FOOTER;*/
        } return (T) this;
    }

    public T performEdit(Enum<?> ve, String fieldName) {
        String value = ve.name();
        switch (value) {
            case "FORM_FIELD": {element = new FormField("//label[.='" + fieldName + "']/ancestor::*[@class='content-render-wrapper']//*[@title='Edit']", "Form Field element"); element.edit(fieldName); break;}
        } return (T)this;
    }
}