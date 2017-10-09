package com.salsalabs.ignite.automation.apiautomation.models.supporter.requests.bydate;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.salsalabs.ignite.automation.apiautomation.models.supporter.requests.CommonRequest;
import com.salsalabs.ignite.automation.apiautomation.models.supporter.requests.bydate.Payload;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "payload"
})
public class SupporterByDateRequest extends CommonRequest {

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SupporterByDateRequest)) return false;

        SupporterByDateRequest that = (SupporterByDateRequest) o;

        if (getPayload() != null ? !getPayload().equals(that.getPayload()) : that.getPayload() != null) return false;
        return getAdditionalProperties() != null ? getAdditionalProperties().equals(that.getAdditionalProperties()) : that.getAdditionalProperties() == null;

    }

    @Override
    public int hashCode() {
        int result = getPayload() != null ? getPayload().hashCode() : 0;
        result = 31 * result + (getAdditionalProperties() != null ? getAdditionalProperties().hashCode() : 0);
        return result;
    }
}
