package com.salsalabs.ignite.automation.suites.regression;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.p2p.EventFundraiserWidgetPage;
import com.salsalabs.ignite.automation.pages.p2p.EventTeamWidgetPage;
import com.salsalabs.ignite.automation.pages.p2p.Eventp2pWidget;


/**
 * 
 *
 */
public class p2pFormFieldsTest extends SeleneseTestCase{
	
	LoginPage loginPage;
	
	@Parameters({ "login", "password"})
	@Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = { "notready_p2p.formFields.createFormWithAnonymous" }, description = "")
	public void createp2pForm(String login, String pass) throws Exception {
		loginPage = new LoginPage(true); 
		String formName = "p2p form " + CommonUtils.getUnicName();
		loginPage.
		doSuccessLogin(login, pass).
		//open("https://hq.test.igniteaction.net/#/activities/widgets/p2p/cf85b860-fc7c-4913-9608-3795c225d530?tab=compose").
		openActivitiesPage().
		openP2PPage().
		openCreateNewp2pForm().
		fillSetupStepAndGoNext(formName,
				"p2p form " + CommonUtils.getUnicName(),
							CommonUtils.getTodayDateDependsOnBrowser(""),
							"8:30am",
							CommonUtils.getTodayDateDependsOnBrowser("", 30),
							"7:00pm",
							"(GMT-06:00) Central Time",
							true).
		fillRegistrationStepAndGoNext("p2p registration " + CommonUtils.getUnicName(),
				true,
				"10",
				"5").
		clickContinueButton().
		clickContinueButton().
		clickNextButton().
		selectLayoutAndClickNext("Basic").
		openEventPageSubTab().
			/*dropOneColumnRow().
			dropVETextElement().
			dropVERegisterButtonElement().
			dropVEDonateButtonElement().
		openRegistrationSubTab().
			dropOneColumnRow().
			dropVETextElement().
			dropVEREgistrationElement().*/
		openCheckoutSubTab().
			//dropOneColumnRow().
			//dropVEFormElement().
			dropFormField("Display my donation anonymously", false).
		/*openConfirmationViewSubTab().
			dropOneColumnRow().
			dropVETextElement().
			editVETextElement("Thank You!").*/
		clickNextToEventPageButton().
		/*openPersonalFundraisingPageSubTab().
			dropOneColumnRow().
			dropVETextElement().
		openDonateSubTab().
			dropOneColumnRow().
			dropVEFormElement().
			dropVETextElement().
		openConfirmationViewSubTab().
			dropOneColumnRow().
			dropVETextElement().*/
		clickNextToTeamTabButton().
		openTeamFundraisingPageSubTab().
			/*dropOneColumnRow().
			dropVETextElement().
		openDonateSubTab().
			dropOneColumnRow().
			dropVETextElement().
			dropVEFormElement().
		openConfirmationViewSubTab().
			dropOneColumnRow().
			dropVETextElement().*/
		clickNextToAutorespondersTabButton().
		clickPublishButton().
		storeEventLink(formName);
		
		varifyAnonimusOptionOnExistedForm(CommonUtils.getParam(PropertyName.P2P_FORM_LINK));
	}
	
	/**
	 * This test requires p2p form with "Display my donation anonymously" option
	 * formURL = p2p form URL
	 * @throws Exception 
	 */
	@Parameters({ "formURL"})
	@Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = { "p2p.formFields.Anonymous" }, description = "")
	public void varifyAnonimusOptionOnExistedForm(String formURL) throws Exception {

		String donationAmount = CommonUtils.getRandomNumericValueFixedLength(2);
		//CommonUtils.setParam("fundraiserName", "Tester.Anonimus20171006144447");
		loginPage = new LoginPage(true);
		Eventp2pWidget eventp2pWidgetPage = loginPage.
				openp2pEventWidgetByLink(formURL);
				eventp2pWidgetPage.
		openp2pEventRegistrationPage().
		selectFundraiserCheckBox(true).
		clickNextButtonOnRegistrationTypesPage().
		fillFundraiserSignInForm("Tester",
				"Anonimus",
				SeleneseTestCase.emailClient.getEmailBox("Anonimus" + CommonUtils.getRandomNumericValueFixedLength(4)),
				"!QAZ2wsx", 
				"!QAZ2wsx",
				true).
		clickCheckOutButton();					
		eventp2pWidgetPage.
		fillp2pEventDonationForm(SeleneseTestCase.emailClient.getEmailBox("Anonimus" + CommonUtils.getRandomNumericValueFixedLength(4)),
				"Tester",
				"Anonimus",
				"10753 blix",
				"North holliwood",
				"91602",
				"CA",
				donationAmount,
				"Tester Testerov",
				"4111111111111111",
				"180",
				"01",
				"2022",
				true,
				true,
				true).
			checkDisplayDonationAnonymouslyOption(true).
			clickSubmitButton().
			verifyEventSubscrIsSuccesses();	
		
		eventp2pWidgetPage.open(formURL);
		
		eventp2pWidgetPage.
		findFundraiserViaSearchFieldAndClick(CommonUtils.getParam(PropertyName.LAST_FUNDRAISER_NAME)).
		verifyNameForLastDonorByDonationAmount("Anonymous", donationAmount, donationAmount);
		
		eventp2pWidgetPage.open(formURL);
		
		eventp2pWidgetPage.
		findTeamViaSearchFieldAndClick(CommonUtils.getParam(PropertyName.LAST_TEAM_NAME)).
		verifyNameForLastDonorByDonationAmount("Anonymous", donationAmount, donationAmount);
		
	}	
	
	/**
	 * This test requires p2p fundraiser form with "Display my donation anonymously" option
	 * formURL = p2p form URL
	 */
	@Parameters({ "fundraiserFormURL"})
	@Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = { "p2p.formFields.AnonymousForFundraiserPage" }, description = "")
	public void varifyAnonimusOptionOnExistedFormForFundrPage(String formURL) {

		String donationAmount = CommonUtils.getRandomNumericValueFixedLength(2);
		loginPage = new LoginPage(true);
		EventFundraiserWidgetPage eventFundraiserWidgetPage = loginPage.
				openp2pEventFundraiserWidgetByLink(formURL);
			eventFundraiserWidgetPage.
				openDonationPageOnFundraiserForm()
				.fillFundraiserDonationForm(SeleneseTestCase.emailClient.getEmailBox("Anonimus" + CommonUtils.getRandomNumericValueFixedLength(4)),
				"Tester",
				"Anonimus",
				"10753 blix",
				"North holliwood",
				"91602",
				"CA",
				donationAmount,
				"Tester Testerov",
				"4111111111111111",
				"180",
				"01",
				"2022",
				true,
				true,
				true).
			checkDisplayDonationAnonymouslyOption(true).
			clickSubmitFundraiserDonationForm().
			verifyTeamDonationIsSuccesses();	
		
			eventFundraiserWidgetPage.open(formURL);
		
		eventFundraiserWidgetPage.
		verifyNameForLastDonorByDonationAmount("Anonymous", donationAmount, donationAmount);		
	}
	
	/**
	 * This test requires p2p team form with "Display my donation anonymously" option
	 * formURL = p2p form URL
	 */
	@Parameters({ "teamFormURL"})
	@Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = { "p2p.formFields.AnonymousForTeamPage" }, description = "")
	public void varifyAnonimusOptionOnExistedFormForTeamPage(String formURL) {

		String donationAmount = CommonUtils.getRandomNumericValueFixedLength(2);
		loginPage = new LoginPage(true);
		EventTeamWidgetPage eventTeamWidgetPage = loginPage.
				openp2pEventTeamWidgetByLink(formURL);
		eventTeamWidgetPage.
				openDonationPageOnFundraiserForm()
				.fillFundraiserDonationForm(SeleneseTestCase.emailClient.getEmailBox("Anonimus" + CommonUtils.getRandomNumericValueFixedLength(4)),
				"Tester",
				"Anonimus",
				"10753 blix",
				"North holliwood",
				"91602",
				"CA",
				donationAmount,
				"Tester Testerov",
				"4111111111111111",
				"180",
				"01",
				"2022",
				true,
				true,
				true).
			checkDisplayDonationAnonymouslyOption(true).
			clickSubmitTeamDonationForm().
			verifyTeamDonationIsSuccesses();	
		
		eventTeamWidgetPage.open(formURL);
		
		eventTeamWidgetPage.
		verifyNameForLastDonotByDonationAmount("Anonymous", donationAmount, donationAmount);		
	}
	
