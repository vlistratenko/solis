package com.salsalabs.ignite.automation.apiautomation.models.devApiModels.activity.getActivityFormSummary.responseP2p;

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
@JsonPropertyOrder({ "total", "offset", "results", "type", "registrationsOnly", "donationsOnly",
		"registrationsWithDonations", "raisedTotal", "raisedRegistrations", "raisedDonations", "fundraisers", "teams",
		"raisedPurchases", "count" })
public class Payload {

	@JsonProperty("total")
	private Integer total;
	@JsonProperty("offset")
	private Integer offset;
	@JsonProperty("results")
	private List<Result> results = null;
	@JsonProperty("type")
	private String type;
	@JsonProperty("registrationsOnly")
	private Integer registrationsOnly;
	@JsonProperty("donationsOnly")
	private Integer donationsOnly;
	@JsonProperty("registrationsWithDonations")
	private Integer registrationsWithDonations;
	@JsonProperty("raisedTotal")
	private String raisedTotal;
	@JsonProperty("raisedRegistrations")
	private String raisedRegistrations;
	@JsonProperty("raisedDonations")
	private String raisedDonations;
	@JsonProperty("fundraisers")
	private Integer fundraisers;
	@JsonProperty("teams")
	private Integer teams;
	@JsonProperty("raisedPurchases")
	private String raisedPurchases;
	@JsonProperty("count")
	private Integer count;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("total")
	public Integer getTotal() {
		return total;
	}

	@JsonProperty("total")
	public void setTotal(Integer total) {
		this.total = total;
	}

	@JsonProperty("offset")
	public Integer getOffset() {
		return offset;
	}

	@JsonProperty("offset")
	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	@JsonProperty("results")
	public List<Result> getResults() {
		return results;
	}

	@JsonProperty("results")
	public void setResults(List<Result> results) {
		this.results = results;
	}

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("registrationsOnly")
	public Integer getRegistrationsOnly() {
		return registrationsOnly;
	}

	@JsonProperty("registrationsOnly")
	public void setRegistrationsOnly(Integer registrationsOnly) {
		this.registrationsOnly = registrationsOnly;
	}

	@JsonProperty("donationsOnly")
	public Integer getDonationsOnly() {
		return donationsOnly;
	}

	@JsonProperty("donationsOnly")
	public void setDonationsOnly(Integer donationsOnly) {
		this.donationsOnly = donationsOnly;
	}

	@JsonProperty("registrationsWithDonations")
	public Integer getRegistrationsWithDonations() {
		return registrationsWithDonations;
	}

	@JsonProperty("registrationsWithDonations")
	public void setRegistrationsWithDonations(Integer registrationsWithDonations) {
		this.registrationsWithDonations = registrationsWithDonations;
	}

	@JsonProperty("raisedTotal")
	public String getRaisedTotal() {
		return raisedTotal;
	}

	@JsonProperty("raisedTotal")
	public void setRaisedTotal(String raisedTotal) {
		this.raisedTotal = raisedTotal;
	}

	@JsonProperty("raisedRegistrations")
	public String getRaisedRegistrations() {
		return raisedRegistrations;
	}

	@JsonProperty("raisedRegistrations")
	public void setRaisedRegistrations(String raisedRegistrations) {
		this.raisedRegistrations = raisedRegistrations;
	}

	@JsonProperty("raisedDonations")
	public String getRaisedDonations() {
		return raisedDonations;
	}

	@JsonProperty("raisedDonations")
	public void setRaisedDonations(String raisedDonations) {
		this.raisedDonations = raisedDonations;
	}

	@JsonProperty("fundraisers")
	public Integer getFundraisers() {
		return fundraisers;
	}

	@JsonProperty("fundraisers")
	public void setFundraisers(Integer fundraisers) {
		this.fundraisers = fundraisers;
	}

	@JsonProperty("teams")
	public Integer getTeams() {
		return teams;
	}

	@JsonProperty("teams")
	public void setTeams(Integer teams) {
		this.teams = teams;
	}

	@JsonProperty("raisedPurchases")
	public String getRaisedPurchases() {
		return raisedPurchases;
	}

	@JsonProperty("raisedPurchases")
	public void setRaisedPurchases(String raisedPurchases) {
		this.raisedPurchases = raisedPurchases;
	}

