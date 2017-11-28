package com.salsalabs.ignite.automation.elements.VE2Elements;

import com.salsalabs.ignite.automation.pages.hq.activities.FormFieldConfigurationModalWindowBasic;

public class FormField extends VEElements {
    
	public static FormField formFieldElement = new FormField("//*[@class='icon-insert-template']//*[.='Form Field']", "Form Field element");
	
	public FormField(String elementPath, String name) {
        super(elementPath, name);
    }

    @Override
    public void drop() {
        dragAndDropElementOnLayoutWithFormElement();
    }
    

    public FormFieldConfigurationModalWindowBasic dropToForm() {
    	dragAndDropElementOnElement(Form.droppedFormElement);
    	return new FormFieldConfigurationModalWindowBasic();
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
