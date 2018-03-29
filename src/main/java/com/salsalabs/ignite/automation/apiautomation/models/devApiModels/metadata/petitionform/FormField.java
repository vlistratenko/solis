package com.salsalabs.ignite.automation.apiautomation.models.devApiModels.metadata.petitionform;

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
        "id",
        "name",
        "inboundParameterName"
})
public class FormField {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("inboundParameterName")
    private String inboundParameterName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("inboundParameterName")
    public String getInboundParameterName() {
        return inboundParameterName;
    }

    @JsonProperty("inboundParameterName")
    public void setInboundParameterName(String inboundParameterName) {
        this.inboundParameterName = inboundParameterName;
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
        return "FormField{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", inboundParameterName='" + inboundParameterName + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FormField formField = (FormField) o;

        if (!id.equals(formField.id)) return false;
        if (!name.equals(formField.name)) return false;
        if (!inboundParameterName.equals(formField.inboundParameterName)) return false;
        return additionalProperties.equals(formField.additionalProperties);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + inboundParameterName.hashCode();
        result = 31 * result + additionalProperties.hashCode();
        return result;
    }
}