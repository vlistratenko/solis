package com.salsalabs.ignite.automation.apiautomation.models.donations.request;

import com.fasterxml.jackson.annotation.*;
import com.salsalabs.ignite.automation.apiautomation.models.supporter.requests.CommonRequest;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "payload"
})
public class ImportDonationRequest extends CommonRequest {

    @JsonProperty("payload")
    private Payload payload;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("payload")
    public Payload getPayload() {
        return payload;
    }

    @JsonProperty("payload")
    public void setPayload(Payload payload) {
        this.payload = payload;
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
        return new ToStringBuilder(this).append("payload", payload).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(additionalProperties).append(payload).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ImportDonationRequest) == false) {
            return false;
        }
        ImportDonationRequest rhs = ((ImportDonationRequest) other);
        return new EqualsBuilder().append(additionalProperties, rhs.additionalProperties).append(payload, rhs.payload).isEquals();
    }

}