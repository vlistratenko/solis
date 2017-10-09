package com.salsalabs.ignite.automation.pages.p2p;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;

public class AddP2PPage_EventPageTab extends AddP2PPage {

	Button eventPageSubTab = new ButtonImpl("//a[.='Event Page']", "Event Page subtab ");
	Button registrationSubTab = new ButtonImpl("//a[.='Registration']", "Registration subtab ");
	Button checkoutSubTab = new ButtonImpl("//a[.='Checkout']", "Checkout subtab ");
	Button confirmationViewSubTab = new ButtonImpl("//a[.='Confirmation View']", "Confirmation View subtab ");
	Button elementsButton = new ButtonImpl("//button[@title='Content Elements']", "VE Elements button");
	
	public AddP2PPage_EventPageTab_EventPageSubTab openEventPageSubTab() {
		eventPageSubTab.click();
		return new AddP2PPage_EventPageTab_EventPageSubTab();
	}
	
	public AddP2PPage_EventPageTab_RegistrationSubTab openRegistrationSubTab() {
		registrationSubTab.click();
		return new AddP2PPage_EventPageTab_RegistrationSubTab();
	}
	
	public AddP2PPage_EventPageTab_CheckoutSubTab openCheckoutSubTab() {
		checkoutSubTab.waitElement(10);
		checkoutSubTab.click();
		return new AddP2PPage_EventPageTab_CheckoutSubTab();
	}
	
	public AddP2PPage_EventPageTab_ConfirmationViewSubTab openConfirmationViewSubTabSubTab() {
		confirmationViewSubTab.click();
		return new AddP2PPage_EventPageTab_ConfirmationViewSubTab();
	}
	
	public AddP2PPage_EventPageTab openElementsPupUp() {
		elementsButton.click();
		return this;
	}
}
