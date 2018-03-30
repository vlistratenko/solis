package com.salsalabs.ignite.automation.apiautomation.models.devApiModels.activity.getActivityFormSummary.responseFundraiserMetadataAndSummary;

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
@JsonPropertyOrder({ "firstName", "middleName", "lastName", "email", "homePhone", "cellPhone", "workPhone", "city",
		"addressLine1", "addressLine2", "state", "stateCode", "zipCode", "country", "countryCode", "supporterId",
		"dateOfBirth", "preferredLanguage", "suffix", "title", "customFields", "fundraiserPageName", "type", "enabled",
		"fundraiserUrl", "goal", "totalDonationsAmount", "totalDonationsCount", "lastTransactionDate", "teamId",
		"teamName", "fundraiserId", "teamCaptain" })
public class Payload {

	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("middleName")
	private String middleName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("email")
	private String email;
	@JsonProperty("homePhone")
	private String homePhone;
	@JsonProperty("cellPhone")
	private String cellPhone;
	@JsonProperty("workPhone")
	private String workPhone;
	@JsonProperty("city")
	private String city;
	@JsonProperty("addressLine1")
	private String addressLine1;
	@JsonProperty("addressLine2")
	private String addressLine2;
	@JsonProperty("state")
	private String state;
	@JsonProperty("stateCode")
	private String stateCode;
	@JsonProperty("zipCode")
	private String zipCode;
	@JsonProperty("country")
	private String country;
	@JsonProperty("countryCode")
	private String countryCode;
	@JsonProperty("supporterId")
	private String supporterId;
	@JsonProperty("dateOfBirth")
	private String dateOfBirth;
	@JsonProperty("preferredLanguage")
	private String preferredLanguage;
	@JsonProperty("suffix")
	private String suffix;
	@JsonProperty("title")
	private String title;
	@JsonProperty("customFields")
	private List<CustomField> customFields = null;
	@JsonProperty("fundraiserPageName")
	private String fundraiserPageName;
	@JsonProperty("type")
	private String type;
	@JsonProperty("enabled")
	private Boolean enabled;
	@JsonProperty("fundraiserUrl")
	private String fundraiserUrl;
	@JsonProperty("goal")
	private Double goal;
	@JsonProperty("totalDonationsAmount")
	private Double totalDonationsAmount;
	@JsonProperty("totalDonationsCount")
	private Integer totalDonationsCount;
	@JsonProperty("lastTransactionDate")
	private String lastTransactionDate;
	@JsonProperty("teamId")
	private String teamId;
	@JsonProperty("teamName")
	private String teamName;
	@JsonProperty("fundraiserId")
	private String fundraiserId;
	@JsonProperty("teamCaptain")
	private Boolean teamCaptain;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

	@JsonProperty("email")
	public String getEmail() {
		return email;
	}

	@JsonProperty("email")
	public void setEmail(String email) {
		this.email = email;
	}

	@JsonProperty("homePhone")
	public String getHomePhone() {
		return homePhone;
	}

	@JsonProperty("homePhone")
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	@JsonProperty("cellPhone")
	public String getCellPhone() {
		return cellPhone;
	}

	@JsonProperty("cellPhone")
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	@JsonProperty("workPhone")
	public String getWorkPhone() {
		return workPhone;
	}

	@JsonProperty("workPhone")
	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	@JsonProperty("city")
	public String getCity() {
		return city;
	}

	@JsonProperty("city")
	public void setCity(String city) {
		this.city = city;
	}

	@JsonProperty("addressLine1")
	public String getAddressLine1() {
		return addressLine1;
	}

	@JsonProperty("addressLine1")
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	@JsonProperty("addressLine2")
	public String getAddressLine2() {
		return addressLine2;
	}

	@JsonProperty("addressLine2")
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	@JsonProperty("state")
	public String getState() {
		return state;
	}

	@JsonProperty("state")
	public void setState(String state) {
		this.state = state;
	}

	@JsonProperty("stateCode")
	public String getStateCode() {
		return stateCode;
	}

	@JsonProperty("stateCode")
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	@JsonProperty("zipCode")
	public String getZipCode() {
		return zipCode;
	}

	@JsonProperty("zipCode")
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@JsonProperty("country")
	public String getCountry() {
		return country;
	}

	@JsonProperty("country")
	public void setCountry(String country) {
		this.country = country;
	}

	@JsonProperty("countryCode")
	public String getCountryCode() {
		return countryCode;
	}

	@JsonProperty("countryCode")
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@JsonProperty("supporterId")
	public String getSupporterId() {
		return supporterId;
	}

	@JsonProperty("supporterId")
	public void setSupporterId(String supporterId) {
		this.supporterId = supporterId;
	}

	@JsonProperty("dateOfBirth")
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	@JsonProperty("dateOfBirth")
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@JsonProperty("preferredLanguage")
	public String getPreferredLanguage() {
		return preferredLanguage;
	}

