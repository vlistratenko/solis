package com.salsalabs.ignite.automation.apiautomation.models.getactivitybytype.response.event;

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
        "attendeeId",
        "firstName",
        "type",
        "status",
        "lastName",
        "email",
        "adressLine1",
        "city",
        "state",
        "phone",
        "currentSupporterId",
        "isCurrentSupporter",
        "lastModified",
        "questions",
        "adressLine2"
})
public class Attendee {

    @JsonProperty("attendeeId")
    private String attendeeId;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("type")
    private String type;
    @JsonProperty("status")
    private String status;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("adressLine1")
    private String adressLine1;
    @JsonProperty("city")
    private String city;
    @JsonProperty("state")
    private String state;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("currentSupporterId")
    private String currentSupporterId;
    @JsonProperty("isCurrentSupporter")
    private Boolean isCurrentSupporter;
    @JsonProperty("lastModified")
    private String lastModified;
    @JsonProperty("questions")
    private List<Object> questions = null;
    @JsonProperty("adressLine2")
    private String adressLine2;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("attendeeId")
    public String getAttendeeId() {
        return attendeeId;
    }

    @JsonProperty("attendeeId")
    public void setAttendeeId(String attendeeId) {
        this.attendeeId = attendeeId;
    }

    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("adressLine1")
    public String getAdressLine1() {
        return adressLine1;
    }

    @JsonProperty("adressLine1")
    public void setAdressLine1(String adressLine1) {
        this.adressLine1 = adressLine1;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    @JsonProperty("phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonProperty("currentSupporterId")
    public String getCurrentSupporterId() {
        return currentSupporterId;
    }

    @JsonProperty("currentSupporterId")
    public void setCurrentSupporterId(String currentSupporterId) {
        this.currentSupporterId = currentSupporterId;
    }

    @JsonProperty("isCurrentSupporter")
    public Boolean getIsCurrentSupporter() {
        return isCurrentSupporter;
    }

    @JsonProperty("isCurrentSupporter")
    public void setIsCurrentSupporter(Boolean isCurrentSupporter) {
        this.isCurrentSupporter = isCurrentSupporter;
    }

    @JsonProperty("lastModified")
    public String getLastModified() {
        return lastModified;
    }

    @JsonProperty("lastModified")
    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    @JsonProperty("questions")
    public List<Object> getQuestions() {
        return questions;
    }

    @JsonProperty("questions")
    public void setQuestions(List<Object> questions) {
        this.questions = questions;
    }

    @JsonProperty("adressLine2")
    public String getAdressLine2() {
        return adressLine2;
    }

    @JsonProperty("adressLine2")
    public void setAdressLine2(String adressLine2) {
        this.adressLine2 = adressLine2;
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
        if (!(o instanceof Attendee)) return false;

        Attendee attendee = (Attendee) o;

        if (getAttendeeId() != null ? !getAttendeeId().equals(attendee.getAttendeeId()) : attendee.getAttendeeId() != null)
            return false;
        if (getFirstName() != null ? !getFirstName().equals(attendee.getFirstName()) : attendee.getFirstName() != null)
            return false;
        if (getType() != null ? !getType().equals(attendee.getType()) : attendee.getType() != null) return false;
        if (getStatus() != null ? !getStatus().equals(attendee.getStatus()) : attendee.getStatus() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(attendee.getLastName()) : attendee.getLastName() != null)
            return false;
        if (getEmail() != null ? !getEmail().equals(attendee.getEmail()) : attendee.getEmail() != null) return false;
        if (getAdressLine1() != null ? !getAdressLine1().equals(attendee.getAdressLine1()) : attendee.getAdressLine1() != null)
            return false;
        if (getCity() != null ? !getCity().equals(attendee.getCity()) : attendee.getCity() != null) return false;
        if (getState() != null ? !getState().equals(attendee.getState()) : attendee.getState() != null) return false;
        if (getPhone() != null ? !getPhone().equals(attendee.getPhone()) : attendee.getPhone() != null) return false;
        if (getCurrentSupporterId() != null ? !getCurrentSupporterId().equals(attendee.getCurrentSupporterId()) : attendee.getCurrentSupporterId() != null)
            return false;
        if (getIsCurrentSupporter() != null ? !getIsCurrentSupporter().equals(attendee.getIsCurrentSupporter()) : attendee.getIsCurrentSupporter() != null)
            return false;
        if (getLastModified() != null ? !getLastModified().equals(attendee.getLastModified()) : attendee.getLastModified() != null)
            return false;
        if (getQuestions() != null ? !getQuestions().equals(attendee.getQuestions()) : attendee.getQuestions() != null)
            return false;
        if (getAdressLine2() != null ? !getAdressLine2().equals(attendee.getAdressLine2()) : attendee.getAdressLine2() != null)
            return false;
        return getAdditionalProperties() != null ? getAdditionalProperties().equals(attendee.getAdditionalProperties()) : attendee.getAdditionalProperties() == null;

    }

    @Override
    public int hashCode() {
        int result = getAttendeeId() != null ? getAttendeeId().hashCode() : 0;
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getAdressLine1() != null ? getAdressLine1().hashCode() : 0);
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        result = 31 * result + (getState() != null ? getState().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        result = 31 * result + (getCurrentSupporterId() != null ? getCurrentSupporterId().hashCode() : 0);
        result = 31 * result + (getIsCurrentSupporter() != null ? getIsCurrentSupporter().hashCode() : 0);
        result = 31 * result + (getLastModified() != null ? getLastModified().hashCode() : 0);
        result = 31 * result + (getQuestions() != null ? getQuestions().hashCode() : 0);
        result = 31 * result + (getAdressLine2() != null ? getAdressLine2().hashCode() : 0);
        result = 31 * result + (getAdditionalProperties() != null ? getAdditionalProperties().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Attendee{" +
                "attendeeId='" + attendeeId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", adressLine1='" + adressLine1 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", phone='" + phone + '\'' +
                ", currentSupporterId='" + currentSupporterId + '\'' +
                ", isCurrentSupporter=" + isCurrentSupporter +
                ", lastModified='" + lastModified + '\'' +
                ", questions=" + questions +
                ", adressLine2='" + adressLine2 + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}