package com.salsalabs.ignite.automation.apiautomation.models.getactivitybytype.response.p2p;

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
        "activityId",
        "activityFormName",
        "activityFormId",
        "supporterId",
        "activityDate",
        "activityType",
        "lastModified",
        "customFieldValues",
        "donationId",
        "totalReceivedAmount",
        "oneTimeAmount",
        "donationType",
        "accountType",
        "accountNumber",
        "accountExpiration",
        "accountProvider",
        "paymentProcessorName",
        "wasImported",
        "transactions",
        "activityResult",
        "tickets",
        "donatedToFundraiser",
        "createdFundraisers"
})
public class Activity {

    @JsonProperty("activityId")
    private String activityId;
    @JsonProperty("activityFormName")
    private String activityFormName;
    @JsonProperty("activityFormId")
    private String activityFormId;
    @JsonProperty("supporterId")
    private String supporterId;
    @JsonProperty("activityDate")
    private String activityDate;
    @JsonProperty("activityType")
    private String activityType;
    @JsonProperty("lastModified")
    private String lastModified;
    @JsonProperty("customFieldValues")
    private List<Object> customFieldValues = null;
    @JsonProperty("donationId")
    private String donationId;
    @JsonProperty("totalReceivedAmount")
    private Integer totalReceivedAmount;
    @JsonProperty("oneTimeAmount")
    private Integer oneTimeAmount;
    @JsonProperty("donationType")
    private String donationType;
    @JsonProperty("accountType")
    private String accountType;
    @JsonProperty("accountNumber")
    private String accountNumber;
    @JsonProperty("accountExpiration")
    private String accountExpiration;
    @JsonProperty("accountProvider")
    private String accountProvider;
    @JsonProperty("paymentProcessorName")
    private String paymentProcessorName;
    @JsonProperty("wasImported")
    private Boolean wasImported;
    @JsonProperty("transactions")
    private List<Transaction> transactions = null;
    @JsonProperty("activityResult")
    private String activityResult;
    @JsonProperty("tickets")
    private List<Ticket> tickets = null;
    @JsonProperty("donatedToFundraiser")
    private DonatedToFundraiser donatedToFundraiser;
    @JsonProperty("createdFundraisers")
    private List<CreatedFundraiser> createdFundraisers = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("activityId")
    public String getActivityId() {
        return activityId;
    }

    @JsonProperty("activityId")
    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    @JsonProperty("activityFormName")
    public String getActivityFormName() {
        return activityFormName;
    }

    @JsonProperty("activityFormName")
    public void setActivityFormName(String activityFormName) {
        this.activityFormName = activityFormName;
    }

    @JsonProperty("activityFormId")
    public String getActivityFormId() {
        return activityFormId;
    }

    @JsonProperty("activityFormId")
    public void setActivityFormId(String activityFormId) {
        this.activityFormId = activityFormId;
    }

    @JsonProperty("supporterId")
    public String getSupporterId() {
        return supporterId;
    }

    @JsonProperty("supporterId")
    public void setSupporterId(String supporterId) {
        this.supporterId = supporterId;
    }

    @JsonProperty("activityDate")
    public String getActivityDate() {
        return activityDate;
    }

    @JsonProperty("activityDate")
    public void setActivityDate(String activityDate) {
        this.activityDate = activityDate;
    }

    @JsonProperty("activityType")
    public String getActivityType() {
        return activityType;
    }

    @JsonProperty("activityType")
    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    @JsonProperty("lastModified")
    public String getLastModified() {
        return lastModified;
    }

    @JsonProperty("lastModified")
    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    @JsonProperty("customFieldValues")
    public List<Object> getCustomFieldValues() {
        return customFieldValues;
    }

    @JsonProperty("customFieldValues")
    public void setCustomFieldValues(List<Object> customFieldValues) {
        this.customFieldValues = customFieldValues;
    }

    @JsonProperty("donationId")
    public String getDonationId() {
        return donationId;
    }

    @JsonProperty("donationId")
    public void setDonationId(String donationId) {
        this.donationId = donationId;
    }

    @JsonProperty("totalReceivedAmount")
    public Integer getTotalReceivedAmount() {
        return totalReceivedAmount;
    }

    @JsonProperty("totalReceivedAmount")
    public void setTotalReceivedAmount(Integer totalReceivedAmount) {
        this.totalReceivedAmount = totalReceivedAmount;
    }

