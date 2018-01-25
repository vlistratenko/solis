package com.salsalabs.ignite.automation.common;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import static java.lang.Thread.sleep;
import java.util.HashMap;
import java.util.Map;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class HttpClient {

	private static final Logger logger = SeleneseTestCase.logger;
	private String authToken = "";
	private String orgID = "";
	private String orgName = "";
	private String host = "";

	public HttpClient(String host) {
		this.host = host.replace("https://", "");
	}

	public HttpClient() {
		this.host = SeleneseTestCase.USED_ENVIRONMENT.getBaseTestUrl().replace("https://", "");
	}

	public HttpClient login(String userName, String pass) {
		Response firstLoginEndpoint = given().config(config().sslConfig(new SSLConfig().allowAllHostnames()))
				.relaxedHTTPSValidation()
				.body("{\"header\":{},\"payload\":{\"username\":\"" + userName + "\",\"password\":\"" + pass + "\"}}")
				.when().contentType(ContentType.JSON).post("https://" + host + "/api/auth/login.json").then().extract()
				.response();
		authToken = firstLoginEndpoint.path("header.authToken");
		orgID = firstLoginEndpoint.path("payload.organization[0].id");
		orgName = firstLoginEndpoint.path("payload.organization[0].name");

		Response secondLoginEndpoint = given().config(config().sslConfig(new SSLConfig().allowAllHostnames()))
				.relaxedHTTPSValidation().header("authToken", authToken)
				.body("{\"header\":{},\"payload\":{\"organization\":[{\"id\":\"" + orgID + "\",\"name\":\"" + orgName
						+ "\"}]}}")
				.when().contentType(ContentType.JSON).post("https://" + host + "/api/auth/login.json").then().extract()
				.response();
		authToken = secondLoginEndpoint.path("header.authToken");
		System.out.println(authToken);
		return this;
	}

	public HttpClient login() {
		return login(CommonUtils.getProperty(PropertyName.ADMIN_EMAIL),
				CommonUtils.getProperty(PropertyName.ADMIN_PASSWORD));
	}

	public Response sendPostrequest(String jsonString, String endPoint) {
		Response postResponse = given().config(config().sslConfig(new SSLConfig().allowAllHostnames()))
				.relaxedHTTPSValidation().header("authToken", authToken).body(jsonString).log().ifValidationFails().when()
				.contentType(ContentType.JSON).post(endPoint).then().log().body().extract().response();
		return postResponse;

	}

	public Response sendGetrequest(String url) {
		Response getResponse = given().config(config().sslConfig(new SSLConfig().allowAllHostnames()))
				.relaxedHTTPSValidation().header("authToken", authToken).when().get(url).then().log().ifError()
				.contentType(ContentType.JSON).extract().response();
		return getResponse;
	}

	public HttpClient createSupporter(String jsonString) {
		sendPostrequest(jsonString, "/api/person/supporter");
		logger.info("Supporter is created");
		return this;
	}

	public HttpClient createCustomGFields(String customFieldObject) {
		sendPostrequest(customFieldObject, "https://" + host + "/api/customField");
		logger.info("Custom File is created");
		return this;
	}

	public Map<Integer, Supporter> getSupporters(String source, Integer supAmount) {
		Map<Integer, Supporter> data = new HashMap<Integer, Supporter>();
		Response res = sendGetrequest(new Supporter().getSupportersRequest("", source));
		logger.info("Getting  the list of supporters in the organization");

		logger.info("Getting  the list of supporters in the organization");
		int ammountofSupporters = res.path("payload.total");
		System.out.println(ammountofSupporters);
		for (int i = 0; i < ammountofSupporters; i++) {
			Supporter supporter = new Supporter();
			supporter.finalEMAIL = res.path("payload.supporters[" + i + "].contacts[0].value");
			supporter.finalEMAIL = supporter.finalEMAIL.replace("ignite.net", "igniteaction.net");
			supporter.firstName = res.path("payload.supporters[" + i + "].firstName");
			supporter.lastorOrgName = res.path("payload.supporters[" + i + "].lastName");
			supporter.postalCode = res.path("payload.supporters[" + i + "].addresses[0].zip");
			supporter.city = res.path("payload.supporters[" + i + "].addresses[0].city");
			supporter.state = res.path("payload.supporters[" + i + "].addresses[0].state");
			data.put(i, supporter);
		}
		logger.info("Amount of supporters " + " " + data.size());
		return data;
	}

	public int getNumberOfSupportersInOrg() {
		Response res = sendGetrequest(new Supporter().getSupportersRequest("", ""));
		int ammountofSupporters = res.path("payload.count");
		return ammountofSupporters;
	}

	public Map<String, String> getListOfSocialPages() {
		Map<String, String> network = new HashMap();
		Response res = sendGetrequest("https://" + host + "/api/organization/" + orgID);
		network.put("facebook", res.path("payload.socialNetworkPages[0].link"));
		network.put("twitter", res.path("payload.socialNetworkPages[1].link"));
		network.put("instagram", res.path("payload.socialNetworkPages[2].link"));
		network.put("pinterest", res.path("payload.socialNetworkPages[3].link"));
		network.put("youTube", res.path("payload.socialNetworkPages[4].link"));
		network.put("linkedin", res.path("payload.socialNetworkPages[5].link"));
		network.put("tumbler", res.path("payload.socialNetworkPages[6].link"));
		return network;

	}

	public HttpClient createCustomField(String customField) {
		Response res = sendPostrequest(customField, "https://" + host + "/api/customField");
		logger.info("Response status code is " + res.getStatusCode());
		return this;
	}

	public HttpClient createGateway(String supp) {
		sendPostrequest(supp, "https://" + host + "/api/organization/paymentConfiguration");
		return this;
	}

	public Map<String, String> getAllSupporterCustomFields(String supporterEmail) {
		Map<String, String> supporterCustomFields = new HashMap<>();
		Response res = getSupporterDetailsInJSON(supporterEmail);
		ArrayList<String> customFields = new ArrayList<String>();
		customFields = res.path("payload.customFields");
		for (int i = 0; i < customFields.size(); i++) {
			supporterCustomFields.put(res.path("payload.customFields[" + i + "].fieldDefinition.name"),
					res.path("payload.customFields[" + i + "].value"));

		}
		supporterCustomFields.forEach((k,v)->{
			logger.info("Name " + k + " Value: " + v);
			System.out.println();
			
		});
		return supporterCustomFields;
	}
	
	public Map<String, String> getAllSupporterContactFields(String supporterEmail){
		Map<String, String> supporterContactFields = new HashMap<>();
		Response res = getSupporterDetailsInJSON(supporterEmail);
		ArrayList<String> contactFields = new ArrayList<String>();
		contactFields = res.path("payload.contacts");
		for (int i = 0; i < contactFields.size(); i++) {
			supporterContactFields.put(res.path("payload.contacts[" + i + "].type"),
					res.path("payload.contacts[" + i + "].value"));

		}
		supporterContactFields.forEach((k,v)->{
			logger.info("Name " + k + " Value: " + v);;
			System.out.println();
			
		});		
		return supporterContactFields;
	}
	
	public Map<String, String> getAllSupporterAdressessFields(String supporterEmail){
		Map<String, String> supporterAdressessFields = new HashMap<>();
		Response res = getSupporterDetailsInJSON(supporterEmail);
		supporterAdressessFields = res.path("payload.addresses[0]");	
		supporterAdressessFields.forEach((k,v)->{
			logger.info("Name " + k + " Value: " + v);
			System.out.println();
			
		});
		return supporterAdressessFields;	
	}
	
	public Supporter getSupporterByEmail(String email) {
		Supporter sup = new Supporter();
		Response res = getSupporterDetailsInJSON(email);
				sup.firstName 	= res.path("payload.firstName");
 				sup.allPersonalFields.put("firstName", sup.firstName );
 				
 				sup.lastorOrgName 	= res.path("payload.lastName");
 				sup.allPersonalFields.put("lastName", sup.lastorOrgName );
 				
 				sup.prefix =  res.path("payload.prefix");
 				sup.allPersonalFields.put("prefix", sup.prefix );
 				
 				sup.middleName =  res.path("payload.middleName");
 				sup.allPersonalFields.put("middleName", sup.middleName );
 				
 				sup.suffix =  res.path("payload.suffix");
 				sup.allPersonalFields.put("suffix", sup.suffix );
 				
 				sup.suffix =  res.path("payload.language");
 				sup.allPersonalFields.put("language", sup.language );
 				
 				sup.birthDate =  res.path("payload.birthDate");
 				sup.allPersonalFields.put("language", sup.birthDate );
 				Map<String, String> contacts = new HashMap<>();
 				contacts = getAllSupporterContactFields(email);

 				sup.finalEMAIL = contacts.get("MessagingEmail");
 				sup.socialFacebook = contacts.get("SocialFacebook");
 				sup.phoneHome = contacts.get("PhoneWork");
 				sup.phoneCell = contacts.get("PhoneCell");
 				sup.phoneWork = contacts.get("PhoneWork");
 				sup.linkedin= contacts.get("LINKEDIN");
 				sup.socialTwitter = contacts.get("SocialTwitter");
 	
 			    sup.addressLine1  = res.path("payload.addresses[0].line1");
 			    sup.addressLine2  = res.path("payload.addresses[0].line2");
 			    sup.city  = res.path("payload.addresses[0].city");	    
 			    sup.state  = res.path("payload.addresses[0].state");
 			   sup.postalCode  = res.path("payload.addresses[0].postalCode");
 			   sup.country  = res.path("payload.addresses[0].country");
 			   sup.zipCode  = res.path("payload.addresses[0].zip");
 			    sup.allCustomFields = getAllSupporterCustomFields(sup.getFinalEMAIL());
 			    sup.allAdressFields = getAllSupporterAdressessFields(sup.getFinalEMAIL());
 			   return sup;

	}
	
	private Response getSupporterDetailsInJSON(String email) {
		Response res = sendGetrequest(new Supporter().getSupportersRequest(email, ""));
		String id = res.path("payload.supporters[0].id");
		SeleneseTestCase.logger.info("Supporter ID: " + id);
		Response res2 = sendGetrequest(new Supporter().getSupporterRequest(id, true));
		return res2;
	}

	public void waitUntilSupporterExists(String email, int seconds) {
		for (int i = 0; i <= seconds; i++) {
			try {
				sleep(1000);
				if (isSupporterExists(email))
					break;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isSupporterExists(String email) {
		boolean isExists = true;
		Response res = sendGetrequest(new Supporter().getSupportersRequest(email, ""));
		String id = res.path("payload.supporters[0].id");
		if (id.isEmpty() || id == "") {
			isExists = false;
			logger.info("Supporter with email " + email + " is not found.");
		}
		return isExists;

	}
}
