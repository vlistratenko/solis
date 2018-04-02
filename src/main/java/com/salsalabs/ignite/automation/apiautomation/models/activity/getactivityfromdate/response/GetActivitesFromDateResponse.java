
package com.salsalabs.ignite.automation.apiautomation.models.activity.getactivityfromdate.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.salsalabs.ignite.automation.apiautomation.models.ExpectedResult;

public class GetActivitesFromDateResponse extends ExpectedResult {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("header")
    @Expose
    private Header header;
    @SerializedName("payload")
    @Expose
    private Payload payload;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetActivitesFromDateResponse)) return false;

        GetActivitesFromDateResponse that = (GetActivitesFromDateResponse) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getTimestamp() != null ? !getTimestamp().equals(that.getTimestamp()) : that.getTimestamp() != null)
            return false;
        if (getHeader() != null ? !getHeader().equals(that.getHeader()) : that.getHeader() != null) return false;
        return getPayload() != null ? getPayload().equals(that.getPayload()) : that.getPayload() == null;

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getTimestamp() != null ? getTimestamp().hashCode() : 0);
        result = 31 * result + (getHeader() != null ? getHeader().hashCode() : 0);
        result = 31 * result + (getPayload() != null ? getPayload().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GetActivitesFromDateResponse{" +
                "id='" + id + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", header=" + header +
                ", payload=" + payload +
                '}';
    }
}
