package com.salsalabs.ignite.automation.pages.hq.event;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.Frame;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.FrameImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;
import com.salsalabs.ignite.automation.pages.hq.HomePage;

public class AddEventPageTicketsTabNewTicket extends HomePage {

    private TextBox ticketName = new TextBoxImpl("//*[@id='ticket_name']","'Ticket name' text field");
    private TextBox attendeesPerTicket = new TextBoxImpl("//*[@id='ticket_attendees']","'Attendees per Ticket' text field");
    private Button saveTicketButton = new ButtonImpl("//*[@id='submitmbutton']/input","Save ticket button");
    private Frame givezooksFrame = new FrameImpl("//iframe[@id='tickets_iframe']","Givezooks iFrame");

    public AddEventPageTicketsTabNewTicket specifyTicketName(String ticketNameValue) {
        givezooksFrame.swithToFrameWithFluentWait(20);
        ticketName.type(ticketNameValue);
        switchDefaultContent();
        return this;
    }

    public AddEventPageTicketsTabNewTicket specifyAttendeesPerTicket(String amountOfAttendees) {
        attendeesPerTicket.type(amountOfAttendees);
        return this;
    }

    public AddEventPageTicketsTabInformationCollected clickSaveTicketInfoButton(){
        givezooksFrame.switchToFrame();
        saveTicketButton.click();
        switchDefaultContent();
        return new AddEventPageTicketsTabInformationCollected();
    }

}
