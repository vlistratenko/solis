package com.salsalabs.ignite.automation.pages.p2p;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.DragableElement;
import com.salsalabs.ignite.automation.elements.VE2Elements.Form;
import com.salsalabs.ignite.automation.elements.VE2Elements.FormField;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.DragableElementImp;
import com.salsalabs.ignite.automation.elements.impl.SelectBoxImpl;
import com.salsalabs.ignite.automation.pages.hq.activities.FormFieldConfigurationModalWindow;
import org.openqa.selenium.JavascriptExecutor;


public class AddP2PPage_EventPageTab_CheckoutSubTab extends AddP2PPage_EventPageTab<AddP2PPage_EventPageTab_CheckoutSubTab> {

	
	DragableElement formFieldElement = new DragableElementImp("//div[@type='formField']", "Form field element ");
	protected Button idLikeToReceiveUpdatesElement = new ButtonImpl("//*[@name='contactOptInCB']/parent::*", "Edit element");
	
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

	public AddP2PPage_EventPageTab_CheckoutSubTab editIdLikeToReceiveUpdatesCheckBoxProperties(String newFieldLabel, String defaultValue) {
		idLikeToReceiveUpdatesElement.scrollIntoView();
		idLikeToReceiveUpdatesElement.doubleClick();
		FormFieldConfigurationModalWindow modal = new FormFieldConfigurationModalWindow();
		modal.labelTextBox.type(newFieldLabel);
		modal.checkBoxDefaultValue.selectByLabel(defaultValue);
		modal.saveButton.click();
		return this;
	}

	public AddP2PPage_EventPageTab_CheckoutSubTab checkIdLikeToReceiveUpdatesCheckBoxProperties(String fieldLabel, String defaultValue) {
		idLikeToReceiveUpdatesElement.scrollIntoView();
		idLikeToReceiveUpdatesElement.doubleClick();
		FormFieldConfigurationModalWindow modal = new FormFieldConfigurationModalWindow();
		verifier.verifyEquals(
				(String) ((JavascriptExecutor) driver).executeScript("return document.querySelector('#FieldEditModal-form\\\\3a p2pEvent > div.appModalContent > div > div > div.element-config-container.vertical-scroll > div > div > div > div:nth-child(1) > div.row.display-form-elements > div:nth-child(1) > div > div > div > div > input').value"),
				fieldLabel);
		verifier.verifyEquals(((SelectBoxImpl) modal.checkBoxDefaultValue).getSelectedLabel(modal.checkBoxDefaultValue.getPath()), defaultValue, "Check default value", true);
		modal.saveButton.click();
		return this;
	}
}
