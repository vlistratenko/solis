package com.salsalabs.ignite.automation.suites.api.devApi;

import java.io.IOException;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.apiautomation.config.Config;
import com.salsalabs.ignite.automation.apiautomation.models.devApiModels.activity.activityTypes.response.ActivityTypesResponse;
import com.salsalabs.ignite.automation.apiautomation.core.CommonTest;

public class ThroughputTest extends CommonTest {

	@Parameters({ "ENV" })
	@Test
	public void callsPerMinuteTest(String env) throws IOException {
		String rsultMessage = null;
		ResponseEntity<ActivityTypesResponse> response = null;

		for (int i = 0; i < 61; i++) {
			response = restClient.exchange(env + Config.Endpoints.ACTIVITYCORESUMMARY + "types", HttpMethod.GET,
					buildRequest(null), ActivityTypesResponse.class);
			Assert.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
		}

		try {
			response = restClient.exchange(env + Config.Endpoints.ACTIVITYCORESUMMARY + "types", HttpMethod.GET,
					buildRequest(null), ActivityTypesResponse.class);
		} catch (HttpClientErrorException e) {
			logger.info(e.getResponseBodyAsString());
			System.out.println(e.getMessage());
			rsultMessage = e.getMessage();
		}
		 Assert.assertTrue(rsultMessage.equals("429 Too Many Requests"));
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " success");
	}
}
