package com.salsalabs.ignite.automation.apiautomation.models.supporter.requests;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.salsalabs.ignite.automation.apiautomation.models.supporter.requests.CommonRequest;
import com.salsalabs.ignite.automation.apiautomation.models.supporter.responses.Payload;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by o.lauta on 9/12/17.
 */
public class CreateSupporterRequest extends CommonRequest {
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
}