    @JsonProperty("oneTimeAmount")
    public Integer getOneTimeAmount() {
        return oneTimeAmount;
    }

    @JsonProperty("oneTimeAmount")
    public void setOneTimeAmount(Integer oneTimeAmount) {
        this.oneTimeAmount = oneTimeAmount;
    }

    @JsonProperty("donationType")
    public String getDonationType() {
        return donationType;
    }

    @JsonProperty("donationType")
    public void setDonationType(String donationType) {
        this.donationType = donationType;
    }

    @JsonProperty("accountType")
    public String getAccountType() {
        return accountType;
    }

    @JsonProperty("accountType")
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @JsonProperty("accountNumber")
    public String getAccountNumber() {
        return accountNumber;
    }

    @JsonProperty("accountNumber")
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @JsonProperty("accountExpiration")
    public String getAccountExpiration() {
        return accountExpiration;
    }

    @JsonProperty("accountExpiration")
    public void setAccountExpiration(String accountExpiration) {
        this.accountExpiration = accountExpiration;
    }

    @JsonProperty("accountProvider")
    public String getAccountProvider() {
        return accountProvider;
    }

    @JsonProperty("accountProvider")
    public void setAccountProvider(String accountProvider) {
        this.accountProvider = accountProvider;
    }

    @JsonProperty("paymentProcessorName")
    public String getPaymentProcessorName() {
        return paymentProcessorName;
    }

    @JsonProperty("paymentProcessorName")
    public void setPaymentProcessorName(String paymentProcessorName) {
        this.paymentProcessorName = paymentProcessorName;
    }

    @JsonProperty("wasImported")
    public Boolean getWasImported() {
        return wasImported;
    }

    @JsonProperty("wasImported")
    public void setWasImported(Boolean wasImported) {
        this.wasImported = wasImported;
    }

    @JsonProperty("transactions")
    public List<Transaction> getTransactions() {
        return transactions;
    }

    @JsonProperty("transactions")
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @JsonProperty("activityResult")
    public String getActivityResult() {
        return activityResult;
    }

    @JsonProperty("activityResult")
    public void setActivityResult(String activityResult) {
        this.activityResult = activityResult;
    }

    @JsonProperty("tickets")
    public List<Ticket> getTickets() {
        return tickets;
    }

    @JsonProperty("tickets")
    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @JsonProperty("donatedToFundraiser")
    public DonatedToFundraiser getDonatedToFundraiser() {
        return donatedToFundraiser;
    }

    @JsonProperty("donatedToFundraiser")
    public void setDonatedToFundraiser(DonatedToFundraiser donatedToFundraiser) {
        this.donatedToFundraiser = donatedToFundraiser;
    }

    @JsonProperty("createdFundraisers")
    public List<CreatedFundraiser> getCreatedFundraisers() {
        return createdFundraisers;
    }

    @JsonProperty("createdFundraisers")
    public void setCreatedFundraisers(List<CreatedFundraiser> createdFundraisers) {
        this.createdFundraisers = createdFundraisers;
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
        if (!(o instanceof Activity)) return false;

        Activity activity = (Activity) o;

        if (getActivityId() != null ? !getActivityId().equals(activity.getActivityId()) : activity.getActivityId() != null)
            return false;
        if (getActivityFormName() != null ? !getActivityFormName().equals(activity.getActivityFormName()) : activity.getActivityFormName() != null)
            return false;
        if (getActivityFormId() != null ? !getActivityFormId().equals(activity.getActivityFormId()) : activity.getActivityFormId() != null)
            return false;
        if (getSupporterId() != null ? !getSupporterId().equals(activity.getSupporterId()) : activity.getSupporterId() != null)
            return false;
        if (getActivityDate() != null ? !getActivityDate().equals(activity.getActivityDate()) : activity.getActivityDate() != null)
            return false;
        if (getActivityType() != null ? !getActivityType().equals(activity.getActivityType()) : activity.getActivityType() != null)
            return false;
        if (getLastModified() != null ? !getLastModified().equals(activity.getLastModified()) : activity.getLastModified() != null)
            return false;
        if (getCustomFieldValues() != null ? !getCustomFieldValues().equals(activity.getCustomFieldValues()) : activity.getCustomFieldValues() != null)
            return false;
        if (getDonationId() != null ? !getDonationId().equals(activity.getDonationId()) : activity.getDonationId() != null)
            return false;
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
        if (getPaymentProcessorName() != null ? !getPaymentProcessorName().equals(activity.getPaymentProcessorName()) : activity.getPaymentProcessorName() != null)
            return false;
        if (getWasImported() != null ? !getWasImported().equals(activity.getWasImported()) : activity.getWasImported() != null)
            return false;
        if (getTransactions() != null ? !getTransactions().equals(activity.getTransactions()) : activity.getTransactions() != null)
            return false;
        if (getActivityResult() != null ? !getActivityResult().equals(activity.getActivityResult()) : activity.getActivityResult() != null)
            return false;
        if (getTickets() != null ? !getTickets().equals(activity.getTickets()) : activity.getTickets() != null)
            return false;
        if (getDonatedToFundraiser() != null ? !getDonatedToFundraiser().equals(activity.getDonatedToFundraiser()) : activity.getDonatedToFundraiser() != null)
            return false;
        if (getCreatedFundraisers() != null ? !getCreatedFundraisers().equals(activity.getCreatedFundraisers()) : activity.getCreatedFundraisers() != null)
            return false;
        return getAdditionalProperties() != null ? getAdditionalProperties().equals(activity.getAdditionalProperties()) : activity.getAdditionalProperties() == null;

    }

