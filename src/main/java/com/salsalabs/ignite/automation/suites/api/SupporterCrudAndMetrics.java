package com.salsalabs.ignite.automation.suites.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salsalabs.ignite.automation.apiautomation.models.ExpectedResult;
import com.salsalabs.ignite.automation.apiautomation.models.metrics.response.MetricsResponse;
import com.salsalabs.ignite.automation.apiautomation.models.supporter.requests.CreateSupporterRequest;
import com.salsalabs.ignite.automation.apiautomation.models.supporter.requests.GetSupporterRequest;
import com.salsalabs.ignite.automation.apiautomation.models.supporter.responses.Supporter;
import com.salsalabs.ignite.automation.apiautomation.models.supporter.responses.SupporterRecordResponse;
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

import static com.salsalabs.ignite.automation.apiautomation.config.Config.*;

public class SupporterCrudAndMetrics extends CommonTest {

    private String supporterInternalId;
    private MetricsResponse metricsBeforeExecution;

    @BeforeTest
    public void getTestData() throws IOException {
        loadExpectedResultObj(
                new HashMap<String, Class<? extends ExpectedResult>>()
                {{
                    put(TEST_DATA_PATH_SUPPORTERS_RESPONSES + "get_supporter_response.json", SupporterRecordResponse.class);
                    put(TEST_DATA_PATH_SUPPORTERS_RESPONSES + "create_supporter_response.json", SupporterRecordResponse.class);
                    put(TEST_DATA_PATH_SUPPORTERS_RESPONSES + "delete_supporter_response.json", SupporterRecordResponse.class);
                }}
        );
    }


    @Parameters({"ENV"})
    @Test
    public void getMetricsBeforeExecution(String env) throws IOException {
        ResponseEntity<MetricsResponse> response =
                restClient.exchange(env + Endpoints.METRICS, HttpMethod.GET, buildRequest(null), MetricsResponse.class);
        metricsBeforeExecution = response.getBody();
        Assert.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }

    @Parameters({"ENV"})
    @Test(dependsOnMethods={"getMetricsBeforeExecution"})
    public void createSupporter(String env) throws IOException {
        CreateSupporterRequest requestObj = new ObjectMapper().readValue(new File(TEST_DATA_PATH_SUPPORTERS_REQUESTS + "create_supporter_request.json"), CreateSupporterRequest.class);
        ResponseEntity<SupporterRecordResponse> response =
                restClient.exchange(env + Endpoints.ADD_UPDATE_DELETE_SUPPORTER, HttpMethod.PUT, buildRequest(requestObj), SupporterRecordResponse.class);
        for (Supporter supporter : response.getBody().getPayload().getSupporters()) {
            supporterInternalId = supporter.getSupporterId();
        }

        SupporterRecordResponse expectedResultObj = ((SupporterRecordResponse) getExpectedResult("create_supporter_response.json"));
        (expectedResultObj.getPayload().getSupporters().get(0)).setSupporterId(supporterInternalId);

        Assert.assertEquals(expectedResultObj.getPayload().getSupporters(), response.getBody().getPayload().getSupporters());
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }

    @Parameters({"ENV"})
    @Test( groups = "a", dependsOnMethods={"createSupporter", "getMetricsBeforeExecution"})
    public void getSupporterByEmail(String env) throws IOException {
        GetSupporterRequest requestObj = new ObjectMapper().
                readValue(new File(TEST_DATA_PATH_SUPPORTERS_REQUESTS + "get_supporter_by_email_request.json"), GetSupporterRequest.class);
        SupporterRecordResponse actualResult = restClient.postForObject(env + Endpoints.SEARCH_SUPPORTER,
                buildRequest(requestObj), SupporterRecordResponse.class);

        SupporterRecordResponse expectedResultObj = ((SupporterRecordResponse) getExpectedResult("get_supporter_response.json"));
        (expectedResultObj.getPayload().getSupporters().get(0)).setSupporterId(supporterInternalId);

        Assert.assertEquals(actualResult.getPayload().getSupporters(), expectedResultObj.getPayload().getSupporters());
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }

    @Parameters({"ENV"})
    @Test( groups = "a", dependsOnMethods={"createSupporter", "getMetricsBeforeExecution"})
    public void getSupporterByInternalId(String env) throws IOException {
        GetSupporterRequest requestObj = new ObjectMapper().readValue(new File(TEST_DATA_PATH_SUPPORTERS_REQUESTS + "get_supporter_by_id_request.json"), GetSupporterRequest.class);
        requestObj.getPayload().getIdentifiers().set(0, supporterInternalId);
        SupporterRecordResponse actualResult = restClient.postForObject(env + Endpoints.SEARCH_SUPPORTER, buildRequest(requestObj), SupporterRecordResponse.class);

        SupporterRecordResponse expectedResultObj = ((SupporterRecordResponse) getExpectedResult("get_supporter_response.json"));
        (expectedResultObj.getPayload().getSupporters().get(0)).setSupporterId(supporterInternalId);

        Assert.assertEquals(actualResult.getPayload().getSupporters(), expectedResultObj.getPayload().getSupporters());
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }

