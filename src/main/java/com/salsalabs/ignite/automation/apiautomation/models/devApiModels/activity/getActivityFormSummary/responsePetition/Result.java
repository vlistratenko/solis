package com.salsalabs.ignite.automation.apiautomation.models.devApiModels.activity.getActivityFormSummary.responsePetition;

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
@JsonPropertyOrder({ "id", "firstName", "middleName", "lastName", "email", "homePhone", "cellPhone", "workPhone",
		"city", "addressLine1", "addressLine2", "state", "stateCode", "zipCode", "country", "countryCode",
		"supporterId", "dateOfBirth", "gender", "preferredLanguage", "suffix", "title", "activityDate", "referringUrl",
		"customFields", "activityType", "confirmationCheckbox", "keepInformed", "signatureDate", "comment",
		"displayComment", "commentModerationState", "displaySignature" })
public class Result {

	@JsonProperty("id")
	private String id;
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
	@JsonProperty("gender")
	private String gender;
	@JsonProperty("preferredLanguage")
	private String preferredLanguage;
	@JsonProperty("suffix")
	private String suffix;
	@JsonProperty("title")
	private String title;
	@JsonProperty("activityDate")
	private String activityDate;
	@JsonProperty("referringUrl")
	private String referringUrl;
	@JsonProperty("customFields")
	private List<CustomField> customFields = null;
	@JsonProperty("activityType")
	private String activityType;
	@JsonProperty("confirmationCheckbox")
	private Boolean confirmationCheckbox;
	@JsonProperty("keepInformed")
	private Boolean keepInformed;
	@JsonProperty("signatureDate")
	private String signatureDate;
	@JsonProperty("comment")
	private String comment;
	@JsonProperty("displayComment")
	private Boolean displayComment;
	@JsonProperty("commentModerationState")
	private String commentModerationState;
	@JsonProperty("displaySignature")
	private Boolean displaySignature;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

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

	@JsonProperty("gender")
	public String getGender() {
		return gender;
	}

