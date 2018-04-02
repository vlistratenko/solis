package com.salsalabs.ignite.automation.apiautomation.models.metrics.response;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "rateLimit",
        "maxBatchSize",
        "supporterRead",
        "supporterAdd",
        "supporterDelete",
        "supporterUpdate",
        "segmentRead",
        "segmentAdd",
        "segmentDelete",
        "segmentUpdate",
        "segmentAssignmentRead",
        "segmentAssignmentAdd",
        "segmentAssignmentUpdate",
        "segmentAssignmentDelete",
        "offlineDonationAdd",
        "offlineDonationUpdate",
        "activityTicketedEvent",
        "activityP2PEvent",
        "activitySubscribe",
        "activityFundraise",
        "activityTargetedLetter",
        "activityPetition",
        "activitySubscriptionManagement",
        "lastAPICall",
        "totalAPICalls",
        "totalAPICallFailures",
        "currentRateLimit"
})
public class Payload {

    @JsonProperty("rateLimit")
    private Integer rateLimit;
    @JsonProperty("maxBatchSize")
    private Integer maxBatchSize;
    @JsonProperty("supporterRead")
    private Integer supporterRead;
    @JsonProperty("supporterAdd")
    private Integer supporterAdd;
    @JsonProperty("supporterDelete")
    private Integer supporterDelete;
    @JsonProperty("supporterUpdate")
    private Integer supporterUpdate;
    @JsonProperty("segmentRead")
    private Integer segmentRead;
    @JsonProperty("segmentAdd")
    private Integer segmentAdd;
    @JsonProperty("segmentDelete")
    private Integer segmentDelete;
    @JsonProperty("segmentUpdate")
    private Integer segmentUpdate;
    @JsonProperty("segmentAssignmentRead")
    private Integer segmentAssignmentRead;
    @JsonProperty("segmentAssignmentAdd")
    private Integer segmentAssignmentAdd;
    @JsonProperty("segmentAssignmentUpdate")
    private Integer segmentAssignmentUpdate;
    @JsonProperty("segmentAssignmentDelete")
    private Integer segmentAssignmentDelete;
    @JsonProperty("offlineDonationAdd")
    private Integer offlineDonationAdd;
    @JsonProperty("offlineDonationUpdate")
    private Integer offlineDonationUpdate;
    @JsonProperty("activityTicketedEvent")
    private Integer activityTicketedEvent;
    @JsonProperty("activityP2PEvent")
    private Integer activityP2PEvent;
    @JsonProperty("activitySubscribe")
    private Integer activitySubscribe;
    @JsonProperty("activityFundraise")
    private Integer activityFundraise;
    @JsonProperty("activityTargetedLetter")
    private Integer activityTargetedLetter;
    @JsonProperty("activityPetition")
    private Integer activityPetition;
    @JsonProperty("activitySubscriptionManagement")
    private Integer activitySubscriptionManagement;
    @JsonProperty("lastAPICall")
    private String lastAPICall;
    @JsonProperty("totalAPICalls")
    private Integer totalAPICalls;
    @JsonProperty("totalAPICallFailures")
    private Integer totalAPICallFailures;
    @JsonProperty("currentRateLimit")
    private Integer currentRateLimit;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("rateLimit")
    public Integer getRateLimit() {
        return rateLimit;
    }

    @JsonProperty("rateLimit")
    public void setRateLimit(Integer rateLimit) {
        this.rateLimit = rateLimit;
    }

    @JsonProperty("maxBatchSize")
    public Integer getMaxBatchSize() {
        return maxBatchSize;
    }

    @JsonProperty("maxBatchSize")
    public void setMaxBatchSize(Integer maxBatchSize) {
        this.maxBatchSize = maxBatchSize;
    }

    @JsonProperty("supporterRead")
    public Integer getSupporterRead() {
        return supporterRead;
    }

    @JsonProperty("supporterRead")
    public void setSupporterRead(Integer supporterRead) {
        this.supporterRead = supporterRead;
    }

    @JsonProperty("supporterAdd")
    public Integer getSupporterAdd() {
        return supporterAdd;
    }

