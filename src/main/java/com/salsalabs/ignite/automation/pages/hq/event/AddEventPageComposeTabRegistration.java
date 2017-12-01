package com.salsalabs.ignite.automation.pages.hq.event;

import com.salsalabs.ignite.automation.elements.VE2Elements.EventFormElementsEventPage;
import com.salsalabs.ignite.automation.elements.VE2Elements.EventFormElementsRegistration;
import com.salsalabs.ignite.automation.pages.hq.HomePage;

public class AddEventPageComposeTabRegistration extends HomePage implements AddEventPageComposeTabPage {

    public AddEventPageComposeTabRegistration selectGatewayByName(String gatewayName) {
        return AddEventPageComposeTabPage.super.selectGatewayByName(gatewayName, this.getClass());
    }

    public AddEventPageComposeTabRegistration dropOneColumnRow(){
        return AddEventPageComposeTabPage.super.dropOneColumnRow(this.getClass());
    }

    public AddEventPageComposeTabRegistration dropRegistration(){
        new EventFormElementsRegistration().performDrop(EventFormElementsRegistration.VE.REGISTRATION);
        return this;
    }
}
