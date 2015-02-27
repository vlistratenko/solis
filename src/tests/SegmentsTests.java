package tests;


import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HQ.LoginPage;
import selenium.CommonUtils;
import selenium.SeleneseTestCase;

public class SegmentsTests extends SeleneseTestCase{
	
	//@Parameters({"sendEmail.From", "sendEmail.OpenAmount", "sendEmail.ClickAmount"})
	@Test( priority=10, groups = {"settings.wepay", ""}, description = "")
	public void createWePayTestTest(/*String emailFrom, Integer openAmount, Integer clickAmount*/) {
		
		LoginPage loginPage = new LoginPage();
		loginPage.
		doSuccessLogin(CommonUtils.getProperty("Admin.email"), CommonUtils.getProperty("Admin.Password")).
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
		doSuccessLogin(CommonUtils.getProperty("Admin.email"), CommonUtils.getProperty("Admin.Password")).
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
		
