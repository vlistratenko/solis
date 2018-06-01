package com.salsalabs.ignite.automation.suites.api.devApi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salsalabs.ignite.automation.apiautomation.config.Config;
import com.salsalabs.ignite.automation.apiautomation.models.ExpectedResult;
import com.salsalabs.ignite.automation.apiautomation.models.devApiModels.metrics.response.MetricsResponse;
import com.salsalabs.ignite.automation.apiautomation.models.devApiModels.submissions.fundraisingform.Request.FundraisingFormSubmissionsRequest;
import com.salsalabs.ignite.automation.apiautomation.models.devApiModels.submissions.fundraisingform.Response.FundraisingFormsSubmissions;
import com.salsalabs.ignite.automation.apiautomation.core.CommonTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static com.salsalabs.ignite.automation.apiautomation.config.Config.TEST_DATA_PATH_SUBMISSIONS;

public class GetFundraisingFormSubmissionsTest extends CommonTest {
    private MetricsResponse metricsBeforeExecution;

    @BeforeTest
    public void getTestData() throws IOException {
        loadExpectedResultObj(
                new HashMap<String, Class<? extends ExpectedResult>>() {{
                    put(TEST_DATA_PATH_SUBMISSIONS + "fundraising_form_submissions_response.json", FundraisingFormsSubmissions.class);

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

    @Parameters({"ENV"})
    @Test(priority = 1)
    public void getFundraisingFormSubmissions(String env) throws IOException {

        FundraisingFormSubmissionsRequest requestObj = new ObjectMapper().readValue(new File(TEST_DATA_PATH_SUBMISSIONS + "fundraising_form_submissions_request.json"), FundraisingFormSubmissionsRequest.class);
        ResponseEntity<FundraisingFormsSubmissions> response =
                restClient.exchange(env + Config.Endpoints.LIST_OF_SUBMISSIONS, HttpMethod.POST, buildRequest(requestObj), FundraisingFormsSubmissions.class);

        FundraisingFormsSubmissions expectedResultObj = (FundraisingFormsSubmissions) getExpectedResult("fundraising_form_submissions_response.json");

        Assert.assertEquals(expectedResultObj.payload, response.getBody().payload);
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
        Assert.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
    }

    @Parameters({"ENV"})
    @Test(priority = 2)
    public void getMetricsAfterExecution(String env) throws IOException {
        ResponseEntity<MetricsResponse> response =
                restClient.exchange(env + Config.Endpoints.DEVAPIMETRICS, HttpMethod.GET, buildRequest(null), MetricsResponse.class);
        metricsBeforeExecution.getPayload().setActivitySubmissionList(metricsBeforeExecution.getPayload().getActivitySubmissionList() + 1);
        metricsBeforeExecution.getPayload().setTotalApiCalls(metricsBeforeExecution.getPayload().getTotalApiCalls() + 1);
        Assert.assertEquals(response.getBody().getPayload(), metricsBeforeExecution.getPayload());
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }
}
