package com.salsalabs.ignite.automation.apiautomation.models.segments.queryallsegment.request;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "offset",
        "count"
})
public class Payload {

    @JsonProperty("offset")
    private Integer offset;
    @JsonProperty("count")
    private Integer count;
    @JsonProperty("includeMemberCounts")
    private String includeMemberCounts;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("offset")
    public Integer getOffset() {
        return offset;
    }

    @JsonProperty("offset")
    public void setOffset(Integer offset) {
        this.offset = offset;
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
        return new ToStringBuilder(this).append("offset", offset).append("count", count).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(count).append(additionalProperties).append(offset).toHashCode();
    }

    public String getIncludeMemberCounts() {
        return includeMemberCounts;
    }

    public void setIncludeMemberCounts(String includeMemberCounts) {
        this.includeMemberCounts = includeMemberCounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payload)) return false;

        Payload payload = (Payload) o;

        if (getOffset() != null ? !getOffset().equals(payload.getOffset()) : payload.getOffset() != null) return false;
        if (getCount() != null ? !getCount().equals(payload.getCount()) : payload.getCount() != null) return false;
        if (getIncludeMemberCounts() != null ? !getIncludeMemberCounts().equals(payload.getIncludeMemberCounts()) : payload.getIncludeMemberCounts() != null)
            return false;
        return getAdditionalProperties() != null ? getAdditionalProperties().equals(payload.getAdditionalProperties()) : payload.getAdditionalProperties() == null;

    }
}