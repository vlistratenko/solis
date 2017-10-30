package com.salsalabs.ignite.automation.elements.VE2Elements;

public class FormField extends VEElements {
    public FormField(String elementPath, String name) {
        super(elementPath, name);
    }

    @Override
    public void drop() {
        dragAndDropElementOnLayoutWithFormElement();
    }

    @Override
    public void edit(String fieldName){
        super.edit(fieldName);
    }

    @Override
    public void deleteFormField(String fieldName){
        super.deleteFormField(fieldName);
    }

}
