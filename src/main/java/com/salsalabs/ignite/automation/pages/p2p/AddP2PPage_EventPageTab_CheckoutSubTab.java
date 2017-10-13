package com.salsalabs.ignite.automation.pages.p2p;

import com.salsalabs.ignite.automation.elements.DragableElement;
import com.salsalabs.ignite.automation.elements.VE2Elements.SignupFormElements;
import com.salsalabs.ignite.automation.elements.impl.DragableElementImp;

public class AddP2PPage_EventPageTab_CheckoutSubTab extends AddP2PPage_EventPageTab<AddP2PPage_EventPageTab_CheckoutSubTab> {

	DragableElement formFieldElement = new DragableElementImp("//div[@type='formField']", "Form field element ");
	
	public AddP2PPage_EventPageTab_CheckoutSubTab addFormField() {
		//elementsButton.click();
		formFieldElement.dragAndDropElement();
		sleep(15);
		return this;
	}
	

	public AddP2PPage_EventPageTab_CheckoutSubTab dropVEFormElement(){
		new SignupFormElements().performDrop(SignupFormElements.VE.FORM);
		return this;
	}
	
	public AddP2PPage_EventPageTab_CheckoutSubTab dropVEFormFieldElement(){
		new SignupFormElements().performDrop(SignupFormElements.VE.FORM_FIELD);
		return this;
	}
}
