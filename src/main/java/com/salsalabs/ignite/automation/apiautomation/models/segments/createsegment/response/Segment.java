package com.salsalabs.ignite.automation.apiautomation.models.segments.createsegment.response;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "segmentId",
        "name",
        "description",
        "totalMembers",
        "result",
        "externalSystemId"
})
public class Segment {

    @JsonProperty("segmentId")
    private String segmentId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("totalMembers")
    private Integer totalMembers;
    @JsonProperty("result")
    private String result;
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

   @JsonProperty("totalMembers")
    public Integer getTotalMembers() {
        return totalMembers;
    }

    @JsonProperty("totalMembers")
    public void setTotalMembers(Integer totalMembers) {
        this.totalMembers = totalMembers;
    }

    @JsonProperty("result")
    public String getResult() {
        return result;
    }

    @JsonProperty("result")
    public void setResult(String result) {
        this.result = result;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Segment)) return false;

        Segment segment = (Segment) o;

        if (getSegmentId() != null ? !getSegmentId().equals(segment.getSegmentId()) : segment.getSegmentId() != null)
            return false;
        if (getName() != null ? !getName().equals(segment.getName()) : segment.getName() != null) return false;
        if (getDescription() != null ? !getDescription().equals(segment.getDescription()) : segment.getDescription() != null)
            return false;
        if (getTotalMembers() != null ? !getTotalMembers().equals(segment.getTotalMembers()) : segment.getTotalMembers() != null)
            //return false;
        if (getResult() != null ? !getResult().equals(segment.getResult()) : segment.getResult() != null) return false;
        if (getExternalSystemId() != null ? !getExternalSystemId().equals(segment.getExternalSystemId()) : segment.getExternalSystemId() != null)
            return false;
        return getAdditionalProperties() != null ? getAdditionalProperties().equals(segment.getAdditionalProperties()) : segment.getAdditionalProperties() == null;

    }

    @Override
    public int hashCode() {
        int result1 = getSegmentId() != null ? getSegmentId().hashCode() : 0;
        result1 = 31 * result1 + (getName() != null ? getName().hashCode() : 0);
        result1 = 31 * result1 + (getDescription() != null ? getDescription().hashCode() : 0);
        result1 = 31 * result1 + (getTotalMembers() != null ? getTotalMembers().hashCode() : 0);
        result1 = 31 * result1 + (getResult() != null ? getResult().hashCode() : 0);
        result1 = 31 * result1 + (getExternalSystemId() != null ? getExternalSystemId().hashCode() : 0);
        result1 = 31 * result1 + (getAdditionalProperties() != null ? getAdditionalProperties().hashCode() : 0);
        return result1;
    }

    @Override
    public String toString() {
        return "Segment{" +
                "segmentId='" + segmentId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", totalMembers=" + totalMembers +
                ", result='" + result + '\'' +
                ", externalSystemId='" + externalSystemId + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
