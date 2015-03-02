package tests;


import org.testng.annotations.Test;

import pages.hq.LoginPage;
import core.util.CommonUtils;
import core.util.PropertyName;
import core.util.SeleneseTestCase;

public class SegmentsTests extends SeleneseTestCase{
	
	//@Parameters({"sendEmail.From", "sendEmail.OpenAmount", "sendEmail.ClickAmount"})
	@Test( priority=10, groups = {"settings.wepay", ""}, description = "")
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
	@Test(priority=70, groups = {"acceptanceTests.user", "createSegment.manually"}, description = "516:59:Segment was NOT created.")
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
		
