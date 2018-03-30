package com.salsalabs.ignite.automation.apiautomation.models.devApiModels.activity.getActivityFormSummary.responseRegistrations;

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
		"dateOfBirth", "preferredLanguage", "suffix", "title", "ticketName", "purchasedBy", "status",
		"registrationDate", "questions", "purchaseType", "fundraiserAvailable", "fundraiserCreated",
		"fundraiserPageName", "fundraiserGoal", "totalDonationsAmount", "totalDonationsCount", "optionalPurchases",
		"teamCaptain", "teamName" })
public class Result {

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
	@JsonProperty("ticketName")
	private String ticketName;
	@JsonProperty("purchasedBy")
	private String purchasedBy;
	@JsonProperty("status")
	private String status;
	@JsonProperty("registrationDate")
	private String registrationDate;
	@JsonProperty("questions")
	private List<Question> questions = null;
	@JsonProperty("purchaseType")
	private String purchaseType;
	@JsonProperty("fundraiserAvailable")
	private Boolean fundraiserAvailable;
	@JsonProperty("fundraiserCreated")
	private String fundraiserCreated;
	@JsonProperty("fundraiserPageName")
	private String fundraiserPageName;
	@JsonProperty("fundraiserGoal")
	private String fundraiserGoal;
	@JsonProperty("totalDonationsAmount")
	private String totalDonationsAmount;
	@JsonProperty("totalDonationsCount")
	private String totalDonationsCount;
	@JsonProperty("optionalPurchases")
	private Boolean optionalPurchases;
	@JsonProperty("teamCaptain")
	private Boolean teamCaptain;
	@JsonProperty("teamName")
	private String teamName;
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

	@JsonProperty("ticketName")
	public String getTicketName() {
		return ticketName;
	}

