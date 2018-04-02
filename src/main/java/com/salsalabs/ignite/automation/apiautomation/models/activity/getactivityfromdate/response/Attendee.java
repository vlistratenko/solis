
package com.salsalabs.ignite.automation.apiautomation.models.activity.getactivityfromdate.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Attendee {

    @SerializedName("attendeeId")
    @Expose
    private String attendeeId;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("currentSupporterId")
    @Expose
    private String currentSupporterId;
    @SerializedName("isCurrentSupporter")
    @Expose
    private Boolean isCurrentSupporter;
    @SerializedName("lastModified")
    @Expose
    private String lastModified;
    @SerializedName("questions")
    @Expose
    private List<Object> questions = null;

    public String getAttendeeId() {
        return attendeeId;
    }

    public void setAttendeeId(String attendeeId) {
        this.attendeeId = attendeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurrentSupporterId() {
        return currentSupporterId;
    }

    public void setCurrentSupporterId(String currentSupporterId) {
        this.currentSupporterId = currentSupporterId;
    }

    public Boolean getIsCurrentSupporter() {
        return isCurrentSupporter;
    }

    public void setIsCurrentSupporter(Boolean isCurrentSupporter) {
        this.isCurrentSupporter = isCurrentSupporter;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public List<Object> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Object> questions) {
        this.questions = questions;
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
        if (getCurrentSupporterId() != null ? !getCurrentSupporterId().equals(attendee.getCurrentSupporterId()) : attendee.getCurrentSupporterId() != null)
            return false;
        if (getIsCurrentSupporter() != null ? !getIsCurrentSupporter().equals(attendee.getIsCurrentSupporter()) : attendee.getIsCurrentSupporter() != null)
            return false;
        return getQuestions() != null ? getQuestions().equals(attendee.getQuestions()) : attendee.getQuestions() == null;

    }

    @Override
    public int hashCode() {
        int result = getAttendeeId() != null ? getAttendeeId().hashCode() : 0;
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getCurrentSupporterId() != null ? getCurrentSupporterId().hashCode() : 0);
        result = 31 * result + (getIsCurrentSupporter() != null ? getIsCurrentSupporter().hashCode() : 0);
        result = 31 * result + (getQuestions() != null ? getQuestions().hashCode() : 0);
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
                ", currentSupporterId='" + currentSupporterId + '\'' +
                ", isCurrentSupporter=" + isCurrentSupporter +
                ", lastModified='" + lastModified + '\'' +
                ", questions=" + questions +
                '}';
    }
}
