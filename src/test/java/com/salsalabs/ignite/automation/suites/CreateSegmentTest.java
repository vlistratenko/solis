package com.salsalabs.ignite.automation.suites;


import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.common.Supporter;
import com.salsalabs.ignite.automation.pages.hq.HomePage;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.supporters.SupportersAddPage;
import com.salsalabs.ignite.automation.pages.hq.supporters.SupportersPage;

public class CreateSegmentTest extends SeleneseTestCase {
	
	private SupportersAddPage supporterAddPage;
	private SupportersPage supportersPage;
	private Supporter supporter;
	


	@Parameters({  "segmentName", "addIncludeRule", "addExcludeRule" , "criteriaForExcludeSupporterManually","criteriaForAddSupporterManually"}) 
	 

@Test(enabled = true, groups = { "createSegment"}, retryAnalyzer = RetryAnalyzer.class)

	
	public void createCustomSegmentTest(String segmentName,String addIncludeRule,  String addExcludeRule, String criteriaForExcludeSupporterManually,String criteriaForAddSupporterManually){



LoginPage loginPage = new LoginPage();
loginPage.
doSuccessLogin(CommonUtils.getProperty(PropertyName.ADMIN_EMAIL),  CommonUtils.getProperty(PropertyName.ADMIN_PASSWORD));

HomePage homePage = new HomePage();
segmentName = segmentName + CommonUtils.getUnicName();
CommonUtils.setProperty("segmentName", segmentName);

homePage.
openAudiencePage().
openSegmentsPage().
openAddSegmentPage().
createNewCustomSegment(segmentName, addIncludeRule, addExcludeRule, criteriaForExcludeSupporterManually, criteriaForAddSupporterManually).
checkSegmentsExists(segmentName);


	
}
	
	/*
	@Test(enabled = true, groups = { "createSegment"}, retryAnalyzer = RetryAnalyzer.class)
	public void segmentruleVerification() {
		
		
		LoginPage loginPage = new LoginPage();
		loginPage.doSuccessLogin(CommonUtils.getProperty(PropertyName.ADMIN_EMAIL),  CommonUtils.getProperty(PropertyName.ADMIN_PASSWORD));
		HomePage homePage = new HomePage();
		supporterAddPage = homePage.
		openAudiencePage().
		openSupportersPage().
		openAddSupporterPage();
		
		
		supporter = Supporter.generateSupporter();
		supportersPage = supporterAddPage.createNewSupporter(supporter);
	
		
	}*/


	
}
