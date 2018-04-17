package com.salsalabs.ignite.automation.apiautomation.models.segments.queryallassignedsupporters.response;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "city",
        "state",
        "postalCode",
        "county",
        "country",
        "lattitude",
        "longitude",
        "addressLine1",
        "federalHouseDistrict",
        "stateHouseDistrict",
        "stateSenateDistrict",
        "countyDistrict",
        "municipalityDistrict"
})
public class Address {

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
    @JsonProperty("lattitude")
    private Double lattitude;
    @JsonProperty("longitude")
    private Double longitude;
    @JsonProperty("addressLine1")
    private String addressLine1;
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
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

    @JsonProperty("addressLine1")
    public String getAddressLine1() {
        return addressLine1;
    }

    @JsonProperty("addressLine1")
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
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
        if (!(o instanceof Address)) return false;

        Address address = (Address) o;

        if (getCity() != null ? !getCity().equals(address.getCity()) : address.getCity() != null) return false;
        if (getState() != null ? !getState().equals(address.getState()) : address.getState() != null) return false;
        if (getPostalCode() != null ? !getPostalCode().equals(address.getPostalCode()) : address.getPostalCode() != null)
            return false;
        if (getCounty() != null ? !getCounty().equals(address.getCounty()) : address.getCounty() != null) return false;
        if (getCountry() != null ? !getCountry().equals(address.getCountry()) : address.getCountry() != null)
            return false;
        if (getLattitude() != null ? !getLattitude().equals(address.getLattitude()) : address.getLattitude() != null)
            return false;
        if (getLongitude() != null ? !getLongitude().equals(address.getLongitude()) : address.getLongitude() != null)
            return false;
        if (getAddressLine1() != null ? !getAddressLine1().equals(address.getAddressLine1()) : address.getAddressLine1() != null)
            return false;
        if (getFederalHouseDistrict() != null ? !getFederalHouseDistrict().equals(address.getFederalHouseDistrict()) : address.getFederalHouseDistrict() != null)
            return false;
        if (getStateHouseDistrict() != null ? !getStateHouseDistrict().equals(address.getStateHouseDistrict()) : address.getStateHouseDistrict() != null)
            return false;
        if (getStateSenateDistrict() != null ? !getStateSenateDistrict().equals(address.getStateSenateDistrict()) : address.getStateSenateDistrict() != null)
            return false;
        if (getCountyDistrict() != null ? !getCountyDistrict().equals(address.getCountyDistrict()) : address.getCountyDistrict() != null)
            return false;
        if (getMunicipalityDistrict() != null ? !getMunicipalityDistrict().equals(address.getMunicipalityDistrict()) : address.getMunicipalityDistrict() != null)
            return false;
        return getAdditionalProperties() != null ? getAdditionalProperties().equals(address.getAdditionalProperties()) : address.getAdditionalProperties() == null;

    }

    @Override
    public int hashCode() {
        int result = getCity() != null ? getCity().hashCode() : 0;
        result = 31 * result + (getState() != null ? getState().hashCode() : 0);
        result = 31 * result + (getPostalCode() != null ? getPostalCode().hashCode() : 0);
        result = 31 * result + (getCounty() != null ? getCounty().hashCode() : 0);
        result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
        result = 31 * result + (getLattitude() != null ? getLattitude().hashCode() : 0);
        result = 31 * result + (getLongitude() != null ? getLongitude().hashCode() : 0);
        result = 31 * result + (getAddressLine1() != null ? getAddressLine1().hashCode() : 0);
        result = 31 * result + (getFederalHouseDistrict() != null ? getFederalHouseDistrict().hashCode() : 0);
        result = 31 * result + (getStateHouseDistrict() != null ? getStateHouseDistrict().hashCode() : 0);
        result = 31 * result + (getStateSenateDistrict() != null ? getStateSenateDistrict().hashCode() : 0);
        result = 31 * result + (getCountyDistrict() != null ? getCountyDistrict().hashCode() : 0);
        result = 31 * result + (getMunicipalityDistrict() != null ? getMunicipalityDistrict().hashCode() : 0);
        result = 31 * result + (getAdditionalProperties() != null ? getAdditionalProperties().hashCode() : 0);
        return result;
    }
}