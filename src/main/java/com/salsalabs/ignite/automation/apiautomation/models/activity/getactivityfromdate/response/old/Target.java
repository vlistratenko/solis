package com.salsalabs.ignite.automation.apiautomation.models.activity.getactivityfromdate.response.old;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "targetId",
        "targetName",
        "targetTitle",
        "politicalParty",
        "state",
        "districtId",
        "districtName",
        "sentEmail",
        "sentFacebook",
        "sentTwitter",
        "madeCall",
        "callDurationSeconds",
        "callResult"
})
public class Target {

    @JsonProperty("targetId")
    private String targetId;
    @JsonProperty("targetName")
    private String targetName;
    @JsonProperty("targetTitle")
    private String targetTitle;
    @JsonProperty("politicalParty")
    private String politicalParty;
    @JsonProperty("state")
    private String state;
    @JsonProperty("districtId")
    private String districtId;
    @JsonProperty("districtName")
    private String districtName;
    @JsonProperty("sentEmail")
    private Boolean sentEmail;
    @JsonProperty("sentFacebook")
    private Boolean sentFacebook;
    @JsonProperty("sentTwitter")
    private Boolean sentTwitter;
    @JsonProperty("madeCall")
    private Boolean madeCall;
    @JsonProperty("callDurationSeconds")
    private Integer callDurationSeconds;
    @JsonProperty("callResult")
    private String callResult;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("targetId")
    public String getTargetId() {
        return targetId;
    }

    @JsonProperty("targetId")
    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    @JsonProperty("targetName")
    public String getTargetName() {
        return targetName;
    }

    @JsonProperty("targetName")
    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    @JsonProperty("targetTitle")
    public String getTargetTitle() {
        return targetTitle;
    }

    @JsonProperty("targetTitle")
    public void setTargetTitle(String targetTitle) {
        this.targetTitle = targetTitle;
    }

    @JsonProperty("politicalParty")
    public String getPoliticalParty() {
        return politicalParty;
    }

    @JsonProperty("politicalParty")
    public void setPoliticalParty(String politicalParty) {
        this.politicalParty = politicalParty;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("districtId")
    public String getDistrictId() {
        return districtId;
    }

    @JsonProperty("districtId")
    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    @JsonProperty("districtName")
    public String getDistrictName() {
        return districtName;
    }

    @JsonProperty("districtName")
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    @JsonProperty("sentEmail")
    public Boolean getSentEmail() {
        return sentEmail;
    }

    @JsonProperty("sentEmail")
    public void setSentEmail(Boolean sentEmail) {
        this.sentEmail = sentEmail;
    }

    @JsonProperty("sentFacebook")
    public Boolean getSentFacebook() {
        return sentFacebook;
    }

    @JsonProperty("sentFacebook")
    public void setSentFacebook(Boolean sentFacebook) {
        this.sentFacebook = sentFacebook;
    }

    @JsonProperty("sentTwitter")
    public Boolean getSentTwitter() {
        return sentTwitter;
    }

    @JsonProperty("sentTwitter")
    public void setSentTwitter(Boolean sentTwitter) {
        this.sentTwitter = sentTwitter;
    }

    @JsonProperty("madeCall")
    public Boolean getMadeCall() {
        return madeCall;
    }

    @JsonProperty("madeCall")
    public void setMadeCall(Boolean madeCall) {
        this.madeCall = madeCall;
    }

    @JsonProperty("callDurationSeconds")
    public Integer getCallDurationSeconds() {
        return callDurationSeconds;
    }

    @JsonProperty("callDurationSeconds")
    public void setCallDurationSeconds(Integer callDurationSeconds) {
        this.callDurationSeconds = callDurationSeconds;
    }

    @JsonProperty("callResult")
    public String getCallResult() {
        return callResult;
    }

    @JsonProperty("callResult")
    public void setCallResult(String callResult) {
        this.callResult = callResult;
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
        if (!(o instanceof Target)) return false;

        Target target = (Target) o;

        if (getTargetId() != null ? !getTargetId().equals(target.getTargetId()) : target.getTargetId() != null)
            return false;
        if (getTargetName() != null ? !getTargetName().equals(target.getTargetName()) : target.getTargetName() != null)
            return false;
        if (getTargetTitle() != null ? !getTargetTitle().equals(target.getTargetTitle()) : target.getTargetTitle() != null)
            return false;
        if (getPoliticalParty() != null ? !getPoliticalParty().equals(target.getPoliticalParty()) : target.getPoliticalParty() != null)
            return false;
        if (getState() != null ? !getState().equals(target.getState()) : target.getState() != null) return false;
        if (getDistrictId() != null ? !getDistrictId().equals(target.getDistrictId()) : target.getDistrictId() != null)
            return false;
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
        return getAdditionalProperties() != null ? getAdditionalProperties().equals(target.getAdditionalProperties()) : target.getAdditionalProperties() == null;

    }

    @Override
    public int hashCode() {
        int result = getTargetId() != null ? getTargetId().hashCode() : 0;
        result = 31 * result + (getTargetName() != null ? getTargetName().hashCode() : 0);
        result = 31 * result + (getTargetTitle() != null ? getTargetTitle().hashCode() : 0);
        result = 31 * result + (getPoliticalParty() != null ? getPoliticalParty().hashCode() : 0);
        result = 31 * result + (getState() != null ? getState().hashCode() : 0);
        result = 31 * result + (getDistrictId() != null ? getDistrictId().hashCode() : 0);
        result = 31 * result + (getDistrictName() != null ? getDistrictName().hashCode() : 0);
        result = 31 * result + (getSentEmail() != null ? getSentEmail().hashCode() : 0);
        result = 31 * result + (getSentFacebook() != null ? getSentFacebook().hashCode() : 0);
        result = 31 * result + (getSentTwitter() != null ? getSentTwitter().hashCode() : 0);
        result = 31 * result + (getMadeCall() != null ? getMadeCall().hashCode() : 0);
        result = 31 * result + (getCallDurationSeconds() != null ? getCallDurationSeconds().hashCode() : 0);
        result = 31 * result + (getCallResult() != null ? getCallResult().hashCode() : 0);
        result = 31 * result + (getAdditionalProperties() != null ? getAdditionalProperties().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Target{" +
                "targetId='" + targetId + '\'' +
                ", targetName='" + targetName + '\'' +
                ", targetTitle='" + targetTitle + '\'' +
                ", politicalParty='" + politicalParty + '\'' +
                ", state='" + state + '\'' +
                ", districtId='" + districtId + '\'' +
                ", districtName='" + districtName + '\'' +
                ", sentEmail=" + sentEmail +
                ", sentFacebook=" + sentFacebook +
                ", sentTwitter=" + sentTwitter +
                ", madeCall=" + madeCall +
                ", callDurationSeconds=" + callDurationSeconds +
                ", callResult='" + callResult + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}