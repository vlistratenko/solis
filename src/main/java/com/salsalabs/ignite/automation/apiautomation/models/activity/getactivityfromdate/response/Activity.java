
package com.salsalabs.ignite.automation.apiautomation.models.activity.getactivityfromdate.response;

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
    @SerializedName("activityFormId")
    @Expose
    private String activityFormId;
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
    private List<Object> customFieldValues = null;
    @SerializedName("letters")
    @Expose
    private List<Letter> letters = null;
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
    @SerializedName("accountNumber")
    @Expose
    private String accountNumber;
    @SerializedName("wasImported")
    @Expose
    private Boolean wasImported;
    @SerializedName("wasAPIImported")
    @Expose
    private Boolean wasAPIImported;
    @SerializedName("transactions")
    @Expose
    private List<Transaction> transactions = null;
    @SerializedName("accountType")
    @Expose
    private String accountType;
    @SerializedName("accountExpiration")
    @Expose
    private String accountExpiration;
    @SerializedName("accountProvider")
    @Expose
    private String accountProvider;
    @SerializedName("paymentProcessorName")
    @Expose
    private String paymentProcessorName;
    @SerializedName("activityResult")
    @Expose
    private String activityResult;
    @SerializedName("tickets")
    @Expose
    private List<Ticket> tickets = null;
    @SerializedName("donatedToFundraiser")
    @Expose
    private DonatedToFundraiser donatedToFundraiser;
    @SerializedName("createdFundraisers")
    @Expose
    private List<CreatedFundraiser> createdFundraisers = null;

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

    public String getActivityFormId() {
        return activityFormId;
    }

    public void setActivityFormId(String activityFormId) {
        this.activityFormId = activityFormId;
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

    public List<Object> getCustomFieldValues() {
        return customFieldValues;
    }

    public void setCustomFieldValues(List<Object> customFieldValues) {
        this.customFieldValues = customFieldValues;
    }

    public List<Letter> getLetters() {
        return letters;
    }

    public void setLetters(List<Letter> letters) {
        this.letters = letters;
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

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
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

    public String getPaymentProcessorName() {
        return paymentProcessorName;
    }

    public void setPaymentProcessorName(String paymentProcessorName) {
        this.paymentProcessorName = paymentProcessorName;
    }

    public String getActivityResult() {
        return activityResult;
    }

    public void setActivityResult(String activityResult) {
        this.activityResult = activityResult;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public DonatedToFundraiser getDonatedToFundraiser() {
        return donatedToFundraiser;
    }

    public void setDonatedToFundraiser(DonatedToFundraiser donatedToFundraiser) {
        this.donatedToFundraiser = donatedToFundraiser;
    }

    public List<CreatedFundraiser> getCreatedFundraisers() {
        return createdFundraisers;
    }

    public void setCreatedFundraisers(List<CreatedFundraiser> createdFundraisers) {
        this.createdFundraisers = createdFundraisers;
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
        if (getActivityFormId() != null ? !getActivityFormId().equals(activity.getActivityFormId()) : activity.getActivityFormId() != null)
            return false;
        if (getActivityDate() != null ? !getActivityDate().equals(activity.getActivityDate()) : activity.getActivityDate() != null)
            return false;
        if (getActivityType() != null ? !getActivityType().equals(activity.getActivityType()) : activity.getActivityType() != null)
            return false;
        if (getCustomFieldValues() != null ? !getCustomFieldValues().equals(activity.getCustomFieldValues()) : activity.getCustomFieldValues() != null)
            return false;
        if (getLetters() != null ? !getLetters().equals(activity.getLetters()) : activity.getLetters() != null)
            return false;
        if (getTotalReceivedAmount() != null ? !getTotalReceivedAmount().equals(activity.getTotalReceivedAmount()) : activity.getTotalReceivedAmount() != null)
            return false;
        if (getOneTimeAmount() != null ? !getOneTimeAmount().equals(activity.getOneTimeAmount()) : activity.getOneTimeAmount() != null)
            return false;
        if (getDonationType() != null ? !getDonationType().equals(activity.getDonationType()) : activity.getDonationType() != null)
            return false;
        if (getAccountNumber() != null ? !getAccountNumber().equals(activity.getAccountNumber()) : activity.getAccountNumber() != null)
            return false;
        if (getWasImported() != null ? !getWasImported().equals(activity.getWasImported()) : activity.getWasImported() != null)
            return false;
        if (getWasAPIImported() != null ? !getWasAPIImported().equals(activity.getWasAPIImported()) : activity.getWasAPIImported() != null)
            return false;
        if (getTransactions() != null ? !getTransactions().equals(activity.getTransactions()) : activity.getTransactions() != null)
            return false;
        if (getAccountType() != null ? !getAccountType().equals(activity.getAccountType()) : activity.getAccountType() != null)
            return false;
        if (getAccountExpiration() != null ? !getAccountExpiration().equals(activity.getAccountExpiration()) : activity.getAccountExpiration() != null)
            return false;
        if (getAccountProvider() != null ? !getAccountProvider().equals(activity.getAccountProvider()) : activity.getAccountProvider() != null)
            return false;
        if (getPaymentProcessorName() != null ? !getPaymentProcessorName().equals(activity.getPaymentProcessorName()) : activity.getPaymentProcessorName() != null)
            return false;
        if (getActivityResult() != null ? !getActivityResult().equals(activity.getActivityResult()) : activity.getActivityResult() != null)
            return false;
        if (getTickets() != null ? !getTickets().equals(activity.getTickets()) : activity.getTickets() != null)
            return false;
        if (getDonatedToFundraiser() != null ? !getDonatedToFundraiser().equals(activity.getDonatedToFundraiser()) : activity.getDonatedToFundraiser() != null)
            return false;
        return getCreatedFundraisers() != null ? getCreatedFundraisers().equals(activity.getCreatedFundraisers()) : activity.getCreatedFundraisers() == null;

    }

    @Override
    public int hashCode() {
        int result = getActivityId() != null ? getActivityId().hashCode() : 0;
        result = 31 * result + (getActivityFormName() != null ? getActivityFormName().hashCode() : 0);
        result = 31 * result + (getActivityFormId() != null ? getActivityFormId().hashCode() : 0);
        result = 31 * result + (getActivityDate() != null ? getActivityDate().hashCode() : 0);
        result = 31 * result + (getActivityType() != null ? getActivityType().hashCode() : 0);
        result = 31 * result + (getCustomFieldValues() != null ? getCustomFieldValues().hashCode() : 0);
        result = 31 * result + (getLetters() != null ? getLetters().hashCode() : 0);
        result = 31 * result + (getTotalReceivedAmount() != null ? getTotalReceivedAmount().hashCode() : 0);
        result = 31 * result + (getOneTimeAmount() != null ? getOneTimeAmount().hashCode() : 0);
        result = 31 * result + (getDonationType() != null ? getDonationType().hashCode() : 0);
        result = 31 * result + (getAccountNumber() != null ? getAccountNumber().hashCode() : 0);
        result = 31 * result + (getWasImported() != null ? getWasImported().hashCode() : 0);
        result = 31 * result + (getWasAPIImported() != null ? getWasAPIImported().hashCode() : 0);
        result = 31 * result + (getTransactions() != null ? getTransactions().hashCode() : 0);
        result = 31 * result + (getAccountType() != null ? getAccountType().hashCode() : 0);
        result = 31 * result + (getAccountExpiration() != null ? getAccountExpiration().hashCode() : 0);
        result = 31 * result + (getAccountProvider() != null ? getAccountProvider().hashCode() : 0);
        result = 31 * result + (getPaymentProcessorName() != null ? getPaymentProcessorName().hashCode() : 0);
        result = 31 * result + (getActivityResult() != null ? getActivityResult().hashCode() : 0);
        result = 31 * result + (getTickets() != null ? getTickets().hashCode() : 0);
        result = 31 * result + (getDonatedToFundraiser() != null ? getDonatedToFundraiser().hashCode() : 0);
        result = 31 * result + (getCreatedFundraisers() != null ? getCreatedFundraisers().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "activityId='" + activityId + '\'' +
                ", activityFormName='" + activityFormName + '\'' +
                ", activityFormId='" + activityFormId + '\'' +
                ", supporterId='" + supporterId + '\'' +
                ", activityDate='" + activityDate + '\'' +
                ", activityType='" + activityType + '\'' +
                ", lastModified='" + lastModified + '\'' +
                ", customFieldValues=" + customFieldValues +
                ", letters=" + letters +
                ", donationId='" + donationId + '\'' +
                ", totalReceivedAmount=" + totalReceivedAmount +
                ", oneTimeAmount=" + oneTimeAmount +
                ", donationType='" + donationType + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", wasImported=" + wasImported +
                ", wasAPIImported=" + wasAPIImported +
                ", transactions=" + transactions +
                ", accountType='" + accountType + '\'' +
                ", accountExpiration='" + accountExpiration + '\'' +
                ", accountProvider='" + accountProvider + '\'' +
                ", paymentProcessorName='" + paymentProcessorName + '\'' +
                ", activityResult='" + activityResult + '\'' +
                ", tickets=" + tickets +
                ", donatedToFundraiser=" + donatedToFundraiser +
                ", createdFundraisers=" + createdFundraisers +
                '}';
    }
}
