package com.salsalabs.ignite.automation.apiautomation.models.devApiModels.activity.allactivityformslist;

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
        "type",
        "id",
        "name",
        "description",
        "createDate",
        "publishDate",
        "modifiedDate",
        "status",
        "visibility"
})
public class Result {

    @JsonProperty("type")
    public String type;
    @JsonProperty("id")
    public String id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("description")
    public String description;
    @JsonProperty("createDate")
    public String createDate;
    @JsonProperty("publishDate")
    public String publishDate;
    @JsonProperty("modifiedDate")
    public String modifiedDate;
    @JsonProperty("status")
    public String status;
    @JsonProperty("visibility")
    public String visibility;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Result result = (Result) o;

        if (!type.equals(result.type)) return false;
        if (!id.equals(result.id)) return false;
        if (!name.equals(result.name)) return false;
        if (!description.equals(result.description)) return false;
        if (!createDate.equals(result.createDate)) return false;
        if (!publishDate.equals(result.publishDate)) return false;
        if (!modifiedDate.equals(result.modifiedDate)) return false;
        if (!status.equals(result.status)) return false;
        if (!visibility.equals(result.visibility)) return false;
        return additionalProperties.equals(result.additionalProperties);
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + createDate.hashCode();
        result = 31 * result + publishDate.hashCode();
        result = 31 * result + modifiedDate.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + visibility.hashCode();
        result = 31 * result + additionalProperties.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Result{" +
                "type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createDate='" + createDate + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", modifiedDate='" + modifiedDate + '\'' +
                ", status='" + status + '\'' +
                ", visibility='" + visibility + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}