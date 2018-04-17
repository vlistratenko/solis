package com.salsalabs.ignite.automation.apiautomation.models.devApiModels.activity.allactivityformslist;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.salsalabs.ignite.automation.apiautomation.models.ExpectedResult;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "timestamp",
        "header",
        "payload"
})
public class AllActivityFormsList  extends ExpectedResult {

    @JsonProperty("id")
    public String id;
    @JsonProperty("timestamp")
    public String timestamp;
    @JsonProperty("header")
    public Header header;
    @JsonProperty("payload")
    public Payload payload;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

        AllActivityFormsList that = (AllActivityFormsList) o;

        if (!payload.equals(that.payload)) return false;
        return additionalProperties.equals(that.additionalProperties);
    }

    @Override
    public int hashCode() {
        int result = payload.hashCode();
        result = 31 * result + additionalProperties.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "AllActivityFormsList{" +
                "payload=" + payload +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}