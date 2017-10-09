package com.salsalabs.ignite.automation.apiautomation.models.segments.deletesegment.response;

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
        "totalMembers",
        "result"
})
public class Segment {

    @JsonProperty("segmentId")
    private String segmentId;
    @JsonProperty("totalMembers")
    private Integer totalMembers;
    @JsonProperty("result")
    private String result;
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
        return "Segment{" +
                "segmentId='" + segmentId + '\'' +
                ", totalMembers=" + totalMembers +
                ", result='" + result + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(result).append(segmentId).append(additionalProperties).append(totalMembers).toHashCode();
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
        return new EqualsBuilder().append(result, rhs.result).append(segmentId, rhs.segmentId).append(additionalProperties, rhs.additionalProperties).append(totalMembers, rhs.totalMembers).isEquals();
    }

}