package com.salsalabs.ignite.automation.pages.hq.event;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.GeneralWebElement;
import com.salsalabs.ignite.automation.pages.hq.HomePage;

public class AddEventPageAutorespondersTab extends HomePage{

    Button publishThisEvent = new ButtonImpl("//*[@id='btnGo-autoresponders-publish']","Publish This Event Button");


    public AddEventPageAutorespondersTab clickPublishOnAutorespondersTab(){
        publishThisEvent.fluentWaitForElementPresenceIgnoringExceptions();
        publishThisEvent.click();
        return this;
    }

    public void openWidget(String activityName) {
        GeneralWebElement link = new GeneralWebElement(".//a[contains(text(), '" + activityName.toLowerCase() + "') and not(contains(text(), 'register'))]", "Activity link");
        link.fluentWaitForElementPresenceIgnoringExceptions();
        link.click();
    }
}
