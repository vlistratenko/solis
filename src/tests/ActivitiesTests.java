package tests;


import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.hq.LoginPage;
import pages.hq.activities.DonationWidget;
import core.util.CommonUtils;
import core.util.EmailClient;
import core.util.PropertyName;
import core.util.SeleneseTestCase;
import core.util.Supporter;

public class ActivitiesTests extends SeleneseTestCase{
	
	@Parameters({"createwidget.widgetName", "createwidget.widgetDescription", "createwidget.widgetLayoutName", "donation.recurringDonation"})
	//@Test( priority=10, groups = {"activities.createDonationForm"}, description = "")
	public DonationWidget createDonationWidgetTest(String widgetName, String widgetDescription, String widgetLayoutName, boolean recurringDonation) 
	{		
		//widgetName = widgetName + CommonUtils.getUnicName();
		String widgetTitle = "Title " + CommonUtils.getUnicName();
		LoginPage loginPage = new LoginPage();
			
		DonationWidget widget = loginPage.
		doSuccessLogin(CommonUtils.getProperty(PropertyName.ADMIN_EMAIL), CommonUtils.getProperty(PropertyName.ADMIN_PASSWORD)).
		openActivitiesPage().
		openFundraisingWidgetPage().
		openAddDonationWidgetPage().
		createDonationWidgetSetupStep(widgetName, widgetDescription).
		selectLayoutForDanationWidgetStep(widgetLayoutName).
		createDonationWidgetDesignWidgetStep().
		hosteWidgetOnLocalPage(widgetTitle, true).
		saveDonationWidgetLink().
		openDonationWidget();
		
		if (recurringDonation) {
			CommonUtils.setProperty(PropertyName.RECURRING_WIDGET_NAME, widgetName);
		}else{
			CommonUtils.setProperty(PropertyName.ONETIME_WIDGET_NAME, widgetName);
		}
		
		return widget;
	}
	
	@Parameters({"createwidget.widgetName", "createwidget.widgetDescription", "createwidget.widgetLayoutName",
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
	public void makeDonationTest(String widgetName, String widgetDescription, String widgetLayoutName,
			String personEmail,
			String personFName,
			String personLName,
			String personAddressLine1,
			String personAddressLine2,
			String personCity,
			String personZip,
			boolean recurringDonation,
			String donationAmount,
			String nameOnCard,
			String cardNumber,
			String cvv,
			String expiryMonth,
			String expiryYear,			
			boolean isFundraising,
			boolean isNewsletter,
			boolean isEmail) 
	{		
		widgetName = widgetName + CommonUtils.getUnicName();
		personEmail = EmailClient.getEmailBox(personEmail+CommonUtils.getUnicName());
		String status = "COMPLETE";
		if (!recurringDonation) {
			status = "CHARGE";
		}		
		createDonationWidgetTest(widgetName, widgetDescription, widgetLayoutName, recurringDonation).
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
			CommonUtils.setProperty(PropertyName.RECURRING_WIDGET_NAME, widgetName);
		}else{
			CommonUtils.setProperty(PropertyName.ONETIME_WIDGET_NAME, widgetName);
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
			boolean recurringDonation) 
	{
		LoginPage loginPage = new LoginPage();
		String status= "";		
		String widgetName;		
		if (recurringDonation) {
			widgetName = CommonUtils.getProperty(PropertyName.RECURRING_WIDGET_NAME);
			status = "COMPLETE";
		}else{
			widgetName = CommonUtils.getProperty(PropertyName.ONETIME_WIDGET_NAME);
			status = "CHARGE";
		}
		
		loginPage.
		doSuccessLogin(CommonUtils.getProperty(PropertyName.ADMIN_EMAIL), CommonUtils.getProperty(PropertyName.ADMIN_PASSWORD)).
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

	@Parameters({"createwidget.widgetName", "createwidget.widgetDescription", "createwidget.widgetLayoutName"})
	@Test( priority=10, groups = {"activities.createSubscribeForm"}, description = "")
	public void createSubscribeWidget(String widgetName, String widgetDescription, String widgetLayoutName) 
	{	
		widgetName = widgetName + CommonUtils.getUnicName();
		String widgetTitle = "Title " + CommonUtils.getUnicName();
		LoginPage loginPage = new LoginPage();
		
		loginPage.
		doSuccessLogin(CommonUtils.getProperty(PropertyName.ADMIN_EMAIL), CommonUtils.getProperty(PropertyName.ADMIN_PASSWORD)).
		openActivitiesPage().
		openSubscribeWidgetsPage().
		openAddSubscribeWidgetPage().
		fillFieldsSubscribeWidgetStepOne(widgetName, widgetDescription).
		selectLayoutForSubscribeWidgetStep(widgetLayoutName).
		fillFieldsSubscribeWidgetStepTwo().
		hosteWidgetOnLocalPage(widgetTitle, true).
		openSubscribeWidget().
		backToSubscribegWidgetPage();
		
		CommonUtils.setProperty(PropertyName.SUBSCRIBE_WIDGET, widgetName);
		CommonUtils.checkAndFail("subscribeSupporterTest");
	}
	
	@Parameters({"supporter.email"})
	@Test( priority=10, groups = {"activities.subscribeSupporter"}, description = "", dependsOnMethods = {"createSubscribeWidget"})
	public void subscribeSupporterTest(@Optional("") String supporterEmail) 
	{	
		Supporter supporter = new Supporter();
		LoginPage loginPage = new LoginPage(true);
		if (!supporterEmail.contains("@")) {
			supporterEmail = EmailClient.getEmailBox(new Supporter().getSubscribedEmail() + CommonUtils.getUnicName());
			supporter.setcPhone("");
			supporter.setAddressLine1("");
			supporter.setFacebook("");
			supporter.setTwitter("");
		}
		loginPage.
		openSubscribeWidgetByLink().
		fillSubscribeWidget(supporterEmail, supporter.getFirstName(), supporter.getLastName(), supporter.getCity(), supporter.getZipCode()).
		verifySubscriptionIsSuccesses().
		backToLoginPage().
		doSuccessLogin(CommonUtils.getProperty(PropertyName.ADMIN_EMAIL), CommonUtils.getProperty(PropertyName.ADMIN_PASSWORD)).
		openAudiencePage().
		openSupportersPage().
		searchSupporter(supporterEmail).
		checkSupporterExists(supporterEmail).
		openSupporterDetailsPage().
		verifySupporterData(
				supporterEmail,
				supporter.getFirstName(),
				supporter.getLastName(),
				supporter.getcPhone(),
				supporter.getAddressLine1(),
				supporter.getCity(),				
				supporter.getZipCode(),
				supporter.getFacebook(),
				supporter.getTwitter(),
				"Subscribed");
				CommonUtils.setProperty(PropertyName.SUBSCRIBED_SUPPORTER, supporterEmail);
		CommonUtils.checkAndFail("subscribeSupporterTest");
	}


}
