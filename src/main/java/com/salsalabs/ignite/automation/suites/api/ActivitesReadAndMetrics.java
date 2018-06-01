package com.salsalabs.ignite.automation.suites.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salsalabs.ignite.automation.apiautomation.config.Config;
import com.salsalabs.ignite.automation.apiautomation.models.ExpectedResult;
import com.salsalabs.ignite.automation.apiautomation.models.activity.getactivityfromdate.request.GetActivitesDateRequest;
import com.salsalabs.ignite.automation.apiautomation.models.activity.getactivityfromdate.response.old.GetActivitesFromDateResponse;
import com.salsalabs.ignite.automation.apiautomation.models.activity.getwithinperiod.request.GetActivitesWithinDateRequest;
import com.salsalabs.ignite.automation.apiautomation.models.getactivitybytype.request.GetActivityByTypeRequest;
import com.salsalabs.ignite.automation.apiautomation.models.getactivitybytype.response.event.GetActivityByTypeTicketedEventResponse;
import com.salsalabs.ignite.automation.apiautomation.models.getactivitybytype.response.p2p.GetActivityByTypeP2PResponse;
import com.salsalabs.ignite.automation.apiautomation.models.metrics.response.MetricsResponse;
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

/**
 * Created by bcmbx on 04.10.2017.
 */
public class ActivitesReadAndMetrics extends CommonTest {

    private MetricsResponse metricsBeforeExecution;

    @BeforeTest
    public void getTestData() throws IOException {
        loadExpectedResultObj(
                new HashMap<String, Class<? extends ExpectedResult>>()
                {{
                    put(TEST_DATA_PATH_ACTIVITIES_RESPONSES + "get_all_activities_from_date_response.json", GetActivitesFromDateResponse.class);
                    put(TEST_DATA_PATH_ACTIVITIES_RESPONSES + "get_all_activities_within_date_range_response.json", GetActivitesFromDateResponse.class);
                    put(TEST_DATA_PATH_ACTIVITIES_RESPONSES + "get_activity_by_type_fundraise_response.json", GetActivitesFromDateResponse.class);
                    put(TEST_DATA_PATH_ACTIVITIES_RESPONSES + "get_activity_by_type_p2p_event_response.json", GetActivityByTypeP2PResponse.class);
                    put(TEST_DATA_PATH_ACTIVITIES_RESPONSES + "get_activity_by_type_petition_response.json", GetActivitesFromDateResponse.class);
                    put(TEST_DATA_PATH_ACTIVITIES_RESPONSES + "get_activity_by_type_subscribe_response.json", GetActivitesFromDateResponse.class);
                    put(TEST_DATA_PATH_ACTIVITIES_RESPONSES + "get_activity_by_type_subscription_management_response.json", GetActivitesFromDateResponse.class);
                    put(TEST_DATA_PATH_ACTIVITIES_RESPONSES + "get_activity_by_type_targeted_letter_response.json", GetActivitesFromDateResponse.class);
                    put(TEST_DATA_PATH_ACTIVITIES_RESPONSES + "get_activity_by_type_ticketed_event_response.json", GetActivityByTypeTicketedEventResponse.class);
                    put(TEST_DATA_PATH_ACTIVITIES_RESPONSES + "get_activities_since_specific_date_response.json", com.salsalabs.ignite.automation.apiautomation.models.activity.getactivityfromdate.response.GetActivitesFromDateResponse.class);
                    put(TEST_DATA_PATH_ACTIVITIES_RESPONSES + "get_specific_activities_since_specific_date_response.json", com.salsalabs.ignite.automation.apiautomation.models.activity.getactivityfromdate.response.GetActivitesFromDateResponse.class);
                    put(TEST_DATA_PATH_ACTIVITIES_RESPONSES + "get_specific_activities_by_id_response.json", com.salsalabs.ignite.automation.apiautomation.models.activity.getactivityfromdate.response.GetActivitesFromDateResponse.class);
                }}
        );
    }


    @Parameters({"ENV"})
    @Test(priority = 0)
    public void getMetricsBeforeExecution(String env) throws IOException {
        ResponseEntity<MetricsResponse> response =
                restClient.exchange(env + Config.Endpoints.METRICS, HttpMethod.GET, buildRequest(null), MetricsResponse.class);
        metricsBeforeExecution = response.getBody();
        Assert.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }

