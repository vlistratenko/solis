package com.salsalabs.ignite.automation.pages.p2p;

import com.salsalabs.ignite.automation.elements.DragableElement;
import com.salsalabs.ignite.automation.elements.VE2Elements.Form;
import com.salsalabs.ignite.automation.elements.VE2Elements.FormField;
import com.salsalabs.ignite.automation.elements.impl.DragableElementImp;


public class AddP2PPage_EventPageTab_CheckoutSubTab extends AddP2PPage_EventPageTab<AddP2PPage_EventPageTab_CheckoutSubTab> {

	
	DragableElement formFieldElement = new DragableElementImp("//div[@type='formField']", "Form field element ");
	
	/*public FormFieldConfigurationModalWindow addFormField() {
		//elementsButton.click();
		formFieldElement.dragAndDropElement();
		sleep(15);
		return new FormFieldConfigurationModalWindow();
	}*/
/*	

	public AddP2PPage_EventPageTab_CheckoutSubTab dropVEFormElement(){
		new P2PFormElements_Checkout().performDrop(P2PFormElements_Checkout.VE.FORM);
		return this;
	}*/
	
	public AddP2PPage_EventPageTab_CheckoutSubTab dropFormField(String fieldName, Boolean isRequired){
		Form.droppedFormElement.waitElement();
		FormField.formFieldElement.dropToForm().
			selectFieldType(fieldName).
			markFieldAsRequired(isRequired).
			saveFieldConfiguration();		
		return this;
	}
}
