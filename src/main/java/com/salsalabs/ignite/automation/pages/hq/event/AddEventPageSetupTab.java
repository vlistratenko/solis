package com.salsalabs.ignite.automation.pages.hq.event;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;
import com.salsalabs.ignite.automation.pages.hq.activities.AddSubscribeWidgetPage;

public class AddEventPageSetupTab extends AddSubscribeWidgetPage {

    private TextBox referenceNameTextField = new TextBoxImpl("//*[@name='name']","'Reference name' text field");
    private TextBox publicilyVisibleNameTextField = new TextBoxImpl("//*[@name='publicName']","'Publicly visible name' text field");
    private Button nextToTicketsButton = new ButtonImpl("//*[@id='btnGo-setup-tickets']","Next:Tickets button in Setup tab");

    public AddEventPageSetupTab specifyEventReferenceName(String eventName) {
        this.widgetName = eventName;
        referenceNameTextField.fluentWaitForElementPresenceIgnoringExceptions();
        referenceNameTextField.type(eventName);
        return this;
    }

    public AddEventPageSetupTab specifyEventPublicilyVisibleName(String eventPublicName) {
        publicilyVisibleNameTextField.fluentWaitForElementPresenceIgnoringExceptions();
        publicilyVisibleNameTextField.type(eventPublicName);
        return this;
    }

    public AddEventPageTicketsTabNewTicket clickNextButtonInSetupTab(){
        nextToTicketsButton.click();
        return new AddEventPageTicketsTabNewTicket();
    }

}
