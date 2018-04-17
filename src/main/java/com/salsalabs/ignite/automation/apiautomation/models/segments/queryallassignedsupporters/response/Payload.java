package com.salsalabs.ignite.automation.apiautomation.models.segments.queryallassignedsupporters.response;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "total",
        "supporters",
        "count"
})
public class Payload {

    @JsonProperty("total")
    private Integer total;
    @JsonProperty("supporters")
    private List<Supporter> supporters = null;
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

    @JsonProperty("supporters")
    public List<Supporter> getSupporters() {
        return supporters;
    }

    @JsonProperty("supporters")
    public void setSupporters(List<Supporter> supporters) {
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

}