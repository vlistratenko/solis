package com.salsalabs.ignite.automation.pages.hq.event;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;
import com.salsalabs.ignite.automation.pages.hq.HomePage;

public class AddEventPageSetupTab extends HomePage {

    private TextBox referenceNameTextField = new TextBoxImpl("//*[@id='activityForm']//input[@type='text'][@name='name']","'Reference name' text field");
    private TextBox publicilyVisibleNameTextField = new TextBoxImpl("//*[@id='activityForm']//input[@type='text'][@name='publicName']","'Publicly visible name' text field");
    private Button nextToTicketsButton = new ButtonImpl("//*[@id='btnGo-setup-tickets']","Next:Tickets button in Setup tab");

    public AddEventPageSetupTab specifyEventReferenceName(String eventName) {
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
