package selenium;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class HttpClient {

	String authToken = "";
	String orgID = "";
	String orgName = "";
	String host = "hq.test.ignite.net";
	CloseableHttpClient httpClient = null;
    HttpPost httpost = null;
    CloseableHttpResponse response = null;
    ArrayList<String> JSONResponse = new ArrayList<String>();
	
	public HttpClient() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
  	
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
        httpost = new HttpPost();
        updateHeaders();
	}
	
	public HttpClient login(String userName, String pass) throws URISyntaxException, ClientProtocolException, IOException {
		updateHeaders();
		sendRequest("https://" + host + "/api/auth/login.json", "{\"header\":{},\"payload\":{\"username\":\"" + userName + "\",\"password\":\"" + pass + "\"}}");
		Header h = response.getHeaders("authToken")[0];
		authToken = h.getValue();  
		updateHeaders();
		for (String temp : JSONResponse) {
		    	 try {
					orgID = jsonParser(temp, "payload.organization.0.id").toString();
					orgName = jsonParser(temp, "payload.organization.0.name").toString();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 }
		 String req =  "{\"header\":{},\"payload\":{\"organization\":[{\"id\":\"" + orgID + "\",\"name\":\"" + orgName + "\"}]}}"; 
		 sendRequest("https://" + host + "/api/auth/login.json", req);
		 h = response.getHeaders("authToken")[0];
		 authToken = h.getValue();
		 updateHeaders();
		 return this;
	}
	
	public HttpClient login() {
		try {
			return login(CommonUtils.getProperty("Admin.email"), CommonUtils.getProperty("Admin.Password"));
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * 
	 */
	public String createSupporter(JSONObject supp) {
		try {
			sendRequest("https://" + host + "/api/person/supporter", supp.toString());
	         for (String temp : JSONResponse) {
	            	 try {
						return jsonParser(temp, "payload.id").toString();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						System.err.println(temp);
						e.printStackTrace();
					}
	         } 
		} catch (URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	
	/*public String unsubscribeSupporter(String supEmail) {
		//"{"header":{},"payload":{"pid":"75004f53-9bf1-4a79-9228-bd9f36dd1bea","oid":"a9866e25-e0c0-4369-be68-e2cbd5f6842b","aid":"00000000-0000-0000-0000-000000000000","cid":"null","eid":"8338a3cb-b2dd-4d9e-95ec-744ca7170628","eType":"EmailBlastContent","data":{"PersonContact@Email@Value":{"value":"teswt.dsf@gsdg.vcdv","label":"Email Address","required":true}},"contentChannels":[],"contactMethods":[]}}"
	}*/
	
	////////////////////////////////////////////////////////////////////
	private CloseableHttpResponse sendRequest(String url, String json) throws URISyntaxException, ClientProtocolException, IOException {
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
        return 	response;	 
	}
	
	private void updateHeaders() {
		try {
			httpost.removeHeaders("authToken");
		} catch (Exception e) {
			// TODO: handle exception
		}
		httpost.addHeader("Connection", "keep-alive");
		httpost.addHeader("content-type", "application/json");
        httpost.addHeader("Referer", "https://hq.test.ignite.net/");
        httpost.addHeader("Accept-Language", "en-US,en;q=0.5");            
        httpost.addHeader("authToken", authToken);
        httpost.addHeader("Pragma", "no-cache");            
        httpost.addHeader("Accept", "application/json, text/plain, */*");
        httpost.addHeader("Content-Type", "application/json; charset=UTF-8");
        httpost.addHeader("Cache-Control", "no-cache");            
        httpost.addHeader("Accept-Encoding", "gzip, deflate");
        httpost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0");

	}
	
	static Object jsonParser(String jsonStr, String key) throws JSONException {
	    int i = 0;
	    Object temp = null;
	    Object json = new JSONObject(jsonStr);
	    String[] keys = key.split("[.]");
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
	    return temp;
	}
}
