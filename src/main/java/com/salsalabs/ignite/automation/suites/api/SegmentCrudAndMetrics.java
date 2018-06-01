package com.salsalabs.ignite.automation.suites.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.salsalabs.ignite.automation.apiautomation.config.Config;
import com.salsalabs.ignite.automation.apiautomation.models.ExpectedResult;
import com.salsalabs.ignite.automation.apiautomation.models.metrics.response.MetricsResponse;
import com.salsalabs.ignite.automation.apiautomation.models.segments.assignsupporter.request.AssignSupporterRequest;
import com.salsalabs.ignite.automation.apiautomation.models.segments.assignsupporter.response.AssignSupporterResponse;
import com.salsalabs.ignite.automation.apiautomation.models.segments.createsegment.request.CreateSegmentRequest;
import com.salsalabs.ignite.automation.apiautomation.models.segments.createsegment.response.CreateSegmentResponse;
import com.salsalabs.ignite.automation.apiautomation.models.segments.deletesegment.request.DeleteSegmentRequest;
import com.salsalabs.ignite.automation.apiautomation.models.segments.deletesegment.response.DeleteSegmentResponse;
import com.salsalabs.ignite.automation.apiautomation.models.segments.queryallassignedsupporters.request.QueryAllAssignedSupportersRequest;
import com.salsalabs.ignite.automation.apiautomation.models.segments.queryallassignedsupporters.response.QueryAllAssignedSupportersResponse;
import com.salsalabs.ignite.automation.apiautomation.models.segments.queryallsegment.request.QueryAllSegmentsRequest;
import com.salsalabs.ignite.automation.apiautomation.models.segments.queryallsegment.response.QueryAllSegmentsResponse;
import com.salsalabs.ignite.automation.apiautomation.models.segments.queryassignedsupporter.response.QueryAssignedSupportersResponse;
import com.salsalabs.ignite.automation.apiautomation.core.CommonTest;
import org.springframework.http.*;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static com.salsalabs.ignite.automation.apiautomation.config.Config.*;

public class SegmentCrudAndMetrics extends CommonTest {

    private MetricsResponse metricsBeforeExecution;

    @BeforeTest
    public void getTestData() throws IOException {
        loadExpectedResultObj(
                new HashMap<String, Class<? extends ExpectedResult>>()
                {{
                    put(TEST_DATA_PATH_SEGMENTS_RESPONSES + "create_segment_response.json", CreateSegmentResponse.class);
                    put(TEST_DATA_PATH_SEGMENTS_RESPONSES + "query_all_segments_response.json", QueryAllSegmentsResponse.class);
                    put(TEST_DATA_PATH_SEGMENTS_RESPONSES + "query_segment_by_id_response.json", QueryAllSegmentsResponse.class);
                    put(TEST_DATA_PATH_SEGMENTS_RESPONSES + "query_segment_by_ext_id_response.json", QueryAllSegmentsResponse.class);
                    put(TEST_DATA_PATH_SEGMENTS_RESPONSES + "assign_supporter_to_segment_response.json", AssignSupporterResponse.class);
                    put(TEST_DATA_PATH_SEGMENTS_RESPONSES + "query_assigned_supporter_response.json", QueryAssignedSupportersResponse.class);
                    put(TEST_DATA_PATH_SEGMENTS_RESPONSES + "delete_supporter_from_segment_response.json", QueryAssignedSupportersResponse.class);
                    put(TEST_DATA_PATH_SEGMENTS_RESPONSES + "delete_segment_response.json", DeleteSegmentResponse.class);
                    put(TEST_DATA_PATH_SEGMENTS_RESPONSES + "query_all_supporter_assigned_to_segment_response.json", QueryAllAssignedSupportersResponse.class);
                    put(TEST_DATA_PATH_SEGMENTS_RESPONSES + "query_all_segments_without_supporters_count_response.json", QueryAllSegmentsResponse.class);
                }}
        );
    }


    @Parameters({"ENV"})
    @Test(priority = 0, groups = "a")
    public void getMetricsBeforeExecution(String env) throws IOException {
        ResponseEntity<MetricsResponse> response =
                restClient.exchange(env + Config.Endpoints.METRICS, HttpMethod.GET, buildRequest(null), MetricsResponse.class);
        metricsBeforeExecution = response.getBody();
        Assert.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }

