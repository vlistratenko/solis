package com.salsalabs.ignite.automation.suites.api.devApi;

import com.salsalabs.ignite.automation.apiautomation.config.Config;
import com.salsalabs.ignite.automation.apiautomation.models.ExpectedResult;
import com.salsalabs.ignite.automation.apiautomation.models.devApiModels.metadata.p2peventform.P2PEventFormMetaData;
import com.salsalabs.ignite.automation.apiautomation.models.devApiModels.metrics.response.MetricsResponse;
import com.salsalabs.ignite.automation.apiautomation.core.CommonTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

import static com.salsalabs.ignite.automation.apiautomation.config.Config.TEST_DATA_PATH_EVENT_FORm_SUMMARY_RESPONSE;

public class GetP2PEventFormMetadataTest extends CommonTest {
    private MetricsResponse metricsBeforeExecution;

    @BeforeTest
    public void getTestData() throws IOException {
        loadExpectedResultObj(
                new HashMap<String, Class<? extends ExpectedResult>>()
                {{
                    put(TEST_DATA_PATH_EVENT_FORm_SUMMARY_RESPONSE + "get_p2p_event_form_metadata.json", P2PEventFormMetaData.class);

                }}
        );
    }

    @Parameters({"ENV"})
    @Test(priority = 0)
    public void getMetricsBeforeExecution(String env) throws IOException {
        ResponseEntity<MetricsResponse> response =
                restClient.exchange(env + Config.Endpoints.DEVAPIMETRICS, HttpMethod.GET, buildRequest(null), MetricsResponse.class);
        metricsBeforeExecution = response.getBody();
        Assert.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }

    @Parameters({"ENV" , "UUID"})
    @Test(priority = 1)
    public void getP2PEventFormMetadata(String env, String uuid) throws IOException {
        ResponseEntity<P2PEventFormMetaData> response =
                restClient.exchange(env + Config.Endpoints.ACTIVITYCORESUMMARY + uuid + "/metadata", HttpMethod.GET, buildRequest(null), P2PEventFormMetaData.class);
        P2PEventFormMetaData expectedResultObj = ((P2PEventFormMetaData) getExpectedResult("get_p2p_event_form_metadata.json"));
        Assert.assertEquals( response.getBody().getPayload(), expectedResultObj.getPayload());
        Assert.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
    }

    @Parameters({"ENV"})
    @Test(priority = 2)
    public void getMetricsAfterExecution(String env) throws IOException {
        ResponseEntity<MetricsResponse> response =
                restClient.exchange(env + Config.Endpoints.DEVAPIMETRICS, HttpMethod.GET, buildRequest(null), MetricsResponse.class);
        metricsBeforeExecution.getPayload().setActivityFormMetadata(metricsBeforeExecution.getPayload().getActivityFormMetadata()+1);
        metricsBeforeExecution.getPayload().setTotalApiCalls(metricsBeforeExecution.getPayload().getTotalApiCalls()+1);
        Assert.assertEquals(response.getBody().getPayload(), metricsBeforeExecution.getPayload());
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }
}
