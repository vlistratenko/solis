package com.salsalabs.ignite.automation.elements.VE2Elements;

import com.salsalabs.ignite.automation.elements.Panel;
import com.salsalabs.ignite.automation.elements.impl.ContentEditTextBoxImpl;
import com.salsalabs.ignite.automation.elements.impl.PanelImpl;

public class TextVEElement extends VEElements {

	public static ContentEditTextBoxImpl textElementContent = new ContentEditTextBoxImpl("//iframe[contains(@title,'ckeditor')]" ,"//*[@contenteditable='true']", "Text element in the VE", true);
	public static String elPath = "//*[contains(text(),'Text')]";
	public static PanelImpl droppedTextElement = new PanelImpl("//div[contains(@class, 'content-render-content')]/p", "Text element in the VE");
	
    public TextVEElement(String path, String name) {
        super(path, name);
    }

    @Override
    public void drop() {
        dragAndDropElementOnLayoutWithFormElement();
    }
    

    public void enterText(String text) {
    	droppedTextElement.moveToElement();
    	super.openEditElementPopUp("Text");
        textElementContent.type(text);
        super.save();
    }
/*
    @Override
    public VEElements configure(VEElements el) {
        return null;
    }

    @Override
    public VEElements edit(VEElements el) {
        return null;
    }

    @Override
    public VEElements delete(VEElements el) {
        return null;
    }*/
    
    protected String getTextElementPath() {
		return textElementContent.getPath(); 
	}
}
