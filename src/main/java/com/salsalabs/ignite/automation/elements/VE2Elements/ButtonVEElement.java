package com.salsalabs.ignite.automation.elements.VE2Elements;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;

public class ButtonVEElement extends VEElements {
	Button linkTab= new ButtonImpl("//a[.='Link']", "Link tab in the button config popup");

    public ButtonVEElement(String path, String name) {
        super(path, name);
    }

    @Override
    public void drop() {
        dragAndDropElementOnLayoutWithFormElement();
    }
    
    public void setLink(String linkName) {
    	super.openEditElementPopUp("Button");
    	linkTab.waitElement();
    	linkTab.click();
    	Button link = new ButtonImpl("//*[text()='" + linkName + "']/ancestor::tr/descendant::span[@class='table-list-icon icon']", "Link tab in the button config popup");
    	link.click();
    	saveContent.click();
    	
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
}