    @Parameters({"ENV"})
    @Test( groups = "a", dependsOnMethods={"createSupporter", "getMetricsBeforeExecution"})
    public void getSupporterByExternalId(String env) throws IOException {
        GetSupporterRequest requestObj = new ObjectMapper().readValue(new File(TEST_DATA_PATH_SUPPORTERS_REQUESTS + "get_supporter_by_external_id_request.json"), GetSupporterRequest.class);
        SupporterRecordResponse actualResult = restClient.postForObject(env + Endpoints.SEARCH_SUPPORTER, buildRequest(requestObj), SupporterRecordResponse.class);

        SupporterRecordResponse expectedResultObj = ((SupporterRecordResponse) getExpectedResult("get_supporter_response.json"));
        (expectedResultObj.getPayload().getSupporters().get(0)).setSupporterId(supporterInternalId);

        Assert.assertEquals(actualResult.getPayload().getSupporters(), expectedResultObj.getPayload().getSupporters());
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }

    @Parameters({"ENV"})
    @Test( groups = "a", dependsOnMethods={"createSupporter", "getMetricsBeforeExecution"})
    public void updateSupporter(String env) throws IOException {
        CreateSupporterRequest requestObj = new ObjectMapper().readValue(new File(TEST_DATA_PATH_SUPPORTERS_REQUESTS + "update_supporter_request.json"), CreateSupporterRequest.class);
        requestObj.getPayload().getSupporters().get(0).setSupporterId(supporterInternalId);
        ResponseEntity<SupporterRecordResponse> response =
                restClient.exchange(env + Endpoints.ADD_UPDATE_DELETE_SUPPORTER, HttpMethod.PUT, buildRequest(requestObj), SupporterRecordResponse.class);

        SupporterRecordResponse expectedResultObj = ((SupporterRecordResponse) getExpectedResult("create_supporter_response.json"));
        expectedResultObj.getPayload().getSupporters().get(0).setSupporterId(supporterInternalId);
        expectedResultObj.getPayload().getSupporters().get(0).getContacts().get(0).setValue("updated_addedviaapi.57xdxscd@mailosaur.io");

        Assert.assertEquals(expectedResultObj.getPayload().getSupporters(), response.getBody().getPayload().getSupporters());
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }

    @Parameters({"ENV"})
    @Test(groups = "a", dependsOnMethods={"createSupporter", "updateSupporter", "getMetricsBeforeExecution"})
    public void deleteSupporter(String env) throws IOException {
        CreateSupporterRequest requestObj = new ObjectMapper().readValue(new File(TEST_DATA_PATH_SUPPORTERS_REQUESTS + "delete_supporter_request.json"), CreateSupporterRequest.class);
        requestObj.getPayload().getSupporters().get(0).setSupporterId(supporterInternalId);
        ResponseEntity<SupporterRecordResponse> response =
                restClient.exchange(env + Endpoints.ADD_UPDATE_DELETE_SUPPORTER, HttpMethod.DELETE, buildRequest(requestObj), SupporterRecordResponse.class);

        SupporterRecordResponse expectedResultObj = ((SupporterRecordResponse) getExpectedResult("delete_supporter_response.json"));
        expectedResultObj.getPayload().getSupporters().get(0).setSupporterId(supporterInternalId);

        Assert.assertEquals(expectedResultObj.getPayload().getSupporters(), response.getBody().getPayload().getSupporters());
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }

    @Parameters({"ENV"})
    @Test(dependsOnGroups = "a")
    public void getMetricsAfterExecution(String env) throws IOException {
        ResponseEntity<MetricsResponse> response =
                restClient.exchange(env + Endpoints.METRICS, HttpMethod.GET, buildRequest(null), MetricsResponse.class);

        metricsBeforeExecution.getPayload().setSupporterAdd(metricsBeforeExecution.getPayload().getSupporterAdd() + 1);
        metricsBeforeExecution.getPayload().setSupporterDelete(metricsBeforeExecution.getPayload().getSupporterDelete() + 1);
        metricsBeforeExecution.getPayload().setSupporterRead(metricsBeforeExecution.getPayload().getSupporterRead() + 3);
        metricsBeforeExecution.getPayload().setSupporterUpdate(metricsBeforeExecution.getPayload().getSupporterUpdate() + 1);
        metricsBeforeExecution.getPayload().setTotalAPICalls(metricsBeforeExecution.getPayload().getTotalAPICalls() + 6);

        Assert.assertEquals(metricsBeforeExecution, response.getBody());
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }
}
