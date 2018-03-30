package com.salsalabs.ignite.automation.apiautomation.models.devApiModels.activity.getActivityFormSummary.responseTargets;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "targetTitle", "targetName", "targetSet", "emailedLetters", "phoneCalls", "facebookPosts",
		"twitterPosts", "total", "targetType", "politicalParty", "state", "districtName" })
public class Result {

	@JsonProperty("targetTitle")
	private String targetTitle;
	@JsonProperty("targetName")
	private String targetName;
	@JsonProperty("targetSet")
	private String targetSet;
	@JsonProperty("emailedLetters")
	private Integer emailedLetters;
	@JsonProperty("phoneCalls")
	private Integer phoneCalls;
	@JsonProperty("facebookPosts")
	private Integer facebookPosts;
	@JsonProperty("twitterPosts")
	private Integer twitterPosts;
	@JsonProperty("total")
	private Integer total;
	@JsonProperty("targetType")
	private String targetType;
	@JsonProperty("politicalParty")
	private String politicalParty;
	@JsonProperty("state")
	private String state;
	@JsonProperty("districtName")
	private String districtName;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("targetTitle")
	public String getTargetTitle() {
		return targetTitle;
	}

	@JsonProperty("targetTitle")
	public void setTargetTitle(String targetTitle) {
		this.targetTitle = targetTitle;
	}

	@JsonProperty("targetName")
	public String getTargetName() {
		return targetName;
	}

	@JsonProperty("targetName")
	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	@JsonProperty("targetSet")
	public String getTargetSet() {
		return targetSet;
	}

	@JsonProperty("targetSet")
	public void setTargetSet(String targetSet) {
		this.targetSet = targetSet;
	}

	@JsonProperty("emailedLetters")
	public Integer getEmailedLetters() {
		return emailedLetters;
	}

	@JsonProperty("emailedLetters")
	public void setEmailedLetters(Integer emailedLetters) {
		this.emailedLetters = emailedLetters;
	}

	@JsonProperty("phoneCalls")
	public Integer getPhoneCalls() {
		return phoneCalls;
	}

	@JsonProperty("phoneCalls")
	public void setPhoneCalls(Integer phoneCalls) {
		this.phoneCalls = phoneCalls;
	}

	@JsonProperty("facebookPosts")
	public Integer getFacebookPosts() {
		return facebookPosts;
	}

	@JsonProperty("facebookPosts")
	public void setFacebookPosts(Integer facebookPosts) {
		this.facebookPosts = facebookPosts;
	}

	@JsonProperty("twitterPosts")
	public Integer getTwitterPosts() {
		return twitterPosts;
	}

	@JsonProperty("twitterPosts")
	public void setTwitterPosts(Integer twitterPosts) {
		this.twitterPosts = twitterPosts;
	}

	@JsonProperty("total")
	public Integer getTotal() {
		return total;
	}

	@JsonProperty("total")
	public void setTotal(Integer total) {
		this.total = total;
	}

	@JsonProperty("targetType")
	public String getTargetType() {
		return targetType;
	}

	@JsonProperty("targetType")
	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	@JsonProperty("politicalParty")
	public String getPoliticalParty() {
		return politicalParty;
	}

	@JsonProperty("politicalParty")
	public void setPoliticalParty(String politicalParty) {
		this.politicalParty = politicalParty;
	}

	@JsonProperty("state")
	public String getState() {
		return state;
	}

	@JsonProperty("state")
	public void setState(String state) {
		this.state = state;
	}

	@JsonProperty("districtName")
	public String getDistrictName() {
		return districtName;
	}

	@JsonProperty("districtName")
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
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
		return "Result [targetTitle=" + targetTitle + ", targetName=" + targetName + ", targetSet=" + targetSet
				+ ", emailedLetters=" + emailedLetters + ", phoneCalls=" + phoneCalls + ", facebookPosts="
				+ facebookPosts + ", twitterPosts=" + twitterPosts + ", total=" + total + ", targetType=" + targetType
				+ ", politicalParty=" + politicalParty + ", state=" + state + ", districtName=" + districtName
				+ ", additionalProperties=" + additionalProperties + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((additionalProperties == null) ? 0 : additionalProperties.hashCode());
		result = prime * result + ((districtName == null) ? 0 : districtName.hashCode());
		result = prime * result + ((emailedLetters == null) ? 0 : emailedLetters.hashCode());
		result = prime * result + ((facebookPosts == null) ? 0 : facebookPosts.hashCode());
		result = prime * result + ((phoneCalls == null) ? 0 : phoneCalls.hashCode());
		result = prime * result + ((politicalParty == null) ? 0 : politicalParty.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((targetName == null) ? 0 : targetName.hashCode());
		result = prime * result + ((targetSet == null) ? 0 : targetSet.hashCode());
		result = prime * result + ((targetTitle == null) ? 0 : targetTitle.hashCode());
		result = prime * result + ((targetType == null) ? 0 : targetType.hashCode());
		result = prime * result + ((total == null) ? 0 : total.hashCode());
		result = prime * result + ((twitterPosts == null) ? 0 : twitterPosts.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Result other = (Result) obj;
		if (additionalProperties == null) {
			if (other.additionalProperties != null)
				return false;
		} else if (!additionalProperties.equals(other.additionalProperties))
			return false;
		if (districtName == null) {
			if (other.districtName != null)
				return false;
		} else if (!districtName.equals(other.districtName))
			return false;
		if (emailedLetters == null) {
			if (other.emailedLetters != null)
				return false;
		} else if (!emailedLetters.equals(other.emailedLetters))
			return false;
		if (facebookPosts == null) {
			if (other.facebookPosts != null)
				return false;
		} else if (!facebookPosts.equals(other.facebookPosts))
			return false;
		if (phoneCalls == null) {
			if (other.phoneCalls != null)
				return false;
		} else if (!phoneCalls.equals(other.phoneCalls))
			return false;
		if (politicalParty == null) {
			if (other.politicalParty != null)
				return false;
		} else if (!politicalParty.equals(other.politicalParty))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (targetName == null) {
			if (other.targetName != null)
				return false;
		} else if (!targetName.equals(other.targetName))
			return false;
		if (targetSet == null) {
			if (other.targetSet != null)
				return false;
		} else if (!targetSet.equals(other.targetSet))
			return false;
		if (targetTitle == null) {
			if (other.targetTitle != null)
				return false;
		} else if (!targetTitle.equals(other.targetTitle))
			return false;
		if (targetType == null) {
			if (other.targetType != null)
				return false;
		} else if (!targetType.equals(other.targetType))
			return false;
		if (total == null) {
			if (other.total != null)
				return false;
		} else if (!total.equals(other.total))
			return false;
		if (twitterPosts == null) {
			if (other.twitterPosts != null)
				return false;
		} else if (!twitterPosts.equals(other.twitterPosts))
			return false;
		return true;
	}
	
	
	
	

}