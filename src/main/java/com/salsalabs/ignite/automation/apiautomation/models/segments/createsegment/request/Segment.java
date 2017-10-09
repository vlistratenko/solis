package com.salsalabs.ignite.automation.apiautomation.models.segments.createsegment.request;

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
        "segmentId",
        "name",
        "description",
        "externalSystemId"
})
public class Segment {

    @JsonProperty("segmentId")
    private String segmentId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("externalSystemId")
    private String externalSystemId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("segmentId")
    public String getSegmentId() {
        return segmentId;
    }

    @JsonProperty("segmentId")
    public void setSegmentId(String segmentId) {
        this.segmentId = segmentId;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("externalSystemId")
    public String getExternalSystemId() {
        return externalSystemId;
    }

    @JsonProperty("externalSystemId")
    public void setExternalSystemId(String externalSystemId) {
        this.externalSystemId = externalSystemId;
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
        return new ToStringBuilder(this).append("segmentId", segmentId).append("name", name).append("description", description).append("externalSystemId", externalSystemId).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(segmentId).append(additionalProperties).append(externalSystemId).append(description).append(name).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Segment) == false) {
            return false;
        }
        Segment rhs = ((Segment) other);
        return new EqualsBuilder().append(segmentId, rhs.segmentId).append(additionalProperties, rhs.additionalProperties).append(externalSystemId, rhs.externalSystemId).append(description, rhs.description).append(name, rhs.name).isEquals();
    }

}