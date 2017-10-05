package com.salsalabs.ignite.automation.apiautomation.models.getactivitybytype.response.event;

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
        "transactionId",
        "reasonId",
        "type",
        "reason",
        "date",
        "amount",
        "deductibleAmount",
        "feesPaid",
        "gatewayTransactionId",
        "gatewayAuthorizationCode"
})
public class Transaction {

    @JsonProperty("transactionId")
    private String transactionId;
    @JsonProperty("reasonId")
    private String reasonId;
    @JsonProperty("type")
    private String type;
    @JsonProperty("reason")
    private String reason;
    @JsonProperty("date")
    private String date;
    @JsonProperty("amount")
    private Integer amount;
    @JsonProperty("deductibleAmount")
    private Integer deductibleAmount;
    @JsonProperty("feesPaid")
    private Integer feesPaid;
    @JsonProperty("gatewayTransactionId")
    private String gatewayTransactionId;
    @JsonProperty("gatewayAuthorizationCode")
    private String gatewayAuthorizationCode;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("transactionId")
    public String getTransactionId() {
        return transactionId;
    }

    @JsonProperty("transactionId")
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @JsonProperty("reasonId")
    public String getReasonId() {
        return reasonId;
    }

    @JsonProperty("reasonId")
    public void setReasonId(String reasonId) {
        this.reasonId = reasonId;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("reason")
    public String getReason() {
        return reason;
    }

    @JsonProperty("reason")
    public void setReason(String reason) {
        this.reason = reason;
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
        if (!(o instanceof Transaction)) return false;

        Transaction that = (Transaction) o;

        if (getTransactionId() != null ? !getTransactionId().equals(that.getTransactionId()) : that.getTransactionId() != null)
            return false;
        if (getReasonId() != null ? !getReasonId().equals(that.getReasonId()) : that.getReasonId() != null)
            return false;
        if (getType() != null ? !getType().equals(that.getType()) : that.getType() != null) return false;
        if (getReason() != null ? !getReason().equals(that.getReason()) : that.getReason() != null) return false;
        if (getDate() != null ? !getDate().equals(that.getDate()) : that.getDate() != null) return false;
        if (getAmount() != null ? !getAmount().equals(that.getAmount()) : that.getAmount() != null) return false;
        if (getDeductibleAmount() != null ? !getDeductibleAmount().equals(that.getDeductibleAmount()) : that.getDeductibleAmount() != null)
            return false;
        if (getFeesPaid() != null ? !getFeesPaid().equals(that.getFeesPaid()) : that.getFeesPaid() != null)
            return false;
        if (getGatewayTransactionId() != null ? !getGatewayTransactionId().equals(that.getGatewayTransactionId()) : that.getGatewayTransactionId() != null)
            return false;
        if (getGatewayAuthorizationCode() != null ? !getGatewayAuthorizationCode().equals(that.getGatewayAuthorizationCode()) : that.getGatewayAuthorizationCode() != null)
            return false;
        return getAdditionalProperties() != null ? getAdditionalProperties().equals(that.getAdditionalProperties()) : that.getAdditionalProperties() == null;

    }

    @Override
    public int hashCode() {
        int result = getTransactionId() != null ? getTransactionId().hashCode() : 0;
        result = 31 * result + (getReasonId() != null ? getReasonId().hashCode() : 0);
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        result = 31 * result + (getReason() != null ? getReason().hashCode() : 0);
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        result = 31 * result + (getAmount() != null ? getAmount().hashCode() : 0);
        result = 31 * result + (getDeductibleAmount() != null ? getDeductibleAmount().hashCode() : 0);
        result = 31 * result + (getFeesPaid() != null ? getFeesPaid().hashCode() : 0);
        result = 31 * result + (getGatewayTransactionId() != null ? getGatewayTransactionId().hashCode() : 0);
        result = 31 * result + (getGatewayAuthorizationCode() != null ? getGatewayAuthorizationCode().hashCode() : 0);
        result = 31 * result + (getAdditionalProperties() != null ? getAdditionalProperties().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", reasonId='" + reasonId + '\'' +
                ", type='" + type + '\'' +
                ", reason='" + reason + '\'' +
                ", date='" + date + '\'' +
                ", amount=" + amount +
                ", deductibleAmount=" + deductibleAmount +
                ", feesPaid=" + feesPaid +
                ", gatewayTransactionId='" + gatewayTransactionId + '\'' +
                ", gatewayAuthorizationCode='" + gatewayAuthorizationCode + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}