    @JsonProperty("supporterAdd")
    public void setSupporterAdd(Integer supporterAdd) {
        this.supporterAdd = supporterAdd;
    }

    @JsonProperty("supporterDelete")
    public Integer getSupporterDelete() {
        return supporterDelete;
    }

    @JsonProperty("supporterDelete")
    public void setSupporterDelete(Integer supporterDelete) {
        this.supporterDelete = supporterDelete;
    }

    @JsonProperty("supporterUpdate")
    public Integer getSupporterUpdate() {
        return supporterUpdate;
    }

    @JsonProperty("supporterUpdate")
    public void setSupporterUpdate(Integer supporterUpdate) {
        this.supporterUpdate = supporterUpdate;
    }

    @JsonProperty("segmentRead")
    public Integer getSegmentRead() {
        return segmentRead;
    }

    @JsonProperty("segmentRead")
    public void setSegmentRead(Integer segmentRead) {
        this.segmentRead = segmentRead;
    }

    @JsonProperty("segmentAdd")
    public Integer getSegmentAdd() {
        return segmentAdd;
    }

    @JsonProperty("segmentAdd")
    public void setSegmentAdd(Integer segmentAdd) {
        this.segmentAdd = segmentAdd;
    }

    @JsonProperty("segmentDelete")
    public Integer getSegmentDelete() {
        return segmentDelete;
    }

    @JsonProperty("segmentDelete")
    public void setSegmentDelete(Integer segmentDelete) {
        this.segmentDelete = segmentDelete;
    }

    @JsonProperty("segmentUpdate")
    public Integer getSegmentUpdate() {
        return segmentUpdate;
    }

    @JsonProperty("segmentUpdate")
    public void setSegmentUpdate(Integer segmentUpdate) {
        this.segmentUpdate = segmentUpdate;
    }

    @JsonProperty("segmentAssignmentRead")
    public Integer getSegmentAssignmentRead() {
        return segmentAssignmentRead;
    }

    @JsonProperty("segmentAssignmentRead")
    public void setSegmentAssignmentRead(Integer segmentAssignmentRead) {
        this.segmentAssignmentRead = segmentAssignmentRead;
    }

    @JsonProperty("segmentAssignmentAdd")
    public Integer getSegmentAssignmentAdd() {
        return segmentAssignmentAdd;
    }

    @JsonProperty("segmentAssignmentAdd")
    public void setSegmentAssignmentAdd(Integer segmentAssignmentAdd) {
        this.segmentAssignmentAdd = segmentAssignmentAdd;
    }

    @JsonProperty("segmentAssignmentUpdate")
    public Integer getSegmentAssignmentUpdate() {
        return segmentAssignmentUpdate;
    }

    @JsonProperty("segmentAssignmentUpdate")
    public void setSegmentAssignmentUpdate(Integer segmentAssignmentUpdate) {
        this.segmentAssignmentUpdate = segmentAssignmentUpdate;
    }

    @JsonProperty("segmentAssignmentDelete")
    public Integer getSegmentAssignmentDelete() {
        return segmentAssignmentDelete;
    }

    @JsonProperty("segmentAssignmentDelete")
    public void setSegmentAssignmentDelete(Integer segmentAssignmentDelete) {
        this.segmentAssignmentDelete = segmentAssignmentDelete;
    }

    @JsonProperty("offlineDonationAdd")
    public Integer getOfflineDonationAdd() {
        return offlineDonationAdd;
    }

    @JsonProperty("offlineDonationAdd")
    public void setOfflineDonationAdd(Integer offlineDonationAdd) {
        this.offlineDonationAdd = offlineDonationAdd;
    }

    @JsonProperty("offlineDonationUpdate")
    public Integer getOfflineDonationUpdate() {
        return offlineDonationUpdate;
    }

    @JsonProperty("offlineDonationUpdate")
    public void setOfflineDonationUpdate(Integer offlineDonationUpdate) {
        this.offlineDonationUpdate = offlineDonationUpdate;
    }

    @JsonProperty("activityTicketedEvent")
    public Integer getActivityTicketedEvent() {
        return activityTicketedEvent;
    }

