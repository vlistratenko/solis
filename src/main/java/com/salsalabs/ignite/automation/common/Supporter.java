package com.salsalabs.ignite.automation.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

public class Supporter {
	public String finalEMAIL = "";
	public String cPhone = "32165498765";
	public String emailDomain = "@devnull.test.ignite.net";
	public String importedEmail = "importedsup";
	public String subscribedEmail = "subscribedsup";
	public String manuallEmail = "munuallysup";
	public String facebook = "fbv";
	public String home_Phone = "98765432112";
	public String preferredLanguage = "English (United States)";
	public String twitter = "twv";
	public String zipCode = "20147";
	public String middleName = "MName";
	public String prefix = "";
	public String suffix = "";
	public String language = "";
	public String birthDate = "";
	public String phoneCell = "";
	public String socialFacebook = "";
	public String phoneHome = "";
	public String phoneWork = "";
	public String linkedin = "";
	public String socialTwitter = "";
	public String externalID = "";
	public String dateOfBirth = "";

	

	public String constituentNumber = "", title = "", firstName = "", lastorOrgName = "", petType = "", petName = "",
			addressLine1 = "", addressLine2 = "", city = "", state = "", postalCode = "", spouseConstituentNumber = "",
			spouseFirstName = "", spouseTitle = "";
	public String source;

	/* map for fields in the file */
	private static Integer CONSTITUENTNUMBER = 0, TITLE = 1, FIRSTNAME = 2, LASTORORGNAME = 3, PETTYPE = 4, PETNAME = 5,
			ADDRESSLINE1 = 6, ADDRESSLINE2 = 7, CITY = 8, STATE = 9, POSTALCODE = 10, SPOUSECONSTITUENTNUMBER = 11,
			SPOUSEFIRSTNAME = 12, SPOUSETITLE = 13, EMAILADDRESS = 14;

	public static Supporter generateSupporter() {
		Supporter result = new Supporter();
		String unicID = CommonUtils.getRandomValue(100000, 0);
		result.setFinalEMAIL(SeleneseTestCase.emailClient.getEmailBox("supman" + unicID));
		result.setExternalID(unicID);
		result.setDateOfBirth(CommonUtils.generateDateOFBirthValue());
		result.setFirstName("Tester" + unicID);
		result.setLastName("Testerov" + unicID);
		result.setMiddleName("TestMiddleName");
		result.setcPhone(CommonUtils.getRandomNumericValueFixedLength(11));
		result.setCity("New York");
		result.setState("New York");
		result.setAddressLine1("350 5th Ave" );
		result.setAddressLine2("Street2" + unicID);
		result.setZipCode(CommonUtils.getRandomNumericValueFixedLength(5));
		result.setFacebook("fb" + unicID);
		result.setTwitter("twitter" + unicID);
		result.setLinkedin("ln" + unicID );
		return result;
	}

	public String getFinalEMAIL() {
		return finalEMAIL;
	}

	public void setFinalEMAIL(String finalEMAIL) {
		this.finalEMAIL = finalEMAIL;
	}

	public String getExternalId() {
		return externalID;
	}
	
	public void setExternalID(String externalID) {
		this.externalID = externalID;
	}
	
