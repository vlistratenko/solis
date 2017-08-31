package com.salsalabs.ignite.automation.suites.regression;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;

public class FeeCalculationTest extends SeleneseTestCase {
	
	@Parameters({"fundraisingFormUrlWepay" , "expectedVisaFeeValue" , "expectedMasterCardFeeValue"  , "expectedMAmexFeeValue"})
	@Test(enabled = true,  retryAnalyzer = RetryAnalyzer.class, groups = { "feeCalculation" }, description = "")
	public void verifyFeeCaclucationDonationFormWepay(String widgetUrlwepayForm, String expectedVisaFeeValue, String expectedMasterCardFeeValue, String expectedMAmexFeeValue){
		
		String email = RandomStringUtils.randomAlphabetic(5) + "@uatauto.ignite.net";
		String supporterFirstname = RandomStringUtils.randomAlphabetic(5) + "supporterFirstname";
		String supporterLastname = RandomStringUtils.randomAlphabetic(5) + "supporterLastname";
		String supporterAddress = RandomStringUtils.randomAlphabetic(5) + "supporterAddress";
		Boolean isRecurring = CommonUtils.getRandomBoolean();
		
		new LoginPage().
		openDonationWidgetByLink(widgetUrlwepayForm).
		fillTheFirstStepOfTheDonationForm(isRecurring , true).
		fillTheSecondStepOfTheDonationForm(email, supporterFirstname, supporterLastname, supporterAddress, "Juneau", "99501" , "AL").
		fillTheThirdStepOfTheDonationForm("4003830171874018" , "123" , "12" , "2020").
		verifyFeeForProvidedCardType("Visa", expectedVisaFeeValue).
		fillTheThirdStepOfTheDonationForm("5496198584584769" , "123" , "11" , "2021").
		verifyFeeForProvidedCardType("MasterCard", expectedMasterCardFeeValue).
		fillTheThirdStepOfTheDonationForm("378282246310005" , "123" , "10" , "2020").
		verifyFeeForProvidedCardType("Amex Card", expectedMAmexFeeValue);
		
	}
	
	@Parameters({"fundraisingFormUrlCardConnect" , "expectedVisaFeeValueCardConnect" , "expectedMasterCardFeeValueCardConnect"  , "expectedMAmexFeeValueCardConnect"})
	@Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = { "feeCalculation" }, description = "")
	public void verifyFeeCaclucationDonationFormCardConnect(String widgetUrlCardConnectForm, String expectedVisaFeeValueCardConnect, String expectedMasterCardFeeValueCardConnect, String expectedMAmexFeeValueCardConnect){
		
		String email = RandomStringUtils.randomAlphabetic(5) + "@uatauto.ignite.net";
		String supporterFirstname = RandomStringUtils.randomAlphabetic(5) + "supporterFirstname";
		String supporterLastname = RandomStringUtils.randomAlphabetic(5) + "supporterLastname";
		String supporterAddress = RandomStringUtils.randomAlphabetic(5) + "supporterAddress";
		Boolean isRecurring = CommonUtils.getRandomBoolean();
		
		new LoginPage().
		openDonationWidgetByLink(widgetUrlCardConnectForm).
		fillTheFirstStepOfTheDonationForm(isRecurring, false).
		fillTheSecondStepOfTheDonationForm(email, supporterFirstname, supporterLastname, supporterAddress, "Juneau", "99501" , "AL").
		fillTheThirdStepOfTheDonationForm("4003830171874018" , "123" , "12" , "2020").
		verifyFeeForProvidedCardType("Visa", expectedVisaFeeValueCardConnect).
		fillTheThirdStepOfTheDonationForm("5496198584584769" , "123" , "11" , "2021").
		verifyFeeForProvidedCardType("MasterCard", expectedMasterCardFeeValueCardConnect).
		fillTheThirdStepOfTheDonationForm("378282246310005" , "123" , "10" , "2020").
		verifyFeeForProvidedCardType("Amex Card", expectedMAmexFeeValueCardConnect);
	}
	

	@Test(enabled = true,  retryAnalyzer = RetryAnalyzer.class, groups = { "feeCalculation" }, description = "")
	@Parameters({"fundraisingFormUrlCardConnect" , "login" , "Passward" , "expectedFeeValueCardConnect", "widgenName" , "expectedFeeValueForAllCardTypesCardConnect" })
	public void transactionDetaisTest(String widgetUrlCardConnectForm, String login , String passward,  String expectedAmountWithFee, String widgetName, String expectedFee){
		
		String email = RandomStringUtils.randomAlphabetic(5) + "@uatauto.ignite.net";
		String supporterFirstname = RandomStringUtils.randomAlphabetic(3) + "Fname";
		String supporterLastname = RandomStringUtils.randomAlphabetic(3) + "Lname";
		String supporterAddress = RandomStringUtils.randomAlphabetic(5) + "Address";
		Boolean isRecurring = CommonUtils.getRandomBoolean();

		LoginPage lp = new LoginPage();
		lp.openDonationWidgetByLink(widgetUrlCardConnectForm).
		fillTheFirstStepOfTheDonationForm(isRecurring, false).
		fillTheSecondStepOfTheDonationForm(email, supporterFirstname, supporterLastname, supporterAddress, "Juneau", "99501" , "AL").
		fillTheThirdStepOfTheDonationForm("5496198584584769" , "123" , "11" , "2021").
		clickDonationButtonNewForms();
		lp.doSuccessLogin( login , passward).
		openDonationsPage().
		verifyDonationRecordInTable(expectedAmountWithFee, "Charge", isRecurring, widgetName, supporterFirstname + " " +supporterLastname).
		openDonation(widgetName).
		verifyDonationAmmountAndFee(expectedAmountWithFee, expectedFee);
	
		
		
		
		
	}
	

}
