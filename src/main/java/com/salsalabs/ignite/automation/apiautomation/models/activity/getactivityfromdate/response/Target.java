
package com.salsalabs.ignite.automation.apiautomation.models.activity.getactivityfromdate.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Target {

    @SerializedName("targetId")
    @Expose
    private String targetId;
    @SerializedName("targetName")
    @Expose
    private String targetName;
    @SerializedName("targetTitle")
    @Expose
    private String targetTitle;
    @SerializedName("targetType")
    @Expose
    private String targetType;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("districtName")
    @Expose
    private String districtName;
    @SerializedName("sentEmail")
    @Expose
    private Boolean sentEmail;
    @SerializedName("sentFacebook")
    @Expose
    private Boolean sentFacebook;
    @SerializedName("sentTwitter")
    @Expose
    private Boolean sentTwitter;
    @SerializedName("madeCall")
    @Expose
    private Boolean madeCall;
    @SerializedName("callDurationSeconds")
    @Expose
    private Integer callDurationSeconds;
    @SerializedName("callResult")
    @Expose
    private String callResult;
    @SerializedName("politicalParty")
    @Expose
    private String politicalParty;
    @SerializedName("districtId")
    @Expose
    private String districtId;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("department")
    @Expose
    private String department;

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getTargetTitle() {
        return targetTitle;
    }

    public void setTargetTitle(String targetTitle) {
        this.targetTitle = targetTitle;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Boolean getSentEmail() {
        return sentEmail;
    }

    public void setSentEmail(Boolean sentEmail) {
        this.sentEmail = sentEmail;
    }

    public Boolean getSentFacebook() {
        return sentFacebook;
    }

    public void setSentFacebook(Boolean sentFacebook) {
        this.sentFacebook = sentFacebook;
    }

    public Boolean getSentTwitter() {
        return sentTwitter;
    }

    public void setSentTwitter(Boolean sentTwitter) {
        this.sentTwitter = sentTwitter;
    }

    public Boolean getMadeCall() {
        return madeCall;
    }

    public void setMadeCall(Boolean madeCall) {
        this.madeCall = madeCall;
    }

    public Integer getCallDurationSeconds() {
        return callDurationSeconds;
    }

    public void setCallDurationSeconds(Integer callDurationSeconds) {
        this.callDurationSeconds = callDurationSeconds;
    }

    public String getCallResult() {
        return callResult;
    }

    public void setCallResult(String callResult) {
        this.callResult = callResult;
    }

    public String getPoliticalParty() {
        return politicalParty;
    }

    public void setPoliticalParty(String politicalParty) {
        this.politicalParty = politicalParty;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Target)) return false;

        Target target = (Target) o;

        if (getTargetId() != null ? !getTargetId().equals(target.getTargetId()) : target.getTargetId() != null)
            return false;
        if (getTargetName() != null ? !getTargetName().equals(target.getTargetName()) : target.getTargetName() != null)
            return false;
        if (getTargetTitle() != null ? !getTargetTitle().equals(target.getTargetTitle()) : target.getTargetTitle() != null)
            return false;
        if (getTargetType() != null ? !getTargetType().equals(target.getTargetType()) : target.getTargetType() != null)
            return false;
        if (getState() != null ? !getState().equals(target.getState()) : target.getState() != null) return false;
        if (getDistrictName() != null ? !getDistrictName().equals(target.getDistrictName()) : target.getDistrictName() != null)
            return false;
        if (getSentEmail() != null ? !getSentEmail().equals(target.getSentEmail()) : target.getSentEmail() != null)
            return false;
        if (getSentFacebook() != null ? !getSentFacebook().equals(target.getSentFacebook()) : target.getSentFacebook() != null)
            return false;
        if (getSentTwitter() != null ? !getSentTwitter().equals(target.getSentTwitter()) : target.getSentTwitter() != null)
            return false;
        if (getMadeCall() != null ? !getMadeCall().equals(target.getMadeCall()) : target.getMadeCall() != null)
            return false;
        if (getCallDurationSeconds() != null ? !getCallDurationSeconds().equals(target.getCallDurationSeconds()) : target.getCallDurationSeconds() != null)
            return false;
        if (getCallResult() != null ? !getCallResult().equals(target.getCallResult()) : target.getCallResult() != null)
            return false;
        if (getPoliticalParty() != null ? !getPoliticalParty().equals(target.getPoliticalParty()) : target.getPoliticalParty() != null)
            return false;
        if (getDistrictId() != null ? !getDistrictId().equals(target.getDistrictId()) : target.getDistrictId() != null)
            return false;
        if (getRole() != null ? !getRole().equals(target.getRole()) : target.getRole() != null) return false;
        return getDepartment() != null ? getDepartment().equals(target.getDepartment()) : target.getDepartment() == null;

    }

    @Override
    public int hashCode() {
        int result = getTargetId() != null ? getTargetId().hashCode() : 0;
        result = 31 * result + (getTargetName() != null ? getTargetName().hashCode() : 0);
        result = 31 * result + (getTargetTitle() != null ? getTargetTitle().hashCode() : 0);
        result = 31 * result + (getTargetType() != null ? getTargetType().hashCode() : 0);
        result = 31 * result + (getState() != null ? getState().hashCode() : 0);
        result = 31 * result + (getDistrictName() != null ? getDistrictName().hashCode() : 0);
        result = 31 * result + (getSentEmail() != null ? getSentEmail().hashCode() : 0);
        result = 31 * result + (getSentFacebook() != null ? getSentFacebook().hashCode() : 0);
        result = 31 * result + (getSentTwitter() != null ? getSentTwitter().hashCode() : 0);
        result = 31 * result + (getMadeCall() != null ? getMadeCall().hashCode() : 0);
        result = 31 * result + (getCallDurationSeconds() != null ? getCallDurationSeconds().hashCode() : 0);
        result = 31 * result + (getCallResult() != null ? getCallResult().hashCode() : 0);
        result = 31 * result + (getPoliticalParty() != null ? getPoliticalParty().hashCode() : 0);
        result = 31 * result + (getDistrictId() != null ? getDistrictId().hashCode() : 0);
        result = 31 * result + (getRole() != null ? getRole().hashCode() : 0);
        result = 31 * result + (getDepartment() != null ? getDepartment().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Target{" +
                "targetId='" + targetId + '\'' +
                ", targetName='" + targetName + '\'' +
                ", targetTitle='" + targetTitle + '\'' +
                ", targetType='" + targetType + '\'' +
                ", state='" + state + '\'' +
                ", districtName='" + districtName + '\'' +
                ", sentEmail=" + sentEmail +
                ", sentFacebook=" + sentFacebook +
                ", sentTwitter=" + sentTwitter +
                ", madeCall=" + madeCall +
                ", callDurationSeconds=" + callDurationSeconds +
                ", callResult='" + callResult + '\'' +
                ", politicalParty='" + politicalParty + '\'' +
                ", districtId='" + districtId + '\'' +
                ", role='" + role + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
