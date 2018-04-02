package com.salsalabs.ignite.automation.apiautomation.models.donations.response;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "readOnly",
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
        "address",
        "contacts",
        "customFieldValues",
        "result"
})
public class Supporter {

    @JsonProperty("readOnly")
    private Boolean readOnly;
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

    @JsonProperty("readOnly")
    public Boolean getReadOnly() {
        return readOnly;
    }

    @JsonProperty("readOnly")
    public void setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
    }

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
        if (!(o instanceof Supporter)) return false;

        Supporter supporter = (Supporter) o;

        if (getReadOnly() != null ? !getReadOnly().equals(supporter.getReadOnly()) : supporter.getReadOnly() != null)
            return false;
        //if (getSupporterId() != null ? !getSupporterId().equals(supporter.getSupporterId()) : supporter.getSupporterId() != null)
          //  return false;
        if (getTitle() != null ? !getTitle().equals(supporter.getTitle()) : supporter.getTitle() != null) return false;
        if (getFirstName() != null ? !getFirstName().equals(supporter.getFirstName()) : supporter.getFirstName() != null)
            return false;
        if (getMiddleName() != null ? !getMiddleName().equals(supporter.getMiddleName()) : supporter.getMiddleName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(supporter.getLastName()) : supporter.getLastName() != null)
            return false;
        if (getSuffix() != null ? !getSuffix().equals(supporter.getSuffix()) : supporter.getSuffix() != null)
            return false;
        if (getDateOfBirth() != null ? !getDateOfBirth().equals(supporter.getDateOfBirth()) : supporter.getDateOfBirth() != null)
            return false;
        if (getGender() != null ? !getGender().equals(supporter.getGender()) : supporter.getGender() != null)
            return false;
       /* if (getCreatedDate() != null ? !getCreatedDate().equals(supporter.getCreatedDate()) : supporter.getCreatedDate() != null)
            return false;
        if (getLastModified() != null ? !getLastModified().equals(supporter.getLastModified()) : supporter.getLastModified() != null)
            return false;*/
        if (getAddress() != null ? !getAddress().equals(supporter.getAddress()) : supporter.getAddress() != null)
            return false;
        if (getContacts() != null ? !getContacts().equals(supporter.getContacts()) : supporter.getContacts() != null)
            return false;
        if (getCustomFieldValues() != null ? !getCustomFieldValues().equals(supporter.getCustomFieldValues()) : supporter.getCustomFieldValues() != null)
            return false;
        if (getResult() != null ? !getResult().equals(supporter.getResult()) : supporter.getResult() != null)
            return false;
        return getAdditionalProperties() != null ? getAdditionalProperties().equals(supporter.getAdditionalProperties()) : supporter.getAdditionalProperties() == null;

    }

    @Override
    public int hashCode() {
        int result1 = getReadOnly() != null ? getReadOnly().hashCode() : 0;
        result1 = 31 * result1 + (getSupporterId() != null ? getSupporterId().hashCode() : 0);
        result1 = 31 * result1 + (getTitle() != null ? getTitle().hashCode() : 0);
        result1 = 31 * result1 + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result1 = 31 * result1 + (getMiddleName() != null ? getMiddleName().hashCode() : 0);
        result1 = 31 * result1 + (getLastName() != null ? getLastName().hashCode() : 0);
        result1 = 31 * result1 + (getSuffix() != null ? getSuffix().hashCode() : 0);
        result1 = 31 * result1 + (getDateOfBirth() != null ? getDateOfBirth().hashCode() : 0);
        result1 = 31 * result1 + (getGender() != null ? getGender().hashCode() : 0);
        result1 = 31 * result1 + (getCreatedDate() != null ? getCreatedDate().hashCode() : 0);
        result1 = 31 * result1 + (getLastModified() != null ? getLastModified().hashCode() : 0);
        result1 = 31 * result1 + (getAddress() != null ? getAddress().hashCode() : 0);
        result1 = 31 * result1 + (getContacts() != null ? getContacts().hashCode() : 0);
        result1 = 31 * result1 + (getCustomFieldValues() != null ? getCustomFieldValues().hashCode() : 0);
        result1 = 31 * result1 + (getResult() != null ? getResult().hashCode() : 0);
        result1 = 31 * result1 + (getAdditionalProperties() != null ? getAdditionalProperties().hashCode() : 0);
        return result1;
    }

    @Override
    public String toString() {
        return "Supporter{" +
                "readOnly=" + readOnly +
                ", supporterId='" + supporterId + '\'' +
                ", title='" + title + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", suffix='" + suffix + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", gender='" + gender + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", lastModified='" + lastModified + '\'' +
                ", address=" + address +
                ", contacts=" + contacts +
                ", customFieldValues=" + customFieldValues +
                ", result='" + result + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}