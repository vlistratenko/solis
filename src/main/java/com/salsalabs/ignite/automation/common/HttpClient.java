package com.salsalabs.ignite.automation.common;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpMethod;
import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.salsalabs.ignite.automation.pages.hq.manage.CustomFieldsPage;

import static java.lang.Thread.sleep;


public class HttpClient {
	
	private static final Logger logger = SeleneseTestCase.logger;

	String authToken = "";
	String orgID = "";
	String orgName = "";
	String host = "";
	CloseableHttpClient httpClient = null;
    HttpPost httpost = null;
    HttpGet httpget = null;
    CloseableHttpResponse response = null;
    ArrayList<String> JSONResponse = new ArrayList<String>();
	
	/*public HttpClient() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
		//getConnection();
	}*/
 
	
	public HttpClient(String host) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
	  	this.host = host.replace("https://", "");
	  	//getConnection();
	}
	
	public HttpClient() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
	  	this.host = SeleneseTestCase.USED_ENVIRONMENT.getBaseTestUrl().replace("https://", "");
	  	//getConnection();
	}
	
	public HttpClient login(String userName, String pass) throws URISyntaxException, ClientProtocolException, IOException {
		updateHeaders();
		sendPOSTRequest("https://" + host + "/api/auth/login.json", "{\"header\":{},\"payload\":{\"username\":\"" + userName + "\",\"password\":\"" + pass + "\"}}");
		Header h = response.getHeaders("authToken")[0];
		authToken = h.getValue();  
		updateHeaders();
		for (String temp : JSONResponse) {
		    	 try {
					orgID = jsonParser(temp, "payload.organization.0.id").toString();
					orgName = jsonParser(temp, "payload.organization.0.name").toString();
				} catch (JSONException e) {
					logger.error("", e);
				}
		 }
		 String req =  "{\"header\":{},\"payload\":{\"organization\":[{\"id\":\"" + orgID + "\",\"name\":\"" + orgName + "\"}]}}"; 
		 sendPOSTRequest("https://" + host + "/api/auth/login.json", req);
		 h = response.getHeaders("authToken")[0];
		 authToken = h.getValue();
		 updateHeaders();
		 return this;
	}
	
	public HttpClient login() {
		try {
			return login(CommonUtils.getProperty(PropertyName.ADMIN_EMAIL), CommonUtils.getProperty(PropertyName.ADMIN_PASSWORD));
		} catch (ClientProtocolException e) {
			logger.error("", e);
		} catch (URISyntaxException e) {
			logger.error("", e);
		} catch (IOException e) {
			logger.error("", e);
		}
		return null;
	}
	
	/**
	 * 
	 * 
	 */
	public String createSupporter(JSONObject supp) {
		try {
			sendPOSTRequest(new Supporter().getCreateSupporterRequest(), supp.toString());
	         for (String temp : JSONResponse) {
	            	 try {
						return jsonParser(temp, "payload.id").toString();
					} catch (JSONException e) {
						logger.error("", e);
					}
	         } 
		} catch (URISyntaxException | IOException e) {
			logger.error("", e);
		}
		return "";
	}

	public String createCustomFields(JSONObject customFieldObject) {
		try {
			sendPOSTRequest("https://" + host + "/api/customField", customFieldObject.toString());
			for (String temp : JSONResponse) {
				try {
					return jsonParser(temp, "payload.id").toString();
				} catch (JSONException e) {
					logger.error("", e);
				}
			}
		} catch (URISyntaxException | IOException e) {
			logger.error("", e);
		}
		return "";
	}
	
	public Map<Integer, Supporter> getSupporters(String source, Integer supAmount) throws JSONException {
        Map<Integer, Supporter> data = new HashMap<Integer, Supporter>();
		try {
			sendGETRequest(new Supporter().getSupportersRequest("", source));//"https://" + host + "/api/search/supporters?criteria=&listOffset=0&listResults=250&sortField=createdDate&sortOrder=DESCENDING" + source);
	        Integer amountOfSupporters = 0;

	        for (String temp : JSONResponse) {
	            	 try {
	            		 amountOfSupporters = (Integer) jsonParser(temp, "payload.count");	            		 
	         			for (int i = 0; i < amountOfSupporters; i++) {
	         				Supporter sup = new Supporter();
	         				sup.firstName = jsonParser(temp, "payload.supporters." + i + ".firstName").toString();
	         				sup.lastorOrgName = jsonParser(temp, "payload.supporters." + i + ".lastName").toString();	         				
	         				sup.postalCode = jsonParser(temp, "payload.supporters." + i + ".addresses.0.zip").toString();
	         				sup.city = jsonParser(temp, "payload.supporters." + i + ".addresses.0.city").toString();
	         				sup.state = jsonParser(temp, "payload.supporters." + i + ".addresses.0.state").toString();	    
	         				sup.finalEMAIL = jsonParser(temp, "payload.supporters." + i + ".contacts.0.value").toString();
	         				sup.finalEMAIL = sup.finalEMAIL.replace("ignite.net", "igniteaction.net");
	         				sup.source = jsonParser(temp, "payload.supporters." + i + ".source").toString();
	         				data.put(i, sup);
	         			}
					} catch (JSONException e) {
						logger.error("", e);
					}
	         }
		} catch (URISyntaxException | IOException e) {
			logger.error("", e);
		}
		return data;
	}
	
	public int getNumberOfSupportersInOrg() throws JSONException {
		Integer amountOfSupporters = 0;
		try {
			sendGETRequest(new Supporter().getSupportersRequest("", ""));//"https://" + host + "/api/search/supporters?criteria=&listOffset=0&listResults=250&sortField=createdDate&sortOrder=DESCENDING");
	        

	        for (String temp : JSONResponse) {
	            	 try {
	            		 amountOfSupporters = (Integer) jsonParser(temp, "payload.count");	            		 
	         			
					} catch (JSONException e) {
						logger.error("", e);
					}
	         }
		} catch (URISyntaxException | IOException e) {
			logger.error("", e);
		}
		return amountOfSupporters;
	}

	public Map<String, String> getAllSupporterCustomFields(String supporterEmail){
		Map<String, String> supporterCustomFields = new HashMap<>();
		try {
			String temp = getSupporterDetailsInJSON(supporterEmail);
			try {
				JSONArray customFields = (JSONArray) jsonParser(temp, "payload.customFields");
				for(int i = 0; i < customFields.length(); i++) {
					supporterCustomFields.put(jsonParser(temp, "payload.customFields." + i + ".fieldDefinition.name").toString(),
							jsonParser(temp, "payload.customFields." + i + ".value").toString());
				}
			} catch (JSONException e) {
				logger.error("", e);
			}
		} catch (URISyntaxException | IOException | JSONException e) {
			logger.error("", e);
		}
		return supporterCustomFields;
	}
	
	public Map<String, String> getAllSupporterContactFields(String supporterEmail){
		Map<String, String> supporterContactFields = new HashMap<>();
		try {
			String temp = getSupporterDetailsInJSON(supporterEmail);
			try {
				JSONArray contactFields = (JSONArray) jsonParser(temp, "payload.contacts");
				for(int i = 0; i < contactFields.length(); i++) {
					supporterContactFields.put(jsonParser(temp, "payload.contacts." + i + ".type").toString(),
							jsonParser(temp, "payload.contacts." + i + ".value").toString());
				}
			} catch (JSONException e) {
				logger.error("", e);
			}
		} catch (URISyntaxException | IOException | JSONException e) {
			logger.error("", e);
		}
		return supporterContactFields;
	}
	
	public Map<String, String> getAllSupporterAdressessFields(String supporterEmail){
		Map<String, String> supporterAdressessFields = new HashMap<>();
		try {
			String temp = getSupporterDetailsInJSON(supporterEmail);
			try {
				JSONObject addressFields = (JSONObject) jsonParser(temp, "payload.addresses.0");
				for(int i = 0; i < addressFields.length(); i++) {
					supporterAdressessFields.put(addressFields.names().getString(i),
							addressFields.getString(addressFields.names().getString(i)));
				}
			} catch (JSONException e) {
				logger.error("", e);
			}
		} catch (URISyntaxException | IOException | JSONException e) {
			logger.error("", e);
		}
		return supporterAdressessFields;
	}
	
	public Supporter getSupporterByEmail(String email) throws JSONException {
		Supporter sup = new Supporter();
		try {
			String temp = getSupporterDetailsInJSON(email);
			//System.err.println(temp);
        	 try { 				
 				sup.firstName = jsonParser(temp, "payload.firstName").toString();
 				sup.allPersonalFields.put("firstName", sup.firstName );
 				
 				sup.lastorOrgName = jsonParser(temp, "payload.lastName").toString();
 				sup.allPersonalFields.put("lastName", sup.lastorOrgName );
 				
 				sup.prefix = jsonParser(temp, "payload.prefix").toString();
 				sup.allPersonalFields.put("prefix", sup.prefix );
 				
 				sup.middleName = jsonParser(temp, "payload.middleName").toString();
 				sup.allPersonalFields.put("middleName", sup.middleName );
 				
 				sup.suffix = jsonParser(temp, "payload.suffix").toString();
 				sup.allPersonalFields.put("suffix", sup.suffix );
 				
 				sup.language = jsonParser(temp, "payload.language").toString();
 				sup.allPersonalFields.put("language", sup.language );
 				
 				sup.birthDate = jsonParser(temp, "payload.birthDate").toString();
 				sup.allPersonalFields.put("language", sup.birthDate );
 				
 				JSONArray contacts = (JSONArray) jsonParser(temp, "payload.contacts");
 				sup.finalEMAIL  = jsonParser(temp, "payload.contacts." + findArrayElementNumberByValue(contacts, "MessagingEmail") + ".value").toString();
 				sup.phoneCell  = jsonParser(temp, "payload.contacts." + findArrayElementNumberByValue(contacts, "PhoneCell") + ".value").toString();
 				sup.socialFacebook  = jsonParser(temp, "payload.contacts." + findArrayElementNumberByValue(contacts, "SocialFacebook") + ".value").toString();
 				sup.phoneHome  = jsonParser(temp, "payload.contacts." + findArrayElementNumberByValue(contacts, "PhoneHome") + ".value").toString();
 				sup.phoneWork  = jsonParser(temp, "payload.contacts." + findArrayElementNumberByValue(contacts, "PhoneWork") + ".value").toString();
 				sup.linkedin  = jsonParser(temp, "payload.contacts." + findArrayElementNumberByValue(contacts, "LINKEDIN") + ".value").toString();
 				sup.socialTwitter  = jsonParser(temp, "payload.contacts." + findArrayElementNumberByValue(contacts, "SocialTwitter") + ".value").toString();
 			    sup.addressLine1  = jsonParser(temp, "payload.addresses.0.line1").toString();
 			    sup.addressLine2  = jsonParser(temp, "payload.addresses.0.line2").toString();
 			    sup.city  = jsonParser(temp, "payload.addresses.0.city").toString();
 			    sup.state  = jsonParser(temp, "payload.addresses.0.state").toString();
 			    sup.postalCode  = jsonParser(temp, "payload.addresses.0.zip").toString();
 			    sup.country  = jsonParser(temp, "payload.addresses.0.country").toString();
 			    sup.zipCode  = jsonParser(temp, "payload.addresses.0.zip").toString();
 			    sup.allCustomFields = getAllSupporterCustomFields(sup.getFinalEMAIL());
 			    sup.allAdressFields = getAllSupporterAdressessFields(sup.getFinalEMAIL());
 			    
			} catch (JSONException e) {
				logger.error("", e);
			}
	         
		} catch (URISyntaxException | IOException e) {
			logger.error("", e);
		}
		return sup;
	}
	
	private CloseableHttpResponse sendPOSTRequest(String url, String json) throws URISyntaxException, ClientProtocolException, IOException {
		try {
			getConnection();
		} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SeleneseTestCase.logger.info("Try to send request to " + url);
		SeleneseTestCase.logger.info("Data to send " + json);
		JSONResponse.clear();
		httpost.setURI(new URI(url)); 
		 StringEntity input = new StringEntity(json);
        input.setContentType("application/json");
        httpost.setEntity(input);
        response = httpClient.execute(httpost);
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatusLine().getStatusCode());
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(
                (response.getEntity().getContent())));
        String output;
        while ((output = br.readLine()) != null) {
        	SeleneseTestCase.logger.info("Response: " + output);
        	JSONResponse.add(output);
        }
		httpClient.close();
        return 	response;	 
	}
	
	private CloseableHttpResponse sendGETRequest(String url/*, String json*/) throws URISyntaxException, ClientProtocolException, IOException {
		//HttpMethod
		try {
			getConnection();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SeleneseTestCase.logger.info("Try to send request to " + url);
		//SeleneseTestCase.logger.info("Token to send " + httpget.getHeaders("authToken")[0]);
		JSONResponse.clear();
		httpget.setURI(new URI(url)); 
        response = httpClient.execute(httpget);
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatusLine().getStatusCode());
        }
        
        BufferedReader br = new BufferedReader(new InputStreamReader(
                (response.getEntity().getContent())));
        String output;
        while ((output = br.readLine()) != null) {
        	SeleneseTestCase.logger.debug("Response: " + output);
        	String infoOutput = output;
        	if (infoOutput.length() > 3000)infoOutput = infoOutput.substring(0, 3000);
        	SeleneseTestCase.logger.info("Response: " + infoOutput);
        	JSONResponse.add(output);
        }
		httpClient.close();
        return 	response;	 
	}
	
	public Map<String, String> getListOfSocialPages(){
		Map<String, String> network = new HashMap();
		try {
			sendGETRequest("https://" + host + "/api/organization/"+orgID);
			
			
			for (String temp : JSONResponse){
				try {
					network.put("facebook", jsonParser(temp, "payload.socialNetworkPages.0.link").toString());
					network.put("twitter", jsonParser(temp, "payload.socialNetworkPages.1.link").toString());
					network.put("instagram", jsonParser(temp, "payload.socialNetworkPages.2.link").toString());
					network.put("pinterest", jsonParser(temp, "payload.socialNetworkPages.3.link").toString());
					network.put("youTube", jsonParser(temp, "payload.socialNetworkPages.4.link").toString());
					network.put("linkedin", jsonParser(temp, "payload.socialNetworkPages.5.link").toString());
					network.put("tumbler", jsonParser(temp, "payload.socialNetworkPages.6.link").toString());
							
				} catch (JSONException e) {
					logger.error("", e);
				}
			}
			
		} catch (URISyntaxException | IOException e) {
			logger.error("", e);
		}
		return network;
		
	}

	public HttpClient createCustomField(JSONObject customField){
		try {
			CloseableHttpResponse resp = sendPOSTRequest("https://" + host + "/api/customField", customField.toString());
            logger.info("Response status code is " + resp.getStatusLine().getStatusCode());
		} catch (URISyntaxException | IOException e) {
			logger.error("", e);
		}
		return this;

	}

	private void updateHeaders() {
		try {
			httpost = new HttpPost();
			httpget = new HttpGet();
		} catch (Exception e) {
			logger.error("", e);
		}
		httpost.addHeader("Connection", "keep-alive");
		httpost.addHeader("content-type", "application/json");
        httpost.addHeader("Referer", "https://" + host + "/");
        httpost.addHeader("Accept-Language", "en-US,en;q=0.5");            
        httpost.addHeader("authToken", authToken);
        httpost.addHeader("Pragma", "no-cache");            
        httpost.addHeader("Accept", "application/json, text/plain, */*");
        httpost.addHeader("Content-Type", "application/json; charset=UTF-8");
        httpost.addHeader("Cache-Control", "no-cache");            
        httpost.addHeader("Accept-Encoding", "gzip, deflate");
        httpost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0");
        
        
       /* Connection: keep-alive
        Referer: https://hq.uat.ignite.net/
        Accept-Language: ru-RU,ru;q=0.8,en-US;q=0.5,en;q=0.3
        authToken: Lsd5VonRzgNkKjdZfPiuSkr-mu544i4de-PtlIubSTHbNhcSb2MlsCMFVeQGsGJWRTQyfbMOIuTCIu0XJxZ_ngxchpEcnussPS8WNaL_SDYFG0CiJsc_0uhmtbz_KNVcwLgTZdvGra_RmHDy4wyFHJEmqK6xx0vscUGMb_b9ue-RenN6PQlULqECzC2Te5LE5szHAW_nzIf7vPCpn63-m4M2LWYU29CXaXCcuwT47naUItMqCBuHMsgRXOMSqs6c
        Accept-Encoding: gzip, deflate, br
        User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64; rv:51.0) Gecko/20100101 Firefox/51.0
        Accept: application/json, text/plain, 
        Host: hq.uat.ignite.net*/
        httpget.addHeader("Connection", "keep-alive");
        httpget.addHeader("Referer", "https://" + host + "/");
        httpget.addHeader("Accept-Language", "ru-RU,ru;q=0.8,en-US;q=0.5,en;q=0.3"); 
        httpget.addHeader("authToken", authToken);
        httpget.addHeader("Accept-Encoding", "gzip, deflate, br");
        httpget.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:51.0) Gecko/20100101 Firefox/51.0");
        httpget.addHeader("Accept", "application/json, text/plain,");
        httpget.addHeader("Host", host);
        
        
		/*httpget.addHeader("content-type", "application/json");
        httpget.addHeader("Pragma", "no-cache");            
        httpget.addHeader("Content-Type", "application/json; charset=UTF-8");
        httpget.addHeader("Cache-Control", "no-cache");*/            
        
        
	}
	
	static Object jsonParser(String jsonStr, String key) throws JSONException {
	    int i = 0;
	    Object temp = null;
	    Object json = new JSONObject(jsonStr);
	    String[] keys = key.split("[.]");
	    try {
			while (i < keys.length) {

				if (json instanceof JSONArray) {
					int index = Integer.parseInt(keys[i]);
					temp = ((JSONArray) json).get(index);
				} else if (json instanceof JSONObject) {
					temp = ((JSONObject) json).get(keys[i]);
				}
				json = temp;
				i++;
			}
		} catch (JSONException e) {
	    	temp = "";
		}
	    return temp;
	}
	
	private void getConnection() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
	   	SSLContextBuilder builder = new SSLContextBuilder();
        builder.loadTrustMaterial(null, new TrustStrategy(){
            public boolean isTrusted(X509Certificate[] chain, String authType)
                    throws CertificateException {
                    return true;
                }
            });
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());


        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", new PlainConnectionSocketFactory())
                .register("https", sslsf)
                .build();


        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
        cm.setMaxTotal(2000);
        httpClient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .setConnectionManager(cm)
                .build();
        //httpost = new HttpPost();
        //httpget = new HttpGet();
        updateHeaders();
	}
	
	private Integer findArrayElementNumberByValue(JSONArray jsonArray, String value) {
		for (int j = 0; j < ((JSONArray) jsonArray).length(); j++) {
			try {
				if (jsonArray.get(j).toString().contains(value)){
					return j;
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return -1;
	}
	
	private String getSupporterDetailsInJSON(String email) throws URISyntaxException, IOException, JSONException {
		sendGETRequest(new Supporter().getSupportersRequest(email, ""));//"https://" + host + "/api/search/supporters?criteria=" + email + "&listOffset=0&listResults=250&sortField=createdDate&sortOrder=DESCENDING");
		String temp = JSONResponse.get(0);
		String id = jsonParser(temp, "payload.supporters.0.id").toString();
		SeleneseTestCase.logger.info("Supporter ID: " + id);
		sendGETRequest(new Supporter().getSupporterRequest(id, true));//"https://" + host + "/api/person/supporter/" + id + "?includeCustomFields=true");
		return JSONResponse.get(0);
	}

	public String createGateway(JSONObject supp) {
		try {
			sendPOSTRequest("https://" + host + "/api/organization/paymentConfiguration", supp.toString());
			for (String temp : JSONResponse) {
				try {
					return jsonParser(temp, "payload.id").toString();
				} catch (JSONException e) {
					logger.error("", e);
				}
			}
		} catch (URISyntaxException | IOException e) {
			logger.error("", e);
		}
		return "";
	}

	public void waitUntilSupporterExists(String email, int seconds) {
		for(int i = 0; i <=seconds; i++) {
			try {
				sleep(1000);
				if(isSupporterExists(email)) break;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isSupporterExists(String email) {
		boolean isExists = true;
		try {
			sendGETRequest(new Supporter().getSupportersRequest(email, ""));
			String temp = JSONResponse.get(0);
			String id = jsonParser(temp, "payload.supporters.0.id").toString();
			if (id.isEmpty() || id=="") {
				isExists = false;
			logger.info("Supporter with email "+email+ " is not found.");}
		} catch (URISyntaxException | IOException | JSONException e) {
			e.printStackTrace();
		}
		return isExists;
	}
}
