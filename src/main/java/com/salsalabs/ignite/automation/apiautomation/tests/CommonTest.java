package com.salsalabs.ignite.automation.apiautomation.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salsalabs.ignite.automation.apiautomation.models.ExpectedResult;
import com.salsalabs.ignite.automation.apiautomation.models.supporter.requests.CommonRequest;
import com.salsalabs.ignite.automation.common.JSONCompareUtil;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import com.google.gson.*;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;


public class CommonTest {

    static protected Logger logger = Logger.getLogger(CommonTest.class);
    protected HttpHeaders headers;
    protected Map<String, ExpectedResult> expectedResult = new HashMap<String, ExpectedResult>();
    protected RestTemplate restClient;
    protected HttpEntity request;

    @Parameters({"AUTH_TOKEN"})
    @BeforeClass
    protected void init(String authToken) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        BasicConfigurator.configure();
        logger.info("Executing: " + this.getClass().getSimpleName());
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("authToken", authToken);
        restClient = restTemplate();
    }

    protected void loadExpectedResultObj(Map<String, Class<? extends ExpectedResult>> expectedObjects) throws IOException {
        System.err.println(System.getProperty("user.dir"));
        ObjectMapper mapper = new ObjectMapper();
        for (Map.Entry<String, Class<? extends ExpectedResult>> entry : expectedObjects.entrySet()) {
            String key = entry.getKey();
            Class<? extends ExpectedResult> value = entry.getValue();
            expectedResult.put(extractFileNameFromPath(key), mapper.readValue(new File(key), value));
        }
    }

    protected HttpEntity buildRequest(CommonRequest body) {
        return new HttpEntity(body, headers);
    }

    protected HttpEntity buildRequest(CommonRequest body, HttpHeaders headers) {
        return new HttpEntity(body, headers);
    }

    protected ExpectedResult getExpectedResult(String jsonFileName) {
        for (Map.Entry<String, ExpectedResult> entry : expectedResult.entrySet()) {
            if (entry.getKey().equals(jsonFileName)) {
                return entry.getValue();
            }
        }
        return null;
    }

    private String extractFileNameFromPath(String path) {
        return path.substring(path.lastIndexOf("\\") + 1, path.length());
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }
    
    public void assertJSON(Object expected, Object actual/*, String message*/) throws JSONException{
    	try {
    		String exp = new Gson().toJson(expected);
    		String act = new Gson().toJson(actual);
    		String[] ignoredNodes = {"rateLimit",
    				"lastApiCall",
    				"totalApiCalls",
    				"currentRateLimit",
    				"header.processingTime",
    				"payload.currentRateLimit",
    				"payload.lastAPICall",
    				"payload.totalAPICalls",
    				"timestamp"
    				};
    		JSONCompareUtil.jsonXAssertEqualsIgnoreNodes("Response Match : ", exp, act, ignoredNodes, JSONCompareMode.NON_EXTENSIBLE);//, new DynamicValueTokenComparator(JSONCompareMode.NON_EXTENSIBLE)
		} catch (AssertionError e) {
			logger.error("Expected:" + expected);
			logger.error("Actual:" + actual);
			throw new JSONException(e.getMessage());
		}
    }

    public RestTemplate restTemplate()
            throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                .loadTrustMaterial(null, acceptingTrustStrategy)
                .build();

        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(csf)
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory();

        requestFactory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        return restTemplate;
    }
    
    

}
