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
        "processingTime",
        "serverId"
})
public class Header {

    @JsonProperty("processingTime")
    private Integer processingTime;
    @JsonProperty("serverId")
    private String serverId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("processingTime")
    public Integer getProcessingTime() {
        return processingTime;
    }

    @JsonProperty("processingTime")
    public void setProcessingTime(Integer processingTime) {
        this.processingTime = processingTime;
    }

    @JsonProperty("serverId")
    public String getServerId() {
        return serverId;
    }

    @JsonProperty("serverId")
    public void setServerId(String serverId) {
        this.serverId = serverId;
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
        if (!(o instanceof Header)) return false;

        Header header = (Header) o;

        if (getProcessingTime() != null ? !getProcessingTime().equals(header.getProcessingTime()) : header.getProcessingTime() != null)
            return false;
        if (getServerId() != null ? !getServerId().equals(header.getServerId()) : header.getServerId() != null)
            return false;
        return getAdditionalProperties() != null ? getAdditionalProperties().equals(header.getAdditionalProperties()) : header.getAdditionalProperties() == null;

    }

    @Override
    public int hashCode() {
        int result = getProcessingTime() != null ? getProcessingTime().hashCode() : 0;
        result = 31 * result + (getServerId() != null ? getServerId().hashCode() : 0);
        result = 31 * result + (getAdditionalProperties() != null ? getAdditionalProperties().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Header{" +
                "processingTime=" + processingTime +
                ", serverId='" + serverId + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}