	@JsonProperty("preferredLanguage")
	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}

	@JsonProperty("suffix")
	public String getSuffix() {
		return suffix;
	}

	@JsonProperty("suffix")
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	@JsonProperty("title")
	public String getTitle() {
		return title;
	}

	@JsonProperty("title")
	public void setTitle(String title) {
		this.title = title;
	}

	@JsonProperty("customFields")
	public List<CustomField> getCustomFields() {
		return customFields;
	}

	@JsonProperty("customFields")
	public void setCustomFields(List<CustomField> customFields) {
		this.customFields = customFields;
	}

	@JsonProperty("fundraiserPageName")
	public String getFundraiserPageName() {
		return fundraiserPageName;
	}

	@JsonProperty("fundraiserPageName")
	public void setFundraiserPageName(String fundraiserPageName) {
		this.fundraiserPageName = fundraiserPageName;
	}

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("enabled")
	public Boolean getEnabled() {
		return enabled;
	}

	@JsonProperty("enabled")
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@JsonProperty("fundraiserUrl")
	public String getFundraiserUrl() {
		return fundraiserUrl;
	}

	@JsonProperty("fundraiserUrl")
	public void setFundraiserUrl(String fundraiserUrl) {
		this.fundraiserUrl = fundraiserUrl;
	}

	@JsonProperty("goal")
	public Double getGoal() {
		return goal;
	}

	@JsonProperty("goal")
	public void setGoal(Double goal) {
		this.goal = goal;
	}

	@JsonProperty("totalDonationsAmount")
	public Double getTotalDonationsAmount() {
		return totalDonationsAmount;
	}

	@JsonProperty("totalDonationsAmount")
	public void setTotalDonationsAmount(Double totalDonationsAmount) {
		this.totalDonationsAmount = totalDonationsAmount;
	}

	@JsonProperty("totalDonationsCount")
	public Integer getTotalDonationsCount() {
		return totalDonationsCount;
	}

	@JsonProperty("totalDonationsCount")
	public void setTotalDonationsCount(Integer totalDonationsCount) {
		this.totalDonationsCount = totalDonationsCount;
	}

	@JsonProperty("lastTransactionDate")
	public String getLastTransactionDate() {
		return lastTransactionDate;
	}

	@JsonProperty("lastTransactionDate")
	public void setLastTransactionDate(String lastTransactionDate) {
		this.lastTransactionDate = lastTransactionDate;
	}

	@JsonProperty("teamId")
	public String getTeamId() {
		return teamId;
	}

	@JsonProperty("teamId")
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	@JsonProperty("teamName")
	public String getTeamName() {
		return teamName;
	}

	@JsonProperty("teamName")
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	@JsonProperty("fundraiserId")
	public String getFundraiserId() {
		return fundraiserId;
	}

	@JsonProperty("fundraiserId")
	public void setFundraiserId(String fundraiserId) {
		this.fundraiserId = fundraiserId;
	}

	@JsonProperty("teamCaptain")
	public Boolean getTeamCaptain() {
		return teamCaptain;
	}

	@JsonProperty("teamCaptain")
	public void setTeamCaptain(Boolean teamCaptain) {
		this.teamCaptain = teamCaptain;
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
		return "Payload [firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName + ", email="
				+ email + ", homePhone=" + homePhone + ", cellPhone=" + cellPhone + ", workPhone=" + workPhone
				+ ", city=" + city + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", state="
				+ state + ", stateCode=" + stateCode + ", zipCode=" + zipCode + ", country=" + country
				+ ", countryCode=" + countryCode + ", supporterId=" + supporterId + ", dateOfBirth=" + dateOfBirth
				+ ", preferredLanguage=" + preferredLanguage + ", suffix=" + suffix + ", title=" + title
				+ ", customFields=" + customFields + ", fundraiserPageName=" + fundraiserPageName + ", type=" + type
				+ ", enabled=" + enabled + ", fundraiserUrl=" + fundraiserUrl + ", goal=" + goal
				+ ", totalDonationsAmount=" + totalDonationsAmount + ", totalDonationsCount=" + totalDonationsCount
				+ ", lastTransactionDate=" + lastTransactionDate + ", teamId=" + teamId + ", teamName=" + teamName
				+ ", fundraiserId=" + fundraiserId + ", teamCaptain=" + teamCaptain + ", additionalProperties="
				+ additionalProperties + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((additionalProperties == null) ? 0 : additionalProperties.hashCode());
		result = prime * result + ((addressLine1 == null) ? 0 : addressLine1.hashCode());
		result = prime * result + ((addressLine2 == null) ? 0 : addressLine2.hashCode());
		result = prime * result + ((cellPhone == null) ? 0 : cellPhone.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((countryCode == null) ? 0 : countryCode.hashCode());
		result = prime * result + ((customFields == null) ? 0 : customFields.hashCode());
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((fundraiserId == null) ? 0 : fundraiserId.hashCode());
		result = prime * result + ((fundraiserPageName == null) ? 0 : fundraiserPageName.hashCode());
		result = prime * result + ((fundraiserUrl == null) ? 0 : fundraiserUrl.hashCode());
		result = prime * result + ((goal == null) ? 0 : goal.hashCode());
		result = prime * result + ((homePhone == null) ? 0 : homePhone.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((lastTransactionDate == null) ? 0 : lastTransactionDate.hashCode());
		result = prime * result + ((middleName == null) ? 0 : middleName.hashCode());
		result = prime * result + ((preferredLanguage == null) ? 0 : preferredLanguage.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((stateCode == null) ? 0 : stateCode.hashCode());
		result = prime * result + ((suffix == null) ? 0 : suffix.hashCode());
		result = prime * result + ((supporterId == null) ? 0 : supporterId.hashCode());
		result = prime * result + ((teamCaptain == null) ? 0 : teamCaptain.hashCode());
		result = prime * result + ((teamId == null) ? 0 : teamId.hashCode());
		result = prime * result + ((teamName == null) ? 0 : teamName.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((totalDonationsAmount == null) ? 0 : totalDonationsAmount.hashCode());
		result = prime * result + ((totalDonationsCount == null) ? 0 : totalDonationsCount.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((workPhone == null) ? 0 : workPhone.hashCode());
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
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
		if (addressLine1 == null) {
			if (other.addressLine1 != null)
				return false;
		} else if (!addressLine1.equals(other.addressLine1))
			return false;
		if (addressLine2 == null) {
			if (other.addressLine2 != null)
				return false;
		} else if (!addressLine2.equals(other.addressLine2))
			return false;
		if (cellPhone == null) {
			if (other.cellPhone != null)
				return false;
		} else if (!cellPhone.equals(other.cellPhone))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (countryCode == null) {
			if (other.countryCode != null)
				return false;
		} else if (!countryCode.equals(other.countryCode))
			return false;
		if (customFields == null) {
			if (other.customFields != null)
				return false;
		} else if (!customFields.equals(other.customFields))
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (enabled == null) {
			if (other.enabled != null)
				return false;
		} else if (!enabled.equals(other.enabled))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (fundraiserId == null) {
			if (other.fundraiserId != null)
				return false;
		} else if (!fundraiserId.equals(other.fundraiserId))
			return false;
		if (fundraiserPageName == null) {
			if (other.fundraiserPageName != null)
				return false;
		} else if (!fundraiserPageName.equals(other.fundraiserPageName))
			return false;
		if (fundraiserUrl == null) {
			if (other.fundraiserUrl != null)
				return false;
		} else if (!fundraiserUrl.equals(other.fundraiserUrl))
			return false;
		if (goal == null) {
			if (other.goal != null)
				return false;
		} else if (!goal.equals(other.goal))
			return false;
		if (homePhone == null) {
			if (other.homePhone != null)
				return false;
		} else if (!homePhone.equals(other.homePhone))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (lastTransactionDate == null) {
			if (other.lastTransactionDate != null)
				return false;
		} else if (!lastTransactionDate.equals(other.lastTransactionDate))
			return false;
		if (middleName == null) {
			if (other.middleName != null)
				return false;
		} else if (!middleName.equals(other.middleName))
			return false;
		if (preferredLanguage == null) {
			if (other.preferredLanguage != null)
				return false;
		} else if (!preferredLanguage.equals(other.preferredLanguage))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (stateCode == null) {
			if (other.stateCode != null)
				return false;
		} else if (!stateCode.equals(other.stateCode))
			return false;
		if (suffix == null) {
			if (other.suffix != null)
				return false;
		} else if (!suffix.equals(other.suffix))
			return false;
		if (supporterId == null) {
			if (other.supporterId != null)
				return false;
		} else if (!supporterId.equals(other.supporterId))
			return false;
		if (teamCaptain == null) {
			if (other.teamCaptain != null)
				return false;
		} else if (!teamCaptain.equals(other.teamCaptain))
			return false;
		if (teamId == null) {
			if (other.teamId != null)
				return false;
		} else if (!teamId.equals(other.teamId))
			return false;
		if (teamName == null) {
			if (other.teamName != null)
				return false;
		} else if (!teamName.equals(other.teamName))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (totalDonationsAmount == null) {
			if (other.totalDonationsAmount != null)
				return false;
		} else if (!totalDonationsAmount.equals(other.totalDonationsAmount))
			return false;
		if (totalDonationsCount == null) {
			if (other.totalDonationsCount != null)
				return false;
		} else if (!totalDonationsCount.equals(other.totalDonationsCount))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (workPhone == null) {
			if (other.workPhone != null)
				return false;
		} else if (!workPhone.equals(other.workPhone))
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}
	
	

}