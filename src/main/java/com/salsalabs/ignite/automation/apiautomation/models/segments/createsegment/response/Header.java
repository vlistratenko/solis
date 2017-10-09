package com.salsalabs.ignite.automation.apiautomation.models.segments.createsegment.response;


import java.util.HashMap;
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
    public String toString() {
        return new ToStringBuilder(this).append("processingTime", processingTime).append("serverId", serverId).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(additionalProperties).append(processingTime).append(serverId).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Header) == false) {
            return false;
        }
        Header rhs = ((Header) other);
        return new EqualsBuilder().append(additionalProperties, rhs.additionalProperties).append(processingTime, rhs.processingTime).append(serverId, rhs.serverId).isEquals();
    }

}