	@JsonProperty("ticketName")
	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}

	@JsonProperty("purchasedBy")
	public String getPurchasedBy() {
		return purchasedBy;
	}

	@JsonProperty("purchasedBy")
	public void setPurchasedBy(String purchasedBy) {
		this.purchasedBy = purchasedBy;
	}

	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
	}

	@JsonProperty("registrationDate")
	public String getRegistrationDate() {
		return registrationDate;
	}

	@JsonProperty("registrationDate")
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	@JsonProperty("questions")
	public List<Question> getQuestions() {
		return questions;
	}

	@JsonProperty("questions")
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	@JsonProperty("purchaseType")
	public String getPurchaseType() {
		return purchaseType;
	}

	@JsonProperty("purchaseType")
	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}

	@JsonProperty("fundraiserAvailable")
	public Boolean getFundraiserAvailable() {
		return fundraiserAvailable;
	}

	@JsonProperty("fundraiserAvailable")
	public void setFundraiserAvailable(Boolean fundraiserAvailable) {
		this.fundraiserAvailable = fundraiserAvailable;
	}

	@JsonProperty("fundraiserCreated")
	public String getFundraiserCreated() {
		return fundraiserCreated;
	}

	@JsonProperty("fundraiserCreated")
	public void setFundraiserCreated(String fundraiserCreated) {
		this.fundraiserCreated = fundraiserCreated;
	}

	@JsonProperty("fundraiserPageName")
	public String getFundraiserPageName() {
		return fundraiserPageName;
	}

	@JsonProperty("fundraiserPageName")
	public void setFundraiserPageName(String fundraiserPageName) {
		this.fundraiserPageName = fundraiserPageName;
	}

	@JsonProperty("fundraiserGoal")
	public String getFundraiserGoal() {
		return fundraiserGoal;
	}

	@JsonProperty("fundraiserGoal")
	public void setFundraiserGoal(String fundraiserGoal) {
		this.fundraiserGoal = fundraiserGoal;
	}

	@JsonProperty("totalDonationsAmount")
	public String getTotalDonationsAmount() {
		return totalDonationsAmount;
	}

	@JsonProperty("totalDonationsAmount")
	public void setTotalDonationsAmount(String totalDonationsAmount) {
		this.totalDonationsAmount = totalDonationsAmount;
	}

	@JsonProperty("totalDonationsCount")
	public String getTotalDonationsCount() {
		return totalDonationsCount;
	}

	@JsonProperty("totalDonationsCount")
	public void setTotalDonationsCount(String totalDonationsCount) {
		this.totalDonationsCount = totalDonationsCount;
	}

	@JsonProperty("optionalPurchases")
	public Boolean getOptionalPurchases() {
		return optionalPurchases;
	}

	@JsonProperty("optionalPurchases")
	public void setOptionalPurchases(Boolean optionalPurchases) {
		this.optionalPurchases = optionalPurchases;
	}

	@JsonProperty("teamCaptain")
	public Boolean getTeamCaptain() {
		return teamCaptain;
	}

	@JsonProperty("teamCaptain")
	public void setTeamCaptain(Boolean teamCaptain) {
		this.teamCaptain = teamCaptain;
	}

	@JsonProperty("teamName")
	public String getTeamName() {
		return teamName;
	}

	@JsonProperty("teamName")
	public void setTeamName(String teamName) {
		this.teamName = teamName;
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
		return "Result [firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName + ", email="
				+ email + ", homePhone=" + homePhone + ", cellPhone=" + cellPhone + ", workPhone=" + workPhone
				+ ", city=" + city + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", state="
				+ state + ", stateCode=" + stateCode + ", zipCode=" + zipCode + ", country=" + country
				+ ", countryCode=" + countryCode + ", supporterId=" + supporterId + ", dateOfBirth=" + dateOfBirth
				+ ", preferredLanguage=" + preferredLanguage + ", suffix=" + suffix + ", title=" + title
				+ ", ticketName=" + ticketName + ", purchasedBy=" + purchasedBy + ", status=" + status
				+ ", registrationDate=" + registrationDate + ", questions=" + questions + ", purchaseType="
				+ purchaseType + ", fundraiserAvailable=" + fundraiserAvailable + ", fundraiserCreated="
				+ fundraiserCreated + ", fundraiserPageName=" + fundraiserPageName + ", fundraiserGoal="
				+ fundraiserGoal + ", totalDonationsAmount=" + totalDonationsAmount + ", totalDonationsCount="
				+ totalDonationsCount + ", optionalPurchases=" + optionalPurchases + ", teamCaptain=" + teamCaptain
				+ ", teamName=" + teamName + ", additionalProperties=" + additionalProperties + "]";
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
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((fundraiserAvailable == null) ? 0 : fundraiserAvailable.hashCode());
		result = prime * result + ((fundraiserCreated == null) ? 0 : fundraiserCreated.hashCode());
		result = prime * result + ((fundraiserGoal == null) ? 0 : fundraiserGoal.hashCode());
		result = prime * result + ((fundraiserPageName == null) ? 0 : fundraiserPageName.hashCode());
		result = prime * result + ((homePhone == null) ? 0 : homePhone.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((middleName == null) ? 0 : middleName.hashCode());
		result = prime * result + ((optionalPurchases == null) ? 0 : optionalPurchases.hashCode());
		result = prime * result + ((preferredLanguage == null) ? 0 : preferredLanguage.hashCode());
		result = prime * result + ((purchaseType == null) ? 0 : purchaseType.hashCode());
		result = prime * result + ((purchasedBy == null) ? 0 : purchasedBy.hashCode());
		result = prime * result + ((questions == null) ? 0 : questions.hashCode());
		result = prime * result + ((registrationDate == null) ? 0 : registrationDate.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((stateCode == null) ? 0 : stateCode.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((suffix == null) ? 0 : suffix.hashCode());
		result = prime * result + ((supporterId == null) ? 0 : supporterId.hashCode());
		result = prime * result + ((teamCaptain == null) ? 0 : teamCaptain.hashCode());
		result = prime * result + ((teamName == null) ? 0 : teamName.hashCode());
		result = prime * result + ((ticketName == null) ? 0 : ticketName.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((totalDonationsAmount == null) ? 0 : totalDonationsAmount.hashCode());
		result = prime * result + ((totalDonationsCount == null) ? 0 : totalDonationsCount.hashCode());
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
		Result other = (Result) obj;
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
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (fundraiserAvailable == null) {
			if (other.fundraiserAvailable != null)
				return false;
		} else if (!fundraiserAvailable.equals(other.fundraiserAvailable))
			return false;
		if (fundraiserCreated == null) {
			if (other.fundraiserCreated != null)
				return false;
		} else if (!fundraiserCreated.equals(other.fundraiserCreated))
			return false;
		if (fundraiserGoal == null) {
			if (other.fundraiserGoal != null)
				return false;
		} else if (!fundraiserGoal.equals(other.fundraiserGoal))
			return false;
		if (fundraiserPageName == null) {
			if (other.fundraiserPageName != null)
				return false;
		} else if (!fundraiserPageName.equals(other.fundraiserPageName))
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
		if (middleName == null) {
			if (other.middleName != null)
				return false;
		} else if (!middleName.equals(other.middleName))
			return false;
		if (optionalPurchases == null) {
			if (other.optionalPurchases != null)
				return false;
		} else if (!optionalPurchases.equals(other.optionalPurchases))
			return false;
		if (preferredLanguage == null) {
			if (other.preferredLanguage != null)
				return false;
		} else if (!preferredLanguage.equals(other.preferredLanguage))
			return false;
		if (purchaseType == null) {
			if (other.purchaseType != null)
				return false;
		} else if (!purchaseType.equals(other.purchaseType))
			return false;
		if (purchasedBy == null) {
			if (other.purchasedBy != null)
				return false;
		} else if (!purchasedBy.equals(other.purchasedBy))
			return false;
		if (questions == null) {
			if (other.questions != null)
				return false;
		} else if (!questions.equals(other.questions))
			return false;
		if (registrationDate == null) {
			if (other.registrationDate != null)
				return false;
		} else if (!registrationDate.equals(other.registrationDate))
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
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
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
		if (teamName == null) {
			if (other.teamName != null)
				return false;
		} else if (!teamName.equals(other.teamName))
			return false;
		if (ticketName == null) {
			if (other.ticketName != null)
				return false;
		} else if (!ticketName.equals(other.ticketName))
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