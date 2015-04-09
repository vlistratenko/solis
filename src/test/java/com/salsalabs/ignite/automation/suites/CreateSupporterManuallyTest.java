package com.salsalabs.ignite.automation.suites;

import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.common.Supporter;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.supporters.SupportersAddPage;
import com.salsalabs.ignite.automation.pages.hq.supporters.SupportersPage;

public class CreateSupporterManuallyTest extends SeleneseTestCase {
	private SupportersAddPage supporterAddPage;
	private SupportersPage supportersPage;
	private Supporter supporter;
	
	@Test(groups = {"createSupporterManually"}, retryAnalyzer = RetryAnalyzer.class)
	public void testCreateSupporterManually() {
		doLoginAndOpenAddSupporterPage();
		supporter = Supporter.generateSupporter();
		supporterAddPage.createNewSupporter(supporter);
		supportersPage.verifySupporterOnTopOfTable(supporter);
	}
	
	private void doLoginAndOpenAddSupporterPage() {
		supporterAddPage = new LoginPage().doSuccessLogin().
				openAudiencePage().openSupportersPage().openAddSupporterPage();
	}
}
