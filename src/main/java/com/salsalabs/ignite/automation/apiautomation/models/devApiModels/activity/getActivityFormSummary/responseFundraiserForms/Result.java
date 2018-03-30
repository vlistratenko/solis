package com.salsalabs.ignite.automation.apiautomation.models.devApiModels.activity.getActivityFormSummary.responseFundraiserForms;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "firstName", "lastName", "email", "city", "addressLine1", "state", "stateCode", "zipCode",
		"country", "countryCode", "supporterId", "referringUrl", "activityType", "confirmationCheckbox", "keepInformed",
		"donationId", "donationAmount", "donationType", "donationCreatedDate", "designation", "dedicationType",
		"dedication", "notify", "transactionId", "transactionType", "transactionDate", "paymentGateway",
		"transactionPnref", "transactionAmount", "transactionFeesPaid", "transactionDeductibleAmount", "creditCardType",
		"creditCardNumber", "creditCardExp", "coverFees" })
public class Result {

	@JsonProperty("id")
	private String id;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("email")
	private String email;
	@JsonProperty("city")
	private String city;
	@JsonProperty("addressLine1")
	private String addressLine1;
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
	@JsonProperty("referringUrl")
	private String referringUrl;
	@JsonProperty("activityType")
	private String activityType;
	@JsonProperty("confirmationCheckbox")
	private Boolean confirmationCheckbox;
	@JsonProperty("keepInformed")
	private Boolean keepInformed;
	@JsonProperty("donationId")
	private String donationId;
	@JsonProperty("donationAmount")
	private String donationAmount;
	@JsonProperty("donationType")
	private String donationType;
	@JsonProperty("donationCreatedDate")
	private String donationCreatedDate;
	@JsonProperty("designation")
	private String designation;
	@JsonProperty("dedicationType")
	private String dedicationType;
	@JsonProperty("dedication")
	private String dedication;
	@JsonProperty("notify")
	private String notify;
	@JsonProperty("transactionId")
	private String transactionId;
	@JsonProperty("transactionType")
	private String transactionType;
	@JsonProperty("transactionDate")
	private String transactionDate;
	@JsonProperty("paymentGateway")
	private String paymentGateway;
	@JsonProperty("transactionPnref")
	private String transactionPnref;
	@JsonProperty("transactionAmount")
	private String transactionAmount;
	@JsonProperty("transactionFeesPaid")
	private String transactionFeesPaid;
	@JsonProperty("transactionDeductibleAmount")
	private String transactionDeductibleAmount;
	@JsonProperty("creditCardType")
	private String creditCardType;
	@JsonProperty("creditCardNumber")
	private String creditCardNumber;
	@JsonProperty("creditCardExp")
	private String creditCardExp;
	@JsonProperty("coverFees")
	private Boolean coverFees;
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

	@JsonProperty("referringUrl")
	public String getReferringUrl() {
		return referringUrl;
	}

