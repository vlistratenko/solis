
package com.salsalabs.ignite.automation.apiautomation.models.activity.getactivityfromdate.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Ticket {

    @SerializedName("ticketId")
    @Expose
    private String ticketId;
    @SerializedName("ticketName")
    @Expose
    private String ticketName;
    @SerializedName("transactionId")
    @Expose
    private String transactionId;
    @SerializedName("ticketStatus")
    @Expose
    private String ticketStatus;
    @SerializedName("ticketCost")
    @Expose
    private Integer ticketCost;
    @SerializedName("deductibleAmount")
    @Expose
    private Integer deductibleAmount;
    @SerializedName("questions")
    @Expose
    private List<Object> questions = null;
    @SerializedName("attendees")
    @Expose
    private List<Attendee> attendees = null;
    @SerializedName("lastModified")
    @Expose
    private String lastModified;

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public Integer getTicketCost() {
        return ticketCost;
    }

    public void setTicketCost(Integer ticketCost) {
        this.ticketCost = ticketCost;
    }

    public Integer getDeductibleAmount() {
        return deductibleAmount;
    }

    public void setDeductibleAmount(Integer deductibleAmount) {
        this.deductibleAmount = deductibleAmount;
    }

    public List<Object> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Object> questions) {
        this.questions = questions;
    }

    public List<Attendee> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<Attendee> attendees) {
        this.attendees = attendees;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;

        Ticket ticket = (Ticket) o;

        if (getTicketId() != null ? !getTicketId().equals(ticket.getTicketId()) : ticket.getTicketId() != null)
            return false;
        if (getTicketName() != null ? !getTicketName().equals(ticket.getTicketName()) : ticket.getTicketName() != null)
            return false;
        if (getTransactionId() != null ? !getTransactionId().equals(ticket.getTransactionId()) : ticket.getTransactionId() != null)
            return false;
        if (getTicketStatus() != null ? !getTicketStatus().equals(ticket.getTicketStatus()) : ticket.getTicketStatus() != null)
            return false;
        if (getTicketCost() != null ? !getTicketCost().equals(ticket.getTicketCost()) : ticket.getTicketCost() != null)
            return false;
        if (getDeductibleAmount() != null ? !getDeductibleAmount().equals(ticket.getDeductibleAmount()) : ticket.getDeductibleAmount() != null)
            return false;
        if (getQuestions() != null ? !getQuestions().equals(ticket.getQuestions()) : ticket.getQuestions() != null)
            return false;
        return getAttendees() != null ? getAttendees().equals(ticket.getAttendees()) : ticket.getAttendees() == null;

    }

    @Override
    public int hashCode() {
        int result = getTicketId() != null ? getTicketId().hashCode() : 0;
        result = 31 * result + (getTicketName() != null ? getTicketName().hashCode() : 0);
        result = 31 * result + (getTransactionId() != null ? getTransactionId().hashCode() : 0);
        result = 31 * result + (getTicketStatus() != null ? getTicketStatus().hashCode() : 0);
        result = 31 * result + (getTicketCost() != null ? getTicketCost().hashCode() : 0);
        result = 31 * result + (getDeductibleAmount() != null ? getDeductibleAmount().hashCode() : 0);
        result = 31 * result + (getQuestions() != null ? getQuestions().hashCode() : 0);
        result = 31 * result + (getAttendees() != null ? getAttendees().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId='" + ticketId + '\'' +
                ", ticketName='" + ticketName + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", ticketStatus='" + ticketStatus + '\'' +
                ", ticketCost=" + ticketCost +
                ", deductibleAmount=" + deductibleAmount +
                ", questions=" + questions +
                ", attendees=" + attendees +
                ", lastModified='" + lastModified + '\'' +
                '}';
    }
}
