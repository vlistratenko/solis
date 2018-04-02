package com.salsalabs.ignite.automation.apiautomation.models.donations.response;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "donations",
        "count"
})
public class Payload {

    @JsonProperty("donations")
    private List<Donation> donations = null;
    @JsonProperty("count")
    private Integer count;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("donations")
    public List<Donation> getDonations() {
        return donations;
    }

    @JsonProperty("donations")
    public void setDonations(List<Donation> donations) {
        this.donations = donations;
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
        return new ToStringBuilder(this).append("donations", donations).append("count", count).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(count).append(additionalProperties).append(donations).toHashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payload)) return false;

        Payload payload = (Payload) o;

        if (getDonations() != null ? !getDonations().equals(payload.getDonations()) : payload.getDonations() != null)
            return false;
        if (getCount() != null ? !getCount().equals(payload.getCount()) : payload.getCount() != null) return false;
        return getAdditionalProperties() != null ? getAdditionalProperties().equals(payload.getAdditionalProperties()) : payload.getAdditionalProperties() == null;

    }
}