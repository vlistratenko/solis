package com.salsalabs.ignite.automation.suites;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.common.Supporter;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;

public class SignUpSupportersDEMO extends SeleneseTestCase {
	
	LoginPage loginPage;
	
	@Parameters({"amount", "formURL"})
	@Test(groups = {"subscribeNewSupporter"}, retryAnalyzer = RetryAnalyzer.class)
	public void testSubscribeNewSupporter(Integer amount, String formURL) {		
		loginPage = new LoginPage(true);
		int amountOfSupporters = CommonUtils.getRandomValueNumericFromTo(1, amount);
		for (int j = 1; j < amountOfSupporters; j++) {
			Supporter sup = Supporter.getSupporterWithRandomDataFromFile();
			loginPage.
			openSubscribeWidgetByLink(formURL).
			fillSubscribeWidget(sup.getFinalEMAIL(),
				sup.getFirstName(),
				sup.getLastName(),
				sup.getCity(),
				sup.getZipCode(), 
				sup.getState()).
			verifySubscriptionIsSuccesses().
			backToLoginPage();
		}
	}
	
	@Parameters({"amount", "formURL", "login", "host"})
	@Test(groups = {"makeDonationRandom"}, retryAnalyzer = RetryAnalyzer.class)
	public void testDonateByExistingSupporter(Integer amount, String formURL, String login, String host) throws KeyManagementException, ClientProtocolException, NoSuchAlgorithmException, KeyStoreException, JSONException, URISyntaxException, IOException {
		
		int amountOfDonations = CommonUtils.getRandomValueNumericFromTo(1, amount);	
		logger.info("Amount of donations " + amountOfDonations);
		Map<Integer, Supporter> sup = new HashMap<Integer, Supporter>();
		
		loginPage = new LoginPage(true);
		for (int j = 0; j < amountOfDonations; j++) {
			Boolean isExisted = CommonUtils.getRandomBoolean();
			Boolean isRequring = CommonUtils.getRandomBoolean();
			if (!isExisted) {
				sup = new Supporter().getSupportersFromSystem(host, login, "!QAZ2wsx", amountOfDonations, "UI,SUBSCRIPTION,PETITION,TARGETED_LETTER," );
				String fname = sup.get(CommonUtils.getRandomValueNumericFromTo(0, sup.size())).firstName,
						lname = sup.get(CommonUtils.getRandomValueNumericFromTo(0, sup.size())).lastorOrgName;
				loginPage.
				openDonationWidgetByLink(formURL).
				fillDonationForm(fname + "." + lname + CommonUtils.getRandomNumericValueFixedLength(4) + "@uatauto.ignite.net",
						fname,
						lname,
						sup.get(CommonUtils.getRandomValueNumericFromTo(0, sup.size()-1)).addressLine1,
						sup.get(CommonUtils.getRandomValueNumericFromTo(0, sup.size()-1)).addressLine2,
						sup.get(CommonUtils.getRandomValueNumericFromTo(0, sup.size()-1)).getCity(),
						sup.get(CommonUtils.getRandomValueNumericFromTo(0, sup.size()-1)).getZipCode(),
						sup.get(CommonUtils.getRandomValueNumericFromTo(0, sup.size()-1)).getState(),
						isRequring,
						CommonUtils.getRandomNumericValueFixedLength(2),
						fname + " " + lname,
						"4111111111111111",
						"180",
						"01",
						"2018",
						true,
						true,
						true).
				clickDonationButton().
				verifyDonationIsSuccesses().
				backToLoginPage();
			}else{
				sup = new Supporter().getSupportersFromSystem(host, login, "!QAZ2wsx", amountOfDonations, "&source=DONATION" );
				int ind = CommonUtils.getRandomValueNumericFromTo(0, sup.size()-1);
				loginPage.
				openDonationWidgetByLink("https://orgname3281ec647c99mailosaurin.test.igniteaction.net/fundraising1").
				fillDonationForm(sup.get(ind).getFinalEMAIL(),
						sup.get(ind).getFirstName(),
						sup.get(ind).getLastName(),
						sup.get(ind).addressLine1,
						sup.get(ind).addressLine2,
						sup.get(ind).getCity(),
						sup.get(ind).getZipCode(),
						sup.get(ind).getState(),
						isRequring,
						CommonUtils.getRandomNumericValueFixedLength(2),
						sup.get(ind).getFirstName() + " " + sup.get(ind).getLastName(),
						"4111111111111111",
						"180",
						"01",
						"2018",
						true,
						true,
						true).
				clickDonationButton().
				verifyDonationIsSuccesses().
				backToLoginPage();
			}
		}
	}
		
	@Parameters({"amount", "formURL", "login", "host"})
	@Test(groups = {"signPetition"}, retryAnalyzer = RetryAnalyzer.class)
	public void testSignPetition(Integer amount, String formURL, String login, String host) throws KeyManagementException, ClientProtocolException, NoSuchAlgorithmException, KeyStoreException, JSONException, URISyntaxException, IOException {
		int amountOfSigns = CommonUtils.getRandomValueNumericFromTo(1, amount);	
		logger.info("Amount of signs " + amountOfSigns);			
		loginPage = new LoginPage(true);
		Supporter tempSupporter = new Supporter();
		String urls[] = CommonUtils.getArrayFromStringBySymbol(formURL, "%");
		for (int j = 0; j < amountOfSigns; j++) {
			formURL = urls[CommonUtils.getRandomValueNumericFromTo(0, 4)-1];
			Boolean isExisted = CommonUtils.getRandomBoolean();
			if (isExisted) {
				tempSupporter = new Supporter().getSupportersFromSystem(host, login, "!QAZ2wsx", amount, "UI,SUBSCRIPTION,TARGETED_LETTER,DONATION,IMPORT" ).get(j);
			}else{
				tempSupporter = Supporter.getSupporterWithRandomDataFromFile();
			}				
			loginPage.
			openPetitionWidgetByLink(formURL).
			signPetitionWithOutChecking(tempSupporter, "Test comment", true, true).
			verifySignIsSuccesses().
			backToLoginPage();
			
		}
	}

	
	
	
}
