package com.salsalabs.ignite.automation.apiautomation.models.activity.getactivityfromdate.response.old;

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
        "activities",
        "count"
})
public class Payload {

    @JsonProperty("total")
    private Integer total;
    @JsonProperty("offset")
    private Integer offset;
    @JsonProperty("activities")
    private List<Activity> activities = null;
    @JsonProperty("count")
    private Integer count;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("total")
    public Integer getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(Integer total) {
        this.total = total;
    }

    @JsonProperty("offset")
    public Integer getOffset() {
        return offset;
    }

    @JsonProperty("offset")
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    @JsonProperty("activities")
    public List<Activity> getActivities() {
        return activities;
    }

    @JsonProperty("activities")
    public void setActivities(List<Activity> activities) {
        this.activities = activities;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payload)) return false;

        Payload payload = (Payload) o;

        if (getTotal() != null ? !getTotal().equals(payload.getTotal()) : payload.getTotal() != null) return false;
        if (getOffset() != null ? !getOffset().equals(payload.getOffset()) : payload.getOffset() != null) return false;
        if (getActivities() != null ? !getActivities().equals(payload.getActivities()) : payload.getActivities() != null)
            return false;
        if (getCount() != null ? !getCount().equals(payload.getCount()) : payload.getCount() != null) return false;
        return getAdditionalProperties() != null ? getAdditionalProperties().equals(payload.getAdditionalProperties()) : payload.getAdditionalProperties() == null;

    }

    @Override
    public int hashCode() {
        int result = getTotal() != null ? getTotal().hashCode() : 0;
        result = 31 * result + (getOffset() != null ? getOffset().hashCode() : 0);
        result = 31 * result + (getActivities() != null ? getActivities().hashCode() : 0);
        result = 31 * result + (getCount() != null ? getCount().hashCode() : 0);
        result = 31 * result + (getAdditionalProperties() != null ? getAdditionalProperties().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Payload{" +
                "total=" + total +
                ", offset=" + offset +
                ", activities=" + activities +
                ", count=" + count +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}