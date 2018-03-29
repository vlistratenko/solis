
package com.salsalabs.ignite.automation.apiautomation.models.devApiModels.metadata.fundraisingform;

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
        "type",
        "id",
        "name",
        "description",
        "createDate",
        "publishDate",
        "modifiedDate",
        "status",
        "visibility",
        "pageUrl",
        "widgetScript",
        "googleAnalytics",
        "facebookPixel",
        "formFields"
})
public class Payload {

    @JsonProperty("type")
    private String type;
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("createDate")
    private String createDate;
    @JsonProperty("publishDate")
    private String publishDate;
    @JsonProperty("modifiedDate")
    private String modifiedDate;
    @JsonProperty("status")
    private String status;
    @JsonProperty("visibility")
    private String visibility;
    @JsonProperty("pageUrl")
    private String pageUrl;
    @JsonProperty("widgetScript")
    private String widgetScript;
    @JsonProperty("googleAnalytics")
    private Boolean googleAnalytics;
    @JsonProperty("facebookPixel")
    private Boolean facebookPixel;
    @JsonProperty("formFields")
    private List<FormField> formFields = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

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

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("createDate")
    public String getCreateDate() {
        return createDate;
    }

    @JsonProperty("createDate")
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @JsonProperty("publishDate")
    public String getPublishDate() {
        return publishDate;
    }

    @JsonProperty("publishDate")
    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    @JsonProperty("modifiedDate")
    public String getModifiedDate() {
        return modifiedDate;
    }

    @JsonProperty("modifiedDate")
    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("visibility")
    public String getVisibility() {
        return visibility;
    }

    @JsonProperty("visibility")
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    @JsonProperty("pageUrl")
    public String getPageUrl() {
        return pageUrl;
    }

    @JsonProperty("pageUrl")
    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    @JsonProperty("widgetScript")
    public String getWidgetScript() {
        return widgetScript;
    }

    @JsonProperty("widgetScript")
    public void setWidgetScript(String widgetScript) {
        this.widgetScript = widgetScript;
    }

    @JsonProperty("googleAnalytics")
    public Boolean getGoogleAnalytics() {
        return googleAnalytics;
    }

    @JsonProperty("googleAnalytics")
    public void setGoogleAnalytics(Boolean googleAnalytics) {
        this.googleAnalytics = googleAnalytics;
    }

    @JsonProperty("facebookPixel")
    public Boolean getFacebookPixel() {
        return facebookPixel;
    }

    @JsonProperty("facebookPixel")
    public void setFacebookPixel(Boolean facebookPixel) {
        this.facebookPixel = facebookPixel;
    }

    @JsonProperty("formFields")
    public List<FormField> getFormFields() {
        return formFields;
    }

    @JsonProperty("formFields")
    public void setFormFields(List<FormField> formFields) {
        this.formFields = formFields;
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
        return new ToStringBuilder(this).append("type", type).append("id", id).append("name", name).append("description", description).append("createDate", createDate).append("publishDate", publishDate).append("modifiedDate", modifiedDate).append("status", status).append("visibility", visibility).append("pageUrl", pageUrl).append("widgetScript", widgetScript).append("googleAnalytics", googleAnalytics).append("facebookPixel", facebookPixel).append("formFields", formFields).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(googleAnalytics).append(visibility).append(status).append(pageUrl).append(type).append(modifiedDate).append(formFields).append(id).append(additionalProperties).append(facebookPixel).append(widgetScript).append(description).append(name).append(createDate).append(publishDate).toHashCode();
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
        return new EqualsBuilder().append(googleAnalytics, rhs.googleAnalytics).append(visibility, rhs.visibility).append(status, rhs.status).append(pageUrl, rhs.pageUrl).append(type, rhs.type).append(modifiedDate, rhs.modifiedDate).append(formFields, rhs.formFields).append(id, rhs.id).append(additionalProperties, rhs.additionalProperties).append(facebookPixel, rhs.facebookPixel).append(widgetScript, rhs.widgetScript).append(description, rhs.description).append(name, rhs.name).append(createDate, rhs.createDate).append(publishDate, rhs.publishDate).isEquals();
    }

}