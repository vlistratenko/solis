package com.salsalabs.ignite.automation.apiautomation.models.devApiModels.metadata.ticketedeventform;

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
        "startDate",
        "endDate",
        "allDay",
        "publicEventName",
        "timezone",
        "venueName",
        "addressLine1",
        "addressLine2",
        "city",
        "state",
        "zipCode",
        "tickets"
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
    @JsonProperty("startDate")
    private String startDate;
    @JsonProperty("endDate")
    private String endDate;
    @JsonProperty("allDay")
    private Boolean allDay;
    @JsonProperty("publicEventName")
    private String publicEventName;
    @JsonProperty("timezone")
    private String timezone;
    @JsonProperty("venueName")
    private String venueName;
    @JsonProperty("addressLine1")
    private String addressLine1;
    @JsonProperty("addressLine2")
    private String addressLine2;
    @JsonProperty("city")
    private String city;
    @JsonProperty("state")
    private String state;
    @JsonProperty("zipCode")
    private String zipCode;
    @JsonProperty("tickets")
    private List<Ticket> tickets = null;
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

    @JsonProperty("startDate")
    public String getStartDate() {
        return startDate;
    }

    @JsonProperty("startDate")
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @JsonProperty("endDate")
    public String getEndDate() {
        return endDate;
    }

    @JsonProperty("endDate")
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @JsonProperty("allDay")
    public Boolean getAllDay() {
        return allDay;
    }

    @JsonProperty("allDay")
    public void setAllDay(Boolean allDay) {
        this.allDay = allDay;
    }

    @JsonProperty("publicEventName")
    public String getPublicEventName() {
        return publicEventName;
    }

    @JsonProperty("publicEventName")
    public void setPublicEventName(String publicEventName) {
        this.publicEventName = publicEventName;
    }

    @JsonProperty("timezone")
    public String getTimezone() {
        return timezone;
    }

    @JsonProperty("timezone")
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    @JsonProperty("venueName")
    public String getVenueName() {
        return venueName;
    }

    @JsonProperty("venueName")
    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    @JsonProperty("addressLine1")
    public String getAddressLine1() {
        return addressLine1;
    }

    @JsonProperty("addressLine1")
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    @JsonProperty("addressLine2")
    public String getAddressLine2() {
        return addressLine2;
    }

    @JsonProperty("addressLine2")
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("zipCode")
    public String getZipCode() {
        return zipCode;
    }

    @JsonProperty("zipCode")
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @JsonProperty("tickets")
    public List<Ticket> getTickets() {
        return tickets;
    }

    @JsonProperty("tickets")
    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
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
        if (o == null || getClass() != o.getClass()) return false;

        Payload payload = (Payload) o;

        if (!type.equals(payload.type)) return false;
        if (!id.equals(payload.id)) return false;
        if (!name.equals(payload.name)) return false;
        if (!description.equals(payload.description)) return false;
        if (!createDate.equals(payload.createDate)) return false;
        if (!publishDate.equals(payload.publishDate)) return false;
        if (!modifiedDate.equals(payload.modifiedDate)) return false;
        if (!status.equals(payload.status)) return false;
        if (!visibility.equals(payload.visibility)) return false;
        if (!pageUrl.equals(payload.pageUrl)) return false;
        if (!googleAnalytics.equals(payload.googleAnalytics)) return false;
        if (!facebookPixel.equals(payload.facebookPixel)) return false;
        if (!formFields.equals(payload.formFields)) return false;
        if (!startDate.equals(payload.startDate)) return false;
        if (!endDate.equals(payload.endDate)) return false;
        if (!allDay.equals(payload.allDay)) return false;
        if (!publicEventName.equals(payload.publicEventName)) return false;
        if (!timezone.equals(payload.timezone)) return false;
        if (!venueName.equals(payload.venueName)) return false;
        if (!addressLine1.equals(payload.addressLine1)) return false;
        if (!addressLine2.equals(payload.addressLine2)) return false;
        if (!city.equals(payload.city)) return false;
        if (!state.equals(payload.state)) return false;
        if (!zipCode.equals(payload.zipCode)) return false;
        if (!tickets.equals(payload.tickets)) return false;
        return additionalProperties.equals(payload.additionalProperties);
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
        result = 31 * result + pageUrl.hashCode();
        result = 31 * result + googleAnalytics.hashCode();
        result = 31 * result + facebookPixel.hashCode();
        result = 31 * result + formFields.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + allDay.hashCode();
        result = 31 * result + publicEventName.hashCode();
        result = 31 * result + timezone.hashCode();
        result = 31 * result + venueName.hashCode();
        result = 31 * result + addressLine1.hashCode();
        result = 31 * result + addressLine2.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + state.hashCode();
        result = 31 * result + zipCode.hashCode();
        result = 31 * result + tickets.hashCode();
        result = 31 * result + additionalProperties.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Payload{" +
                "type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createDate='" + createDate + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", modifiedDate='" + modifiedDate + '\'' +
                ", status='" + status + '\'' +
                ", visibility='" + visibility + '\'' +
                ", pageUrl='" + pageUrl + '\'' +
                ", googleAnalytics=" + googleAnalytics +
                ", facebookPixel=" + facebookPixel +
                ", formFields=" + formFields +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", allDay=" + allDay +
                ", publicEventName='" + publicEventName + '\'' +
                ", timezone='" + timezone + '\'' +
                ", venueName='" + venueName + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", tickets=" + tickets +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}