    @JsonProperty("activityTicketedEvent")
    public void setActivityTicketedEvent(Integer activityTicketedEvent) {
        this.activityTicketedEvent = activityTicketedEvent;
    }

    @JsonProperty("activityP2PEvent")
    public Integer getActivityP2PEvent() {
        return activityP2PEvent;
    }

    @JsonProperty("activityP2PEvent")
    public void setActivityP2PEvent(Integer activityP2PEvent) {
        this.activityP2PEvent = activityP2PEvent;
    }

    @JsonProperty("activitySubscribe")
    public Integer getActivitySubscribe() {
        return activitySubscribe;
    }

    @JsonProperty("activitySubscribe")
    public void setActivitySubscribe(Integer activitySubscribe) {
        this.activitySubscribe = activitySubscribe;
    }

    @JsonProperty("activityFundraise")
    public Integer getActivityFundraise() {
        return activityFundraise;
    }

    @JsonProperty("activityFundraise")
    public void setActivityFundraise(Integer activityFundraise) {
        this.activityFundraise = activityFundraise;
    }

    @JsonProperty("activityTargetedLetter")
    public Integer getActivityTargetedLetter() {
        return activityTargetedLetter;
    }

    @JsonProperty("activityTargetedLetter")
    public void setActivityTargetedLetter(Integer activityTargetedLetter) {
        this.activityTargetedLetter = activityTargetedLetter;
    }

    @JsonProperty("activityPetition")
    public Integer getActivityPetition() {
        return activityPetition;
    }

    @JsonProperty("activityPetition")
    public void setActivityPetition(Integer activityPetition) {
        this.activityPetition = activityPetition;
    }

    @JsonProperty("activitySubscriptionManagement")
    public Integer getActivitySubscriptionManagement() {
        return activitySubscriptionManagement;
    }

    @JsonProperty("activitySubscriptionManagement")
    public void setActivitySubscriptionManagement(Integer activitySubscriptionManagement) {
        this.activitySubscriptionManagement = activitySubscriptionManagement;
    }

    @JsonProperty("lastAPICall")
    public String getLastAPICall() {
        return lastAPICall;
    }

    @JsonProperty("lastAPICall")
    public void setLastAPICall(String lastAPICall) {
        this.lastAPICall = lastAPICall;
    }

    @JsonProperty("totalAPICalls")
    public Integer getTotalAPICalls() {
        return totalAPICalls;
    }

    @JsonProperty("totalAPICalls")
    public void setTotalAPICalls(Integer totalAPICalls) {
        this.totalAPICalls = totalAPICalls;
    }

    @JsonProperty("totalAPICallFailures")
    public Integer getTotalAPICallFailures() {
        return totalAPICallFailures;
    }

    @JsonProperty("totalAPICallFailures")
    public void setTotalAPICallFailures(Integer totalAPICallFailures) {
        this.totalAPICallFailures = totalAPICallFailures;
    }

    @JsonProperty("currentRateLimit")
    public Integer getCurrentRateLimit() {
        return currentRateLimit;
    }

