package com.salsalabs.ignite.automation.apiautomation.models.devApiModels.submissions.signupform.Response;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "value",
        "type",
        "customFieldType"
})
public class CustomField {

    @JsonProperty("name")
    public String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomField that = (CustomField) o;

        if (!name.equals(that.name)) return false;
        if (!value.equals(that.value)) return false;
        if (!type.equals(that.type)) return false;
        if (!customFieldType.equals(that.customFieldType)) return false;
        return additionalProperties.equals(that.additionalProperties);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + value.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + customFieldType.hashCode();
        result = 31 * result + additionalProperties.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CustomField{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", type='" + type + '\'' +
                ", customFieldType='" + customFieldType + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }

    @JsonProperty("value")
    public String value;
    @JsonProperty("type")
    public String type;
    @JsonProperty("customFieldType")
    public String customFieldType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}