package com.salsalabs.ignite.automation.elements.VE2Elements;

import com.salsalabs.ignite.automation.elements.Panel;
import com.salsalabs.ignite.automation.elements.impl.PanelImpl;

public class Form extends VEElements {
    
	public static Panel droppedFormElement = new PanelImpl("//div[contains(@class, 'render-container-formcontainer')]/descendant::*[contains(@class, 'ui-droppable')]", "Form in the VE");
	
	public Form(String elementPath, String name) {
        super(elementPath, name);
    }

    @Override
    public void drop() {
        dragAndDropOnRow();
    }

}
