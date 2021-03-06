
package com.salsalabs.ignite.automation.apiautomation.models.activity.getactivityfromdate.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Letter {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("targets")
    @Expose
    private List<Target> targets = null;
    @SerializedName("additionalComment")
    @Expose
    private String additionalComment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Target> getTargets() {
        return targets;
    }

    public void setTargets(List<Target> targets) {
        this.targets = targets;
    }

    public String getAdditionalComment() {
        return additionalComment;
    }

    public void setAdditionalComment(String additionalComment) {
        this.additionalComment = additionalComment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Letter)) return false;

        Letter letter = (Letter) o;

        if (getName() != null ? !getName().equals(letter.getName()) : letter.getName() != null) return false;
        if (getSubject() != null ? !getSubject().equals(letter.getSubject()) : letter.getSubject() != null)
            return false;
        if (getMessage() != null ? !getMessage().equals(letter.getMessage()) : letter.getMessage() != null)
            return false;
        if (getTargets() != null ? !getTargets().equals(letter.getTargets()) : letter.getTargets() != null)
            return false;
        return getAdditionalComment() != null ? getAdditionalComment().equals(letter.getAdditionalComment()) : letter.getAdditionalComment() == null;

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getSubject() != null ? getSubject().hashCode() : 0);
        result = 31 * result + (getMessage() != null ? getMessage().hashCode() : 0);
        result = 31 * result + (getTargets() != null ? getTargets().hashCode() : 0);
        result = 31 * result + (getAdditionalComment() != null ? getAdditionalComment().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Letter{" +
                "name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                ", targets=" + targets +
                ", additionalComment='" + additionalComment + '\'' +
                '}';
    }
}
