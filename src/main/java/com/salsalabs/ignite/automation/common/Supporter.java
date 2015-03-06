package com.salsalabs.ignite.automation.common;

import org.json.JSONException;
import org.json.JSONObject;

public class Supporter {
	private String finalEMAIL = "";
	private String cPhone = "32165498765";
	private String city = "CityT";
	private String emailDomain = "@devnull.test.ignite.net";
	private String importedEmail = "importedsup";
	private String subscribedEmail = "subscribedsup";
	private String manuallEmail = "munuallysup";
	private String facebook = "fbv";
	private String firstName = "Tester";
	private String home_Phone = "98765432112";
	private String lastName = "Testerov";
	private String preferredLanguage = "English (United States)";
	private String state = "NY";
	private String twitter = "twv";
	private String zipCode = "20147";
	private String addressLine1 = "Street line1";
	private String addressLine2 = "Street line2 ";
	private String middleName = "MName";

	public String getFinalEMAIL() {
		return finalEMAIL;
	}

	public void setFinalEMAIL(String finalEMAIL) {
		this.finalEMAIL = finalEMAIL;
	}

	public String getcPhone() {
		return cPhone;
	}

	public void setcPhone(String cPhone) {
		this.cPhone = cPhone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmailDomain() {
		return emailDomain;
	}

	public void setEmailDomain(String emailDomain) {
		this.emailDomain = emailDomain;
	}

	public String getImportedEmail() {
		return importedEmail;
	}

	public void setImportedEmail(String importedEmail) {
		this.importedEmail = importedEmail;
	}

	public String getSubscribedEmail() {
		return subscribedEmail;
	}

	public void setSubscribedEmail(String subscribedEmail) {
		this.subscribedEmail = subscribedEmail;
	}

	public String getManuallEmail() {
		return manuallEmail;
	}

	public void setManuallEmail(String manuallEmail) {
		this.manuallEmail = manuallEmail;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getHome_Phone() {
		return home_Phone;
	}

	public void setHome_Phone(String home_Phone) {
		this.home_Phone = home_Phone;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPreferredLanguage() {
		return preferredLanguage;
	}

	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public JSONObject getSupporterJSON(String email) throws JSONException {
		String j = "{\"header\":{},\"payload\":{\"firstName\":\"" + firstName + "\",\"middleName\":\"" + middleName + "\",\"lastName\":\"" + lastName + "\",\"language\":\"en-US\",\"contacts\":[{\"type\":\"PhoneCell\",\"value\":\"" + cPhone
				+ "\"},{\"type\":\"MessagingEmail\",\"value\":\"" + email + "\",\"status\":\"OptedIn\"},{\"type\":\"SocialFacebook\",\"value\":\"" + facebook + "\"},{\"type\":\"SocialTwitter\",\"value\":\"" + twitter
				+ "\"},{\"type\":\"SocialGooglePlus\",\"value\":\"goo\"}],\"customFields\":[],\"addresses\":[{\"line1\":\"" + addressLine1 + "\",\"line2\":\"" + addressLine2 + "\",\"city\":\"" + city + "\",\"state\":\"" + state + "\",\"zip\":\""
				+ zipCode + "\",\"addressType\":\"AddressHome\"}]}}";
		return new JSONObject(j);
	}
}
