package com.salsalabs.ignite.automation.pages.hq.event;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.pages.hq.activities.AddSubscribeWidgetPage;

public class AddEventPageSelectLayoutTab extends AddSubscribeWidgetPage {

    private Button nextToComposeTabButton = new ButtonImpl("//*[@id='btnCompose']","Next:Compose button");

    public AddEventPageComposeTabEventPage clickNextButtonInSelectLayoutTab(){
        nextToComposeTabButton.fluentWaitForElementPresenceIgnoringExceptions();
        nextToComposeTabButton.click();
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
