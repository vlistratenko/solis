package com.salsalabs.ignite.automation.apiautomation.models.devApiModels.metrics.response;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "rateLimit", "lastApiCall", "totalApiCalls", "lastApiCallFailure", "totalApiCallFailures",
		"currentRateLimit", "activityFormTypeList", "activityFormList", "activityFormMetadata", "activityFormSummary",
		"blastList", "targetedActionTargetsList", "eventAttendeesList", "p2pRegistrationsList",
		"p2pFundraisersSummaryList", "p2pTeamsList", "p2pPurchasesList", "fundraiserMetadata", "teamMetadata",
		"activitySubmissionList" })
public class Payload {

	@JsonProperty("rateLimit")
	private Integer rateLimit;
	@JsonProperty("lastApiCall")
	private String lastApiCall;
	@JsonProperty("totalApiCalls")
	private Integer totalApiCalls;
	@JsonProperty("lastApiCallFailure")
	private String lastApiCallFailure;
	@JsonProperty("totalApiCallFailures")
	private Integer totalApiCallFailures;
	@JsonProperty("currentRateLimit")
	private Integer currentRateLimit;
	@JsonProperty("activityFormTypeList")
	private Integer activityFormTypeList;
	@JsonProperty("activityFormList")
	private Integer activityFormList;
	@JsonProperty("activityFormMetadata")
	private Integer activityFormMetadata;
	@JsonProperty("activityFormSummary")
	private Integer activityFormSummary;
	@JsonProperty("blastList")
	private Integer blastList;
	@JsonProperty("targetedActionTargetsList")
	private Integer targetedActionTargetsList;
	@JsonProperty("eventAttendeesList")
	private Integer eventAttendeesList;
	@JsonProperty("p2pRegistrationsList")
	private Integer p2pRegistrationsList;
	@JsonProperty("p2pFundraisersSummaryList")
	private Integer p2pFundraisersSummaryList;
	@JsonProperty("p2pTeamsList")
	private Integer p2pTeamsList;
	@JsonProperty("p2pPurchasesList")
	private Integer p2pPurchasesList;
	@JsonProperty("fundraiserMetadata")
	private Integer fundraiserMetadata;
	@JsonProperty("teamMetadata")
	private Integer teamMetadata;
	@JsonProperty("activitySubmissionList")
	private Integer activitySubmissionList;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("rateLimit")
	public Integer getRateLimit() {
		return rateLimit;
	}

	@JsonProperty("rateLimit")
	public void setRateLimit(Integer rateLimit) {
		this.rateLimit = rateLimit;
	}

	@JsonProperty("lastApiCall")
	public String getLastApiCall() {
		return lastApiCall;
	}

	@JsonProperty("lastApiCall")
	public void setLastApiCall(String lastApiCall) {
		this.lastApiCall = lastApiCall;
	}

	@JsonProperty("totalApiCalls")
	public Integer getTotalApiCalls() {
		return totalApiCalls;
	}

	@JsonProperty("totalApiCalls")
	public void setTotalApiCalls(Integer totalApiCalls) {
		this.totalApiCalls = totalApiCalls;
	}

	@JsonProperty("lastApiCallFailure")
	public String getLastApiCallFailure() {
		return lastApiCallFailure;
	}

	@JsonProperty("lastApiCallFailure")
	public void setLastApiCallFailure(String lastApiCallFailure) {
		this.lastApiCallFailure = lastApiCallFailure;
	}

	@JsonProperty("totalApiCallFailures")
	public Integer getTotalApiCallFailures() {
		return totalApiCallFailures;
	}

	@JsonProperty("totalApiCallFailures")
	public void setTotalApiCallFailures(Integer totalApiCallFailures) {
		this.totalApiCallFailures = totalApiCallFailures;
	}

	@JsonProperty("currentRateLimit")
	public Integer getCurrentRateLimit() {
		return currentRateLimit;
	}

	@JsonProperty("currentRateLimit")
	public void setCurrentRateLimit(Integer currentRateLimit) {
		this.currentRateLimit = currentRateLimit;
	}

	@JsonProperty("activityFormTypeList")
	public Integer getActivityFormTypeList() {
		return activityFormTypeList;
	}

	@JsonProperty("activityFormTypeList")
	public void setActivityFormTypeList(Integer activityFormTypeList) {
		this.activityFormTypeList = activityFormTypeList;
	}

	@JsonProperty("activityFormList")
	public Integer getActivityFormList() {
		return activityFormList;
	}

	@JsonProperty("activityFormList")
	public void setActivityFormList(Integer activityFormList) {
		this.activityFormList = activityFormList;
	}

	@JsonProperty("activityFormMetadata")
	public Integer getActivityFormMetadata() {
		return activityFormMetadata;
	}

	@JsonProperty("activityFormMetadata")
	public void setActivityFormMetadata(Integer activityFormMetadata) {
		this.activityFormMetadata = activityFormMetadata;
	}

	@JsonProperty("activityFormSummary")
	public Integer getActivityFormSummary() {
		return activityFormSummary;
	}

