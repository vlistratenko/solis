package com.salsalabs.ignite.automation.pages.hq.activities.event;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.Panel;
import com.salsalabs.ignite.automation.elements.VE2Elements.EventFormElementsEventPage;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.PanelImpl;
import com.salsalabs.ignite.automation.pages.hq.HomePage;
import com.salsalabs.ignite.automation.pages.p2p.AddP2PPage_EventPageTab_CheckoutSubTab;

public class AddEventPageComposeTabEventPage extends HomePage implements AddEventPageComposeTabPage{
    Button checkoutSubTab = new ButtonImpl("//a[.='Checkout']", "Checkout subtab ");
    Panel checkoutVEPanel = new PanelImpl("//div[@autotest-id='compose-tab-checkout']", "Event tab, Checkout sub tab");

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

    public AddP2PPage_EventPageTab_CheckoutSubTab openCheckoutSubTab() {
        checkoutSubTab.waitElement();
        checkoutSubTab.click();
        checkoutVEPanel.waitElement();
        return new AddP2PPage_EventPageTab_CheckoutSubTab();
    }

}
