package com.salsalabs.ignite.automation.apiautomation.models.devApiModels.metadata.targetedactionform;

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
        "formFields",
        "includeClickToCall",
        "targetSets",
        "promotionalFacebookMessage",
        "promotionalTwitterMessage"
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
    @JsonProperty("includeClickToCall")
    private Boolean includeClickToCall;
    @JsonProperty("targetSets")
    private List<TargetSet> targetSets = null;
    @JsonProperty("promotionalFacebookMessage")
    private String promotionalFacebookMessage;
    @JsonProperty("promotionalTwitterMessage")
    private String promotionalTwitterMessage;
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

    @JsonProperty("includeClickToCall")
    public Boolean getIncludeClickToCall() {
        return includeClickToCall;
    }

    @JsonProperty("includeClickToCall")
    public void setIncludeClickToCall(Boolean includeClickToCall) {
        this.includeClickToCall = includeClickToCall;
    }

    @JsonProperty("targetSets")
    public List<TargetSet> getTargetSets() {
        return targetSets;
    }

    @JsonProperty("targetSets")
    public void setTargetSets(List<TargetSet> targetSets) {
        this.targetSets = targetSets;
    }

    @JsonProperty("promotionalFacebookMessage")
    public String getPromotionalFacebookMessage() {
        return promotionalFacebookMessage;
    }

    @JsonProperty("promotionalFacebookMessage")
    public void setPromotionalFacebookMessage(String promotionalFacebookMessage) {
        this.promotionalFacebookMessage = promotionalFacebookMessage;
    }

    @JsonProperty("promotionalTwitterMessage")
    public String getPromotionalTwitterMessage() {
        return promotionalTwitterMessage;
    }

    @JsonProperty("promotionalTwitterMessage")
    public void setPromotionalTwitterMessage(String promotionalTwitterMessage) {
        this.promotionalTwitterMessage = promotionalTwitterMessage;
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