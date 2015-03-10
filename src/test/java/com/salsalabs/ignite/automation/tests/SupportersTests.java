package com.salsalabs.ignite.automation.tests;


import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.supporters.SupportersPage;

public class SupportersTests extends SeleneseTestCase{
	
	@Parameters({ "createSupporter.amount"})
	@Test(retryAnalyzer=RetryAnalyzer.class, priority=40, groups = {"acceptanceTests.user", "createSupporter.manually"}, description = "494:53:Supporter was NOT created")
	public void createSupporerManually(Integer amount){
		LoginPage loginPage = new LoginPage();
		SupportersPage supportersPage = loginPage.
		doSuccessLogin(CommonUtils.getProperty(PropertyName.ADMIN_EMAIL), CommonUtils.getProperty(PropertyName.ADMIN_PASSWORD)).
		openAudiencePage().
		openSupportersPage();
		
		for (int i = 0; i < amount; i++) {
			supportersPage.
			openAddSupporterPage().
			createNewSupporter().
			checkSupporterExists(CommonUtils.getParam("supporterEmail"));
			logger.info("Supporter #" + i + " was added");
		}
		amount = amount + Integer.parseInt(CommonUtils.getProperty(PropertyName.AMOUNT_OF_SUPPORTERS)); 
		CommonUtils.setProperty(PropertyName.AMOUNT_OF_SUPPORTERS, amount.toString());
		
	}
	
}
		
