package com.salsalabs.ignite.automation.suites;

import org.testng.annotations.Test;

import com.salsalabs.ignite.automation.common.RetryAnalyzer;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.common.Supporter;
import com.salsalabs.ignite.automation.pages.hq.LoginPage;
import com.salsalabs.ignite.automation.pages.hq.supporters.SupportersAddPage;
import com.salsalabs.ignite.automation.pages.hq.supporters.SupportersPage;

/**
 * <b>This test contains scenarios related to manual supporter creation (TestLink: TC6)</b>
 * @author a.hubachov
 *
 */
public class CreateSupporterManuallyTest extends SeleneseTestCase {
	private SupportersAddPage supporterAddPage;
	private SupportersPage supportersPage;
	private Supporter supporter;
	
	/**
	 * <b>Create a single supporter manually</b>
	 * <p>
	 * Steps:
	 * <ul>
	 * <li> Login into existing organization
	 * <li> Open Audience page 
	 * <li> Click on Add Supporter button, choose Add a Single Supporter form dropdown
	 * <li> Fill email, first name, last name, phone, address, city, state, zip code, facebook, twitter
	 * <li> Click on Save button
	 * <li> <font color="green"><b>Verify that record about the user is on the top of the table</b></font>
	 * </ul>
	 *  
	 */
	@Test(groups = {"createSupporterManually"}, retryAnalyzer = RetryAnalyzer.class)
	public void testCreateSupporterManually() {
		doLoginAndOpenAddSupporterPage();
		supporter = Supporter.generateSupporter();
		supporterAddPage.createNewSupporter(supporter);
		supportersPage.verifySupporterOnTopOfTableFull(supporter);
	}
	
	private void doLoginAndOpenAddSupporterPage() {
		supporterAddPage = new LoginPage().doSuccessLogin().
				openAudiencePage().openSupportersPage().openAddSupporterPage();
	}
}
