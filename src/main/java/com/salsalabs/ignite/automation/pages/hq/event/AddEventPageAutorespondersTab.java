package com.salsalabs.ignite.automation.pages.hq.event;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.pages.hq.HomePage;

public class AddEventPageAutorespondersTab extends HomePage{

    Button publishThisEvent = new ButtonImpl("//*[@id='btnGo-autoresponders-publish']","Publish This Event Button");

    public AddEventPageAutorespondersTab clickPublishOnAutorespondersTab(){
        publishThisEvent.fluentWaitForElementPresenceIgnoringExceptions();
        publishThisEvent.click();
        return this;
    }

}
