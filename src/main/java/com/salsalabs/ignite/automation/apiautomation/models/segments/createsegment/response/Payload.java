package com.salsalabs.ignite.automation.apiautomation.models.segments.createsegment.response;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "segments",
        "count"
})
public class Payload {

    @JsonProperty("segments")
    private List<Segment> segments = null;
    @JsonProperty("count")
    private Integer count;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("segments")
    public List<Segment> getSegments() {
        return segments;
    }

    @JsonProperty("segments")
    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }

    @JsonProperty("count")
    public Integer getCount() {
        return count;
    }

    @JsonProperty("count")
    public void setCount(Integer count) {
        this.count = count;
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
        return new ToStringBuilder(this).append("segments", segments).append("count", count).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payload)) return false;

        Payload payload = (Payload) o;

        if (getSegments() != null ? !getSegments().equals(payload.getSegments()) : payload.getSegments() != null)
            return false;
        if (getCount() != null ? !getCount().equals(payload.getCount()) : payload.getCount() != null) return false;
        return getAdditionalProperties() != null ? getAdditionalProperties().equals(payload.getAdditionalProperties()) : payload.getAdditionalProperties() == null;

    }

    @Override
    public int hashCode() {
        int result = getSegments() != null ? getSegments().hashCode() : 0;
        result = 31 * result + (getCount() != null ? getCount().hashCode() : 0);
        result = 31 * result + (getAdditionalProperties() != null ? getAdditionalProperties().hashCode() : 0);
        return result;
    }
}
