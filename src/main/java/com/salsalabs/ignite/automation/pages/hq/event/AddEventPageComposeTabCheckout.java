package com.salsalabs.ignite.automation.pages.hq.event;

import com.salsalabs.ignite.automation.elements.VE2Elements.EventFormElementsCheckout;
import com.salsalabs.ignite.automation.elements.VE2Elements.SignupFormElements;
import com.salsalabs.ignite.automation.pages.hq.HomePage;
import com.salsalabs.ignite.automation.pages.hq.activities.ActivitiesPage;
import com.salsalabs.ignite.automation.pages.hq.activities.AddSubscribeWidgetPage;

public class AddEventPageComposeTabCheckout extends HomePage implements AddEventPageComposeTabPage {

    public AddEventPageComposeTabCheckout dropVEFormElement(){
        new EventFormElementsCheckout().performDrop(EventFormElementsCheckout.VE.FORM);
        return this;
    }

    private AddEventPageComposeTabCheckout selectGatewayByName(String gatewayName) {
        return AddEventPageComposeTabPage.super.selectGatewayByName(gatewayName, this.getClass());
    }

    public AddEventPageComposeTabCheckout dropOneColumnRow() {
        return AddEventPageComposeTabPage.super.dropOneColumnRow(this.getClass());
    }
}