    @Parameters({"ENV"})
    @Test(priority = 1, groups = "a", dependsOnMethods={"getMetricsBeforeExecution"})
    public void createSegment(String env) throws IOException {
        CreateSegmentRequest requestObj = new ObjectMapper().readValue(new File(TEST_DATA_PATH_SEGMENTS_REQUESTS + "create_segment_request.json"), CreateSegmentRequest.class);
        ResponseEntity<CreateSegmentResponse> response =
                restClient.exchange(env + Endpoints.CREATE_UPDATE_DELETE_SEGMENT, HttpMethod.PUT, buildRequest(requestObj), CreateSegmentResponse.class);

        CreateSegmentResponse expectedResultObj = ((CreateSegmentResponse) getExpectedResult("create_segment_response.json"));
        Assert.assertEquals(response.getBody().getPayload().getSegments(), expectedResultObj.getPayload().getSegments());
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }

    @Parameters({"ENV"})
    @Test(priority = 2, groups = "a", dependsOnMethods={"getMetricsBeforeExecution" , "createSegment"})
    public void queryAllSegments(String env) throws IOException {
        QueryAllSegmentsRequest requestObj = new ObjectMapper().readValue(new File(TEST_DATA_PATH_SEGMENTS_REQUESTS + "query_all_segments_request.json"), QueryAllSegmentsRequest.class);
        ResponseEntity<QueryAllSegmentsResponse> response =
                restClient.exchange(env + Endpoints.SEARCH_SEGMENT, HttpMethod.POST, buildRequest(requestObj), QueryAllSegmentsResponse.class);

        QueryAllSegmentsResponse expectedResultObj = ((QueryAllSegmentsResponse) getExpectedResult("query_all_segments_response.json"));

        Assert.assertEquals(expectedResultObj.getPayload().getSegments(), response.getBody().getPayload().getSegments());
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }

    @Parameters({"ENV"})
    @Test(priority = 2, groups = "a", dependsOnMethods={"getMetricsBeforeExecution" , "createSegment"})
    public void queryAllSegmentsWithoutSupportersCount(String env) throws IOException {
        QueryAllSegmentsRequest requestObj = new ObjectMapper().readValue(new File(TEST_DATA_PATH_SEGMENTS_REQUESTS + "query_all_segments_without_supporters_count_request.json"), QueryAllSegmentsRequest.class);
        ResponseEntity<QueryAllSegmentsResponse> response =
                restClient.exchange(env + Endpoints.SEARCH_SEGMENT, HttpMethod.POST, buildRequest(requestObj), QueryAllSegmentsResponse.class);

        QueryAllSegmentsResponse expectedResultObj = ((QueryAllSegmentsResponse) getExpectedResult("query_all_segments_without_supporters_count_response.json"));

        Assert.assertEquals(expectedResultObj.getPayload().getSegments(), response.getBody().getPayload().getSegments());
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }

    @Parameters({"ENV"})
    @Test(priority = 3, groups = "a", dependsOnMethods={"getMetricsBeforeExecution", "createSegment"})
    public void querySegmentById(String env) throws IOException {
        QueryAllSegmentsRequest requestObj = new ObjectMapper().readValue(new File(TEST_DATA_PATH_SEGMENTS_REQUESTS + "query_segment_by_id.json"), QueryAllSegmentsRequest.class);
        ResponseEntity<QueryAllSegmentsResponse> response =
                restClient.exchange(env + Endpoints.SEARCH_SEGMENT, HttpMethod.POST, buildRequest(requestObj), QueryAllSegmentsResponse.class);

        QueryAllSegmentsResponse expectedResultObj = ((QueryAllSegmentsResponse) getExpectedResult("query_segment_by_id_response.json"));

        Assert.assertEquals(expectedResultObj.getPayload().getSegments(), response.getBody().getPayload().getSegments());
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }

