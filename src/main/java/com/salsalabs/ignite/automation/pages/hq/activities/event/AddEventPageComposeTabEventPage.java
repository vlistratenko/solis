package com.salsalabs.ignite.automation.pages.hq.activities.event;

import com.salsalabs.ignite.automation.elements.VE2Elements.EventFormElementsEventPage;
import com.salsalabs.ignite.automation.pages.hq.HomePage;

public class AddEventPageComposeTabEventPage extends HomePage implements AddEventPageComposeTabPage{

    public AddEventPageComposeTabEventPage dropOneColumnRow(){
        return AddEventPageComposeTabPage.super.dropOneColumnRow(this.getClass());
    }

    public AddEventPageComposeTabEventPage dropDonateButton(){
        new EventFormElementsEventPage().performDrop(EventFormElementsEventPage.VE.DONATEBUTTON);
        return this;
    }

    public AddEventPageComposeTabEventPage dropRegisterButton(){
        new EventFormElementsEventPage().performDrop(EventFormElementsEventPage.VE.REGISTERBUTTON);
        return this;
    }

    private AddEventPageComposeTabEventPage selectGatewayByName(String gatewayName) {
        return AddEventPageComposeTabPage.super.selectGatewayByName(gatewayName, this.getClass());
    }



}
