package com.salsalabs.ignite.automation.pages.hq.event;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.Label;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.GeneralWebElement;
import com.salsalabs.ignite.automation.elements.impl.LabelImpl;
import com.salsalabs.ignite.automation.pages.hq.activities.AddSubscribeWidgetPage;

public class AddEventPageAutorespondersTab extends AddSubscribeWidgetPage{

    Button publishThisEvent = new ButtonImpl("//*[@id='btnGo-autoresponders-publish']","Publish This Event Button");
    Button nextToAutorespondersButton = new ButtonImpl("//*[@id='btnGo-compose-autoresponders']","Next:Autoresponders button");
    Label urlLabel = new LabelImpl(".//label[text()='URL']/following-sibling::label", "URL label");
    Button nextToAutorespondersButton = new ButtonImpl("//*[@id='btnGo-compose-autoresponders']","Next:Autoresponders button");

    public AddEventPageAutorespondersTab clickPublishOnAutorespondersTab(){
        publishThisEvent.fluentWaitForElementPresenceIgnoringExceptions();
        publishThisEvent.click();
        urlLabel.fluentWaitForElementPresenceIgnoringExceptions();
        return this;
    }

    public void openWidget(String activityName) {
        GeneralWebElement link = new GeneralWebElement(".//a[contains(text(), '" + activityName.toLowerCase() + "') and not(contains(text(), 'register'))]", "Activity link");
        link.fluentWaitForElementPresenceIgnoringExceptions();
        link.click();
    }

    public AddEventPageAutorespondersTab clickNextButtonInComposeTab(){
        nextToAutorespondersButton.fluentWaitForElementPresenceIgnoringExceptions();
        nextToAutorespondersButton.click();
        return this;
    }
}
