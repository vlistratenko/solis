package com.salsalabs.ignite.automation.pages.p2p;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.VE2Elements.P2PFormElements_Checkout;
import com.salsalabs.ignite.automation.elements.VE2Elements.P2PFormElements_Events;
import com.salsalabs.ignite.automation.elements.VE2Elements.P2PFormElements_Registration;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;

public class AddP2PPage_EventPageTab<T> extends AddP2PPage {

	Button eventPageSubTab = new ButtonImpl("//a[.='Event Page']", "Event Page subtab ");
	Button registrationSubTab = new ButtonImpl("//a[.='Registration']", "Registration subtab ");
	Button checkoutSubTab = new ButtonImpl("//a[.='Checkout']", "Checkout subtab ");
	Button confirmationViewSubTab = new ButtonImpl("//a[.='Confirmation View']", "Confirmation View subtab ");
	Button elementsButton = new ButtonImpl("//button[@title='Content Elements']", "VE Elements button");
	Button clickNextToFundraiserPage = new ButtonImpl("//button[@id='btnGo-compose-composePersonal']", "Next: Fundraiser Page Template");
	/*
	Panel droppedRegisterElement = new PanelImpl("//div[@class='registration']", "Registration in the VE");
	Panel droppedRegisterButtonElement = new PanelImpl("//a[.='Register']", "Register button in the VE");
	Panel droppedDonateButtonElement = new PanelImpl("//a[contains(., 'Donate')]", "Donate button in the VE");*/
	
	public AddP2PPage_EventPageTab_EventPageSubTab openEventPageSubTab() {
		eventPageSubTab.click();
		eventVEPanel.waitElement();
		return new AddP2PPage_EventPageTab_EventPageSubTab();
	}
	
	public AddP2PPage_EventPageTab_RegistrationSubTab openRegistrationSubTab() {
		registrationSubTab.click();
		registrationVEPanel.waitElement(); 
		return new AddP2PPage_EventPageTab_RegistrationSubTab();
	}
	
	public AddP2PPage_EventPageTab_CheckoutSubTab openCheckoutSubTab() {
		checkoutSubTab.waitElement();
		checkoutSubTab.click();
		checkoutVEPanel.waitElement();
		return new AddP2PPage_EventPageTab_CheckoutSubTab();
	}
	
	@SuppressWarnings("unchecked")
	public T openConfirmationViewSubTab() {
		confirmationViewSubTab.waitElement();
		confirmationViewSubTab.scrollIntoView();
		confirmationViewSubTab.click();
		confirmationVEPanel.waitElement();
		return (T)this;
	}
	
	public AddP2PPage_EventPageTab<T> openElementsPupUp() {
		elementsButton.click();
		return this;
	}
	
	public AddP2PPage_FundraiserPageTab_PersonalFundraisingSubTab clickNextToEventPageButton() {
		clickNextToFundraiserPage.waitElement();
		sleep(3);
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
	
	@SuppressWarnings("unchecked")
	public T editVETextElement(String value){
		textElement.enterText(value);
		return (T)this;
	}
	
	@SuppressWarnings("unchecked")
	public T dropVEFormElement(){
		new P2PFormElements_Events().performDrop(P2PFormElements_Checkout.VE.FORM);
		//droppedFormElement.waitElement(15);
		return (T)this;
	}
	
	@SuppressWarnings("unchecked")
	public T dropVERegisterButtonElement(){
		new P2PFormElements_Events().performDrop(P2PFormElements_Events.VE.REGISTERBUTTON);
		//droppedRegisterButtonElement.waitElement(15);
		return (T)this;
	}
	
	@SuppressWarnings("unchecked")
	public T dropVEREgistrationElement(){
		new P2PFormElements_Events().performDrop(P2PFormElements_Registration.VE.REGISTRATION);
		//droppedRegisterElement.waitElement(10);
		return (T)this;
	}
	
	@SuppressWarnings("unchecked")
	public T dropVEDonateButtonElement(){
		new P2PFormElements_Events().performDrop(P2PFormElements_Events.VE.DONATEBUTTON);
		//droppedDonateButtonElement.waitElement(10);
		return (T)this;
	}
	
}
