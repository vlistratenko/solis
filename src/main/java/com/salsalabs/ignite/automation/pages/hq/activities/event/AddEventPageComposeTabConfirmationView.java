package com.salsalabs.ignite.automation.pages.hq.activities.event;

import com.salsalabs.ignite.automation.pages.hq.HomePage;

public class AddEventPageComposeTabConfirmationView extends HomePage implements AddEventPageComposeTabPage  {

    private AddEventPageComposeTabConfirmationView selectGatewayByName(String gatewayName) {
        return AddEventPageComposeTabPage.super.selectGatewayByName(gatewayName, this.getClass());
    }

    public AddEventPageComposeTabConfirmationView dropOneColumnRow(){
        return AddEventPageComposeTabPage.super.dropOneColumnRow(this.getClass());
    }

}