    @Parameters({"ENV"})
    @Test(priority = 4, groups = "a", dependsOnMethods={"getMetricsBeforeExecution", "createSegment"})
    public void querySegmentByExternalId(String env) throws IOException {
        QueryAllSegmentsRequest requestObj = new ObjectMapper().readValue(new File(TEST_DATA_PATH_SEGMENTS_REQUESTS + "query_segment_by_ext_id.json"), QueryAllSegmentsRequest.class);
        ResponseEntity<QueryAllSegmentsResponse> response =
                restClient.exchange(env + Endpoints.SEARCH_SEGMENT, HttpMethod.POST, buildRequest(requestObj), QueryAllSegmentsResponse.class);

        QueryAllSegmentsResponse expectedResultObj = ((QueryAllSegmentsResponse) getExpectedResult("query_segment_by_ext_id_response.json"));

        Assert.assertEquals(expectedResultObj.getPayload().getSegments(), response.getBody().getPayload().getSegments());
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }

    @Parameters({"ENV"})
    @Test(priority = 5, groups = "a", dependsOnMethods={"getMetricsBeforeExecution", "createSegment"})
    public void assignSupporterToSegment(String env) throws IOException {
        AssignSupporterRequest requestObj = new ObjectMapper().readValue(new File(TEST_DATA_PATH_SEGMENTS_REQUESTS + "assign_supporter_to_segment_request.json"), AssignSupporterRequest.class);
        ResponseEntity<AssignSupporterResponse> response =
                restClient.exchange(env + Endpoints.ASSIGN_REMOVE_SUPPORTER_TOFROM_SEGMENT, HttpMethod.PUT, buildRequest(requestObj), AssignSupporterResponse.class);

        AssignSupporterResponse expectedResultObj = ((AssignSupporterResponse) getExpectedResult("assign_supporter_to_segment_response.json"));

        Assert.assertEquals(response.getBody().getPayload().getSupporters(), expectedResultObj.getPayload().getSupporters());
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }

    @Parameters({"ENV"})
    @Test(priority = 6, groups = "a", dependsOnMethods={"getMetricsBeforeExecution", "createSegment", "assignSupporterToSegment"})
    public void querySupporterAssignedToSegment(String env) throws IOException {
        AssignSupporterRequest requestObj = new ObjectMapper().readValue(new File(TEST_DATA_PATH_SEGMENTS_REQUESTS + "assign_supporter_to_segment_request.json"), AssignSupporterRequest.class);
        ResponseEntity<QueryAssignedSupportersResponse> response =
                restClient.exchange(env + Endpoints.SEARCH_SUPPORTERS_ASSIGNED_TO_SEGMENT, HttpMethod.POST, buildRequest(requestObj), QueryAssignedSupportersResponse.class);

        QueryAssignedSupportersResponse expectedResultObj = ((QueryAssignedSupportersResponse) getExpectedResult("query_assigned_supporter_response.json"));
        Assert.assertEquals(expectedResultObj.getPayload().getSupporters(), response.getBody().getPayload().getSupporters());
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }

    @Parameters({"ENV"})
    @Test(priority = 7, groups = "a", dependsOnMethods={"getMetricsBeforeExecution", "createSegment", "assignSupporterToSegment"})
    public void deleteSupporterFromSegment(String env) throws IOException {
        AssignSupporterRequest requestObj = new ObjectMapper().readValue(new File(TEST_DATA_PATH_SEGMENTS_REQUESTS + "assign_supporter_to_segment_request.json"), AssignSupporterRequest.class);
        ResponseEntity<QueryAssignedSupportersResponse> response =
                restClient.exchange(env + Endpoints.ASSIGN_REMOVE_SUPPORTER_TOFROM_SEGMENT, HttpMethod.DELETE, buildRequest(requestObj), QueryAssignedSupportersResponse.class);

        QueryAssignedSupportersResponse expectedResultObj = ((QueryAssignedSupportersResponse) getExpectedResult("delete_supporter_from_segment_response.json"));

        Assert.assertEquals(expectedResultObj.getPayload().getSupporters(), response.getBody().getPayload().getSupporters());
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }

    @Parameters({"ENV"})
    @Test(priority = 8, groups = "a", dependsOnMethods={"getMetricsBeforeExecution", "createSegment", "assignSupporterToSegment"})
    public void deleteSegment(String env) throws IOException {
        DeleteSegmentRequest requestObj = new ObjectMapper().readValue(new File(TEST_DATA_PATH_SEGMENTS_REQUESTS + "delete_segment_request.json"), DeleteSegmentRequest.class);
        ResponseEntity<DeleteSegmentResponse> response =
                restClient.exchange(env + Endpoints.CREATE_UPDATE_DELETE_SEGMENT, HttpMethod.DELETE, buildRequest(requestObj), DeleteSegmentResponse.class);

        DeleteSegmentResponse expectedResultObj = ((DeleteSegmentResponse) getExpectedResult("delete_segment_response.json"));

        Assert.assertEquals(expectedResultObj.getPayload().getSegments(), response.getBody().getPayload().getSegments());
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }

    @Parameters({"ENV"})
    @Test(priority = 8, groups = "a", dependsOnMethods={"getMetricsBeforeExecution", "createSegment", "assignSupporterToSegment"})
    public void getAllSupportersAssignedToSegment(String env) throws IOException {
        QueryAllAssignedSupportersRequest requestObj = new ObjectMapper().readValue(new File(TEST_DATA_PATH_SEGMENTS_REQUESTS + "query_all_supporter_assigned_to_segment_request.json"), QueryAllAssignedSupportersRequest.class);
        HttpHeaders headers = getHeaders();
        headers.set("authToken", "4umCqL9TrmQ16YqxK-FMe6gMJNbziuByDfUH_8D3qhmDKRLKloEeTTJDjnWWjbQNa3ie-uQLm6bX9g8WPqY9ISmZoMbkZTIyYgivbBy_EmU");
        ResponseEntity<QueryAllAssignedSupportersResponse> response =
                restClient.exchange(env + Endpoints.SEARCH_SUPPORTERS_ASSIGNED_TO_SEGMENT, HttpMethod.POST, buildRequest(requestObj, headers), QueryAllAssignedSupportersResponse.class);
        QueryAllAssignedSupportersResponse expectedResultObj = ((QueryAllAssignedSupportersResponse) getExpectedResult("query_all_supporter_assigned_to_segment_response.json"));
        Assert.assertEquals(expectedResultObj.getPayload().getSupporters(), response.getBody().getPayload().getSupporters());
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }

    @Parameters({"ENV"})
    @Test(priority = 9, dependsOnGroups = "a")
    public void getMetricsAfterExecution(String env) throws IOException {
        HttpHeaders headers = getHeaders();
        headers.set("authToken", "rCtN2nuDMaAycSnkrjqOBzrAZH8V-T3lZRUIjlIr2ZGSB8b_Y0NWf1klSRwNQtyWgRSadaTVcI1ugEXZRug8HiPx_oSZnnTAZvNqRoctZKG9zTqR3j-4kDkw1X6TUjgqqSeIPO7er_iJ-JLV__1_2Q");
        ResponseEntity<MetricsResponse> response =
                restClient.exchange(env + Endpoints.METRICS, HttpMethod.GET, buildRequest(null, headers), MetricsResponse.class);

        metricsBeforeExecution.getPayload().setSegmentAdd(metricsBeforeExecution.getPayload().getSegmentAdd() + 1);
        metricsBeforeExecution.getPayload().setSegmentDelete(metricsBeforeExecution.getPayload().getSegmentDelete() + 1);
        metricsBeforeExecution.getPayload().setSegmentRead(metricsBeforeExecution.getPayload().getSegmentRead() + 38);
        metricsBeforeExecution.getPayload().setSegmentAssignmentRead(metricsBeforeExecution.getPayload().getSegmentAssignmentRead() + 1);
        metricsBeforeExecution.getPayload().setSegmentAssignmentAdd(metricsBeforeExecution.getPayload().getSegmentAssignmentAdd() + 1);
        metricsBeforeExecution.getPayload().setSegmentAssignmentDelete(metricsBeforeExecution.getPayload().getSegmentAssignmentDelete() + 1);
        metricsBeforeExecution.getPayload().setTotalAPICalls(metricsBeforeExecution.getPayload().getTotalAPICalls() + 9);

        Assert.assertEquals(metricsBeforeExecution, response.getBody());
        logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
    }
}
