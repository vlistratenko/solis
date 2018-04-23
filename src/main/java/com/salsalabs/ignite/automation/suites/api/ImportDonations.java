package com.salsalabs.ignite.automation.suites.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salsalabs.ignite.automation.apiautomation.models.ExpectedResult;
import com.salsalabs.ignite.automation.apiautomation.models.activity.getactivitybyid.request.GetActivityByIdRequest;
import com.salsalabs.ignite.automation.apiautomation.models.activity.getactivitybyid.response.GetActivityByIdResponse;
import com.salsalabs.ignite.automation.apiautomation.models.donations.request.ImportDonationRequest;
import com.salsalabs.ignite.automation.apiautomation.models.donations.response.ImportDonationResponse;
import com.salsalabs.ignite.automation.apiautomation.models.metrics.response.MetricsResponse;
import com.salsalabs.ignite.automation.apiautomation.tests.CommonTest;
import com.salsalabs.ignite.automation.apiautomation.utils.EmailGenerator;
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

/**
 * Created by bcmbx on 04.10.2017.
 */
public class ImportDonations extends CommonTest {

    private MetricsResponse metricsBeforeExecution;
    private String email1;
    private String email2;
    private String activityId1;
    private String activityId2;

    @BeforeTest
    public void getTestData() throws IOException {
        loadExpectedResultObj(
                new HashMap<String, Class<? extends ExpectedResult>>()
                {{
                    put(TEST_DATA_PATH_DONATIONS_RESPONSES + "import_donation_response.json", ImportDonationResponse.class);
                    put(TEST_DATA_PATH_DONATIONS_RESPONSES + "get_imported_donations_response1.json", GetActivityByIdResponse.class);
                    put(TEST_DATA_PATH_DONATIONS_RESPONSES + "get_imported_donations_response2.json", GetActivityByIdResponse.class);
                    put(TEST_DATA_PATH_DONATIONS_RESPONSES + "update_donation_response.json", ImportDonationResponse.class);
                    put(TEST_DATA_PATH_DONATIONS_RESPONSES + "refund_transaction_response.json", ImportDonationResponse.class);
                    put(TEST_DATA_PATH_DONATIONS_RESPONSES + "get_imported_transaction_after_refund_response.json", GetActivityByIdResponse.class);
                }}
        );
        email1 = EmailGenerator.getRandomEmail();
        email2 = EmailGenerator.getRandomEmail();
    }


    @Parameters({"ENV"})
    @Test(priority = 0)
    public void getMetricsBeforeExecution(String env) throws IOException {
        ResponseEntity<MetricsResponse> response =
                restClient.exchange(env + Endpoints.METRICS, HttpMethod.GET, buildRequest(null), MetricsResponse.class);
        metricsBeforeExecution = response.getBody();
        Assert.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }

    @Parameters({"ENV"})
    @Test(priority = 1)
    public void importDonations(String env) throws IOException {
        ImportDonationRequest requestObj = new ObjectMapper().readValue(new File(TEST_DATA_PATH_DONATIONS_REQUESTS + "import_donation_request.json"), ImportDonationRequest.class);
        requestObj.getPayload().getDonations().get(0).getSupporter().getContacts().get(0).setValue(email1);
        requestObj.getPayload().getDonations().get(0).setGatewayTransactionId(email1.substring(0, 31));
        requestObj.getPayload().getDonations().get(1).getSupporter().getContacts().get(0).setValue(email2);
        requestObj.getPayload().getDonations().get(1).setGatewayTransactionId(email2.substring(0, 31));
        ResponseEntity<ImportDonationResponse> response =
                restClient.exchange(env + Endpoints.DONATIONS_IMPORT, HttpMethod.POST, buildRequest(requestObj), ImportDonationResponse.class);
        ImportDonationResponse expectedResultObj = ((ImportDonationResponse) getExpectedResult("import_donation_response.json"));
        expectedResultObj.getPayload().getDonations().get(0).getSupporter().getContacts().get(0).setValue(email1);
        expectedResultObj.getPayload().getDonations().get(0).setGatewayTransactionId(email1.substring(0, 31));
        expectedResultObj.getPayload().getDonations().get(1).getSupporter().getContacts().get(0).setValue(email2);
        expectedResultObj.getPayload().getDonations().get(1).setGatewayTransactionId(email2.substring(0, 31));
        activityId1 = response.getBody().getPayload().getDonations().get(0).getActivityId();
        activityId2 = response.getBody().getPayload().getDonations().get(1).getActivityId();
        Assert.assertEquals(response.getBody().getPayload().getDonations(), expectedResultObj.getPayload().getDonations());
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }

    @Parameters({"ENV"})
    @Test(priority = 2)
    public void getImportedDonation(String env) throws IOException, InterruptedException {
        GetActivityByIdRequest requestObj1 = new ObjectMapper().readValue(new File(TEST_DATA_PATH_DONATIONS_REQUESTS + "get_imported_donations_request.json"), GetActivityByIdRequest.class);
        requestObj1.getPayload().getActivityIds().set(0, activityId1);

        ResponseEntity<GetActivityByIdResponse> response1 =
                restClient.exchange(env + Endpoints.SEARCH_ACTIVITIES, HttpMethod.POST, buildRequest(requestObj1), GetActivityByIdResponse.class);
        GetActivityByIdResponse expectedResultObj1 = ((GetActivityByIdResponse) getExpectedResult("get_imported_donations_response1.json"));
        expectedResultObj1.getPayload().getActivities().get(0).setActivityId(activityId1);
        expectedResultObj1.getPayload().getActivities().get(0).getTransactions().get(0).setGatewayTransactionId(email1.substring(0, 31));
        GetActivityByIdRequest requestObj2 = new ObjectMapper().readValue(new File(TEST_DATA_PATH_DONATIONS_REQUESTS + "get_imported_donations_request.json"), GetActivityByIdRequest.class);
        requestObj2.getPayload().getActivityIds().set(0, activityId2);
        ResponseEntity<GetActivityByIdResponse> response2 =
                restClient.exchange(env + Endpoints.SEARCH_ACTIVITIES, HttpMethod.POST, buildRequest(requestObj2), GetActivityByIdResponse.class);
        GetActivityByIdResponse expectedResultObj2 = ((GetActivityByIdResponse) getExpectedResult("get_imported_donations_response2.json"));
        expectedResultObj2.getPayload().getActivities().get(0).setActivityId(activityId2);
        expectedResultObj2.getPayload().getActivities().get(0).getTransactions().get(0).setGatewayTransactionId(email2.substring(0, 31));
        Assert.assertEquals(response1.getBody().getPayload().getActivities(), expectedResultObj1.getPayload().getActivities());
        Assert.assertEquals(response2.getBody().getPayload().getActivities(), expectedResultObj2.getPayload().getActivities());
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }

    @Parameters({"ENV"})
    @Test(priority = 3)
    public void updateImportedDonation(String env) throws IOException {
        ImportDonationRequest requestObj = new ObjectMapper().readValue(new File(TEST_DATA_PATH_DONATIONS_REQUESTS + "update_donation_request.json"), ImportDonationRequest.class);
        requestObj.getPayload().getDonations().get(0).getSupporter().getContacts().get(0).setValue(email1);
        requestObj.getPayload().getDonations().get(0).setGatewayTransactionId(email1.substring(0, 31));
        ResponseEntity<ImportDonationResponse> response =
                restClient.exchange(env + Endpoints.DONATIONS_IMPORT, HttpMethod.POST, buildRequest(requestObj), ImportDonationResponse.class);
        ImportDonationResponse expectedResultObj = ((ImportDonationResponse) getExpectedResult("update_donation_response.json"));
        expectedResultObj.getPayload().getDonations().get(0).getSupporter().getContacts().get(0).setValue(email1);
        expectedResultObj.getPayload().getDonations().get(0).setGatewayTransactionId(email1.substring(0, 31));
        Assert.assertEquals(response.getBody().getPayload().getDonations(), expectedResultObj.getPayload().getDonations());
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }

    @Parameters({"ENV"})
    @Test(priority = 4)
    public void refundDonation(String env) throws IOException {
        ImportDonationRequest requestObj = new ObjectMapper().readValue(new File(TEST_DATA_PATH_DONATIONS_REQUESTS + "refund_transaction_request.json"), ImportDonationRequest.class);
        requestObj.getPayload().getDonations().get(0).getSupporter().getContacts().get(0).setValue(email1);
        requestObj.getPayload().getDonations().get(0).setGatewayTransactionId(email1.substring(0, 31));
        ResponseEntity<ImportDonationResponse> response =
                restClient.exchange(env + Endpoints.DONATIONS_IMPORT, HttpMethod.POST, buildRequest(requestObj), ImportDonationResponse.class);
        ImportDonationResponse expectedResultObj = ((ImportDonationResponse) getExpectedResult("refund_transaction_response.json"));
        expectedResultObj.getPayload().getDonations().get(0).getSupporter().getContacts().get(0).setValue(email1);
        expectedResultObj.getPayload().getDonations().get(0).setGatewayTransactionId(email1.substring(0, 31));
        Assert.assertEquals(response.getBody().getPayload().getDonations(), expectedResultObj.getPayload().getDonations());
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }

    @Parameters({"ENV"})
    @Test(priority = 5)
    public void getImportedDonationAfterRefund(String env) throws IOException, InterruptedException {
        GetActivityByIdRequest requestObj = new ObjectMapper().readValue(new File(TEST_DATA_PATH_DONATIONS_REQUESTS + "get_imported_donations_request.json"), GetActivityByIdRequest.class);
        requestObj.getPayload().getActivityIds().set(0, activityId1);
        ResponseEntity<GetActivityByIdResponse> response =
                restClient.exchange(env + Endpoints.SEARCH_ACTIVITIES, HttpMethod.POST, buildRequest(requestObj), GetActivityByIdResponse.class);
        GetActivityByIdResponse expectedResultObj = ((GetActivityByIdResponse) getExpectedResult("get_imported_transaction_after_refund_response.json"));
        expectedResultObj.getPayload().getActivities().get(0).setActivityId(activityId1);
        expectedResultObj.getPayload().getActivities().get(0).getTransactions().get(0).setGatewayTransactionId(email1.substring(0, 31));
        expectedResultObj.getPayload().getActivities().get(0).getTransactions().get(1).setGatewayTransactionId(email1.substring(0, 31));
        Assert.assertEquals(response.getBody().getPayload().getActivities(), expectedResultObj.getPayload().getActivities());
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }

    @Parameters({"ENV"})
    @Test(priority = 3)
    public void getMetricsAfterExecution(String env) throws IOException {
        ResponseEntity<MetricsResponse> response =
                restClient.exchange(env + Endpoints.METRICS, HttpMethod.GET, buildRequest(null), MetricsResponse.class);
        metricsBeforeExecution.getPayload().setOfflineDonationAdd(metricsBeforeExecution.getPayload().getOfflineDonationAdd() + 2);
        metricsBeforeExecution.getPayload().setActivityFundraise(metricsBeforeExecution.getPayload().getActivityFundraise() + 2);
        metricsBeforeExecution.getPayload().setTotalAPICalls(metricsBeforeExecution.getPayload().getTotalAPICalls() + 6);
        Assert.assertEquals(response.getBody().getPayload(), metricsBeforeExecution.getPayload());
        Assert.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }
}
