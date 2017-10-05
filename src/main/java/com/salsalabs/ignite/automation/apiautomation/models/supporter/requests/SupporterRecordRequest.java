package com.salsalabs.ignite.automation.apiautomation.models.supporter.requests;

import com.fasterxml.jackson.annotation.*;
import com.salsalabs.ignite.automation.apiautomation.models.supporter.responses.Payload;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "timestamp",
        "header",
        "payload"
})
public class SupporterRecordRequest extends CommonRequest {

    @JsonProperty("payload")
    private com.salsalabs.ignite.automation.apiautomation.models.supporter.responses.Payload payload;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("payload")
    public com.salsalabs.ignite.automation.apiautomation.models.supporter.responses.Payload getPayload() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SupporterRecordRequest that = (SupporterRecordRequest) o;

        if (payload != null ? !payload.equals(that.payload) : that.payload != null) return false;
        return additionalProperties != null ? additionalProperties.equals(that.additionalProperties) : that.additionalProperties == null;

    }

    @Override
    public int hashCode() {
        int result = payload != null ? payload.hashCode() : 0;
        result = 31 * result + (additionalProperties != null ? additionalProperties.hashCode() : 0);
        return result;
    }
}