	@JsonProperty("activityFormSummary")
	public void setActivityFormSummary(Integer activityFormSummary) {
		this.activityFormSummary = activityFormSummary;
	}

	@JsonProperty("blastList")
	public Integer getBlastList() {
		return blastList;
	}

	@JsonProperty("blastList")
	public void setBlastList(Integer blastList) {
		this.blastList = blastList;
	}

	@JsonProperty("targetedActionTargetsList")
	public Integer getTargetedActionTargetsList() {
		return targetedActionTargetsList;
	}

	@JsonProperty("targetedActionTargetsList")
	public void setTargetedActionTargetsList(Integer targetedActionTargetsList) {
		this.targetedActionTargetsList = targetedActionTargetsList;
	}

	@JsonProperty("eventAttendeesList")
	public Integer getEventAttendeesList() {
		return eventAttendeesList;
	}

	@JsonProperty("eventAttendeesList")
	public void setEventAttendeesList(Integer eventAttendeesList) {
		this.eventAttendeesList = eventAttendeesList;
	}

	@JsonProperty("p2pRegistrationsList")
	public Integer getP2pRegistrationsList() {
		return p2pRegistrationsList;
	}

	@JsonProperty("p2pRegistrationsList")
	public void setP2pRegistrationsList(Integer p2pRegistrationsList) {
		this.p2pRegistrationsList = p2pRegistrationsList;
	}

	@JsonProperty("p2pFundraisersSummaryList")
	public Integer getP2pFundraisersSummaryList() {
		return p2pFundraisersSummaryList;
	}

	@JsonProperty("p2pFundraisersSummaryList")
	public void setP2pFundraisersSummaryList(Integer p2pFundraisersSummaryList) {
		this.p2pFundraisersSummaryList = p2pFundraisersSummaryList;
	}

	@JsonProperty("p2pTeamsList")
	public Integer getP2pTeamsList() {
		return p2pTeamsList;
	}

	@JsonProperty("p2pTeamsList")
	public void setP2pTeamsList(Integer p2pTeamsList) {
		this.p2pTeamsList = p2pTeamsList;
	}

	@JsonProperty("p2pPurchasesList")
	public Integer getP2pPurchasesList() {
		return p2pPurchasesList;
	}

	@JsonProperty("p2pPurchasesList")
	public void setP2pPurchasesList(Integer p2pPurchasesList) {
		this.p2pPurchasesList = p2pPurchasesList;
	}

	@JsonProperty("fundraiserMetadata")
	public Integer getFundraiserMetadata() {
		return fundraiserMetadata;
	}

	@JsonProperty("fundraiserMetadata")
	public void setFundraiserMetadata(Integer fundraiserMetadata) {
		this.fundraiserMetadata = fundraiserMetadata;
	}

	@JsonProperty("teamMetadata")
	public Integer getTeamMetadata() {
		return teamMetadata;
	}

	@JsonProperty("teamMetadata")
	public void setTeamMetadata(Integer teamMetadata) {
		this.teamMetadata = teamMetadata;
	}

	@JsonProperty("activitySubmissionList")
	public Integer getActivitySubmissionList() {
		return activitySubmissionList;
	}

