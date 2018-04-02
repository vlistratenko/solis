package com.salsalabs.ignite.automation.apiautomation.models.donations.response;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "activityId",
        "accountType",
        "accountNumber",
        "accountExpiration",
        "accountProvider",
        "dedicationType",
        "dedication",
        "notify",
        "type",
        "date",
        "amount",
        "deductibleAmount",
        "designation",
        "feesPaid",
        "gatewayTransactionId",
        "gatewayAuthorizationCode",
        "supporter",
        "activityFormName",
        "result",
        "customFieldValues",
        "activityFormId"
})
public class Donation {

    @JsonProperty("activityId")
    private String activityId;
    @JsonProperty("accountType")
    private String accountType;
    @JsonProperty("accountNumber")
    private String accountNumber;
    @JsonProperty("accountExpiration")
    private String accountExpiration;
    @JsonProperty("accountProvider")
    private String accountProvider;
    @JsonProperty("dedicationType")
    private String dedicationType;
    @JsonProperty("dedication")
    private String dedication;
    @JsonProperty("notify")
    private String notify;
    @JsonProperty("type")
    private String type;
    @JsonProperty("date")
    private String date;
    @JsonProperty("amount")
    private Integer amount;
    @JsonProperty("deductibleAmount")
    private Integer deductibleAmount;
    @JsonProperty("designation")
    private String designation;
    @JsonProperty("feesPaid")
    private Integer feesPaid;
    @JsonProperty("gatewayTransactionId")
    private String gatewayTransactionId;
    @JsonProperty("gatewayAuthorizationCode")
    private String gatewayAuthorizationCode;
    @JsonProperty("supporter")
    private Supporter supporter;
    @JsonProperty("activityFormName")
    private String activityFormName;
    @JsonProperty("result")
    private String result;
    @JsonProperty("customFieldValues")
    private List<CustomFieldValue_> customFieldValues = null;
    @JsonProperty("activityFormId")
    private String activityFormId;
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

    @JsonProperty("dedicationType")
    public String getDedicationType() {
        return dedicationType;
    }

    @JsonProperty("dedicationType")
    public void setDedicationType(String dedicationType) {
        this.dedicationType = dedicationType;
    }

    @JsonProperty("dedication")
    public String getDedication() {
        return dedication;
    }

    @JsonProperty("dedication")
    public void setDedication(String dedication) {
        this.dedication = dedication;
    }

    @JsonProperty("notify")
    public String getNotify() {
        return notify;
    }