    @JsonProperty("currentRateLimit")
    public void setCurrentRateLimit(Integer currentRateLimit) {
        this.currentRateLimit = currentRateLimit;
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
        if (!(o instanceof Payload)) return false;

        Payload payload = (Payload) o;

        if (getRateLimit() != null ? !getRateLimit().equals(payload.getRateLimit()) : payload.getRateLimit() != null)
            return false;
        if (getMaxBatchSize() != null ? !getMaxBatchSize().equals(payload.getMaxBatchSize()) : payload.getMaxBatchSize() != null)
            return false;
        if (getSupporterRead() != null ? !getSupporterRead().equals(payload.getSupporterRead()) : payload.getSupporterRead() != null)
            return false;
        if (getSupporterAdd() != null ? !getSupporterAdd().equals(payload.getSupporterAdd()) : payload.getSupporterAdd() != null)
            return false;
        if (getSupporterDelete() != null ? !getSupporterDelete().equals(payload.getSupporterDelete()) : payload.getSupporterDelete() != null)
            return false;
        if (getSupporterUpdate() != null ? !getSupporterUpdate().equals(payload.getSupporterUpdate()) : payload.getSupporterUpdate() != null)
            return false;
        if (getSegmentRead() != null ? !getSegmentRead().equals(payload.getSegmentRead()) : payload.getSegmentRead() != null)
            return false;
        if (getSegmentAdd() != null ? !getSegmentAdd().equals(payload.getSegmentAdd()) : payload.getSegmentAdd() != null)
            return false;
        if (getSegmentDelete() != null ? !getSegmentDelete().equals(payload.getSegmentDelete()) : payload.getSegmentDelete() != null)
            return false;
        if (getSegmentUpdate() != null ? !getSegmentUpdate().equals(payload.getSegmentUpdate()) : payload.getSegmentUpdate() != null)
            return false;
        if (getSegmentAssignmentRead() != null ? !getSegmentAssignmentRead().equals(payload.getSegmentAssignmentRead()) : payload.getSegmentAssignmentRead() != null)
            return false;
        if (getSegmentAssignmentAdd() != null ? !getSegmentAssignmentAdd().equals(payload.getSegmentAssignmentAdd()) : payload.getSegmentAssignmentAdd() != null)
            return false;
        if (getSegmentAssignmentUpdate() != null ? !getSegmentAssignmentUpdate().equals(payload.getSegmentAssignmentUpdate()) : payload.getSegmentAssignmentUpdate() != null)
            return false;
        if (getSegmentAssignmentDelete() != null ? !getSegmentAssignmentDelete().equals(payload.getSegmentAssignmentDelete()) : payload.getSegmentAssignmentDelete() != null)
            return false;
        if (getOfflineDonationAdd() != null ? !getOfflineDonationAdd().equals(payload.getOfflineDonationAdd()) : payload.getOfflineDonationAdd() != null)
            return false;
        if (getOfflineDonationUpdate() != null ? !getOfflineDonationUpdate().equals(payload.getOfflineDonationUpdate()) : payload.getOfflineDonationUpdate() != null)
            return false;
        if (getActivityTicketedEvent() != null ? !getActivityTicketedEvent().equals(payload.getActivityTicketedEvent()) : payload.getActivityTicketedEvent() != null)
            return false;
        if (getActivityP2PEvent() != null ? !getActivityP2PEvent().equals(payload.getActivityP2PEvent()) : payload.getActivityP2PEvent() != null)
            return false;
        if (getActivitySubscribe() != null ? !getActivitySubscribe().equals(payload.getActivitySubscribe()) : payload.getActivitySubscribe() != null)
            return false;
        if (getActivityFundraise() != null ? !getActivityFundraise().equals(payload.getActivityFundraise()) : payload.getActivityFundraise() != null)
            return false;
        if (getActivityTargetedLetter() != null ? !getActivityTargetedLetter().equals(payload.getActivityTargetedLetter()) : payload.getActivityTargetedLetter() != null)
            return false;
        if (getActivityPetition() != null ? !getActivityPetition().equals(payload.getActivityPetition()) : payload.getActivityPetition() != null)
            return false;
        if (getActivitySubscriptionManagement() != null ? !getActivitySubscriptionManagement().equals(payload.getActivitySubscriptionManagement()) : payload.getActivitySubscriptionManagement() != null)
            return false;
        if (getTotalAPICallFailures() != null ? !getTotalAPICallFailures().equals(payload.getTotalAPICallFailures()) : payload.getTotalAPICallFailures() != null)
            return false;
      /*  if (getCurrentRateLimit() != null ? !getCurrentRateLimit().equals(payload.getCurrentRateLimit()) : payload.getCurrentRateLimit() != null)
            return false;*/
        return getAdditionalProperties() != null ? getAdditionalProperties().equals(payload.getAdditionalProperties()) : payload.getAdditionalProperties() == null;

    }

