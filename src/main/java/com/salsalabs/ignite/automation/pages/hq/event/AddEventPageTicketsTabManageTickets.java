package com.salsalabs.ignite.automation.pages.hq.event;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.pages.hq.HomePage;
import com.salsalabs.ignite.automation.pages.hq.activities.event.AddEventPageSelectLayoutTab;
import com.salsalabs.ignite.automation.pages.hq.activities.event.AddEventPageTicketsTabNewTicket;

public class AddEventPageTicketsTabManageTickets extends HomePage {

    private Button createTicketButton = new ButtonImpl("//a[@class='button'][@href]","Create a ticket button");
    private Button nextToLayoutsPageButton = new ButtonImpl("//*[@id='btnGo-tickets-compose']","Create a ticket button");

    public AddEventPageTicketsTabNewTicket clickCreateTicketButton(){
        createTicketButton.fluentWaitForElementPresenceIgnoringExceptions();
        createTicketButton.click();
        return new AddEventPageTicketsTabNewTicket();
    }

    public AddEventPageSelectLayoutTab clickNextButtonInTicketsTab(){
        nextToLayoutsPageButton.click();
        return new AddEventPageSelectLayoutTab();
    }




}