	@JsonProperty("activitySubmissionList")
	public void setActivitySubmissionList(Integer activitySubmissionList) {
		this.activitySubmissionList = activitySubmissionList;
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
		return "Payload [rateLimit=" + rateLimit + ", totalApiCalls=" + totalApiCalls
				+ ", lastApiCallFailure=" + lastApiCallFailure + ", totalApiCallFailures=" + totalApiCallFailures
				+ "activityFormTypeList=" + activityFormTypeList
				+ ", activityFormList=" + activityFormList + ", activityFormMetadata=" + activityFormMetadata
				+ ", activityFormSummary=" + activityFormSummary + ", blastList=" + blastList
				+ ", targetedActionTargetsList=" + targetedActionTargetsList + ", eventAttendeesList="
				+ eventAttendeesList + ", p2pRegistrationsList=" + p2pRegistrationsList + ", p2pFundraisersSummaryList="
				+ p2pFundraisersSummaryList + ", p2pTeamsList=" + p2pTeamsList + ", p2pPurchasesList="
				+ p2pPurchasesList + ", fundraiserMetadata=" + fundraiserMetadata + ", teamMetadata=" + teamMetadata
				+ ", activitySubmissionList=" + activitySubmissionList + ", additionalProperties="
				+ additionalProperties + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activityFormList == null) ? 0 : activityFormList.hashCode());
		result = prime * result + ((activityFormMetadata == null) ? 0 : activityFormMetadata.hashCode());
		result = prime * result + ((activityFormSummary == null) ? 0 : activityFormSummary.hashCode());
		result = prime * result + ((activityFormTypeList == null) ? 0 : activityFormTypeList.hashCode());
		result = prime * result + ((activitySubmissionList == null) ? 0 : activitySubmissionList.hashCode());
		result = prime * result + ((additionalProperties == null) ? 0 : additionalProperties.hashCode());
		result = prime * result + ((blastList == null) ? 0 : blastList.hashCode());
		result = prime * result + ((eventAttendeesList == null) ? 0 : eventAttendeesList.hashCode());
		result = prime * result + ((fundraiserMetadata == null) ? 0 : fundraiserMetadata.hashCode());
		result = prime * result + ((lastApiCallFailure == null) ? 0 : lastApiCallFailure.hashCode());
		result = prime * result + ((p2pFundraisersSummaryList == null) ? 0 : p2pFundraisersSummaryList.hashCode());
		result = prime * result + ((p2pPurchasesList == null) ? 0 : p2pPurchasesList.hashCode());
		result = prime * result + ((p2pRegistrationsList == null) ? 0 : p2pRegistrationsList.hashCode());
		result = prime * result + ((p2pTeamsList == null) ? 0 : p2pTeamsList.hashCode());
		result = prime * result + ((rateLimit == null) ? 0 : rateLimit.hashCode());
		result = prime * result + ((targetedActionTargetsList == null) ? 0 : targetedActionTargetsList.hashCode());
		result = prime * result + ((teamMetadata == null) ? 0 : teamMetadata.hashCode());
		result = prime * result + ((totalApiCallFailures == null) ? 0 : totalApiCallFailures.hashCode());
		result = prime * result + ((totalApiCalls == null) ? 0 : totalApiCalls.hashCode());
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
		Payload other = (Payload) obj;
		if (activityFormList == null) {
			if (other.activityFormList != null)
				return false;
		} else if (!activityFormList.equals(other.activityFormList))
			return false;
		if (activityFormMetadata == null) {
			if (other.activityFormMetadata != null)
				return false;
		} else if (!activityFormMetadata.equals(other.activityFormMetadata))
			return false;
		if (activityFormSummary == null) {
			if (other.activityFormSummary != null)
				return false;
		} else if (!activityFormSummary.equals(other.activityFormSummary))
			return false;
		if (activityFormTypeList == null) {
			if (other.activityFormTypeList != null)
				return false;
		} else if (!activityFormTypeList.equals(other.activityFormTypeList))
			return false;
		if (activitySubmissionList == null) {
			if (other.activitySubmissionList != null)
				return false;
		} else if (!activitySubmissionList.equals(other.activitySubmissionList))
			return false;
		if (additionalProperties == null) {
			if (other.additionalProperties != null)
				return false;
		} else if (!additionalProperties.equals(other.additionalProperties))
			return false;
		if (blastList == null) {
			if (other.blastList != null)
				return false;
		} else if (!blastList.equals(other.blastList))
			return false;
		if (eventAttendeesList == null) {
			if (other.eventAttendeesList != null)
				return false;
		} else if (!eventAttendeesList.equals(other.eventAttendeesList))
			return false;
		if (fundraiserMetadata == null) {
			if (other.fundraiserMetadata != null)
				return false;
		} else if (!fundraiserMetadata.equals(other.fundraiserMetadata))
			return false;
		if (lastApiCallFailure == null) {
			if (other.lastApiCallFailure != null)
				return false;
		} else if (!lastApiCallFailure.equals(other.lastApiCallFailure))
			return false;
		if (p2pFundraisersSummaryList == null) {
			if (other.p2pFundraisersSummaryList != null)
				return false;
		} else if (!p2pFundraisersSummaryList.equals(other.p2pFundraisersSummaryList))
			return false;
		if (p2pPurchasesList == null) {
			if (other.p2pPurchasesList != null)
				return false;
		} else if (!p2pPurchasesList.equals(other.p2pPurchasesList))
			return false;
		if (p2pRegistrationsList == null) {
			if (other.p2pRegistrationsList != null)
				return false;
		} else if (!p2pRegistrationsList.equals(other.p2pRegistrationsList))
			return false;
		if (p2pTeamsList == null) {
			if (other.p2pTeamsList != null)
				return false;
		} else if (!p2pTeamsList.equals(other.p2pTeamsList))
			return false;
		if (rateLimit == null) {
			if (other.rateLimit != null)
				return false;
		} else if (!rateLimit.equals(other.rateLimit))
			return false;
		if (targetedActionTargetsList == null) {
			if (other.targetedActionTargetsList != null)
				return false;
		} else if (!targetedActionTargetsList.equals(other.targetedActionTargetsList))
			return false;
		if (teamMetadata == null) {
			if (other.teamMetadata != null)
				return false;
		} else if (!teamMetadata.equals(other.teamMetadata))
			return false;
		if (totalApiCallFailures == null) {
			if (other.totalApiCallFailures != null)
				return false;
		} else if (!totalApiCallFailures.equals(other.totalApiCallFailures))
			return false;
		if (totalApiCalls == null) {
			if (other.totalApiCalls != null)
				return false;
		} else if (!totalApiCalls.equals(other.totalApiCalls))
			return false;
		return true;
	}

	
	
	
}