package com.salsalabs.ignite.automation.apiautomation.models.segments.queryassignedsupporter.response;

import java.util.HashMap;
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
        "addressLine1",
        "city",
        "state",
        "postalCode",
        "county",
        "country",
        "federalHouseDistrict",
        "stateHouseDistrict",
        "stateSenateDistrict",
        "countyDistrict",
        "municipalityDistrict",
        "lattitude",
        "longitude"
})
public class Address {

    @JsonProperty("addressLine1")
    private String addressLine1;
    @JsonProperty("city")
    private String city;
    @JsonProperty("state")
    private String state;
    @JsonProperty("postalCode")
    private String postalCode;
    @JsonProperty("county")
    private String county;
    @JsonProperty("country")
    private String country;
    @JsonProperty("federalHouseDistrict")
    private String federalHouseDistrict;
    @JsonProperty("stateHouseDistrict")
    private String stateHouseDistrict;
    @JsonProperty("stateSenateDistrict")
    private String stateSenateDistrict;
    @JsonProperty("countyDistrict")
    private String countyDistrict;
    @JsonProperty("municipalityDistrict")
    private String municipalityDistrict;
    @JsonProperty("lattitude")
    private Double lattitude;
    @JsonProperty("longitude")
    private Double longitude;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("addressLine1")
    public String getAddressLine1() {
        return addressLine1;
    }

    @JsonProperty("addressLine1")
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
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

    @JsonProperty("postalCode")
    public String getPostalCode() {
        return postalCode;
    }

    @JsonProperty("postalCode")
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @JsonProperty("county")
    public String getCounty() {
        return county;
    }

    @JsonProperty("county")
    public void setCounty(String county) {
        this.county = county;
    }

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("federalHouseDistrict")
    public String getFederalHouseDistrict() {
        return federalHouseDistrict;
    }

    @JsonProperty("federalHouseDistrict")
    public void setFederalHouseDistrict(String federalHouseDistrict) {
        this.federalHouseDistrict = federalHouseDistrict;
    }

    @JsonProperty("stateHouseDistrict")
    public String getStateHouseDistrict() {
        return stateHouseDistrict;
    }

    @JsonProperty("stateHouseDistrict")
    public void setStateHouseDistrict(String stateHouseDistrict) {
        this.stateHouseDistrict = stateHouseDistrict;
    }

    @JsonProperty("stateSenateDistrict")
    public String getStateSenateDistrict() {
        return stateSenateDistrict;
    }

    @JsonProperty("stateSenateDistrict")
    public void setStateSenateDistrict(String stateSenateDistrict) {
        this.stateSenateDistrict = stateSenateDistrict;
    }

    @JsonProperty("countyDistrict")
    public String getCountyDistrict() {
        return countyDistrict;
    }

    @JsonProperty("countyDistrict")
    public void setCountyDistrict(String countyDistrict) {
        this.countyDistrict = countyDistrict;
    }

    @JsonProperty("municipalityDistrict")
    public String getMunicipalityDistrict() {
        return municipalityDistrict;
    }

    @JsonProperty("municipalityDistrict")
    public void setMunicipalityDistrict(String municipalityDistrict) {
        this.municipalityDistrict = municipalityDistrict;
    }

    @JsonProperty("lattitude")
    public Double getLattitude() {
        return lattitude;
    }

    @JsonProperty("lattitude")
    public void setLattitude(Double lattitude) {
        this.lattitude = lattitude;
    }

    @JsonProperty("longitude")
    public Double getLongitude() {
        return longitude;
    }

    @JsonProperty("longitude")
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
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
        return new ToStringBuilder(this).append("addressLine1", addressLine1).append("city", city).append("state", state).append("postalCode", postalCode).append("county", county).append("country", country).append("federalHouseDistrict", federalHouseDistrict).append("stateHouseDistrict", stateHouseDistrict).append("stateSenateDistrict", stateSenateDistrict).append("countyDistrict", countyDistrict).append("municipalityDistrict", municipalityDistrict).append("lattitude", lattitude).append("longitude", longitude).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(countyDistrict).append(stateSenateDistrict).append(state).append(federalHouseDistrict).append(addressLine1).append(country).append(city).append(postalCode).append(county).append(additionalProperties).append(stateHouseDistrict).append(lattitude).append(longitude).append(municipalityDistrict).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Address) == false) {
            return false;
        }
        Address rhs = ((Address) other);
        return new EqualsBuilder().append(countyDistrict, rhs.countyDistrict).append(stateSenateDistrict, rhs.stateSenateDistrict).append(state, rhs.state).append(federalHouseDistrict, rhs.federalHouseDistrict).append(addressLine1, rhs.addressLine1).append(country, rhs.country).append(city, rhs.city).append(postalCode, rhs.postalCode).append(county, rhs.county).append(additionalProperties, rhs.additionalProperties).append(stateHouseDistrict, rhs.stateHouseDistrict).append(lattitude, rhs.lattitude).append(longitude, rhs.longitude).append(municipalityDistrict, rhs.municipalityDistrict).isEquals();
    }

}