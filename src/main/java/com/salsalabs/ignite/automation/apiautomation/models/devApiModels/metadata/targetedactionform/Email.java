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
        "eligibleTargetsCount",
        "topic",
        "subject",
        "message",
        "canModifyContentOption",
        "allowComments"
})
public class Email {

    @JsonProperty("eligibleTargetsCount")
    private Integer eligibleTargetsCount;
    @JsonProperty("topic")
    private String topic;
    @JsonProperty("subject")
    private String subject;
    @JsonProperty("message")
    private String message;
    @JsonProperty("canModifyContentOption")
    private Boolean canModifyContentOption;
    @JsonProperty("allowComments")
    private Boolean allowComments;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("eligibleTargetsCount")
    public Integer getEligibleTargetsCount() {
        return eligibleTargetsCount;
    }

    @JsonProperty("eligibleTargetsCount")
    public void setEligibleTargetsCount(Integer eligibleTargetsCount) {
        this.eligibleTargetsCount = eligibleTargetsCount;
    }

    @JsonProperty("topic")
    public String getTopic() {
        return topic;
    }

    @JsonProperty("topic")
    public void setTopic(String topic) {
        this.topic = topic;
    }

    @JsonProperty("subject")
    public String getSubject() {
        return subject;
    }

    @JsonProperty("subject")
    public void setSubject(String subject) {
        this.subject = subject;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("canModifyContentOption")
    public Boolean getCanModifyContentOption() {
        return canModifyContentOption;
    }

    @JsonProperty("canModifyContentOption")
    public void setCanModifyContentOption(Boolean canModifyContentOption) {
        this.canModifyContentOption = canModifyContentOption;
    }

    @JsonProperty("allowComments")
    public Boolean getAllowComments() {
        return allowComments;
    }

    @JsonProperty("allowComments")
    public void setAllowComments(Boolean allowComments) {
        this.allowComments = allowComments;
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