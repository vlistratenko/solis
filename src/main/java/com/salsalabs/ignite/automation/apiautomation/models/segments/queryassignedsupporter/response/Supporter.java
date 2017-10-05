package com.salsalabs.ignite.automation.apiautomation.models.segments.queryassignedsupporter.response;

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
        "supporterId",
        "firstName",
        "lastName",
        "createdDate",
        "lastModified",
        "address",
        "contacts",
        "customFieldValues",
        "result"
})
public class Supporter {

    @JsonProperty("supporterId")
    private String supporterId;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("createdDate")
    private String createdDate;
    @JsonProperty("lastModified")
    private String lastModified;
    @JsonProperty("address")
    private com.salsalabs.ignite.automation.apiautomation.models.segments.queryassignedsupporter.response.Address address;
    @JsonProperty("contacts")
    private List<com.salsalabs.ignite.automation.apiautomation.models.segments.queryassignedsupporter.response.Contact> contacts = null;
    @JsonProperty("customFieldValues")
    private List<Object> customFieldValues = null;
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

    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("createdDate")
    public String getCreatedDate() {
        return createdDate;
    }

    @JsonProperty("createdDate")
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @JsonProperty("lastModified")
    public String getLastModified() {
        return lastModified;
    }

    @JsonProperty("lastModified")
    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    @JsonProperty("address")
    public com.salsalabs.ignite.automation.apiautomation.models.segments.queryassignedsupporter.response.Address getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(com.salsalabs.ignite.automation.apiautomation.models.segments.queryassignedsupporter.response.Address address) {
        this.address = address;
    }

    @JsonProperty("contacts")
    public List<com.salsalabs.ignite.automation.apiautomation.models.segments.queryassignedsupporter.response.Contact> getContacts() {
        return contacts;
    }

    @JsonProperty("contacts")
    public void setContacts(List<com.salsalabs.ignite.automation.apiautomation.models.segments.queryassignedsupporter.response.Contact> contacts) {
        this.contacts = contacts;
    }

    @JsonProperty("customFieldValues")
    public List<Object> getCustomFieldValues() {
        return customFieldValues;
    }

    @JsonProperty("customFieldValues")
    public void setCustomFieldValues(List<Object> customFieldValues) {
        this.customFieldValues = customFieldValues;
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
    public String toString() {
        return new ToStringBuilder(this).append("supporterId", supporterId).append("firstName", firstName).append("lastName", lastName).append("createdDate", createdDate).append("lastModified", lastModified).append("address", address).append("contacts", contacts).append("customFieldValues", customFieldValues).append("result", result).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(result).append(lastName).append(lastModified).append(address).append(additionalProperties).append(customFieldValues).append(firstName).append(createdDate).append(contacts).append(supporterId).toHashCode();
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
        return new EqualsBuilder().append(result, rhs.result).append(lastName, rhs.lastName).append(lastModified, rhs.lastModified).append(address, rhs.address).append(additionalProperties, rhs.additionalProperties).append(customFieldValues, rhs.customFieldValues).append(firstName, rhs.firstName).append(createdDate, rhs.createdDate).append(contacts, rhs.contacts).append(supporterId, rhs.supporterId).isEquals();
    }

}