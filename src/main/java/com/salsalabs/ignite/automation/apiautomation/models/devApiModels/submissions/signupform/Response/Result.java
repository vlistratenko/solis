package com.salsalabs.ignite.automation.apiautomation.models.devApiModels.submissions.signupform.Response;

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
        "id",
        "supporterId",
        "activityDate",
        "formName",
        "formId",
        "customFields",
        "activityType",
        "modifiedDate"
})
public class Result {

    @JsonProperty("id")
    public String id;
    @JsonProperty("supporterId")
    public String supporterId;
    @JsonProperty("activityDate")
    public String activityDate;
    @JsonProperty("formName")
    public String formName;
    @JsonProperty("formId")
    public String formId;
    @JsonProperty("customFields")
    public List<CustomField> customFields = null;
    @JsonProperty("activityType")
    public String activityType;
    @JsonProperty("modifiedDate")
    public String modifiedDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Result result = (Result) o;

        if (!id.equals(result.id)) return false;
        if (!supporterId.equals(result.supporterId)) return false;
        if (!activityDate.equals(result.activityDate)) return false;
        if (!formName.equals(result.formName)) return false;
        if (!formId.equals(result.formId)) return false;
        if (customFields != null ? !customFields.equals(result.customFields) : result.customFields != null)
            return false;
        if (!activityType.equals(result.activityType)) return false;
        if (!modifiedDate.equals(result.modifiedDate)) return false;
        return additionalProperties.equals(result.additionalProperties);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + supporterId.hashCode();
        result = 31 * result + activityDate.hashCode();
        result = 31 * result + formName.hashCode();
        result = 31 * result + formId.hashCode();
        result = 31 * result + customFields.hashCode();
        result = 31 * result + activityType.hashCode();
        result = 31 * result + modifiedDate.hashCode();
        result = 31 * result + additionalProperties.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id='" + id + '\'' +
                ", supporterId='" + supporterId + '\'' +
                ", activityDate='" + activityDate + '\'' +
                ", formName='" + formName + '\'' +
                ", formId='" + formId + '\'' +
                ", customFields=" + customFields +
                ", activityType='" + activityType + '\'' +
                ", modifiedDate='" + modifiedDate + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }

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