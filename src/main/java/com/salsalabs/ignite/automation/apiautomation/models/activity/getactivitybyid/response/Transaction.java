package com.salsalabs.ignite.automation.apiautomation.models.activity.getactivitybyid.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Transaction {

    @SerializedName("transactionId")
    @Expose
    private String transactionId;
    @SerializedName("relatedTransactionId")
    @Expose
    private String relatedTransactionId;
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
    @SerializedName("deductibleAmount")
    @Expose
    private Integer deductibleAmount;
    @SerializedName("feesPaid")
    @Expose
    private Integer feesPaid;
    @SerializedName("gatewayTransactionId")
    @Expose
    private String gatewayTransactionId;
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

    public String getGatewayTransactionId() {
        return gatewayTransactionId;
    }

    public String getRelatedTransactionId() {
        return relatedTransactionId;
    }

    public void setRelatedTransactionId(String relatedTransactionId) {
        this.relatedTransactionId = relatedTransactionId;
    }

    public void setGatewayTransactionId(String gatewayTransactionId) {
        this.gatewayTransactionId = gatewayTransactionId;
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

        /*if (getTransactionId() != null ? !getTransactionId().equals(that.getTransactionId()) : that.getTransactionId() != null)
            return false;
        if (getRelatedTransactionId() != null ? !getRelatedTransactionId().equals(that.getRelatedTransactionId()) : that.getRelatedTransactionId() != null)
            return false;*/
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
        return getGatewayAuthorizationCode() != null ? getGatewayAuthorizationCode().equals(that.getGatewayAuthorizationCode()) : that.getGatewayAuthorizationCode() == null;

    }

    @Override
    public int hashCode() {
        int result = 0;
        //int result = getTransactionId() != null ? getTransactionId().hashCode() : 0;
        //result = 31 * result + (getRelatedTransactionId() != null ? getRelatedTransactionId().hashCode() : 0);
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        result = 31 * result + (getReason() != null ? getReason().hashCode() : 0);
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        result = 31 * result + (getAmount() != null ? getAmount().hashCode() : 0);
        result = 31 * result + (getDeductibleAmount() != null ? getDeductibleAmount().hashCode() : 0);
        result = 31 * result + (getFeesPaid() != null ? getFeesPaid().hashCode() : 0);
        result = 31 * result + (getGatewayTransactionId() != null ? getGatewayTransactionId().hashCode() : 0);
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
                ", deductibleAmount=" + deductibleAmount +
                ", feesPaid=" + feesPaid +
                ", gatewayTransactionId='" + gatewayTransactionId + '\'' +
                ", gatewayAuthorizationCode='" + gatewayAuthorizationCode + '\'' +
                '}';
    }
}