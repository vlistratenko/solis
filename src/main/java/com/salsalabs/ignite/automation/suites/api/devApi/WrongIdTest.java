package com.salsalabs.ignite.automation.suites.api.devApi;

import com.salsalabs.ignite.automation.apiautomation.config.Config.Endpoints;
import com.salsalabs.ignite.automation.apiautomation.models.ExpectedResult;
import com.salsalabs.ignite.automation.apiautomation.models.devApiModels.emptyPayload.EmptyPayloadResponse;
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

import static com.salsalabs.ignite.automation.apiautomation.config.Config.TEST_DATA_PATH_TA_SUMMARY_RESPONSE;

public class WrongIdTest extends CommonTest {

	@BeforeTest
	public void getTestData() throws IOException {
		loadExpectedResultObj(new HashMap<String, Class<? extends ExpectedResult>>() {
			{
				put(TEST_DATA_PATH_TA_SUMMARY_RESPONSE + "emptyPayload.json", EmptyPayloadResponse.class);

			}
		});
	}

	// if wrong id is specified to any dev api endpoint , Empty payload should
	// be returned
	@Parameters({ "ENV", "UUID" })
	@Test
	public void verifySummaryEndpointIfWrongIdIsSpecified(String env, String uuid) {

		ResponseEntity<EmptyPayloadResponse> response = restClient.exchange(
				env + Endpoints.ACTIVITYCORESUMMARY + uuid + "/summary", HttpMethod.GET, buildRequest(null),
				EmptyPayloadResponse.class);
		EmptyPayloadResponse expectedResultObj = ((EmptyPayloadResponse) getExpectedResult("emptyPayload.json"));
		Assert.assertEquals(response.getBody().getPayload(), expectedResultObj.getPayload());
		Assert.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
	}

	@Parameters({ "ENV", "UUID" })
	@Test
	public void verifySummaryTArgets(String env, String uuid) {
		ResponseEntity<EmptyPayloadResponse> response = restClient.exchange(
				env + Endpoints.ACTIVITYCORESUMMARY + uuid + "/summary/targets", HttpMethod.GET, buildRequest(null),
				EmptyPayloadResponse.class);
		EmptyPayloadResponse expectedResultObj = ((EmptyPayloadResponse) getExpectedResult("emptyPayload.json"));
		Assert.assertEquals(response.getBody().getPayload(), expectedResultObj.getPayload());
		Assert.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
	}

	@Parameters({ "ENV", "UUID" })
	@Test
	public void verifySummaryAttendees(String env, String uuid) {
		ResponseEntity<EmptyPayloadResponse> response = restClient.exchange(
				env + Endpoints.ACTIVITYCORESUMMARY + uuid + "/summary/attendees", HttpMethod.GET, buildRequest(null),
				EmptyPayloadResponse.class);
		EmptyPayloadResponse expectedResultObj = ((EmptyPayloadResponse) getExpectedResult("emptyPayload.json"));
		Assert.assertEquals(response.getBody().getPayload(), expectedResultObj.getPayload());
		Assert.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
	}

	@Parameters({ "ENV", "UUID" })
	@Test
	public void verifySummaryRegistrations(String env, String uuid) {
		ResponseEntity<EmptyPayloadResponse> response = restClient.exchange(
				env + Endpoints.ACTIVITYCORESUMMARY + uuid + "/summary/registrations", HttpMethod.GET,
				buildRequest(null), EmptyPayloadResponse.class);
		EmptyPayloadResponse expectedResultObj = ((EmptyPayloadResponse) getExpectedResult("emptyPayload.json"));
		Assert.assertEquals(response.getBody().getPayload(), expectedResultObj.getPayload());
		Assert.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
	}

	@Parameters({ "ENV", "UUID" })
	@Test
	public void verifySummaryFundraisers(String env, String uuid) {
		ResponseEntity<EmptyPayloadResponse> response = restClient.exchange(
				env + Endpoints.ACTIVITYCORESUMMARY + uuid + "/summary/fundraisers", HttpMethod.GET, buildRequest(null),
				EmptyPayloadResponse.class);
		EmptyPayloadResponse expectedResultObj = ((EmptyPayloadResponse) getExpectedResult("emptyPayload.json"));
		Assert.assertEquals(response.getBody().getPayload(), expectedResultObj.getPayload());
		Assert.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
	}

	@Parameters({ "ENV", "UUID" })
	@Test
	public void verifySummaryTeams(String env, String uuid) {
		ResponseEntity<EmptyPayloadResponse> response = restClient.exchange(
				env + Endpoints.ACTIVITYCORESUMMARY + uuid + "/summary/fundraisers", HttpMethod.GET, buildRequest(null),
				EmptyPayloadResponse.class);
		EmptyPayloadResponse expectedResultObj = ((EmptyPayloadResponse) getExpectedResult("emptyPayload.json"));
		Assert.assertEquals(response.getBody().getPayload(), expectedResultObj.getPayload());
		Assert.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
	}

	@Parameters({ "ENV", "UUID" })
	@Test
	public void verifySummaryTeamsPurchases(String env, String uuid) {
		ResponseEntity<EmptyPayloadResponse> response = restClient.exchange(
				env + Endpoints.ACTIVITYCORESUMMARY + uuid + "/summary/purchases", HttpMethod.GET, buildRequest(null),
				EmptyPayloadResponse.class);
		EmptyPayloadResponse expectedResultObj = ((EmptyPayloadResponse) getExpectedResult("emptyPayload.json"));
		Assert.assertEquals(response.getBody().getPayload(), expectedResultObj.getPayload());
		Assert.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
	}

	@Parameters({ "ENV", "UUID" })
	@Test
	public void verifySummaryOfSpecificFundraiser(String env, String uuid) {
		ResponseEntity<EmptyPayloadResponse> response = restClient.exchange(
				env + Endpoints.ACTIVITYCORESUMMARY + "/fundraisers/" + uuid, HttpMethod.GET, buildRequest(null),
				EmptyPayloadResponse.class);
		EmptyPayloadResponse expectedResultObj = ((EmptyPayloadResponse) getExpectedResult("emptyPayload.json"));
		Assert.assertEquals(response.getBody().getPayload(), expectedResultObj.getPayload());
		Assert.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
	}

	@Parameters({ "ENV", "UUID" })
	@Test
	public void verifySummaryOfSpecificTeam(String env, String uuid) {
		ResponseEntity<EmptyPayloadResponse> response = restClient.exchange(
				env + Endpoints.ACTIVITYCORESUMMARY + "/teams/" + uuid, HttpMethod.GET, buildRequest(null),
				EmptyPayloadResponse.class);
		EmptyPayloadResponse expectedResultObj = ((EmptyPayloadResponse) getExpectedResult("emptyPayload.json"));
		Assert.assertEquals(response.getBody().getPayload(), expectedResultObj.getPayload());
		Assert.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
	}

	@Parameters({ "ENV", "UUID" })
	@Test
	public void verifyMetadata(String env, String uuid) {

		ResponseEntity<EmptyPayloadResponse> response = restClient.exchange(
				env + Endpoints.ACTIVITYCORESUMMARY + uuid + "/metadata", HttpMethod.GET, buildRequest(null),
				EmptyPayloadResponse.class);
		EmptyPayloadResponse expectedResultObj = ((EmptyPayloadResponse) getExpectedResult("emptyPayload.json"));
		Assert.assertEquals(response.getBody().getPayload(), expectedResultObj.getPayload());
		Assert.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
	}

}
