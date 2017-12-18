package com.salsalabs.ignite.automation.pages.hq.activities.event;

import com.salsalabs.ignite.automation.elements.VE2Elements.EventFormElementsCheckout;
import com.salsalabs.ignite.automation.pages.hq.HomePage;

public class AddEventPageComposeTabCheckout extends HomePage implements AddEventPageComposeTabPage {

    public AddEventPageComposeTabCheckout dropVEFormElement(){
        new EventFormElementsCheckout().performDrop(EventFormElementsCheckout.VE.FORM);
        waitUntilAngularIsComplete();
        sleep(1);
        return this;
    }

    private AddEventPageComposeTabCheckout selectGatewayByName(String gatewayName) {
        return AddEventPageComposeTabPage.super.selectGatewayByName(gatewayName, this.getClass());
    }

    public AddEventPageComposeTabCheckout dropOneColumnRow() {
        return AddEventPageComposeTabPage.super.dropOneColumnRow(this.getClass());
    }
}
