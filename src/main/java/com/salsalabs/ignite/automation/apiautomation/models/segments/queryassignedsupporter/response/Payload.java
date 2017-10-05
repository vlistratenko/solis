package com.salsalabs.ignite.automation.apiautomation.models.segments.queryassignedsupporter.response;

import java.util.HashMap;
import java.util.List;
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
        "supporters",
        "count"
})
public class Payload {

    @JsonProperty("supporters")
    private List<com.salsalabs.ignite.automation.apiautomation.models.segments.queryassignedsupporter.response.Supporter> supporters = null;
    @JsonProperty("count")
    private Integer count;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("supporters")
    public List<com.salsalabs.ignite.automation.apiautomation.models.segments.queryassignedsupporter.response.Supporter> getSupporters() {
        return supporters;
    }

    @JsonProperty("supporters")
    public void setSupporters(List<com.salsalabs.ignite.automation.apiautomation.models.segments.queryassignedsupporter.response.Supporter> supporters) {
        this.supporters = supporters;
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
        return new ToStringBuilder(this).append("supporters", supporters).append("count", count).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(count).append(additionalProperties).append(supporters).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Payload) == false) {
            return false;
        }
        Payload rhs = ((Payload) other);
        return new EqualsBuilder().append(count, rhs.count).append(additionalProperties, rhs.additionalProperties).append(supporters, rhs.supporters).isEquals();
    }

}