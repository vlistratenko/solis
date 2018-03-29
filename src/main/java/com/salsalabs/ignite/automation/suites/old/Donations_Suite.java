/*
package com.salsalabs.ignite.automation.suites.old;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.tests.old.ActivitiesTests;
import com.salsalabs.ignite.automation.tests.old.AdminTest;
import com.salsalabs.ignite.automation.tests.old.SettingsTests;

public class Donations_Suite extends SeleneseTestCase{

	@Test(retryAnalyzer=RetryAnalyzer.class, enabled = true, groups = {"createAdmin"}, description = "484:51:New org was NOT created")
	@Parameters({"createOrg.domainType",
		"createOrg.orgName",
		"createOrg.orgDescrption",
		"createOrg.firstName",
		"createOrg.lastName",		
		"createOrg.status",
		"createOrg.featureList" })
	public void createOrgTest(	     	
		     	String domainType,
				String orgName,
				String orgDescrption,
				String firstName,
				String lastName,		
				String status,
				String featureList){
		
		new AdminTest().createOrgTest(domainType, orgName, orgDescrption, firstName, lastName, status, featureList);	
	}
	
	@Test(retryAnalyzer=RetryAnalyzer.class, enabled = true, groups = {"createAdmin"}, dependsOnMethods="createOrgTest", description = "489:52:New Admin account was NOT confirmed")
	@Parameters({"newuser.password"})
	public void confirmAdminAccountTest(String userPassword){
		new AdminTest().confirmAdminAccountTest(userPassword);
	}
	
	@Test(retryAnalyzer=RetryAnalyzer.class, priority=30, groups = {"createAdmin"}, description = "489:52:New admin can NOT login", dependsOnMethods="confirmAdminAccountTest")
	public void loginAsNewSuperAdminTest(){
		new AdminTest().loginAsNewSuperAdminTest();
	}
	
	@Parameters({"wePayNickName", "wePayDescr", "wePayOrgType"})
	@Test(retryAnalyzer=RetryAnalyzer.class,  priority=40, groups = {"settings.wepay"}, description = "", dependsOnGroups={"createAdmin"})
	public void createWePayAcountTest(String wePayNickName, String wePayDescr, String wePayOrgType) {
		
		new SettingsTests().createWePayAcountTest(wePayNickName, wePayDescr, wePayOrgType);
	}
	
	@Parameters({"createwidget.widgetName", "createwidget.widgetDescription", "createwidget.widgetLayoutName",
		"donation.personEmail",
		"donation.personFName",
		"donation.personLName",
		"donation.personAddressLine1",
		"donation.personAddressLine2",
		"donation.personCity",
		"donation.personZip",
		"donation.donationAmount",
		"donation.nameOnCard",
		"donation.cardNumber",
		"donation.cvv",
		"donation.expiryMonth",
		"donation.expiryYear",			
		"donation.isFundraising",
		"donation.isNewsletter",
		"donation.isEmail"})
	@Test(retryAnalyzer=RetryAnalyzer.class, priority=50, groups = {"activities.createDonationForm"}, description = "",
			dependsOnGroups={"settings.wepay"}*/
/*, dataProvider = "donationData"*//*
)
	public void makeDonationTest(String widgetName, String widgetDescription, String widgetLayoutName,
			String personEmail,
			String personFName,
			String personLName,
			String personAddressLine1,
			String personAddressLine2,
			String personCity,
			String personZip,
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
		boolean recurringDonation = false;
		new ActivitiesTests().makeDonationTest(widgetName,
				widgetDescription,
				widgetLayoutName,
				personEmail,
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
				isEmail);;		
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
		"donation.personZip"})
	@Test(retryAnalyzer=RetryAnalyzer.class,  priority=60, groups = {"activities.refundDonation"}, description = "", dependsOnGroups={"activities.createDonationForm"}*/
/*, dataProvider = "donationDataForRefund"*//*
)
	public void refundDonation(String donationAmount,
			String cardNumber,
			String expiryMonth,
			String expiryYear,
			String personFName,
			String personLName,
			String personAddressLine1,
			String personAddressLine2,
			String personCity,
			String personZip) 
	{	
		boolean recurringDonation = false;
		new ActivitiesTests().refundDonation(donationAmount,
				cardNumber,
				expiryMonth,
				expiryYear,
				personFName,
				personLName,
				personAddressLine1,
				personAddressLine2,
				personCity,
				personZip,
				recurringDonation);
	}
	
	@Parameters({"createwidget.widgetName", "createwidget.widgetDescription", "createwidget.widgetLayoutName",
		"donation.personEmail",
		"donation.personFName",
		"donation.personLName",
		"donation.personAddressLine1",
		"donation.personAddressLine2",
		"donation.personCity",
		"donation.personZip",
		"donation.donationAmount",
		"donation.nameOnCard",
		"donation.cardNumber",
		"donation.cvv",
		"donation.expiryMonth",
		"donation.expiryYear",			
		"donation.isFundraising",
		"donation.isNewsletter",
		"donation.isEmail"})
	@Test(retryAnalyzer=RetryAnalyzer.class, priority=50, groups = {"activities.createRecuringDonationForm"}, description = "",
			dependsOnGroups={"settings.wepay"}*/
/*, dataProvider = "donationData"*//*
)
	public void makeDonationRecuringTest(String widgetName, String widgetDescription, String widgetLayoutName,
			String personEmail,
			String personFName,
			String personLName,
			String personAddressLine1,
			String personAddressLine2,
			String personCity,
			String personZip,
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
		boolean recurringDonation = true;
		new ActivitiesTests().makeDonationTest(widgetName,
				widgetDescription,
				widgetLayoutName,
				personEmail,
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
				isEmail);;		
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
		"donation.personZip"})
	@Test(retryAnalyzer=RetryAnalyzer.class,  priority=60, groups = {"activities.refundRecuringDonation"}, description = "", dependsOnGroups={"activities.createRecuringDonationForm"}*/
/*, dataProvider = "donationDataForRefund"*//*
)
	public void refundRecuringDonation(String donationAmount,
			String cardNumber,
			String expiryMonth,
			String expiryYear,
			String personFName,
			String personLName,
			String personAddressLine1,
			String personAddressLine2,
			String personCity,
			String personZip) 
	{	
		boolean recurringDonation = true;
		new ActivitiesTests().refundDonation(donationAmount,
				cardNumber,
				expiryMonth,
				expiryYear,
				personFName,
				personLName,
				personAddressLine1,
				personAddressLine2,
				personCity,
				personZip,
				recurringDonation);
	}
}
*/
