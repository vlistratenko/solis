package com.salsalabs.ignite.automation.pages.hq.activities.event;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.pages.hq.activities.AddSubscribeWidgetPage;
import com.salsalabs.ignite.automation.pages.hq.event.AddEventPageSetupTab;

public class EventsPage extends AddSubscribeWidgetPage {

    private Button createEventButton = new ButtonImpl("//button[@autotest-id='btn_create_ticketed_event_dashboard']","Create Event button");

    public AddEventPageSetupTab clickCreateAnEventButton(){
        createEventButton.fluentWaitForElementPresenceIgnoringExceptions();
        createEventButton.click();
        return new AddEventPageSetupTab();
    }
}