    @Parameters({"ENV"})
    @Test(priority = 1)
    public void getAllActivities(String env) throws IOException {
        GetActivitesDateRequest requestObj = new ObjectMapper().readValue(new File(TEST_DATA_PATH_ACTIVITIES_REQUESTS + "get_all_activities_request.json"), GetActivitesDateRequest.class);
        ResponseEntity<GetActivitesFromDateResponse> response =
                restClient.exchange(env + Endpoints.SEARCH_ACTIVITIES, HttpMethod.POST, buildRequest(requestObj), GetActivitesFromDateResponse.class);

        GetActivitesFromDateResponse expectedResultObj = ((GetActivitesFromDateResponse) getExpectedResult("get_all_activities_from_date_response.json"));
        Assert.assertEquals(expectedResultObj.getPayload().getActivities(), response.getBody().getPayload().getActivities());
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }

    @Parameters({"ENV"})
    @Test(priority = 1)
    public void getActivitiesByTypeFundraise(String env) throws IOException {
        GetActivityByTypeRequest requestObj = new ObjectMapper().readValue(new File(TEST_DATA_PATH_ACTIVITIES_REQUESTS + "get_activity_by_type_fundraise_request.json"), GetActivityByTypeRequest.class);
        ResponseEntity<GetActivitesFromDateResponse> response =
                restClient.exchange(env + Endpoints.SEARCH_ACTIVITIES, HttpMethod.POST, buildRequest(requestObj), GetActivitesFromDateResponse.class);
        
        GetActivitesFromDateResponse expectedResultObj = ((GetActivitesFromDateResponse) getExpectedResult("get_activity_by_type_fundraise_response.json"));
        Assert.assertEquals(response.getBody().getPayload().getActivities(),expectedResultObj.getPayload().getActivities());
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }

    @Parameters({"ENV"})
    @Test(priority = 1)
    public void getActivitiesByTypeP2pevent(String env) throws IOException {
        GetActivityByTypeRequest requestObj = new ObjectMapper().readValue(new File(TEST_DATA_PATH_ACTIVITIES_REQUESTS + "get_activity_by_type_p2p_event_request.json"), GetActivityByTypeRequest.class);
        ResponseEntity<GetActivityByTypeP2PResponse> response =
                restClient.exchange(env + Endpoints.SEARCH_ACTIVITIES, HttpMethod.POST, buildRequest(requestObj), GetActivityByTypeP2PResponse.class);

        GetActivityByTypeP2PResponse expectedResultObj = ((GetActivityByTypeP2PResponse) getExpectedResult("get_activity_by_type_p2p_event_response.json"));
        Assert.assertEquals(expectedResultObj.getPayload().getActivities(), response.getBody().getPayload().getActivities());
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }

    @Parameters({"ENV"})
    @Test(priority = 1)
    public void getActivitiesByTypePetition(String env) throws IOException {
        GetActivityByTypeRequest requestObj = new ObjectMapper().readValue(new File(TEST_DATA_PATH_ACTIVITIES_REQUESTS + "get_activity_by_type_petition_request.json"), GetActivityByTypeRequest.class);
        ResponseEntity<GetActivitesFromDateResponse> response =
                restClient.exchange(env + Endpoints.SEARCH_ACTIVITIES, HttpMethod.POST, buildRequest(requestObj), GetActivitesFromDateResponse.class);

        GetActivitesFromDateResponse expectedResultObj = ((GetActivitesFromDateResponse) getExpectedResult("get_activity_by_type_petition_response.json"));
        Assert.assertEquals(expectedResultObj.getPayload().getActivities(), response.getBody().getPayload().getActivities());
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }

    @Parameters({"ENV"})
    @Test(priority = 1)
    public void getActivitiesByTypeSubscribe(String env) throws IOException {
        GetActivityByTypeRequest requestObj = new ObjectMapper().readValue(new File(TEST_DATA_PATH_ACTIVITIES_REQUESTS + "get_activity_by_type_subscribe_request.json"), GetActivityByTypeRequest.class);
        ResponseEntity<GetActivitesFromDateResponse> response =
                restClient.exchange(env + Endpoints.SEARCH_ACTIVITIES, HttpMethod.POST, buildRequest(requestObj), GetActivitesFromDateResponse.class);

        GetActivitesFromDateResponse expectedResultObj = ((GetActivitesFromDateResponse) getExpectedResult("get_activity_by_type_subscribe_response.json"));
        Assert.assertEquals(expectedResultObj.getPayload().getActivities(), response.getBody().getPayload().getActivities());
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }

    @Parameters({"ENV"})
    @Test(priority = 1)
    public void getActivitiesByTypeSubscribtionManagement(String env) throws IOException {
        GetActivityByTypeRequest requestObj = new ObjectMapper().readValue(new File(TEST_DATA_PATH_ACTIVITIES_REQUESTS + "get_activity_by_type_subscription_management_request.json"), GetActivityByTypeRequest.class);
        ResponseEntity<GetActivitesFromDateResponse> response =
                restClient.exchange(env + Endpoints.SEARCH_ACTIVITIES, HttpMethod.POST, buildRequest(requestObj), GetActivitesFromDateResponse.class);

        GetActivitesFromDateResponse expectedResultObj = ((GetActivitesFromDateResponse) getExpectedResult("get_activity_by_type_subscription_management_response.json"));
        Assert.assertEquals(expectedResultObj.getPayload().getActivities(), response.getBody().getPayload().getActivities());
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }

    @Parameters({"ENV"})
    @Test(priority = 1)
    public void getActivitiesByTypeTargetedLetter(String env) throws IOException {
        GetActivityByTypeRequest requestObj = new ObjectMapper().readValue(new File(TEST_DATA_PATH_ACTIVITIES_REQUESTS + "get_activity_by_type_targeted_letter_request.json"), GetActivityByTypeRequest.class);
        ResponseEntity<GetActivitesFromDateResponse> response =
                restClient.exchange(env + Endpoints.SEARCH_ACTIVITIES, HttpMethod.POST, buildRequest(requestObj), GetActivitesFromDateResponse.class);

        GetActivitesFromDateResponse expectedResultObj = ((GetActivitesFromDateResponse) getExpectedResult("get_activity_by_type_targeted_letter_response.json"));
        Assert.assertEquals(expectedResultObj.getPayload().getActivities(), response.getBody().getPayload().getActivities());
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }

    @Parameters({"ENV"})
    @Test(priority = 1)
    public void getActivitiesByTypeTicketedEvent(String env) throws IOException {
        GetActivityByTypeRequest requestObj = new ObjectMapper().readValue(new File(TEST_DATA_PATH_ACTIVITIES_REQUESTS + "get_activity_by_type_ticketed_event_request.json"), GetActivityByTypeRequest.class);
        ResponseEntity<GetActivityByTypeTicketedEventResponse> response =
                restClient.exchange(env + Endpoints.SEARCH_ACTIVITIES, HttpMethod.POST, buildRequest(requestObj), GetActivityByTypeTicketedEventResponse.class);

        GetActivityByTypeTicketedEventResponse expectedResultObj = ((GetActivityByTypeTicketedEventResponse) getExpectedResult("get_activity_by_type_ticketed_event_response.json"));
        Assert.assertEquals(response.getBody().getPayload().getActivities(), expectedResultObj.getPayload().getActivities());
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }

    @Parameters({"ENV"})
    @Test(priority = 1)
    public void getActivitiesWithinDateRange(String env) throws IOException {
        GetActivitesWithinDateRequest requestObj = new ObjectMapper().readValue(new File(TEST_DATA_PATH_ACTIVITIES_REQUESTS + "get_all_activities_within_date_range_request.json"), GetActivitesWithinDateRequest.class);
        ResponseEntity<GetActivitesFromDateResponse> response =
                restClient.exchange(env + Endpoints.SEARCH_ACTIVITIES, HttpMethod.POST, buildRequest(requestObj), GetActivitesFromDateResponse.class);

        GetActivitesFromDateResponse expectedResultObj = ((GetActivitesFromDateResponse) getExpectedResult("get_all_activities_within_date_range_response.json"));
        Assert.assertEquals(expectedResultObj.getPayload().getActivities(), response.getBody().getPayload().getActivities());
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }


    @Parameters({"ENV"})
    @Test(priority = 1)
    public void getActivitiesSinceSpecificDate(String env) throws IOException {
        GetActivitesWithinDateRequest requestObj = new ObjectMapper().readValue(new File(TEST_DATA_PATH_ACTIVITIES_REQUESTS + "get_activities_since_specific_date_request.json"), GetActivitesWithinDateRequest.class);
        ResponseEntity<com.salsalabs.ignite.automation.apiautomation.models.activity.getactivityfromdate.response.GetActivitesFromDateResponse> response =
                restClient.exchange(env + Endpoints.SEARCH_ACTIVITIES, HttpMethod.POST, buildRequest(requestObj), com.salsalabs.ignite.automation.apiautomation.models.activity.getactivityfromdate.response.GetActivitesFromDateResponse.class);

        com.salsalabs.ignite.automation.apiautomation.models.activity.getactivityfromdate.response.GetActivitesFromDateResponse expectedResultObj = ((com.salsalabs.ignite.automation.apiautomation.models.activity.getactivityfromdate.response.GetActivitesFromDateResponse) getExpectedResult("get_activities_since_specific_date_response.json"));
        Assert.assertEquals(response.getBody().getPayload().getActivities(), expectedResultObj.getPayload().getActivities());
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }

    @Parameters({"ENV"})
    @Test(priority = 1)
    public void getSpecificActivitiesSinceSpecificDate(String env) throws IOException {
        GetActivitesWithinDateRequest requestObj = new ObjectMapper().readValue(new File(TEST_DATA_PATH_ACTIVITIES_REQUESTS + "get_specific_activities_since_specific_date_request.json"), GetActivitesWithinDateRequest.class);
        ResponseEntity<com.salsalabs.ignite.automation.apiautomation.models.activity.getactivityfromdate.response.GetActivitesFromDateResponse> response =
                restClient.exchange(env + Endpoints.SEARCH_ACTIVITIES, HttpMethod.POST, buildRequest(requestObj), com.salsalabs.ignite.automation.apiautomation.models.activity.getactivityfromdate.response.GetActivitesFromDateResponse.class);

        com.salsalabs.ignite.automation.apiautomation.models.activity.getactivityfromdate.response.GetActivitesFromDateResponse expectedResultObj = ((com.salsalabs.ignite.automation.apiautomation.models.activity.getactivityfromdate.response.GetActivitesFromDateResponse) getExpectedResult("get_specific_activities_since_specific_date_response.json"));
        Assert.assertEquals(response.getBody().getPayload().getActivities(), expectedResultObj.getPayload().getActivities());
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }


    @Parameters({"ENV"})
    @Test(priority = 1)
    public void getSpecificActivitiesById(String env) throws IOException {
        GetActivitesWithinDateRequest requestObj = new ObjectMapper().readValue(new File(TEST_DATA_PATH_ACTIVITIES_REQUESTS + "get_specific_activities_by_id_request.json"), GetActivitesWithinDateRequest.class);
        ResponseEntity<com.salsalabs.ignite.automation.apiautomation.models.activity.getactivityfromdate.response.GetActivitesFromDateResponse> response =
                restClient.exchange(env + Endpoints.SEARCH_ACTIVITIES, HttpMethod.POST, buildRequest(requestObj), com.salsalabs.ignite.automation.apiautomation.models.activity.getactivityfromdate.response.GetActivitesFromDateResponse.class);

        com.salsalabs.ignite.automation.apiautomation.models.activity.getactivityfromdate.response.GetActivitesFromDateResponse expectedResultObj = ((com.salsalabs.ignite.automation.apiautomation.models.activity.getactivityfromdate.response.GetActivitesFromDateResponse) getExpectedResult("get_specific_activities_by_id_response.json"));
        Assert.assertEquals(response.getBody().getPayload().getActivities(), expectedResultObj.getPayload().getActivities());
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }

    @Parameters({"ENV"})
    @Test(priority = 2)
    public void getMetricsAfterExecution(String env) throws IOException {
        ResponseEntity<MetricsResponse> response =
                restClient.exchange(env + Endpoints.METRICS, HttpMethod.GET, buildRequest(null), MetricsResponse.class);

        metricsBeforeExecution.getPayload().setActivityTicketedEvent(metricsBeforeExecution.getPayload().getActivityTicketedEvent() + 4);
        metricsBeforeExecution.getPayload().setActivityP2PEvent(metricsBeforeExecution.getPayload().getActivityP2PEvent() + 4);
        metricsBeforeExecution.getPayload().setActivitySubscribe(metricsBeforeExecution.getPayload().getActivitySubscribe() + 34);
        metricsBeforeExecution.getPayload().setActivityFundraise(metricsBeforeExecution.getPayload().getActivityFundraise() + 19);
        metricsBeforeExecution.getPayload().setActivityTargetedLetter(metricsBeforeExecution.getPayload().getActivityTargetedLetter() + 6);
        metricsBeforeExecution.getPayload().setActivityPetition(metricsBeforeExecution.getPayload().getActivityPetition() + 18);
        metricsBeforeExecution.getPayload().setActivitySubscriptionManagement(metricsBeforeExecution.getPayload().getActivitySubscriptionManagement() + 1);
        metricsBeforeExecution.getPayload().setTotalAPICalls(metricsBeforeExecution.getPayload().getTotalAPICalls() + 11);

        Assert.assertEquals(metricsBeforeExecution, response.getBody());
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }
}
