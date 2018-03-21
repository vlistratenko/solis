package com.salsalabs.ignite.automation.suites.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salsalabs.ignite.automation.apiautomation.config.Config;
import com.salsalabs.ignite.automation.apiautomation.models.ExpectedResult;
import com.salsalabs.ignite.automation.apiautomation.models.supporter.requests.bydate.SupporterByDateRequest;
import com.salsalabs.ignite.automation.apiautomation.models.supporter.responses.bydate.SupporterByDateResponse;
import com.salsalabs.ignite.automation.apiautomation.tests.CommonTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static com.salsalabs.ignite.automation.apiautomation.config.Config.TEST_DATA_PATH_SUPPORTERS_REQUESTS;
import static com.salsalabs.ignite.automation.apiautomation.config.Config.TEST_DATA_PATH_SUPPORTERS_RESPONSES;

/**
 * Created by bcmbx on 02.10.2017.
 */
public class GetSupporterByDate extends CommonTest {

    @BeforeTest
    public void getTestData() throws IOException {
        loadExpectedResultObj(
                new HashMap<String, Class<? extends ExpectedResult>>()
                {{
                    put(TEST_DATA_PATH_SUPPORTERS_RESPONSES + "get_supporter_since_specific_date_response.json", SupporterByDateResponse.class);
                    put(TEST_DATA_PATH_SUPPORTERS_RESPONSES + "get_supporters_within_date_range_response.json", SupporterByDateResponse.class);
                }}
        );
    }

    @Parameters({"ENV"})
    @Test
    public void getSupporterSinceSpecificDate(String env) throws IOException {
        SupporterByDateRequest requestObj = new ObjectMapper().readValue(new File(TEST_DATA_PATH_SUPPORTERS_REQUESTS + "get_supporter_since_specific_date_request.json"), SupporterByDateRequest.class);
        ResponseEntity<SupporterByDateResponse> response =
                restClient.exchange(env + Config.Endpoints.SEARCH_SUPPORTER, HttpMethod.POST, buildRequest(requestObj), SupporterByDateResponse.class);

        SupporterByDateResponse expectedResultObj = (SupporterByDateResponse) getExpectedResult("get_supporter_since_specific_date_response.json");

        for (int i = 0; i <  expectedResultObj.getPayload().getSupporters().size(); i++) {
            Assert.assertEquals(expectedResultObj.getPayload().getSupporters().get(i), response.getBody().getPayload().getSupporters().get(i));
        }
        Assert.assertEquals(response.getBody().getPayload().getSupporters(), expectedResultObj.getPayload().getSupporters());
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }

    @Parameters({"ENV"})
    @Test
    public void getSupportersWithinDateRange(String env) throws IOException {
        SupporterByDateRequest requestObj = new ObjectMapper().readValue(new File(TEST_DATA_PATH_SUPPORTERS_REQUESTS + "get_supporters_within_date_range_request.json"), SupporterByDateRequest.class);
        ResponseEntity<SupporterByDateResponse> response =
                restClient.exchange(env + Config.Endpoints.SEARCH_SUPPORTER, HttpMethod.POST, buildRequest(requestObj), SupporterByDateResponse.class);

        SupporterByDateResponse expectedResultObj = (SupporterByDateResponse) getExpectedResult("get_supporters_within_date_range_response.json");

        for (int i = 0; i <  expectedResultObj.getPayload().getSupporters().size(); i++) {
            Assert.assertEquals(response.getBody().getPayload().getSupporters().get(i), expectedResultObj.getPayload().getSupporters().get(i));
        }
        Assert.assertEquals(expectedResultObj.getPayload().getSupporters(), response.getBody().getPayload().getSupporters());
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }


}
