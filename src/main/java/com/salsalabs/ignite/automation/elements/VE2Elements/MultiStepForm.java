package com.salsalabs.ignite.automation.elements.VE2Elements;

public class MultiStepForm extends VEElements {
    public MultiStepForm(String elementPath, String name) {
        super(elementPath, name);
    }

    @Override
    public void drop() {
        dragAndDropElementOnLayoutWithRowElement();
    }
}