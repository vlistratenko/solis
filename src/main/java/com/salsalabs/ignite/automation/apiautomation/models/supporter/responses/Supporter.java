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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "supporterId",
        "title",
        "firstName",
        "middleName",
        "lastName",
        "suffix",
        "dateOfBirth",
        "gender",
        "createdDate",
        "lastModified",
        "externalSystemId",
        "address",
        "contacts",
        "customFieldValues",
        "result"
})
public class Supporter {

    @JsonProperty("supporterId")
    private String supporterId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("middleName")
    private String middleName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("suffix")
    private String suffix;
    @JsonProperty("dateOfBirth")
    private String dateOfBirth;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("createdDate")
    private String createdDate;
    @JsonProperty("lastModified")
    private String lastModified;
    @JsonProperty("externalSystemId")
    private String externalSystemId;
    @JsonProperty("address")
    private Address address;
    @JsonProperty("contacts")
    private List<Contact> contacts = null;
    @JsonProperty("customFieldValues")
    private List<CustomFieldValue> customFieldValues = null;
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

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("middleName")
    public String getMiddleName() {
        return middleName;
    }

    @JsonProperty("middleName")
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("suffix")
    public String getSuffix() {
        return suffix;
    }

    @JsonProperty("suffix")
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @JsonProperty("dateOfBirth")
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    @JsonProperty("dateOfBirth")
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @JsonProperty("gender")
    public String getGender() {
        return gender;
    }

    @JsonProperty("gender")
    public void setGender(String gender) {
        this.gender = gender;
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

    @JsonProperty("externalSystemId")
    public String getExternalSystemId() {
        return externalSystemId;
    }

    @JsonProperty("externalSystemId")
    public void setExternalSystemId(String externalSystemId) {
        this.externalSystemId = externalSystemId;
    }

    @JsonProperty("address")
    public Address getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(Address address) {
        this.address = address;
    }

    @JsonProperty("contacts")
    public List<Contact> getContacts() {
        return contacts;
    }

    @JsonProperty("contacts")
    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @JsonProperty("customFieldValues")
    public List<CustomFieldValue> getCustomFieldValues() {
        return customFieldValues;
    }

    @JsonProperty("customFieldValues")
    public void setCustomFieldValues(List<CustomFieldValue> customFieldValues) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Supporter supporter = (Supporter) o;

        if (supporterId != null ? !supporterId.equals(supporter.supporterId) : supporter.supporterId != null)
            return false;
        if (title != null ? !title.equals(supporter.title) : supporter.title != null) return false;
        if (firstName != null ? !firstName.equals(supporter.firstName) : supporter.firstName != null) return false;
        if (middleName != null ? !middleName.equals(supporter.middleName) : supporter.middleName != null) return false;
        if (lastName != null ? !lastName.equals(supporter.lastName) : supporter.lastName != null) return false;
        if (suffix != null ? !suffix.equals(supporter.suffix) : supporter.suffix != null) return false;
        /*if (dateOfBirth != null ? !dateOfBirth.equals(supporter.dateOfBirth) : supporter.dateOfBirth != null)
            return false;*/
        if (gender != null ? !gender.equals(supporter.gender) : supporter.gender != null) return false;
       /*if (createdDate != null ? !createdDate.equals(supporter.createdDate) : supporter.createdDate != null)
            return false;
        if (lastModified != null ? !lastModified.equals(supporter.lastModified) : supporter.lastModified != null)
            return false;*/
        if (externalSystemId != null ? !externalSystemId.equals(supporter.externalSystemId) : supporter.externalSystemId != null)
            return false;
        if (address != null ? !address.equals(supporter.address) : supporter.address != null) return false;
        if (contacts != null ? !contacts.equals(supporter.contacts) : supporter.contacts != null) return false;
        if (customFieldValues != null ? !customFieldValues.equals(supporter.customFieldValues) : supporter.customFieldValues != null)
            return false;
        /*if (result != null ? !result.equals(supporter.result) : supporter.result != null) return false;*/
        return additionalProperties != null ? additionalProperties.equals(supporter.additionalProperties) : supporter.additionalProperties == null;

    }

    @Override
    public int hashCode() {
        int result1 = supporterId != null ? supporterId.hashCode() : 0;
        result1 = 31 * result1 + (title != null ? title.hashCode() : 0);
        result1 = 31 * result1 + (firstName != null ? firstName.hashCode() : 0);
        result1 = 31 * result1 + (middleName != null ? middleName.hashCode() : 0);
        result1 = 31 * result1 + (lastName != null ? lastName.hashCode() : 0);
        result1 = 31 * result1 + (suffix != null ? suffix.hashCode() : 0);
        result1 = 31 * result1 + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result1 = 31 * result1 + (gender != null ? gender.hashCode() : 0);
        result1 = 31 * result1 + (createdDate != null ? createdDate.hashCode() : 0);
        result1 = 31 * result1 + (lastModified != null ? lastModified.hashCode() : 0);
        result1 = 31 * result1 + (externalSystemId != null ? externalSystemId.hashCode() : 0);
        result1 = 31 * result1 + (address != null ? address.hashCode() : 0);
        result1 = 31 * result1 + (contacts != null ? contacts.hashCode() : 0);
        result1 = 31 * result1 + (customFieldValues != null ? customFieldValues.hashCode() : 0);
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        result1 = 31 * result1 + (additionalProperties != null ? additionalProperties.hashCode() : 0);
        return result1;
    }

    @Override
    public String toString() {
        return "Supporter{" +
                "supporterId='" + supporterId + '\'' +
                ", title='" + title + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", suffix='" + suffix + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", gender='" + gender + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", lastModified='" + lastModified + '\'' +
                ", externalSystemId='" + externalSystemId + '\'' +
                ", address=" + address +
                ", contacts=" + contacts +
                ", customFieldValues=" + customFieldValues +
                ", result='" + result + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