	@JsonProperty("referringUrl")
	public void setReferringUrl(String referringUrl) {
		this.referringUrl = referringUrl;
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

	@JsonProperty("donationId")
	public String getDonationId() {
		return donationId;
	}

	@JsonProperty("donationId")
	public void setDonationId(String donationId) {
		this.donationId = donationId;
	}

	@JsonProperty("donationAmount")
	public String getDonationAmount() {
		return donationAmount;
	}

	@JsonProperty("donationAmount")
	public void setDonationAmount(String donationAmount) {
		this.donationAmount = donationAmount;
	}

	@JsonProperty("donationType")
	public String getDonationType() {
		return donationType;
	}

	@JsonProperty("donationType")
	public void setDonationType(String donationType) {
		this.donationType = donationType;
	}

	@JsonProperty("donationCreatedDate")
	public String getDonationCreatedDate() {
		return donationCreatedDate;
	}

	@JsonProperty("donationCreatedDate")
	public void setDonationCreatedDate(String donationCreatedDate) {
		this.donationCreatedDate = donationCreatedDate;
	}

	@JsonProperty("designation")
	public String getDesignation() {
		return designation;
	}

	@JsonProperty("designation")
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@JsonProperty("dedicationType")
	public String getDedicationType() {
		return dedicationType;
	}

	@JsonProperty("dedicationType")
	public void setDedicationType(String dedicationType) {
		this.dedicationType = dedicationType;
	}

	@JsonProperty("dedication")
	public String getDedication() {
		return dedication;
	}

	@JsonProperty("dedication")
	public void setDedication(String dedication) {
		this.dedication = dedication;
	}

	@JsonProperty("notify")
	public String getNotify() {
		return notify;
	}

	@JsonProperty("notify")
	public void setNotify(String notify) {
		this.notify = notify;
	}

	@JsonProperty("transactionId")
	public String getTransactionId() {
		return transactionId;
	}

	@JsonProperty("transactionId")
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	@JsonProperty("transactionType")
	public String getTransactionType() {
		return transactionType;
	}

	@JsonProperty("transactionType")
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	@JsonProperty("transactionDate")
	public String getTransactionDate() {
		return transactionDate;
	}

	@JsonProperty("transactionDate")
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	@JsonProperty("paymentGateway")
	public String getPaymentGateway() {
		return paymentGateway;
	}

	@JsonProperty("paymentGateway")
	public void setPaymentGateway(String paymentGateway) {
		this.paymentGateway = paymentGateway;
	}

	@JsonProperty("transactionPnref")
	public String getTransactionPnref() {
		return transactionPnref;
	}

	@JsonProperty("transactionPnref")
	public void setTransactionPnref(String transactionPnref) {
		this.transactionPnref = transactionPnref;
	}

	@JsonProperty("transactionAmount")
	public String getTransactionAmount() {
		return transactionAmount;
	}

	@JsonProperty("transactionAmount")
	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	@JsonProperty("transactionFeesPaid")
	public String getTransactionFeesPaid() {
		return transactionFeesPaid;
	}

	@JsonProperty("transactionFeesPaid")
	public void setTransactionFeesPaid(String transactionFeesPaid) {
		this.transactionFeesPaid = transactionFeesPaid;
	}

	@JsonProperty("transactionDeductibleAmount")
	public String getTransactionDeductibleAmount() {
		return transactionDeductibleAmount;
	}

	@JsonProperty("transactionDeductibleAmount")
	public void setTransactionDeductibleAmount(String transactionDeductibleAmount) {
		this.transactionDeductibleAmount = transactionDeductibleAmount;
	}

	@JsonProperty("creditCardType")
	public String getCreditCardType() {
		return creditCardType;
	}

	@JsonProperty("creditCardType")
	public void setCreditCardType(String creditCardType) {
		this.creditCardType = creditCardType;
	}

	@JsonProperty("creditCardNumber")
	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	@JsonProperty("creditCardNumber")
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	@JsonProperty("creditCardExp")
	public String getCreditCardExp() {
		return creditCardExp;
	}

	@JsonProperty("creditCardExp")
	public void setCreditCardExp(String creditCardExp) {
		this.creditCardExp = creditCardExp;
	}

	@JsonProperty("coverFees")
	public Boolean getCoverFees() {
		return coverFees;
	}

	@JsonProperty("coverFees")
	public void setCoverFees(Boolean coverFees) {
		this.coverFees = coverFees;
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
		return "Result [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", city=" + city + ", addressLine1=" + addressLine1 + ", state=" + state + ", stateCode=" + stateCode
				+ ", zipCode=" + zipCode + ", country=" + country + ", countryCode=" + countryCode + ", supporterId="
				+ supporterId + ", referringUrl=" + referringUrl + ", activityType=" + activityType
				+ ", confirmationCheckbox=" + confirmationCheckbox + ", keepInformed=" + keepInformed + ", donationId="
				+ donationId + ", donationAmount=" + donationAmount + ", donationType=" + donationType
				+ ", donationCreatedDate=" + donationCreatedDate + ", designation=" + designation + ", dedicationType="
				+ dedicationType + ", dedication=" + dedication + ", notify=" + notify + ", transactionId="
				+ transactionId + ", transactionType=" + transactionType + ", transactionDate=" + transactionDate
				+ ", paymentGateway=" + paymentGateway + ", transactionPnref=" + transactionPnref
				+ ", transactionAmount=" + transactionAmount + ", transactionFeesPaid=" + transactionFeesPaid
				+ ", transactionDeductibleAmount=" + transactionDeductibleAmount + ", creditCardType=" + creditCardType
				+ ", creditCardNumber=" + creditCardNumber + ", creditCardExp=" + creditCardExp + ", coverFees="
				+ coverFees + ", additionalProperties=" + additionalProperties + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activityType == null) ? 0 : activityType.hashCode());
		result = prime * result + ((additionalProperties == null) ? 0 : additionalProperties.hashCode());
		result = prime * result + ((addressLine1 == null) ? 0 : addressLine1.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((confirmationCheckbox == null) ? 0 : confirmationCheckbox.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((countryCode == null) ? 0 : countryCode.hashCode());
		result = prime * result + ((coverFees == null) ? 0 : coverFees.hashCode());
		result = prime * result + ((creditCardExp == null) ? 0 : creditCardExp.hashCode());
		result = prime * result + ((creditCardNumber == null) ? 0 : creditCardNumber.hashCode());
		result = prime * result + ((creditCardType == null) ? 0 : creditCardType.hashCode());
		result = prime * result + ((dedication == null) ? 0 : dedication.hashCode());
		result = prime * result + ((dedicationType == null) ? 0 : dedicationType.hashCode());
		result = prime * result + ((designation == null) ? 0 : designation.hashCode());
		result = prime * result + ((donationAmount == null) ? 0 : donationAmount.hashCode());
		result = prime * result + ((donationCreatedDate == null) ? 0 : donationCreatedDate.hashCode());
		result = prime * result + ((donationId == null) ? 0 : donationId.hashCode());
		result = prime * result + ((donationType == null) ? 0 : donationType.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((keepInformed == null) ? 0 : keepInformed.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((notify == null) ? 0 : notify.hashCode());
		result = prime * result + ((paymentGateway == null) ? 0 : paymentGateway.hashCode());
		result = prime * result + ((referringUrl == null) ? 0 : referringUrl.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((stateCode == null) ? 0 : stateCode.hashCode());
		result = prime * result + ((supporterId == null) ? 0 : supporterId.hashCode());
		result = prime * result + ((transactionAmount == null) ? 0 : transactionAmount.hashCode());
		result = prime * result + ((transactionDate == null) ? 0 : transactionDate.hashCode());
		result = prime * result + ((transactionDeductibleAmount == null) ? 0 : transactionDeductibleAmount.hashCode());
		result = prime * result + ((transactionFeesPaid == null) ? 0 : transactionFeesPaid.hashCode());
		result = prime * result + ((transactionId == null) ? 0 : transactionId.hashCode());
		result = prime * result + ((transactionPnref == null) ? 0 : transactionPnref.hashCode());
		result = prime * result + ((transactionType == null) ? 0 : transactionType.hashCode());
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
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
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
		if (coverFees == null) {
			if (other.coverFees != null)
				return false;
		} else if (!coverFees.equals(other.coverFees))
			return false;
		if (creditCardExp == null) {
			if (other.creditCardExp != null)
				return false;
		} else if (!creditCardExp.equals(other.creditCardExp))
			return false;
		if (creditCardNumber == null) {
			if (other.creditCardNumber != null)
				return false;
		} else if (!creditCardNumber.equals(other.creditCardNumber))
			return false;
		if (creditCardType == null) {
			if (other.creditCardType != null)
				return false;
		} else if (!creditCardType.equals(other.creditCardType))
			return false;
		if (dedication == null) {
			if (other.dedication != null)
				return false;
		} else if (!dedication.equals(other.dedication))
			return false;
		if (dedicationType == null) {
			if (other.dedicationType != null)
				return false;
		} else if (!dedicationType.equals(other.dedicationType))
			return false;
		if (designation == null) {
			if (other.designation != null)
				return false;
		} else if (!designation.equals(other.designation))
			return false;
		if (donationAmount == null) {
			if (other.donationAmount != null)
				return false;
		} else if (!donationAmount.equals(other.donationAmount))
			return false;
		if (donationCreatedDate == null) {
			if (other.donationCreatedDate != null)
				return false;
		} else if (!donationCreatedDate.equals(other.donationCreatedDate))
			return false;
		if (donationId == null) {
			if (other.donationId != null)
				return false;
		} else if (!donationId.equals(other.donationId))
			return false;
		if (donationType == null) {
			if (other.donationType != null)
				return false;
		} else if (!donationType.equals(other.donationType))
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
		if (notify == null) {
			if (other.notify != null)
				return false;
		} else if (!notify.equals(other.notify))
			return false;
		if (paymentGateway == null) {
			if (other.paymentGateway != null)
				return false;
		} else if (!paymentGateway.equals(other.paymentGateway))
			return false;
		if (referringUrl == null) {
			if (other.referringUrl != null)
				return false;
		} else if (!referringUrl.equals(other.referringUrl))
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
		if (supporterId == null) {
			if (other.supporterId != null)
				return false;
		} else if (!supporterId.equals(other.supporterId))
			return false;
		if (transactionAmount == null) {
			if (other.transactionAmount != null)
				return false;
		} else if (!transactionAmount.equals(other.transactionAmount))
			return false;
		if (transactionDate == null) {
			if (other.transactionDate != null)
				return false;
		} else if (!transactionDate.equals(other.transactionDate))
			return false;
		if (transactionDeductibleAmount == null) {
			if (other.transactionDeductibleAmount != null)
				return false;
		} else if (!transactionDeductibleAmount.equals(other.transactionDeductibleAmount))
			return false;
		if (transactionFeesPaid == null) {
			if (other.transactionFeesPaid != null)
				return false;
		} else if (!transactionFeesPaid.equals(other.transactionFeesPaid))
			return false;
		if (transactionId == null) {
			if (other.transactionId != null)
				return false;
		} else if (!transactionId.equals(other.transactionId))
			return false;
		if (transactionPnref == null) {
			if (other.transactionPnref != null)
				return false;
		} else if (!transactionPnref.equals(other.transactionPnref))
			return false;
		if (transactionType == null) {
			if (other.transactionType != null)
				return false;
		} else if (!transactionType.equals(other.transactionType))
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}
	
	

}