package com.salsalabs.ignite.automation.apiautomation.models.getactivitybytype.response.p2p;

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
        "fundraiserId",
        "supporterId",
        "activityId",
        "type",
        "name",
        "pageUrl",
        "goal",
        "amountRaised",
        "donationCount",
        "lastDonation",
        "relatedFundraisersCount",
        "fundraisingPageEnabled",
        "lastModified"
})
public class DonatedToFundraiser {

    @JsonProperty("fundraiserId")
    private String fundraiserId;
    @JsonProperty("supporterId")
    private String supporterId;
    @JsonProperty("activityId")
    private String activityId;
    @JsonProperty("type")
    private String type;
    @JsonProperty("name")
    private String name;
    @JsonProperty("pageUrl")
    private String pageUrl;
    @JsonProperty("goal")
    private Integer goal;
    @JsonProperty("amountRaised")
    private Integer amountRaised;
    @JsonProperty("donationCount")
    private Integer donationCount;
    @JsonProperty("lastDonation")
    private String lastDonation;
    @JsonProperty("relatedFundraisersCount")
    private Integer relatedFundraisersCount;
    @JsonProperty("fundraisingPageEnabled")
    private Boolean fundraisingPageEnabled;
    @JsonProperty("lastModified")
    private String lastModified;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("fundraiserId")
    public String getFundraiserId() {
        return fundraiserId;
    }

    @JsonProperty("fundraiserId")
    public void setFundraiserId(String fundraiserId) {
        this.fundraiserId = fundraiserId;
    }

    @JsonProperty("supporterId")
    public String getSupporterId() {
        return supporterId;
    }

    @JsonProperty("supporterId")
    public void setSupporterId(String supporterId) {
        this.supporterId = supporterId;
    }

    @JsonProperty("activityId")
    public String getActivityId() {
        return activityId;
    }

    @JsonProperty("activityId")
    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("pageUrl")
    public String getPageUrl() {
        return pageUrl;
    }

    @JsonProperty("pageUrl")
    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    @JsonProperty("goal")
    public Integer getGoal() {
        return goal;
    }

    @JsonProperty("goal")
    public void setGoal(Integer goal) {
        this.goal = goal;
    }

    @JsonProperty("amountRaised")
    public Integer getAmountRaised() {
        return amountRaised;
    }

    @JsonProperty("amountRaised")
    public void setAmountRaised(Integer amountRaised) {
        this.amountRaised = amountRaised;
    }

    @JsonProperty("donationCount")
    public Integer getDonationCount() {
        return donationCount;
    }

    @JsonProperty("donationCount")
    public void setDonationCount(Integer donationCount) {
        this.donationCount = donationCount;
    }

    @JsonProperty("lastDonation")
    public String getLastDonation() {
        return lastDonation;
    }

    @JsonProperty("lastDonation")
    public void setLastDonation(String lastDonation) {
        this.lastDonation = lastDonation;
    }

    @JsonProperty("relatedFundraisersCount")
    public Integer getRelatedFundraisersCount() {
        return relatedFundraisersCount;
    }

    @JsonProperty("relatedFundraisersCount")
    public void setRelatedFundraisersCount(Integer relatedFundraisersCount) {
        this.relatedFundraisersCount = relatedFundraisersCount;
    }

    @JsonProperty("fundraisingPageEnabled")
    public Boolean getFundraisingPageEnabled() {
        return fundraisingPageEnabled;
    }

    @JsonProperty("fundraisingPageEnabled")
    public void setFundraisingPageEnabled(Boolean fundraisingPageEnabled) {
        this.fundraisingPageEnabled = fundraisingPageEnabled;
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
        if (!(o instanceof DonatedToFundraiser)) return false;

        DonatedToFundraiser that = (DonatedToFundraiser) o;

        if (getFundraiserId() != null ? !getFundraiserId().equals(that.getFundraiserId()) : that.getFundraiserId() != null)
            return false;
        if (getSupporterId() != null ? !getSupporterId().equals(that.getSupporterId()) : that.getSupporterId() != null)
            return false;
        if (getActivityId() != null ? !getActivityId().equals(that.getActivityId()) : that.getActivityId() != null)
            return false;
        if (getType() != null ? !getType().equals(that.getType()) : that.getType() != null) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getPageUrl() != null ? !getPageUrl().equals(that.getPageUrl()) : that.getPageUrl() != null) return false;
        if (getGoal() != null ? !getGoal().equals(that.getGoal()) : that.getGoal() != null) return false;
        if (getAmountRaised() != null ? !getAmountRaised().equals(that.getAmountRaised()) : that.getAmountRaised() != null)
            return false;
        if (getDonationCount() != null ? !getDonationCount().equals(that.getDonationCount()) : that.getDonationCount() != null)
            return false;
        if (getLastDonation() != null ? !getLastDonation().equals(that.getLastDonation()) : that.getLastDonation() != null)
            return false;
        if (getRelatedFundraisersCount() != null ? !getRelatedFundraisersCount().equals(that.getRelatedFundraisersCount()) : that.getRelatedFundraisersCount() != null)
            return false;
        if (getFundraisingPageEnabled() != null ? !getFundraisingPageEnabled().equals(that.getFundraisingPageEnabled()) : that.getFundraisingPageEnabled() != null)
            return false;
        if (getLastModified() != null ? !getLastModified().equals(that.getLastModified()) : that.getLastModified() != null)
            return false;
        return getAdditionalProperties() != null ? getAdditionalProperties().equals(that.getAdditionalProperties()) : that.getAdditionalProperties() == null;

    }

    @Override
    public int hashCode() {
        int result = getFundraiserId() != null ? getFundraiserId().hashCode() : 0;
        result = 31 * result + (getSupporterId() != null ? getSupporterId().hashCode() : 0);
        result = 31 * result + (getActivityId() != null ? getActivityId().hashCode() : 0);
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getPageUrl() != null ? getPageUrl().hashCode() : 0);
        result = 31 * result + (getGoal() != null ? getGoal().hashCode() : 0);
        result = 31 * result + (getAmountRaised() != null ? getAmountRaised().hashCode() : 0);
        result = 31 * result + (getDonationCount() != null ? getDonationCount().hashCode() : 0);
        result = 31 * result + (getLastDonation() != null ? getLastDonation().hashCode() : 0);
        result = 31 * result + (getRelatedFundraisersCount() != null ? getRelatedFundraisersCount().hashCode() : 0);
        result = 31 * result + (getFundraisingPageEnabled() != null ? getFundraisingPageEnabled().hashCode() : 0);
        result = 31 * result + (getLastModified() != null ? getLastModified().hashCode() : 0);
        result = 31 * result + (getAdditionalProperties() != null ? getAdditionalProperties().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DonatedToFundraiser{" +
                "fundraiserId='" + fundraiserId + '\'' +
                ", supporterId='" + supporterId + '\'' +
                ", activityId='" + activityId + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", pageUrl='" + pageUrl + '\'' +
                ", goal=" + goal +
                ", amountRaised=" + amountRaised +
                ", donationCount=" + donationCount +
                ", lastDonation='" + lastDonation + '\'' +
                ", relatedFundraisersCount=" + relatedFundraisersCount +
                ", fundraisingPageEnabled=" + fundraisingPageEnabled +
                ", lastModified='" + lastModified + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}