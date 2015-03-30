package com.salsalabs.ignite.automation.tests.old;


import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;

public class SegmentsTests extends SeleneseTestCase{
	
	//@Parameters({"sendEmail.From", "sendEmail.OpenAmount", "sendEmail.ClickAmount"})
	@Test(retryAnalyzer=RetryAnalyzer.class,  priority=10, groups = {"settings.wepay", ""}, description = "")
	public void createWePayTestTest(/*String emailFrom, Integer openAmount, Integer clickAmount*/) {
		
		LoginPage loginPage = new LoginPage();
		loginPage.
		doSuccessLogin(CommonUtils.getProperty(PropertyName.ADMIN_EMAIL), CommonUtils.getProperty(PropertyName.ADMIN_PASSWORD)).
		openSettingsPage().
		switchToPaymentGatewaysPage();
		

	}
	
	//NB!! DONT DELETE! WORK IN PROGRESS!!!!!!!
	
	/*@Parameters({"segmentName",
		"segmentTag",
		"addIncludeRule",
		"addExcludeRule",
		"criteriaForExcludeSupporterManually",
		"criteriaForAddSupporterManually"})
	@Test(retryAnalyzer=RetryAnalyzer.class, priority=70, groups = {"acceptanceTests.user", "createSegment.manually"}, description = "516:59:Segment was NOT created.")
	public void createSegmentTest(String segmentName,
			String segmentTag,
			String addIncludeRule,
			String addExcludeRule,
			String criteriaForExcludeSupporterManually,
			String criteriaForAddSupporterManually) {
		segmentName = segmentName + CommonUtils.getUnicName();
		LoginPage loginPage = new LoginPage();
		loginPage.
		doSuccessLogin(CommonUtils.getProperty(PropertyName.ADMIN_EMAIL), CommonUtils.getProperty(PropertyName.ADMIN_PASSWORD)).
		openAudiencePage().
		openSegmentsPage().
		openAddSegmentPage().
		createNewSegment(segmentName,
				segmentTag,
				addIncludeRule,
				addExcludeRule,
				criteriaForExcludeSupporterManually,
				criteriaForAddSupporterManually).
		checkSegmentsExists(segmentName);
		CommonUtils.setProperty("segmentName", segmentName);

	}*/
	
}
		