	public String getBirthdate() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfbirth) {
		this.dateOfBirth = dateOfbirth;
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
	
	public String getLinkedin() {
		return linkedin;
	}
	
	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
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
		return lastorOrgName;
	}

	public void setLastName(String lastName) {
		this.lastorOrgName = lastName;
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

	public String getPostalCode() {
		return postalCode;
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
		String j = "{\"header\":{},\"payload\":{\"firstName\":\"" + firstName + "\",\"middleName\":\"" + middleName
				+ "\",\"lastName\":\"" + lastorOrgName
				+ "\",\"language\":\"en-US\", \"contacts\":[{\"type\":\"PhoneCell\",\"value\":\"" + cPhone
				+ "\"},{\"type\":\"MessagingEmail\",\"value\":\"" + email
				+ "\",\"status\":\"OptedIn\"},{\"type\":\"SocialFacebook\",\"value\":\"" + facebook
				+ "\"},{\"type\":\"SocialTwitter\",\"value\":\"" + twitter
				+ "\"},{\"type\":\"SocialGooglePlus\",\"value\":\"goo\"}],\"customFields\":[],\"addresses\":[{\"line1\":\""
				+ addressLine1 + "\",\"line2\":\"" + addressLine2 + "\",\"city\":\"" + city + "\",\"state\":\"" + state
				+ "\",\"zip\":\"" + zipCode + "\",\"addressType\":\"AddressHome\"}]}}";
		return new JSONObject(j);
	}

	public JSONObject getSupporterJSONWithExternalId(String email, String externalSystemId) throws JSONException {
		String j = "{\"header\":{},\"payload\":{\"firstName\":\"" + firstName + "\",\"middleName\":\"" + middleName
				+ "\",\"lastName\":\"" + lastorOrgName + "\",\"language\":\"en-US\",\"externalSystemId\":\""
				+ externalSystemId + "\" , \"contacts\":[{\"type\":\"PhoneCell\",\"value\":\"" + cPhone
				+ "\"},{\"type\":\"MessagingEmail\",\"value\":\"" + email
				+ "\",\"status\":\"OptedIn\"},{\"type\":\"SocialFacebook\",\"value\":\"" + facebook
				+ "\"},{\"type\":\"SocialTwitter\",\"value\":\"" + twitter
				+ "\"},{\"type\":\"SocialGooglePlus\",\"value\":\"goo\"}],\"customFields\":[],\"addresses\":[{\"line1\":\""
				+ addressLine1 + "\",\"line2\":\"" + addressLine2 + "\",\"city\":\"" + city + "\",\"state\":\"" + state
				+ "\",\"zip\":\"" + zipCode + "\",\"addressType\":\"AddressHome\"}]}}";
		return new JSONObject(j);
	}

	public static Map<Integer, Supporter> getSupportersFromFile() {
		String filename = new File("all living with spouses and email.csv").getAbsolutePath();
		Map<Integer, Supporter> data = new HashMap<Integer, Supporter>();
		int i = 0;
		try {
			BufferedReader in = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = in.readLine()) != null) {
				Supporter sup = new Supporter();
				String[] fields = line.split(",");
				for (int j = 0; j < fields.length; j++) {
					sup.constituentNumber = fields[CONSTITUENTNUMBER];
					sup.title = fields[TITLE];
					sup.firstName = fields[FIRSTNAME];
					sup.lastorOrgName = fields[LASTORORGNAME];
					sup.petType = fields[PETTYPE];
					sup.petName = fields[PETNAME];
					sup.addressLine1 = fields[ADDRESSLINE1];
					sup.addressLine2 = fields[ADDRESSLINE2];
					sup.city = fields[CITY];
					sup.state = fields[STATE];
					sup.postalCode = fields[POSTALCODE];
					sup.spouseConstituentNumber = fields[SPOUSECONSTITUENTNUMBER];
					sup.spouseFirstName = fields[SPOUSEFIRSTNAME];
					sup.spouseTitle = fields[SPOUSETITLE];
					sup.finalEMAIL = fields[EMAILADDRESS];
				}

				data.put(i, sup);

				i++;
			}
			in.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	public Map<Integer, Supporter> getSupportersFromSystem(String host, String login, String pass,
			Integer amountOfSupporters, String source) throws KeyManagementException, ClientProtocolException,
			NoSuchAlgorithmException, KeyStoreException, JSONException, URISyntaxException, IOException {
		return new HttpClient(host).login(login, pass).getSupporters(source, amountOfSupporters);

	}

	public Supporter getSupporterFromSystemByEmail(String email, String host, String login, String pass)
			throws KeyManagementException, ClientProtocolException, NoSuchAlgorithmException, KeyStoreException,
			JSONException, URISyntaxException, IOException {
		return new HttpClient(host).login(login, pass).getSupporterByEmail(email);

	}

	public static Supporter getSupporterWithRandomDataFromFile() {
		Map<Integer, Supporter> data = getSupportersFromFile();
		Supporter sup = new Supporter();

		sup.constituentNumber = data.get(CommonUtils.getRandomValueNumericFromTo(0, data.size())).constituentNumber;
		sup.title = data.get(CommonUtils.getRandomValueNumericFromTo(0, data.size())).title;
		sup.firstName = data.get(CommonUtils.getRandomValueNumericFromTo(0, data.size())).firstName;
		sup.lastorOrgName = data.get(CommonUtils.getRandomValueNumericFromTo(0, data.size())).lastorOrgName;
		sup.petType = data.get(CommonUtils.getRandomValueNumericFromTo(0, data.size())).petType;
		sup.petName = data.get(CommonUtils.getRandomValueNumericFromTo(0, data.size())).petName;
		sup.addressLine1 = data.get(CommonUtils.getRandomValueNumericFromTo(0, data.size())).addressLine1;
		sup.addressLine2 = data.get(CommonUtils.getRandomValueNumericFromTo(0, data.size())).addressLine2;
		sup.city = data.get(CommonUtils.getRandomValueNumericFromTo(0, data.size())).city;
		sup.state = data.get(CommonUtils.getRandomValueNumericFromTo(0, data.size())).state;
		sup.postalCode = data.get(CommonUtils.getRandomValueNumericFromTo(0, data.size())).postalCode;
		sup.spouseConstituentNumber = data
				.get(CommonUtils.getRandomValueNumericFromTo(0, data.size())).spouseConstituentNumber;
		sup.spouseFirstName = data.get(CommonUtils.getRandomValueNumericFromTo(0, data.size())).spouseFirstName;
		sup.spouseTitle = data.get(CommonUtils.getRandomValueNumericFromTo(0, data.size())).spouseTitle;
		sup.finalEMAIL = sup.firstName + "." + sup.lastorOrgName + CommonUtils.getRandomNumericValueFixedLength(4)
				+ SeleneseTestCase.emailClient.getEmailBox("");
		return sup;
	}
}
