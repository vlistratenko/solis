package com.salsalabs.ignite.automation.pages.hq.activities.event;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.pages.hq.HomePage;

public class AddEventPageSelectLayoutTab extends HomePage {

    private Button nextToComposeTabButton = new ButtonImpl("//*[@id='btnCompose']","Next:Compose button");

    public AddEventPageComposeTabEventPage clickNextButtonInSelectLayoutTab(){
        nextToComposeTabButton.fluentWaitForElementPresenceIgnoringExceptions();
        nextToComposeTabButton.click();
        waitUntilAngularIsComplete();
        return new AddEventPageComposeTabEventPage();
    }

    public AddEventPageSelectLayoutTab selectLayout(String layout) {
        Button lay = new ButtonImpl("//strong[.='" + layout + "']/ancestor::div[contains(@class,'layout_item')]/descendant::button[contains(@ng-click,'selectItem')]", layout + " layout");
        lay.fluentWaitForElementPresenceIgnoringExceptions();
        lay.scrollIntoView();
        lay.click();
        return this;
    }

}
