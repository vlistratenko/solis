package com.salsalabs.ignite.automation.suites.api.devApi;

import static com.salsalabs.ignite.automation.apiautomation.config.Config.TEST_DATA_PATH_TEAM_METADATA_SUMMARY_RESPONSE;

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
import com.salsalabs.ignite.automation.apiautomation.models.devApiModels.activity.getActivityFormSummary.resposeGetTeamMetadataAndSummary.GetTeamMetadataAndSummary;
import com.salsalabs.ignite.automation.apiautomation.models.devApiModels.metrics.response.MetricsResponse;
import com.salsalabs.ignite.automation.apiautomation.core.CommonTest;

public class GetP2pTeamMetadataAndSummary extends CommonTest {
	private MetricsResponse metricsBeforeExecution;

	@BeforeTest
	public void getTestData() throws IOException {
		loadExpectedResultObj(new HashMap<String, Class<? extends ExpectedResult>>() {
			{
				put(TEST_DATA_PATH_TEAM_METADATA_SUMMARY_RESPONSE + "get_team_metadata_and_summary.json",
						GetTeamMetadataAndSummary.class);

			}
		});
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

	@Parameters({ "ENV", "UUID" })
	@Test(priority = 1)
	public void getTeamMetadaAndSummary(String env, String uuid) throws IOException {
		ResponseEntity<GetTeamMetadataAndSummary> response = restClient.exchange(
				env + Endpoints.ACTIVITYCORESUMMARY + "teams/"+ uuid , HttpMethod.GET, buildRequest(null),
				GetTeamMetadataAndSummary.class);
		GetTeamMetadataAndSummary expectedResultObj = ((GetTeamMetadataAndSummary) getExpectedResult(
				"get_team_metadata_and_summary.json"));
		Assert.assertEquals(response.getBody().getPayload(), expectedResultObj.getPayload());
		Assert.assertTrue(response.getStatusCode().equals(HttpStatus.OK));

	}

	@Parameters({ "ENV" })
	@Test(priority = 2)
	public void getMetricsAfterExecution(String env) throws IOException {
		ResponseEntity<MetricsResponse> response = restClient.exchange(env + Endpoints.DEVAPIMETRICS, HttpMethod.GET,
				buildRequest(null), MetricsResponse.class);
		metricsBeforeExecution.getPayload()
				.setTeamMetadata(metricsBeforeExecution.getPayload().getTeamMetadata() + 1);
		metricsBeforeExecution.getPayload()
				.setTotalApiCalls(metricsBeforeExecution.getPayload().getTotalApiCalls() + 1);

		Assert.assertEquals(response.getBody().getPayload(), metricsBeforeExecution.getPayload());
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
	}

	
}
