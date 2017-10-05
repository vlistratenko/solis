package com.salsalabs.ignite.automation.suites.regression;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;

public class DonationImportTest extends SeleneseTestCase {
	
	
	@Test(enabled = true,  retryAnalyzer = RetryAnalyzer.class, groups = { "donationImport" }, description = "")
	@Parameters({"login" , "Passward" })
	public void donationImportTest(String login , String pass){
		String importname = CommonUtils.getUnicName();
		String externalId=  CommonUtils.getRandomNumericValueFixedLength(5);
		boolean donationType = CommonUtils.getRandomBoolean();
		String ammount = CommonUtils.getRandomValueFromTo(10, 1000, 2);
		String expectedSourceType= importname+ " " + "(imported)";
		
		LoginPage lp = new LoginPage();
		lp.doSuccessLogin( login , pass).
		openDonationsPage().
		openDonationImportPage().
		uploadTheImportedFile(importname, login , pass, externalId, ammount, donationType).
		proceedtoTheMappingStep().
		completeImport().
		backToTransactionPage().
		findTransactionByAmmount(ammount).
		verifyDonationRecordInTable("$"+ammount+"", "Charge", donationType, expectedSourceType, "...");
		
	}
	

}
