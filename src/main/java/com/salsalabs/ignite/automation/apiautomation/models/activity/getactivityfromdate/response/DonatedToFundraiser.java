
package com.salsalabs.ignite.automation.apiautomation.models.activity.getactivityfromdate.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DonatedToFundraiser {

    @SerializedName("fundraiserId")
    @Expose
    private String fundraiserId;
    @SerializedName("supporterId")
    @Expose
    private String supporterId;
    @SerializedName("activityId")
    @Expose
    private String activityId;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("pageUrl")
    @Expose
    private String pageUrl;
    @SerializedName("goal")
    @Expose
    private Integer goal;
    @SerializedName("amountRaised")
    @Expose
    private Integer amountRaised;
    @SerializedName("donationCount")
    @Expose
    private Integer donationCount;
    @SerializedName("lastDonation")
    @Expose
    private String lastDonation;
    @SerializedName("relatedFundraisersCount")
    @Expose
    private Integer relatedFundraisersCount;
    @SerializedName("fundraisingPageEnabled")
    @Expose
    private Boolean fundraisingPageEnabled;
    @SerializedName("lastModified")
    @Expose
    private String lastModified;

    public String getFundraiserId() {
        return fundraiserId;
    }

    public void setFundraiserId(String fundraiserId) {
        this.fundraiserId = fundraiserId;
    }

    public String getSupporterId() {
        return supporterId;
    }

    public void setSupporterId(String supporterId) {
        this.supporterId = supporterId;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public Integer getGoal() {
        return goal;
    }

    public void setGoal(Integer goal) {
        this.goal = goal;
    }

    public Integer getAmountRaised() {
        return amountRaised;
    }

    public void setAmountRaised(Integer amountRaised) {
        this.amountRaised = amountRaised;
    }

    public Integer getDonationCount() {
        return donationCount;
    }

    public void setDonationCount(Integer donationCount) {
        this.donationCount = donationCount;
    }

    public String getLastDonation() {
        return lastDonation;
    }

    public void setLastDonation(String lastDonation) {
        this.lastDonation = lastDonation;
    }

    public Integer getRelatedFundraisersCount() {
        return relatedFundraisersCount;
    }

    public void setRelatedFundraisersCount(Integer relatedFundraisersCount) {
        this.relatedFundraisersCount = relatedFundraisersCount;
    }

    public Boolean getFundraisingPageEnabled() {
        return fundraisingPageEnabled;
    }

    public void setFundraisingPageEnabled(Boolean fundraisingPageEnabled) {
        this.fundraisingPageEnabled = fundraisingPageEnabled;
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
        if (!(o instanceof DonatedToFundraiser)) return false;

        DonatedToFundraiser that = (DonatedToFundraiser) o;

        if (getFundraiserId() != null ? !getFundraiserId().equals(that.getFundraiserId()) : that.getFundraiserId() != null)
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
        return getFundraisingPageEnabled() != null ? getFundraisingPageEnabled().equals(that.getFundraisingPageEnabled()) : that.getFundraisingPageEnabled() == null;

    }

    @Override
    public int hashCode() {
        int result = getFundraiserId() != null ? getFundraiserId().hashCode() : 0;
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
                '}';
    }
}
