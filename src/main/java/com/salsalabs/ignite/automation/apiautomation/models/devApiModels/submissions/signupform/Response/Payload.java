package com.salsalabs.ignite.automation.apiautomation.models.devApiModels.submissions.signupform.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "total",
        "offset",
        "results",
        "count"
})
public class Payload {

    @JsonProperty("total")
    public Integer total;
    @JsonProperty("offset")
    public Integer offset;
    @JsonProperty("results")
    public List<Result> results = null;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payload payload = (Payload) o;

        if (!total.equals(payload.total)) return false;
        if (!offset.equals(payload.offset)) return false;
        if (!results.equals(payload.results)) return false;
        if (!count.equals(payload.count)) return false;
        return additionalProperties.equals(payload.additionalProperties);
    }

    @Override
    public int hashCode() {
        int result = total.hashCode();
        result = 31 * result + offset.hashCode();
        result = 31 * result + results.hashCode();
        result = 31 * result + count.hashCode();
        result = 31 * result + additionalProperties.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Payload{" +
                "total=" + total +
                ", offset=" + offset +
                ", results=" + results +
                ", count=" + count +
                ", additionalProperties=" + additionalProperties +
                '}';

    }

    @JsonProperty("count")
    public Integer count;
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

}