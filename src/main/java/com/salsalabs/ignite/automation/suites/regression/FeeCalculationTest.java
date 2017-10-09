package com.salsalabs.ignite.automation.suites.regression;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.activities.DonationWidget;

public class FeeCalculationTest extends SeleneseTestCase {
	
	  /**
	   * ($100 + $0.30)/ (1 - 0.022) == $102.56 : Wepay Fee Calculation Example for Visa
	   * (100 + 0.2) /(1-0.0289 ) = 100.2 / 0.9711 = 103.18 Card Connect Fee Calculation example For Visa
     */
	@Parameters({"fundraisingFormUrlWepay"})
	@Test(enabled = true,  retryAnalyzer = RetryAnalyzer.class, groups = { "feeCalculation" }, description = "")
	public void verifyFeeCaclucationDonationFormWepay(String widgetUrlwepayForm){
		
		String email = RandomStringUtils.randomAlphabetic(5) + ".74580786@mailosaur.in";
		String supporterFirstname = RandomStringUtils.randomAlphabetic(5) + "supporterFirstname";
		String supporterLastname = RandomStringUtils.randomAlphabetic(5) + "supporterLastname";
		String supporterAddress = RandomStringUtils.randomAlphabetic(5) + "supporterAddress";
		Boolean isRecurring = CommonUtils.getRandomBoolean();
		double randomDonationAmmount = CommonUtils.getRandomValueNumericFromTo(10, 200);
		
		new LoginPage().
		openDonationWidgetByLink(widgetUrlwepayForm).
		fillTheFirstStepOfTheDonationForm(String.valueOf(randomDonationAmmount), isRecurring , true).
		fillTheSecondStepOfTheDonationForm(email, supporterFirstname, supporterLastname, supporterAddress, "Juneau", "99501" , "AL").
		fillTheThirdStepOfTheDonationForm("4003830171874018" , "123" , "12" , "2020").
		veriFyfeeCalculationForWepay("Visa" , randomDonationAmmount).
		fillTheThirdStepOfTheDonationForm("5496198584584769" , "123" , "11" , "2021").
		veriFyfeeCalculationForWepay("MasterCard" , randomDonationAmmount).
		fillTheThirdStepOfTheDonationForm("378282246310005" , "123" , "10" , "2020").
		veriFyfeeCalculationForWepay("Amex" , randomDonationAmmount);
		
	}
	
	@Parameters({"fundraisingFormUrlCardConnect"})
	@Test(enabled = true, retryAnalyzer = RetryAnalyzer.class, groups = { "feeCalculation" }, description = "")
	public void verifyFeeCaclucationDonationFormCardConnect(String widgetUrlCardConnectForm){
		
		String email = RandomStringUtils.randomAlphabetic(5) + ".74580786@mailosaur.in";
		String supporterFirstname = RandomStringUtils.randomAlphabetic(5) + "supporterFirstname";
		String supporterLastname = RandomStringUtils.randomAlphabetic(5) + "supporterLastname";
		String supporterAddress = RandomStringUtils.randomAlphabetic(5) + "supporterAddress";
		Boolean isRecurring = CommonUtils.getRandomBoolean();
		double randomDonationAmmount = CommonUtils.getRandomValueNumericFromTo(10, 200);
		
		new LoginPage().
		openDonationWidgetByLink(widgetUrlCardConnectForm).
		fillTheFirstStepOfTheDonationForm(String.valueOf(randomDonationAmmount), isRecurring, true).
		fillTheSecondStepOfTheDonationForm(email, supporterFirstname, supporterLastname, supporterAddress, "Juneau", "99501" , "AL").
		fillTheThirdStepOfTheDonationForm("4003830171874018" , "123" , "12" , "2020").
		veriFyfeeCalculationForCardConnect("Visa", randomDonationAmmount).
		fillTheThirdStepOfTheDonationForm("5496198584584769" , "123" , "11" , "2021").
		veriFyfeeCalculationForCardConnect("MasterCard", randomDonationAmmount).
		fillTheThirdStepOfTheDonationForm("378282246310005" , "123" , "10" , "2020").
		veriFyfeeCalculationForCardConnect("Amex", randomDonationAmmount);
	}
	

	@Test(enabled = true,  retryAnalyzer = RetryAnalyzer.class, groups = { "feeCalculation" }, description = "")
	@Parameters({"fundraisingFormUrlCardConnect" , "login" , "Passward" , "widgenName" })
	public void transactionDetaisTest(String widgetUrlCardConnectForm, String login , String passward,  String widgetName){
		
		String email = RandomStringUtils.randomAlphabetic(5) + ".74580786@mailosaur.in";
		String supporterFirstname = RandomStringUtils.randomAlphabetic(3) + "Fname";
		String supporterLastname = RandomStringUtils.randomAlphabetic(3) + "Lname";
		String supporterAddress = RandomStringUtils.randomAlphabetic(5) + "Address";
		Boolean isRecurring = CommonUtils.getRandomBoolean();
		double randomDonationAmmount = CommonUtils.getRandomValueNumericFromTo(10, 200);
		String expectedAmountWithFee = new DonationWidget().calculateAmountWithFeeForCardConnect(randomDonationAmmount);
		String expectedFeeValueForAllCardTypesCardConnect = new DonationWidget().calculateFeeForCardConnect(randomDonationAmmount);

		LoginPage lp = new LoginPage();
		lp.openDonationWidgetByLink(widgetUrlCardConnectForm).
		fillTheFirstStepOfTheDonationForm(String.valueOf(randomDonationAmmount) , isRecurring, true).
		fillTheSecondStepOfTheDonationForm(email, supporterFirstname, supporterLastname, supporterAddress, "Juneau", "99501" , "AL").
		fillTheThirdStepOfTheDonationForm("5496198584584769" , "123" , "11" , "2021").
		clickDonationButtonNewForms();
		lp.doSuccessLogin( login , passward).
		openDonationsPage().
		verifyDonationRecordInTable(expectedAmountWithFee, "Charge", isRecurring, widgetName, supporterFirstname + " " +supporterLastname).
		openDonation(widgetName).
		verifyDonationAmmountAndFee(expectedAmountWithFee, expectedFeeValueForAllCardTypesCardConnect);
	
		
		
		
		
	}
	

}
