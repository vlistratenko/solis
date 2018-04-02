
package com.salsalabs.ignite.automation.apiautomation.models.activity.getactivityfromdate.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Transaction {

    @SerializedName("transactionId")
    @Expose
    private String transactionId;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("reason")
    @Expose
    private String reason;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("gatewayTransactionId")
    @Expose
    private String gatewayTransactionId;
    @SerializedName("reasonId")
    @Expose
    private String reasonId;
    @SerializedName("deductibleAmount")
    @Expose
    private Integer deductibleAmount;
    @SerializedName("feesPaid")
    @Expose
    private Integer feesPaid;
    @SerializedName("gatewayAuthorizationCode")
    @Expose
    private String gatewayAuthorizationCode;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getGatewayTransactionId() {
        return gatewayTransactionId;
    }

    public void setGatewayTransactionId(String gatewayTransactionId) {
        this.gatewayTransactionId = gatewayTransactionId;
    }

    public String getReasonId() {
        return reasonId;
    }

    public void setReasonId(String reasonId) {
        this.reasonId = reasonId;
    }

    public Integer getDeductibleAmount() {
        return deductibleAmount;
    }

    public void setDeductibleAmount(Integer deductibleAmount) {
        this.deductibleAmount = deductibleAmount;
    }

    public Integer getFeesPaid() {
        return feesPaid;
    }

    public void setFeesPaid(Integer feesPaid) {
        this.feesPaid = feesPaid;
    }

    public String getGatewayAuthorizationCode() {
        return gatewayAuthorizationCode;
    }

    public void setGatewayAuthorizationCode(String gatewayAuthorizationCode) {
        this.gatewayAuthorizationCode = gatewayAuthorizationCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;

        Transaction that = (Transaction) o;

        if (getTransactionId() != null ? !getTransactionId().equals(that.getTransactionId()) : that.getTransactionId() != null)
            return false;
        if (getType() != null ? !getType().equals(that.getType()) : that.getType() != null) return false;
        if (getReason() != null ? !getReason().equals(that.getReason()) : that.getReason() != null) return false;
        if (getDate() != null ? !getDate().equals(that.getDate()) : that.getDate() != null) return false;
        if (getAmount() != null ? !getAmount().equals(that.getAmount()) : that.getAmount() != null) return false;
        if (getGatewayTransactionId() != null ? !getGatewayTransactionId().equals(that.getGatewayTransactionId()) : that.getGatewayTransactionId() != null)
            return false;
        if (getReasonId() != null ? !getReasonId().equals(that.getReasonId()) : that.getReasonId() != null)
            return false;
        if (getDeductibleAmount() != null ? !getDeductibleAmount().equals(that.getDeductibleAmount()) : that.getDeductibleAmount() != null)
            return false;
        if (getFeesPaid() != null ? !getFeesPaid().equals(that.getFeesPaid()) : that.getFeesPaid() != null)
            return false;
        return getGatewayAuthorizationCode() != null ? getGatewayAuthorizationCode().equals(that.getGatewayAuthorizationCode()) : that.getGatewayAuthorizationCode() == null;

    }

    @Override
    public int hashCode() {
        int result = getTransactionId() != null ? getTransactionId().hashCode() : 0;
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        result = 31 * result + (getReason() != null ? getReason().hashCode() : 0);
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        result = 31 * result + (getAmount() != null ? getAmount().hashCode() : 0);
        result = 31 * result + (getGatewayTransactionId() != null ? getGatewayTransactionId().hashCode() : 0);
        result = 31 * result + (getReasonId() != null ? getReasonId().hashCode() : 0);
        result = 31 * result + (getDeductibleAmount() != null ? getDeductibleAmount().hashCode() : 0);
        result = 31 * result + (getFeesPaid() != null ? getFeesPaid().hashCode() : 0);
        result = 31 * result + (getGatewayAuthorizationCode() != null ? getGatewayAuthorizationCode().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", type='" + type + '\'' +
                ", reason='" + reason + '\'' +
                ", date='" + date + '\'' +
                ", amount=" + amount +
                ", gatewayTransactionId='" + gatewayTransactionId + '\'' +
                ", reasonId='" + reasonId + '\'' +
                ", deductibleAmount=" + deductibleAmount +
                ", feesPaid=" + feesPaid +
                ", gatewayAuthorizationCode='" + gatewayAuthorizationCode + '\'' +
                '}';
    }
}
