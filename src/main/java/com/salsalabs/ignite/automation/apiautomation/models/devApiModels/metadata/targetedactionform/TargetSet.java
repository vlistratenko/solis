package com.salsalabs.ignite.automation.apiautomation.models.devApiModels.metadata.targetedactionform;

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
        "description",
        "email",
        "twitter",
        "facebook"
})
public class TargetSet {

    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("email")
    private Email email;
    @JsonProperty("twitter")
    private Twitter twitter;
    @JsonProperty("facebook")
    private Facebook facebook;
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

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("email")
    public Email getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(Email email) {
        this.email = email;
    }

    @JsonProperty("twitter")
    public Twitter getTwitter() {
        return twitter;
    }

    @JsonProperty("twitter")
    public void setTwitter(Twitter twitter) {
        this.twitter = twitter;
    }

    @JsonProperty("facebook")
    public Facebook getFacebook() {
        return facebook;
    }

    @JsonProperty("facebook")
    public void setFacebook(Facebook facebook) {
        this.facebook = facebook;
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