    @JsonProperty("notify")
    public void setNotify(String notify) {
        this.notify = notify;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("amount")
    public Integer getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @JsonProperty("deductibleAmount")
    public Integer getDeductibleAmount() {
        return deductibleAmount;
    }

    @JsonProperty("deductibleAmount")
    public void setDeductibleAmount(Integer deductibleAmount) {
        this.deductibleAmount = deductibleAmount;
    }

    @JsonProperty("designation")
    public String getDesignation() {
        return designation;
    }

    @JsonProperty("designation")
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @JsonProperty("feesPaid")
    public Integer getFeesPaid() {
        return feesPaid;
    }

    @JsonProperty("feesPaid")
    public void setFeesPaid(Integer feesPaid) {
        this.feesPaid = feesPaid;
    }

    @JsonProperty("gatewayTransactionId")
    public String getGatewayTransactionId() {
        return gatewayTransactionId;
    }

    @JsonProperty("gatewayTransactionId")
    public void setGatewayTransactionId(String gatewayTransactionId) {
        this.gatewayTransactionId = gatewayTransactionId;
    }

    @JsonProperty("gatewayAuthorizationCode")
    public String getGatewayAuthorizationCode() {
        return gatewayAuthorizationCode;
    }

    @JsonProperty("gatewayAuthorizationCode")
    public void setGatewayAuthorizationCode(String gatewayAuthorizationCode) {
        this.gatewayAuthorizationCode = gatewayAuthorizationCode;
    }

    @JsonProperty("supporter")
    public Supporter getSupporter() {
        return supporter;
    }

    @JsonProperty("supporter")
    public void setSupporter(Supporter supporter) {
        this.supporter = supporter;
    }

    @JsonProperty("activityFormName")
    public String getActivityFormName() {
        return activityFormName;
    }

    @JsonProperty("activityFormName")
    public void setActivityFormName(String activityFormName) {
        this.activityFormName = activityFormName;
    }

    @JsonProperty("result")
    public String getResult() {
        return result;
    }

    @JsonProperty("result")
    public void setResult(String result) {
        this.result = result;
    }

    @JsonProperty("customFieldValues")
    public List<CustomFieldValue_> getCustomFieldValues() {
        return customFieldValues;
    }

    @JsonProperty("customFieldValues")
    public void setCustomFieldValues(List<CustomFieldValue_> customFieldValues) {
        this.customFieldValues = customFieldValues;
    }

    @JsonProperty("activityFormId")
    public String getActivityFormId() {
        return activityFormId;
    }

    @JsonProperty("activityFormId")
    public void setActivityFormId(String activityFormId) {
        this.activityFormId = activityFormId;
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
    public String toString() {
        return "Donation{" +
                "activityId='" + activityId + '\'' +
                ", accountType='" + accountType + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", accountExpiration='" + accountExpiration + '\'' +
                ", accountProvider='" + accountProvider + '\'' +
                ", dedicationType='" + dedicationType + '\'' +
                ", dedication='" + dedication + '\'' +
                ", notify='" + notify + '\'' +
                ", type='" + type + '\'' +
                ", date='" + date + '\'' +
                ", amount=" + amount +
                ", deductibleAmount=" + deductibleAmount +
                ", designation='" + designation + '\'' +
                ", feesPaid=" + feesPaid +
                ", gatewayTransactionId='" + gatewayTransactionId + '\'' +
                ", gatewayAuthorizationCode='" + gatewayAuthorizationCode + '\'' +
                ", supporter=" + supporter +
                ", activityFormName='" + activityFormName + '\'' +
                ", result='" + result + '\'' +
                ", customFieldValues=" + customFieldValues +
                ", activityFormId='" + activityFormId + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Donation)) return false;

        Donation donation = (Donation) o;

        if (getAccountType() != null ? !getAccountType().equals(donation.getAccountType()) : donation.getAccountType() != null)
            return false;
        if (getAccountNumber() != null ? !getAccountNumber().equals(donation.getAccountNumber()) : donation.getAccountNumber() != null)
            return false;
        if (getAccountExpiration() != null ? !getAccountExpiration().equals(donation.getAccountExpiration()) : donation.getAccountExpiration() != null)
            return false;
        if (getAccountProvider() != null ? !getAccountProvider().equals(donation.getAccountProvider()) : donation.getAccountProvider() != null)
            return false;
        if (getDedicationType() != null ? !getDedicationType().equals(donation.getDedicationType()) : donation.getDedicationType() != null)
            return false;
        if (getDedication() != null ? !getDedication().equals(donation.getDedication()) : donation.getDedication() != null)
            return false;
        if (getNotify() != null ? !getNotify().equals(donation.getNotify()) : donation.getNotify() != null)
            return false;
        if (getType() != null ? !getType().equals(donation.getType()) : donation.getType() != null) return false;
        if (getDate() != null ? !getDate().equals(donation.getDate()) : donation.getDate() != null) return false;
        if (getAmount() != null ? !getAmount().equals(donation.getAmount()) : donation.getAmount() != null)
            return false;
        if (getDeductibleAmount() != null ? !getDeductibleAmount().equals(donation.getDeductibleAmount()) : donation.getDeductibleAmount() != null)
            return false;
        if (getDesignation() != null ? !getDesignation().equals(donation.getDesignation()) : donation.getDesignation() != null)
            return false;
        if (getFeesPaid() != null ? !getFeesPaid().equals(donation.getFeesPaid()) : donation.getFeesPaid() != null)
            return false;
        if (getGatewayTransactionId() != null ? !getGatewayTransactionId().equals(donation.getGatewayTransactionId()) : donation.getGatewayTransactionId() != null)
            return false;
        if (getGatewayAuthorizationCode() != null ? !getGatewayAuthorizationCode().equals(donation.getGatewayAuthorizationCode()) : donation.getGatewayAuthorizationCode() != null)
            return false;
        if (getSupporter() != null ? !getSupporter().equals(donation.getSupporter()) : donation.getSupporter() != null)
            return false;
        if (getActivityFormName() != null ? !getActivityFormName().equals(donation.getActivityFormName()) : donation.getActivityFormName() != null)
            return false;
        if (getResult() != null ? !getResult().equals(donation.getResult()) : donation.getResult() != null)
            return false;
        if (getCustomFieldValues() != null ? !getCustomFieldValues().equals(donation.getCustomFieldValues()) : donation.getCustomFieldValues() != null)
            return false;
        if (getActivityFormId() != null ? !getActivityFormId().equals(donation.getActivityFormId()) : donation.getActivityFormId() != null)
            return false;
        return getAdditionalProperties() != null ? getAdditionalProperties().equals(donation.getAdditionalProperties()) : donation.getAdditionalProperties() == null;

    }

    @Override
    public int hashCode() {
        int result1 = getAccountType() != null ? getAccountType().hashCode() : 0;
        result1 = 31 * result1 + (getAccountNumber() != null ? getAccountNumber().hashCode() : 0);
        result1 = 31 * result1 + (getAccountExpiration() != null ? getAccountExpiration().hashCode() : 0);
        result1 = 31 * result1 + (getAccountProvider() != null ? getAccountProvider().hashCode() : 0);
        result1 = 31 * result1 + (getDedicationType() != null ? getDedicationType().hashCode() : 0);
        result1 = 31 * result1 + (getDedication() != null ? getDedication().hashCode() : 0);
        result1 = 31 * result1 + (getNotify() != null ? getNotify().hashCode() : 0);
        result1 = 31 * result1 + (getType() != null ? getType().hashCode() : 0);
        result1 = 31 * result1 + (getDate() != null ? getDate().hashCode() : 0);
        result1 = 31 * result1 + (getAmount() != null ? getAmount().hashCode() : 0);
        result1 = 31 * result1 + (getDeductibleAmount() != null ? getDeductibleAmount().hashCode() : 0);
        result1 = 31 * result1 + (getDesignation() != null ? getDesignation().hashCode() : 0);
        result1 = 31 * result1 + (getFeesPaid() != null ? getFeesPaid().hashCode() : 0);
        result1 = 31 * result1 + (getGatewayTransactionId() != null ? getGatewayTransactionId().hashCode() : 0);
        result1 = 31 * result1 + (getGatewayAuthorizationCode() != null ? getGatewayAuthorizationCode().hashCode() : 0);
        result1 = 31 * result1 + (getSupporter() != null ? getSupporter().hashCode() : 0);
        result1 = 31 * result1 + (getActivityFormName() != null ? getActivityFormName().hashCode() : 0);
        result1 = 31 * result1 + (getResult() != null ? getResult().hashCode() : 0);
        result1 = 31 * result1 + (getCustomFieldValues() != null ? getCustomFieldValues().hashCode() : 0);
        result1 = 31 * result1 + (getActivityFormId() != null ? getActivityFormId().hashCode() : 0);
        result1 = 31 * result1 + (getAdditionalProperties() != null ? getAdditionalProperties().hashCode() : 0);
        return result1;
    }
}