/*
	*//**
	 * This test requires event form with "Display my donation anonymously" option
	 * formURL = event form URL
	 *//*
	@Parameters({ "eventFormURL"})
	@Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = { "event.formFields.AnonymousForEventPage" }, description = "")
	public void varifyAnonimusOptionOnExistedFormForEvent(String formURL) {

		String donationAmount = CommonUtils.getRandomNumericValueFixedLength(2);
		loginPage = new LoginPage(true);
		EventWidget eventWidgetPage = loginPage.
				openEventWidgetByLink(formURL).
				openDonationPage().
				fillEventDonationForm(SeleneseTestCase.emailClient.getEmailBox("Anonimus" + CommonUtils.getRandomNumericValueFixedLength(4)),
						"Tester",
						"Anonimus",
						"10753 blix",
						"North holliwood",
						"91602",
						"CA",
						donationAmount,
						"Tester Testerov",
						"4111111111111111",
						"180",
						"01",
						"2022").		
		checkDisplayDonationAnonymouslyOption(true).
		clickSubmitButton();
		eventWidgetPage.verifyDonationIsSuccesses();	
		
		eventWidgetPage.open(formURL);
		
		eventWidgetPage.
		verifyNameForLastDonotByDonationAmount("Anonymous", donationAmount, donationAmount);		
	}*/
	
	
}
