package com.salsalabs.ignite.automation.pages.p2p;

import com.salsalabs.ignite.automation.elements.DragableElement;
import com.salsalabs.ignite.automation.elements.impl.DragableElementImp;

public class AddP2PPage_EventPageTab_CheckoutSubTab extends AddP2PPage_EventPageTab {

	DragableElement formFieldElement = new DragableElementImp("//div[@type='formField']", "Form field element ");
	
	public AddP2PPage_EventPageTab_CheckoutSubTab addFormField() {
		//elementsButton.click();
		formFieldElement.dragAndDropElement();
		sleep(15);
		return this;
	} 
}
