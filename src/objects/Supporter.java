package objects;

import org.json.JSONException;
import org.json.JSONObject;


public class Supporter {
	public String finalEMAIL = "";
	public String cPhone = "32165498765";
	public String City = "CityT";
	public String emailDomain="@devnull.test.ignite.net";
	public String importedEmail = "importedsup";
	public String subscribedEmail = "subscribedsup";
	public String manuallEmail = "munuallysup";
	public String facebook = "fbv";
	public String firstName = "Tester";
	public String Home_Phone = "98765432112";
	public String lastName = "Testerov";
	public String PreferredLanguage = "English (United States)";
	public String state = "NY";
	public String twitter = "twv";
	public String zipCode = "20147";
	public String addressLine1 = "Street line1";
	public String addressLine2 = "Street line2 ";
	public String MiddleName = "MName";
	
	public JSONObject getSupporterJSON(String email) throws JSONException {
		//JSONObject supp = new JSONObject();
/*		supp.put("header", "{}");
		JSONObject jo = new JSONObject();
		jo.put("firstName", "John");
		jo.put("lastName", "Doe");
		JSONArray supData = new JSONArray();
		supData.put(jo);
		supp.put("payload", supData);*/
		String j = "{\"header\":{},\"payload\":{\"firstName\":\"" + 
		firstName + "\",\"middleName\":\"" + MiddleName +
		"\",\"lastName\":\"" + lastName +
		"\",\"language\":\"en-US\",\"contacts\":[{\"type\":\"PhoneCell\",\"value\":\"" + cPhone +
		"\"},{\"type\":\"MessagingEmail\",\"value\":\"" + email +
		"\",\"status\":\"OptedIn\"},{\"type\":\"SocialFacebook\",\"value\":\"" + facebook +
		"\"},{\"type\":\"SocialTwitter\",\"value\":\"" + twitter +
		"\"},{\"type\":\"SocialGooglePlus\",\"value\":\"goo\"}],\"customFields\":[],\"addresses\":[{\"line1\":\"" + addressLine1 +
		"\",\"line2\":\""+ addressLine2 + "\",\"city\":\"" + City + "\",\"state\":\"" + state + 
		"\",\"zip\":\"" + zipCode + "\",\"addressType\":\"AddressHome\"}]}}";
		return new JSONObject(j);
	}
}
