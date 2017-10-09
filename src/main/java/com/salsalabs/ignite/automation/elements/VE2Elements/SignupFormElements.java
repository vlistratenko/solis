package com.salsalabs.ignite.automation.elements.VE2Elements;

public class SignupFormElements extends GeneralFormsElements {

    public enum VE {
        TEXT,
        IMAGE,
        DIVIDER,
        LOGO,
        BUTTON,
        CALLOUT,
        SHARE,
        FOLLOW,
        FORM,
        FORM_FIELD,
        STEPS_NAVIGATION,
        HTML,
        ONECOLUMN,
        TWOCOLUMN,
        THREECOLUMN,
        FOURCOLUMN,
        LSIDEBAR,
        RSIDEBAR,
        HEADER,
        FOOTER;}

    public SignupFormElements performDrop(VE el) {
        super.drop(el);
        return new SignupFormElements();
    }

    public SignupFormElements performEdit(VE el, String fieldLabel) {
        super.edit(el, fieldLabel);
        return new SignupFormElements();
    }

}
