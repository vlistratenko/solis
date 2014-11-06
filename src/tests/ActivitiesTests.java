package tests;


import objects.Supporter;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.HQ.LoginPage;
import selenium.CommonUtils;
import selenium.EmailClient;
import selenium.SeleneseTestCase;

public class ActivitiesTests extends SeleneseTestCase{
	
	@Parameters({"createwidget.widgetName", "createwidget.widgetDescription", 
		"donation.personEmail",
		"donation.personFName",
		"donation.personLName",
		"donation.personAddressLine1",
		"donation.personAddressLine2",
		"donation.personCity",
		"donation.personZip",
		"donation.recurringDonation",
		"donation.donationAmount",
		"donation.nameOnCard",
		"donation.cardNumber",
		"donation.cvv",
		"donation.expiryMonth",
		"donation.expiryYear",			
		"donation.isFundraising",
		"donation.isNewsletter",
		"donation.isEmail"})
	@Test( priority=10, groups = {"activities.createDonationForm"}, description = "")
	public void makeDonationTest(String widgetName, String widgetDescription,
			String personEmail,
			String personFName,
			String personLName,
			String personAddressLine1,
			String personAddressLine2,
			String personCity,
			String personZip,
			Boolean recurringDonation,
			String donationAmount,
			String nameOnCard,
			String cardNumber,
			String cvv,
			String expiryMonth,
			String expiryYear,			
			Boolean isFundraising,
			Boolean isNewsletter,
			Boolean isEmail) 
	{		
		widgetName = widgetName + CommonUtils.getUnicName();
		String widgetTitle = "Title " + CommonUtils.getUnicName();
		LoginPage loginPage = new LoginPage();
		personEmail = EmailClient.getEmailBox(personEmail+CommonUtils.getUnicName());
		
		String status = "CHARGE";		
		loginPage.
		doSuccessLogin(CommonUtils.getProperty("Admin.email"), CommonUtils.getProperty("Admin.Password")).
		openActivitiesPage().
		openFundraisingWidgetPage().
		openAddDonationWidgetPage().
		createDonationWidgetSetupStep(widgetName, widgetDescription).
		createDonationWidgetDesignWidgetStep().
		hosteWidgetOnLocalPage(widgetTitle, true).
		openDonationWidget().
		fillDonationForm(personEmail,
				personFName,
				personLName,
				personAddressLine1,
				personAddressLine2,
				personCity,
				personZip,
				recurringDonation,
				donationAmount,
				nameOnCard,
				cardNumber,
				cvv,
				expiryMonth,
				expiryYear,
				isFundraising,
				isNewsletter,
				isEmail).
		clickDonationButton().
		verifyDonationIsSuccesses().//if fail rerun for other value
		backToFundraisingWidgetPage().
		openDonationsPage().
		verifyDonationRecordInTable(donationAmount+".00", status, recurringDonation, widgetName, personFName + " " + personLName).
		openAudiencePage().
		openSupportersPage().
		searchSupporter(personEmail).
		checkSupporterExists(personEmail);
		
		if (recurringDonation) {
			CommonUtils.setProperty("recurringWidgetName", widgetName);
		}else{
			CommonUtils.setProperty("oneTimeWidgetName", widgetName);
		}
		CommonUtils.checkAndFail("makeDonationTest");
	}
	
	@Parameters({"donation.donationAmount",
		"donation.cardNumber",
		"donation.expiryMonth",
		"donation.expiryYear",	
		"donation.personFName",
		"donation.personLName",
		"donation.personAddressLine1",
		"donation.personAddressLine2",		
		"donation.personCity",
		"donation.personZip",
		"donation.recurringDonation"})
	@Test( priority=10, groups = {"activities.refundDonation"}, description = "")
	public void refundDonation(String donationAmount,
			String cardNumber,
			String expiryMonth,
			String expiryYear,
			String personFName,
			String personLName,
			String personAddressLine1,
			String personAddressLine2,
			String personCity,
			String personZip,
			Boolean recurringDonation) 
	{
		LoginPage loginPage = new LoginPage();
		String status= "CHARGE";		
		String widgetName;		
		if (recurringDonation) {
			widgetName = CommonUtils.getProperty("recurringWidgetName");
			//status = "AUTHORIZE";
		}else{
			widgetName = CommonUtils.getProperty("oneTimeWidgetName");
			//status = "CHARGE";
		}
		
		loginPage.
		doSuccessLogin(CommonUtils.getProperty("Admin.email"), CommonUtils.getProperty("Admin.Password")).
		openDonationsPage().
		openDonation(widgetName).
		verifyDonation(donationAmount,
				donationAmount,
				CommonUtils.anonimizeCreditCardNumber(cardNumber) + " Exp " + expiryMonth + "/" + expiryYear,
				widgetName,
				personFName + " " + personLName,
				personAddressLine1,
				personAddressLine2,
				personCity,
				personZip,
				donationAmount+".00",
				"CREDIT_CARD",
				status,
				recurringDonation).
		refundDonation(recurringDonation).
		verifyDonationAfterRefund("Refund", recurringDonation);
	}

	@Parameters({"createwidget.widgetName"})
	@Test( priority=10, groups = {"activities.createSubscribeForm"}, description = "")
	public void subscribeSupporterTest(String widgetName) 
	{	
		Supporter supporter = new Supporter();
		widgetName = widgetName + CommonUtils.getUnicName();
		String widgetTitle = "Title " + CommonUtils.getUnicName();
		LoginPage loginPage = new LoginPage();
		String supporterEmail = EmailClient.getEmailBox(new Supporter().subscribedEmail + CommonUtils.getUnicName());

		loginPage.
		doSuccessLogin(CommonUtils.getProperty("Admin.email"), CommonUtils.getProperty("Admin.Password")).
		openActivitiesPage().
		openSubscribeWidgetsPage().
		openAddSubscribeWidgetPage().
		fillFieldsSubscribeWidgetStepOne(widgetName, "TestWDescription").
		fillFieldsSubscribeWidgetStepTwo().
		hosteWidgetOnLocalPage(widgetTitle, true).
		openSubscribeWidget().
		fillSubscribeWidget(supporterEmail, supporter.firstName, supporter.lastName, supporter.City, supporter.zipCode).
		verifySubscriptionIsSuccesses().
		backToSubscribegWidgetPage().
		openAudiencePage().
		openSupportersPage().
		searchSupporter(supporterEmail).
		checkSupporterExists(supporterEmail).
		openSupporterDetailsPage().
		verifySupporterData(
				supporter.subscribedEmail,
				supporter.firstName,
				supporter.lastName,
				"",
				"",
				supporter.City,
				supporter.zipCode,
				"",
				"",
				"Subscribed");
		
		CommonUtils.setProperty("subscribeWidget", widgetName);
		CommonUtils.setProperty("subscribedSupporter", supporterEmail);
		CommonUtils.checkAndFail("subscribeSupporterTest");
	}


}
