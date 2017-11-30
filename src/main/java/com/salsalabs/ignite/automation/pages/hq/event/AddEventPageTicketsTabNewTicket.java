package com.salsalabs.ignite.automation.pages.hq.event;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;
import com.salsalabs.ignite.automation.pages.hq.HomePage;

public class AddEventPageTicketsTabNewTicket extends HomePage {

    private TextBox ticketName = new TextBoxImpl("//*[@id='ticket_name']","'Ticket name' text field");
    private TextBox attendeesPerTicket = new TextBoxImpl("//*[@id='ticket_attendees']","'Attendees per Ticket' text field");
    private Button saveTicketButton = new ButtonImpl("//*[@id='submitmbutton']/input","Save ticket button");

    public AddEventPageTicketsTabNewTicket specifyTicketName(String ticketNameValue) {
        //ticketName.fluentWaitForElementPresenceIgnoringExceptions(120);
        ticketName.type(ticketNameValue);
        return this;
    }

    public AddEventPageTicketsTabNewTicket specifyAttendeesPerTicket(String amountOfAttendees) {
        attendeesPerTicket.fluentWaitForElementPresenceIgnoringExceptions();
        attendeesPerTicket.type(amountOfAttendees);
        return this;
    }

    public AddEventPageTicketsTabInformationCollected clickSaveTicketInfoButton(){
        saveTicketButton.click();
        return new AddEventPageTicketsTabInformationCollected();
    }

}
