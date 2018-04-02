package com.salsalabs.ignite.automation.apiautomation.models.activity.getactivitybyid.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Activity {

    @SerializedName("activityId")
    @Expose
    private String activityId;
    @SerializedName("activityFormName")
    @Expose
    private String activityFormName;
    @SerializedName("supporterId")
    @Expose
    private String supporterId;
    @SerializedName("activityDate")
    @Expose
    private String activityDate;
    @SerializedName("activityType")
    @Expose
    private String activityType;
    @SerializedName("lastModified")
    @Expose
    private String lastModified;
    @SerializedName("customFieldValues")
    @Expose
    private List<CustomFieldValue> customFieldValues = null;
    @SerializedName("donationId")
    @Expose
    private String donationId;
    @SerializedName("totalReceivedAmount")
    @Expose
    private Integer totalReceivedAmount;
    @SerializedName("oneTimeAmount")
    @Expose
    private Integer oneTimeAmount;
    @SerializedName("donationType")
    @Expose
    private String donationType;
    @SerializedName("accountType")
    @Expose
    private String accountType;
    @SerializedName("accountNumber")
    @Expose
    private String accountNumber;
    @SerializedName("accountExpiration")
    @Expose
    private String accountExpiration;
    @SerializedName("accountProvider")
    @Expose
    private String accountProvider;
    @SerializedName("designation")
    @Expose
    private String designation;
    @SerializedName("dedicationType")
    @Expose
    private String dedicationType;
    @SerializedName("dedication")
    @Expose
    private String dedication;
    @SerializedName("notify")
    @Expose
    private String notify;
    @SerializedName("wasImported")
    @Expose
    private Boolean wasImported;
    @SerializedName("wasAPIImported")
    @Expose
    private Boolean wasAPIImported;
    @SerializedName("transactions")
    @Expose
    private List<Transaction> transactions = null;
    @SerializedName("activityFormId")
    @Expose
    private String activityFormId;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActivityFormName() {
        return activityFormName;
    }

    public void setActivityFormName(String activityFormName) {
        this.activityFormName = activityFormName;
    }

    public String getSupporterId() {
        return supporterId;
    }

    public void setSupporterId(String supporterId) {
        this.supporterId = supporterId;
    }

    public String getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(String activityDate) {
        this.activityDate = activityDate;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public List<CustomFieldValue> getCustomFieldValues() {
        return customFieldValues;
    }

    public void setCustomFieldValues(List<CustomFieldValue> customFieldValues) {
        this.customFieldValues = customFieldValues;
    }

    public String getDonationId() {
        return donationId;
    }

    public void setDonationId(String donationId) {
        this.donationId = donationId;
    }

    public Integer getTotalReceivedAmount() {
        return totalReceivedAmount;
    }

    public void setTotalReceivedAmount(Integer totalReceivedAmount) {
        this.totalReceivedAmount = totalReceivedAmount;
    }

    public Integer getOneTimeAmount() {
        return oneTimeAmount;
    }

    public void setOneTimeAmount(Integer oneTimeAmount) {
        this.oneTimeAmount = oneTimeAmount;
    }

    public String getDonationType() {
        return donationType;
    }

    public void setDonationType(String donationType) {
        this.donationType = donationType;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountExpiration() {
        return accountExpiration;
    }

    public void setAccountExpiration(String accountExpiration) {
        this.accountExpiration = accountExpiration;
    }

    public String getAccountProvider() {
        return accountProvider;
    }

    public void setAccountProvider(String accountProvider) {
        this.accountProvider = accountProvider;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDedicationType() {
        return dedicationType;
    }

    public void setDedicationType(String dedicationType) {
        this.dedicationType = dedicationType;
    }

    public String getDedication() {
        return dedication;
    }

    public void setDedication(String dedication) {
        this.dedication = dedication;
    }

    public String getNotify() {
        return notify;
    }

    public void setNotify(String notify) {
        this.notify = notify;
    }

    public Boolean getWasImported() {
        return wasImported;
    }

    public void setWasImported(Boolean wasImported) {
        this.wasImported = wasImported;
    }

    public Boolean getWasAPIImported() {
        return wasAPIImported;
    }

    public void setWasAPIImported(Boolean wasAPIImported) {
        this.wasAPIImported = wasAPIImported;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public String getActivityFormId() {
        return activityFormId;
    }

    public void setActivityFormId(String activityFormId) {
        this.activityFormId = activityFormId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Activity)) return false;

        Activity activity = (Activity) o;

        if (getActivityId() != null ? !getActivityId().equals(activity.getActivityId()) : activity.getActivityId() != null)
            return false;
        if (getActivityFormName() != null ? !getActivityFormName().equals(activity.getActivityFormName()) : activity.getActivityFormName() != null)
            return false;
      /*  if (getSupporterId() != null ? !getSupporterId().equals(activity.getSupporterId()) : activity.getSupporterId() != null)
            return false;*/
        if (getActivityDate() != null ? !getActivityDate().equals(activity.getActivityDate()) : activity.getActivityDate() != null)
            return false;
        if (getActivityType() != null ? !getActivityType().equals(activity.getActivityType()) : activity.getActivityType() != null)
            return false;
        /*if (getLastModified() != null ? !getLastModified().equals(activity.getLastModified()) : activity.getLastModified() != null)
            return false;*/
        if (getCustomFieldValues() != null ? !getCustomFieldValues().equals(activity.getCustomFieldValues()) : activity.getCustomFieldValues() != null)
            return false;
       /* if (getDonationId() != null ? !getDonationId().equals(activity.getDonationId()) : activity.getDonationId() != null)
            return false;*/
        if (getTotalReceivedAmount() != null ? !getTotalReceivedAmount().equals(activity.getTotalReceivedAmount()) : activity.getTotalReceivedAmount() != null)
            return false;
        if (getOneTimeAmount() != null ? !getOneTimeAmount().equals(activity.getOneTimeAmount()) : activity.getOneTimeAmount() != null)
            return false;
        if (getDonationType() != null ? !getDonationType().equals(activity.getDonationType()) : activity.getDonationType() != null)
            return false;
        if (getAccountType() != null ? !getAccountType().equals(activity.getAccountType()) : activity.getAccountType() != null)
            return false;
        if (getAccountNumber() != null ? !getAccountNumber().equals(activity.getAccountNumber()) : activity.getAccountNumber() != null)
            return false;
        if (getAccountExpiration() != null ? !getAccountExpiration().equals(activity.getAccountExpiration()) : activity.getAccountExpiration() != null)
            return false;
        if (getAccountProvider() != null ? !getAccountProvider().equals(activity.getAccountProvider()) : activity.getAccountProvider() != null)
            return false;
        if (getDesignation() != null ? !getDesignation().equals(activity.getDesignation()) : activity.getDesignation() != null)
            return false;
        if (getDedicationType() != null ? !getDedicationType().equals(activity.getDedicationType()) : activity.getDedicationType() != null)
            return false;
        if (getDedication() != null ? !getDedication().equals(activity.getDedication()) : activity.getDedication() != null)
            return false;
        if (getNotify() != null ? !getNotify().equals(activity.getNotify()) : activity.getNotify() != null)
            return false;
        if (getWasImported() != null ? !getWasImported().equals(activity.getWasImported()) : activity.getWasImported() != null)
            return false;
        if (getWasAPIImported() != null ? !getWasAPIImported().equals(activity.getWasAPIImported()) : activity.getWasAPIImported() != null)
            return false;
        if (getTransactions() != null ? !getTransactions().equals(activity.getTransactions()) : activity.getTransactions() != null)
            return false;
        return getActivityFormId() != null ? getActivityFormId().equals(activity.getActivityFormId()) : activity.getActivityFormId() == null;

    }

    @Override
    public int hashCode() {
        int result = getActivityId() != null ? getActivityId().hashCode() : 0;
        result = 31 * result + (getActivityFormName() != null ? getActivityFormName().hashCode() : 0);
        //result = 31 * result + (getSupporterId() != null ? getSupporterId().hashCode() : 0);
        result = 31 * result + (getActivityDate() != null ? getActivityDate().hashCode() : 0);
        result = 31 * result + (getActivityType() != null ? getActivityType().hashCode() : 0);
        //result = 31 * result + (getLastModified() != null ? getLastModified().hashCode() : 0);
        result = 31 * result + (getCustomFieldValues() != null ? getCustomFieldValues().hashCode() : 0);
        //result = 31 * result + (getDonationId() != null ? getDonationId().hashCode() : 0);
        result = 31 * result + (getTotalReceivedAmount() != null ? getTotalReceivedAmount().hashCode() : 0);
        result = 31 * result + (getOneTimeAmount() != null ? getOneTimeAmount().hashCode() : 0);
        result = 31 * result + (getDonationType() != null ? getDonationType().hashCode() : 0);
        result = 31 * result + (getAccountType() != null ? getAccountType().hashCode() : 0);
        result = 31 * result + (getAccountNumber() != null ? getAccountNumber().hashCode() : 0);
        result = 31 * result + (getAccountExpiration() != null ? getAccountExpiration().hashCode() : 0);
        result = 31 * result + (getAccountProvider() != null ? getAccountProvider().hashCode() : 0);
        result = 31 * result + (getDesignation() != null ? getDesignation().hashCode() : 0);
        result = 31 * result + (getDedicationType() != null ? getDedicationType().hashCode() : 0);
        result = 31 * result + (getDedication() != null ? getDedication().hashCode() : 0);
        result = 31 * result + (getNotify() != null ? getNotify().hashCode() : 0);
        result = 31 * result + (getWasImported() != null ? getWasImported().hashCode() : 0);
        result = 31 * result + (getWasAPIImported() != null ? getWasAPIImported().hashCode() : 0);
        result = 31 * result + (getTransactions() != null ? getTransactions().hashCode() : 0);
        result = 31 * result + (getActivityFormId() != null ? getActivityFormId().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "activityId='" + activityId + '\'' +
                ", activityFormName='" + activityFormName + '\'' +
                ", supporterId='" + supporterId + '\'' +
                ", activityDate='" + activityDate + '\'' +
                ", activityType='" + activityType + '\'' +
                ", lastModified='" + lastModified + '\'' +
                ", customFieldValues=" + customFieldValues +
                ", donationId='" + donationId + '\'' +
                ", totalReceivedAmount=" + totalReceivedAmount +
                ", oneTimeAmount=" + oneTimeAmount +
                ", donationType='" + donationType + '\'' +
                ", accountType='" + accountType + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", accountExpiration='" + accountExpiration + '\'' +
                ", accountProvider='" + accountProvider + '\'' +
                ", designation='" + designation + '\'' +
                ", dedicationType='" + dedicationType + '\'' +
                ", dedication='" + dedication + '\'' +
                ", notify='" + notify + '\'' +
                ", wasImported=" + wasImported +
                ", wasAPIImported=" + wasAPIImported +
                ", transactions=" + transactions +
                ", activityFormId='" + activityFormId + '\'' +
                '}';
    }
}
