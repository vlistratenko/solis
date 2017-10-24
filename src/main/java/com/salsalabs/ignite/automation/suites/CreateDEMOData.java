package com.salsalabs.ignite.automation.suites;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.Environment;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.common.Supporter;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.activities.EventWidget;
import com.salsalabs.ignite.automation.pages.p2p.Eventp2pWidget;

public class CreateDEMOData extends SeleneseTestCase {
	
	LoginPage loginPage;
	
	@Parameters({"amount", "formURL"})
	@Test(groups = {"testSubscribeDEMOSupporter"}, retryAnalyzer = RetryAnalyzer.class)
	public void testSubscribeDEMOSupporter(Integer amount, String formURL) {		
		loginPage = new LoginPage(true);
		int amountOfSupporters = CommonUtils.getRandomValueNumericFromTo(1, amount);
		String urls[] = CommonUtils.getArrayFromStringBySymbol(formURL, "%");
		for (int j = 1; j < amountOfSupporters; j++) {
			formURL = urls[CommonUtils.getRandomValueNumericFromTo(0, urls.length+1)-1];
			Supporter sup = Supporter.getSupporterWithRandomDataFromFile();
			loginPage.
			openSubscribeWidgetByLink(formURL).
			fillSubscribeWidgetOldForms(sup.getFinalEMAIL(),
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
	public void testDonateBySupporter(Integer amount, String formURL, String login, String host) throws KeyManagementException, ClientProtocolException, NoSuchAlgorithmException, KeyStoreException, JSONException, URISyntaxException, IOException {
		
		int amountOfDonations = CommonUtils.getRandomValueNumericFromTo(1,amount);	
		logger.info("Amount of donations " + amountOfDonations);
		Map<Integer, Supporter> sup = new HashMap<Integer, Supporter>();
		String urls[] = CommonUtils.getArrayFromStringBySymbol(formURL, "%");
		loginPage = new LoginPage(true);
		for (int j = 0; j < amountOfDonations; j++) {
			Boolean isExisted = CommonUtils.getRandomBoolean();
			Boolean isRequring = CommonUtils.getRandomBoolean();
			logger.info("Test data:");
			logger.info("Amount of donations " + amountOfDonations);
			logger.info("Is existed supporter - " + isExisted);
			logger.info("Is requring donations - " + isRequring);
			if (!isExisted) {
				sup = new Supporter().getSupportersFromSystem(host, login, "!QAZ2wsx", amountOfDonations, "&source=IMPORT,UI,SUBSCRIBE,PETITION,TARGETED_LETTER,API," );
				String fname = sup.get(CommonUtils.getRandomValueNumericFromTo(0, sup.size())).firstName,
						lname = sup.get(CommonUtils.getRandomValueNumericFromTo(0, sup.size())).lastorOrgName;
				formURL = urls[CommonUtils.getRandomValueNumericFromTo(0, urls.length+1)-1];
				loginPage.
				openDonationWidgetByLink(formURL).
				fillDonationForm(SeleneseTestCase.emailClient.getEmailBox(fname + "." + lname + CommonUtils.getRandomNumericValueFixedLength(4)),
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
				sup = new Supporter().getSupportersFromSystem(host, login, "!QAZ2wsx", amountOfDonations, "&source=FUNDRAISE,TICKETED_EVENT," );
				int ind = CommonUtils.getRandomValueNumericFromTo(0, sup.size()-1);
				formURL = urls[CommonUtils.getRandomValueNumericFromTo(0, urls.length+1)-1];
				loginPage.
				openDonationWidgetByLink(formURL).
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
			formURL = urls[CommonUtils.getRandomValueNumericFromTo(0, urls.length+1)-1];
			Boolean isExisted = CommonUtils.getRandomBoolean();
			String comment = "Test comment";
			if (CommonUtils.getRandomBoolean()) {
				comment = "";
			}
			if (isExisted) {
				tempSupporter = new Supporter().getSupportersFromSystem(host, login, "!QAZ2wsx", amount, "&source=IMPORT,UI,SUBSCRIBE,FUNDRAISE,TARGETED_LETTER,TICKETED_EVENT,API," ).get(j);
			}else{
				tempSupporter = Supporter.getSupporterWithRandomDataFromFile();
			}				
			loginPage.
			openPetitionWidgetByLink(formURL).
			signPetitionWithOutCheckingOldForms(tempSupporter, comment, true, true).
			verifySignIsSuccesses().
			backToLoginPage();
			
		}
	}
	
	@Parameters({"amount", "formURL", "login", "host"})
	@Test(groups = {"sendTL"}, retryAnalyzer = RetryAnalyzer.class)
	public void testSendTL(Integer amount, String formURL, String login, String host) throws KeyManagementException, ClientProtocolException, NoSuchAlgorithmException, KeyStoreException, JSONException, URISyntaxException, IOException {		
		int amountOfEmails = CommonUtils.getRandomValueNumericFromTo(1, amount);	
		logger.info("Amount of letters " + amountOfEmails);			
		loginPage = new LoginPage(true);
		Supporter tempSupporter = new Supporter();
		String urls[] = CommonUtils.getArrayFromStringBySymbol(formURL, "%");
		for (int j = 0; j < amountOfEmails; j++) {
			formURL = urls[CommonUtils.getRandomValueNumericFromTo(0, urls.length+1)-1];
			Boolean isExisted = CommonUtils.getRandomBoolean();
			if (isExisted) {
				tempSupporter = new Supporter().getSupportersFromSystem(host, login, "!QAZ2wsx", amount, "&source=IMPORT,UI,SUBSCRIBE,FUNDRAISE,PETITION,TICKETED_EVENT,API," ).get(j);
			}else{
				tempSupporter = Supporter.getSupporterWithRandomDataFromFile();
			}				
			loginPage.
			openTLWidgetByLink(formURL).
			findLegistratorTLForm("10753 BLIX", "91602", tempSupporter.finalEMAIL, tempSupporter.firstName,  tempSupporter.lastorOrgName, "North holliwood", "CA").
			fillTLWidget("Mr").
			verifySubscriptionIsSuccesses().
			backToLoginPage();
			
		}
	}
	
	@Parameters({ "sendEmail.from", "login", "segmentName"})
	@Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = { "email.sendEmailsEmailsAndClickIn" }, description = "")
	public void sendEmailBlastTest(String emailFrom, String login, String segmentName) {
		
		String emailBlastName = "We need your help!!! Blast from " + CommonUtils.getTodayDate() + " " + CommonUtils.getRandomNumericValueFixedLength(5);
		String emailSubject = emailBlastName;
		String host = new Environment("UAT", "LOCAL").getBaseTestUrl().replace("https://", "");
		CommonUtils.setProperty(PropertyName.EMAIL_FROM, emailFrom);
		CommonUtils.setProperty(PropertyName.EMAIL_BLAST_NAME, emailBlastName);
		CommonUtils.setProperty(PropertyName.EMAIL_SUBJECT, emailSubject);
				
		LoginPage loginPage = new LoginPage();
		loginPage.
		doSuccessLogin(login, "!QAZ2wsx").
		openMessagingPage().
		openEmailBlastsPage().
		openAddEmailPage().
		fillAllFieldsAndGoForward(emailBlastName).
		selectAudienceType("Selected segments of your list, or specific supporters").//(""Entire list ").
		addSegment(segmentName).
		openComposePage().
		selectLayout("Basic").
		addLink("organizationforinternationalchange.uat.igniteaction.net/socialjusticeequality/index.html").
		fillAllFieldsAndGoForward(emailSubject, emailFrom, 1).
		fillAllFieldsAndPublish(100, 1).
		openDashboard().
		openMessagingPage().
		verifyActivityIsPresentInTableAllActivities("Email", emailBlastName).
		waitForStatus("COMPLETED", 6);
		
		Integer openAmount = CommonUtils.getRandomValueNumericFromTo(1, Integer.parseInt(CommonUtils.getProperty(PropertyName.AMOUNT_OF_PUBLISHED_EMAILS)));
		Integer clickAmount = CommonUtils.getRandomValueNumericFromTo(1, openAmount);
		Integer unsubAmount = CommonUtils.getRandomValueNumericFromTo(1, 10);
		Integer conversionAmount = CommonUtils.getRandomValueNumericFromTo(1, 10);
		Map<String, List<?>> emails = loginPage.openEmails(1, openAmount);		
		loginPage.clickLinkInEmail(emails, 1, "http://organizationforinternationalchange.uat.igniteaction.net/socialjusticeequality/index.html", clickAmount);		
		loginPage.unsubscribeByEmail(emails, 1, unsubAmount);
		
		loginPage.clickLinkInEmailAndFillDonationForm(emails, emailSubject, "http://organizationforinternationalchange.uat.igniteaction.net/socialjusticeequality/index.html", conversionAmount, login, host);
	}
	
	@Parameters({"amount", "formURL", "login", "host"})
	@Test(groups = {"submitEventRandom"}, retryAnalyzer = RetryAnalyzer.class)
	public void testSubmitEventBySupporter(Integer amount, String formURL, String login, String host) throws Exception {
		
		int amountOfDonations = CommonUtils.getRandomValueNumericFromTo(1, amount);	
		logger.info("Amount of donations " + amountOfDonations);
		Map<Integer, Supporter> sup = new HashMap<Integer, Supporter>();
		String urls[] = CommonUtils.getArrayFromStringBySymbol(formURL, "%");
		loginPage = new LoginPage(true);
		for (int j = 0; j < amountOfDonations; j++) {
			Boolean isExisted = CommonUtils.getRandomBoolean();
			Boolean isWithTickets = CommonUtils.getRandomBoolean();
			logger.info("Test data:");
			logger.info("Amount of donations " + amountOfDonations);
			logger.info("Is existed supporter - " + isExisted);
			logger.info("Is event with registration - " + isWithTickets);
			if (!isExisted) {
				sup = new Supporter().getSupportersFromSystem(host, login, "!QAZ2wsx", amountOfDonations, "&source=IMPORT,UI,SUBSCRIBE,PETITION,TARGETED_LETTER,API," );
				String fname = sup.get(CommonUtils.getRandomValueNumericFromTo(0, sup.size())).firstName,
						lname = sup.get(CommonUtils.getRandomValueNumericFromTo(0, sup.size())).lastorOrgName;
				formURL = urls[CommonUtils.getRandomValueNumericFromTo(0, urls.length+1)-1];
				EventWidget eventWidgetPage = loginPage.
				openEventWidgetByLink(formURL);
				
				if (!isWithTickets) {
					eventWidgetPage.
					openEventRegistrationPage().
					fillEventRegistrationForm(SeleneseTestCase.emailClient.getEmailBox(fname + "." + lname + CommonUtils.getRandomNumericValueFixedLength(4)),
							fname,
							lname);					
				}else{
					eventWidgetPage.openDonationPage();
				}
				
				eventWidgetPage.
				fillEventDonationForm(SeleneseTestCase.emailClient.getEmailBox(fname + "." + lname + CommonUtils.getRandomNumericValueFixedLength(4)),
					fname,
					lname,
					sup.get(CommonUtils.getRandomValueNumericFromTo(0, sup.size()-1)).addressLine1,
					sup.get(CommonUtils.getRandomValueNumericFromTo(0, sup.size()-1)).getCity(),
					sup.get(CommonUtils.getRandomValueNumericFromTo(0, sup.size()-1)).getZipCode(),
					sup.get(CommonUtils.getRandomValueNumericFromTo(0, sup.size()-1)).getState(),
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
				verifyEventSubscrIsSuccesses().
				backToLoginPage();
			}else{
				sup = new Supporter().getSupportersFromSystem(host, login, "!QAZ2wsx", amountOfDonations, "&source=FUNDRAISE,TICKETED_EVENT," );
				int ind = CommonUtils.getRandomValueNumericFromTo(0, sup.size()-1);
				formURL = urls[CommonUtils.getRandomValueNumericFromTo(0, urls.length+1)-1];
				
				
				EventWidget eventWidgetPage = loginPage.
						openEventWidgetByLink(formURL);
				if (!isWithTickets) {
					eventWidgetPage.
					openEventRegistrationPage().
					fillEventRegistrationForm(sup.get(ind).getFinalEMAIL(),
							sup.get(ind).getFirstName(),
							sup.get(ind).getLastName());					
				}else{
					eventWidgetPage.openDonationPage();
				}
				
				eventWidgetPage.
				fillEventDonationForm(sup.get(ind).getFinalEMAIL(),
						sup.get(ind).getFirstName(),
						sup.get(ind).getLastName(),
						sup.get(ind).addressLine1,
						sup.get(ind).getCity(),
						sup.get(ind).getZipCode(),
						sup.get(ind).getState(),
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
				verifyEventSubscrIsSuccesses().
				backToLoginPage();
			}
		}
	}
	
	@Parameters({"amount", "formURL", "login", "host"})
	@Test(groups = {"submitp2pEventRandom"}, retryAnalyzer = RetryAnalyzer.class)
	public void testSubmitp2pEventBySupporter(Integer amount, String formURL, String login, String host) throws Exception {
		
		int amountOfDonations = CommonUtils.getRandomValueNumericFromTo(1, amount);
		Map<Integer, Supporter> sup = new HashMap<Integer, Supporter>();
		String urls[] = CommonUtils.getArrayFromStringBySymbol(formURL, "%");
		loginPage = new LoginPage(true);
		for (int j = 0; j < amountOfDonations; j++) {
			sup = new Supporter().getSupportersFromSystem(host, login, "!QAZ2wsx", amountOfDonations, "&source=IMPORT,UI,SUBSCRIBE,PETITION,TARGETED_LETTER,API," );
			String fname = sup.get(CommonUtils.getRandomValueNumericFromTo(0, sup.size())).firstName,
					lname = sup.get(CommonUtils.getRandomValueNumericFromTo(0, sup.size())).lastorOrgName;
			Boolean isFundraiser = CommonUtils.getRandomBoolean();
			Boolean isWithTickets = CommonUtils.getRandomBoolean();//if false and isFundraiser is false then it = donation only
			logger.info("Test data:");
			logger.info("Amount of donations " + amountOfDonations);
			if (!isFundraiser && !isWithTickets) {
				logger.info("This is Donation only submition");
			}else{
				logger.info("Is event with fundraiser creation - " + isFundraiser);
				logger.info("Is event with registration - " + isWithTickets);
			}
			if (!isFundraiser) {
				formURL = urls[CommonUtils.getRandomValueNumericFromTo(0, urls.length+1)-1];
				Eventp2pWidget eventp2pWidgetPage = loginPage.
				openp2pEventWidgetByLink(formURL);
				
				if (isWithTickets) {
					eventp2pWidgetPage.
					openp2pEventRegistrationPage().
					selectFundraiserCheckBox(isFundraiser).
					selectQtyOfAttendee().
					clickNextButtonOnRegistrationTypesPage().
					fillp2pEventRegistrationForm(SeleneseTestCase.emailClient.getEmailBox(fname + "." + lname + CommonUtils.getRandomNumericValueFixedLength(4)),
							fname,
							lname).
					clickCheckOutButton();					
				}else{
					eventp2pWidgetPage.
					openp2pDonationPage();
				}
				
				eventp2pWidgetPage.
				fillp2pEventDonationForm(SeleneseTestCase.emailClient.getEmailBox(fname + "." + lname + CommonUtils.getRandomNumericValueFixedLength(4)),
					fname,
					lname,
					sup.get(CommonUtils.getRandomValueNumericFromTo(0, sup.size()-1)).addressLine1,
					sup.get(CommonUtils.getRandomValueNumericFromTo(0, sup.size()-1)).getCity(),
					sup.get(CommonUtils.getRandomValueNumericFromTo(0, sup.size()-1)).getZipCode(),
					sup.get(CommonUtils.getRandomValueNumericFromTo(0, sup.size()-1)).getState(),
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
				verifyEventSubscrIsSuccesses().
				backToLoginPage();
			}else{
				formURL = urls[CommonUtils.getRandomValueNumericFromTo(0, urls.length+1)-1];
				Eventp2pWidget eventp2pWidgetPage = loginPage.
						openp2pEventWidgetByLink(formURL);
				if (!isWithTickets) {
					eventp2pWidgetPage.
					openp2pEventRegistrationPage().
					selectFundraiserCheckBox(isFundraiser).
					clickNextButtonOnRegistrationTypesPage().
					fillFundraiserSignInForm(fname,
							lname,
							SeleneseTestCase.emailClient.getEmailBox(fname + "." + lname + CommonUtils.getRandomNumericValueFixedLength(4)),
							"!QAZ2wsx", 
							"!QAZ2wsx").
					clickCheckOutButton();					
				}else{
					eventp2pWidgetPage.
					openp2pEventRegistrationPage().
					selectFundraiserCheckBox(isFundraiser).
					selectQtyOfAttendee().
					clickNextButtonOnRegistrationTypesPage().
					fillFundraiserSignInForm(fname,
							lname,
							SeleneseTestCase.emailClient.getEmailBox(fname + "." + lname + CommonUtils.getRandomNumericValueFixedLength(4)),
							"!QAZ2wsx", 
							"!QAZ2wsx").
					fillp2pEventRegistrationForm(SeleneseTestCase.emailClient.getEmailBox(fname + "." + lname + CommonUtils.getRandomNumericValueFixedLength(4)),
									fname,
									lname).
					clickCheckOutButton();	
					}
				eventp2pWidgetPage.
				fillp2pEventDonationForm(SeleneseTestCase.emailClient.getEmailBox(fname + "." + lname + CommonUtils.getRandomNumericValueFixedLength(4)),
						fname,
						lname,
						sup.get(CommonUtils.getRandomValueNumericFromTo(0, sup.size()-1)).addressLine1,
						sup.get(CommonUtils.getRandomValueNumericFromTo(0, sup.size()-1)).getCity(),
						sup.get(CommonUtils.getRandomValueNumericFromTo(0, sup.size()-1)).getZipCode(),
						sup.get(CommonUtils.getRandomValueNumericFromTo(0, sup.size()-1)).getState(),
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
					verifyEventSubscrIsSuccesses().
					backToLoginPage();				
			}
		}
	}
	
	
	
}
