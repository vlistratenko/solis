package com.salsalabs.ignite.automation.suites.api.devApi;

import static com.salsalabs.ignite.automation.apiautomation.config.Config.TEST_DATA_PATH_TA_SUMMARY_RESPONSE;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.apiautomation.config.Config;
import com.salsalabs.ignite.automation.apiautomation.config.Config.Endpoints;
import com.salsalabs.ignite.automation.apiautomation.models.ExpectedResult;
import com.salsalabs.ignite.automation.apiautomation.models.devApiModels.activity.getActivityFormSummary.responseTA.GetTaResponse;
import com.salsalabs.ignite.automation.apiautomation.models.devApiModels.activity.getActivityFormSummary.responseTeams.GetP2pTeamSummaryResponse;
import com.salsalabs.ignite.automation.apiautomation.models.devApiModels.metrics.response.MetricsResponse;
import com.salsalabs.ignite.automation.apiautomation.tests.CommonTest;

public class GetTaSummaryTest extends CommonTest {
	private MetricsResponse metricsBeforeExecution;
	 
	 @BeforeTest
	    public void getTestData() throws IOException {
	        loadExpectedResultObj(
	                new HashMap<String, Class<? extends ExpectedResult>>()
	                {{
	                    put(TEST_DATA_PATH_TA_SUMMARY_RESPONSE + "get_ta_summary_response.json", GetTaResponse.class);

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
	    public void getTASummaryTest(String env, String uuid) throws IOException {
	        ResponseEntity<GetTaResponse> response =
	             restClient.exchange(env + Endpoints.ACTIVITYCORESUMMARY+ uuid+"/summary", HttpMethod.GET, buildRequest(null), GetTaResponse.class);
	        GetTaResponse expectedResultObj = ((GetTaResponse) getExpectedResult("get_ta_summary_response.json"));
	        Assert.assertEquals( response.getBody().getPayload(), expectedResultObj.getPayload());
	        Assert.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
	     
	    }
	   
	   @Parameters({ "ENV" })
		@Test(priority = 2)
		public void getMetricsAfterExecution(String env) throws IOException {
			ResponseEntity<MetricsResponse> response = restClient.exchange(env + Endpoints.DEVAPIMETRICS, HttpMethod.GET,
					buildRequest(null), MetricsResponse.class);
			metricsBeforeExecution.getPayload()
					.setActivityFormSummary(metricsBeforeExecution.getPayload().getActivityFormSummary() + 1);
			metricsBeforeExecution.getPayload()
					.setTotalApiCalls(metricsBeforeExecution.getPayload().getTotalApiCalls() + 1);

			Assert.assertEquals(response.getBody().getPayload(), metricsBeforeExecution.getPayload());
			logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
		}

}