	@JsonProperty("gender")
	public void setGender(String gender) {
		this.gender = gender;
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

	@JsonProperty("activityDate")
	public String getActivityDate() {
		return activityDate;
	}

	@JsonProperty("activityDate")
	public void setActivityDate(String activityDate) {
		this.activityDate = activityDate;
	}

	@JsonProperty("referringUrl")
	public String getReferringUrl() {
		return referringUrl;
	}

	@JsonProperty("referringUrl")
	public void setReferringUrl(String referringUrl) {
		this.referringUrl = referringUrl;
	}

	@JsonProperty("customFields")
	public List<CustomField> getCustomFields() {
		return customFields;
	}

	@JsonProperty("customFields")
	public void setCustomFields(List<CustomField> customFields) {
		this.customFields = customFields;
	}

	@JsonProperty("activityType")
	public String getActivityType() {
		return activityType;
	}

	@JsonProperty("activityType")
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	@JsonProperty("confirmationCheckbox")
	public Boolean getConfirmationCheckbox() {
		return confirmationCheckbox;
	}

	@JsonProperty("confirmationCheckbox")
	public void setConfirmationCheckbox(Boolean confirmationCheckbox) {
		this.confirmationCheckbox = confirmationCheckbox;
	}

	@JsonProperty("keepInformed")
	public Boolean getKeepInformed() {
		return keepInformed;
	}

	@JsonProperty("keepInformed")
	public void setKeepInformed(Boolean keepInformed) {
		this.keepInformed = keepInformed;
	}

	@JsonProperty("signatureDate")
	public String getSignatureDate() {
		return signatureDate;
	}

	@JsonProperty("signatureDate")
	public void setSignatureDate(String signatureDate) {
		this.signatureDate = signatureDate;
	}

	@JsonProperty("comment")
	public String getComment() {
		return comment;
	}

	@JsonProperty("comment")
	public void setComment(String comment) {
		this.comment = comment;
	}

	@JsonProperty("displayComment")
	public Boolean getDisplayComment() {
		return displayComment;
	}

	@JsonProperty("displayComment")
	public void setDisplayComment(Boolean displayComment) {
		this.displayComment = displayComment;
	}

	@JsonProperty("commentModerationState")
	public String getCommentModerationState() {
		return commentModerationState;
	}

	@JsonProperty("commentModerationState")
	public void setCommentModerationState(String commentModerationState) {
		this.commentModerationState = commentModerationState;
	}

	@JsonProperty("displaySignature")
	public Boolean getDisplaySignature() {
		return displaySignature;
	}

	@JsonProperty("displaySignature")
	public void setDisplaySignature(Boolean displaySignature) {
		this.displaySignature = displaySignature;
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
		return "Result [id=" + id + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
				+ ", email=" + email + ", homePhone=" + homePhone + ", cellPhone=" + cellPhone + ", workPhone="
				+ workPhone + ", city=" + city + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2
				+ ", state=" + state + ", stateCode=" + stateCode + ", zipCode=" + zipCode + ", country=" + country
				+ ", countryCode=" + countryCode + ", supporterId=" + supporterId + ", dateOfBirth=" + dateOfBirth
				+ ", gender=" + gender + ", preferredLanguage=" + preferredLanguage + ", suffix=" + suffix + ", title="
				+ title + ", activityDate=" + activityDate + ", referringUrl=" + referringUrl + ", customFields="
				+ customFields + ", activityType=" + activityType + ", confirmationCheckbox=" + confirmationCheckbox
				+ ", keepInformed=" + keepInformed + ", signatureDate=" + signatureDate + ", comment=" + comment
				+ ", displayComment=" + displayComment + ", commentModerationState=" + commentModerationState
				+ ", displaySignature=" + displaySignature + ", additionalProperties=" + additionalProperties + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activityDate == null) ? 0 : activityDate.hashCode());
		result = prime * result + ((activityType == null) ? 0 : activityType.hashCode());
		result = prime * result + ((additionalProperties == null) ? 0 : additionalProperties.hashCode());
		result = prime * result + ((addressLine1 == null) ? 0 : addressLine1.hashCode());
		result = prime * result + ((addressLine2 == null) ? 0 : addressLine2.hashCode());
		result = prime * result + ((cellPhone == null) ? 0 : cellPhone.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((commentModerationState == null) ? 0 : commentModerationState.hashCode());
		result = prime * result + ((confirmationCheckbox == null) ? 0 : confirmationCheckbox.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((countryCode == null) ? 0 : countryCode.hashCode());
		result = prime * result + ((customFields == null) ? 0 : customFields.hashCode());
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((displayComment == null) ? 0 : displayComment.hashCode());
		result = prime * result + ((displaySignature == null) ? 0 : displaySignature.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((homePhone == null) ? 0 : homePhone.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((keepInformed == null) ? 0 : keepInformed.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((middleName == null) ? 0 : middleName.hashCode());
		result = prime * result + ((preferredLanguage == null) ? 0 : preferredLanguage.hashCode());
		result = prime * result + ((referringUrl == null) ? 0 : referringUrl.hashCode());
		result = prime * result + ((signatureDate == null) ? 0 : signatureDate.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((stateCode == null) ? 0 : stateCode.hashCode());
		result = prime * result + ((suffix == null) ? 0 : suffix.hashCode());
		result = prime * result + ((supporterId == null) ? 0 : supporterId.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		if (activityDate == null) {
			if (other.activityDate != null)
				return false;
		} else if (!activityDate.equals(other.activityDate))
			return false;
		if (activityType == null) {
			if (other.activityType != null)
				return false;
		} else if (!activityType.equals(other.activityType))
			return false;
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
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (commentModerationState == null) {
			if (other.commentModerationState != null)
				return false;
		} else if (!commentModerationState.equals(other.commentModerationState))
			return false;
		if (confirmationCheckbox == null) {
			if (other.confirmationCheckbox != null)
				return false;
		} else if (!confirmationCheckbox.equals(other.confirmationCheckbox))
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
		if (displayComment == null) {
			if (other.displayComment != null)
				return false;
		} else if (!displayComment.equals(other.displayComment))
			return false;
		if (displaySignature == null) {
			if (other.displaySignature != null)
				return false;
		} else if (!displaySignature.equals(other.displaySignature))
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
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (homePhone == null) {
			if (other.homePhone != null)
				return false;
		} else if (!homePhone.equals(other.homePhone))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (keepInformed == null) {
			if (other.keepInformed != null)
				return false;
		} else if (!keepInformed.equals(other.keepInformed))
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
		if (preferredLanguage == null) {
			if (other.preferredLanguage != null)
				return false;
		} else if (!preferredLanguage.equals(other.preferredLanguage))
			return false;
		if (referringUrl == null) {
			if (other.referringUrl != null)
				return false;
		} else if (!referringUrl.equals(other.referringUrl))
			return false;
		if (signatureDate == null) {
			if (other.signatureDate != null)
				return false;
		} else if (!signatureDate.equals(other.signatureDate))
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
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
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