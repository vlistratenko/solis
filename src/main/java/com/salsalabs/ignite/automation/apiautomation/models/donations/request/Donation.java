package com.salsalabs.ignite.automation.apiautomation.models.donations.request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "accountType",
        "accountNumber",
        "accountExpiration",
        "accountProvider",
        "dedicationType",
        "dedication",
        "type",
        "date",
        "amount",
        "deductibleAmount",
        "feesPaid",
        "gatewayTransactionId",
        "gatewayAuthorizationCode",
        "activityFormName",
        "notify",
        "designation",
        "customFieldValues",
        "supporter",
        "activityFormId"
})
public class Donation {

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
    @JsonProperty("type")
    private String type;
    @JsonProperty("date")
    private String date;
    @JsonProperty("amount")
    private Double amount;
    @JsonProperty("deductibleAmount")
    private Double deductibleAmount;
    @JsonProperty("feesPaid")
    private Double feesPaid;
    @JsonProperty("gatewayTransactionId")
    private String gatewayTransactionId;
    @JsonProperty("gatewayAuthorizationCode")
    private String gatewayAuthorizationCode;
    @JsonProperty("activityFormName")
    private String activityFormName;
    @JsonProperty("notify")
    private String notify;
    @JsonProperty("designation")
    private String designation;
    @JsonProperty("customFieldValues")
    private List<CustomFieldValue> customFieldValues = null;
    @JsonProperty("supporter")
    private Supporter supporter;
    @JsonProperty("activityFormId")
    private String activityFormId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
    public Double getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @JsonProperty("deductibleAmount")
    public Double getDeductibleAmount() {
        return deductibleAmount;
    }

    @JsonProperty("deductibleAmount")
    public void setDeductibleAmount(Double deductibleAmount) {
        this.deductibleAmount = deductibleAmount;
    }

    @JsonProperty("feesPaid")
    public Double getFeesPaid() {
        return feesPaid;
    }

    @JsonProperty("feesPaid")
    public void setFeesPaid(Double feesPaid) {
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

    @JsonProperty("activityFormName")
    public String getActivityFormName() {
        return activityFormName;
    }

    @JsonProperty("activityFormName")
    public void setActivityFormName(String activityFormName) {
        this.activityFormName = activityFormName;
    }

    @JsonProperty("notify")
    public String getNotify() {
        return notify;
    }

    @JsonProperty("notify")
    public void setNotify(String notify) {
        this.notify = notify;
    }

    @JsonProperty("designation")
    public String getDesignation() {
        return designation;
    }

    @JsonProperty("designation")
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @JsonProperty("customFieldValues")
    public List<CustomFieldValue> getCustomFieldValues() {
        return customFieldValues;
    }

    @JsonProperty("customFieldValues")
    public void setCustomFieldValues(List<CustomFieldValue> customFieldValues) {
        this.customFieldValues = customFieldValues;
    }

    @JsonProperty("supporter")
    public Supporter getSupporter() {
        return supporter;
    }

    @JsonProperty("supporter")
    public void setSupporter(Supporter supporter) {
        this.supporter = supporter;
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
        return new ToStringBuilder(this).append("accountType", accountType).append("accountNumber", accountNumber).append("accountExpiration", accountExpiration).append("accountProvider", accountProvider).append("dedicationType", dedicationType).append("dedication", dedication).append("type", type).append("date", date).append("amount", amount).append("deductibleAmount", deductibleAmount).append("feesPaid", feesPaid).append("gatewayTransactionId", gatewayTransactionId).append("gatewayAuthorizationCode", gatewayAuthorizationCode).append("activityFormName", activityFormName).append("notify", notify).append("designation", designation).append("customFieldValues", customFieldValues).append("supporter", supporter).append("activityFormId", activityFormId).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(activityFormName).append(gatewayAuthorizationCode).append(accountExpiration).append(supporter).append(feesPaid).append(gatewayTransactionId).append(designation).append(dedication).append(customFieldValues).append(date).append(type).append(deductibleAmount).append(dedicationType).append(amount).append(accountNumber).append(additionalProperties).append(notify).append(accountType).append(accountProvider).append(activityFormId).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Donation) == false) {
            return false;
        }
        Donation rhs = ((Donation) other);
        return new EqualsBuilder().append(activityFormName, rhs.activityFormName).append(gatewayAuthorizationCode, rhs.gatewayAuthorizationCode).append(accountExpiration, rhs.accountExpiration).append(supporter, rhs.supporter).append(feesPaid, rhs.feesPaid).append(gatewayTransactionId, rhs.gatewayTransactionId).append(designation, rhs.designation).append(dedication, rhs.dedication).append(customFieldValues, rhs.customFieldValues).append(date, rhs.date).append(type, rhs.type).append(deductibleAmount, rhs.deductibleAmount).append(dedicationType, rhs.dedicationType).append(amount, rhs.amount).append(accountNumber, rhs.accountNumber).append(additionalProperties, rhs.additionalProperties).append(notify, rhs.notify).append(accountType, rhs.accountType).append(accountProvider, rhs.accountProvider).append(activityFormId, rhs.activityFormId).isEquals();
    }

}