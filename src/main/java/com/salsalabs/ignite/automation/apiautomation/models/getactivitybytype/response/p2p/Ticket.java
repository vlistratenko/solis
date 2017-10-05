package com.salsalabs.ignite.automation.apiautomation.models.getactivitybytype.response.p2p;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "ticketId",
        "ticketName",
        "transactionId",
        "ticketStatus",
        "ticketCost",
        "deductibleAmount",
        "questions",
        "attendees",
        "lastModified"
})
public class Ticket {

    @JsonProperty("ticketId")
    private String ticketId;
    @JsonProperty("ticketName")
    private String ticketName;
    @JsonProperty("transactionId")
    private String transactionId;
    @JsonProperty("ticketStatus")
    private String ticketStatus;
    @JsonProperty("ticketCost")
    private Integer ticketCost;
    @JsonProperty("deductibleAmount")
    private Integer deductibleAmount;
    @JsonProperty("questions")
    private List<Object> questions = null;
    @JsonProperty("attendees")
    private List<Attendee> attendees = null;
    @JsonProperty("lastModified")
    private String lastModified;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("ticketId")
    public String getTicketId() {
        return ticketId;
    }

    @JsonProperty("ticketId")
    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    @JsonProperty("ticketName")
    public String getTicketName() {
        return ticketName;
    }

    @JsonProperty("ticketName")
    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    @JsonProperty("transactionId")
    public String getTransactionId() {
        return transactionId;
    }

    @JsonProperty("transactionId")
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @JsonProperty("ticketStatus")
    public String getTicketStatus() {
        return ticketStatus;
    }

    @JsonProperty("ticketStatus")
    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    @JsonProperty("ticketCost")
    public Integer getTicketCost() {
        return ticketCost;
    }

    @JsonProperty("ticketCost")
    public void setTicketCost(Integer ticketCost) {
        this.ticketCost = ticketCost;
    }

    @JsonProperty("deductibleAmount")
    public Integer getDeductibleAmount() {
        return deductibleAmount;
    }

    @JsonProperty("deductibleAmount")
    public void setDeductibleAmount(Integer deductibleAmount) {
        this.deductibleAmount = deductibleAmount;
    }

    @JsonProperty("questions")
    public List<Object> getQuestions() {
        return questions;
    }

    @JsonProperty("questions")
    public void setQuestions(List<Object> questions) {
        this.questions = questions;
    }

    @JsonProperty("attendees")
    public List<Attendee> getAttendees() {
        return attendees;
    }

    @JsonProperty("attendees")
    public void setAttendees(List<Attendee> attendees) {
        this.attendees = attendees;
    }

    @JsonProperty("lastModified")
    public String getLastModified() {
        return lastModified;
    }

    @JsonProperty("lastModified")
    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
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
        if (getAttendees() != null ? !getAttendees().equals(ticket.getAttendees()) : ticket.getAttendees() != null)
            return false;
        if (getLastModified() != null ? !getLastModified().equals(ticket.getLastModified()) : ticket.getLastModified() != null)
            return false;
        return getAdditionalProperties() != null ? getAdditionalProperties().equals(ticket.getAdditionalProperties()) : ticket.getAdditionalProperties() == null;

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
        result = 31 * result + (getLastModified() != null ? getLastModified().hashCode() : 0);
        result = 31 * result + (getAdditionalProperties() != null ? getAdditionalProperties().hashCode() : 0);
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
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}