package com.salsalabs.ignite.automation.apiautomation.models.donations.request;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "title",
        "firstName",
        "middleName",
        "lastName",
        "suffix",
        "dateOfBirth",
        "gender",
        "address",
        "contacts",
        "customFieldValues"
})
public class Supporter {

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
    @JsonProperty("address")
    private Address address;
    @JsonProperty("contacts")
    private List<Contact> contacts = null;
    @JsonProperty("customFieldValues")
    private List<CustomFieldValue_> customFieldValues = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
    public List<CustomFieldValue_> getCustomFieldValues() {
        return customFieldValues;
    }

    @JsonProperty("customFieldValues")
    public void setCustomFieldValues(List<CustomFieldValue_> customFieldValues) {
        this.customFieldValues = customFieldValues;
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
        return new ToStringBuilder(this).append("title", title).append("firstName", firstName).append("middleName", middleName).append("lastName", lastName).append("suffix", suffix).append("dateOfBirth", dateOfBirth).append("gender", gender).append("address", address).append("contacts", contacts).append("customFieldValues", customFieldValues).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(dateOfBirth).append(middleName).append(lastName).append(title).append(address).append(additionalProperties).append(customFieldValues).append(gender).append(firstName).append(suffix).append(contacts).toHashCode();
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
        return new EqualsBuilder().append(dateOfBirth, rhs.dateOfBirth).append(middleName, rhs.middleName).append(lastName, rhs.lastName).append(title, rhs.title).append(address, rhs.address).append(additionalProperties, rhs.additionalProperties).append(customFieldValues, rhs.customFieldValues).append(gender, rhs.gender).append(firstName, rhs.firstName).append(suffix, rhs.suffix).append(contacts, rhs.contacts).isEquals();
    }

}