package com.salsalabs.ignite.automation.apiautomation.models.devApiModels.metadata.p2peventform;

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
public class P2PEventFormMetaData extends ExpectedResult {

    @JsonProperty("id")
    private String id;
    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("header")
    private Header header;
    @JsonProperty("payload")
    private Payload payload;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("timestamp")
    public String getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty("header")
    public Header getHeader() {
        return header;
    }

    @JsonProperty("header")
    public void setHeader(Header header) {
        this.header = header;
    }

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
        if (o == null || getClass() != o.getClass()) return false;

        P2PEventFormMetaData that = (P2PEventFormMetaData) o;

        if (!id.equals(that.id)) return false;
        if (!payload.equals(that.payload)) return false;
        return additionalProperties.equals(that.additionalProperties);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + payload.hashCode();
        result = 31 * result + additionalProperties.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "P2PEventFormMetaData{" +
                "id='" + id + '\'' +
                ", payload=" + payload +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}