    @Override
    public int hashCode() {
        int result = getActivityId() != null ? getActivityId().hashCode() : 0;
        result = 31 * result + (getActivityFormName() != null ? getActivityFormName().hashCode() : 0);
        result = 31 * result + (getActivityFormId() != null ? getActivityFormId().hashCode() : 0);
        result = 31 * result + (getSupporterId() != null ? getSupporterId().hashCode() : 0);
        result = 31 * result + (getActivityDate() != null ? getActivityDate().hashCode() : 0);
        result = 31 * result + (getActivityType() != null ? getActivityType().hashCode() : 0);
        result = 31 * result + (getLastModified() != null ? getLastModified().hashCode() : 0);
        result = 31 * result + (getCustomFieldValues() != null ? getCustomFieldValues().hashCode() : 0);
        result = 31 * result + (getDonationId() != null ? getDonationId().hashCode() : 0);
        result = 31 * result + (getTotalReceivedAmount() != null ? getTotalReceivedAmount().hashCode() : 0);
        result = 31 * result + (getOneTimeAmount() != null ? getOneTimeAmount().hashCode() : 0);
        result = 31 * result + (getDonationType() != null ? getDonationType().hashCode() : 0);
        result = 31 * result + (getAccountType() != null ? getAccountType().hashCode() : 0);
        result = 31 * result + (getAccountNumber() != null ? getAccountNumber().hashCode() : 0);
        result = 31 * result + (getAccountExpiration() != null ? getAccountExpiration().hashCode() : 0);
        result = 31 * result + (getAccountProvider() != null ? getAccountProvider().hashCode() : 0);
        result = 31 * result + (getPaymentProcessorName() != null ? getPaymentProcessorName().hashCode() : 0);
        result = 31 * result + (getWasImported() != null ? getWasImported().hashCode() : 0);
        result = 31 * result + (getTransactions() != null ? getTransactions().hashCode() : 0);
        result = 31 * result + (getActivityResult() != null ? getActivityResult().hashCode() : 0);
        result = 31 * result + (getTickets() != null ? getTickets().hashCode() : 0);
        result = 31 * result + (getDonatedToFundraiser() != null ? getDonatedToFundraiser().hashCode() : 0);
        result = 31 * result + (getCreatedFundraisers() != null ? getCreatedFundraisers().hashCode() : 0);
        result = 31 * result + (getAdditionalProperties() != null ? getAdditionalProperties().hashCode() : 0);
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
                ", donationId='" + donationId + '\'' +
                ", totalReceivedAmount=" + totalReceivedAmount +
                ", oneTimeAmount=" + oneTimeAmount +
                ", donationType='" + donationType + '\'' +
                ", accountType='" + accountType + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", accountExpiration='" + accountExpiration + '\'' +
                ", accountProvider='" + accountProvider + '\'' +
                ", paymentProcessorName='" + paymentProcessorName + '\'' +
                ", wasImported=" + wasImported +
                ", transactions=" + transactions +
                ", activityResult='" + activityResult + '\'' +
                ", tickets=" + tickets +
                ", donatedToFundraiser=" + donatedToFundraiser +
                ", createdFundraisers=" + createdFundraisers +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}