package com.salsalabs.ignite.automation.pages.p2p;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.VE2Elements.P2PFormElements_Events;
import com.salsalabs.ignite.automation.elements.VE2Elements.SignupFormElements;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;

public class AddP2PPage_EventPageTab<T> extends AddP2PPage {

	Button eventPageSubTab = new ButtonImpl("//a[.='Event Page']", "Event Page subtab ");
	Button registrationSubTab = new ButtonImpl("//a[.='Registration']", "Registration subtab ");
	Button checkoutSubTab = new ButtonImpl("//a[.='Checkout']", "Checkout subtab ");
	Button confirmationViewSubTab = new ButtonImpl("//a[.='Confirmation View']", "Confirmation View subtab ");
	Button elementsButton = new ButtonImpl("//button[@title='Content Elements']", "VE Elements button");
	Button clickNextToFundraiserPage = new ButtonImpl("//button[@id='btnGo-compose-composePersonal']", "Next: Fundraiser Page Template");
	
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
	
	public AddP2PPage_EventPageTab_ConfirmationViewSubTab openConfirmationViewSubTab() {
		confirmationViewSubTab.click();
		return new AddP2PPage_EventPageTab_ConfirmationViewSubTab();
	}
	
	public AddP2PPage_EventPageTab<T> openElementsPupUp() {
		elementsButton.click();
		return this;
	}
	
	public AddP2PPage_FundraiserPageTab_PersonalFundraisingSubTab clickNextButton() {
		clickNextToFundraiserPage.click();
		return new AddP2PPage_FundraiserPageTab_PersonalFundraisingSubTab();
	}

	@SuppressWarnings("unchecked")
	public T dropOneColumnRow(){
		new P2PFormElements_Events().performDrop(P2PFormElements_Events.veRows.ONECOLUMN);
		return (T)this;
	}


	@SuppressWarnings("unchecked")
	public T dropVETextElement(){
		new P2PFormElements_Events().performDrop(P2PFormElements_Events.VE.TEXT);
		return (T)this;
	}
}
