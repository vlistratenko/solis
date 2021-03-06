package com.salsalabs.ignite.automation.pages.hq.activities.event;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.pages.hq.HomePage;

public class AddEventPageTicketsTabManageTickets extends HomePage {

    private Button createTicketButton = new ButtonImpl("//a[@class='button'][@href]","Create a ticket button");
    private Button nextToLayoutsPageButton = new ButtonImpl("//*[@id='btnGo-tickets-compose']","Next:Compose button");

    public AddEventPageTicketsTabNewTicket clickCreateTicketButton(){
        createTicketButton.fluentWaitForElementPresenceIgnoringExceptions();
        createTicketButton.click();
        return new AddEventPageTicketsTabNewTicket();
    }

    public AddEventPageSelectLayoutTab clickNextButtonInTicketsTab(){
        nextToLayoutsPageButton.click();
        nextToLayoutsPageButton.click();
        return new AddEventPageSelectLayoutTab();
    }




}