    @Override
    public int hashCode() {
        int result = getRateLimit() != null ? getRateLimit().hashCode() : 0;
        result = 31 * result + (getMaxBatchSize() != null ? getMaxBatchSize().hashCode() : 0);
        result = 31 * result + (getSupporterRead() != null ? getSupporterRead().hashCode() : 0);
        result = 31 * result + (getSupporterAdd() != null ? getSupporterAdd().hashCode() : 0);
        result = 31 * result + (getSupporterDelete() != null ? getSupporterDelete().hashCode() : 0);
        result = 31 * result + (getSupporterUpdate() != null ? getSupporterUpdate().hashCode() : 0);
        result = 31 * result + (getSegmentRead() != null ? getSegmentRead().hashCode() : 0);
        result = 31 * result + (getSegmentAdd() != null ? getSegmentAdd().hashCode() : 0);
        result = 31 * result + (getSegmentDelete() != null ? getSegmentDelete().hashCode() : 0);
        result = 31 * result + (getSegmentUpdate() != null ? getSegmentUpdate().hashCode() : 0);
        result = 31 * result + (getSegmentAssignmentRead() != null ? getSegmentAssignmentRead().hashCode() : 0);
        result = 31 * result + (getSegmentAssignmentAdd() != null ? getSegmentAssignmentAdd().hashCode() : 0);
        result = 31 * result + (getSegmentAssignmentUpdate() != null ? getSegmentAssignmentUpdate().hashCode() : 0);
        result = 31 * result + (getSegmentAssignmentDelete() != null ? getSegmentAssignmentDelete().hashCode() : 0);
        result = 31 * result + (getOfflineDonationAdd() != null ? getOfflineDonationAdd().hashCode() : 0);
        result = 31 * result + (getOfflineDonationUpdate() != null ? getOfflineDonationUpdate().hashCode() : 0);
        result = 31 * result + (getActivityTicketedEvent() != null ? getActivityTicketedEvent().hashCode() : 0);
        result = 31 * result + (getActivityP2PEvent() != null ? getActivityP2PEvent().hashCode() : 0);
        result = 31 * result + (getActivitySubscribe() != null ? getActivitySubscribe().hashCode() : 0);
        result = 31 * result + (getActivityFundraise() != null ? getActivityFundraise().hashCode() : 0);
        result = 31 * result + (getActivityTargetedLetter() != null ? getActivityTargetedLetter().hashCode() : 0);
        result = 31 * result + (getActivityPetition() != null ? getActivityPetition().hashCode() : 0);
        result = 31 * result + (getActivitySubscriptionManagement() != null ? getActivitySubscriptionManagement().hashCode() : 0);
        result = 31 * result + (getTotalAPICallFailures() != null ? getTotalAPICallFailures().hashCode() : 0);
        result = 31 * result + (getCurrentRateLimit() != null ? getCurrentRateLimit().hashCode() : 0);
        result = 31 * result + (getAdditionalProperties() != null ? getAdditionalProperties().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Payload{" +
                "rateLimit=" + rateLimit +
                ", maxBatchSize=" + maxBatchSize +
                ", supporterRead=" + supporterRead +
                ", supporterAdd=" + supporterAdd +
                ", supporterDelete=" + supporterDelete +
                ", supporterUpdate=" + supporterUpdate +
                ", segmentRead=" + segmentRead +
                ", segmentAdd=" + segmentAdd +
                ", segmentDelete=" + segmentDelete +
                ", segmentUpdate=" + segmentUpdate +
                ", segmentAssignmentRead=" + segmentAssignmentRead +
                ", segmentAssignmentAdd=" + segmentAssignmentAdd +
                ", segmentAssignmentUpdate=" + segmentAssignmentUpdate +
                ", segmentAssignmentDelete=" + segmentAssignmentDelete +
                ", offlineDonationAdd=" + offlineDonationAdd +
                ", offlineDonationUpdate=" + offlineDonationUpdate +
                ", activityTicketedEvent=" + activityTicketedEvent +
                ", activityP2PEvent=" + activityP2PEvent +
                ", activitySubscribe=" + activitySubscribe +
                ", activityFundraise=" + activityFundraise +
                ", activityTargetedLetter=" + activityTargetedLetter +
                ", activityPetition=" + activityPetition +
                ", activitySubscriptionManagement=" + activitySubscriptionManagement +
                ", lastAPICall='" + lastAPICall + '\'' +
                ", totalAPICalls=" + totalAPICalls +
                ", totalAPICallFailures=" + totalAPICallFailures +
                ", currentRateLimit=" + currentRateLimit +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}