	@JsonProperty("count")
	public Integer getCount() {
		return count;
	}

	@JsonProperty("count")
	public void setCount(Integer count) {
		this.count = count;
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
		return "Payload [total=" + total + ", offset=" + offset + ", results=" + results + ", type=" + type
				+ ", registrationsOnly=" + registrationsOnly + ", donationsOnly=" + donationsOnly
				+ ", registrationsWithDonations=" + registrationsWithDonations + ", raisedTotal=" + raisedTotal
				+ ", raisedRegistrations=" + raisedRegistrations + ", raisedDonations=" + raisedDonations
				+ ", fundraisers=" + fundraisers + ", teams=" + teams + ", raisedPurchases=" + raisedPurchases
				+ ", count=" + count + ", additionalProperties=" + additionalProperties + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((additionalProperties == null) ? 0 : additionalProperties.hashCode());
		result = prime * result + ((count == null) ? 0 : count.hashCode());
		result = prime * result + ((donationsOnly == null) ? 0 : donationsOnly.hashCode());
		result = prime * result + ((fundraisers == null) ? 0 : fundraisers.hashCode());
		result = prime * result + ((offset == null) ? 0 : offset.hashCode());
		result = prime * result + ((raisedDonations == null) ? 0 : raisedDonations.hashCode());
		result = prime * result + ((raisedPurchases == null) ? 0 : raisedPurchases.hashCode());
		result = prime * result + ((raisedRegistrations == null) ? 0 : raisedRegistrations.hashCode());
		result = prime * result + ((raisedTotal == null) ? 0 : raisedTotal.hashCode());
		result = prime * result + ((registrationsOnly == null) ? 0 : registrationsOnly.hashCode());
		result = prime * result + ((registrationsWithDonations == null) ? 0 : registrationsWithDonations.hashCode());
		result = prime * result + ((results == null) ? 0 : results.hashCode());
		result = prime * result + ((teams == null) ? 0 : teams.hashCode());
		result = prime * result + ((total == null) ? 0 : total.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		if (additionalProperties == null) {
			if (other.additionalProperties != null)
				return false;
		} else if (!additionalProperties.equals(other.additionalProperties))
			return false;
		if (count == null) {
			if (other.count != null)
				return false;
		} else if (!count.equals(other.count))
			return false;
		if (donationsOnly == null) {
			if (other.donationsOnly != null)
				return false;
		} else if (!donationsOnly.equals(other.donationsOnly))
			return false;
		if (fundraisers == null) {
			if (other.fundraisers != null)
				return false;
		} else if (!fundraisers.equals(other.fundraisers))
			return false;
		if (offset == null) {
			if (other.offset != null)
				return false;
		} else if (!offset.equals(other.offset))
			return false;
		if (raisedDonations == null) {
			if (other.raisedDonations != null)
				return false;
		} else if (!raisedDonations.equals(other.raisedDonations))
			return false;
		if (raisedPurchases == null) {
			if (other.raisedPurchases != null)
				return false;
		} else if (!raisedPurchases.equals(other.raisedPurchases))
			return false;
		if (raisedRegistrations == null) {
			if (other.raisedRegistrations != null)
				return false;
		} else if (!raisedRegistrations.equals(other.raisedRegistrations))
			return false;
		if (raisedTotal == null) {
			if (other.raisedTotal != null)
				return false;
		} else if (!raisedTotal.equals(other.raisedTotal))
			return false;
		if (registrationsOnly == null) {
			if (other.registrationsOnly != null)
				return false;
		} else if (!registrationsOnly.equals(other.registrationsOnly))
			return false;
		if (registrationsWithDonations == null) {
			if (other.registrationsWithDonations != null)
				return false;
		} else if (!registrationsWithDonations.equals(other.registrationsWithDonations))
			return false;
		if (results == null) {
			if (other.results != null)
				return false;
		} else if (!results.equals(other.results))
			return false;
		if (teams == null) {
			if (other.teams != null)
				return false;
		} else if (!teams.equals(other.teams))
			return false;
		if (total == null) {
			if (other.total != null)
				return false;
		} else if (!total.equals(other.total))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	

}