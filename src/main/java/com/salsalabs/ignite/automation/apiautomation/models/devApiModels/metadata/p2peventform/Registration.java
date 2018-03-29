package com.salsalabs.ignite.automation.apiautomation.models.devApiModels.metadata.p2peventform;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "price",
        "totalAvailable"
})
public class Registration {

    @JsonProperty("name")
    private String name;
    @JsonProperty("price")
    private Integer price;
    @JsonProperty("totalAvailable")
    private Integer totalAvailable;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("price")
    public Integer getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(Integer price) {
        this.price = price;
    }

    @JsonProperty("totalAvailable")
    public Integer getTotalAvailable() {
        return totalAvailable;
    }

    @JsonProperty("totalAvailable")
    public void setTotalAvailable(Integer totalAvailable) {
        this.totalAvailable = totalAvailable;
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

        Registration that = (Registration) o;

        if (!name.equals(that.name)) return false;
        if (!price.equals(that.price)) return false;
        if (!totalAvailable.equals(that.totalAvailable)) return false;
        return additionalProperties.equals(that.additionalProperties);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + totalAvailable.hashCode();
        result = 31 * result + additionalProperties.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", totalAvailable=" + totalAvailable +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}


