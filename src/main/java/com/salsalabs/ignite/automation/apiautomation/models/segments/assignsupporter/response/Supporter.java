package com.salsalabs.ignite.automation.apiautomation.models.segments.assignsupporter.response;

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
        "supporterId",
        "result"
})
public class Supporter {

    @JsonProperty("supporterId")
    private String supporterId;
    @JsonProperty("result")
    private String result;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("supporterId")
    public String getSupporterId() {
        return supporterId;
    }

    @JsonProperty("supporterId")
    public void setSupporterId(String supporterId) {
        this.supporterId = supporterId;
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
    public int hashCode() {
        return new HashCodeBuilder().append(result).append(additionalProperties).append(supporterId).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Supporter) == false) {
            return false;
        }
        Supporter rhs = ((Supporter) other);
        return new EqualsBuilder().append(result, rhs.result).append(additionalProperties, rhs.additionalProperties).append(supporterId, rhs.supporterId).isEquals();
    }

    @Override
    public String toString() {
        return "Supporter{" +
                "supporterId='" + supporterId + '\'' +
                ", result='" + result + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}