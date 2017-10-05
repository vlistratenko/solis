package com.salsalabs.ignite.automation.apiautomation.models.supporter.responses;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "supporters",
        "count"
})
public class Payload {

    @JsonProperty("supporters")
    private List<Supporter> supporters = null;
    @JsonProperty("count")
    private Integer count;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(supporters).append(count).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payload payload = (Payload) o;

        if (supporters != null ? !supporters.equals(payload.supporters) : payload.supporters != null) return false;
        if (count != null ? !count.equals(payload.count) : payload.count != null) return false;
        return additionalProperties != null ? additionalProperties.equals(payload.additionalProperties) : payload.additionalProperties == null;

    }
}
