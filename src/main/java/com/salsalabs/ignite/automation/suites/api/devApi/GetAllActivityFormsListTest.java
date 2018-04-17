package com.salsalabs.ignite.automation.suites.api.devApi;

import com.salsalabs.ignite.automation.apiautomation.config.Config;
import com.salsalabs.ignite.automation.apiautomation.models.ExpectedResult;
import com.salsalabs.ignite.automation.apiautomation.models.devApiModels.activity.allactivityformslist.AllActivityFormsList;
import com.salsalabs.ignite.automation.apiautomation.models.devApiModels.metadata.fundraisingform.FundraisingFormMetaData;
import com.salsalabs.ignite.automation.apiautomation.models.devApiModels.metrics.response.MetricsResponse;
import com.salsalabs.ignite.automation.apiautomation.tests.CommonTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;

import static com.salsalabs.ignite.automation.apiautomation.config.Config.Endpoints.ACTIVITYCORESUMMARY;
import static com.salsalabs.ignite.automation.apiautomation.config.Config.TEST_DATA_PATH_ACTIVITY_LIST_RESPONSE;

public class GetAllActivityFormsListTest extends CommonTest {
    private MetricsResponse metricsBeforeExecution;

    @BeforeTest
    public void getTestData() throws IOException {
        loadExpectedResultObj(
                new HashMap<String, Class<? extends ExpectedResult>>()
                {{
                    put(TEST_DATA_PATH_ACTIVITY_LIST_RESPONSE + "get_all_activity_forms_list.json", AllActivityFormsList.class);
                }}
        );
    }

    @Parameters({ "ENV" })
    @Test(priority = 0)
    public void getMetricsBeforeExecution(String env) throws IOException {
        ResponseEntity<MetricsResponse> response = restClient.exchange(env + Config.Endpoints.DEVAPIMETRICS,
                HttpMethod.GET, buildRequest(null), MetricsResponse.class);
        metricsBeforeExecution = response.getBody();
        Assert.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }

    @Parameters({"ENV"})
    @Test(priority = 1)
    public void getAllActivityFormsList(String env) throws IOException {
        final String parameters = "?types=SUBSCRIBE,FUNDRAISE,PETITION,TARGETED_LETTER,TICKETED_EVENT,P2P_EVENT&sortFIeld=description&sortOrder=ASCENDING&count=20";
        ResponseEntity<AllActivityFormsList> response =
                restClient.exchange(env + ACTIVITYCORESUMMARY + parameters, HttpMethod.GET, buildRequest(null), AllActivityFormsList.class);
        AllActivityFormsList expectedResultObj = ((AllActivityFormsList) getExpectedResult("get_all_activity_forms_list.json"));
        Assert.assertEquals(response.getBody().payload, expectedResultObj.payload);
        Assert.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
    }

    @Parameters({ "ENV" })
    @Test(priority = 2)
    public void getMetricsAfterExecution(String env) throws IOException {
        ResponseEntity<MetricsResponse> response = restClient.exchange(env + Config.Endpoints.DEVAPIMETRICS, HttpMethod.GET,
                buildRequest(null), MetricsResponse.class);
        metricsBeforeExecution.getPayload()
                .setActivityFormList(metricsBeforeExecution.getPayload().getActivityFormList() + 1);
        metricsBeforeExecution.getPayload()
                .setTotalApiCalls(metricsBeforeExecution.getPayload().getTotalApiCalls() + 1);
        Assert.assertEquals(response.getBody().getPayload(), metricsBeforeExecution.getPayload());
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }
}
