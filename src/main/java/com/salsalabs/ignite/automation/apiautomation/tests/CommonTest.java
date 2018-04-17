package com.salsalabs.ignite.automation.apiautomation.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salsalabs.ignite.automation.apiautomation.models.ExpectedResult;
import com.salsalabs.ignite.automation.apiautomation.models.supporter.requests.CommonRequest;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
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
    protected void init(String authToken) {
        BasicConfigurator.configure();
        logger.info("Executing: " + this.getClass().getSimpleName());
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("authToken", authToken);
        restClient = new RestTemplate();
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
}
